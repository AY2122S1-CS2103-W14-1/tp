package seedu.docit.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.docit.commons.core.index.Index;
import seedu.docit.model.Model;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.appointment.AppointmentContainsPatientPredicate;
import seedu.docit.model.patient.Patient;

public class AppointmentCommandTestUtil {

    /**
     * Updates {@code model}'s filtered list to show appointments with patient at the given {@code targetIndex} in the
     * {@code model}'s patient list. May filter with more than one appointment.
     */
    public static void showAppointmentWithPatientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAppointmentList().size());

        Patient patient = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        model.updateFilteredAppointmentList(new AppointmentContainsPatientPredicate(patient));

        assert(1 <= model.getFilteredAppointmentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show appointment at the given {@code targetIndex}.
     * Filters only one appointment.
     */
    public static void showAppointmentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAppointmentList().size());

        Appointment appointment = model.getFilteredAppointmentList().get(targetIndex.getZeroBased());
        model.updateFilteredAppointmentList(x -> x.equals(appointment));

        assertEquals(1, model.getFilteredAppointmentList().size());
    }
}
