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
public interface ArchivedAppointmentBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getArchivedAppointmentBookFilePath();

    /**
     * Returns AppointmentBook data as a {@link ReadOnlyAppointmentBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAppointmentBook> readArchivedAppointmentBook(ReadOnlyAddressBook addressBook)
            throws DataConversionException, IOException;

    /**
     * @see #getArchivedAppointmentBookFilePath()
     */
    Optional<ReadOnlyAppointmentBook> readArchivedAppointmentBook(ReadOnlyAddressBook addressBook, Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAppointmentBook} to the storage.
     * @param appointmentBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveArchivedAppointmentBook(ReadOnlyAppointmentBook appointmentBook,
                                     ReadOnlyAddressBook addressBook) throws IOException;

    /**
     * @see #saveArchivedAppointmentBook(ReadOnlyAppointmentBook)
     */
    void saveArchivedAppointmentBook(ReadOnlyAppointmentBook appointmentBook, ReadOnlyAddressBook addressBook,
                                     Path filePath) throws IOException;
}
