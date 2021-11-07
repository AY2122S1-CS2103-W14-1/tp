package seedu.docit.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.docit.logic.commands.CommandTestUtil.DESC_AMY_APPT;
import static seedu.docit.logic.commands.CommandTestUtil.DESC_BOB_APPT;
import static seedu.docit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.docit.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.docit.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.docit.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.docit.model.AppointmentBook;
import seedu.docit.model.ArchivedAppointmentBook;
import seedu.docit.model.Model;
import seedu.docit.model.ModelManager;
import seedu.docit.model.UserPrefs;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.testutil.TypicalAppointments;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditAppointmentCommand.
 */
public class EditAppointmentCommandTest {

    private final AppointmentBook fullApptBook = TypicalAppointments.getTypicalAppointmentBook();
    private final Model model = new ModelManager(getTypicalAddressBook(), fullApptBook,
            new ArchivedAppointmentBook(), new UserPrefs());

    @BeforeAll
    public static void resetTypicalAppointments() {
        TypicalAppointments.resetPrescriptions();
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditAppointmentCommand editCommand = new EditAppointmentCommand(
                INDEX_FIRST_PATIENT, new EditAppointmentDescriptor());
        Appointment editedAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_PATIENT.getZeroBased());

        // no change occurs when there is no field to edit
        String expectedMessage = String.format(
                EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), fullApptBook,
                        new ArchivedAppointmentBook(), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final EditAppointmentCommand standardCommand = new EditAppointmentCommand(INDEX_FIRST_PATIENT, DESC_AMY_APPT);

        // same values -> returns true
        EditAppointmentDescriptor copyDescriptor = new EditAppointmentDescriptor(DESC_AMY_APPT);
        EditAppointmentCommand commandWithSameValues = new EditAppointmentCommand(INDEX_FIRST_PATIENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_SECOND_PATIENT, DESC_AMY_APPT)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_FIRST_PATIENT, DESC_BOB_APPT)));
    }
}
