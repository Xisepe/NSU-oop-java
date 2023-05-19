package ui;

import factory.storage.Storage;

import javax.swing.*;

public class StorageView extends JPanel {
    private final Storage storage;
    private final JLabel storageName;
    private final JLabel totalDetails;
    private final JLabel currentDetails;

    public StorageView(Storage<?> storage, String storageName) {
        this.storage = storage;
        this.storageName = new JLabel(storageName);
        this.totalDetails = new JLabel(String.format("Total: %d", storage.getTotalDetails()));
        this.currentDetails = new JLabel(String.format("Current: %d", storage.getCurrentDetails()));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(this.storageName);
        add(totalDetails);
        add(currentDetails);
    }

    private String buildLabel(String s, int value) {
        return String.format("%s: %d", s, value);
    }

    public void update() {
        updateCurrentDetails();
        updateTotalDetails();
    }

    public void updateTotalDetails() {
        totalDetails.setText(buildLabel("Total", storage.getTotalDetails()));
    }
    public void updateCurrentDetails() {
        currentDetails.setText(buildLabel("Current", storage.getCurrentDetails()));
    }
}
