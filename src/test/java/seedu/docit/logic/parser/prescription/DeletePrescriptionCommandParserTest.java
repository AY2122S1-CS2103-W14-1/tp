package seedu.docit.logic.parser.prescription;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_APPOINTMENT_INDEX;
import static seedu.docit.logic.commands.CommandTestUtil.VALID_PRESCRIPTION_MEDICINE;
import static seedu.docit.logic.parser.AppointmentCommandParserTestUtil.assertParseFailure;
import static seedu.docit.logic.parser.AppointmentCommandParserTestUtil.assertParseSuccess;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.docit.logic.parser.prescription.DeletePrescriptionCommandParser.EMPTY_MEDICINE_FIELD_ERROR_MESSAGE;
import static seedu.docit.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.prescription.DeletePrescriptionCommand;
import seedu.docit.logic.parser.exceptions.ParseException;
import seedu.docit.model.ArchivedAppointmentBook;
import seedu.docit.model.Model;
import seedu.docit.model.ModelManager;
import seedu.docit.model.UserPrefs;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.prescription.Prescription;



public class DeletePrescriptionCommandParserTest {
    private static final String defaultMedicine = "Panadol";
    private static final String defaultVolume = "400 ml";
    private static final String defaultDuration = "3 times a day";
    private static final Prescription validPrescription =
            new Prescription(defaultMedicine, defaultVolume, defaultDuration);

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalAppointmentBook(),
            new ArchivedAppointmentBook(), new UserPrefs());
    private final Appointment defaultAppointment = model.getAppointmentBook().getAppointmentList().get(0);

    private final DeletePrescriptionCommandParser parser = new DeletePrescriptionCommandParser();

    @Test
    public void parseAppointmentCommand_allFieldsPresent_success() throws ParseException {
        assertParseSuccess(parser, VALID_APPOINTMENT_INDEX + " "
                + PREFIX_NAME + VALID_PRESCRIPTION_MEDICINE,
                new DeletePrescriptionCommand(Index.fromOneBased(Integer.parseInt(VALID_APPOINTMENT_INDEX)),
                        VALID_PRESCRIPTION_MEDICINE));
    }

    @Test
    public void parseAppointmentCommand_allFieldsBlank_failure() throws ParseException {
        assertParseFailure(parser, " "
                        + PREFIX_NAME,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePrescriptionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseAppointmentCommand_noIndex_failure() throws ParseException {
        assertParseFailure(parser, " "
                        + PREFIX_NAME + VALID_PRESCRIPTION_MEDICINE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePrescriptionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseAppointmentCommand_badIndex_failure() throws ParseException {
        assertParseFailure(parser, "notIndex "
                        + PREFIX_NAME + VALID_PRESCRIPTION_MEDICINE,
                "Index is not a non-zero unsigned integer.");
    }

    @Test
    public void parseAppointmentCommand_blankEntry_failure() throws ParseException {
        assertParseFailure(parser, VALID_APPOINTMENT_INDEX + " "
                        + PREFIX_NAME,
                EMPTY_MEDICINE_FIELD_ERROR_MESSAGE);
    }
}
