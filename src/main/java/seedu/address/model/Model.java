package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Patient;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;

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
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPatient(Patient patient);

    /**
     * Deletes the given person. The person must exist in the address book.
     */
    void deletePatient(Patient target);

    /**
     * Adds the given person. {@code person} must not already exist in the address book.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}. {@code target} must exist in the address
     * book. The person identity of {@code editedPerson} must not be the same as another existing person in the address
     * book.
     */
    void setPatient(Patient target, Patient editedPatient);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
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
     * Returns the AppointmentBook
     */
    ReadOnlyAppointmentBook getAppointmentBook();

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the appointment Book.
     */
    boolean hasAppointment(Appointment appointment);

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

    // TODO: Remove and replace with appointment view in UI

    /**
     * Temporarily returns appointment list to be printed in CommandResult.
     */
    String getAppointments();

    /**
     * Temporarily returns archived appointment list to be printed in CommandResult.
     */
    String getArchivedAppointments();

    /**
     * Returns an unmodifiable view of the filtered appointment list
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);
}
