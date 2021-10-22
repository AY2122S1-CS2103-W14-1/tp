package seedu.address.model.person;

import seedu.address.model.Entry;
import seedu.address.model.EntryList;

public class MedicalHistory {
    private EntryList<Entry<MedicalEntry>> entryList = new EntryList<>();

    /**
     * Constructs an {@code DateOfBirth}.
     *
     * @param medicalHistory Medical history of patient.
     */
    public MedicalHistory(String medicalHistory) throws IllegalArgumentException {
        Entry<MedicalEntry> medicalEntry = Entry.of(null);
        if (medicalHistory != "" && medicalHistory != " " && medicalHistory != null) {
            medicalEntry = Entry.of(new MedicalEntry(medicalHistory));
        }
        entryList.add(medicalEntry);
    }

    /**
     * A medical entry only exists when a patient has a Medical History.
     */
    private class MedicalEntry {
        private final String description;

        private MedicalEntry(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return this.description;
        }
    }

    @Override
    public String toString() {
        int size = this.entryList.size();
        String toReturn = "";
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                toReturn = toReturn + this.entryList.get(i);
            } else {
                toReturn = toReturn + this.entryList.get(i) + ", ";
            }
        }
        return toReturn;
    }

}
