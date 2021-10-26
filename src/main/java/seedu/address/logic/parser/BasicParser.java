package seedu.address.logic.parser;

import seedu.address.logic.commands.BasicCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a BasicParser that is able to parse BASIC user input into a {@code BasicCommand} of type {@code T}.
 */
public interface BasicParser<T extends BasicCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
