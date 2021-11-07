package seedu.docit.logic.parser;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.docit.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.docit.logic.parser.PatientCommandParserTestUtil.assertParseFailure;
import static seedu.docit.logic.parser.PatientCommandParserTestUtil.assertParseSuccess;
import static seedu.docit.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.DeleteMedicalEntryCommand;

public class DeleteMedicalEntryCommandParserTest {
    private DeleteMedicalEntryCommandParser parser = new DeleteMedicalEntryCommandParser();

    @Test
    public void parsePatientCommand_validDeletion_successFul() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + "1 i/1",
            new DeleteMedicalEntryCommand(INDEX_FIRST_PATIENT, INDEX_FIRST_PATIENT));
    }

    @Test
    public void parsePatientCommand_invalidPatientIndex_failure() {
        Index invalidPatientIndex = Index.fromOneBased(Integer.MAX_VALUE);
        Index negativePatientIndex = Index.fromOneBased(Integer.MIN_VALUE);

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteMedicalEntryCommand.MESSAGE_USAGE);

        assertParseFailure(parser, invalidPatientIndex.getOneBased() + "i/1", expectedMessage);
        assertParseFailure(parser, negativePatientIndex.getOneBased() + "i/1", expectedMessage);
    }

    @Test
    public void parsePatientCommand_invalidMedicalIndex_failure() {
        Index invalidMedicalIndex = Index.fromOneBased(Integer.MIN_VALUE);
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteMedicalEntryCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "1 i/" + invalidMedicalIndex.getOneBased(), expectedMessage);
    }
}
