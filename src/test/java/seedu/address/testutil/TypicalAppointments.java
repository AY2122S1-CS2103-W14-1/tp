package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AppointmentBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {
    public static final Appointment APPOINTMENT_1 =
        new Appointment(SampleDataUtil.getSamplePersons()[0], "2021-1-1 1600");
    public static final Appointment APPOINTMENT_2 =
        new Appointment(SampleDataUtil.getSamplePersons()[0], "2021-2-2 1600");
    public static final Appointment APPOINTMENT_3 =
        new Appointment(SampleDataUtil.getSamplePersons()[0], "2021-3-3 1600");
    public static final Appointment APPOINTMENT_4 =
        new Appointment(SampleDataUtil.getSamplePersons()[0], "2021-4-1 1600");
    public static final Appointment APPOINTMENT_5 =
        new Appointment(SampleDataUtil.getSamplePersons()[0], "2021-5-2 1600");
    public static final Appointment APPOINTMENT_6 =
        new Appointment(SampleDataUtil.getSamplePersons()[0], "2021-6-3 1600");
    public static final Appointment APPOINTMENT_7 =
        new Appointment(SampleDataUtil.getSamplePersons()[0], "2021-7-1 1600");
    public static final Appointment APPOINTMENT_8 =
        new Appointment(SampleDataUtil.getSamplePersons()[0], "2021-8-2 1600");
    public static final Appointment APPOINTMENT_9 =
        new Appointment(SampleDataUtil.getSamplePersons()[0], "2021-9-3 1600");
    public static final Appointment APPOINTMENT_10 =
        new Appointment(SampleDataUtil.getSamplePersons()[0], "2021-10-1 1600");
    public static final Appointment APPOINTMENT_11 =
        new Appointment(SampleDataUtil.getSamplePersons()[0], "2021-11-2 1600");
    public static final Appointment APPOINTMENT_12 =
        new Appointment(SampleDataUtil.getSamplePersons()[0], "2021-12-3 1600");


    private TypicalAppointments() {
    } // prevents instantiation

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
        return new ArrayList<>(
            Arrays.asList(APPOINTMENT_1, APPOINTMENT_2, APPOINTMENT_3, APPOINTMENT_4, APPOINTMENT_5, APPOINTMENT_6,
                APPOINTMENT_7));
    }
}
