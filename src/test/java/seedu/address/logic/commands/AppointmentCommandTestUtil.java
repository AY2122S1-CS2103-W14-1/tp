package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

public class AppointmentCommandTestUtil {

    /**
     * Updates {@code model}'s filtered list to show only the appointment at the given {@code targetIndex} in the {@code
     * model}'s appointment list.
     */
    public static void showAppointmentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAppointmentList().size());

        Appointment appointment = model.getFilteredAppointmentList().get(targetIndex.getZeroBased());
        /*
        final int id = appointment.getPatient();

        model.updateFilteredAppointmentList(new AppointmentContainsKeywordsPredicate(Arrays.asList(id)));

        assertEquals(1, model.getFilteredAppointmentList().size());
         */
    }
}
