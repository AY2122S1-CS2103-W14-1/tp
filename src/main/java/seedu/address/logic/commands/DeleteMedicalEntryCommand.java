package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Patient;

public class DeleteMedicalEntryCommand extends PatientCommand {
    public static final String COMMAND_WORD = "md";

    public static final String MESSAGE_USAGE = "pt " + COMMAND_WORD + ": Deletes a medical entry to the patient. \n"
        + "Parameters: INDEX (must be a positive integer) "
        + PREFIX_INDEX + "INDEX OF MEDICAL ENTRY\n"
        + "Example: " + "pt " + COMMAND_WORD + "1 "
        + PREFIX_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "Updated: ";

    private final Index patientIndex;
    private final Index medicalIndex;

    /**
     * Constructor for the DeleteMedicalEntryCommand.
     * @param patientIndex index of the patient to delete a medical entry from.
     * @param medicalIndex index of the medical entry to be deleted.
     */
    public DeleteMedicalEntryCommand(Index patientIndex, Index medicalIndex) {
        requireNonNull(patientIndex);
        requireNonNull(medicalIndex);
        this.patientIndex = patientIndex;
        this.medicalIndex = medicalIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Patient> lastShownList = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(patientIndex.getZeroBased());
        Patient editedPatient = patientToEdit.deleteMedicalHistory(medicalIndex);

        model.setPatient(patientToEdit, editedPatient);
        model.updateAppointmentBook(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedPatient));
    }
}
