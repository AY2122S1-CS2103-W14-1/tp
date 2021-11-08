package seedu.docit.model.patient.exceptions;

import seedu.docit.model.patient.MedicalHistory;

public class ExceedCharacterLimit extends RuntimeException {
    public ExceedCharacterLimit() {
        super("Max number of characters for a medical entry is " + MedicalHistory.MAX_CHAR_LIMIT + "!!!");
    }
}
