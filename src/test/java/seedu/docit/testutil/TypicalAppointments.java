package seedu.docit.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.docit.model.AppointmentBook;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.util.SampleDataUtil;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {
    public static final Appointment APPOINTMENT_1 = new Appointment(SampleDataUtil.getSamplePatients()[0],
        LocalDateTime.of(2022, 1, 1, 16, 0));
    public static final Appointment APPOINTMENT_2 = new Appointment(SampleDataUtil.getSamplePatients()[1],
        LocalDateTime.of(2022, 2, 2, 16, 0));
    public static final Appointment APPOINTMENT_3 = new Appointment(SampleDataUtil.getSamplePatients()[2],
        LocalDateTime.of(2022, 3, 3, 16, 0));
    public static final Appointment APPOINTMENT_4 = new Appointment(SampleDataUtil.getSamplePatients()[3],
        LocalDateTime.of(2022, 4, 1, 16, 0));
    public static final Appointment APPOINTMENT_5 = new Appointment(SampleDataUtil.getSamplePatients()[4],
        LocalDateTime.of(2022, 5, 1, 16, 0));
    public static final Appointment APPOINTMENT_6 = new Appointment(SampleDataUtil.getSamplePatients()[5],
        LocalDateTime.of(2022, 6, 1, 16, 0));
    public static final Appointment APPOINTMENT_7 = new Appointment(SampleDataUtil.getSamplePatients()[0],
        LocalDateTime.of(2022, 7, 1, 16, 0));
    public static final Appointment APPOINTMENT_8 = new Appointment(SampleDataUtil.getSamplePatients()[0],
        LocalDateTime.of(2022, 8, 1, 16, 0));
    public static final Appointment APPOINTMENT_9 = new Appointment(SampleDataUtil.getSamplePatients()[0],
        LocalDateTime.of(2022, 9, 1, 16, 0));
    public static final Appointment APPOINTMENT_10 = new Appointment(SampleDataUtil.getSamplePatients()[0],
        LocalDateTime.of(2022, 10, 1, 16, 0));
    public static final Appointment APPOINTMENT_11 = new Appointment(SampleDataUtil.getSamplePatients()[0],
        LocalDateTime.of(2022, 11, 1, 16, 0));
    public static final Appointment APPOINTMENT_12 = new Appointment(SampleDataUtil.getSamplePatients()[0],
        LocalDateTime.of(2022, 12, 1, 16, 0));


    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns an {@code AppointmentList} with all the typical appointments.
     */
    public static AppointmentBook getTypicalAppointmentList() {
        AppointmentBook ab = new AppointmentBook();
        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(APPOINTMENT_1, APPOINTMENT_2, APPOINTMENT_3,
                APPOINTMENT_4, APPOINTMENT_5, APPOINTMENT_6, APPOINTMENT_7));
    }
}
