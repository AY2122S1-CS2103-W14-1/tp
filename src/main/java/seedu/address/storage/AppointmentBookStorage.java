package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;

/**
 * Represents a storage for {@link seedu.address.model.AppointmentBook}.
 */
public interface AppointmentBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAppointmentBookFilePath();

    /**
     * Returns AppointmentBook data as a {@link ReadOnlyAppointmentBook}. Returns {@code Optional.empty()} if storage
     * file is not found.
     *
     * @param addressBook address book.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAppointmentBook> readAppointmentBook(ReadOnlyAddressBook addressBook)
        throws DataConversionException, IOException;

    /**
     * @see #getAppointmentBookFilePath()
     */
    Optional<ReadOnlyAppointmentBook> readAppointmentBook(ReadOnlyAddressBook addressBook, Path filePath)
        throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAppointmentBook} to the storage.
     *
     * @param appointmentBook cannot be null.
     * @param addressBook     address book.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, ReadOnlyAddressBook addressBook)
        throws IOException;

    /**
     * @see #saveAppointmentBook(ReadOnlyAppointmentBook, ReadOnlyAddressBook)
     */
    void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, ReadOnlyAddressBook addressBook, Path filePath)
        throws IOException;
}
