package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.logic.commands.ListPatientCommand;
import seedu.address.logic.commands.PatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class PatientBookParser {
    /**
     * Parses user input of patient command for execution.
     *
     * @param commandWord command word
     * @param arguments arguments of command
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public PatientCommand parsePatientCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddPatientCommand.COMMAND_WORD:
            return new AddPatientCommandParser().parsePatientCommand(arguments);
        case EditPatientCommand.COMMAND_WORD:
            return new EditPatientCommandParser().parsePatientCommand(arguments);
        case DeletePatientCommand.COMMAND_WORD:
            return new DeletePatientCommandParser().parsePatientCommand(arguments);
        case FindPatientCommand.COMMAND_WORD:
            return new FindPatientCommandParser().parsePatientCommand(arguments);
        case ListPatientCommand.COMMAND_WORD:
            return new ListPatientCommand();
        default:
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}