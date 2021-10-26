package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AppointmentCommand;
import seedu.address.logic.commands.ArchiveAppointmentCommand;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.ListAppointmentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AppointmentBookParser {
    /**
     * Parses user input of appointment command for execution.
     * @param commandWord command word
     * @param arguments arguments of command
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public AppointmentCommand parseAppointmentCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parseAppointmentCommand(arguments);
        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parseAppointmentCommand(arguments);
        case ArchiveAppointmentCommand.COMMAND_WORD:
            return new ArchiveAppointmentCommandParser().parseAppointmentCommand(arguments);
        case ListAppointmentsCommand.COMMAND_WORD:
            return new ListAppointmentsCommand();
        default:
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}
