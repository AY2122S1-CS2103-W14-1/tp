package seedu.address.model.person;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import seedu.address.model.Entry;
import seedu.address.model.EntryList;

public class MedicalHistory {
    public static final MedicalHistory EMPTY_MEDICAL_HISTORY = new MedicalHistory(null);
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
     * The class is made private to ensure that other classes do not break the abstraction barrier.
     */
    private class MedicalEntry {
        private final String description;
        private LocalDate dateOfEntry;

        private MedicalEntry(String description) {
            this.description = description;
            this.dateOfEntry = LocalDate.now(ZoneId.of("Singapore"));
        }

        private MedicalEntry(String description, LocalDate date) {
            this.description = description;
            this.dateOfEntry = date;
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu");
            String dateToString = dateOfEntry.format(formatter);
            return dateToString + " | " + this.description;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof MedicalEntry) {
                MedicalEntry m = (MedicalEntry) o;

                if ((this.description.equals(m.description) || this.description == m.description)
                        && this.dateOfEntry.equals(m.dateOfEntry)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Deletes a medical entry specified from the index from the list of medical entries.
     * @param i index to specify the medical entry to be deleted.
     */
    public void delete(int i) {
        this.entryList.delete(i);
    }

    /**
     * Adds a MedicalEntry into the MedicalHistory, with the entry consisting of a description and a date.
     * @param desc description of the medical entry.
     * @param date date of recording of the medical entry.
     */
    public void add(String desc, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu");
        LocalDate dateOfEntry = LocalDate.parse(date, formatter);
        MedicalEntry entryToAdd = new MedicalEntry(desc, dateOfEntry);
        this.entryList.add(Entry.of(entryToAdd));
    }

    /**
     * Adds a MedicalEntry into the MedicalHistory, with the entry consisting of a description.
     * @param desc description of the medical entry.
     */
    public void add(String desc) {
        MedicalEntry entryToAdd = new MedicalEntry(desc);
        this.entryList.add(Entry.of(entryToAdd));
    }

    /**
     * Returns the count of medical entries within the medical history.
     * @return count of medical entries.
     */
    public int size() {
        return this.entryList.size();
    }

    @Override
    public String toString() { // to store the list into a CSV format
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof MedicalHistory) {
            MedicalHistory m = (MedicalHistory) o;

            if (m.entryList.size() != this.entryList.size()) {
                return false;
            }

            return checkIsEqual(this.entryList, m.entryList);

        }
        return false;
    }

    private boolean checkIsEqual(EntryList<Entry<MedicalEntry>> entryList, EntryList<Entry<MedicalEntry>> otherList) {
        int len = this.entryList.size();

        for (int i = 0; i < len; i++) {
            if (!this.entryList.get(i).equals(otherList.get(i))) {
                return false;
            }
        }

        return true;
    }

    public boolean isEmpty() {
        return this.equals(EMPTY_MEDICAL_HISTORY);
    }

}
