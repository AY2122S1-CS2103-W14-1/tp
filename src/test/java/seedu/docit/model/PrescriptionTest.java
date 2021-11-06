package seedu.docit.model;

import static seedu.docit.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.docit.model.prescription.Prescription;

public class PrescriptionTest {
    @Test
    public void constructor_nullInputs_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Prescription(null, null, null));
    }

    @Test
    public void constructor_blankInputs_throwEmptyInputException() {
        assertThrows(RuntimeException.class,
                "Medicine cannot be blank. Volume cannot be blank. Duration cannot be blank.", () ->
                        new Prescription("", "", ""));
    }
}
