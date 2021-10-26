package seedu.address.logic.parser.prescription;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VOLUME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.prescription.AddPrescriptionCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AddPrescriptionCommand object
 */
public class AddPrescriptionCommandParser implements Parser<AddPrescriptionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPrescriptionCommand and returns an
     * AddPrescriptionCommand object for execution.
     * @param args Argument input for the command
     * @return A valid AddPrescriptionCommand
     * @throws ParseException when the input is invalid
     */
    @Override
    public AddPrescriptionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INDEX,
                PREFIX_NAME, PREFIX_DURATION, PREFIX_VOLUME);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX, PREFIX_VOLUME, PREFIX_NAME, PREFIX_DURATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPrescriptionCommand.MESSAGE_USAGE));
        }
        try {
            Index appointmentIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            String medicineName = argMultimap.getValue(PREFIX_NAME).get();
            String duration = argMultimap.getValue(PREFIX_DURATION).get();
            String volume = argMultimap.getValue(PREFIX_VOLUME).get();
            return new AddPrescriptionCommand(appointmentIndex, medicineName, volume, duration);

        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPrescriptionCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given {@code
     * ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
