package seedu.docit.testutil.stubs;

import static java.util.Objects.requireNonNull;

import seedu.docit.model.patient.Patient;


/**
 * A Model stub that contains a single patient.
 */
public class ModelStubWithPatient extends ModelStub {
    private final Patient patient;

    /**
     * Returns a model stub containing a patient
     * @param patient Patient to be contained in model stub
     */
    public ModelStubWithPatient(Patient patient) {
        requireNonNull(patient);
        this.patient = patient;
    }

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return this.patient.isSamePatient(patient);
    }
}
