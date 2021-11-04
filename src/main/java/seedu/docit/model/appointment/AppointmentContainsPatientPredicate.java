package seedu.docit.model.appointment;

import seedu.docit.model.patient.Email;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that an {@code Appointment}'s {@code Id} matches any of the patient email given.
 */
public class AppointmentContainsPatientPredicate implements Predicate<Appointment> {
    private final List<Email> emails;

    public AppointmentContainsPatientPredicate(List<Email> emails) {
        this.emails = emails;
    }

    @Override
    public boolean test(Appointment appointment) {
        return emails.stream().anyMatch(email -> email.equals(appointment.getPatient().getEmail()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AppointmentContainsPatientPredicate // instanceof handles nulls
            && emails.equals(((AppointmentContainsPatientPredicate) other).emails)); // state check
    }
}
