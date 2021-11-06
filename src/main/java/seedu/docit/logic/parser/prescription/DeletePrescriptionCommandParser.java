package seedu.docit.logic.parser.prescription;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.prescription.DeletePrescriptionCommand;
import seedu.docit.logic.parser.AppointmentParser;
import seedu.docit.logic.parser.ArgumentMultimap;
import seedu.docit.logic.parser.ArgumentTokenizer;
import seedu.docit.logic.parser.CliSyntax;
import seedu.docit.logic.parser.ParserUtil;
import seedu.docit.logic.parser.exceptions.ParseException;

public class DeletePrescriptionCommandParser implements AppointmentParser<DeletePrescriptionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeletePrescriptionCommand and returns a
     * DeletePrescriptionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePrescriptionCommand parseAppointmentCommand(String args) throws ParseException {
        Index index;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME);

        if (!ParserUtil.hasAllPrefixes(argMultimap, CliSyntax.PREFIX_NAME)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePrescriptionCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw pe;
        }

        String medicineName = argMultimap.getValue(CliSyntax.PREFIX_NAME).get();

        if (medicineName.isBlank()) {
            throw new ParseException("Medicine fields cannot be blank.");
        }
        return new DeletePrescriptionCommand(index, medicineName);
    }
}
