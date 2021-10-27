package seedu.docit.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.docit.commons.exceptions.IllegalValueException;
import seedu.docit.model.AppointmentBook;
import seedu.docit.model.ReadOnlyAddressBook;
import seedu.docit.model.ReadOnlyAppointmentBook;
import seedu.docit.model.appointment.Appointment;

/**
 * An Immutable AppointmentBook that is serializable to JSON format.
 */
@JsonRootName(value = "appointmentBook")
public class JsonSerializableAppointmentBook {

    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointments list contains duplicate appointment(s).";

    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAppointmentBook} with the given appointments.
     */
    @JsonCreator
    public JsonSerializableAppointmentBook(
        @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {
        this.appointments.addAll(appointments);
    }

    /**
     * Converts a given {@code ReadOnlyAppointmentBook} into this class for Jackson use.
     *
     * @param source      future changes to this will not affect the created {@code JsonSerializableAppointmentBook}.
     * @param addressBook {@code AddressBook} that this appointment book references
     */
    public JsonSerializableAppointmentBook(ReadOnlyAppointmentBook source, ReadOnlyAddressBook addressBook) {
        appointments.addAll(source.getAppointmentList().stream().map(x -> new JsonAdaptedAppointment(x, addressBook))
            .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AppointmentBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AppointmentBook toModelType(ReadOnlyAddressBook addressBook) throws IllegalValueException {
        AppointmentBook appointmentBook = new AppointmentBook();
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType(addressBook);
            if (appointmentBook.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            appointmentBook.addAppointment(appointment);
        }
        return appointmentBook;
    }

}
