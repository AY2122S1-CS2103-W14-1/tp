package seedu.docit.logic.commands.prescriptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.docit.logic.commands.prescription.AddPrescriptionCommand.INPUT_TOO_LONG_ERROR_MESSAGE;
import static seedu.docit.logic.commands.prescription.AddPrescriptionCommand.MESSAGE_SUCCESS;
import static seedu.docit.testutil.Assert.assertThrows;
import static seedu.docit.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.CommandResult;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.logic.commands.prescription.AddPrescriptionCommand;
import seedu.docit.model.ArchivedAppointmentBook;
import seedu.docit.model.Model;
import seedu.docit.model.ModelManager;
import seedu.docit.model.UserPrefs;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.prescription.Prescription;

public class AddPrescriptionCommandTest {
    private static final String defaultMedicine = "Penicillin";
    private static final String defaultVolume = "400 ml";
    private static final String defaultDuration = "3 times a day";
    private static final Prescription validPrescription =
            new Prescription(defaultMedicine, defaultVolume, defaultDuration);

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalAppointmentBook(),
            new ArchivedAppointmentBook(), new UserPrefs());
    private final Appointment defaultAppointment = model.getAppointmentBook().getAppointmentList().get(0);

    private String generateString(char character, int length) {
        char[] chars = new char[length];
        Arrays.fill(chars, character);
        String result = new String(chars);
        return result;
    }

    @Test
    public void constructor_nullPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddPrescriptionCommand(null, null, null, null));
    }

    @Test
    public void execute_validPrescription_addSuccessfully() throws CommandException {
        AddPrescriptionCommand addPrescriptionCommand =
                new AddPrescriptionCommand(Index.fromOneBased(2), defaultMedicine, defaultVolume, defaultDuration);

        CommandResult actualCommandResult = addPrescriptionCommand.execute(model);
        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS,
                defaultMedicine.toLowerCase(), defaultVolume.toLowerCase(), defaultDuration.toLowerCase()));

        assertEquals(actualCommandResult, expectedCommandResult);

    }

    @Test
    public void execute_duplicatePrescription_throwsCommandException() {
        AddPrescriptionCommand addPrescriptionCommand =
                new AddPrescriptionCommand(Index.fromOneBased(1), defaultMedicine, defaultVolume, defaultDuration);

        defaultAppointment.addPrescription(validPrescription);

        assertThrows(CommandException.class, AddPrescriptionCommand.MESSAGE_DUPLICATE_MEDICINE, () ->
                addPrescriptionCommand.execute(model));

    }

    @Test
    public void execute_appointmentToAddDoesNotExist_throwsCommandException() {
        int maxSize = model.getAppointmentBook().getAppointmentList().size();
        AddPrescriptionCommand invalidAddPrescriptionCommand =
                new AddPrescriptionCommand(Index.fromOneBased(maxSize + 1),
                        defaultMedicine, defaultVolume, defaultDuration);

        assertThrows(CommandException.class, MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX, () ->
                invalidAddPrescriptionCommand.execute(model));

    }

    @Test
    public void execute_appointmentToAddMedicineTooLong_throwsCommandException() {
        String longMedicineName = generateString('A', Prescription.MEDICINE_CHAR_LENGTH_LIMIT + 1);
        AddPrescriptionCommand invalidAddPrescriptionCommand =
                new AddPrescriptionCommand(Index.fromOneBased(1), longMedicineName, defaultVolume, defaultDuration);

        assertThrows(CommandException.class, INPUT_TOO_LONG_ERROR_MESSAGE, () ->
                invalidAddPrescriptionCommand.execute(model));

    }

    @Test
    public void execute_appointmentToAddVolumeTooLong_throwsCommandException() {
        String longVolumeInput = generateString('A', Prescription.VOLUME_CHAR_LENGTH_LIMIT + 1);
        AddPrescriptionCommand invalidAddPrescriptionCommand =
                new AddPrescriptionCommand(Index.fromOneBased(3), defaultMedicine, longVolumeInput, defaultDuration);

        assertThrows(CommandException.class, INPUT_TOO_LONG_ERROR_MESSAGE, () ->
                invalidAddPrescriptionCommand.execute(model));

    }

    @Test
    public void execute_appointmentToAddDurationTooLong_throwsCommandException() {
        String longDurationInput = generateString('A', Prescription.DURATION_CHAR_LENGTH_LIMIT + 1);
        AddPrescriptionCommand invalidAddPrescriptionCommand =
                new AddPrescriptionCommand(Index.fromOneBased(1), defaultMedicine, defaultVolume, longDurationInput);

        assertThrows(CommandException.class, INPUT_TOO_LONG_ERROR_MESSAGE, () ->
                invalidAddPrescriptionCommand.execute(model));

    }

    @Test
    public void execute_appointmentToAllFieldsMaxLength_addSuccessfully() throws CommandException {
        String longMedicineName = generateString('A', Prescription.MEDICINE_CHAR_LENGTH_LIMIT);
        String longVolumeInput = generateString('A', Prescription.VOLUME_CHAR_LENGTH_LIMIT);
        String longDurationInput = generateString('A', Prescription.DURATION_CHAR_LENGTH_LIMIT);
        AddPrescriptionCommand addPrescriptionCommand =
                new AddPrescriptionCommand(Index.fromOneBased(1), longMedicineName, longVolumeInput, longDurationInput);

        CommandResult actualCommandResult = addPrescriptionCommand.execute(model);
        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS,
                longMedicineName.toLowerCase(), longVolumeInput.toLowerCase(), longDurationInput.toLowerCase()));

        assertEquals(actualCommandResult, expectedCommandResult);

    }

    @Test
    public void equals_sameContent_success() {
        AddPrescriptionCommand addPrescriptionCommand = new AddPrescriptionCommand(Index.fromOneBased(1),
                defaultMedicine, defaultVolume, defaultDuration);
        AddPrescriptionCommand addPrescriptionCommandCopy = new AddPrescriptionCommand(Index.fromOneBased(1),
                defaultMedicine, defaultVolume, defaultDuration);
        assert(addPrescriptionCommand.equals(addPrescriptionCommand));
        assert(addPrescriptionCommand.equals(addPrescriptionCommandCopy));
    }

    @Test
    public void equals_differentContent_failure() {
        AddPrescriptionCommand addPrescriptionCommand = new AddPrescriptionCommand(Index.fromOneBased(1),
                defaultMedicine, defaultVolume, defaultDuration);
        AddPrescriptionCommand addPrescriptionCommandNotSame = new AddPrescriptionCommand(
                Index.fromOneBased(1),
                "medicine", "20 ml", "twice a day");
        assertFalse(addPrescriptionCommand.equals(addPrescriptionCommandNotSame));
    }

    @Test
    public void equals_null_failure() {
        AddPrescriptionCommand addPrescriptionCommand = new AddPrescriptionCommand(Index.fromOneBased(1),
                defaultMedicine, defaultVolume, defaultDuration);
        assertFalse(addPrescriptionCommand.equals(null));
    }
}
