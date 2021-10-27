package seedu.docit.logic.parser;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.ArchiveAppointmentCommand;
import seedu.docit.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ArchiveAppointmentCommand object
 */
public class ArchiveAppointmentCommandParser implements AppointmentParser<ArchiveAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ArchiveAppointmentCommand and returns a
     * DeleteAppointmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ArchiveAppointmentCommand parseAppointmentCommand(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ArchiveAppointmentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ArchiveAppointmentCommand.MESSAGE_USAGE), pe);
        }
    }

}
