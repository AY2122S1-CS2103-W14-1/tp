package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.model.appointment.Appointment;


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
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getAppointmentDetails(Appointment appointment) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + String.valueOf(appointment.getPatient()) + " ");
        sb.append(PREFIX_DATETIME + appointment.getDatetime() + " ");
        return sb.toString();
    }

}
