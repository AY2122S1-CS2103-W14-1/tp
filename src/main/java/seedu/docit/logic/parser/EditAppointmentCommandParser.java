package seedu.docit.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.EditAppointmentCommand;
import seedu.docit.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.docit.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditAppointmentCommand object
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAppointmentCommand
     * and returns an EditAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DATETIME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            Index patientIndex;

            try {
                patientIndex = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
            } catch (ParseException pe) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE), pe);
            }

            editAppointmentDescriptor.setPatientIndex(patientIndex);
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_DATETIME).isPresent()) {
            editAppointmentDescriptor.setDatetime(
                ParserUtil.parseDateTime(argMultimap.getValue(CliSyntax.PREFIX_DATETIME).get(),
                    ParserUtil.INPUT_DATE_TIME_FORMATTER));
        }

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAppointmentCommand(index, editAppointmentDescriptor);
    }
}
