package seedu.address.logic.commands.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.prescription.exceptions.MedicineNotFoundException;



public class DeletePrescriptionCommand extends Command {
    public static final String COMMAND_WORD = "pd";

    public static final String MESSAGE_USAGE = "appt "
            + COMMAND_WORD + ": Deletes a prescription from an appointment based "
            + "on appointment index and medicine name. \n"
            + "Parameters: \n"
            + PREFIX_APPOINTMENT_INDEX + "ID OF APPOINTMENT \n"
            + PREFIX_NAME + "MEDICINE \n"
            + "Example: appt " + COMMAND_WORD + " "
            + PREFIX_APPOINTMENT_INDEX + "1 "
            + PREFIX_NAME + "Penicillin ";

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
        this.targetMedicineName = targetMedicineName;
    }

    @Override public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (targetAppointmentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToTarget = lastShownList.get(targetAppointmentIndex.getZeroBased());
        try {
            appointmentToTarget.removePrescription(targetMedicineName);
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
