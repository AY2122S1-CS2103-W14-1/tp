package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Patient;

public class AddMedicalEntryCommand extends PatientCommand {
    public static final String COMMAND_WORD = "ma";

    public static final String MESSAGE_USAGE = "pt " + COMMAND_WORD + ": Adds a medical entry to the patient. \n"
        + "Parameters: INDEX (must be a positive integer) "
        + PREFIX_MEDICAL + "MEDICAL HISTORY\n"
        + "Example: " + "pt " + COMMAND_WORD + " "
        + PREFIX_MEDICAL + "diabetes";

    public static final String MESSAGE_SUCCESS = "Updated: ";

    private final Index index;
    private final MedicalHistory medicalHistory;

    /**
     * Creates a MedicalEntry to add onto the MedicalHistory of the specified {@code Patient}
     * @param i index of the patinet specified.
     * @param m medical history for the entry to be added to.
     */
    public AddMedicalEntryCommand(Index i, MedicalHistory m) {
        requireNonNull(i);
        requireNonNull(m);
        this.index = i;
        this.medicalHistory = m;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        Patient editedPatient = patientToEdit.addMedicalHistory(this.medicalHistory);

        model.setPatient(patientToEdit, editedPatient);
        model.updateAppointmentBook(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedPatient));
    }
}
