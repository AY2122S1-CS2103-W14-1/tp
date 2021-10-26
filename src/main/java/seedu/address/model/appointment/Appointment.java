package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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

    public static final DateTimeFormatter UI_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");

    // Identity fields
    private final Patient patient;
    private final UniquePrescriptionList prescriptions;
    private final FilteredList<Prescription> filteredPrescriptions;
    private final LocalDateTime datetime;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Patient patient, LocalDateTime datetime) {
        requireAllNonNull(patient, datetime);
        this.patient = patient;
        this.datetime = datetime;
        this.prescriptions = new UniquePrescriptionList();
        this.filteredPrescriptions = new FilteredList<>(this.prescriptions.getPrescriptions());
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getDatetime() {
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

    /**
     * Edits the prescription associated with this Appointment
     * @param prescription Prescription to be edited
     * @throws MedicineNotFoundException when the prescription cannot be found.
     */
    public void editPrescription(Prescription prescription) throws MedicineNotFoundException {
        removePrescription(prescription.getMedicine());
        addPrescription(prescription);
    }

    public String getFormattedDatetimeString() {
        return getDatetime().format(UI_DATE_TIME_FORMATTER);
    }

    public String getInputFormattedDatetimeString() {
        return getDatetime().format(INPUT_DATE_TIME_FORMATTER);
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
        return "" + getPatient() + "; Datetime: " + getDatetime().format(UI_DATE_TIME_FORMATTER) + "; Prescription: "
                + getPrescriptions() + "\n";
    }

    public boolean containsPrescription(Prescription p) {
        return prescriptions.contains(p);
    }
}
