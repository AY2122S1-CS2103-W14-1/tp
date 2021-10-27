package seedu.docit.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.docit.logic.commands.PatientCommand;
import seedu.docit.logic.parser.exceptions.ParseException;

/**
 * Contains helper methods for testing patient command parsers.
 */
public class PatientCommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(PatientParser parser, String userInput, PatientCommand expectedCommand) {
        try {
            PatientCommand command = parser.parsePatientCommand(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(PatientParser parser, String userInput, String expectedMessage) {
        try {
            parser.parsePatientCommand(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }
}
