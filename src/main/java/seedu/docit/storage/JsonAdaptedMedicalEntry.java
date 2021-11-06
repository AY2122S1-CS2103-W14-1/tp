package seedu.docit.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.docit.commons.exceptions.IllegalValueException;
import seedu.docit.model.patient.MedicalHistory;

/**
 * Jackson-friendly version of {@link MedicalHistory}.
 */
public class JsonAdaptedMedicalEntry {

    private final String description;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedMedicalEntry} with the given {@code desc, dateOfRecord}.
     */
    @JsonCreator
    public JsonAdaptedMedicalEntry(@JsonProperty("description") String desc,
                                   @JsonProperty("date") String dateOfRecord) {
        this.description = desc;
        this.date = dateOfRecord;
    }

    public String getDescription() {
        return description;
    }

    public String getDateString() {
        return date;
    }

    /**
     * Converts this Jackson-friendly adapted MedicalEntry object into the model's {@code MedicalHistory} object.
     **/
    public MedicalHistory.MedicalEntry toModelType() throws IllegalValueException {
        LocalDate dateOfRecord = LocalDate.parse(date, DateTimeFormatter.ofPattern("d MMM uuuu"));
        return new MedicalHistory.MedicalEntry(description, dateOfRecord);
    }
}
