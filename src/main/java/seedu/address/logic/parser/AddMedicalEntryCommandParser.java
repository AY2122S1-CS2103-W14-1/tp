package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMedicalEntryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MedicalHistory;

/**
 * Parses input arguments and creates a new AddMedicalEntry object
 */
public class AddMedicalEntryCommandParser implements PatientParser<AddMedicalEntryCommand>  {
    /**
     * Parses the given {@code String} of arguments in the context of the AddMedicalEntryCommand
     * and returns an AddPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMedicalEntryCommand parsePatientCommand(String args) throws ParseException {
        requireNonNull(args);

        Index index;

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_MEDICAL);

        System.out.println(args);

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            System.out.println(argMultimap.getValue(PREFIX_MEDICAL).isPresent());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMedicalEntryCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.getValue(PREFIX_MEDICAL).isPresent()
            || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMedicalEntryCommand.MESSAGE_USAGE));
        }

        MedicalHistory medicalHistory = ParserUtil.parseMedicalHistory(argMultimap.getAllValues(PREFIX_MEDICAL));
        System.out.println(medicalHistory.display(""));

        return new AddMedicalEntryCommand(index, medicalHistory);
    }

}
