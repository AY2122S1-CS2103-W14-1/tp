package seedu.docit.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.docit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.docit.testutil.Assert.assertThrows;
import static seedu.docit.testutil.TypicalAppointments.APPOINTMENT_1;
import static seedu.docit.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.docit.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.AddAppointmentCommand;
import seedu.docit.logic.commands.AddPatientCommand;
import seedu.docit.logic.commands.ClearCommand;
import seedu.docit.logic.commands.DeleteAppointmentCommand;
import seedu.docit.logic.commands.DeletePatientCommand;
import seedu.docit.logic.commands.EditPatientCommand;
import seedu.docit.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.docit.logic.commands.ExitCommand;
import seedu.docit.logic.commands.FindPatientCommand;
import seedu.docit.logic.commands.HelpCommand;
import seedu.docit.logic.commands.ListAppointmentsCommand;
import seedu.docit.logic.commands.ListPatientCommand;
import seedu.docit.logic.parser.exceptions.ParseException;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.patient.Patient;
import seedu.docit.model.patient.PatientContainsKeywordsPredicate;
import seedu.docit.testutil.AppointmentUtil;
import seedu.docit.testutil.EditPatientDescriptorBuilder;
import seedu.docit.testutil.PatientBuilder;
import seedu.docit.testutil.PatientUtil;

public class AddressBookParserTest {

    public static final String PT_PREFIX = "pt ";
    public static final String APPT_PREFIX = "apmt ";
    public static final String BASIC_CMD_PREFIX = "doc ";
    private final AddressBookParser parser = new AddressBookParser();

    // Patient related section

    @Test
    public void parseCommand_addPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        AddPatientCommand command = (AddPatientCommand) parser.parseCommand(
                PT_PREFIX + PatientUtil.getAddCommand(patient));
        assertEquals(new AddPatientCommand(patient), command);
    }

    @Test
    public void parseCommand_deletePatient() throws Exception {
        DeletePatientCommand command = (DeletePatientCommand) parser.parseCommand(PT_PREFIX
                + DeletePatientCommand.COMMAND_WORD + " " + INDEX_FIRST_PATIENT.getOneBased());
        assertEquals(new DeletePatientCommand(INDEX_FIRST_PATIENT), command);
    }

    @Test
    public void parseCommand_editPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(patient).build();
        EditPatientCommand command = (EditPatientCommand) parser.parseCommand(PT_PREFIX
                + EditPatientCommand.COMMAND_WORD + " " + INDEX_FIRST_PATIENT.getOneBased()
                + " " + PatientUtil.getEditPatientDescriptorDetails(descriptor));
        assertEquals(new EditPatientCommand(INDEX_FIRST_PATIENT, descriptor), command);
    }

    @Test
    public void parseCommand_findPatient() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPatientCommand command = (FindPatientCommand) parser.parseCommand(PT_PREFIX
                + FindPatientCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindPatientCommand(new PatientContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_listPatient() throws Exception {
        assertTrue(parser.parseCommand(PT_PREFIX + ListPatientCommand.COMMAND_WORD) instanceof ListPatientCommand);
        assertTrue(parser.parseCommand(
                PT_PREFIX + ListPatientCommand.COMMAND_WORD + " 3") instanceof ListPatientCommand);
    }

    // Appointment related section

    @Test
    public void parseCommand_addAppointment() throws Exception {
        Appointment appointment = APPOINTMENT_1;
        AddAppointmentCommand command = (AddAppointmentCommand) parser.parseCommand(APPT_PREFIX
                + AppointmentUtil.getAddCommand(appointment));
        assertEquals(new AddAppointmentCommand(Index.fromOneBased(1), appointment.getDatetime()), command);
    }

    @Test
    public void parseCommand_deleteAppointment() throws Exception {
        DeleteAppointmentCommand command = (DeleteAppointmentCommand) parser.parseCommand(
                APPT_PREFIX + DeleteAppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_APPOINTMENT.getOneBased());
        assertEquals(new DeleteAppointmentCommand(INDEX_FIRST_APPOINTMENT), command);
    }

    @Test
    @Disabled("Not yet implemented")
    public void parseCommand_editAppointment() throws Exception {
        //TODO implement editAppointment test
    }

    @Test
    @Disabled("Not yet implemented")
    public void parseCommand_findAppointment() throws Exception {
        //TODO implement findAppointment test
    }

    @Test
    public void parseCommand_listAppointments() throws Exception {
        assertTrue(parser.parseCommand(APPT_PREFIX + ListAppointmentsCommand.COMMAND_WORD)
                instanceof ListAppointmentsCommand);
        assertTrue(parser.parseCommand(APPT_PREFIX + ListAppointmentsCommand.COMMAND_WORD + " 3")
                instanceof ListAppointmentsCommand);
    }

    // General commands

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(BASIC_CMD_PREFIX + ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(BASIC_CMD_PREFIX + ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(BASIC_CMD_PREFIX + ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(BASIC_CMD_PREFIX + ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(BASIC_CMD_PREFIX + HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(BASIC_CMD_PREFIX + HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    // Erroneous commands

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
