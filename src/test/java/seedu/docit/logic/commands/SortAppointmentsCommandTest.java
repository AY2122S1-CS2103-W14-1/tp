package seedu.docit.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.docit.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.model.ArchivedAppointmentBook;
import seedu.docit.model.Model;
import seedu.docit.model.ModelManager;
import seedu.docit.model.UserPrefs;

public class SortAppointmentsCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalAppointmentBook(),
            new ArchivedAppointmentBook(), new UserPrefs());
    }

    @Test
    public void execute_sortAppointments_successful() throws CommandException {
        String expectedMessage = SortAppointmentsCommand.MESSAGE_SORT_APPOINTMENT_SUCCESS;
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        Command command = new SortAppointmentsCommand();
        CommandResult commandResult = command.execute(model);
        assertEquals(expectedCommandResult, commandResult);
    }

    @Test
    public void equals_sortAppointmentsCommand_successful() {
        Command command = new SortAppointmentsCommand();
        Command otherCommand = new SortAppointmentsCommand();
        assertEquals(command, otherCommand);
    }

    @Test
    public void equals_sortAppointmentsCommand_failure() {
        Command command = new SortAppointmentsCommand();
        Command otherCommand = new ListAppointmentsCommand();
        assertFalse(command.equals(otherCommand));
    }
}
