package seedu.docit.logic.parser;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.docit.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.docit.logic.parser.PatientCommandParserTestUtil.assertParseFailure;
import static seedu.docit.logic.parser.PatientCommandParserTestUtil.assertParseSuccess;
import static seedu.docit.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.docit.logic.commands.AddMedicalEntryCommand;
import seedu.docit.model.patient.MedicalHistory;



public class AddMedicalEntryCommandParserTest {
    private AddMedicalEntryCommandParser parser = new AddMedicalEntryCommandParser();

    @Test
    public void parsePatientCommand_validMedicalEntry_successFul() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + "1 m/diabetes",
            new AddMedicalEntryCommand(INDEX_FIRST_PATIENT, new MedicalHistory("diabetes")));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + "1 m/random string",
            new AddMedicalEntryCommand(INDEX_FIRST_PATIENT, new MedicalHistory("random string")));
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + "1 m/    random string      ",
            new AddMedicalEntryCommand(INDEX_FIRST_PATIENT, new MedicalHistory("random string")));
    }

    @Test
    public void parsePatientCommand_invalidMedicalEntry_successFul() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMedicalEntryCommand.MESSAGE_USAGE);

        String invalidMedicalEntry = "@@@@@@@@";
        String otherInvalidMedicalEntry = "{";
        String noEntry = "";
        String singleSpace = " ";

        assertParseFailure(parser, invalidMedicalEntry, expectedMessage);
        assertParseFailure(parser, otherInvalidMedicalEntry, expectedMessage);
        assertParseFailure(parser, noEntry, expectedMessage);
        assertParseFailure(parser, singleSpace, expectedMessage);
    }

}
