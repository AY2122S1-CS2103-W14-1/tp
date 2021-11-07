package seedu.docit.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.docit.model.patient.Patient;
import seedu.docit.testutil.AppointmentBuilder;
import seedu.docit.testutil.TypicalPatients;

public class AppointmentContainsPatientPredicateTest {

    @Test
    public void equals() {
        Patient firstPatient = TypicalPatients.ALICE;
        Patient secondPatient = TypicalPatients.BENSON;

        AppointmentContainsPatientPredicate firstPredicate =
            new AppointmentContainsPatientPredicate(firstPatient);
        AppointmentContainsPatientPredicate secondPredicate =
            new AppointmentContainsPatientPredicate(secondPatient);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AppointmentContainsPatientPredicate firstPredicateCopy =
            new AppointmentContainsPatientPredicate(firstPatient);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Appointment -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsPatient_returnsTrue() {
        // One patient
        AppointmentContainsPatientPredicate predicate =
            new AppointmentContainsPatientPredicate(TypicalPatients.ALICE);
        assertTrue(predicate.test(new AppointmentBuilder().withPatient(TypicalPatients.ALICE).build()));
    }

    @Test
    public void test_nameDoesNotContainPatient_returnsFalse() {
        // Zero patient
        AppointmentContainsPatientPredicate predicate = new AppointmentContainsPatientPredicate(TypicalPatients.ALICE);
        assertFalse(predicate.test(null));

        // Non-matching patient
        predicate = new AppointmentContainsPatientPredicate(TypicalPatients.ALICE);
        assertFalse(predicate.test(new AppointmentBuilder().withPatient(TypicalPatients.BENSON).build()));
    }
}
