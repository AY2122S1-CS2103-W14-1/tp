package seedu.docit.testutil.stubs;

import static java.util.Objects.requireNonNull;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.docit.model.AddressBook;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.patient.Patient;
import seedu.docit.testutil.AddressBookBuilder;
import seedu.docit.testutil.PatientBuilder;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * A Model stub that contains a single patient.
 */
public class ModelStubWithAppointment extends ModelStub {
    private final Appointment appointment;
    private final Patient patient = new PatientBuilder().build();
    private final AddressBook addressbook = new AddressBookBuilder().withPatient(patient).build();
    private final FilteredList<Patient> filteredPatients = new FilteredList<Patient>(addressbook.getPatientList());

    /**
     * Returns a model stub that contains 1 appointment
     * @param appointment Appointment to be contained in the stub
     */
    public ModelStubWithAppointment(Appointment appointment) {
        requireNonNull(appointment);
        this.appointment = appointment;
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return this.appointment.isSameAppointment(appointment);
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

}
