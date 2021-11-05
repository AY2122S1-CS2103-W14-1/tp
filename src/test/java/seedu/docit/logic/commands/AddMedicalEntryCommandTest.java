package seedu.docit.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.docit.testutil.Assert.assertThrows;
import static seedu.docit.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.model.AppointmentBook;
import seedu.docit.model.ArchivedAppointmentBook;
import seedu.docit.model.Model;
import seedu.docit.model.ModelManager;
import seedu.docit.model.UserPrefs;
import seedu.docit.model.patient.MedicalHistory;
import seedu.docit.model.patient.Patient;
import seedu.docit.testutil.PatientBuilder;

public class AddMedicalEntryCommandTest {
    private static final String DEFAULT_MEDICAL = "high blood pressure";

    private final Model model = new ModelManager(getTypicalAddressBook(), new AppointmentBook(),
        new ArchivedAppointmentBook(), new UserPrefs());



    @Test
    public void constructor_nullMedicalEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMedicalEntryCommand(null, null));
    }

    @Test
    public void execute_validMedicalEntry_addSuccessful() throws CommandException {
        Patient editedPatient =
            new PatientBuilder(getTypicalAddressBook().getPatientOfIndex(INDEX_FIRST_PATIENT)).build();

        AddMedicalEntryCommand addMedicalEntryCommand = new AddMedicalEntryCommand(INDEX_FIRST_PATIENT,
            new MedicalHistory(DEFAULT_MEDICAL));

        CommandResult commandResult = addMedicalEntryCommand.execute(model);

        CommandResult expectedCommandResult = new CommandResult(AddMedicalEntryCommand.MESSAGE_SUCCESS
                                                    + editedPatient);

        assertEquals(expectedCommandResult, commandResult);
    }

    @Test
    public void execute_patientToAddDoesNotExist_throwsCommandException() throws CommandException {
        int n = getTypicalAddressBook().getPatientList().size();
        Index invalidIndex = Index.fromOneBased(n + 1);
        Index otherInvalidIndex = Index.fromZeroBased(n);

        AddMedicalEntryCommand addMedicalEntryCommand = new AddMedicalEntryCommand(invalidIndex,
            new MedicalHistory(DEFAULT_MEDICAL));
        AddMedicalEntryCommand otherAddMedicalEntryCommand = new AddMedicalEntryCommand(otherInvalidIndex,
            new MedicalHistory(DEFAULT_MEDICAL));

        assertThrows(CommandException.class, () -> addMedicalEntryCommand.execute(model));
        assertThrows(CommandException.class, () -> otherAddMedicalEntryCommand.execute(model));
    }
}
