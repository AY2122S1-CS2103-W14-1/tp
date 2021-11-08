package seedu.docit.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.model.Model;
import seedu.docit.model.appointment.Appointment;

/**
 * Archives an appointment identified using it's displayed index from the appointment book.
 */
public class SortAppointmentsCommand extends AppointmentCommand {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = "apmt " + COMMAND_WORD
            + ": Sorts appointments based on their urgency and name.\n"
            + "Parameters: [parameter to sort by..]\n" + "Example: apmt " + COMMAND_WORD + ""; // TODO

    public static final String MESSAGE_SORT_APPOINTMENT_SUCCESS = "Sorted Appointments based on "
            + "today > ascending date > ascending name.";

    public SortAppointmentsCommand() {

    }

    @Override public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        model.sortAppointments();
        return new CommandResult(String.format(MESSAGE_SORT_APPOINTMENT_SUCCESS));
    }

    @Override public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortAppointmentsCommand);
    }
}
