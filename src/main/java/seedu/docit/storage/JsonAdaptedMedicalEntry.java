package seedu.docit.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    public String getDescription() {
        return description;
    }

    public String getDateString() {
        return date;
    }

}
