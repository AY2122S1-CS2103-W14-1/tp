package seedu.address.testutil.stubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.AppointmentBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.appointment.Appointment;

/**
 * A Model stub that always accept the appointment being added.
 */
public class ModelStubAcceptingAppointmentAdded extends ModelStub {
    public final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointmentsAdded.stream().anyMatch(appointment::isSameAppointment);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        requireNonNull(appointment);
        appointmentsAdded.add(appointment);
    }

    @Override
    public ReadOnlyAppointmentBook getAppointmentBook() {
        return new AppointmentBook();
    }
}
