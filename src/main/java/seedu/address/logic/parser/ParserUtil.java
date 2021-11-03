package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DATETIME = "%s is incorrect datetime format.";
    public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");

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
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
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
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code String datetime} of given format into a {@code LocalDateTime}.
     */
    public static LocalDateTime parseDateTime(String datetime, DateTimeFormatter formatter) throws ParseException {
        requireNonNull(datetime);
        if (formatter == null) {
            formatter = DEFAULT_DATE_TIME_FORMATTER;
        }
        try {
            return LocalDateTime.parse(datetime, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATETIME, datetime));
        }
    }

    /**
     * Parses {@code String medicalHistory} into a {@Code MedicalHistory}.
     */
    public static MedicalHistory parseMedicalHistory(String medicalHistory) {
        Object[] detailedEntries = breakMhIntoEntries(medicalHistory);
        MedicalHistory toParseMh = new MedicalHistory(null);

        if (detailedEntries.length > 0) { // has at least one medical entry
            toParseMh.delete(0);

            for (int i = 0; i < detailedEntries.length; i++) {
                @SuppressWarnings("unchecked")
                String[] entry = (String[]) detailedEntries[i];

                if (entry.length == 1) { // no date
                    if (!isValidMh(entry[0])) {
                        toParseMh = MedicalHistory.EMPTY_MEDICAL_HISTORY;
                    } else {
                        toParseMh.add(entry[0].trim());
                    }
                } else {
                    if (!isValidMh(entry[1])) {
                        toParseMh = MedicalHistory.EMPTY_MEDICAL_HISTORY;
                    } else {
                        toParseMh.add(entry[1].trim(), entry[0].trim());
                    }
                }
            }
        }

        return toParseMh;
    }

    /**
     * Overloads method to ensure that medical history can be an optional.
     * @param medicalEntries an empty Arraylist.
     * @return an empty medical history.
     */
    public static MedicalHistory parseMedicalHistory(Collection<String> medicalEntries) {
        requireNonNull(medicalEntries);

        MedicalHistory toParseMh = new MedicalHistory("");
        toParseMh.delete(0);

        for (String medicalEntry : medicalEntries) {
            MedicalHistory mh = parseMedicalHistory(medicalEntry);
            if (mh.equals(MedicalHistory.EMPTY_MEDICAL_HISTORY)) {
                toParseMh = MedicalHistory.EMPTY_MEDICAL_HISTORY;
                break;
            } else {
                toParseMh.append(parseMedicalHistory(medicalEntry));
            }
        }

        System.out.println(toParseMh.equals(MedicalHistory.EMPTY_MEDICAL_HISTORY));

        return toParseMh.size() == 0 ? MedicalHistory.EMPTY_MEDICAL_HISTORY : toParseMh;
    }

    private static Object[] breakMhIntoEntries(String medicalHistory) {
        String[] entries = medicalHistory.split(", ");
        Object[] entriesDateDesc = Arrays.stream(entries).map(x -> x.split("\\| ")).toArray();
        return entriesDateDesc;
    }

    private static boolean isValidMh(String entry) {
        return !(entry.length() == 0 || entry == " " || entry == null);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given {@code
     * ArgumentMultimap}.
     */
    public static boolean hasAllPrefixes(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
