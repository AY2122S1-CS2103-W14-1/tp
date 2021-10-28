package seedu.docit.logic.commands;

import seedu.docit.model.Model;

/**
 * Terminates the program.
 */
public class CancelCommand extends BasicCommand {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_OP_CANCELED = "The operation has been canceled. No changes were made.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_OP_CANCELED, false, false);
    }
}
