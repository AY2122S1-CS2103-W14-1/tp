package seedu.docit.model.appointment;

import java.util.function.Predicate;

import seedu.docit.model.patient.Patient;


/**
 * Tests that an {@code Appointment}'s {@code Patient} email matches any of the patient emails given.
 */
public class AppointmentContainsPatientPredicate implements Predicate<Appointment> {
    private final Patient patient;

    public AppointmentContainsPatientPredicate(Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean test(Appointment appointment) {
        return patient.isSamePatient(appointment.getPatient());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AppointmentContainsPatientPredicate // instanceof handles nulls
            && patient.equals(((AppointmentContainsPatientPredicate) other).patient)); // state check
    }
}
