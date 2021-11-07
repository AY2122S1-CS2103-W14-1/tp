package seedu.docit.logic.commands.prescriptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.docit.logic.commands.prescription.DeletePrescriptionCommand.MESSAGE_DELETE_PRESCRIPTION_SUCCESS;
import static seedu.docit.testutil.Assert.assertThrows;
import static seedu.docit.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.CommandResult;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.logic.commands.prescription.DeletePrescriptionCommand;
import seedu.docit.model.ArchivedAppointmentBook;
import seedu.docit.model.Model;
import seedu.docit.model.ModelManager;
import seedu.docit.model.UserPrefs;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.prescription.Prescription;



public class DeletePrescriptionCommandTest {
    private static final String defaultMedicine = "Panadol";
    private static final String defaultVolume = "400 ml";
    private static final String defaultDuration = "3 times a day";
    private static final Prescription validPrescription =
            new Prescription(defaultMedicine, defaultVolume, defaultDuration);

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalAppointmentBook(),
            new ArchivedAppointmentBook(), new UserPrefs());
    private final Appointment defaultAppointment = model.getAppointmentBook().getAppointmentList().get(0);

    @Test
    public void constructor_nullPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeletePrescriptionCommand(null, null));
    }

    @Test
    public void execute_validPrescription_deleteSuccessfully() throws CommandException {
        defaultAppointment.addPrescription(validPrescription);
        DeletePrescriptionCommand deletePrescriptionCommand =
                new DeletePrescriptionCommand(Index.fromOneBased(1), defaultMedicine);

        CommandResult actualCommandResult = deletePrescriptionCommand.execute(model);
        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_DELETE_PRESCRIPTION_SUCCESS,
                defaultMedicine.toLowerCase(), defaultAppointment.getPatient().getName()));

        assertEquals(actualCommandResult, expectedCommandResult);

    }

    @Test
    public void execute_missingAppointment_throwsCommandException() throws CommandException {
        int maxSize = model.getAppointmentBook().getAppointmentList().size();
        DeletePrescriptionCommand deletePrescriptionCommand =
                new DeletePrescriptionCommand(Index.fromOneBased(maxSize + 1), defaultMedicine);

        assertThrows(CommandException.class, MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX, () ->
                deletePrescriptionCommand.execute(model));

    }

    @Test
    public void execute_missingPrescription_throwsCommandException() throws CommandException {
        DeletePrescriptionCommand deletePrescriptionCommand =
                new DeletePrescriptionCommand(Index.fromOneBased(1), defaultMedicine);

        assertThrows(CommandException.class, "Medicine name not found in prescription list", () ->
                deletePrescriptionCommand.execute(model));

    }

    @Test
    public void equals_sameContent_success() {
        DeletePrescriptionCommand deletePrescriptionCommand = new DeletePrescriptionCommand(Index.fromOneBased(1),
                defaultMedicine);
        DeletePrescriptionCommand deletePrescriptionCommandCopy = new DeletePrescriptionCommand(Index.fromOneBased(1),
                defaultMedicine);
        assert(deletePrescriptionCommand.equals(deletePrescriptionCommand));
        assert(deletePrescriptionCommand.equals(deletePrescriptionCommandCopy));
    }

    @Test
    public void equals_differentContent_failure() {
        DeletePrescriptionCommand deletePrescriptionCommand = new DeletePrescriptionCommand(Index.fromOneBased(1),
                defaultMedicine);
        DeletePrescriptionCommand deletePrescriptionCommandNotSame = new DeletePrescriptionCommand(
                Index.fromOneBased(1),
                "notMedicine");
        assertFalse(deletePrescriptionCommand.equals(deletePrescriptionCommandNotSame));
    }

    @Test
    public void equals_null_failure() {
        DeletePrescriptionCommand deletePrescriptionCommand = new DeletePrescriptionCommand(Index.fromOneBased(1),
                defaultMedicine);
        assertFalse(deletePrescriptionCommand.equals(null));
    }

}
