package services.factory.impl;

import models.command.Command;
import services.factory.F;
import services.factory.Factory;

import java.io.FileInputStream;
import java.util.*;

public class CommandFactory implements Factory {
    public static final String PROPERTY_FILE_KEY = "command.factory.properties";
    private static Factory instance;
    private final Map<String, Class> classes = new TreeMap<>();

    private CommandFactory() {
        try {
            initFromSystemProperties();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getClass().getName() + ": " +
                                       ex.getMessage());
        }
    }

    @Override
    public Command newInstance(String name) {
        return null;
    }

    private void initFromSystemProperties() throws Exception {
        ResourceBundle rb = ResourceBundle.getBundle(PROPERTY_FILE_KEY);
        while (rb.getKeys().hasMoreElements()) {
            String key = rb.getKeys().nextElement();
            String val = rb.getString(key);
            Class cls = Class.forName(val);
            classes.put(key, cls);
        }
    }
}
