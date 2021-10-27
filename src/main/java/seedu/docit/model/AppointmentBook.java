package seedu.docit.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.appointment.UniqueAppointmentList;
import seedu.docit.model.patient.Patient;
import seedu.docit.model.prescription.Prescription;

/**
 * Wraps all data at the docit-book level Duplicates are not allowed (by .isSameAppointment comparison)
 */
public class AppointmentBook implements ReadOnlyAppointmentBook {

    private final UniqueAppointmentList appointments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        appointments = new UniqueAppointmentList();
    }

    public AppointmentBook() {
    }

    /**
     * Creates an AppointmentBook using the Appointments in the {@code toBeCopied}
     */
    public AppointmentBook(ReadOnlyAppointmentBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the appointment list with {@code appointments}. {@code appointments} must not contain
     * duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Resets the existing data of this {@code AppointmentBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAppointmentBook newData) {
        requireNonNull(newData);

        setAppointments(newData.getAppointmentList());
    }

    //// appointment-level operations

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the address book.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Adds an appointment to the address book. The appointment must not already exist in the address book.
     */
    public void addAppointment(Appointment p) {
        appointments.add(p);
    }

    /**
     * Replaces the given appointment {@code target} in the list with {@code editedAppointment}. {@code target} must
     * exist in the address book. The appointment identity of {@code editedAppointment} must not be the same as another
     * existing appointment in the address book.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);

        appointments.setAppointment(target, editedAppointment);
    }

    /**
     * Updates appointments in the list with {@code target} when there are changes to the patient's details.
     * The appointment identity of {@code editedAppointment} must not be the same as another
     * existing appointment in the address book.
     */
    public void updatePatient(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);

        for (Appointment appointment : appointments) {
            if (appointment.getPatient().equals(target)) {
                Appointment editedAppointment = new Appointment(editedPatient, appointment.getDatetime());
                setAppointment(appointment, editedAppointment);
            }
        }
    }

    /**
     * Removes/updates appointments in the list with {@code target} when a patient is removed from the AddressBook.
     */
    public void removePatient(Patient target) {
        ArrayList<Appointment> appointmentsToRemove = new ArrayList<>();

        for (Appointment appointment : appointments) {

            if (appointment.getPatient().equals(target)) {
                appointmentsToRemove.add(appointment);
            }
        }

        for (Appointment appointmentToRemove : appointmentsToRemove) {
            removeAppointment(appointmentToRemove);
        }
    }

    /**
     * Removes {@code key} from this {@code AppointmentBook}. {@code key} must exist in the address book.
     */
    public void removeAppointment(Appointment key) {
        appointments.remove(key);
    }

    /**
     * Adds {@code prescriptionToAdd} to {@code appointmentToMakePrescription}.
     */
    public void addPrescriptionToApmt(Appointment target, Prescription prescriptionToAdd) {
        for (Appointment appointment : appointments) {
            if (appointment.equals(target)) {
                target.addPrescription(prescriptionToAdd);
            }
        }
    }

    //// util methods

    @Override public String toString() {
        Iterator<Appointment> i = appointments.iterator();
        StringBuilder r = new StringBuilder();
        while (i.hasNext()) {
            r.append(i.next());
        }
        return appointments.asUnmodifiableObservableList().size() + " appointments:\n" + r.toString();
        // TODO: refine later
    }

    @Override public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AppointmentBook // instanceof handles nulls
            && appointments.equals(((AppointmentBook) other).appointments));
    }

    @Override public int hashCode() {
        return appointments.hashCode();
    }

}
