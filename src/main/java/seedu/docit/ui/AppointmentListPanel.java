package seedu.docit.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.docit.commons.core.LogsCenter;
import seedu.docit.model.appointment.Appointment;

/**
 * Panel containing the list of apointments.
 */
public class AppointmentListPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentListPanel.class);

    @FXML
    private ListView<Appointment> appointmentListView;

    /**
     * Creates a {@code AppointmentListPanel} with the given {@code ObservableList}.
     */
    public AppointmentListPanel(ObservableList<Appointment> appointmentList) {
        super(FXML);
        appointmentListView.setItems(appointmentList);
        appointmentListView.setCellFactory(listView -> new AppointmentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Appointment} using a {@code AppointmentCard}.
     */
    private static class AppointmentListViewCell extends ListCell<Appointment> {
        @Override
        protected void updateItem(Appointment appointment, boolean empty) {
            super.updateItem(appointment, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else if (appointment != null) {
                setGraphic(new AppointmentCard(appointment, getIndex() + 1).getRoot());
            } else {
                setGraphic(null);
                setText(null);
            }
        }
    }

}
