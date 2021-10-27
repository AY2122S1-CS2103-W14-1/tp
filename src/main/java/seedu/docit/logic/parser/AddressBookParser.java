package seedu.docit.logic.parser;

import static seedu.docit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.docit.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.docit.logic.commands.Command;
import seedu.docit.logic.commands.HelpCommand;
import seedu.docit.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(doc) (?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern PTNT_COMMAND_FORMAT = Pattern.compile("(pt) (?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern APPT_COMMAND_FORMAT = Pattern.compile("(apmt) (?<commandWord>\\S+)(?<arguments>.*)");

    private final PatientBookParser patientParser = new PatientBookParser();
    private final AppointmentBookParser apptParser = new AppointmentBookParser();
    private final BasicAddressBookParser basicParser = new BasicAddressBookParser();

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        // Patient Command Matching
        final Matcher patientMatcher = PTNT_COMMAND_FORMAT.matcher(userInput.trim());
        final Matcher basicMatcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        final Matcher apptMatcher = APPT_COMMAND_FORMAT.matcher(userInput.trim());

        // empty inputs
        if (userInput.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        if (basicMatcher.matches()) {
            // Basic Command Matching
            final String commandWord = basicMatcher.group("commandWord");
            final String arguments = basicMatcher.group("arguments"); // ignore any arguments
            return basicParser.parseBasicCommand(commandWord);
        } else if (apptMatcher.matches()) {
            // Appointment Command Matching
            final String commandWord = apptMatcher.group("commandWord");
            final String arguments = apptMatcher.group("arguments");
            return apptParser.parseAppointmentCommand(commandWord, arguments);
        } else if (patientMatcher.matches()) {
            // Patient Command Matching
            final String commandWord = patientMatcher.group("commandWord");
            final String arguments = patientMatcher.group("arguments");
            return patientParser.parsePatientCommand(commandWord, arguments);
        } else {
            throw new ParseException(String.format(MESSAGE_UNKNOWN_COMMAND, HelpCommand.MESSAGE_USAGE));
        }
    }
}