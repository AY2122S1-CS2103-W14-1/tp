package seedu.docit.logic.commands.prescription;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.docit.commons.core.Messages;
import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.AppointmentCommand;
import seedu.docit.logic.commands.CommandResult;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.logic.parser.CliSyntax;
import seedu.docit.model.Model;
import seedu.docit.model.appointment.Appointment;


public class ListPrescriptionsCommand extends AppointmentCommand {
    public static final String COMMAND_WORD = "pl";

    public static final String MESSAGE_USAGE = "apmt "
            + COMMAND_WORD + ": List all prescriptions of an appointment "
            + "Parameters: \n"
            + CliSyntax.PREFIX_INDEX + "ID OF APPOINTMENT \n"
            + "Example: apmt " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_INDEX + "1 ";

    public static final String MESSAGE_SUCCESS = "Listed all prescriptions of appointment";

    private final Index targetAppointmentIndex;

    public ListPrescriptionsCommand(Index targetAppointmentIndex) {
        this.targetAppointmentIndex = targetAppointmentIndex;
    }

    @Override public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();
        if (targetAppointmentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToTarget = lastShownList.get(targetAppointmentIndex.getZeroBased());

        return new CommandResult(MESSAGE_SUCCESS + appointmentToTarget.getPrescriptions());
    }
}
