package seedu.docit.logic.commands.prescription;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.docit.commons.core.Messages;
import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.AppointmentCommand;
import seedu.docit.logic.commands.CommandResult;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.logic.parser.CliSyntax;
import seedu.docit.model.Model;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.prescription.Prescription;

/**
 * Adds a prescription to an appointment.
 */
public class AddPrescriptionCommand extends AppointmentCommand {
    public static final String COMMAND_WORD = "pa";

    public static final String MESSAGE_USAGE =
            "apmt "
                    + COMMAND_WORD + ": Adds a prescription to an appointment. "
                    + "Parameters: \n"
                    + CliSyntax.PREFIX_INDEX + "ID OF APPOINTMENT \n"
                    + CliSyntax.PREFIX_NAME + "MEDICINE \n"
                    + CliSyntax.PREFIX_VOLUME + "VOLUME \n"
                    + CliSyntax.PREFIX_DURATION + "DURATION \n"
                    + "Example: apmt " + COMMAND_WORD + " "
                    + CliSyntax.PREFIX_INDEX + "1 "
                    + CliSyntax.PREFIX_NAME + "Penicillin "
                    + CliSyntax.PREFIX_VOLUME + "400 ml "
                    + CliSyntax.PREFIX_DURATION + "2 times a week ";

    public static final String MESSAGE_SUCCESS = "New prescription added";
    public static final String MESSAGE_DUPLICATE_MEDICINE =
            "This medicine already exists in the prescription for this appointment";

    private final Index targetAppointmentIndex;
    private String medicine;
    private String volume;
    private String duration;

    /**
     * Creates an AddPrescriptionCommand to add the specified {@code Prescription}
     * @param targetAppointmentIndex appointment index to make prescription
     */
    public AddPrescriptionCommand(Index targetAppointmentIndex, String medicine, String volume, String duration) {
        requireNonNull(targetAppointmentIndex);
        requireNonNull(medicine);
        requireNonNull(volume);
        requireNonNull(duration);
        this.targetAppointmentIndex = targetAppointmentIndex;
        this.volume = volume;
        this.medicine = medicine;
        this.duration = duration;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (targetAppointmentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToMakePrescription = lastShownList.get(targetAppointmentIndex.getZeroBased());
        Prescription prescriptionToAdd = new Prescription(medicine, volume, duration, appointmentToMakePrescription);

        if (appointmentToMakePrescription.containsPrescription(prescriptionToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEDICINE);
        }

        model.addPrescription(appointmentToMakePrescription, prescriptionToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, prescriptionToAdd));
    }
}
