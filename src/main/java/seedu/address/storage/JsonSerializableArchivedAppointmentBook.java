package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ArchivedAppointmentBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.appointment.Appointment;

/**
 * An Immutable AppointmentBook that is serializable to JSON format.
 */
@JsonRootName(value = "archivedAppointmentBook")
class JsonSerializableArchivedAppointmentBook {

    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointments list contains duplicate appointment(s).";

    private final List<JsonAdaptedAppointment> archivedAppointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAppointmentBook} with the given appointments.
     */
    @JsonCreator
    public JsonSerializableArchivedAppointmentBook(
            @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {
        this.archivedAppointments.addAll(appointments);
    }

    /**
     * Converts a given {@code ReadOnlyAppointmentBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAppointmentBook}.
     */
    public JsonSerializableArchivedAppointmentBook(ReadOnlyAppointmentBook source, ReadOnlyAddressBook addressBook) {
        archivedAppointments.addAll(source.getAppointmentList().stream()
            .map(x -> new JsonAdaptedAppointment(x, addressBook))
            .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AppointmentBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ArchivedAppointmentBook toModelType(ReadOnlyAddressBook addressBook) throws IllegalValueException {
        ArchivedAppointmentBook appointmentBook = new ArchivedAppointmentBook();
        for (JsonAdaptedAppointment jsonAdaptedAppointment : archivedAppointments) {
            Appointment archivedAppointment = jsonAdaptedAppointment.toModelType(addressBook);
            if (appointmentBook.hasAppointment(archivedAppointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            appointmentBook.addAppointment(archivedAppointment);
        }
        return appointmentBook;
    }

}
