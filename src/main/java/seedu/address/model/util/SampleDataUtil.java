package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.ArchivedAppointmentBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePersons() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), new MedicalHistory("lovesick")),
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
            new Appointment(1, "2020-10-17")
        };
    }

    public static Appointment[] getSampleArchivedAppointments() {
        return new Appointment[] {
            new Appointment(58, "2009-10-17"),
            new Appointment(56, "2009-10-17"),
            new Appointment(98, "2009-10-17"),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePersons()) {
            sampleAb.addPerson(samplePatient);
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
