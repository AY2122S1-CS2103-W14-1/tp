package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that an {@code Appointment}'s {@code Id} matches any of the keywords given.
 */
public class AppointmentContainsIdPredicate implements Predicate<Appointment> {
    private final List<Integer> keywords;

    public AppointmentContainsIdPredicate(List<Integer> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        /*return keywords.stream()
                .anyMatch(keyword -> keyword.equals(appointment.getPatientUuid()));*/
        return false;   // TODO: Fix or delete this class
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentContainsIdPredicate // instanceof handles nulls
                && keywords.equals(((AppointmentContainsIdPredicate) other).keywords)); // state check
    }
}
