package seedu.docit.model.util;

import java.time.LocalDateTime;

import seedu.docit.model.AddressBook;
import seedu.docit.model.AppointmentBook;
import seedu.docit.model.ArchivedAppointmentBook;
import seedu.docit.model.ReadOnlyAddressBook;
import seedu.docit.model.ReadOnlyAppointmentBook;
import seedu.docit.model.appointment.Appointment;
import seedu.docit.model.patient.Address;
import seedu.docit.model.patient.Email;
import seedu.docit.model.patient.MedicalHistory;
import seedu.docit.model.patient.Name;
import seedu.docit.model.patient.Patient;
import seedu.docit.model.patient.Phone;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static Patient patientAlex = new Patient(new Name("Alex Yeoh"), new Phone("87438807"),
            new Email("alexyeoh" + "@example" + ".com"), new Address("Blk 30 Geylang Street 29, #06-40"),
            MedicalHistory.EMPTY_MEDICAL_HISTORY);

    public static Patient[] getSamplePatients() {
        MedicalHistory berniceMh = MedicalHistory.generate("diabetes", "1 Oct 1999");

        MedicalHistory charlotteMh = MedicalHistory.generate("scoliosis", "2 May 2000");

        MedicalHistory davidMh = MedicalHistory.generate("stage 1a cancer", "5 Sep 2005");

        MedicalHistory irfanMh = MedicalHistory.generate("high blood pressure", "8 Aug 2010");

        MedicalHistory royMh = MedicalHistory.generate("anxiety", "8 Aug 2017");

        return new Patient[] {
            patientAlex,
            new Patient(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), berniceMh),
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), charlotteMh),
            new Patient(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), davidMh),
            new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), irfanMh),
            new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), royMh)
        };
    }

    // TODO: Improve sample appointments
    public static Appointment[] getSampleAppointments() {
        return new Appointment[] {
            new Appointment(patientAlex, LocalDateTime.of(2040, 10, 17, 12, 0))
        };
    }


    public static Appointment[] getSampleArchivedAppointments() {
        return new Appointment[] {
            new Appointment(patientAlex, LocalDateTime.of(2019, 10, 17, 12, 0))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }
        return sampleAb;
    }

    public static ReadOnlyAppointmentBook getSampleAppointmentBook() {
        AppointmentBook sampleAb = new AppointmentBook();
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAb.addAppointment(sampleAppointment);
        }
        return sampleAb;
    }

    public static ReadOnlyAppointmentBook getSampleArchivedAppointmentBook() {
        ArchivedAppointmentBook sampleAb = new ArchivedAppointmentBook();
        for (Appointment sampleAppointment : getSampleArchivedAppointments()) {
            sampleAb.addAppointment(sampleAppointment);
        }
        return sampleAb;
    }
}
