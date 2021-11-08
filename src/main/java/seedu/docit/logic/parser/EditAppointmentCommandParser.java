package seedu.docit.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_INDEX;

import java.time.LocalDateTime;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.EditAppointmentCommand;
import seedu.docit.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.docit.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditAppointmentCommand object
 */
public class EditAppointmentCommandParser implements AppointmentParser<EditAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAppointmentCommand
     * and returns an EditAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentCommand parseAppointmentCommand(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_DATETIME);

        Index apmtIndex;
        try {
            apmtIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        if (argMultimap.getValue(PREFIX_INDEX).isPresent()) {
            String index = argMultimap.getValue(CliSyntax.PREFIX_INDEX).get().trim();

            if (index.equals("")) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE));
            }

            Index patientIndex;
            try {
                patientIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            } catch (ParseException pe) {
                throw new ParseException(
                    pe.getMessage().concat("\n").concat(EditAppointmentCommand.MESSAGE_USAGE), pe);
            }

            editAppointmentDescriptor.setPatientIndex(patientIndex);
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_DATETIME).isPresent()) {
            String datetime = argMultimap.getValue(CliSyntax.PREFIX_DATETIME).get().trim();

            if (datetime.equals("")) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE));
            }

            LocalDateTime localDateTime;
            try {
                localDateTime = ParserUtil.parseDateTime(argMultimap.getValue(CliSyntax.PREFIX_DATETIME).get(),
                    ParserUtil.INPUT_DATE_TIME_FORMATTER);
            } catch (ParseException pe) {
                throw new ParseException(
                    pe.getMessage().concat("\n").concat(EditAppointmentCommand.MESSAGE_USAGE), pe);
            }
            editAppointmentDescriptor.setDatetime(localDateTime);
        }

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAppointmentCommand(apmtIndex, editAppointmentDescriptor);
    }
}
