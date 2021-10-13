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

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;



public class ArchiveAppointmentCommandTest {
    private final Model model = new ModelManager(new AddressBook(), getTypicalAppointmentList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Appointment appointmentToArchive = model.getFilteredAppointmentList().get(
                INDEX_FIRST_APPOINTMENT.getZeroBased());
        ArchiveAppointmentCommand archiveAppointmentCommand = new ArchiveAppointmentCommand(INDEX_FIRST_APPOINTMENT);

        String expectedMessage = String.format(ArchiveAppointmentCommand.MESSAGE_ARCHIVE_APPOINTMENT_SUCCESS,
                appointmentToArchive);

        ModelManager expectedModel = new ModelManager(new AddressBook(), model.getAppointmentBook(), new UserPrefs());
        expectedModel.archiveAppointment(appointmentToArchive);

        assertCommandSuccess(archiveAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        ArchiveAppointmentCommand archiveAppointmentCommand = new ArchiveAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(archiveAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test public void execute_validIndexFilteredList_success() {
        showAppointmentAtIndex(model, INDEX_FIRST_APPOINTMENT);

        Appointment appointmentToArchive = model.getFilteredAppointmentList().get(
                INDEX_FIRST_APPOINTMENT.getZeroBased());
        ArchiveAppointmentCommand archiveAppointmentCommand = new ArchiveAppointmentCommand(INDEX_FIRST_APPOINTMENT);

        String expectedMessage = String.format(ArchiveAppointmentCommand.MESSAGE_ARCHIVE_APPOINTMENT_SUCCESS,
                appointmentToArchive);

        Model expectedModel = new ModelManager(new AddressBook(), model.getAppointmentBook(), new UserPrefs());
        expectedModel.archiveAppointment(appointmentToArchive);
        showNoAppointment(expectedModel);

        assertCommandSuccess(archiveAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAppointmentAtIndex(model, INDEX_FIRST_APPOINTMENT);

        Index outOfBoundIndex = INDEX_SECOND_APPOINTMENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAppointmentBook().getAppointmentList().size());

        ArchiveAppointmentCommand archiveAppointmentCommand = new ArchiveAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(archiveAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test public void equals() {
        ArchiveAppointmentCommand archiveFirstAppointmentCommand = new ArchiveAppointmentCommand(INDEX_FIRST_PERSON);
        ArchiveAppointmentCommand archiveSecondAppointmentCommand = new ArchiveAppointmentCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(archiveFirstAppointmentCommand.equals(archiveFirstAppointmentCommand));

        // same values -> returns true
        ArchiveAppointmentCommand archiveFirstAppointmentCommandCopy =
                new ArchiveAppointmentCommand(INDEX_FIRST_PERSON);
        assertTrue(archiveFirstAppointmentCommand.equals(archiveFirstAppointmentCommandCopy));

        // different types -> returns false
        assertFalse(archiveFirstAppointmentCommand.equals(1));

        // null -> returns false
        assertFalse(archiveFirstAppointmentCommand.equals(null));

        // different person -> returns false
        assertFalse(archiveFirstAppointmentCommand.equals(archiveSecondAppointmentCommand));
    }



    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoAppointment(Model model) {
        model.updateFilteredAppointmentList(p -> false);

        assertTrue(model.getFilteredAppointmentList().isEmpty());
    }
}
