package seedu.address.model.prescription;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Patient;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.prescription.exceptions.DuplicatePrescriptionException;
import seedu.address.model.prescription.exceptions.MedicineNotFoundException;


import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

public class UniquePrescriptionList {
    private enum Parameter{MEDICINE, VOLUME, DURATION};
    private final ObservableList<Prescription> prescriptions = FXCollections.observableArrayList();
    private final ObservableList<Prescription> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(prescriptions);


    public void add(Prescription p) throws DuplicatePrescriptionException {
        if (contains(p)) {
            throw new DuplicatePrescriptionException();
        }
        prescriptions.add(p);
    }

    public void remove(String medicineName) throws MedicineNotFoundException {
        requireNonNull(medicineName);
        Prescription intendedDeletion = null;
        for (Prescription p: prescriptions) {
            if (p.getMedicine().equals(medicineName)) {
                intendedDeletion = p;
                break;
            }
        }

        if (Objects.equals(intendedDeletion, null)) {
            throw new MedicineNotFoundException();
        } else {
            prescriptions.remove(intendedDeletion);
        }
    }

    public void remove(Prescription prescription) throws MedicineNotFoundException {
        requireNonNull(prescription);
        if (!prescriptions.remove(prescription)) {
            throw new MedicineNotFoundException();
        }
    }

    /**
     * Returns index of prescription in the list.
     */
    public Index indexOf(Prescription prescription) {
        requireNonNull(prescription);
        return prescriptions.indexOf(prescription) != -1 ? Index.fromZeroBased(prescriptions.indexOf(prescription)) : null;
    }

    private String findSomething(Parameter variable, String term) {
        StringBuilder sb = new StringBuilder();
        Integer counter = 1;
        for (Prescription p: prescriptions) {

            String parameter = "";
            switch (variable) {
            case MEDICINE : parameter = p.getMedicine();
                break;
            case VOLUME : parameter = p.getVolume();
                break;
            case DURATION : parameter = p.getDuration();
                break;
            }
            if (parameter.contains(term)) {
                sb.append(counter).append(". ");
                sb.append(p);
                sb.append("\n");
                counter ++;
            }
        }
        return sb.toString();

    }

    public ObservableList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public String findMedicine(String medicine) {
        return findSomething(Parameter.MEDICINE, medicine);
    }

    public String findVolume(String volume) {
        return findSomething(Parameter.VOLUME, volume);
    }

    public String findDuration(String duration) {
        return findSomething(Parameter.DURATION, duration);
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean prescriptionsAreUnique(List<Patient> patients) {
        for (int i = 0; i < patients.size() - 1; i++) {
            for (int j = i + 1; j < patients.size(); j++) {
                if (patients.get(i).isSamePatient(patients.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Prescription prescription) {
        requireNonNull(prescription);
        return prescriptions.stream().anyMatch(prescription::isSamePrescription);
    }


    public boolean isEmpty() {
        return prescriptions.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniquePrescriptionList that = (UniquePrescriptionList) o;
        return prescriptions.equals(that.prescriptions);
    }

}
