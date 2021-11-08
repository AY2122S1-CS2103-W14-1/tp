package seedu.docit.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.docit.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.docit.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.docit.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.docit.model.prescription.Prescription;

/**
 * A list of appointments that enforces uniqueness between its elements and does not allow nulls. An appointment is
 * considered unique by comparing using {@code Appointment#isSameAppointment(Appointment)}. As such, adding and updating
 * of appointments uses Appointment#isSameAppointment(Appointment) for equality so as to ensure that the appointment
 * being added or updated is unique in terms of identity in the UniqueAppointmentList. However, the removal of a
 * appointment uses Appointment#equals(Object) so as to ensure that the appointment with exactly the same fields will be
 * removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Appointment#isSameAppointment(Appointment)
 */
public class UniqueAppointmentList implements Iterable<Appointment> {

    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent appointment as the given argument.
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAppointment);
    }

    /**
     * Adds an appointment to the list. The appointment must not already exist in the list.
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAppointmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the appointment {@code target} in the list with {@code editedAppointment}. {@code target} must exist in
     * the list. The appointment identity of {@code editedAppointment} must not be the same as another existing
     * appointment in the list.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AppointmentNotFoundException();
        }

        if (!target.isSameAppointment(editedAppointment) && contains(editedAppointment)) {
            throw new DuplicateAppointmentException();
        }

        internalList.set(index, editedAppointment);
    }

    /**
     * Removes the equivalent appointment from the list. The appointment must exist in the list.
     */
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AppointmentNotFoundException();
        }
    }

    public void setAppointments(UniqueAppointmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code appointments}. {@code appointments} must not contain duplicate
     * appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!appointmentsAreUnique(appointments)) {
            throw new DuplicateAppointmentException();
        }

        internalList.setAll(appointments);
    }

    /**
     * Sorts the contents of this list.
     */
    public void sort() {
        internalList.sort(Appointment::compareTo);
    }

    /**
     * Adds a prescription to the indexed appointment in the list.
     */
    public void addPrescription(int index, Prescription p) {
        internalList.get(index).addPrescription(p);
    }

    /**
     * Removes a prescription from an appointment specified by the index in the list.
     */
    public void deletePrescription(int index, String medicine) {
        internalList.get(index).deletePrescription(medicine);
    }

    /**
     * Edits a prescription from an appointment specified by the index in the list.
     */
    public void editPrescription(int index, Prescription p) {
        internalList.get(index).editPrescription(p);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }

    @Override public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueAppointmentList // instanceof handles nulls
            && internalList.equals(((UniqueAppointmentList) other).internalList));
    }

    @Override public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code appointments} contains only unique appointments.
     */
    private boolean appointmentsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).isSameAppointment(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
