package seedu.docit.storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.docit.commons.exceptions.IllegalValueException;
import seedu.docit.model.patient.Address;
import seedu.docit.model.patient.Email;
import seedu.docit.model.patient.MedicalHistory;
import seedu.docit.model.patient.Name;
import seedu.docit.model.patient.Patient;
import seedu.docit.model.patient.Phone;
import seedu.docit.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Patient}.
 */
public class JsonAdaptedPatient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String medicalHistory;

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("docit") String address,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("medicalHistory") String medical) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.medicalHistory = medical;

    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        medicalHistory = source.getMedicalHistory().toString();
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient.
     */
    public Patient toModelType() throws IllegalValueException {
        final List<Tag> patientTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            patientTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(patientTags);

        Object[] detailedEntries = breakIntoEntries(medicalHistory);

        MedicalHistory modelMedicalHistory = new MedicalHistory("");

        if (detailedEntries.length > 0) { // has at least one medical entry
            modelMedicalHistory.delete(0);

            for (int i = 0; i < detailedEntries.length; i++) {
                @SuppressWarnings("unchecked")
                String[] entry = (String[]) detailedEntries[i];

                if (entry.length == 1) { // no date
                    if (!isValidMh(entry[0])) {
                        modelMedicalHistory = MedicalHistory.EMPTY_MEDICAL_HISTORY;
                    } else {
                        modelMedicalHistory.add(entry[0].trim());
                    }
                } else {
                    if (!isValidMh(entry[1])) {
                        modelMedicalHistory = MedicalHistory.EMPTY_MEDICAL_HISTORY;
                    } else {
                        modelMedicalHistory.add(entry[1].trim(), entry[0].trim());
                    }
                }
            }
        }

        return new Patient(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelMedicalHistory);
    }

    private static Object[] breakIntoEntries(String medicalHistory) {
        String[] entries = medicalHistory.split(", ");
        Object[] entriesDateDesc = Arrays.stream(entries).map(x -> x.split("\\| ")).toArray();
        return entriesDateDesc;
    }

    private static boolean isValidMh(String entry) {
        return !(entry.length() == 0 || entry == " " || entry == null);
    }

}
