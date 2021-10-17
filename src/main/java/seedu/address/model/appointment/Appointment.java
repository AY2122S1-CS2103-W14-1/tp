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
    private final int patientUuid;
    private final String datetime;

    // Data fields

    private Patient patient;    // Lazy result storing

    /**
     * Every field must be present and not null.
     */
    public Appointment(int patientUuid, String datetime) {
        requireAllNonNull(patientUuid, datetime);
        this.patientUuid = patientUuid;
        this.datetime = datetime;
    }

    /**
     * Instantiate appointment with patient instance
     */
    public Appointment(Patient patient, String datetime) {
        requireAllNonNull(patient, datetime);
        this.patientUuid = patient.getUuid();
        this.patient = patient;
        this.datetime = datetime;
    }

    public int getPatientUuid() {
        return patientUuid;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns true if both appointments have the same name and datetime. This defines a weaker notion of equality
     * between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null && otherAppointment.getPatientUuid() == getPatientUuid()
            && otherAppointment.getDatetime().equals(getDatetime());
    }

    /**
     * Returns true if both appointments have the same identity and data fields. This defines a stronger notion of
     * equality between two appointments.
     */
    @Override public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.getPatientUuid() == (getPatientUuid()) && otherAppointment.getDatetime()
            .equals(getDatetime());
    }

    @Override public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patientUuid, datetime);
    }

    @Override public String toString() {
        if (patient != null) {
            return "" + getPatient() + "; Datetime: "  + getDatetime() + "\n";
        } else {
            return "" + getPatientUuid() + "; Datetime: " + getDatetime() + "\n";
        }
    }

}
