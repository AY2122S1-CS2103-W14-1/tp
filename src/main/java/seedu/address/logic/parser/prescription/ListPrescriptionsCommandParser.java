package seedu.address.logic.parser.prescription;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_INDEX;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.prescription.ListPrescriptionsCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;





public class ListPrescriptionsCommandParser implements Parser<ListPrescriptionsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListPrescriptionsCommand and returns a
     * ListPrescriptionsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListPrescriptionsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_APPOINTMENT_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListPrescriptionsCommand.MESSAGE_USAGE));
        }

        Index appointmentIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_APPOINTMENT_INDEX).get());

        ListPrescriptionsCommand newCommand = new ListPrescriptionsCommand(appointmentIndex);

        return newCommand;
    }
}
