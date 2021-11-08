package seedu.docit.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.docit.testutil.TypicalAppointments.getAppointmentBookWithExpiredApmts;
import static seedu.docit.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.docit.testutil.TypicalAppointments.getTypicalArchivedAppointmentBook;
import static seedu.docit.testutil.TypicalPatients.getTypicalAddressBook;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class AutoArchiveTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), getAppointmentBookWithExpiredApmts(),
            new AppointmentBook(), new UserPrefs());

    @Test
    public void autoarchiveOnStartup_expiredAppointmentsPresent_autoarchivesExpiredAppointments() {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), getTypicalAppointmentBook(),
                getTypicalArchivedAppointmentBook(), new UserPrefs());

        assertEquals(model, expectedModel);
    }

    @Test
    public void initAutoArchive_initOnUpdateHour_autoArchiveImmediately() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
        ZonedDateTime sampleTime = ZonedDateTime.parse("2019-03-27 05:00:00 AM +05:30", formatter);
        ZonedDateTime nextRun = sampleTime.withHour(5).withMinute(0).withSecond(0);
        ZonedDateTime expectedNextRun = ZonedDateTime.parse("2019-03-27 05:00:00 AM +05:30", formatter);

        assertTrue(sampleTime.compareTo(nextRun) == 0);

        if (sampleTime.compareTo(nextRun) > 0) {
            nextRun = nextRun.plusDays(1);
        }

        Duration duration = Duration.between(sampleTime, nextRun);
        long initialDelay = duration.getSeconds();

        assertTrue(initialDelay == 0);

        assertEquals(nextRun, expectedNextRun);
    }

    @Test
    public void initAutoArchive_initAfterUpdateHour_autoArchiveNextDay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
        ZonedDateTime sampleTime = ZonedDateTime.parse("2019-03-27 05:00:01 AM +05:30", formatter);
        ZonedDateTime nextRun = sampleTime.withHour(5).withMinute(0).withSecond(0);
        ZonedDateTime expectedNextRun = ZonedDateTime.parse("2019-03-28 05:00:00 AM +05:30", formatter);

        assertTrue(sampleTime.compareTo(nextRun) > 0);

        if (sampleTime.compareTo(nextRun) > 0) {
            nextRun = nextRun.plusDays(1);
        }

        Duration duration = Duration.between(sampleTime, nextRun);
        long initialDelay = duration.getSeconds();

        assertTrue(initialDelay > 0);

        assertEquals(nextRun, expectedNextRun);
    }

    @Test
    public void initAutoArchive_initBeforeUpdateHour_autoArchiveSameDay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
        ZonedDateTime sampleTime = ZonedDateTime.parse("2019-03-27 04:59:59 AM +05:30", formatter);
        ZonedDateTime nextRun = sampleTime.withHour(5).withMinute(0).withSecond(0);
        ZonedDateTime expectedNextRun = ZonedDateTime.parse("2019-03-27 05:00:00 AM +05:30", formatter);

        assertTrue(sampleTime.compareTo(nextRun) < 0);

        if (sampleTime.compareTo(nextRun) > 0) {
            nextRun = nextRun.plusDays(1);
        }

        Duration duration = Duration.between(sampleTime, nextRun);
        long initialDelay = duration.getSeconds();

        assertTrue(initialDelay > 0);

        assertEquals(nextRun, expectedNextRun);
    }
}
