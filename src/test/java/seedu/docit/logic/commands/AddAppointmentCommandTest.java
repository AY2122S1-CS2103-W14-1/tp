package seedu.docit.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.docit.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.util.SampleDataUtil;
import seedu.docit.testutil.PatientBuilder;
import seedu.docit.testutil.stubs.ModelStub;
import seedu.docit.testutil.stubs.ModelStubAcceptingAppointmentAdded;
import seedu.docit.testutil.stubs.ModelStubWithAppointment;


public class AddAppointmentCommandTest {
    private final Index defaultPatientIndex = Index.fromOneBased(1);
    private final LocalDateTime defaultDateTime = LocalDateTime.of(2020, 12, 31, 12, 0);
    private Appointment defaultAppointment = new Appointment(new PatientBuilder().build(), defaultDateTime);

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null, null));
    }

    @Test
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
        Appointment appointment2 = new Appointment(SampleDataUtil.getSamplePatients()[0], defaultDateTime);
        AddAppointmentCommand addAppointment1 = new AddAppointmentCommand(Index.fromOneBased(1), defaultDateTime);
        AddAppointmentCommand addAppointment2 = new AddAppointmentCommand(Index.fromOneBased(2), defaultDateTime);

        // same object -> returns true
        assertTrue(addAppointment1.equals(addAppointment1));

        // same values -> returns true
        AddAppointmentCommand addAppointmentCommandCopy =
            new AddAppointmentCommand(Index.fromOneBased(1), defaultDateTime);
        assertTrue(addAppointment1.equals(addAppointmentCommandCopy));


        // different types -> returns false
        assertFalse(addAppointment1.equals(1));

        // null -> returns false
        assertFalse(addAppointment1.equals(null));

        // different patient -> returns false
        assertFalse(addAppointment1.equals(addAppointment2));
    }



}
