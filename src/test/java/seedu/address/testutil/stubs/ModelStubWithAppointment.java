package seedu.address.testutil.stubs;

import static java.util.Objects.requireNonNull;

import seedu.address.model.appointment.Appointment;



/**
 * A Model stub that contains a single person.
 */
public class ModelStubWithAppointment extends ModelStub {
    private final Appointment appointment;

    /**
     * Returns a model stub that contains 1 appointment
     * @param appointment Appointment to be contained in the stub
     */
    public ModelStubWithAppointment(Appointment appointment) {
        requireNonNull(appointment);
        this.appointment = appointment;
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return this.appointment.isSameAppointment(appointment);
    }
}
