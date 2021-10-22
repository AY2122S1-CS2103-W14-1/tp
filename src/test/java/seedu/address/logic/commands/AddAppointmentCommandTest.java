package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.stubs.ModelStub;
import seedu.address.testutil.stubs.ModelStubAcceptingAppointmentAdded;
import seedu.address.testutil.stubs.ModelStubWithAppointment;


public class AddAppointmentCommandTest {
    private final Index defaultPatientIndex = Index.fromOneBased(1);
    private final String defaultDateTime = "2021-12-31 1600";
    private Appointment defaultAppointment = new Appointment(SampleDataUtil.getSamplePersons()[0], defaultDateTime);

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null, null));
    }

    @Test
    @Disabled
    // Add appointment command now requires to get Patient with Index
    // TODO: Need to write new test cases for using Index similar to Edit Command Tests
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = defaultAppointment;

        CommandResult commandResult = new AddAppointmentCommand(defaultPatientIndex,
                defaultDateTime).execute(modelStub);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentsAdded);
    }

    @Test
    @Disabled
    // TODO
    public void execute_duplicateAppointment_throwsCommandException() {
        Appointment validAppointment = defaultAppointment;
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(defaultPatientIndex, defaultDateTime);
        ModelStub modelStub = new ModelStubWithAppointment(validAppointment);

        assertThrows(CommandException.class, AddAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT, () ->
                addAppointmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment appointment1 = defaultAppointment;
        Appointment appointment2 = new Appointment(SampleDataUtil.getSamplePersons()[0], "2022-12-31 1600");
        AddAppointmentCommand addAppointment1 = new AddAppointmentCommand(Index.fromOneBased(1), "");
        AddAppointmentCommand addAppointment2 = new AddAppointmentCommand(Index.fromOneBased(2), "");

        // same object -> returns true
        assertTrue(addAppointment1.equals(addAppointment1));

        // same values -> returns true
        AddAppointmentCommand addAppointmentCommandCopy = new AddAppointmentCommand(Index.fromOneBased(1), "");
        assertTrue(addAppointment1.equals(addAppointmentCommandCopy));


        // different types -> returns false
        assertFalse(addAppointment1.equals(1));

        // null -> returns false
        assertFalse(addAppointment1.equals(null));

        // different person -> returns false
        assertFalse(addAppointment1.equals(addAppointment2));
    }



}
