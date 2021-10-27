package seedu.docit.logic.parser;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.AddAppointmentCommand;
import seedu.docit.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object
 */
public class AddAppointmentCommandParser implements AppointmentParser<AddAppointmentCommand> {
    public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
    public static final DateTimeFormatter FANCY_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");

    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand and returns an
     * AddAppointmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parseAppointmentCommand(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_INDEX,
                CliSyntax.PREFIX_DATETIME);

        if (!ParserUtil.hasAllPrefixes(argMultimap, CliSyntax.PREFIX_INDEX, CliSyntax.PREFIX_DATETIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));
        }

        Index patientIndex;
        try {
            patientIndex = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE), pe);
        }

        String datetime = argMultimap.getValue(CliSyntax.PREFIX_DATETIME).get();

        LocalDateTime localDateTime;
        try {
            localDateTime = ParserUtil.parseDateTime(datetime, DEFAULT_DATE_TIME_FORMATTER);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddAppointmentCommand.MESSAGE_USAGE), pe);
        }

        return new AddAppointmentCommand(patientIndex, localDateTime);
    }


}
