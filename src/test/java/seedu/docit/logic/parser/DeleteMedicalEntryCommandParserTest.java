package seedu.docit.logic.parser;

import static seedu.docit.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.docit.logic.parser.PatientCommandParserTestUtil.assertParseSuccess;
import static seedu.docit.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import org.junit.jupiter.api.Test;
import seedu.docit.logic.commands.DeleteMedicalEntryCommand;
import seedu.docit.model.patient.MedicalHistory;

public class DeleteMedicalEntryCommandParserTest {
    private DeleteMedicalEntryCommandParser parser = new DeleteMedicalEntryCommandParser();

    @Test
    public void parsePatientCommand_validMedicalEntry_successFul() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + "1 i/1",
            new DeleteMedicalEntryCommand(INDEX_FIRST_PATIENT, INDEX_FIRST_PATIENT));
    }
}
