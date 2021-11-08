package seedu.docit.logic.parser;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_ALL;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DATE;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_ALL;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_DATE;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_HOUR;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_MIN;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_MONTH;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_NULL;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_RANDOM;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_YEAR;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_MIN;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_MONTH;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_RANDOM;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_YEAR;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_DATE;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_MINUTE;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_MONTH;
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
import static seedu.docit.logic.parser.ParserUtil.MESSAGE_INVALID_DATETIME;
import static seedu.docit.logic.parser.ParserUtil.MESSAGE_INVALID_DATETIME_VALUE;
import static seedu.docit.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

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
            MESSAGE_INVALID_INDEX.concat("\n").concat(AddAppointmentCommand.MESSAGE_USAGE));

        // invalid year
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_1 + INVALID_APPOINTMENT_DATE_TIME_DESC_YEAR,
            String.format(MESSAGE_INVALID_DATETIME, INVALID_APPOINTMENT_DATE_TIME_YEAR)
                .concat("\n").concat(AddAppointmentCommand.MESSAGE_USAGE));

        String expectedInvalidMonthMessage = "Text '" + INVALID_APPOINTMENT_DATE_TIME_MONTH
            + "' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): " + INVALID_MONTH;

        // invalid month
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_1 + INVALID_APPOINTMENT_DATE_TIME_DESC_MONTH,
            expectedInvalidMonthMessage.concat("\n").concat(AddAppointmentCommand.MESSAGE_USAGE));

        String expectedInvalidDateMessage = "Text '" + INVALID_APPOINTMENT_DATE_TIME_DATE
            + "' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): " + INVALID_DATE;

        // invalid date
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_1 + INVALID_APPOINTMENT_DATE_TIME_DESC_DATE,
            expectedInvalidDateMessage.concat("\n").concat(AddAppointmentCommand.MESSAGE_USAGE));

        // invalid time above 2359
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_1 + INVALID_APPOINTMENT_DATE_TIME_DESC_HOUR,
            MESSAGE_INVALID_DATETIME_VALUE.concat("\n").concat(AddAppointmentCommand.MESSAGE_USAGE));

        String expectedInvalidMinuteMessage = "Text '" + INVALID_APPOINTMENT_DATE_TIME_MIN
            + "' could not be parsed: Invalid value for MinuteOfHour (valid values 0 - 59): " + INVALID_MINUTE;

        // invalid minute above 59 but below 2359
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_1 + INVALID_APPOINTMENT_DATE_TIME_DESC_MIN,
            expectedInvalidMinuteMessage.concat("\n").concat(AddAppointmentCommand.MESSAGE_USAGE));

        // invalid null input
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_1 + INVALID_APPOINTMENT_DATE_TIME_DESC_NULL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));

        // invalid random input
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_1 + INVALID_APPOINTMENT_DATE_TIME_DESC_RANDOM,
            String.format(MESSAGE_INVALID_DATETIME, INVALID_APPOINTMENT_DATE_TIME_RANDOM)
                .concat("\n").concat(AddAppointmentCommand.MESSAGE_USAGE));

        // invalid input for all date time fields
        assertParseFailure(parser, VALID_PATIENT_INDEX_DESC_1 + INVALID_APPOINTMENT_DATE_TIME_DESC_ALL,
            String.format(MESSAGE_INVALID_DATETIME, INVALID_APPOINTMENT_DATE_TIME_ALL)
                .concat("\n").concat(AddAppointmentCommand.MESSAGE_USAGE));

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_PATIENT_INDEX_DESC_MINUS_1 + INVALID_APPOINTMENT_DATE_TIME_DESC_MONTH,
            MESSAGE_INVALID_INDEX.concat("\n").concat(AddAppointmentCommand.MESSAGE_USAGE));


        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY
                        + VALID_PATIENT_INDEX_DESC_1
                        + VALID_APPOINTMENT_DATE_TIME_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE));
    }
}
