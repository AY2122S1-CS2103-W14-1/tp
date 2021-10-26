package seedu.address.logic.commands.prescription;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.prescription.Prescription;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds a prescription to an appointment.
 */
public class AddPrescriptionCommand extends Command {
    public static final String COMMAND_WORD = "pa";

    public static final String MESSAGE_USAGE =
            "appt "
                    + COMMAND_WORD + ": Adds a prescription to an appointment. "
                    + "Parameters: \n"
                    + PREFIX_APPOINTMENT_INDEX + "ID OF APPOINTMENT \n"
                    + PREFIX_NAME + "MEDICINE \n"
                    + PREFIX_VOLUME + "VOLUME \n"
                    + PREFIX_DURATION + "DURATION \n"
                    + "Example: appt " + COMMAND_WORD + " "
                    + PREFIX_APPOINTMENT_INDEX + "1 "
                    + PREFIX_NAME + "Penicillin "
                    + PREFIX_VOLUME + "400 ml "
                    + PREFIX_DURATION + "2 times a week ";

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
        Prescription toAdd = new Prescription(medicine, volume, duration, appointmentToMakePrescription);

        if (appointmentToMakePrescription.containsPrescription(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEDICINE);
        }
        appointmentToMakePrescription.addPrescription(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
