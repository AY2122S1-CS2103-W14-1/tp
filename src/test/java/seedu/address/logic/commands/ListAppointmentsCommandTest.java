package seedu.address.logic.commands;

import static seedu.address.logic.commands.AppointmentCommandTestUtil.showAppointmentAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ListAppointmentCommand.
 */
public class ListAppointmentsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), getTypicalAppointmentList(), new UserPrefs());
        expectedModel = new ModelManager(new AddressBook(), model.getAppointmentBook(), new UserPrefs());
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
        showAppointmentAtIndex(model, INDEX_FIRST_APPOINTMENT);
        assertCommandSuccess(new ListPatientCommand(), model, ListAppointmentsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
