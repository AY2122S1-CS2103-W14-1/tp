package seedu.docit.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import seedu.docit.commons.core.Messages;
import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.logic.parser.CliSyntax;
import seedu.docit.model.Model;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.patient.Patient;

/**
 * Adds an appointment to the appointment book.
 */
public class AddAppointmentCommand extends AppointmentCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE =
        "apmt " + COMMAND_WORD + ": Adds an appointment to the appointment book. "
            + "Parameters: " + CliSyntax.PREFIX_INDEX + "INDEX "
            + CliSyntax.PREFIX_DATETIME + "DATETIME \n"
            + "Example: apmt " + COMMAND_WORD + " " + CliSyntax.PREFIX_INDEX + "1 "
            + CliSyntax.PREFIX_DATETIME + "2021-12-31 1600";

    public static final String MESSAGE_SUCCESS = "New appointment added: \n%1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT =
        "This appointment already exists in the appointment book/archives";

    private final Index targetPatientIndex;
    private final LocalDateTime datetime;
    private Appointment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Appointment}
     * @param targetPatientIndex patient index to make appointment
     * @param datetime date and time of appointment
     */
    public AddAppointmentCommand(Index targetPatientIndex, LocalDateTime datetime) {
        requireNonNull(targetPatientIndex);
        this.targetPatientIndex = targetPatientIndex;
        this.datetime = datetime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (targetPatientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToMakeAppointment = lastShownList.get(targetPatientIndex.getZeroBased());

        toAdd = new Appointment(patientToMakeAppointment, datetime);

        if (model.hasAppointment(toAdd) || model.hasAppointmentInArchives(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.addAppointment(toAdd);
        model.archivePastAppointments();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddAppointmentCommand // instanceof handles nulls
            && targetPatientIndex.equals(((AddAppointmentCommand) other).targetPatientIndex)
            && datetime.equals(((AddAppointmentCommand) other).datetime));
    }
}
