package seedu.docit.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.docit.commons.core.Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW;
import static seedu.docit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.docit.testutil.TypicalPatients.CARL;
import static seedu.docit.testutil.TypicalPatients.ELLE;
import static seedu.docit.testutil.TypicalPatients.FIONA;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.docit.model.AppointmentBook;
import seedu.docit.model.ArchivedAppointmentBook;
import seedu.docit.model.Model;
import seedu.docit.model.ModelManager;
import seedu.docit.model.UserPrefs;
import seedu.docit.model.patient.PatientContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPatientCommand}.
 */
public class FindPatientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new AppointmentBook(),
            new ArchivedAppointmentBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new AppointmentBook(),
            new ArchivedAppointmentBook(), new UserPrefs());

    @Test
    public void equals() {
        PatientContainsKeywordsPredicate firstPredicate =
                new PatientContainsKeywordsPredicate(Collections.singletonList("first"));
        PatientContainsKeywordsPredicate secondPredicate =
                new PatientContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPatientCommand findFirstCommand = new FindPatientCommand(firstPredicate);
        FindPatientCommand findSecondCommand = new FindPatientCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPatientCommand findFirstCommandCopy = new FindPatientCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPatientFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 0);
        PatientContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindPatientCommand command = new FindPatientCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPatientList());
    }

    @Test
    public void execute_multipleKeywords_multiplePatientsFound() {
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 3);
        PatientContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindPatientCommand command = new FindPatientCommand(predicate);
        expectedModel.updateFilteredPatientList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPatientList());
    }

    /**
     * Parses {@code userInput} into a {@code PatientContainsKeywordsPredicate}.
     */
    private PatientContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PatientContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
