package seedu.docit.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.docit.commons.exceptions.DataConversionException;
import seedu.docit.model.ReadOnlyAddressBook;
import seedu.docit.model.ReadOnlyAppointmentBook;
import seedu.docit.model.ReadOnlyUserPrefs;
import seedu.docit.model.UserPrefs;

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
    Optional<ReadOnlyAppointmentBook> readAppointmentBook(ReadOnlyAddressBook addressBook)
        throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyAppointmentBook> readArchivedAppointmentBook(ReadOnlyAddressBook addressBook)
            throws DataConversionException, IOException;

    @Override
    void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, ReadOnlyAddressBook addressBook)
        throws IOException;

    @Override
    void saveArchivedAppointmentBook(ReadOnlyAppointmentBook archivedAppointmentBook,
                                     ReadOnlyAddressBook addressBook) throws IOException;
}
