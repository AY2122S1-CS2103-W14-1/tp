package seedu.docit.logic.parser;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.docit.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.docit.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.docit.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.docit.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.docit.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.docit.logic.commands.CommandTestUtil.MEDICAL_DESC_PATIENT;
import static seedu.docit.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.docit.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.docit.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.docit.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.docit.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.docit.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.docit.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.docit.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.docit.logic.parser.PatientCommandParserTestUtil.assertParseFailure;
import static seedu.docit.logic.parser.PatientCommandParserTestUtil.assertParseSuccess;
import static seedu.docit.testutil.TypicalPatients.AMY;
import static seedu.docit.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.docit.logic.commands.AddPatientCommand;
import seedu.docit.model.patient.Address;
import seedu.docit.model.patient.Email;
import seedu.docit.model.patient.Name;
import seedu.docit.model.patient.Patient;
import seedu.docit.model.patient.Phone;
import seedu.docit.model.tag.Tag;
import seedu.docit.testutil.PatientBuilder;

public class AddPatientCommandParserTest {
    private AddPatientCommandParser parser = new AddPatientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Patient expectedPatient = new PatientBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + MEDICAL_DESC_PATIENT, new AddPatientCommand(expectedPatient));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + MEDICAL_DESC_PATIENT, new AddPatientCommand(expectedPatient));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + MEDICAL_DESC_PATIENT, new AddPatientCommand(expectedPatient));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + MEDICAL_DESC_PATIENT, new AddPatientCommand(expectedPatient));

        // multiple addresses - last docit accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + MEDICAL_DESC_PATIENT, new AddPatientCommand(expectedPatient));

        // multiple tags - all accepted
        Patient expectedPatientMultipleTags = new PatientBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MEDICAL_DESC_PATIENT,
                    new AddPatientCommand(expectedPatientMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Patient expectedPatient = new PatientBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + MEDICAL_DESC_PATIENT,
                new AddPatientCommand(expectedPatient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + MEDICAL_DESC_PATIENT,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + MEDICAL_DESC_PATIENT,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                + MEDICAL_DESC_PATIENT,
                expectedMessage);

        // missing docit prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                + MEDICAL_DESC_PATIENT,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                + MEDICAL_DESC_PATIENT,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MEDICAL_DESC_PATIENT, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MEDICAL_DESC_PATIENT, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MEDICAL_DESC_PATIENT, Email.MESSAGE_CONSTRAINTS);

        // invalid docit
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MEDICAL_DESC_PATIENT, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND + MEDICAL_DESC_PATIENT, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + MEDICAL_DESC_PATIENT,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + MEDICAL_DESC_PATIENT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPatientCommand.MESSAGE_USAGE));
    }
}
