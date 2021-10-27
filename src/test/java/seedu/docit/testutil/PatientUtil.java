package seedu.docit.testutil;

import static seedu.docit.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.docit.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.docit.logic.commands.AddPatientCommand;
import seedu.docit.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.docit.model.patient.Patient;
import seedu.docit.model.tag.Tag;

/**
 * A utility class for Patient.
 */
public class PatientUtil {
    /**
     * Returns an add command string for adding the {@code patient}.
     */
    public static String getAddCommand(Patient patient) {
        return AddPatientCommand.COMMAND_WORD + " " + getPatientDetails(patient);
    }

    /**
     * Returns the part of command string for the given {@code patient}'s details.
     */
    public static String getPatientDetails(Patient patient) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + patient.getName().fullName + " ");
        sb.append(PREFIX_PHONE + patient.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + patient.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + patient.getAddress().value + " ");

        patient.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        sb.append(PREFIX_MEDICAL + patient.getMedicalHistory().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPatientDescriptor}'s details.
     */
    public static String getEditPatientDescriptorDetails(EditPatientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG + " ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        descriptor.getMedicalHistory().ifPresent(medicalHistory ->
                sb.append(PREFIX_MEDICAL).append(medicalHistory.toString()).append(" "));
        return sb.toString();
    }
}
