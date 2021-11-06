package seedu.docit.testutil;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.patient.Patient;
import seedu.docit.model.prescription.Prescription;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final Patient DEFAULT_PATIENT = new PatientBuilder().build();
    public static final LocalDateTime DEFAULT_DATETIME = LocalDateTime.of(2021, 12, 31, 18, 0);
    public static final Prescription DEFAULT_PRESCRIPTION = new Prescription("sulphonylureas", "1 tablet", "daily");

    private Patient patient;
    private LocalDateTime datetime;
    private Set<Prescription> prescriptions = new HashSet<>();

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        patient = DEFAULT_PATIENT;
        datetime = DEFAULT_DATETIME;
        prescriptions.add(DEFAULT_PRESCRIPTION);
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        patient = appointmentToCopy.getPatient();
        datetime = appointmentToCopy.getDatetime();
        prescriptions = appointmentToCopy.getPrescriptions();
    }

    /**
     * Sets the {@code Patient} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    /**
     * Sets the {@code LocalDateTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
        return this;
    }

    /**
     * Sets the {@code Prescription} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPrescription(Prescription prescription) {
        this.prescriptions = new HashSet<>();
        this.prescriptions.add(prescription);
        return this;
    }

    /**
     * Sets the {@code Prescription} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPrescription(String medicine, String volume, String duration) {
        this.prescriptions = new HashSet<>();
        this.prescriptions.add(new Prescription(medicine, volume, duration));
        return this;
    }

    public Appointment build() {
        return new Appointment(patient, datetime, prescriptions);
    }

}
