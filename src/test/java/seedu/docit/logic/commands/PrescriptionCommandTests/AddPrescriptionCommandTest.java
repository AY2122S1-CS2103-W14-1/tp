package seedu.docit.logic.commands.PrescriptionCommandTests;

import static seedu.docit.logic.commands.prescription.AddPrescriptionCommand.MESSAGE_SUCCESS;
import static seedu.docit.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import seedu.docit.model.util.SampleDataUtil;
import seedu.docit.testutil.stubs.ModelStub;
import seedu.docit.testutil.stubs.ModelStubWithAppointment;

import java.time.LocalDateTime;


public class AddPrescriptionCommandTest {
    private static final String defaultMedicine = "Penicillin";
    private static final String defaultVolume = "400 ml";
    private static final String defaultDuration = "3 times a day";
    private static final Prescription validPrescription =
            new Prescription(defaultMedicine, defaultVolume, defaultDuration);

    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalAppointmentList(),
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
                new AddPrescriptionCommand(Index.fromOneBased(1), defaultMedicine, defaultVolume, defaultDuration);

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
    public void execute_emptyMedicineName_throwsCommandException() {
        AddPrescriptionCommand nullMedicinePrescription =
                new AddPrescriptionCommand(Index.fromOneBased(1), "", defaultVolume, defaultDuration);

        assertThrows(CommandException.class, AddPrescriptionCommand.MESSAGE_DUPLICATE_MEDICINE, () ->
                nullMedicinePrescription.execute(model));

    }
}
