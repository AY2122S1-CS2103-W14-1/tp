package seedu.docit.logic.parser.prescription;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_APPOINTMENT_INDEX;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_PRESCRIPTION_DURATION;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_PRESCRIPTION_MEDICINE;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_PRESCRIPTION_VOLUME;
import static seedu.docit.logic.parser.AppointmentCommandParserTestUtil.assertParseFailure;
import static seedu.docit.logic.parser.AppointmentCommandParserTestUtil.assertParseSuccess;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_VOLUME;

import org.junit.jupiter.api.Test;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.prescription.AddPrescriptionCommand;
import seedu.docit.logic.parser.exceptions.ParseException;


public class AddPrescriptionParserTest {
    private final AddPrescriptionCommandParser parser = new AddPrescriptionCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        assertParseSuccess(parser, VALID_APPOINTMENT_INDEX + " "
                        + PREFIX_NAME + VALID_PRESCRIPTION_MEDICINE + " "
                        + PREFIX_VOLUME + VALID_PRESCRIPTION_VOLUME + " "
                        + PREFIX_DURATION + VALID_PRESCRIPTION_DURATION,
                new AddPrescriptionCommand(Index.fromOneBased(Integer.parseInt(VALID_APPOINTMENT_INDEX)),
                VALID_PRESCRIPTION_MEDICINE,
                VALID_PRESCRIPTION_VOLUME,
                VALID_PRESCRIPTION_DURATION));
    }

    @Test
    public void parse_allFieldsBlank_failure() throws ParseException {
        assertParseFailure(parser, " "
                        + PREFIX_NAME + " "
                        + PREFIX_VOLUME + " "
                        + PREFIX_DURATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPrescriptionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noIndex_failure() throws ParseException {
        assertParseFailure(parser, PREFIX_NAME + VALID_PRESCRIPTION_MEDICINE + " "
                        + PREFIX_VOLUME + VALID_PRESCRIPTION_VOLUME + " "
                        + PREFIX_DURATION + VALID_PRESCRIPTION_DURATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPrescriptionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noPrefix_failure() throws ParseException {
        assertParseFailure(parser, VALID_APPOINTMENT_INDEX + " "
                        + VALID_PRESCRIPTION_MEDICINE + " "
                        + VALID_PRESCRIPTION_VOLUME + " "
                        + VALID_PRESCRIPTION_DURATION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPrescriptionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_blankEntry_failure() throws ParseException {
        assertParseFailure(parser, VALID_APPOINTMENT_INDEX + " "
                        + PREFIX_NAME + " "
                        + PREFIX_VOLUME + " "
                        + PREFIX_DURATION,
                "Medicine/Duration/Volume fields cannot be blank.");
    }
}
