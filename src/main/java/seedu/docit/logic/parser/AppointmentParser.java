package seedu.docit.logic.parser;

import seedu.docit.logic.commands.AppointmentCommand;
import seedu.docit.logic.parser.exceptions.ParseException;

/**
 * Represents a AppointmentParser that is able to parse
 * appointment-related user input into a {@code AppointmentCommand} of type {@code T}.
 */
public interface AppointmentParser<T extends AppointmentCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parseAppointmentCommand(String userInput) throws ParseException;
}
