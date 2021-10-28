package seedu.docit.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.docit.commons.core.Messages;
import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.model.Model;
import seedu.docit.model.appointment.Appointment;

/**
 * Archives an appointment identified using it's displayed index from the appointment book.
 */
public class ArchiveAppointmentCommand extends AppointmentCommand {

    public static final String COMMAND_WORD = "archive";

    public static final String MESSAGE_USAGE = "apmt " + COMMAND_WORD
            + ": Archives the appointment identified by the index number used in the displayed appointment list.\n"
            + "Parameters: INDEX (must be a positive integer)\n" + "Example: apmt " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ARCHIVE_APPOINTMENT_SUCCESS = "Archived Appointment: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT =
            "This appointment already exists in the archives. Removing appointment.";

    private final Index targetIndex;

    public ArchiveAppointmentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToArchive = lastShownList.get(targetIndex.getZeroBased());

        if (model.hasAppointmentInArchives(appointmentToArchive)) {
            model.deleteAppointment(appointmentToArchive);
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }
        model.archiveAppointment(appointmentToArchive);
        return new CommandResult(String.format(MESSAGE_ARCHIVE_APPOINTMENT_SUCCESS, appointmentToArchive));
    }

    @Override public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchiveAppointmentCommand // instanceof handles nulls
                && targetIndex.equals(((ArchiveAppointmentCommand) other).targetIndex)); // state check
    }
}
