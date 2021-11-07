package seedu.docit.testutil;

import java.time.LocalDateTime;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.docit.model.appointment.Appointment;

/**
 * A utility class to help with building EditAppointmentDescriptor objects.
 *
 */
public class EditAppointmentDescriptorBuilder {

    private EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAppointmentDescriptor} with fields containing {@code appt}'s details
     */
    public EditAppointmentDescriptorBuilder(Appointment appt) {
        descriptor = new EditAppointmentDescriptor();
        descriptor.setDatetime(appt.getDatetime());
    }

    /**
     * Sets the {@code patientIndex} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withPatientIndex(int patientIndex) {
        descriptor.setPatientIndex(Index.fromOneBased(patientIndex));
        return this;
    }

    /**
     * Sets the {@code idx} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDatetime(String datetime) {
        descriptor.setDatetime(LocalDateTime.parse(datetime));
        return this;
    }

    public EditAppointmentDescriptor build() {
        return descriptor;
    }
}
