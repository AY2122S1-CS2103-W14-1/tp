package seedu.docit.logic.commands;

import static seedu.docit.testutil.Assert.assertThrows;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.model.AppointmentBook;
import seedu.docit.model.ArchivedAppointmentBook;
import seedu.docit.model.Model;
import seedu.docit.model.ModelManager;
import seedu.docit.model.UserPrefs;
import seedu.docit.model.patient.Patient;

public class DeleteMedicalEntryTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new AppointmentBook(),
        new ArchivedAppointmentBook(), new UserPrefs());

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteMedicalEntryCommand(null, null));
    }

    @Test
    public void execute_patientDoesNotExist_throwsCommandException() throws CommandException {
        int numberOfPatients = getTypicalAddressBook().getPatientList().size();
        int countOfMedicalEntriesFromFirstPatient = getTypicalAddressBook().getPatientOfIndex(Index.fromOneBased(1))
                                                                           .getMedicalHistory().size();

        Index validMedicalIndex = Index.fromOneBased(countOfMedicalEntriesFromFirstPatient);

        Index invalidPatientIndex = Index.fromOneBased(numberOfPatients + 1);
        Index otherInvalidPatientIndex = Index.fromZeroBased(numberOfPatients);

        DeleteMedicalEntryCommand deleteMedicalEntryCommand =
            new DeleteMedicalEntryCommand(invalidPatientIndex, validMedicalIndex);
        DeleteMedicalEntryCommand otherDeleteMedicalEntryCommand =
            new DeleteMedicalEntryCommand(otherInvalidPatientIndex, validMedicalIndex);

        assertThrows(CommandException.class, () -> deleteMedicalEntryCommand.execute(model));
        assertThrows(CommandException.class, () -> otherDeleteMedicalEntryCommand.execute(model));
    }

    @Test
    public void execute_medicalEntryDoesNotExist_throwsCommandException() throws CommandException {
        Index validPatientIndex = Index.fromOneBased(1);
        int countOfMedicalEntriesFromFirstPatient = getTypicalAddressBook().getPatientOfIndex(validPatientIndex)
            .getMedicalHistory().size();

        Index tooLargeMedical = Index.fromOneBased(countOfMedicalEntriesFromFirstPatient + 1);


        DeleteMedicalEntryCommand deleteMedicalEntryCommand =
            new DeleteMedicalEntryCommand(validPatientIndex, tooLargeMedical);

        assertThrows(CommandException.class, () -> deleteMedicalEntryCommand.execute(model));
    }

    @Test
    public void execute_emptyMedicalHistory_throwsCommandException() throws CommandException {
        Index validPatientIndex = Index.fromOneBased(1);
        int countOfMedicalEntriesFromFirstPatient = getTypicalAddressBook().getPatientOfIndex(validPatientIndex)
            .getMedicalHistory().size();

        Patient targetPatient = getTypicalAddressBook().getPatientOfIndex(validPatientIndex);
        Patient firstPatient = targetPatient;

        // delete medical entries
        for (int i = 0; i < countOfMedicalEntriesFromFirstPatient; i++) {
            firstPatient = firstPatient.deleteMedicalHistory(Index.fromZeroBased(i));
        }

        model.setPatient(targetPatient, firstPatient);

        Index tooLargeMedical = Index.fromOneBased(countOfMedicalEntriesFromFirstPatient);

        DeleteMedicalEntryCommand deleteMedicalEntryCommand =
            new DeleteMedicalEntryCommand(validPatientIndex, tooLargeMedical);

        assertThrows(CommandException.class, () -> deleteMedicalEntryCommand.execute(model));
    }

}
