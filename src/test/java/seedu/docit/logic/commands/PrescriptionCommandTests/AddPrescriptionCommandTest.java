package seedu.docit.logic.commands.PrescriptionCommandTests;

import static seedu.docit.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.AddAppointmentCommand;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.logic.commands.prescription.AddPrescriptionCommand;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.prescription.Prescription;
import seedu.docit.model.util.SampleDataUtil;
import seedu.docit.testutil.stubs.ModelStub;
import seedu.docit.testutil.stubs.ModelStubWithAppointment;

import java.time.LocalDateTime;


public class AddPrescriptionCommandTest {
    private final Index defaultPatientIndex = Index.fromOneBased(1);
    private final LocalDateTime defaultDateTime = LocalDateTime.of(2020, 12, 31, 12, 0);
    private Appointment defaultAppointment = new Appointment(SampleDataUtil.getSamplePatients()[0], defaultDateTime);
    private ModelStub modelStub = new ModelStubWithAppointment(defaultAppointment);

    @Test
    public void constructor_nullPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddPrescriptionCommand(null, null, null, null));
    }

    @Test
    public void execute_duplicatePrescription_throwsCommandException() {
        String defaultMedicine = "Penicillin";
        String defaultVolume = "400 ml";
        String defaultDuration = "3 times a day";
        Prescription validPrescription =
                new Prescription(defaultMedicine, defaultVolume, defaultDuration);
        AddPrescriptionCommand addPrescriptionCommand =
                new AddPrescriptionCommand(Index.fromOneBased(1), defaultMedicine, defaultVolume, defaultDuration);

        defaultAppointment.addPrescription(validPrescription);

        assertThrows(CommandException.class, AddPrescriptionCommand.MESSAGE_DUPLICATE_MEDICINE, () ->
                addPrescriptionCommand.execute(modelStub));

    }
}
