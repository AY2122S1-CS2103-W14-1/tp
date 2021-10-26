package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
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
    private final Index index;
    private final MedicalHistory medicalHistory;

    public static final String COMMAND_WORD = "mh-add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a medical entry to the patient. \n"
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_PHONE + "PHONE "
        + PREFIX_EMAIL + "EMAIL "
        + PREFIX_ADDRESS + "ADDRESS "
        + "[" + PREFIX_TAG + "TAG]... "
        + "[" + PREFIX_MEDICAL + "MEDICAL HISTORY]\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_PHONE + "98765432 "
        + PREFIX_EMAIL + "johnd@example.com "
        + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
        + PREFIX_TAG + "friends "
        + PREFIX_TAG + "owesMoney "
        + PREFIX_MEDICAL + "lovesick";

    public static final String MESSAGE_SUCCESS = "Entries of the patient's medical history now include: ";

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
        System.out.println(editedPatient.toString());

        model.setPatient(patientToEdit, editedPatient);
        model.updateAppointmentBook(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, this.medicalHistory));
    }
}
