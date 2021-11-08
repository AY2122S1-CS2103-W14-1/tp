package seedu.docit.model;

import static java.util.Objects.requireNonNull;
import static seedu.docit.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.docit.commons.core.GuiSettings;
import seedu.docit.commons.core.LogsCenter;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.patient.Patient;
import seedu.docit.model.prescription.Prescription;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final int UPDATE_HOUR = 5;

    private final AddressBook addressBook;
    private final AppointmentBook appointmentBook;
    private final ArchivedAppointmentBook archivedAppointmentBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Appointment> filteredAppointments;

    /**
     * Initializes a ModelManager with the given addressBook, appointmentBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyAppointmentBook appointmentBook,
        ReadOnlyAppointmentBook archivedAppointmentBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, appointmentBook, archivedAppointmentBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " appointment book: " + appointmentBook + " and "
            + "archived appointment book: " + archivedAppointmentBook + "and" + "user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.appointmentBook = new AppointmentBook(appointmentBook);
        this.archivedAppointmentBook = new ArchivedAppointmentBook(archivedAppointmentBook);
        this.userPrefs = new UserPrefs(userPrefs);

        filteredPatients = new FilteredList<>(this.addressBook.getPatientList());
        filteredAppointments = new FilteredList<>(this.appointmentBook.getAppointmentList());

        initAutoArchive();
    }

    public ModelManager() {
        this(new AddressBook(), new AppointmentBook(), new AppointmentBook(), new UserPrefs());
    }

    /**
     * Sets up scheduler to auto-archive past appointments
     */
    private void initAutoArchive() {
        // @@author joshenx-reused
        // Reused from https://stackoverflow.com/a/20388073
        // with minor modifications. Runnable class AutoArchiveAmpts is newly implemented.
        archivePastAppointments();

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Singapore"));
        ZonedDateTime nextRun = now.withHour(UPDATE_HOUR).withMinute(0).withSecond(0);
        if (now.compareTo(nextRun) > 0) {
            nextRun = nextRun.plusDays(1);
        }

        Duration duration = Duration.between(now, nextRun);
        long initialDelay = duration.getSeconds();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new AutoArchiveApmts(this),
                initialDelay,
                TimeUnit.DAYS.toSeconds(1),
                TimeUnit.SECONDS);
        // @@author
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public Path getAppointmentBookFilePath() {
        return userPrefs.getAppointmentBookFilePath();
    }

    @Override
    public void setAppointmentBookFilePath(Path appointmentBookFilePath) {
        requireNonNull(appointmentBookFilePath);
        userPrefs.setAppointmentBookFilePath(appointmentBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return addressBook.hasPatient(patient);
    }

    @Override
    public void deletePatient(Patient target) {
        addressBook.removePatient(target);
    }

    @Override
    public void addPatient(Patient patient) {
        addressBook.addPatient(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        addressBook.setPatient(target, editedPatient);
    }

    //=========== Filtered Patient List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    //=========== AppointmentBook ================================================================================

    @Override
    public void setAppointmentBook(ReadOnlyAppointmentBook appointmentBook) {
        this.appointmentBook.resetData(appointmentBook);
    }

    @Override
    public void updateAppointmentBook(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        appointmentBook.updatePatient(target, editedPatient);
        archivedAppointmentBook.updatePatient(target, editedPatient);
    }

    @Override
    public void deleteAppointmentsWithPatient(Patient target) {
        requireNonNull(target);

        appointmentBook.removePatient(target);
        archivedAppointmentBook.removePatient(target);
    }

    @Override
    public ReadOnlyAppointmentBook getAppointmentBook() {
        return appointmentBook;
    }

    @Override
    public ReadOnlyAppointmentBook getArchivedAppointmentBook() {
        return archivedAppointmentBook;
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointmentBook.hasAppointment(appointment);
    }

    @Override
    public boolean hasAppointmentInArchives(Appointment appointment) {
        requireNonNull(appointment);
        return archivedAppointmentBook.hasAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Appointment target) {
        appointmentBook.removeAppointment(target);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentBook.addAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public void archiveAppointment(Appointment target) {
        appointmentBook.removeAppointment(target);
        archivedAppointmentBook.addAppointment(target);
    }

    @Override
    public void archivePastAppointments() {
        ArrayList<Appointment> appointmentsToArchive = new ArrayList<>();
        ObservableList<Appointment> appointmentList = appointmentBook.getAppointmentList();

        for (Appointment appointment : appointmentList) {
            if (isExpired(appointment)) {
                appointmentsToArchive.add(appointment);
            }
        }

        for (Appointment appointmentToArchive : appointmentsToArchive) {
            archiveAppointment(appointmentToArchive);
        }
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        appointmentBook.setAppointment(target, editedAppointment);
    }

    @Override
    public void sortAppointments() {
        appointmentBook.sortAppointments();
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public void addPrescription(Appointment target, Prescription p) {
        Set<Prescription> nextPrescription = target.getPrescriptions();
        nextPrescription.add(p);

        Appointment editedAppt = new Appointment(target.getPatient(), target.getDatetime(), nextPrescription);
        appointmentBook.setAppointment(target, editedAppt);
    }

    @Override
    public void deletePrescription(Appointment target, String medicine) {
        target.deletePrescription(medicine);
        Set<Prescription> nextPrescription = target.getPrescriptions();
        Appointment editedAppt = new Appointment(target.getPatient(), target.getDatetime(), nextPrescription);
        appointmentBook.setAppointment(target, editedAppt);
    }

    @Override
    public void editPrescription(int index, Prescription p) {
        appointmentBook.editPrescription(index, p);
    }

    //=========== ArchivedAppointmentBook =======================================================================

    @Override
    public void setArchivedAppointmentBook(ReadOnlyAppointmentBook appointmentBook) {
        this.archivedAppointmentBook.resetData(appointmentBook);
    }

    /**
     * Checks if Appointment is 24-hours/1-day past its scheduled time.
     *
     * @param appointment
     * @return true if appointment is past its scheduled time.
     */
    public boolean isExpired(Appointment appointment) {
        Clock cl = Clock.systemUTC();
        LocalDateTime now = LocalDateTime.now(cl);
        LocalDateTime apptTime = appointment.getDatetime();
        Duration duration = Duration.between(apptTime, now);

        return duration.toDays() >= 1;
    }

    @Override
    public String getAppointments() {
        return appointmentBook.toString();
    }

    @Override
    public String getArchivedAppointments() {
        return archivedAppointmentBook.toString();
    }

    @Override
    public void clearAllRecords() {
        this.setAppointmentBook(new AppointmentBook());
        this.setArchivedAppointmentBook(new AppointmentBook());
        this.setAddressBook(new AddressBook());
    }

    /**
     * Returns an unmodifiable view of the list of archived {@code Appointment}s.
     */
    @Override
    public ObservableList<Appointment> getArchivedAppointmentList() {
        return archivedAppointmentBook.getAppointmentList();
    }

    //=========== Filtered Appointment List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Appointment} backed by the internal list of
     * {@code versionedAppointmentBook}
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPatients.equals(other.filteredPatients)
                && appointmentBook.equals(other.appointmentBook)
                && archivedAppointmentBook.equals(other.archivedAppointmentBook)
                && filteredAppointments.equals(other.filteredAppointments);
    }

}
