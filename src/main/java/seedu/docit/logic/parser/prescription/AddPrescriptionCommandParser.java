package seedu.docit.logic.parser.prescription;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.prescription.AddPrescriptionCommand;
import seedu.docit.logic.parser.ArgumentMultimap;
import seedu.docit.logic.parser.ArgumentTokenizer;
import seedu.docit.logic.parser.CliSyntax;
import seedu.docit.logic.parser.Parser;
import seedu.docit.logic.parser.ParserUtil;
import seedu.docit.logic.parser.exceptions.ParseException;


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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_INDEX,
                CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DURATION, CliSyntax.PREFIX_VOLUME);

        if (!ParserUtil.hasAllPrefixes(argMultimap, CliSyntax.PREFIX_INDEX, CliSyntax.PREFIX_VOLUME,
                CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DURATION) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPrescriptionCommand.MESSAGE_USAGE));
        }
        try {
            Index appointmentIndex = ParserUtil.parseIndex(argMultimap.getValue(CliSyntax.PREFIX_INDEX).get());
            String medicineName = argMultimap.getValue(CliSyntax.PREFIX_NAME).get();
            String duration = argMultimap.getValue(CliSyntax.PREFIX_DURATION).get();
            String volume = argMultimap.getValue(CliSyntax.PREFIX_VOLUME).get();

            if (medicineName.isBlank() || duration.isBlank() || volume.isBlank()) {
                throw new ParseException("Medicine/Duration/Volume fields cannot be blank.");
            }
            return new AddPrescriptionCommand(appointmentIndex, medicineName, volume, duration);

        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPrescriptionCommand.MESSAGE_USAGE), pe);
        }
    }

}
