package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Patient;
import seedu.address.model.util.SampleDataUtil;

/**
 * Adds an appointment to the appointment book.
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE =
        "appt " + COMMAND_WORD + ": Adds an appointment to the appointment book. " + "Parameters: " + PREFIX_NAME
            + "ID " + PREFIX_DATETIME + "DATETIME \n" + "Example: appt " + COMMAND_WORD + " " + PREFIX_NAME + "1 "
            + PREFIX_DATETIME + "2021-12-31 1600";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT =
        "This appointment already exists in the appointment book";

    private final Index targetPatientIndex;
    private final String datetime;
    private Appointment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Appointment}
     *
     * @param targetPatientIndex patient index to make appointment
     * @param datetime           date and time of appointment
     */
    public AddAppointmentCommand(Index targetPatientIndex, String datetime) {
        requireNonNull(targetPatientIndex, datetime);
        this.targetPatientIndex = targetPatientIndex;
        this.datetime = datetime;
    }

    @Override public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (targetPatientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToMakeAppointment = lastShownList.get(targetPatientIndex.getZeroBased());

        toAdd = new Appointment(SampleDataUtil.getSamplePersons()[0], datetime);

        if (model.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddAppointmentCommand // instanceof handles nulls
            && targetPatientIndex.equals(((AddAppointmentCommand) other).targetPatientIndex) && datetime.equals(
            ((AddAppointmentCommand) other).datetime));
    }
}
