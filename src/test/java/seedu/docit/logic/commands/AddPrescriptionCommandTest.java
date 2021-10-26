package seedu.docit.logic.commands;

import static seedu.docit.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.docit.logic.commands.prescription.AddPrescriptionCommand;


public class AddPrescriptionCommandTest {
    @Test
    public void constructor_nullPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddPrescriptionCommand(null, null, null, null));
    }

}
