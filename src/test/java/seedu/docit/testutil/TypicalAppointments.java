package seedu.docit.testutil;

import static seedu.docit.testutil.TypicalPrescriptions.PRESCRIPTION_COLD;
import static seedu.docit.testutil.TypicalPrescriptions.PRESCRIPTION_DIABETES;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.docit.model.AppointmentBook;
import seedu.docit.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {
    // Upcoming appointments
    public static final Appointment A1 = new Appointment(TypicalPatients.ALICE,
        LocalDateTime.of(2022, 1, 1, 16, 0));
    public static final Appointment A2 = new Appointment(TypicalPatients.ALICE,
        LocalDateTime.of(2022, 2, 2, 16, 0));
    public static final Appointment B1 = new Appointment(TypicalPatients.BENSON,
        LocalDateTime.of(2022, 1, 1, 16, 0));
    public static final Appointment B2 = new Appointment(TypicalPatients.BENSON,
        LocalDateTime.of(2022, 2, 1, 16, 0));

    // Upcoming not present in typical appointment book
    public static final Appointment C1 = new Appointment(TypicalPatients.CARL,
        LocalDateTime.of(2022, 5, 21, 16, 0));
    public static final Appointment C2 = new Appointment(TypicalPatients.CARL,
        LocalDateTime.of(2022, 7, 6, 16, 0));

    // Past appointments
    public static final Appointment A_PAST = new Appointment(TypicalPatients.ALICE,
        LocalDateTime.of(2012, 5, 1, 16, 0), PRESCRIPTION_DIABETES);
    public static final Appointment B_PAST = new Appointment(TypicalPatients.BENSON,
        LocalDateTime.of(1999, 10, 1, 16, 0), PRESCRIPTION_COLD);

    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns an {@code AppointmentBook} with all the typical upcoming appointments.
     */
    public static AppointmentBook getTypicalAppointmentBook() {
        AppointmentBook ab = new AppointmentBook();
        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(A1, A2, B1,
            B2));
    }

    /**
     * Resets prescriptions on all typical upcoming appointments
     */
    public static void resetPrescriptions() {
        A1.resetPrescriptions();
        A2.resetPrescriptions();
        B1.resetPrescriptions();
        B2.resetPrescriptions();
        C1.resetPrescriptions();
        C2.resetPrescriptions();
    }
}
