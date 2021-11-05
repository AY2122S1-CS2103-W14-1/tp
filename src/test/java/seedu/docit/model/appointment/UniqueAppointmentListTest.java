package seedu.docit.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.docit.testutil.Assert.assertThrows;
import static seedu.docit.testutil.TypicalAppointments.A1;
import static seedu.docit.testutil.TypicalAppointments.B1;
import static seedu.docit.testutil.TypicalPrescriptions.INSULIN_JAB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.docit.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.docit.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.docit.testutil.AppointmentBuilder;

public class UniqueAppointmentListTest {

    private final UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        assertFalse(uniqueAppointmentList.contains(A1));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(A1);
        assertTrue(uniqueAppointmentList.contains(A1));
    }

    @Test
    public void contains_appointmentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAppointmentList.add(A1);
        Appointment editedA1 = new AppointmentBuilder(A1).withPrescription(INSULIN_JAB).build();
        assertTrue(uniqueAppointmentList.contains(editedA1));
    }

    @Test
    public void add_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.add(null));
    }

    @Test
    public void add_duplicateAppointment_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(A1);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.add(A1));
    }

    @Test
    public void setAppointment_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(null, A1));
    }

    @Test
    public void setAppointment_nullEditedAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(A1, null));
    }

    @Test
    public void setAppointment_targetAppointmentNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.setAppointment(A1, A1));
    }

    @Test
    public void setAppointment_editedAppointmentIsSameAppointment_success() {
        uniqueAppointmentList.add(A1);
        uniqueAppointmentList.setAppointment(A1, A1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(A1);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasSameIdentity_success() {
        uniqueAppointmentList.add(A1);
        Appointment editedA1 = new AppointmentBuilder(A1).withPrescription(INSULIN_JAB).build();
        uniqueAppointmentList.setAppointment(A1, editedA1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(editedA1);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasDifferentIdentity_success() {
        uniqueAppointmentList.add(A1);
        uniqueAppointmentList.setAppointment(A1, B1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(B1);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasNonUniqueIdentity_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(A1);
        uniqueAppointmentList.add(B1);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.setAppointment(A1, B1));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.remove(A1));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(A1);
        uniqueAppointmentList.remove(A1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullUniqueAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueAppointmentList.setAppointments((UniqueAppointmentList) null));
    }

    @Test
    public void setAppointments_uniqueAppointmentList_replacesOwnListWithProvidedUniqueAppointmentList() {
        uniqueAppointmentList.add(A1);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(B1);
        uniqueAppointmentList.setAppointments(expectedUniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointments((List<Appointment>) null));
    }

    @Test
    public void setAppointments_list_replacesOwnListWithProvidedList() {
        uniqueAppointmentList.add(A1);
        List<Appointment> appointmentList = Collections.singletonList(B1);
        uniqueAppointmentList.setAppointments(appointmentList);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(B1);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_listWithDuplicateAppointments_throwsDuplicateAppointmentException() {
        List<Appointment> listWithDuplicateAppointments = Arrays.asList(A1, A1);
        assertThrows(DuplicateAppointmentException.class, ()
            -> uniqueAppointmentList.setAppointments(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueAppointmentList.asUnmodifiableObservableList().remove(0));
    }
}
