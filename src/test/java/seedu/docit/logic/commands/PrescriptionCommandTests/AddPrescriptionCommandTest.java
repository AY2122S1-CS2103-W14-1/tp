package seedu.docit.logic.commands.PrescriptionCommandTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.docit.logic.commands.prescription.AddPrescriptionCommand.MESSAGE_SUCCESS;
import static seedu.docit.testutil.Assert.assertThrows;
import static seedu.docit.testutil.TypicalAppointments.getTypicalAppointmentList;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

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

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalAppointmentList(),
            new ArchivedAppointmentBook(), new UserPrefs());
    private final Appointment defaultAppointment = model.getAppointmentBook().getAppointmentList().get(0);

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
                defaultMedicine, defaultVolume, defaultDuration));

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
                new AddPrescriptionCommand(Index.fromOneBased(maxSize + 1), defaultMedicine, defaultVolume, defaultDuration);

        assertThrows(CommandException.class, MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX, () ->
                invalidAddPrescriptionCommand.execute(model));

    }
}
