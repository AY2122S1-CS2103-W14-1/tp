package seedu.docit.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.docit.commons.core.GuiSettings;
import seedu.docit.logic.commands.CommandResult;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.logic.parser.exceptions.ParseException;
import seedu.docit.model.Model;
import seedu.docit.model.ReadOnlyAddressBook;
import seedu.docit.model.ReadOnlyAppointmentBook;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.patient.Patient;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of patients */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the AppointmentBook.
     *
     * @see Model#getAppointmentBook()
     */
    ReadOnlyAppointmentBook getAppointmentBook();

    /**
     * Returns the ArchivedAppointmentBook.
     *
     * @see Model#getArchivedAppointmentBook()
     */
    ReadOnlyAppointmentBook getArchivedAppointmentBook();

    /** Returns an unmodifiable view of the filtered list of appointments */
    ObservableList<Appointment> getFilteredAppointmentList();

    /** Returns an unmodifiable view of the archived list of appointments */
    ObservableList<Appointment> getArchivedAppointmentList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAppointmentBookFilePath();


    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
