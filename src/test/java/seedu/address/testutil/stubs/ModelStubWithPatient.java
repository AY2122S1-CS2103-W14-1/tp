package seedu.address.testutil.stubs;

import static java.util.Objects.requireNonNull;

import seedu.address.model.person.Patient;


/**
 * A Model stub that contains a single person.
 */
public class ModelStubWithPatient extends ModelStub {
    private final Patient patient;

    /**
     * Returns a model stub containing a person
     * @param patient Person to be contained in model stub
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
