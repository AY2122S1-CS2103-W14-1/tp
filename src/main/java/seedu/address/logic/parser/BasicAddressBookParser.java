package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.BasicCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class BasicAddressBookParser {
    /**
     * Parses user input of basic command for execution.
     * @param commandWord command word
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public BasicCommand parseBasicCommand(String commandWord) throws ParseException {
        switch (commandWord) {
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        default:
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}
