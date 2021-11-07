package seedu.docit.model.patient;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.docit.model.Entry;
import seedu.docit.model.EntryList;

public class MedicalHistory {
    public static final MedicalHistory EMPTY_MEDICAL_HISTORY = new MedicalHistory(null);
    public static final String MESSAGE_CONSTRAINTS = "Medical History should only contain alphanumeric characters, "
        + "dash, commas, and spaces, "
        + "should not be numerical only, "
        + "and should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum} \\-,]*";

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
     */
    public static class MedicalEntry {
        private final String description;
        private final LocalDate dateOfEntry;

        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu");

        private MedicalEntry(String description) {
            this.description = description;
            this.dateOfEntry = LocalDate.now(ZoneId.of("Singapore"));
        }

        /**
         * Constructor for the inner class Medical Entry.
         * @param description description of a Medical Entry.
         * @param date date of record of the medcial entry.
         */
        public MedicalEntry(String description, LocalDate date) {
            this.description = description;
            this.dateOfEntry = date;
        }

        @Override
        public String toString() {
            String dateToString = dateOfEntry.format(formatter);
            return this.description + ", recorded " + dateToString;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof MedicalEntry) {
                MedicalEntry m = (MedicalEntry) o;
                return this.description.equals(m.description) && this.dateOfEntry.equals(m.dateOfEntry);
            }
            return false;
        }

        public String getDescription() {
            return this.description;
        }

        public String getDateString() {
            return this.dateOfEntry.format(formatter);
        }
    }

    /**
     * Deletes a medical entry specified from the index from the list of medical entries.
     * @param i index to specify the medical entry to be deleted.
     */
    public MedicalHistory delete(int i) {
        if (this.isEmpty()) {
            return this;
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
                s = s.append(icon).append(i + 1 + ". " + entryList.get(i));
            } else {
                s = s.append(icon).append(i + 1 + ". " + entryList.get(i)).append("\n");
            }
        }

        return s.toString();

    }

    /**
     * Returns true if a given string is a valid Medical Entry.
     */
    public static boolean isValidMedicalEntry(String test) {
        return !(test.length() == 0 || test == " " || test == null)
            && test.matches(VALIDATION_REGEX);
    }

    /**
     * Generates a Medical History that contains nothing.
     * @return a medical history object that contains nothing.
     */
    public static MedicalHistory generate() {
        MedicalHistory mh = new MedicalHistory("");
        mh.delete(0);
        return mh;
    }

    /**
     * Generates a Medical History that contains the given description and the date of record.
     * @return a medical history object that contains nothing.
     */
    public static MedicalHistory generate(String desc, String date) {
        MedicalHistory mh = new MedicalHistory("");
        mh.delete(0);
        mh.add(desc, date);
        return mh;
    }

    /**
     * Generates a Medical History that contains the given description, with date of record being today.
     * @return a medical history object that contains nothing.
     */
    public static MedicalHistory generate(String desc) {
        MedicalHistory mh = new MedicalHistory("");
        mh.delete(0);
        mh.add(desc);
        return mh;
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

            return isEqual(m.entryList);

        }
        return false;
    }

    private boolean isEqual(EntryList<Entry<MedicalEntry>> otherList) {
        if (this.isEmpty()) {
            return otherList == this.entryList;
        }

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

    /**
     * Converts the Medical History object into a List representation.
     * @return list representation of MedicalHistory object.
     */
    public List<MedicalEntry> toList() {
        if (this.isEmpty()) {
            return new ArrayList<>();
        }

        int len = entryList.size();
        List<MedicalEntry> toReturn = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            toReturn.add(entryList.get(i).get());
        }

        return toReturn;
    }

}
