package seedu.docit.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.docit.testutil.Assert.assertThrows;
import static seedu.docit.testutil.TypicalAppointments.A1;
import static seedu.docit.testutil.TypicalAppointments.C1;
import static seedu.docit.testutil.TypicalAppointments.C2;
import static seedu.docit.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.docit.commons.exceptions.DataConversionException;
import seedu.docit.model.AppointmentBook;
import seedu.docit.model.ReadOnlyAppointmentBook;
import seedu.docit.testutil.TypicalAppointments;

public class JsonAppointmentBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAppointmentBookStorageTest");

    @TempDir
    public Path testFolder;

    @BeforeAll
    public static void resetTypicalAppointments() {
        TypicalAppointments.resetPrescriptions();
    }

    @Test
    public void readAppointmentBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAppointmentBook(null));
    }

    private java.util.Optional<ReadOnlyAppointmentBook> readAppointmentBook(String filePath) throws Exception {
        return new JsonAppointmentBookStorage(Paths.get(filePath)).readAppointmentBook(getTypicalAddressBook(),
            addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAppointmentBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAppointmentBook("notJsonFormatAppointmentBook.json"));
    }

    @Test
    public void readAppointmentBook_invalidAppointmentAppointmentBook_throwDataConversionException() {
        assertThrows(
            DataConversionException.class, () -> readAppointmentBook("invalidAppointmentAppointmentBook.json"));
    }

    @Test
    public void readAppointmentBook_invalidAndValidAppointmentAppointmentBook_throwDataConversionException() {
        assertThrows(
            DataConversionException.class, () -> readAppointmentBook("invalidAndValidAppointmentAppointmentBook.json"));
    }

    @Test
    public void readAndSaveAppointmentBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAppointmentBook.json");
        AppointmentBook original = getTypicalAppointmentBook();
        JsonAppointmentBookStorage jsonAppointmentBookStorage = new JsonAppointmentBookStorage(filePath);

        // Save in new file and read back
        jsonAppointmentBookStorage.saveAppointmentBook(original, getTypicalAddressBook(), filePath);
        ReadOnlyAppointmentBook readBack = jsonAppointmentBookStorage.readAppointmentBook(getTypicalAddressBook(),
            filePath).get();
        assertEquals(original, new AppointmentBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addAppointment(C1);
        original.removeAppointment(A1);
        jsonAppointmentBookStorage.saveAppointmentBook(original, getTypicalAddressBook(), filePath);
        readBack = jsonAppointmentBookStorage.readAppointmentBook(getTypicalAddressBook(), filePath).get();
        assertEquals(original, new AppointmentBook(readBack));

        // Save and read without specifying file path
        original.addAppointment(C2);
        jsonAppointmentBookStorage.saveAppointmentBook(original, getTypicalAddressBook()); // file path not specified
        readBack = jsonAppointmentBookStorage
            .readAppointmentBook(getTypicalAddressBook()).get(); // file path not specified
        assertEquals(original, new AppointmentBook(readBack));

    }

    @Test
    public void saveAppointmentBook_nullAppointmentBook_throwsNullPointerException() {
        assertThrows(
            NullPointerException.class, () -> saveAppointmentBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code appointmentBook} at the specified {@code filePath}.
     */
    private void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, String filePath) {
        try {
            new JsonAppointmentBookStorage(Paths.get(filePath))
                    .saveAppointmentBook(appointmentBook, getTypicalAddressBook(),
                        addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAppointmentBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAppointmentBook(new AppointmentBook(), null));
    }
}
