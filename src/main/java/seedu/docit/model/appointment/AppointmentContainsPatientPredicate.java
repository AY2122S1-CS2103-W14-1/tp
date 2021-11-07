package seedu.docit.model.appointment;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.docit.model.patient.Patient;


/**
 * Tests that an {@code Appointment}'s {@code Patient} matches any of the patient given.
 */
public class AppointmentContainsPatientPredicate implements Predicate<Appointment> {
    private final Patient patient;

    /**
     * Constructor for predicate that requires a non-null patient.
     * @param patient patient object to test with
     */
    public AppointmentContainsPatientPredicate(Patient patient) {
        requireNonNull(patient);
        this.patient = patient;
    }

    @Override
    public boolean test(Appointment appointment) {
        if (appointment == null) {
            return false;
        } else {
            return patient.isSamePatient(appointment.getPatient());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AppointmentContainsPatientPredicate // instanceof handles nulls
            && patient.equals(((AppointmentContainsPatientPredicate) other).patient)); // state check
    }
}
