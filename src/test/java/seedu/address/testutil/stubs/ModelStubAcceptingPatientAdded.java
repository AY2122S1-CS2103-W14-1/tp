package seedu.address.testutil.stubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Patient;

/**
 * A Model stub that always accept the person being added.
 */
public class ModelStubAcceptingPatientAdded extends ModelStub {
    public final ArrayList<Patient> personsAdded = new ArrayList<>();

    @Override
    public boolean hasPatient(Patient person) {
        requireNonNull(person);
        return personsAdded.stream().anyMatch(person::isSamePatient);
    }

    @Override
    public void addPatient(Patient person) {
        requireNonNull(person);
        personsAdded.add(person);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
