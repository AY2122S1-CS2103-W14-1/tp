package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.*;
import seedu.address.model.person.Patient;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeletePatientCommand}.
 */
public class DeletePatientCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new AppointmentBook(),
            new ArchivedAppointmentBook(), new UserPrefs());


    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePatientCommand deleteCommand = new DeletePatientCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PERSON_SUCCESS, patientToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new AppointmentBook(),
                new ArchivedAppointmentBook(), new UserPrefs());
        expectedModel.deletePatient(patientToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        DeletePatientCommand deleteCommand = new DeletePatientCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePatientCommand deleteCommand = new DeletePatientCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PERSON_SUCCESS, patientToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new AppointmentBook(),
                new ArchivedAppointmentBook(), new UserPrefs());
        expectedModel.deletePatient(patientToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeletePatientCommand deleteCommand = new DeletePatientCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test public void equals() {
        DeletePatientCommand deleteFirstCommand = new DeletePatientCommand(INDEX_FIRST_PERSON);
        DeletePatientCommand deleteSecondCommand = new DeletePatientCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePatientCommand deleteFirstCommandCopy = new DeletePatientCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPatientList(p -> false);

        assertTrue(model.getFilteredPatientList().isEmpty());
    }
}
