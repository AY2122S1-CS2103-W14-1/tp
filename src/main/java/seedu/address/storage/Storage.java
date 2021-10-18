package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, AppointmentBookStorage,
        ArchivedAppointmentBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Path getAppointmentBookFilePath();

    @Override
    Path getArchivedAppointmentBookFilePath();

    @Override
    Optional<ReadOnlyAppointmentBook> readAppointmentBook() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyAppointmentBook> readArchivedAppointmentBook() throws DataConversionException, IOException;

    @Override
    void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook) throws IOException;

    @Override
    void saveArchivedAppointmentBook(ReadOnlyAppointmentBook archivedAppointmentBook) throws IOException;
}
