package seedu.docit.testutil.stubs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.prescription.Prescription;

public class ModelStubWithPrescription extends ModelStubWithAppointment {
    /**
     * Returns a model stub that contains 1 appointment with 1 prescription
     *
     * @param appointment Appointment to be contained in the stub
     */
    public ModelStubWithPrescription(Appointment appointment, Prescription prescription) {
        super(appointment);
        appointment.addPrescription(prescription);
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return FXCollections.unmodifiableObservableList(this.appointment);
    }
}
