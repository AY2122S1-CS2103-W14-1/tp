package seedu.docit.model.prescription;

import java.util.Objects;

import seedu.docit.model.appointment.Appointment;

public class Prescription {
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
        this.medicine = medicine;
        this.volume = volume;
        this.duration = duration;
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

    public boolean medicineContain(String term) {
        return medicine.contains(term);
    }

    public boolean volumeContain(String term) {
        return volume.contains(term);
    }

    public boolean durationContain(String term) {
        return duration.contains(term);
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
    public int hashCode() {
        return Objects.hash(getMedicine(), getVolume(), getDuration());
    }

    @Override
    public String toString() {
        return "Prescription: "
                + "Medicine: " + medicine
                + ", Volume: " + volume
                + ", Duration: " + duration;
    }
}
