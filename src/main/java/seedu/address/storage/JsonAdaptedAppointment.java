package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String patientUuid;
    private final String datetime;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("patientId") String patientUuid,
        @JsonProperty("datetime") String datetime) {
        this.patientUuid = patientUuid;
        this.datetime = datetime;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        patientUuid = Integer.toString(source.getPatientUuid());
        datetime = source.getDatetime();
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (patientUuid == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Patient ID"));
        }

        int modelPatientId;
        try {
            modelPatientId = Integer.parseInt(patientUuid);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Patient ID must be an integer.");
        }

        if (datetime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Datetime"));
        }
        return new Appointment(modelPatientId, datetime);
    }

}
