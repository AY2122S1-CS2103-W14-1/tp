package seedu.docit.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.docit.commons.core.Messages;
import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.model.Model;
import seedu.docit.model.appointment.Appointment;

/**
 * Deletes an appointment identified using it's displayed index from the appointment book.
 */
public class DeleteAppointmentCommand extends AppointmentCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the appointment identified by the index number used in the displayed appointment list.\n"
        + "Parameters: INDEX (must be a positive integer)\n" + "Example: appt " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment: %1$s";

    private final Index targetIndex;

    public DeleteAppointmentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteAppointment(appointmentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS, appointmentToDelete));
    }

    @Override public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteAppointmentCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteAppointmentCommand) other).targetIndex)); // state check
    }
}
