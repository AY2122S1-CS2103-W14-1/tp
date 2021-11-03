package seedu.docit.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.docit.commons.exceptions.IllegalValueException;
import seedu.docit.model.patient.MedicalHistory;
import seedu.docit.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
public class JsonAdaptedMedicalEntry {

    private final String description;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedMedicalEntry(@JsonProperty("description") String desc,
                                   @JsonProperty("date") String dateOfRecord) {
        this.description = desc;
        this.date = dateOfRecord;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedMedicalEntry(MedicalHistory.MedicalEntry source) {
        this.description = source.getDescription();
        this.date = source.getDateString();
    }

    public String getDescription() {
        return description;
    }

    public String getDateString() {
        return date;
    }

    /**
     * Converts this Jackson-friendly adapted prescription object into the model's {@code Prescription} object.
     **/
    public MedicalHistory.MedicalEntry toModelType() throws IllegalValueException {
        LocalDate dateOfRecord = LocalDate.parse(date, DateTimeFormatter.ofPattern("d MMM uuuu"));
        return new MedicalHistory.MedicalEntry(description, dateOfRecord);
    }

}