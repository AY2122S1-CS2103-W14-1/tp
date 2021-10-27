package seedu.docit.model;

import javafx.collections.ObservableList;
import seedu.docit.commons.core.index.Index;
import seedu.docit.model.patient.Patient;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the patient list. This list will not contain any duplicate patients.
     */
    ObservableList<Patient> getPatientList();

    /**
     * Returns patient at index position or null if not exist.
     */
    Patient getPatientOfIndex(Index index);

    /**
     * Returns index of patient in the list.
     */
    Index getIndexOfPatient(Patient patient);

}
