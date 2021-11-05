package seedu.docit.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.docit.commons.core.index.Index;
import seedu.docit.model.Model;
import seedu.docit.model.appointment.AppointmentContainsPatientPredicate;
import seedu.docit.model.patient.Patient;

public class AppointmentCommandTestUtil {

    /**
     * Updates {@code model}'s filtered list to show appointments with patient at the given {@code targetIndex} in the
     * {@code model}'s patient list.
     */
    public static void showAppointmentWithPatientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAppointmentList().size());

        Patient patient = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        model.updateFilteredAppointmentList(new AppointmentContainsPatientPredicate(patient));

        assertEquals(1, model.getFilteredAppointmentList().size());
    }
}
