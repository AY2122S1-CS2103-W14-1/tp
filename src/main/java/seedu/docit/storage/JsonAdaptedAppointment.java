package seedu.docit.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.docit.commons.core.index.Index;
import seedu.docit.commons.exceptions.IllegalValueException;
import seedu.docit.logic.parser.ParserUtil;
import seedu.docit.logic.parser.exceptions.ParseException;
import seedu.docit.model.ReadOnlyAddressBook;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.patient.Patient;
import seedu.docit.model.prescription.Prescription;
import seedu.docit.model.prescription.UniquePrescriptionList;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");

    private final String patientIndex;
    private final String datetime;
    private final List<JsonAdaptedPrescription> prescriptionList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator public JsonAdaptedAppointment(@JsonProperty("patientId") String patientIndex,
        @JsonProperty("datetime") String datetime, @JsonProperty("UniquePrescriptionList")
                                                       List<JsonAdaptedPrescription> appointmentList) {
        this.patientIndex = patientIndex;
        this.datetime = datetime;
        if (appointmentList != null) {
            this.prescriptionList.addAll(appointmentList);
        }
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source, ReadOnlyAddressBook addressBook) {
        patientIndex = Integer.toString(addressBook.getIndexOfPatient(source.getPatient()).getZeroBased());
        datetime = source.getFormattedDatetimeString();
        prescriptionList.addAll(source.getPrescriptions()
                        .getPrescriptions().stream().map(JsonAdaptedPrescription::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType(ReadOnlyAddressBook addressBook) throws IllegalValueException {
        if (patientIndex == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Index.class.getSimpleName()));
        }

        Index modelPatientIndex;
        try {
            modelPatientIndex = Index.fromZeroBased(Integer.parseInt(patientIndex));
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Patient index must be an integer.");
        }

        Patient patientToAppointment = addressBook.getPatientOfIndex(modelPatientIndex);

        if (patientToAppointment == null) {
            throw new IllegalValueException("Patient that has appointment does not exist.");
        }

        if (datetime == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName()));
        }

        LocalDateTime localDateTime;
        try {
            localDateTime = ParserUtil.parseDateTime(datetime, DATE_TIME_FORMATTER);
        } catch (ParseException e) {
            throw new IllegalValueException(LocalDateTime.class.getSimpleName() + " is of incorrect format.");
        }

        if (prescriptionList == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Appointment List"));
        }

        UniquePrescriptionList newPrescriptionList = new UniquePrescriptionList();
        for (JsonAdaptedPrescription prescription : prescriptionList) {
            Prescription newPrescription = new Prescription(
                    prescription.getPrescriptionMedicine(),
                    prescription.getPrescriptionVolume(),
                    prescription.getPrescriptionDuration());
            newPrescriptionList.add(newPrescription);
        }

        return new Appointment(patientToAppointment, localDateTime, newPrescriptionList);
    }

}
