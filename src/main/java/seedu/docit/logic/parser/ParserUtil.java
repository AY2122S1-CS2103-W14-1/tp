package seedu.docit.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.docit.commons.core.LogsCenter;
import seedu.docit.commons.core.index.Index;
import seedu.docit.commons.util.StringUtil;
import seedu.docit.logic.parser.exceptions.ParseException;
import seedu.docit.model.patient.Address;
import seedu.docit.model.patient.Email;
import seedu.docit.model.patient.MedicalHistory;
import seedu.docit.model.patient.Name;
import seedu.docit.model.patient.Phone;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DATETIME = "%s is incorrect datetime format.";
    public static final String MESSAGE_INVALID_DATETIME_VALUE = "Year must be between year 2000 to 2999 inclusive and "
        + "hour must"
        + " be between 0000 to 2359 inclusive.";
    public static final String MESSAGE_INVALID_NUMERICAL_ONLY = "%s cannot be numerical only.";
    public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM uuuu HHmm");
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu-M-d HHmm");

    private static final int min_year = 2000;
    private static final int max_year = 2999;
    private static final int max_hour = 2359;

    private static final Logger logger = LogsCenter.getLogger(ParserUtil.class);

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String docit} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code docit} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (isNumericalOnly(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses {@code String datetime} of given format into a {@code LocalDateTime}.
     */
    public static LocalDateTime parseDateTime(String datetime, DateTimeFormatter formatter) throws ParseException {
        requireNonNull(datetime);
        if (formatter == null) {
            formatter = DEFAULT_DATE_TIME_FORMATTER;
        }

        Pattern p = Pattern.compile("(?<year>[0-9]{4})-[0-9]{1,2}-[0-9]{1,2} (?<hour>[0-9]{4})");
        Matcher m = p.matcher(datetime);
        if (!m.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATETIME, datetime));
        }

        int year;
        int hour;
        try {
            year = Integer.parseInt(m.group("year"));
            hour = Integer.parseInt(m.group("hour"));
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATETIME, datetime));
        }

        // to limit inputs further
        if (year < min_year || year > max_year || hour > max_hour) {
            throw new ParseException(MESSAGE_INVALID_DATETIME_VALUE);
        }

        try {
            return LocalDateTime.parse(datetime, formatter.withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            logger.warning(e.getMessage());
            throw new ParseException(e.getMessage());
        }
    }

    /**
     * Parses {@code String medicalEntry} into a {@code MedicalHistory}.
     */
    public static MedicalHistory parseMedicalEntry(String medicalEntry) throws ParseException {
        requireNonNull(medicalEntry);
        String trimmedMedicalEntry = medicalEntry.trim();

        if (isNumericalOnly(trimmedMedicalEntry)) {
            throw new ParseException(MedicalHistory.MESSAGE_CONSTRAINTS);
        }
        if (!MedicalHistory.isValidMedicalEntry(trimmedMedicalEntry)) {
            throw new ParseException(MedicalHistory.MESSAGE_CONSTRAINTS);
        }
        return new MedicalHistory(trimmedMedicalEntry);
    }

    /**
     * Overloads method to ensure that medical history can be an optional.
     * @param medicalEntries an empty Arraylist.
     * @return an empty medical history.
     */
    public static MedicalHistory parseMedicalHistory(Collection<String> medicalEntries) throws ParseException {
        requireNonNull(medicalEntries);

        MedicalHistory toParseMh = new MedicalHistory("");
        toParseMh.delete(0);

        for (String medicalEntry : medicalEntries) {
            MedicalHistory mh = parseMedicalEntry(medicalEntry);
            if (mh.equals(MedicalHistory.EMPTY_MEDICAL_HISTORY)) {
                toParseMh = MedicalHistory.EMPTY_MEDICAL_HISTORY;
                break;
            } else {
                toParseMh.append(parseMedicalEntry(medicalEntry));
            }
        }

        return toParseMh.size() == 0 ? MedicalHistory.EMPTY_MEDICAL_HISTORY : toParseMh;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given {@code
     * ArgumentMultimap}.
     */
    public static boolean hasAllPrefixes(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean isNumericalOnly(String input) {
        requireNonNull(input);
        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(input);
        return m.matches();
    }
}
