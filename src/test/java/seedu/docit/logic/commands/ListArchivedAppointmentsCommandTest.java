package seedu.docit.logic.commands;

import static seedu.docit.logic.commands.AppointmentCommandTestUtil.showAppointmentAtIndex;
import static seedu.docit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.docit.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.docit.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.docit.model.ArchivedAppointmentBook;
import seedu.docit.model.Model;
import seedu.docit.model.ModelManager;
import seedu.docit.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListArchivedAppointmentCommand.
 */
public class ListArchivedAppointmentsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalAppointmentBook(),
            new ArchivedAppointmentBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getAppointmentBook(),
            new ArchivedAppointmentBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListArchivedAppointmentsCommand(), model,
                ListArchivedAppointmentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showAppointmentAtIndex(model, INDEX_FIRST_APPOINTMENT);
        assertCommandSuccess(new ListArchivedAppointmentsCommand(), model,
            ListArchivedAppointmentsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
