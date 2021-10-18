package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AppointmentCommandTestUtil.showAppointmentAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.util.SampleDataUtil;


public class DeleteAppointmentCommandTest {
    private final Model model = new ModelManager(SampleDataUtil.getSampleAddressBook(), getTypicalAppointmentList(),
            getTypicalAppointmentList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Appointment appointmentToDelete = model.getFilteredAppointmentList().get(
                INDEX_FIRST_APPOINTMENT.getZeroBased());
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(INDEX_FIRST_APPOINTMENT);

        String expectedMessage = String.format(DeleteAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS,
                appointmentToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getAppointmentBook(),
                model.getArchivedAppointmentBook(), new UserPrefs());
        expectedModel.deleteAppointment(appointmentToDelete);

        assertCommandSuccess(deleteAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(deleteAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }


    @Test
    @Disabled
    // TODO: Failing at :57, see related TODO at AppointmentCommandTestUtil.java:26
    public void execute_validIndexFilteredList_success() {
        showAppointmentAtIndex(model, INDEX_FIRST_APPOINTMENT);

        Appointment appointmentToDelete = model.getFilteredAppointmentList().get(
                INDEX_FIRST_APPOINTMENT.getZeroBased());
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(INDEX_FIRST_APPOINTMENT);

        String expectedMessage = String.format(DeleteAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS,
                appointmentToDelete);

        Model expectedModel = new ModelManager(new AddressBook(), model.getAppointmentBook(),
                model.getArchivedAppointmentBook(), new UserPrefs());
        expectedModel.deleteAppointment(appointmentToDelete);
        showNoAppointment(expectedModel);

        assertCommandSuccess(deleteAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    @Disabled
    // TODO: Failing at :73, see related TODO at AppointmentCommandTestUtil.java:26
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAppointmentAtIndex(model, INDEX_FIRST_APPOINTMENT);

        Index outOfBoundIndex = INDEX_SECOND_APPOINTMENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAppointmentBook().getAppointmentList().size());

        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(deleteAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test public void equals() {
        DeleteAppointmentCommand deleteFirstAppointmentCommand = new DeleteAppointmentCommand(INDEX_FIRST_PERSON);
        DeleteAppointmentCommand deleteSecondAppointmentCommand = new DeleteAppointmentCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstAppointmentCommand.equals(deleteFirstAppointmentCommand));

        // same values -> returns true
        DeleteAppointmentCommand deleteFirstAppointmentCommandCopy = new DeleteAppointmentCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstAppointmentCommand.equals(deleteFirstAppointmentCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstAppointmentCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstAppointmentCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstAppointmentCommand.equals(deleteSecondAppointmentCommand));
    }



    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoAppointment(Model model) {
        model.updateFilteredAppointmentList(p -> false);

        assertTrue(model.getFilteredAppointmentList().isEmpty());
    }
}
