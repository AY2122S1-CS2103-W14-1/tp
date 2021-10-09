package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.stubs.ModelStub;
import seedu.address.testutil.stubs.ModelStubAcceptingAppointmentAdded;
import seedu.address.testutil.stubs.ModelStubWithAppointment;


public class AddAppointmentCommandTest {
    private final int defaultPatientId = 0;
    private final String defaultDateTime = "2021-12-31 1600";
    private Appointment defaultAppointment = new Appointment(defaultPatientId, defaultDateTime);

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null));
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = defaultAppointment;

        CommandResult commandResult = new AddAppointmentCommand(defaultAppointment).execute(modelStub);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentsAdded);
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        Appointment validAppointment = defaultAppointment;
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(validAppointment);
        ModelStub modelStub = new ModelStubWithAppointment(validAppointment);

        assertThrows(CommandException.class, AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT, () ->
                addAppointmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment appointment1 = defaultAppointment;
        Appointment appointment2 = new Appointment(1, "2022-12-31 1600");
        AddAppointmentCommand addAppointment1 = new AddAppointmentCommand(appointment1);
        AddAppointmentCommand addAppointment2 = new AddAppointmentCommand(appointment2);

        // same object -> returns true
        assertTrue(addAppointment1.equals(addAppointment1));

        // same values -> returns true
        AddAppointmentCommand addAppointmentCommandCopy = new AddAppointmentCommand(defaultAppointment);
        assertTrue(addAppointment1.equals(addAppointmentCommandCopy));


        // different types -> returns false
        assertFalse(addAppointment1.equals(1));

        // null -> returns false
        assertFalse(addAppointment1.equals(null));

        // different person -> returns false
        assertFalse(addAppointment1.equals(addAppointment2));
    }



}
