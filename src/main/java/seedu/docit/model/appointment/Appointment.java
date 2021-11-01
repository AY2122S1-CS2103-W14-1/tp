package seedu.docit.model.appointment;

import static seedu.docit.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.docit.model.patient.Patient;
import seedu.docit.model.prescription.Prescription;
import seedu.docit.model.prescription.exceptions.DuplicatePrescriptionException;
import seedu.docit.model.prescription.exceptions.MedicineNotFoundException;

/**
 * Represents an Appointment in the appointment book. Guarantees: details are present and not null, field values are
 * validated, immutable.
 */
public class Appointment implements Comparable<Appointment> {

    public static final DateTimeFormatter UI_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
    public static final DateTimeFormatter UI_DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");
    public static final DateTimeFormatter UI_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");

    // Identity fields
    private final Patient patient;
    private Set<Prescription> prescriptions = new HashSet<>();
    private final LocalDateTime datetime;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Patient patient, LocalDateTime datetime) {
        this(patient, datetime, new HashSet<>());
    }

    /**
     * Every field must be present and not null.
     */
    public Appointment(Patient patient, LocalDateTime datetime, Set<Prescription> prescriptionList) {
        requireAllNonNull(patient, datetime);
        this.patient = patient;
        this.datetime = datetime;
        this.prescriptions.addAll(prescriptionList);
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    /**
     * Adds a prescription into the appointment.
     * @param prescription prescription to be added.
     * @throws DuplicatePrescriptionException if prescription already exists.
     */
    public void addPrescription(Prescription prescription) throws DuplicatePrescriptionException {
        for (Prescription p : prescriptions) {
            if (p.getMedicine().equals(prescription.getMedicine())) {
                throw new DuplicatePrescriptionException();
            }
        }
        this.prescriptions.add(prescription);
        Set<Prescription> p = new HashSet<>();
        p.addAll(prescriptions);
        this.prescriptions = p;
    }

    /**
     * Removes a prescription from an appointment.
     * @param medicineName medicine name of prescription to be removed.
     * @throws MedicineNotFoundException if no such medicine exists.
     */
    public void removePrescription(String medicineName) throws MedicineNotFoundException {
        if (!this.prescriptions.removeIf(p -> p.hasSameMedicalName(
                new Prescription(medicineName, "", "")))) {
            throw new MedicineNotFoundException();
        }
        Set<Prescription> p = new HashSet<>();
        p.addAll(prescriptions);
        this.prescriptions = p;
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

    public String getFormattedDateString() {
        return getDatetime().format(UI_DATE_FORMATTER);
    }

    public String getFormattedTimeString() {
        return getDatetime().format(UI_TIME_FORMATTER);
    }

    public String getInputFormattedDatetimeString() {
        return getDatetime().format(INPUT_DATE_TIME_FORMATTER);
    }

    /**
     * Returns whether this appointment contains an appointment with the exact same name.
     * @param prescription Prescription to be checked
     * @return True or false
     */
    public boolean containsPrescriptionWithSameMedicine(Prescription prescription) {
        for (Prescription p: prescriptions) {
            if (prescription.getMedicine().equals(p.getMedicine())) {
                return true;
            }
        }
        return false;
    }

    public boolean isToday() {
        return getDatetime().toLocalDate().equals(LocalDate.now());
    }

    /**
     * Returns true if both appointments have the same identity fields. This defines a weaker notion of equality
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
        return isSameAppointment(otherAppointment)
            && otherAppointment.getPrescriptions().equals(getPrescriptions());
    }


    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patient, datetime, prescriptions);
    }

    @Override
    public String toString() {
        return "Patient: " + getPatient().getName() + "; Datetime: " + getDatetime().format(UI_DATE_TIME_FORMATTER)
            + "; " + "Prescription: " + getPrescriptions() + "\n";
    }

    @Override
    public int compareTo(Appointment o) {
        return Comparator.comparing(Appointment::isToday).reversed()
                .thenComparing(Appointment::getDatetime)
                .thenComparing(Appointment::getPatient)
                .compare(this, o);
    }
}
