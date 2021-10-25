package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PatientContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public PatientContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient patient) {
        boolean hasName = keywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(patient.getName().fullName, keyword));
        boolean hasMedicalHistory = patient.getMedicalHistory().toStream().anyMatch(mh ->
            keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(mh, keyword)));

        return hasName || hasMedicalHistory;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PatientContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PatientContainsKeywordsPredicate) other).keywords)); // state check
    }

}
