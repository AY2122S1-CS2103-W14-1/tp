package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.prescription.AddPrescriptionCommand;
import seedu.address.model.person.Patient;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.stubs.ModelStubAcceptingPatientAdded;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

public class AddPrescriptionCommandTest {
    @Test
    public void constructor_nullPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddPrescriptionCommand(null, null, null, null));
    }

}
