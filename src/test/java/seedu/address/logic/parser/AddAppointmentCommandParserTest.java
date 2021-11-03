package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_ALL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_MONTH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_NULL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_RANDOM;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_TIME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPOINTMENT_DATE_TIME_DESC_YEAR;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PATIENT_ID_DESC_MINUS_1;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_ID_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_ID_DESC_0;
import static seedu.address.logic.parser.AppointmentCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.AppointmentCommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Name;
import seedu.address.model.util.SampleDataUtil;


public class AddAppointmentCommandParserTest {
    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    @Disabled("Currently, parser do not discard unused preamble")
    public void parse_allFieldsPresent_success() {
        LocalDateTime expectedDateTime = LocalDateTime.of(
            2021, 12, 21, 16, 0);
        Appointment expectedAppointment = new Appointment(SampleDataUtil.getSamplePersons()[0], expectedDateTime);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_PATIENT_ID_0 + VALID_APPOINTMENT_DATE_TIME,
                new AddAppointmentCommand(Index.fromOneBased(1), expectedDateTime));

        // fully correct entry
        assertParseSuccess(parser, VALID_PATIENT_ID_0 + VALID_APPOINTMENT_DATE_TIME,
                new AddAppointmentCommand(Index.fromOneBased(1), expectedDateTime));

        // fully correct entry with irrelevant ending whitespace only preamble
        assertParseSuccess(parser, VALID_PATIENT_ID_0 + VALID_APPOINTMENT_DATE_TIME + PREAMBLE_WHITESPACE,
                new AddAppointmentCommand(Index.fromOneBased(1), expectedDateTime));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE);

        // missing patient id prefix
        assertParseFailure(parser, VALID_PATIENT_ID_0 + VALID_APPOINTMENT_DATE_TIME_DESC,
                expectedMessage);

        // missing date time prefix
        assertParseFailure(parser, VALID_PATIENT_ID_DESC_0 + VALID_APPOINTMENT_DATE_TIME,
                expectedMessage);


        // all prefixes missing
        assertParseFailure(parser, VALID_PATIENT_ID_0 + VALID_APPOINTMENT_DATE_TIME,
                expectedMessage);
    }

    @Test
    @Disabled("Currently, no error checking is done for patient id and date time")
    public void parse_invalidValue_failure() {
        // invalid id
        assertParseFailure(parser, INVALID_PATIENT_ID_DESC_MINUS_1 + VALID_APPOINTMENT_DATE_TIME_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // invalid year
        assertParseFailure(parser, VALID_PATIENT_ID_DESC_0 + INVALID_APPOINTMENT_DATE_TIME_DESC_YEAR,
                Name.MESSAGE_CONSTRAINTS);

        // invalid month
        assertParseFailure(parser, VALID_PATIENT_ID_DESC_0 + INVALID_APPOINTMENT_DATE_TIME_DESC_MONTH,
                Name.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, VALID_PATIENT_ID_DESC_0 + INVALID_APPOINTMENT_DATE_TIME_DESC_DATE,
                Name.MESSAGE_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, VALID_PATIENT_ID_DESC_0 + INVALID_APPOINTMENT_DATE_TIME_DESC_TIME,
                Name.MESSAGE_CONSTRAINTS);

        // invalid null input
        assertParseFailure(parser, VALID_PATIENT_ID_DESC_0 + INVALID_APPOINTMENT_DATE_TIME_DESC_NULL,
                Name.MESSAGE_CONSTRAINTS);

        // invalid random input
        assertParseFailure(parser, VALID_PATIENT_ID_DESC_0 + INVALID_APPOINTMENT_DATE_TIME_DESC_RANDOM,
                Name.MESSAGE_CONSTRAINTS);

        // invalid input for all date time fields
        assertParseFailure(parser, VALID_PATIENT_ID_DESC_0 + INVALID_APPOINTMENT_DATE_TIME_DESC_ALL,
                Name.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_PATIENT_ID_DESC_MINUS_1 + INVALID_APPOINTMENT_DATE_TIME_DESC_MONTH,
                Name.MESSAGE_CONSTRAINTS);


        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY
                        + VALID_PATIENT_ID_DESC_0
                        + VALID_APPOINTMENT_DATE_TIME_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE));
    }
}
