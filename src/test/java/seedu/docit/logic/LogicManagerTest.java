package seedu.docit.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX;
import static seedu.docit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.docit.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.docit.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.docit.logic.commands.CommandTestUtil.MEDICAL_DESC_PATIENT;
import static seedu.docit.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.docit.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.docit.testutil.Assert.assertThrows;
import static seedu.docit.testutil.TypicalPatients.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.docit.logic.commands.AddPatientCommand;
import seedu.docit.logic.commands.CommandResult;
import seedu.docit.logic.commands.ListPatientCommand;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.logic.parser.exceptions.ParseException;
import seedu.docit.model.AppointmentBook;
import seedu.docit.model.ArchivedAppointmentBook;
import seedu.docit.model.Model;
import seedu.docit.model.ModelManager;
import seedu.docit.model.ReadOnlyAddressBook;
import seedu.docit.model.ReadOnlyAppointmentBook;
import seedu.docit.model.UserPrefs;
import seedu.docit.model.patient.Patient;
import seedu.docit.storage.JsonAddressBookStorage;
import seedu.docit.storage.JsonAppointmentBookStorage;
import seedu.docit.storage.JsonArchivedAppointmentBookStorage;
import seedu.docit.storage.JsonUserPrefsStorage;
import seedu.docit.storage.StorageManager;
import seedu.docit.testutil.PatientBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");
    private static final String PT_PREFIX = "pt ";

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonAppointmentBookStorage appointmentBookStorage =
            new JsonAppointmentBookStorage(temporaryFolder.resolve("appointmentBook.json"));
        JsonArchivedAppointmentBookStorage archivedAppointmentBookStorage =
                new JsonArchivedAppointmentBookStorage(temporaryFolder.resolve("archivedAppointmentBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, appointmentBookStorage,
                archivedAppointmentBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "pt delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = PT_PREFIX + ListPatientCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListPatientCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonAppointmentBookStorage appointmentBookStorage =
            new JsonAppointmentBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAppointmentBook.json"));
        JsonArchivedAppointmentBookStorage archivedAppointmentBookStorage =
                new JsonArchivedAppointmentBookIoExceptionThrowingStub(temporaryFolder.resolve(
                        "ioExceptionArchivedAppointmentBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, appointmentBookStorage,
                archivedAppointmentBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = PT_PREFIX + AddPatientCommand.COMMAND_WORD + NAME_DESC_AMY
                + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + MEDICAL_DESC_PATIENT;
        Patient expectedPatient = new PatientBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPatient(expectedPatient);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPatientList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getAddressBook(), new AppointmentBook(),
                new ArchivedAppointmentBook(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonAddressBookIoExceptionThrowingStub extends JsonAddressBookStorage {
        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonAppointmentBookIoExceptionThrowingStub extends JsonAppointmentBookStorage {
        private JsonAppointmentBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, ReadOnlyAddressBook addressBook,
            Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonArchivedAppointmentBookIoExceptionThrowingStub extends JsonArchivedAppointmentBookStorage {
        private JsonArchivedAppointmentBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveArchivedAppointmentBook(ReadOnlyAppointmentBook appointmentBook,
                                                ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
