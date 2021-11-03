package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private AppointmentBookStorage appointmentBookStorage;
    private ArchivedAppointmentBookStorage archivedAppointmentBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage,
        AppointmentBookStorage appointmentBookStorage,
        ArchivedAppointmentBookStorage archivedAppointmentBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.appointmentBookStorage = appointmentBookStorage;
        this.archivedAppointmentBookStorage = archivedAppointmentBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }


    // ================ AppointmentBook methods ==============================

    @Override
    public Path getAppointmentBookFilePath() {
        return appointmentBookStorage.getAppointmentBookFilePath();
    }

    @Override
    public Path getArchivedAppointmentBookFilePath() {
        return archivedAppointmentBookStorage.getArchivedAppointmentBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAppointmentBook> readAppointmentBook(ReadOnlyAddressBook addressBook)
        throws DataConversionException, IOException {
        return readAppointmentBook(addressBook, appointmentBookStorage.getAppointmentBookFilePath());

    }

    @Override
    public Optional<ReadOnlyAppointmentBook> readAppointmentBook(ReadOnlyAddressBook addressBook, Path filePath)
        throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return appointmentBookStorage.readAppointmentBook(addressBook, filePath);
    }

    @Override
    public Optional<ReadOnlyAppointmentBook> readArchivedAppointmentBook(ReadOnlyAddressBook addressBook)
            throws DataConversionException, IOException {
        return readArchivedAppointmentBook(addressBook,
                archivedAppointmentBookStorage.getArchivedAppointmentBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAppointmentBook> readArchivedAppointmentBook(ReadOnlyAddressBook addressBook, Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return archivedAppointmentBookStorage.readArchivedAppointmentBook(addressBook, filePath);
    }

    @Override
    public void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, ReadOnlyAddressBook addressBook)
        throws IOException {
        saveAppointmentBook(appointmentBook, addressBook, appointmentBookStorage.getAppointmentBookFilePath());
    }

    @Override
    public void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, ReadOnlyAddressBook addressBook,
        Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        appointmentBookStorage.saveAppointmentBook(appointmentBook, addressBook, filePath);
    }

    @Override
    public void saveArchivedAppointmentBook(ReadOnlyAppointmentBook archivedAppointmentBook,
                                            ReadOnlyAddressBook addressBook) throws IOException {
        saveAppointmentBook(archivedAppointmentBook, addressBook,
                archivedAppointmentBookStorage.getArchivedAppointmentBookFilePath());
    }

    @Override
    public void saveArchivedAppointmentBook(ReadOnlyAppointmentBook archivedAppointmentBook,
                                            ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        archivedAppointmentBookStorage.saveArchivedAppointmentBook(archivedAppointmentBook, addressBook, filePath);
    }
}
