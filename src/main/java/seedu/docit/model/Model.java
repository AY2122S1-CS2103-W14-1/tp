package seedu.docit.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.docit.commons.core.GuiSettings;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.patient.Patient;
import seedu.docit.model.prescription.Prescription;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;
    Predicate<Prescription> PREDICATE_SHOW_ALL_PRESCRIPTIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address book.
     */
    boolean hasPatient(Patient patient);

    /**
     * Deletes the given patient. The patient must exist in the address book.
     */
    void deletePatient(Patient target);

    /**
     * Adds the given patient. {@code patient} must not already exist in the address book.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}. {@code target} must exist in the docit
     * book. The patient identity of {@code editedPatient} must not be the same as another existing patient in the docit
     * book.
     */
    void setPatient(Patient target, Patient editedPatient);

    /** Returns an unmodifiable view of the filtered patient list */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    /**
     * Returns the user prefs' appointment Book file path.
     */
    Path getAppointmentBookFilePath();

    /**
     * Sets the user prefs' appointment Book file path.
     */
    void setAppointmentBookFilePath(Path appointmentBookFilePath);

    /**
     * Replaces appointment Book data with the data in {@code appointmentBook}.
     */
    void setAppointmentBook(ReadOnlyAppointmentBook appointmentBook);

    /**
     * Replaces archived appointment Book data with the data in {@code appointmentBook}.
     */
    void setArchivedAppointmentBook(ReadOnlyAppointmentBook appointmentBook);

    /**
     * Updates appointments' details in appointment Book data with {@code editedPatient}'s details.
     */
    void updateAppointmentBook(Patient target, Patient editedPatient);

    /**
     * Removes appointments in appointment Book data when a Patient is removed.
     * Appointments with the Patient will be deleted.
     */
    void deleteAppointmentsWithPatient(Patient target);

    /**
     * Returns the AppointmentBook
     */
    ReadOnlyAppointmentBook getAppointmentBook();

    /**
     * Returns the ArchivedAppointmentBook
     */
    ReadOnlyAppointmentBook getArchivedAppointmentBook();

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the appointment Book.
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Returns true if an appointment with the same identity as {@code appointmentToArchive} exists
     * in the appointment archive book.
     */
    boolean hasAppointmentInArchives(Appointment appointmentToArchive);

    /**
     * Deletes the given appointment. The appointment must exist in the appointment Book.
     */
    void deleteAppointment(Appointment target);

    /**
     * Adds the given appointment. {@code appointment} must not already exist in the appointment Book.
     */
    void addAppointment(Appointment appointment);

    /**
     * Archives the given appointment.The appointment must exist in the appointment Book.
     */
    void archiveAppointment(Appointment appointmentToArchive);

    /**
     * Replaces the given appointment {@code target} with {@code editedAppointment}. {@code target} must exist in the
     * appointment Book. The appointment identity of {@code editedAppointment} must not be the same as another existing
     * appointment in the appointment Book.
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

    /**
     * Sorts appointments in order of whether its today, followed by dateTime, and patient name.
     */
    void sortAppointments();

    /**
     * Temporarily returns appointment list to be printed in CommandResult.
     */
    String getAppointments();

    /**
     * Temporarily returns archived appointment list to be printed in CommandResult.
     */
    String getArchivedAppointments();

    /**
     * Adds a prescription to appointment i in the list.
     */
    public void addPrescription(Appointment target, Prescription p);

    /**
     * Removes a prescription from an appointment i in the list.
     */
    public void deletePrescription(Appointment target, String medicine);

    /**
     * Edits a prescription from an appointment i in the list.
     */
    public void editPrescription(int index, Prescription p);

    /**
     * Returns an unmodifiable view of the filtered appointment list
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Returns an unmodifiable view of the filtered appointment list
     */
    ObservableList<Appointment> getArchivedAppointmentList();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);

    /**
     * Archives all appointments 1-day past their scheduled date.
     */
    void archivePastAppointments();

    /**
     * Deletes all records of patients, appointments, and archived appointments.
     */
    void clearAllRecords();
}
