package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteMedicalEntryCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteMedicalEntry object
 */
public class DeleteMedicalEntryCommandParser implements PatientParser<DeleteMedicalEntryCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddMedicalEntryCommand
     * and returns an AddPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMedicalEntryCommand parsePatientCommand(String args) throws ParseException {
        requireNonNull(args);

        Index patientIndex;
        Index medicalIndex;

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_INDEX);

        System.out.println(args);

        try {
            patientIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            medicalIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteMedicalEntryCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.getValue(PREFIX_INDEX).isPresent()
            || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteMedicalEntryCommand.MESSAGE_USAGE));
        }

        return new DeleteMedicalEntryCommand(patientIndex, medicalIndex);
    }
}
