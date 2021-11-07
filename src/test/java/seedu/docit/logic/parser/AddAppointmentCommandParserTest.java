package seedu.docit.logic.parser;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_ALL;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_DATE;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_MONTH;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_NULL;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_RANDOM;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_TIME;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_YEAR;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_PATIENT_INDEX_DESC_MINUS_1;
import static seedu.docit.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.docit.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_TIME;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_TIME_DESC;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_PATIENT_INDEX_1;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_PATIENT_INDEX_DESC_0;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_PATIENT_INDEX_DESC_1;
import static seedu.docit.logic.parser.AppointmentCommandParserTestUtil.assertParseFailure;
import static seedu.docit.logic.parser.AppointmentCommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.AddAppointmentCommand;


public class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        LocalDateTime expectedDateTime = LocalDateTime.of(2020, 1, 10, 16, 00); // equivalent to valid appointment

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_PATIENT_INDEX_DESC_1 + VALID_APPOINTMENT_DATE_TIME_DESC,
                new AddAppointmentCommand(Index.fromOneBased(1), expectedDateTime));

        // fully correct entry
        assertParseSuccess(parser, VALID_PATIENT_INDEX_DESC_1 + VALID_APPOINTMENT_DATE_TIME_DESC,
                new AddAppointmentCommand(Index.fromOneBased(1), expectedDateTime));

        // fully correct entry with irrelevant ending whitespace only preamble
        assertParseSuccess(parser, VALID_PATIENT_INDEX_DESC_1 + VALID_APPOINTMENT_DATE_TIME_DESC + PREAMBLE_WHITESPACE,
                new AddAppointmentCommand(Index.fromOneBased(1), expectedDateTime));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE);

        // missing patient index prefix
        assertParseFailure(parser, VALID_PATIENT_INDEX_1 + VALID_APPOINTMENT_DATE_TIME_DESC,
                expectedMessage);

        // missing date time prefix
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_0 + VALID_APPOINTMENT_DATE_TIME,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_PATIENT_INDEX_1 + VALID_APPOINTMENT_DATE_TIME,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid index
        assertParseFailure(parser, INVALID_PATIENT_INDEX_DESC_MINUS_1 + VALID_APPOINTMENT_DATE_TIME_DESC,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));

        // invalid year
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_0 + INVALID_APPOINTMENT_DATE_TIME_DESC_YEAR,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));

        // invalid month
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_0 + INVALID_APPOINTMENT_DATE_TIME_DESC_MONTH,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));

        // invalid date
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_0 + INVALID_APPOINTMENT_DATE_TIME_DESC_DATE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));

        // invalid time
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_0 + INVALID_APPOINTMENT_DATE_TIME_DESC_TIME,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));

        // invalid null input
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_0 + INVALID_APPOINTMENT_DATE_TIME_DESC_NULL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));

        // invalid random input
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_0 + INVALID_APPOINTMENT_DATE_TIME_DESC_RANDOM,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));

        // invalid input for all date time fields
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_0 + INVALID_APPOINTMENT_DATE_TIME_DESC_ALL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_PATIENT_INDEX_DESC_MINUS_1 + INVALID_APPOINTMENT_DATE_TIME_DESC_MONTH,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));


        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY
                        + VALID_PATIENT_INDEX_DESC_1
                        + VALID_APPOINTMENT_DATE_TIME_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));
    }
}
