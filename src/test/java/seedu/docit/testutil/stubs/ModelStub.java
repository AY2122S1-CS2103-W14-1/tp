package seedu.docit.testutil.stubs;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.docit.commons.core.GuiSettings;
import seedu.docit.model.Model;
import seedu.docit.model.ReadOnlyAddressBook;
import seedu.docit.model.ReadOnlyAppointmentBook;
import seedu.docit.model.ReadOnlyUserPrefs;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.patient.Patient;
import seedu.docit.model.prescription.Prescription;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPatient(Patient patient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPatient(Patient patient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePatient(Patient target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAppointmentBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAppointmentBookFilePath(Path appointmentBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addAppointment(Appointment appointment) {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Archives the given appointment.The appointment must exist in the appointment Book.
     *
     * @param appointmentToArchive
     */
    @Override
    public void archiveAppointment(Appointment appointmentToArchive) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAppointmentBook(ReadOnlyAppointmentBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateAppointmentBook(Patient target, Patient editedPatient) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteAppointmentsWithPatient(Patient target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAppointmentBook getAppointmentBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAppointmentBook getArchivedAppointmentBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteAppointment(Appointment target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void sortAppointments() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public String getAppointments() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public String getArchivedAppointments() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Appointment> getArchivedAppointmentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void archivePastAppointments() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPrescription(Appointment target, Prescription p) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePrescription(Appointment target, String medicine) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void editPrescription(int i, Prescription p) {
        throw new AssertionError("This method should not be called.");
    }
}
