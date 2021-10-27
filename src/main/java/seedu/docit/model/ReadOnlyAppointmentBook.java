package seedu.docit.model;

import javafx.collections.ObservableList;
import seedu.docit.model.appointment.Appointment;

/**
 * Unmodifiable view of an appointment book
 */
public interface ReadOnlyAppointmentBook {

    /**
     * Returns an unmodifiable view of the appointment list.
     * This list will not contain any duplicate appointment.
     */
    ObservableList<Appointment> getAppointmentList();
}
