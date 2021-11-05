package seedu.docit.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.docit.testutil.TypicalAppointments.A1;
import static seedu.docit.testutil.TypicalAppointments.A2;
import static seedu.docit.testutil.TypicalAppointments.B1;
import static seedu.docit.testutil.TypicalAppointments.B2;
import static seedu.docit.testutil.TypicalPatients.BENSON;
import static seedu.docit.testutil.TypicalPrescriptions.INSULIN_JAB;

import org.junit.jupiter.api.Test;

import seedu.docit.testutil.AppointmentBuilder;

public class AppointmentTest {
    @Test
    public void isSameAppointment() {
        // same object -> returns true
        assertTrue(A1.isSameAppointment(A1));

        // null -> returns false
        assertFalse(A1.isSameAppointment(null));

        // same patient and datetime, different prescription  -> returns true
        Appointment editedA1 = new AppointmentBuilder(A1).withPrescription(INSULIN_JAB).build();
        assertTrue(A1.isSameAppointment(editedA1));

        // different patient, all other attributes same -> returns false
        editedA1 = new AppointmentBuilder(A1).withPatient(BENSON).build();
        assertFalse(A1.isSameAppointment(editedA1));

        // different datetime, all other attributes same -> returns false
        Appointment editedB1 = new AppointmentBuilder(B1).withDatetime(B2.getDatetime()).build();
        assertFalse(B1.isSameAppointment(editedB1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Appointment a1Copy = new AppointmentBuilder(A1).build();
        assertTrue(A1.equals(a1Copy));

        // same object -> returns true
        assertTrue(A1.equals(A1));

        // null -> returns false
        assertFalse(A1.equals(null));

        // different type -> returns false
        assertFalse(A1.equals(5));

        // different appointment -> returns false
        assertFalse(A1.equals(B1));

        // different patient -> returns false
        Appointment editedA1 = new AppointmentBuilder(A1).withPatient(BENSON).build();
        assertFalse(A1.equals(editedA1));

        // different datetime -> returns false
        editedA1 = new AppointmentBuilder(A1).withDatetime(A2.getDatetime()).build();
        assertFalse(A1.equals(editedA1));

        // different prescription -> returns false
        editedA1 = new AppointmentBuilder(A1).withPrescription(INSULIN_JAB).build();
        assertFalse(A1.equals(editedA1));
    }
}
