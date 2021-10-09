package seedu.address.testutil.stubs;

import static java.util.Objects.requireNonNull;

import seedu.address.model.person.Person;

/**
 * A Model stub that contains a single person.
 */
public class ModelStubWithPerson extends ModelStub {
    private final Person person;

    /**
     * Returns a model stub containing a person
     * @param person Person to be contained in model stub
     */
    public ModelStubWithPerson(Person person) {
        requireNonNull(person);
        this.person = person;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return this.person.isSamePerson(person);
    }
}
