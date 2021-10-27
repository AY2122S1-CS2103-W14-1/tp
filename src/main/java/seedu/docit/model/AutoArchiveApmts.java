package seedu.docit.model;

/**
 * Runnable task to auto archive appointments that are 1-day past.
 */
public class AutoArchiveApmts implements Runnable {
    private final Model model;

    AutoArchiveApmts(Model model) {
        this.model = model;
    }

    @Override
    public void run() {
        model.archivePastAppointments();
    }
}
