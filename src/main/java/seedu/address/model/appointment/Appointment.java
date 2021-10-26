package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Patient;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.UniquePrescriptionList;
import seedu.address.model.prescription.exceptions.DuplicatePrescriptionException;
import seedu.address.model.prescription.exceptions.MedicineNotFoundException;

/**
 * Represents an Appointment in the appointment book. Guarantees: details are present and not null, field values are
 * validated, immutable.
 */
public class Appointment {

    // Identity fields
    private final Patient patient;
    private final String datetime;
    private final UniquePrescriptionList prescriptions;
    private final FilteredList<Prescription> filteredPrescriptions;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Patient patient, String datetime) {
        requireAllNonNull(patient, datetime);
        this.patient = patient;
        this.datetime = datetime;
        this.prescriptions = new UniquePrescriptionList();
        this.filteredPrescriptions = new FilteredList<>(this.prescriptions.getPrescriptions());
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getPrescriptions() {
        return prescriptions.toString();
    }

    public void addPrescription(Prescription prescription) throws DuplicatePrescriptionException {
        this.prescriptions.add(prescription);
    }

    public void removePrescription(String medicineName) throws MedicineNotFoundException {
        this.prescriptions.remove(medicineName);
    }

    public void editPrescription(Prescription prescription) throws MedicineNotFoundException {
        removePrescription(prescription.getMedicine());
        addPrescription(prescription);
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
            && otherAppointment.getDatetime().equals(getDatetime())
                && otherAppointment.getPrescriptions().equals(getPrescriptions());
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
        return isSameAppointment(otherAppointment);
    }

    public void updateFilteredPrescriptions(Predicate<Prescription> predicate) {
        filteredPrescriptions.setPredicate(predicate);
    }

    public ObservableList<Prescription> getFilteredPrescriptions() {
        return filteredPrescriptions;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patient, datetime, prescriptions);
    }

    @Override
    public String toString() {
        return "" + getPatient() + "; Datetime: " + getDatetime() + "; Prescription: " + getPrescriptions() + "\n";
    }

    public boolean containsPrescription(Prescription p) {
        return prescriptions.contains(p);
    }
}
