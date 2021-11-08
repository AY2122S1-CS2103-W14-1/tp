package seedu.docit.model.patient.exceptions;

import seedu.docit.model.patient.MedicalHistory;

public class TooManyMedicalEntriesException extends RuntimeException {
    public  TooManyMedicalEntriesException() {
        super("Max number of medical entries is " + MedicalHistory.MAX_SIZE + "!!!");
    }
}
