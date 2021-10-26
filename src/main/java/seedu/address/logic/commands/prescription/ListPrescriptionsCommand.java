package seedu.address.logic.commands.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PRESCRIPTIONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AppointmentCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;



public class ListPrescriptionsCommand extends AppointmentCommand {
    public static final String COMMAND_WORD = "pl";

    public static final String MESSAGE_USAGE = "apmt "
            + COMMAND_WORD + ": List all prescriptions of an appointment "
            + "Parameters: \n"
            + PREFIX_INDEX + "ID OF APPOINTMENT \n"
            + "Example: appt " + COMMAND_WORD + " "
            + PREFIX_INDEX + "1 ";

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

        appointmentToTarget.updateFilteredPrescriptions(PREDICATE_SHOW_ALL_PRESCRIPTIONS);

        return new CommandResult(MESSAGE_SUCCESS + appointmentToTarget.getFilteredPrescriptions());
    }
}
