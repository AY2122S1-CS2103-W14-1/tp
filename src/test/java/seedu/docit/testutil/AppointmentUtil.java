package seedu.docit.testutil;

import static seedu.docit.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_INDEX;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.AddAppointmentCommand;
import seedu.docit.logic.commands.EditAppointmentCommand;
import seedu.docit.logic.parser.ParserUtil;
import seedu.docit.model.ReadOnlyAddressBook;
import seedu.docit.model.appointment.Appointment;

/**
 * A utility class for Appointment.
 */
public class AppointmentUtil {
    /**
     * Returns an add command string for adding the {@code appointment}.
     */
    public static String getAddCommand(Appointment appointment, ReadOnlyAddressBook addressBook) {
        return AddAppointmentCommand.COMMAND_WORD + " " + getAppointmentDetails(appointment, addressBook);
    }

    /**
     * Returns the part of command string for the given {@code appointment}'s details.
     */
    public static String getAppointmentDetails(Appointment appointment, ReadOnlyAddressBook addressBook) {
        // Get index of appointment's patient in address book
        Index patientIndex = addressBook.getIndexOfPatient(appointment.getPatient());
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_INDEX).append(patientIndex.getOneBased()).append(" ");
        sb.append(PREFIX_DATETIME).append(appointment.getInputFormattedDatetimeString()).append(" ");
        return sb.toString();
    }


    /**
     * Returns the part of command string for the given {@code EditAppointmentDescriptor}'s details.
     */
    public static String getEditAppointmentDescriptorDetails(EditAppointmentCommand.EditAppointmentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getPatientIndex().ifPresent(
            patientIndex -> sb.append(PREFIX_INDEX).append(patientIndex.getOneBased()).append(" "));
        descriptor.getDatetime().ifPresent(datetime -> sb.append(PREFIX_DATETIME).append(datetime.format(ParserUtil.INPUT_DATE_TIME_FORMATTER)).append(" "));
        return sb.toString();
    }

}
