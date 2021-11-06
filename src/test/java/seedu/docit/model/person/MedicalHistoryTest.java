package seedu.docit.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.docit.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.docit.model.patient.MedicalHistory;

public class MedicalHistoryTest {

    @Test
    public void constructor_null_sizeIsOne() {
        MedicalHistory test = new MedicalHistory(null);
        assertEquals(1, test.size());
    }

    @Test
    public void constructor_validDescription_dateIsNow() {
        MedicalHistory validMedicalHistory = new MedicalHistory("diabetes");
        MedicalHistory.MedicalEntry validMedicalEntry = validMedicalHistory.toList().get(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu");
        assertEquals(LocalDate.now().format(formatter), validMedicalEntry.getDateString());
    }

    @Test
    public void constructor_validDescription_descriptionIsStored() {
        MedicalHistory validMedicalHistory = new MedicalHistory("diabetes");
        MedicalHistory.MedicalEntry validMedicalEntry = validMedicalHistory.toList().get(0);
        assertEquals("diabetes", validMedicalEntry.getDescription());
    }

    @Test
    public void isValidMedicalEntry() {
        assertThrows(NullPointerException.class, () -> MedicalHistory.isValidMedicalEntry(null));

        //invalid medical entry
        assertFalse(MedicalHistory.isValidMedicalEntry(""));
        assertFalse(MedicalHistory.isValidMedicalEntry("@@@@@@@@@@@"));
        assertFalse(MedicalHistory.isValidMedicalEntry(";"));
        assertFalse(MedicalHistory.isValidMedicalEntry(";/"));

        // valid medical entry - alphanumeric characters
        assertTrue(MedicalHistory.isValidMedicalEntry("diabetes"));
        assertTrue(MedicalHistory.isValidMedicalEntry("high blood pressure"));
        assertTrue(MedicalHistory.isValidMedicalEntry("123456"));
        assertTrue(MedicalHistory.isValidMedicalEntry("123456abc"));
    }

    @Test
    public void append_emptyMedicalHistory_returnsAppendedMedicalHistory() {
        MedicalHistory toAppend = new MedicalHistory("diabetes");
        MedicalHistory result = MedicalHistory.EMPTY_MEDICAL_HISTORY.append(toAppend);
        assertEquals(toAppend, result);
    }

    @Test
    public void delete_emptyMedicalHistory_returnsEmptyMedicalHistory() {
        MedicalHistory deleted = MedicalHistory.EMPTY_MEDICAL_HISTORY.delete(0);
        assertEquals(deleted, MedicalHistory.EMPTY_MEDICAL_HISTORY);
    }

}
