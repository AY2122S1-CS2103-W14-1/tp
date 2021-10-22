package seedu.address.model.person;

import java.util.ArrayList;

public class MedicalHistory {
    private final ArrayList<String> medicalHistory = new ArrayList<>();

    /**
     * Constructs an {@code DateOfBirth}.
     *
     * @param medicalHistory Medical history of patient.
     */
    public MedicalHistory(String medicalHistory) throws IllegalArgumentException {
        this.medicalHistory.add(medicalHistory);
    }

    @Override
    public String toString() {
        int size = this.medicalHistory.size();
        String toReturn = "";
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                toReturn = toReturn + this.medicalHistory.get(i);
            } else {
                toReturn = toReturn + this.medicalHistory.get(i) + ", ";
            }
        }
        return toReturn;
    }

}
