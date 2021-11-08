package seedu.docit.logic.parser.prescription;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.prescription.AddPrescriptionCommand;
import seedu.docit.logic.parser.AppointmentParser;
import seedu.docit.logic.parser.ArgumentMultimap;
import seedu.docit.logic.parser.ArgumentTokenizer;
import seedu.docit.logic.parser.CliSyntax;
import seedu.docit.logic.parser.ParserUtil;
import seedu.docit.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AddPrescriptionCommand object
 */
public class AddPrescriptionCommandParser implements AppointmentParser<AddPrescriptionCommand> {
    private static final String EMPTY_FIELD_ERROR_MESSAGE = "%1$s fields cannot be blank.";
    public static final String MEDICINE_EMPTY_FIELD_ERROR_MESSAGE =
            String.format(EMPTY_FIELD_ERROR_MESSAGE, "Medicine name");
    public static final String VOLUME_EMPTY_FIELD_ERROR_MESSAGE =
            String.format(EMPTY_FIELD_ERROR_MESSAGE, "Volume");
    public static final String DURATION_EMPTY_FIELD_ERROR_MESSAGE =
            String.format(EMPTY_FIELD_ERROR_MESSAGE, "Duration");

    /**
     * Parses the given {@code String} of arguments in the context of the AddPrescriptionCommand and returns an
     * AddPrescriptionCommand object for execution.
     * @param args Argument input for the command
     * @return A valid AddPrescriptionCommand
     * @throws ParseException when the input is invalid
     */
    @Override
    public AddPrescriptionCommand parseAppointmentCommand(String args) throws ParseException {
        Index index;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DURATION, CliSyntax.PREFIX_VOLUME);

        if (!ParserUtil.hasAllPrefixes(argMultimap, CliSyntax.PREFIX_VOLUME,
                CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_DURATION) || argMultimap.getPreamble().isBlank()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPrescriptionCommand.MESSAGE_USAGE));
        }
        Index appointmentIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        String medicineName = argMultimap.getValue(CliSyntax.PREFIX_NAME).get();
        String duration = argMultimap.getValue(CliSyntax.PREFIX_DURATION).get();
        String volume = argMultimap.getValue(CliSyntax.PREFIX_VOLUME).get();

        if (medicineName.isBlank()) {
            throw new ParseException(MEDICINE_EMPTY_FIELD_ERROR_MESSAGE);
        }
        if (volume.isBlank()) {
            throw new ParseException(VOLUME_EMPTY_FIELD_ERROR_MESSAGE);
        }
        if (duration.isBlank()) {
            throw new ParseException(DURATION_EMPTY_FIELD_ERROR_MESSAGE);
        }

        return new AddPrescriptionCommand(appointmentIndex, medicineName, volume, duration);
    }
}
