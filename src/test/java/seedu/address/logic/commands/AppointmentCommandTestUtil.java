package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentContainsKeywordsPredicate;

public class AppointmentCommandTestUtil {

    /**
     * Updates {@code model}'s filtered list to show only the appointment at the given {@code targetIndex} in the
     * {@code model}'s appointment list.
     */
    public static void showAppointmentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAppointmentList().size());

        Appointment appointment = model.getFilteredAppointmentList().get(targetIndex.getZeroBased());
        final int id = 0;
        model.updateFilteredAppointmentList(new AppointmentContainsKeywordsPredicate(Arrays.asList(id)));
        // TODO: AppointmentContainsKeywordsPredicate needs to be modified as Appointmnet no longer uses id

        assertEquals(1, model.getFilteredAppointmentList().size());
    }
}
