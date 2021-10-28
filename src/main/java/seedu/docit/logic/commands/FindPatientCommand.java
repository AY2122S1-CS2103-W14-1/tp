package seedu.docit.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.docit.commons.core.Messages;
import seedu.docit.model.Model;
import seedu.docit.model.patient.PatientContainsKeywordsPredicate;

/**
 * Finds and lists all patients in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPatientCommand extends PatientCommand {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "pt " + COMMAND_WORD + ": Finds all patients whose names or "
            + "medical history contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: pt " + COMMAND_WORD + " alice bob charlie";

    private final PatientContainsKeywordsPredicate predicate;

    public FindPatientCommand(PatientContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, model.getFilteredPatientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPatientCommand // instanceof handles nulls
                && predicate.equals(((FindPatientCommand) other).predicate)); // state check
    }
}
