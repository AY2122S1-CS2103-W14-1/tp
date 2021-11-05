package seedu.docit.logic.commands;

import static seedu.docit.logic.commands.AppointmentCommandTestUtil.showAppointmentWithPatientAtIndex;
import static seedu.docit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.docit.testutil.TypicalAppointments.getTypicalAppointmentList;
import static seedu.docit.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.docit.model.AddressBook;
import seedu.docit.model.Model;
import seedu.docit.model.ModelManager;
import seedu.docit.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ListAppointmentCommand.
 */
public class ListAppointmentsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), getTypicalAppointmentList(),
                getTypicalAppointmentList(), new UserPrefs());
        expectedModel = new ModelManager(new AddressBook(), model.getAppointmentBook(),
                model.getArchivedAppointmentBook(), new UserPrefs());
    }

    @Test
    @Disabled("Not implemented yet, currently list is shown in user feedback")
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListAppointmentsCommand(), model, ListAppointmentsCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    @Disabled("Not implemented yet, currently list is shown in user feedback")
    public void execute_listIsFiltered_showsEverything() {
        showAppointmentWithPatientAtIndex(model, INDEX_FIRST_APPOINTMENT);
        assertCommandSuccess(new ListPatientCommand(), model, ListAppointmentsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
