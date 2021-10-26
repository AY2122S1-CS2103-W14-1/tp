package seedu.docit.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.EditPatientCommand;
import seedu.docit.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.docit.logic.parser.exceptions.ParseException;
import seedu.docit.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditPatientCommand object
 */
public class EditPatientCommandParser implements PatientParser<EditPatientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPatientCommand
     * and returns an EditPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPatientCommand parsePatientCommand(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_MEDICAL);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPatientCommand.MESSAGE_USAGE), pe);
        }

        EditPatientDescriptor editPatientDescriptor = new EditPatientDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPatientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPatientDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPatientDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPatientDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_MEDICAL).isPresent()) {
            editPatientDescriptor
                    .setMedicalHistory(ParserUtil.parseMedicalHistory(argMultimap.getAllValues(PREFIX_MEDICAL)));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPatientDescriptor::setTags);

        if (!editPatientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPatientCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPatientCommand(index, editPatientDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
