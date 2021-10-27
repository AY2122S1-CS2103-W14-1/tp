package seedu.docit.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.docit.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.docit.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.docit.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import seedu.docit.commons.core.Messages;
import seedu.docit.commons.core.index.Index;
import seedu.docit.commons.util.CollectionUtil;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.logic.parser.CliSyntax;
import seedu.docit.model.Model;
import seedu.docit.model.ReadOnlyAddressBook;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.patient.Patient;

/**
 * Edits the details of an existing appointment in the appointment book.
 */
public class EditAppointmentCommand extends AppointmentCommand {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_INDEX + "PATIENT INDEX] \n"
            + "[" + PREFIX_DATETIME + "DATETIME] \n"
            + "Example: apmt " + COMMAND_WORD + " 1 "
            + PREFIX_INDEX + "2 "
            + PREFIX_DATETIME + "2012-12-31 1600";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT =
        "This appointment already exists in the appointment book.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param index of the appointment in the filtered appointment list to edit
     * @param editAppointmentDescriptor details to edit the appointment with
     */
    public EditAppointmentCommand(Index index, EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);

        this.index = index;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor,
            model.getAddressBook());

        if (!appointmentToEdit.isSameAppointment(editedAppointment) && model.hasAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredPatientList(Model.PREDICATE_SHOW_ALL_PATIENTS);
        model.updateFilteredAppointmentList(Model.PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor} and {@code addressBook}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
        EditAppointmentDescriptor editAppointmentDescriptor, ReadOnlyAddressBook addressBook)
        throws CommandException {
        assert appointmentToEdit != null;

        Patient updatedPatient = null;
        if (editAppointmentDescriptor.getPatientIndex().isPresent()) {
            Index updatedIndex = editAppointmentDescriptor.getPatientIndex().get();
            updatedPatient = addressBook.getPatientOfIndex(updatedIndex);
        } else {
            updatedPatient = appointmentToEdit.getPatient();
        }
        if (updatedPatient == null) {
            throw new CommandException("Patient that has the appointment does not exist.");
        }

        LocalDateTime updatedDatetime = editAppointmentDescriptor.getDatetime().orElse(appointmentToEdit.getDatetime());

        return new Appointment(updatedPatient, updatedDatetime);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        // state check
        EditAppointmentCommand e = (EditAppointmentCommand) other;
        return index.equals(e.index)
                && editAppointmentDescriptor.equals(e.editAppointmentDescriptor);
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the appointment.
     */
    public static class EditAppointmentDescriptor {
        private Index patientIndex;
        private LocalDateTime datetime;

        public EditAppointmentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setPatientIndex(toCopy.patientIndex);
            setDatetime(toCopy.datetime);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(patientIndex, datetime);
        }

        public void setPatientIndex(Index patientIndex) {
            this.patientIndex = patientIndex;
        }

        public Optional<Index> getPatientIndex() {
            return Optional.ofNullable(patientIndex);
        }

        public void setDatetime(LocalDateTime datetime) {
            this.datetime = datetime;
        }

        public Optional<LocalDateTime> getDatetime() {
            return Optional.ofNullable(datetime);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentDescriptor)) {
                return false;
            }

            // state check
            EditAppointmentDescriptor e = (EditAppointmentDescriptor) other;

            return getPatientIndex().equals(e.getPatientIndex())
                    && getDatetime().equals(e.getDatetime());
        }
    }
}
