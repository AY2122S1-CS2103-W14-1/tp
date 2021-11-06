package seedu.docit.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.docit.model.prescription.Prescription;

/**
 * A utility class containing a list of {@code Prescription} objects to be used in tests.
 */
public class TypicalPrescriptions {
    // Typical Prescriptions
    public static final Set<Prescription> PRESCRIPTION_DIABETES = new HashSet<>(
        Arrays.asList(
            new Prescription("insulin", "1 tablet", "daily after meals"),
            new Prescription("sulfonylureas", "1 tablet", "when needed"),
            new Prescription("dopamine agonist", "1 capsule", "daily after meals")
        ));

    public static final Set<Prescription> PRESCRIPTION_COLD = new HashSet<>(
        Arrays.asList(
            new Prescription("ibuprofen", "1 jab", "every week")
        ));

    public static final Prescription INSULIN_JAB = new Prescription("insulin jab", "1 jab", "daily after meals");

}
