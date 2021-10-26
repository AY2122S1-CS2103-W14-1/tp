package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import java.util.stream.Stream;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMedicalEntryCommand;
import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.MedicalHistory;

/**
 * Parses input arguments and creates a new AddMedicalEntry object
 */
public class AddMedicalEntryCommandParser implements PatientParser<AddMedicalEntryCommand>  {
    /**
     * Parses the given {@code String} of arguments in the context of the AddPatientCommand
     * and returns an AddPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMedicalEntryCommand parsePatientCommand(String args) throws ParseException {
        requireNonNull(args);

        Index index;

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_MEDICAL);

        System.out.println(args);

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            System.out.println(argMultimap.getValue(PREFIX_MEDICAL).isPresent());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPatientCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.getValue(PREFIX_MEDICAL).isPresent()
            || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMedicalEntryCommand.MESSAGE_USAGE));
        }

        MedicalHistory medicalHistory = ParserUtil.parseMedicalHistory(argMultimap.getAllValues(PREFIX_MEDICAL));

        return new AddMedicalEntryCommand(index, medicalHistory);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
