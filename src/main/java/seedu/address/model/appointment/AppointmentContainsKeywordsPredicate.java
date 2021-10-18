package seedu.address.model.appointment;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that an {@code Appointment}'s {@code Id} matches any of the keywords given.
 */
public class AppointmentContainsKeywordsPredicate implements Predicate<Appointment> {
    private final List<Integer> keywords;

    public AppointmentContainsKeywordsPredicate(List<Integer> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return keywords.stream().anyMatch(keyword -> keyword.equals(appointment.getDatetime()));
        // TODO: Implement new feature to find patient or delete this class
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AppointmentContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((AppointmentContainsKeywordsPredicate) other).keywords)); // state check
    }
}
