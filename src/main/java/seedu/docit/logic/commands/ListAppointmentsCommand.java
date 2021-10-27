package seedu.docit.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.docit.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import seedu.docit.model.Model;

/**
 * Lists all appointments in the address book to the user.
 */
public class ListAppointmentsCommand extends AppointmentCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all appointments";


    @Override public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
