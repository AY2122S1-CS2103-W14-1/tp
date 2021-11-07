package seedu.docit.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.docit.storage.JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.docit.testutil.Assert.assertThrows;
import static seedu.docit.testutil.TypicalAppointments.A1;
import static seedu.docit.testutil.TypicalAppointments.B1;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.docit.commons.core.index.Index;
import seedu.docit.commons.exceptions.IllegalValueException;

public class JsonAdaptedAppointmentTest {
    private static final String INVALID_PATIENT_INDEX = "a1";
    private static final String INVALID_DATETIME = "22012sa";

    private static final String VALID_PATIENT_INDEX = "1";
    private static final String VALID_DATETIME = "2022-2-1 1339";
    private static final List<JsonAdaptedPrescription> VALID_PRESCRIPTIONS =
        A1.getPrescriptions()
              .stream()
              .filter(x -> x != null)
              .map(x -> new JsonAdaptedPrescription(x.getVolume(), x.getMedicine(), x.getDuration()))
              .collect(Collectors.toList());

    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(B1, getTypicalAddressBook());
        assertEquals(B1, appointment.toModelType(getTypicalAddressBook()));
    }

    @Test
    public void toModelType_invalidPatientIndex_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(INVALID_PATIENT_INDEX, VALID_DATETIME, VALID_PRESCRIPTIONS);
        String expectedMessage = "Patient index must be an integer.";
        assertThrows(IllegalValueException.class,
            expectedMessage, () -> appointment.toModelType(getTypicalAddressBook()));
    }

    @Test
    public void toModelType_nullPatientIndex_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(null,
            VALID_DATETIME, VALID_PRESCRIPTIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Index.class.getSimpleName());
        assertThrows(IllegalValueException.class,
            expectedMessage, () -> appointment.toModelType(getTypicalAddressBook()));
    }

    @Test
    public void toModelType_invalidDatetime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
                new JsonAdaptedAppointment(VALID_PATIENT_INDEX, INVALID_DATETIME, VALID_PRESCRIPTIONS);
        String expectedMessage = LocalDateTime.class.getSimpleName() + " is of incorrect format.";
        assertThrows(IllegalValueException.class,
            expectedMessage, () -> appointment.toModelType(getTypicalAddressBook()));
    }

    @Test
    public void toModelType_nullDatetime_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_PATIENT_INDEX,
            null, VALID_PRESCRIPTIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class,
            expectedMessage, () -> appointment.toModelType(getTypicalAddressBook()));
    }

}
