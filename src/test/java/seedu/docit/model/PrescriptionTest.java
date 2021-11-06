package seedu.docit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.docit.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.docit.model.prescription.Prescription;

public class PrescriptionTest {
    private static final String defaultMedicine = "Penicillin";
    private static final String defaultVolume = "400 ml";
    private static final String defaultDuration = "3 times a day";

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

    @Test
    public void equals_sameObject_success() {
        Prescription prescription = new Prescription(defaultMedicine, defaultVolume, defaultDuration);
        Prescription prescriptionCopy = new Prescription(defaultMedicine, defaultVolume, defaultDuration);
        assertTrue(prescription.equals(prescription));
        assertTrue(prescription.equals(prescriptionCopy));
    }

    @Test
    public void equals_null_failure() {
        Prescription prescription = new Prescription(defaultMedicine, defaultVolume, defaultDuration);
        assertFalse(prescription.equals(null));
    }

    @Test
    public void hasSameMedicalName_sameMedicalName_success() {
        Prescription prescription = new Prescription(defaultMedicine, defaultVolume, defaultDuration);
        Prescription prescriptionTwo = new Prescription(defaultMedicine, "500 ml", "6 days");
        assertTrue(prescription.hasSameMedicalName(prescriptionTwo));
    }

    @Test
    public void hasSameMedicalName_differentMedicalName_success() {
        Prescription prescription = new Prescription(defaultMedicine, defaultVolume, defaultDuration);
        Prescription prescriptionTwo = new Prescription("poison", defaultVolume, defaultDuration);
        assertFalse(prescription.hasSameMedicalName(prescriptionTwo));
    }

    @Test
    public void toUiFormat_standardInput_correctFormat() {
        Prescription prescription = new Prescription(defaultMedicine, defaultVolume, defaultDuration);
        assertEquals(prescription.toUiFormat(), defaultMedicine.toLowerCase() + " | "
            + defaultVolume + " | " + defaultDuration);
    }

    @Test
    public void toString_standardInput_correctFormat() {
        Prescription prescription = new Prescription(defaultMedicine, defaultVolume, defaultDuration);
        assertEquals(prescription.toString(), "Medicine: " + defaultMedicine.toLowerCase()
                + ", Volume: " + defaultVolume
                + ", Duration: " + defaultDuration);
    }
}
