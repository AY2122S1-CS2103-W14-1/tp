package seedu.docit.logic.commands;

import static seedu.docit.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.docit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.docit.model.AppointmentBook;
import seedu.docit.model.ArchivedAppointmentBook;
import seedu.docit.model.Model;
import seedu.docit.model.ModelManager;
import seedu.docit.model.UserPrefs;
import seedu.docit.model.patient.Patient;
import seedu.docit.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddPatientCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new AppointmentBook(),
                new ArchivedAppointmentBook(), new UserPrefs());
    }

    @Test
    public void execute_newPatient_success() {
        Patient validPatient = new PatientBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new AppointmentBook(),
                new ArchivedAppointmentBook(), new UserPrefs());
        expectedModel.addPatient(validPatient);

        assertCommandSuccess(new AddPatientCommand(validPatient), model,
                String.format(AddPatientCommand.MESSAGE_SUCCESS, validPatient), expectedModel);
    }

    @Test
    public void execute_duplicatePatient_throwsCommandException() {
        Patient patientInList = model.getAddressBook().getPatientList().get(0);
        assertCommandFailure(new AddPatientCommand(patientInList), model, AddPatientCommand.MESSAGE_DUPLICATE_PATIENT);
    }

}
