package seedu.docit.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.docit.testutil.Assert.assertThrows;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.docit.commons.exceptions.IllegalValueException;
import seedu.docit.commons.util.JsonUtil;
import seedu.docit.model.AppointmentBook;
import seedu.docit.testutil.TypicalAppointments;

public class JsonSerializableAppointmentBookTest {

    private static final Path TEST_DATA_FOLDER =
        Paths.get("src", "test", "data", "JsonSerializableAppointmentBookTest");
    private static final Path TYPICAL_APPOINTMENTS_FILE =
        TEST_DATA_FOLDER.resolve("typicalAppointmentsAppointmentBook.json");
    private static final Path INVALID_APPOINTMENT_FILE =
        TEST_DATA_FOLDER.resolve("invalidAppointmentAppointmentBook.json");
    private static final Path DUPLICATE_APPOINTMENT_FILE =
        TEST_DATA_FOLDER.resolve("duplicateAppointmentAppointmentBook.json");

    @BeforeAll
    public static void resetTypicalAppointments() {
        TypicalAppointments.resetPrescriptions();
    }

    @Test
    public void toModelType_typicalAppointmentsFile_success() throws Exception {
        JsonSerializableAppointmentBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_APPOINTMENTS_FILE,
                JsonSerializableAppointmentBook.class).get();
        AppointmentBook appointmentBookFromFile = dataFromFile.toModelType(getTypicalAddressBook());
        AppointmentBook typicalAppointmentsAppointmentBook = TypicalAppointments.getTypicalAppointmentBook();
        assertEquals(appointmentBookFromFile, typicalAppointmentsAppointmentBook);
    }

    @Test
    public void toModelType_invalidAppointmentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAppointmentBook dataFromFile = JsonUtil.readJsonFile(INVALID_APPOINTMENT_FILE,
                JsonSerializableAppointmentBook.class).get();
        assertThrows(
            IllegalValueException.class, () -> dataFromFile.toModelType(getTypicalAddressBook()));
    }

    @Test
    public void toModelType_duplicateAppointments_throwsIllegalValueException() throws Exception {
        JsonSerializableAppointmentBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_APPOINTMENT_FILE,
                JsonSerializableAppointmentBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAppointmentBook.MESSAGE_DUPLICATE_APPOINTMENT, ()
            -> dataFromFile.toModelType(getTypicalAddressBook()));
    }

}
