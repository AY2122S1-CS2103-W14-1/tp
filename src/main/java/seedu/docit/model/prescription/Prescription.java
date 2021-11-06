package seedu.docit.model.prescription;

public class Prescription {
    public static final int MEDICINE_CHAR_LENGTH_LIMIT = 20;
    public static final int VOLUME_CHAR_LENGTH_LIMIT = 20;
    public static final int DURATION_CHAR_LENGTH_LIMIT = 40;

    private String medicine;
    private String volume;
    private String duration;

    /**
     * Creates a new Prescription object with medicine, volume, duration and associated appointment
     * @param medicine Name of medicine
     * @param volume Volume of medicine
     * @param duration Duration of medicine intake
     */
    public Prescription(String medicine, String volume, String duration) {
        if (medicine.isBlank() || volume.isBlank() || duration.isBlank()) {
            throw new RuntimeException("Medicine cannot be blank. Volume cannot be blank. Duration cannot be blank.");
        }
        this.medicine = medicine.toLowerCase();
        this.volume = volume.toLowerCase();
        this.duration = duration.toLowerCase();
    }


    public String getDuration() {
        return duration;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getVolume() {
        return volume;
    }

    /**
     * A looser definition of equality where two prescriptions are considered the same if they have the same medicine
     * name.
     * @param p The other prescription
     * @return Boolean indicating if the two prescriptions have the same name.
     */
    public boolean hasSameMedicalName(Prescription p) {
        return this.getMedicine().equals(p.getMedicine());
    }

    public String toUiFormat() {
        return medicine + " | " + volume + " | " + duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Prescription that = (Prescription) o;
        return that.getDuration().equals(getDuration())
                && that.getMedicine().equals(getMedicine())
                && that.getVolume().equals(getVolume());
    }

    @Override
    public String toString() {
        return "Medicine: " + medicine
                + ", Volume: " + volume
                + ", Duration: " + duration;
    }
}
