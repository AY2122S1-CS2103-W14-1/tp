package seedu.address.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Patient;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Patient> getPersonList();

    /**
     * Returns patient at index position or null if not exist.
     */
    Patient getPatientOfIndex(Index index);

    /**
     * Returns index of patient in the list.
     */
    Index getIndexOfPatient(Patient patient);

}
