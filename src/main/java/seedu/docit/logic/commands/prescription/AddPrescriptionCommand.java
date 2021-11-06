package seedu.docit.logic.commands.prescription;

import static java.util.Objects.requireNonNull;

import java.util.List;
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
import seedu.docit.model.prescription.Prescription;

/**
 * Adds a prescription to an appointment.
 */
public class AddPrescriptionCommand extends AppointmentCommand {
    public static final String COMMAND_WORD = "pa";

    public static final String MESSAGE_USAGE =
            "apmt "
                    + COMMAND_WORD + ": Adds a prescription to an appointment. \n"
                    + "Parameters: "
                    + "APPOINTMENT_INDEX "
                    + CliSyntax.PREFIX_NAME + "MEDICINE "
                    + CliSyntax.PREFIX_VOLUME + "VOLUME "
                    + CliSyntax.PREFIX_DURATION + "DURATION \n"
                    + "Example: apmt " + COMMAND_WORD + " "
                    + "1 "
                    + CliSyntax.PREFIX_NAME + "Penicillin "
                    + CliSyntax.PREFIX_VOLUME + "400 ml "
                    + CliSyntax.PREFIX_DURATION + "2 times a week ";

    public static final String MESSAGE_SUCCESS = "New prescription added: \nMedicine: %1$s\n"
            + "Volume: %2$s\nDuration: %3$s";
    public static final String MESSAGE_DUPLICATE_MEDICINE =
            "This medicine already exists in the prescription for this appointment";
    public static final String MESSAGE_FIELD_TOO_LONG =
            "Medicine name can only be %1$s characters long. \nVolume field can only be %2$s characters long. "
                    + "\nDuration field can only be %3$s characters long.";
    private static Logger logger = Logger.getLogger("AddPrescriptionCommand");

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
        this.volume = volume.toLowerCase();
        this.medicine = medicine.toLowerCase();
        this.duration = duration.toLowerCase();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.log(Level.INFO, "going to start adding prescription");
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (targetAppointmentIndex.getZeroBased() >= lastShownList.size()) {
            logger.log(Level.WARNING, "prescription adding error, "
                    + Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToMakePrescription = lastShownList.get(targetAppointmentIndex.getZeroBased());

        Prescription prescriptionToAdd = new Prescription(medicine, volume, duration);

        if (appointmentToMakePrescription.containsPrescription(prescriptionToAdd)) {
            logger.log(Level.WARNING, "prescription adding error, "
                + MESSAGE_DUPLICATE_MEDICINE);
            throw new CommandException(MESSAGE_DUPLICATE_MEDICINE);
        }

        if (volume.length() > Prescription.VOLUME_CHAR_LENGTH_LIMIT
                || medicine.length() > Prescription.MEDICINE_CHAR_LENGTH_LIMIT
                || duration.length() > Prescription.DURATION_CHAR_LENGTH_LIMIT) {
            logger.log(Level.WARNING, String.format(MESSAGE_FIELD_TOO_LONG,
                    Prescription.MEDICINE_CHAR_LENGTH_LIMIT,
                    Prescription.VOLUME_CHAR_LENGTH_LIMIT,
                    Prescription.DURATION_CHAR_LENGTH_LIMIT
            ));
            throw new CommandException(String.format(MESSAGE_FIELD_TOO_LONG,
                    Prescription.MEDICINE_CHAR_LENGTH_LIMIT,
                    Prescription.VOLUME_CHAR_LENGTH_LIMIT,
                    Prescription.DURATION_CHAR_LENGTH_LIMIT
            ));
        }

        model.addPrescription(appointmentToMakePrescription, prescriptionToAdd);
        model.updateFilteredAppointmentList(Model.PREDICATE_SHOW_ALL_APPOINTMENTS);
        logger.log(Level.INFO, "prescription adding success");
        return new CommandResult(String.format(MESSAGE_SUCCESS, medicine, volume, duration));
    }
}
