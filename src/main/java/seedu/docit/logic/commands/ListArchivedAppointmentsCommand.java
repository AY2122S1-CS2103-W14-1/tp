package seedu.docit.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.docit.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import seedu.docit.model.Model;

/**
 * Lists all archived appointments in the address book to the user.
 */
public class ListArchivedAppointmentsCommand extends AppointmentCommand {

    public static final String COMMAND_WORD = "alist";

    public static final String MESSAGE_SUCCESS = "Listed all archived appointments";


    @Override public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
