package seedu.address.logic.parser.prescription;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.prescription.DeletePrescriptionCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;



public class DeletePrescriptionCommandParser implements Parser<DeletePrescriptionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeletePrescriptionCommand and returns a
     * DeletePrescriptionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePrescriptionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_APPOINTMENT_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_APPOINTMENT_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePrescriptionCommand.MESSAGE_USAGE));
        }



        try {
            Index appointmentIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_APPOINTMENT_INDEX).get());
            String medicineName = argMultimap.getValue(PREFIX_NAME).get();
            return new DeletePrescriptionCommand(appointmentIndex, medicineName);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePrescriptionCommand.MESSAGE_USAGE), pe);
        }
    }
}
