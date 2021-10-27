package seedu.docit.logic.parser;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Set;

import seedu.docit.logic.commands.AddPatientCommand;
import seedu.docit.logic.parser.exceptions.ParseException;
import seedu.docit.model.patient.Address;
import seedu.docit.model.patient.Email;
import seedu.docit.model.patient.MedicalHistory;
import seedu.docit.model.patient.Name;
import seedu.docit.model.patient.Patient;
import seedu.docit.model.patient.Phone;
import seedu.docit.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddPatientCommand object
 */
public class AddPatientCommandParser implements PatientParser<AddPatientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPatientCommand
     * and returns an AddPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPatientCommand parsePatientCommand(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE,
                    CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_ADDRESS,
                    CliSyntax.PREFIX_TAG, CliSyntax.PREFIX_MEDICAL);

        if (!ParserUtil.hasAllPrefixes(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_ADDRESS,
                CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));
        MedicalHistory medicalHistory = ParserUtil.parseMedicalHistory(argMultimap
                .getAllValues(CliSyntax.PREFIX_MEDICAL));

        Patient patient = new Patient(name, phone, email, address, tagList, medicalHistory);

        return new AddPatientCommand(patient);
    }
}
