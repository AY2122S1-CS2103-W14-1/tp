package seedu.docit.logic.commands.prescription;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            + "Parameters: "
            + "APPOINTMENT_INDEX "
            + CliSyntax.PREFIX_NAME + "MEDICINE \n"
            + "Example: apmt " + COMMAND_WORD + " "
            + "1 "
            + CliSyntax.PREFIX_NAME + "Penicillin ";

    public static final String MESSAGE_DELETE_PRESCRIPTION_SUCCESS = "Deleted prescription: \nMedicine: %1$s\n\n"
            + "from %2$s's appointment.";

    private static Logger logger = Logger.getLogger("DeletePrescriptionCommand");


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
        logger.log(Level.INFO, "going to start deleting prescription");
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (targetAppointmentIndex.getZeroBased() >= lastShownList.size()) {
            logger.log(Level.WARNING, "deleting prescription failed, "
                    + Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }
        assert (targetAppointmentIndex.getZeroBased() >= 0
                && targetAppointmentIndex.getZeroBased() < lastShownList.size());

        Appointment appointmentToTarget = lastShownList.get(targetAppointmentIndex.getZeroBased());
        try {
            model.deletePrescription(appointmentToTarget, targetMedicineName);
            logger.log(Level.INFO, "deleting prescription success");
            return new CommandResult(String.format(MESSAGE_DELETE_PRESCRIPTION_SUCCESS,
                    targetMedicineName, appointmentToTarget.getPatient().getName()));
        } catch (MedicineNotFoundException e) {
            logger.log(Level.WARNING, "deleting prescription failed, "
                    + e.getMessage());
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

}
