package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Patient;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Patient patient;

    private final String PHONE_EMOJI = "\uD83D\uDCDE";
    private final String ADDRESS_EMOJI = "\uD83C\uDFE0";
    private final String EMAIL_EMOJI = "\uD83D\uDCE7";
    private final String MEDICAL_HISTORY_EMOJI = "\uD83C\uDFE5";


    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label medicalHistory;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Patient patient, int displayedIndex) {
        super(FXML);
        this.patient = patient;
        id.setText(displayedIndex + ". ");
        name.setText(patient.getName().fullName);
        phone.setText(PHONE_EMOJI +"\t" + patient.getPhone().value);
        address.setText(ADDRESS_EMOJI + "\t" + patient.getAddress().value);
        email.setText(EMAIL_EMOJI + "\t" + patient.getEmail().value);
        patient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        if (!patient.getMedicalHistory().isEmpty()) {
            medicalHistory.setText(MEDICAL_HISTORY_EMOJI + "\t" + patient.getMedicalHistory());
        } else {
            medicalHistory.setVisible(false);
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && patient.equals(card.patient);
    }
}
