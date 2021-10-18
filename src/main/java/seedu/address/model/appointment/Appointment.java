package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Patient;

/**
 * Represents an Appointment in the appointment book. Guarantees: details are present and not null, field values are
 * validated, immutable.
 */
public class Appointment {

    // Identity fields
    private final Patient patient;
    private final String datetime;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Patient patient, String datetime) {
        requireAllNonNull(patient, datetime);
        this.patient = patient;
        this.datetime = datetime;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDatetime() {
        return datetime;
    }

    /**
     * Returns true if both appointments have the same name and datetime. This defines a weaker notion of equality
     * between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null && otherAppointment.getPatient().equals(getPatient())
            && otherAppointment.getDatetime().equals(getDatetime());
    }

    /**
     * Returns true if both appointments have the same identity and data fields. This defines a stronger notion of
     * equality between two appointments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.getPatient().equals(getPatient()) && otherAppointment.getDatetime()
            .equals(getDatetime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patient, datetime);
    }

    @Override
    public String toString() {
        return "" + getPatient() + "; Datetime: " + getDatetime() + "\n";
    }

}
