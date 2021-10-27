package seedu.docit.logic.parser.prescription;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.prescription.ListPrescriptionsCommand;
import seedu.docit.logic.parser.ArgumentMultimap;
import seedu.docit.logic.parser.ArgumentTokenizer;
import seedu.docit.logic.parser.CliSyntax;
import seedu.docit.logic.parser.Parser;
import seedu.docit.logic.parser.ParserUtil;
import seedu.docit.logic.parser.exceptions.ParseException;


public class ListPrescriptionsCommandParser implements Parser<ListPrescriptionsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListPrescriptionsCommand and returns a
     * ListPrescriptionsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListPrescriptionsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_INDEX);

        if (!ParserUtil.hasAllPrefixes(argMultimap, CliSyntax.PREFIX_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListPrescriptionsCommand.MESSAGE_USAGE));
        }

        Index appointmentIndex = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_INDEX).get());

        ListPrescriptionsCommand newCommand = new ListPrescriptionsCommand(appointmentIndex);

        return newCommand;
    }
}
