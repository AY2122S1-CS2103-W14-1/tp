package seedu.docit.testutil;

import static seedu.docit.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_INDEX;

import seedu.docit.logic.commands.AddAppointmentCommand;
import seedu.docit.model.appointment.Appointment;




/**
 * A utility class for Appointment.
 */
public class AppointmentUtil {

    /**
     * Returns an add command string for adding the {@code appointment}.
     */
    public static String getAddCommand(Appointment appointment) {
        return AddAppointmentCommand.COMMAND_WORD + " " + getAppointmentDetails(appointment);
    }

    /**
     * Returns the part of command string for the given {@code patient}'s details.
     */
    public static String getAppointmentDetails(Appointment appointment) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_INDEX + String.valueOf(1) + " "); // Changed patientId to arbitrary index
        sb.append(PREFIX_DATETIME + appointment.getInputFormattedDatetimeString() + " ");
        return sb.toString();
    }

}
