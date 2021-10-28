package seedu.docit.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.docit.commons.exceptions.IllegalValueException;
import seedu.docit.model.prescription.Prescription;
import seedu.docit.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
public class JsonAdaptedPrescription {

    private final String volume;
    private final String medicine;
    private final String duration;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedPrescription(@JsonProperty("volume") String volume,
                                   @JsonProperty("medicine") String medicine,
                                   @JsonProperty("duration") String duration) {
        this.volume = volume;
        this.duration = duration;
        this.medicine = medicine;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedPrescription(Prescription source) {
        this.volume = source.getVolume();
        this.duration = source.getDuration();
        this.medicine = source.getMedicine();
    }

    public String getPrescriptionMedicine() {
        return medicine;
    }

    public String getPrescriptionVolume() {
        return volume;
    }

    public String getPrescriptionDuration() {
        return duration;
    }

    /**
     * Converts this Jackson-friendly adapted prescription object into the model's {@code Prescription} object.
     **/
    public Prescription toModelType() throws IllegalValueException {
        return new Prescription(medicine, volume, duration);
    }

}
