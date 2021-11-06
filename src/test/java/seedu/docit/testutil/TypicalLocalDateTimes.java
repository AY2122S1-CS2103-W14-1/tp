package seedu.docit.testutil;

import java.time.LocalDateTime;

/**
 * A utility class containing a list of {@code LocalDateTime} objects to be used in tests.
 */
public class TypicalLocalDateTimes {
    // Future
    public static final LocalDateTime FIRST_JAN = LocalDateTime.of(2042, 1, 1, 0, 0);
    public static final LocalDateTime NON_LEAP_FEB = LocalDateTime.of(2023, 2, 28, 0, 0);
    public static final LocalDateTime LEAP_FEB = LocalDateTime.of(2024, 2, 29, 0, 0);

    // Past
    public static final LocalDateTime PAST_1 = LocalDateTime.of(2020, 1, 1, 0, 0);
    public static final LocalDateTime PAST_2 = LocalDateTime.of(2020, 12, 31, 23, 59);
}
