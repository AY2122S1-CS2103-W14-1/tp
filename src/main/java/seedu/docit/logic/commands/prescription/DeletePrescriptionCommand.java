package seedu.docit.logic.commands.prescription;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.docit.commons.core.Messages;
import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.AppointmentCommand;
import seedu.docit.logic.commands.CommandResult;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.logic.parser.CliSyntax;
import seedu.docit.model.Model;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.prescription.exceptions.MedicineNotFoundException;


public class DeletePrescriptionCommand extends AppointmentCommand {
    public static final String COMMAND_WORD = "pd";

    public static final String MESSAGE_USAGE = "apmt "
            + COMMAND_WORD + ": Deletes a prescription from an appointment based "
            + "on appointment index and medicine name. \n"
            + "Parameters: \n"
            + CliSyntax.PREFIX_INDEX + "ID OF APPOINTMENT \n"
            + CliSyntax.PREFIX_NAME + "MEDICINE \n"
            + "Example: apmt " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_INDEX + "1 "
            + CliSyntax.PREFIX_NAME + "Penicillin ";

    public static final String MESSAGE_DELETE_PRESCRIPTION_SUCCESS = "Deleted prescription";

    private final Index targetAppointmentIndex;
    private final String targetMedicineName;

    /**
     * Deletes a prescription from an appointment based on appointment index and medicine name
     * @param targetAppointmentIndex Index of targeted appointment
     * @param targetMedicineName Name of medicine associated with prescription to be deleted
     */
    public DeletePrescriptionCommand(Index targetAppointmentIndex, String targetMedicineName) {
        this.targetAppointmentIndex = targetAppointmentIndex;
        this.targetMedicineName = targetMedicineName.trim().toLowerCase();
    }

    @Override public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (targetAppointmentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToTarget = lastShownList.get(targetAppointmentIndex.getZeroBased());
        try {
            model.deletePrescription(appointmentToTarget, targetMedicineName);
            return new CommandResult(MESSAGE_DELETE_PRESCRIPTION_SUCCESS);
        } catch (MedicineNotFoundException e) {
            throw new CommandException(e.getMessage());
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeletePrescriptionCommand that = (DeletePrescriptionCommand) o;
        return Objects.equals(targetAppointmentIndex, that.targetAppointmentIndex)
                && Objects.equals(targetMedicineName, that.targetMedicineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetAppointmentIndex, targetMedicineName);
    }
}
