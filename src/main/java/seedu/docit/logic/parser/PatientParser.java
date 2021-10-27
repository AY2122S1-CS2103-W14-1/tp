package seedu.docit.logic.parser;

import seedu.docit.logic.commands.PatientCommand;
import seedu.docit.logic.parser.exceptions.ParseException;

/**
 * Represents a PatientParser that is able to parse patient-related
 * user input into a {@code Patientommand} of type {@code T}.
 */
public interface PatientParser<T extends PatientCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parsePatientCommand(String userInput) throws ParseException;
}
