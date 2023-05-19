package ui;

import factory.Factory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FactoryView extends JPanel {
    private final List<StorageView> storageViews = new ArrayList<>();
    private final List<DelayControlView> delayControlViews = new ArrayList<>();
    private final Factory factory;

    public FactoryView(Factory factory) {
        this.factory = factory;
        initViews();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addViews();
    }

    public void update() {
        storageViews.forEach(StorageView::update);
    }

    private void initViews() {
        initStorageViews();
        initDelayControlViews();
    }
    private void addViews() {
        storageViews.forEach(this::add);
        delayControlViews.forEach(this::add);
    }
    private void initStorageViews() {
        storageViews.add(new StorageView(
                factory.getStorageProvider().getBodyStorage(),
                "Body storage"
        ));
        storageViews.add(new StorageView(
                factory.getStorageProvider().getEngineStorage(),
                "Engine storage"
        ));
        storageViews.add(new StorageView(
                factory.getStorageProvider().getAccessoryStorage(),
                "Accessory storage"
        ));
        storageViews.add(new StorageView(
                factory.getStorageProvider().getCarStorage(),
                "Car storage"
        ));
    }
    private void initDelayControlViews() {
        delayControlViews.add(
            new DelayControlView(factory.getDelayProvider().getBodySupplierDelay(), "Body supplier delay")
        );
        delayControlViews.add(
                new DelayControlView(factory.getDelayProvider().getEngineSupplierDelay(), "Engine supplier delay")
        );
        delayControlViews.add(
                new DelayControlView(factory.getDelayProvider().getAccessorySupplierDelay(), "Accessory supplier delay")
        );
        delayControlViews.add(
                new DelayControlView(factory.getDelayProvider().getDealerDelay(), "Dealer delay")
        );
    }
}
