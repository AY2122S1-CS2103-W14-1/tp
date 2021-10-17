package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Patient;
import seedu.address.model.util.SampleDataUtil;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String patientIndex;
    private final String datetime;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator public JsonAdaptedAppointment(@JsonProperty("patientId") String patientIndex,
        @JsonProperty("datetime") String datetime) {
        this.patientIndex = patientIndex;
        this.datetime = datetime;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source, ReadOnlyAddressBook addressBook) {
        patientIndex = Integer.toString(addressBook.getIndexOfPatient(source.getPatient()).getZeroBased());
        datetime = source.getDatetime();
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType(ReadOnlyAddressBook addressBook) throws IllegalValueException {
        if (patientIndex == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Patient ID"));
        }

        Index modelPatientIndex;
        try {
            modelPatientIndex = Index.fromZeroBased(Integer.parseInt(patientIndex));
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Patient ID must be an integer.");
        }

        Patient patientToAppointment = addressBook.getPatientOfIndex(modelPatientIndex);

        if (patientToAppointment == null) {
            throw new IllegalValueException("Patient that has appointment does not exist.");
        }


        if (datetime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Datetime"));
        }
        return new Appointment(SampleDataUtil.getSamplePersons()[0], datetime);
    }

}
