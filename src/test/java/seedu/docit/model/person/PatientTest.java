package seedu.docit.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.docit.testutil.TypicalPatients.ALICE;
import static seedu.docit.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.docit.commons.core.index.Index;
import seedu.docit.testutil.PatientBuilder;

public class PatientTest {
    @Test
    public void isSamePatient() {
        // same object -> returns true
        assertTrue(ALICE.isSamePatient(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePatient(null));

        // same name, all other attributes different -> returns true
        Patient editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICE.isSamePatient(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePatient(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Patient editedBob = new PatientBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePatient(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PatientBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePatient(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Patient aliceCopy = new PatientBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different patient -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Patient editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PatientBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different docit -> returns false
        editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    // Testing if deleting from an EMPTY_MEDICAL_HISTORY returns an EMPTY_MEDICAL_HISTORY
    @Test
    public void deleteMedicalHistory_emptyMedicalHistory_returnsEmptyMedicalHistory() {
        int len = ALICE.getMedicalHistory().size();

        Patient editedPatient = new PatientBuilder(ALICE)
                                    .withMedicalHistory("diabetes", "21 Oct 2021").build();

        editedPatient = editedPatient.deleteMedicalHistory(Index.fromZeroBased(0));
        MedicalHistory editedMedicalHistory = editedPatient.getMedicalHistory();

        assertEquals(MedicalHistory.EMPTY_MEDICAL_HISTORY, editedMedicalHistory);
    }

    @Test
    public void addMedicalHistory() {
        Patient editedPatient = new PatientBuilder(ALICE).build();
        MedicalHistory toAdd = new MedicalHistory("diabetes");
        editedPatient = ALICE.addMedicalHistory(toAdd);
        MedicalHistory controlTest = ALICE.getMedicalHistory();
        controlTest = controlTest.append(toAdd);

        assertEquals(controlTest, editedPatient.getMedicalHistory());
    }

    @Test
    public void addMedicalHistory_emptyMedicalHistory_returnSameMedicalHistoryAdded() {
        Patient editedPatient = new PatientBuilder(ALICE).build();
        MedicalHistory toAdd = new MedicalHistory("diabetes");
        editedPatient = ALICE.addMedicalHistory(MedicalHistory.EMPTY_MEDICAL_HISTORY);

        assertEquals(ALICE.getMedicalHistory(), editedPatient.getMedicalHistory());
    }

    @Test
    public void hasEmptyMedicalHistory() {
        int len = ALICE.getMedicalHistory().size();
        Patient editedPatient = new PatientBuilder(ALICE)
                                    .withMedicalHistory("diabetes", "21 Oct 2021").build();

        editedPatient = editedPatient.deleteMedicalHistory(Index.fromZeroBased(0));

        assertTrue(editedPatient.hasEmptyMedicalHistory());
    }


}
