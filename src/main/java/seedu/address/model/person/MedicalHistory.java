package seedu.address.model.person;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

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
    public MedicalHistory(String medicalHistory) {
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
    public MedicalHistory delete(int i) {
        if (this.isEmpty()) {
            return this; // nothing to delete
        }

        this.entryList.delete(i);
        return this;
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
     * Appends medical entries of another {@code MedicalHistory} object to this {@code MedicalHistory} object.
     * @param mh {@code MedicalHistory} object that is to be added to
     */
    public MedicalHistory append(MedicalHistory mh) {
        if (this.isEmpty()) { // if no record was stored
            return mh;
        }

        for (int i = 0; i < mh.size(); i++) {
            this.entryList.add(mh.entryList.get(i));
        }

        return this;
    }

    /**
     * Returns the count of medical entries within the medical history.
     * @return count of medical entries.
     */
    public int size() {
        return this.entryList.size();
    }

    /**
     * Returns the string representation of Medical History as displayed on the app.
     * @return string representation for GUI.
     */
    public String display(String emoji) {
        if (this.isEmpty()) {
            return "No medical history recorded.";
        }
        int size = entryList.size();
        StringBuilder s = new StringBuilder();
        StringBuilder icon = new StringBuilder(emoji + "\t");

        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                s = s.append(icon).append(entryList.get(i));
            } else {
                s = s.append(icon).append(entryList.get(i)).append("\n");
            }
        }

        return s.toString();

    }

    @Override
    public String toString() { // to store the list into a CSV format
        if (this.isEmpty()) {
            return "";
        }

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

            return checkIsEqual(m.entryList);

        }
        return false;
    }

    private boolean checkIsEqual(EntryList<Entry<MedicalEntry>> otherList) {
        int len = this.entryList.size();

        for (int i = 0; i < len; i++) {
            if (!this.entryList.get(i).equals(otherList.get(i))) {
                return false;
            }
        }

        return true;
    }

    public boolean isEmpty() {
        return this == EMPTY_MEDICAL_HISTORY;
    }

    /**
     * Converts the MedicalHistory object into a stream representation.
     * @return stream representation of MedicalHistory object.
     */
    public Stream<String> toStream() {
        return this.isEmpty()
            ? Stream.of("")
            : this.entryList
                .toStream()
                .map(entry -> entry.get())
                .filter(val -> val != null)
                .map(entry -> entry.description);
    }

}
