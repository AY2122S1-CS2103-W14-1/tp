package seedu.docit.testutil.stubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.docit.model.AddressBook;
import seedu.docit.model.ReadOnlyAddressBook;
import seedu.docit.model.patient.Patient;

/**
 * A Model stub that always accept the patient being added.
 */
public class ModelStubAcceptingPatientAdded extends ModelStub {
    public final ArrayList<Patient> patientsAdded = new ArrayList<>();

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patientsAdded.stream().anyMatch(patient::isSamePatient);
    }

    @Override
    public void addPatient(Patient patient) {
        requireNonNull(patient);
        patientsAdded.add(patient);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
