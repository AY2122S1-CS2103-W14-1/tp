package seedu.docit.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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
import seedu.docit.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    private static Patient patientAlex = new Patient(new Name("Alex Yeoh"), new Phone("87438807"),
        new Email("alexyeoh" + "@example" + ".com"), new Address("Blk 30 Geylang Street 29, #06-40"),
        getTagSet("friends"), new MedicalHistory("lovesick"));


    public static Patient[] getSamplePatients() {
        return new Patient[] {
            patientAlex,
            new Patient(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), new MedicalHistory("lovesick")),
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), new MedicalHistory("lovesick")),
            new Patient(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), new MedicalHistory("lovesick")),
            new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new MedicalHistory("lovesick")),
            new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), new MedicalHistory("lovesick"))
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

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
