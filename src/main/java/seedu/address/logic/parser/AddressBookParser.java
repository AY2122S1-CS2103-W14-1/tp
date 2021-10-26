package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.ArchiveAppointmentCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.commands.EditAppointmentCommand;
import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAppointmentsCommand;
import seedu.address.logic.commands.ListPatientCommand;
import seedu.address.logic.commands.prescription.AddPrescriptionCommand;
import seedu.address.logic.commands.prescription.DeletePrescriptionCommand;
import seedu.address.logic.commands.prescription.ListPrescriptionsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.prescription.AddPrescriptionCommandParser;
import seedu.address.logic.parser.prescription.DeletePrescriptionCommandParser;
import seedu.address.logic.parser.prescription.ListPrescriptionsCommandParser;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern APPT_COMMAND_FORMAT = Pattern.compile("appt (?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        // Appointment Command Matching
        final Matcher apptMatcher = APPT_COMMAND_FORMAT.matcher(userInput.trim());
        if (apptMatcher.matches()) {
            final String commandWord = apptMatcher.group("commandWord");
            final String arguments = apptMatcher.group("arguments");
            return parseApptCommand(commandWord, arguments);
        }

        // Basic Command Matching
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        return parseBasicCommand(commandWord, arguments);
    }

    /**
     * Parses user input of basic command for execution.
     * @param commandWord command word
     * @param arguments arguments of command
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseBasicCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddPatientCommand.COMMAND_WORD:
            return new AddPatientCommandParser().parse(arguments);
        case EditPatientCommand.COMMAND_WORD:
            return new EditPatientCommandParser().parse(arguments);
        case DeletePatientCommand.COMMAND_WORD:
            return new DeletePatientCommandParser().parse(arguments);
        case FindPatientCommand.COMMAND_WORD:
            return new FindPatientCommandParser().parse(arguments);
        case ListPatientCommand.COMMAND_WORD:
            return new ListPatientCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input of appointment command for execution.
     * @param commandWord command word
     * @param arguments arguments of command
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseApptCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);
        case EditAppointmentCommand.COMMAND_WORD:
            return new EditAppointmentCommandParser().parse(arguments);
        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parse(arguments);
        case ArchiveAppointmentCommand.COMMAND_WORD:
            return new ArchiveAppointmentCommandParser().parse(arguments);
        case ListAppointmentsCommand.COMMAND_WORD:
            return new ListAppointmentsCommand();
        case AddPrescriptionCommand.COMMAND_WORD:
            return new AddPrescriptionCommandParser().parse(arguments);
        case DeletePrescriptionCommand.COMMAND_WORD:
            return new DeletePrescriptionCommandParser().parse(arguments);
        case ListPrescriptionsCommand.COMMAND_WORD:
            return new ListPrescriptionsCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
