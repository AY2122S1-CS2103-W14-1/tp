package seedu.docit.logic.parser;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.docit.logic.parser.AppointmentCommandParserTestUtil.assertParseFailure;
import static seedu.docit.logic.parser.AppointmentCommandParserTestUtil.assertParseSuccess;
import static seedu.docit.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.docit.logic.commands.ArchiveAppointmentCommand;


/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ArchiveAppointmentCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the ArchiveAppointmentCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ArchiveAppointmentCommandParserTest {
    private ArchiveAppointmentCommandParser parser = new ArchiveAppointmentCommandParser();

    @Test
    public void parse_validArgs_returnsArchiveCommand() {
        assertParseSuccess(parser, "1", new ArchiveAppointmentCommand(INDEX_FIRST_PATIENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ArchiveAppointmentCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ArchiveAppointmentCommand.MESSAGE_USAGE));

    }


}
