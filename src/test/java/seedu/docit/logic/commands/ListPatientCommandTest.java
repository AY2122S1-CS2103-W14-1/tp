package seedu.docit.logic.commands;

import static seedu.docit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.docit.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.docit.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.docit.model.AppointmentBook;
import seedu.docit.model.ArchivedAppointmentBook;
import seedu.docit.model.Model;
import seedu.docit.model.ModelManager;
import seedu.docit.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListPatientCommand.
 */
public class ListPatientCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new AppointmentBook(),
                new ArchivedAppointmentBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new AppointmentBook(),
                new ArchivedAppointmentBook(), new UserPrefs());
    }

    @Test public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPatientCommand(), model, ListPatientCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test public void execute_listIsFiltered_showsEverything() {
        showPatientAtIndex(model, INDEX_FIRST_PATIENT);
        assertCommandSuccess(new ListPatientCommand(), model, ListPatientCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
