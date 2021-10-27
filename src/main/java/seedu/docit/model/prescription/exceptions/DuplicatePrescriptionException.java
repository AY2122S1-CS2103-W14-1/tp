package seedu.docit.model.prescription.exceptions;

public class DuplicatePrescriptionException extends RuntimeException {
    public DuplicatePrescriptionException() {
        super("Operation would result in duplicate prescriptions");
    }

}
