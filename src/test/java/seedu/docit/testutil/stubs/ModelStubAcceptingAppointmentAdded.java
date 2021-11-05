package seedu.docit.testutil.stubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.docit.model.AddressBook;
import seedu.docit.model.AppointmentBook;
import seedu.docit.model.ReadOnlyAppointmentBook;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.patient.Patient;
import seedu.docit.testutil.AddressBookBuilder;
import seedu.docit.testutil.PatientBuilder;

/**
 * A Model stub that always accept the appointment being added.
 */
public class ModelStubAcceptingAppointmentAdded extends ModelStub {
    public final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();
    private final Patient patient = new PatientBuilder().build();
    private final AddressBook addressbook = new AddressBookBuilder().withPatient(patient).build();
    private final FilteredList<Patient> filteredPatients = new FilteredList<Patient>(addressbook.getPatientList());

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointmentsAdded.stream().anyMatch(appointment::isSameAppointment);
    }

    @Override
    public boolean hasAppointmentInArchives(Appointment appointment) {
        return false;
    }

    @Override
    public void addAppointment(Appointment appointment) {
        requireNonNull(appointment);
        appointmentsAdded.add(appointment);
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public ReadOnlyAppointmentBook getAppointmentBook() {
        return new AppointmentBook();
    }
}
