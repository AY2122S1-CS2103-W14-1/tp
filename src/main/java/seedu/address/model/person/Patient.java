package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    // Patient specific fields
    private final MedicalHistory medicalHistory;

    /**
     * Every field must be present and not null.
     */
    public Patient(Name name, Phone phone, Email email, Address address, Set<Tag> tags, MedicalHistory medicalHistory) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.medicalHistory = medicalHistory;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public MedicalHistory getMedicalHistory() {
        return this.medicalHistory;
    }

    /**
     * Returns a {@code Patient} object that has the combined {@code MedicalHistory} object.
     * @param mH {@code MedicalHistory} object to be combined with existing patient medical history.
     * @return patient with combined {@code MedicalHistory} object.
     */
    public Patient addMedicalHistory(MedicalHistory mH) { // tell-don't-ask
        return new Patient(name, phone, email, address, tags, this.medicalHistory.append(mH));
    }

    /**
     * Returns a {@code Patient} object that has the deleted {@code MedicalHistory} object.
     * @param index {@code MedicalHistory} object to with a specified deleted medical entry.
     * @return patient with deleted medical entry from {@code MedicalHistory} object.
     */
    public Patient deleteMedicalHistory(Index index) { // tell-don't-ask
        int i = index.getZeroBased();

        if (i >= this.medicalHistory.size()) {
            return this;
        }

        if (this.medicalHistory.size() - 1 == 0) {
            return new Patient(name, phone, email, address, tags, MedicalHistory.EMPTY_MEDICAL_HISTORY);
        }

        return new Patient(name, phone, email, address, tags, this.medicalHistory.delete(i));
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePatient(Patient otherPatient) {
        if (otherPatient == this) {
            return true;
        }

        return otherPatient != null
                && otherPatient.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return otherPatient.getName().equals(getName())
                && otherPatient.getPhone().equals(getPhone())
                && otherPatient.getEmail().equals(getEmail())
                && otherPatient.getAddress().equals(getAddress())
                && otherPatient.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        if (!getMedicalHistory().isEmpty()) {
            builder.append("; Medical History: ")
                .append(getMedicalHistory());
        }

        return builder.toString();
    }

}
