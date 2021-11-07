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
    public void parsePatientCommand_blankMedicalEntry_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMedicalEntryCommand.MESSAGE_USAGE);

        String noEntry = "";
        String singleSpace = " ";
        String noMedicalEntry = "1 ";

        assertParseFailure(parser, noEntry, expectedMessage);
        assertParseFailure(parser, singleSpace, expectedMessage);
        assertParseFailure(parser, noMedicalEntry, expectedMessage);
    }

    @Test
    public void parsePatientCommand_invalidMedicalEntry_failure() {
        String wrongPrefixMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMedicalEntryCommand.MESSAGE_USAGE);
        String invalidMessage = MedicalHistory.MESSAGE_CONSTRAINTS;

        String incorrectPrefix = "1 i/diabetes";
        String invalidDescription = "1 m/@@@@";

        assertParseFailure(parser, incorrectPrefix, wrongPrefixMessage);
        assertParseFailure(parser, invalidDescription, invalidMessage);
    }

}
