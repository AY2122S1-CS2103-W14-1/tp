package seedu.docit.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.docit.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.docit.commons.core.index.Index;
import seedu.docit.logic.commands.exceptions.CommandException;
import seedu.docit.model.AddressBook;
import seedu.docit.model.Model;
import seedu.docit.model.patient.Patient;
import seedu.docit.model.patient.PatientContainsKeywordsPredicate;
import seedu.docit.testutil.EditPatientDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String MEDICAL_DESC_PATIENT = " " + PREFIX_MEDICAL + "lovesick";

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String VALID_PATIENT_INDEX_0 = "0";
    public static final String VALID_PATIENT_INDEX_1 = "1";
    public static final String VALID_PATIENT_INDEX_2 = "2";

    public static final String VALID_PATIENT_INDEX_DESC_0 = " " + PREFIX_INDEX + VALID_PATIENT_INDEX_0;
    public static final String VALID_PATIENT_INDEX_DESC_1 = " " + PREFIX_INDEX + VALID_PATIENT_INDEX_1;
    public static final String VALID_PATIENT_INDEX_DESC_2 = " " + PREFIX_INDEX + VALID_PATIENT_INDEX_2;

    public static final String INVALID_PATIENT_INDEX_MINUS_1 = "-1";

    public static final String INVALID_PATIENT_INDEX_DESC_MINUS_1 = " " + PREFIX_INDEX + INVALID_PATIENT_INDEX_MINUS_1;

    public static final String VALID_APPOINTMENT_DATE_TIME = "2020-1-10 1600";
    public static final String INVALID_APPOINTMENT_DATE_TIME_MONTH = "2020-13-10 1600"; // invalid month
    public static final String INVALID_APPOINTMENT_DATE_TIME_YEAR = "-1-13-10 1600"; // invalid year
    public static final String INVALID_APPOINTMENT_DATE_TIME_DATE = "2020-1-43 1600"; // invalid date
    public static final String INVALID_APPOINTMENT_DATE_TIME_TIME = "2020-12-10 3400"; // invalid time
    public static final String INVALID_APPOINTMENT_DATE_TIME_ALL = "-1-13-33 9900"; // invalid datetime
    public static final String INVALID_APPOINTMENT_DATE_TIME_NULL = ""; // invalid entry
    public static final String INVALID_APPOINTMENT_DATE_TIME_RANDOM = "asfdsafsfs"; // invalid entry

    public static final String VALID_APPOINTMENT_DATE_TIME_DESC =
            " " + PREFIX_DATETIME + VALID_APPOINTMENT_DATE_TIME;
    public static final String INVALID_APPOINTMENT_DATE_TIME_DESC_MONTH =
            " " + PREFIX_DATETIME + INVALID_APPOINTMENT_DATE_TIME_MONTH; // invalid month
    public static final String INVALID_APPOINTMENT_DATE_TIME_DESC_YEAR =
            " " + PREFIX_DATETIME + INVALID_APPOINTMENT_DATE_TIME_YEAR; // invalid year
    public static final String INVALID_APPOINTMENT_DATE_TIME_DESC_DATE =
            " " + PREFIX_DATETIME + INVALID_APPOINTMENT_DATE_TIME_DATE; // invalid date
    public static final String INVALID_APPOINTMENT_DATE_TIME_DESC_TIME =
            " " + PREFIX_DATETIME + INVALID_APPOINTMENT_DATE_TIME_TIME; // invalid time
    public static final String INVALID_APPOINTMENT_DATE_TIME_DESC_ALL =
            " " + PREFIX_DATETIME + INVALID_APPOINTMENT_DATE_TIME_ALL; // invalid datetime
    public static final String INVALID_APPOINTMENT_DATE_TIME_DESC_NULL =
            " " + PREFIX_DATETIME + INVALID_APPOINTMENT_DATE_TIME_NULL; // invalid entry
    public static final String INVALID_APPOINTMENT_DATE_TIME_DESC_RANDOM =
            " " + PREFIX_DATETIME + INVALID_APPOINTMENT_DATE_TIME_RANDOM; // invalid entry

    public static final EditPatientCommand.EditPatientDescriptor DESC_AMY;
    public static final EditPatientCommand.EditPatientDescriptor DESC_BOB;

    public static final String VALID_PRESCRIPTION_MEDICINE = "Penicillin";
    public static final String VALID_PRESCRIPTION_VOLUME = "400 ml";
    public static final String VALID_PRESCRIPTION_DURATION = "2 times a week";

    public static final String VALID_APPOINTMENT_INDEX = "1";

    public static final String VALID_PRESCRIPTION_MEDICINE_DESC =
            " " + PREFIX_NAME + VALID_PRESCRIPTION_MEDICINE;

    static {
        DESC_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withMedicalHistory(MEDICAL_DESC_PATIENT).build();
        DESC_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withMedicalHistory(MEDICAL_DESC_PATIENT).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered patient list and selected patient in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Patient> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPatientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPatientList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the patient at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPatientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Patient patient = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        final String[] splitName = patient.getName().fullName.split("\\s+");
        model.updateFilteredPatientList(new PatientContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPatientList().size());
    }

}
