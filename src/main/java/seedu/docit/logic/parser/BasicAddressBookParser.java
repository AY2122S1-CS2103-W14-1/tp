package seedu.docit.logic.parser;

import static seedu.docit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.docit.logic.commands.BasicCommand;
import seedu.docit.logic.commands.ClearCommand;
import seedu.docit.logic.commands.ExitCommand;
import seedu.docit.logic.commands.HelpCommand;
import seedu.docit.logic.parser.exceptions.ParseException;

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
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
