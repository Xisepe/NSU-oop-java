package services.impl;

import exceptions.factory.*;
import lombok.extern.log4j.Log4j;
import models.command.Command;
import services.Factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Log4j
public class SingletonCommandFactory implements Factory<Command> {
    private static final String COMMAND_FACTORY_PROPERTIES = "command.factory.properties";
    private static SingletonCommandFactory instance;
    private final Map<String, Class<? extends Command>> creationRegistry;
    private final Map<String, Command> singletonInstances;

    private SingletonCommandFactory() {
        creationRegistry = new HashMap<>();
        singletonInstances = new HashMap<>();
        initialize();
    }

    private void initialize() {
        log.info("Initializing command factory");
        Properties properties = new Properties();
        try {
            properties.load(
                    Thread
                            .currentThread()
                            .getContextClassLoader()
                            .getResourceAsStream(COMMAND_FACTORY_PROPERTIES)
            );
        } catch (IOException e) {
            log.warn("Cannot open properties file: " + COMMAND_FACTORY_PROPERTIES);
            throw new NoFactoryConfigException(COMMAND_FACTORY_PROPERTIES);
        }
        properties.forEach((key, value) -> {
            try {
                creationRegistry.put((String) key, (Class<Command>) Class.forName((String) value));
            } catch (ClassNotFoundException e) {
                log.warn("Cannot find class: " + (String) value);
                throw new ClassLoadFactoryException((String) value);
            }
        });
    }

    public static SingletonCommandFactory getInstance() {
        if (instance == null) {
            instance = new SingletonCommandFactory();
        }
        return instance;
    }

    @Override
    public void register(String name, Class<Command> commandClass) {
        if (creationRegistry.containsKey(name)) {
            log.warn("Cannot register command: " + name);
            throw new ClassAlreadyRegisteredException(commandClass.getName());
        }
        creationRegistry.put(name, commandClass);
    }

    @Override
    public Command create(String name) {
        log.info("Creating command: " + name);
        return singletonInstances.computeIfAbsent(name, key -> {
            Class<? extends Command> aClass = creationRegistry.get(name);
            if (aClass == null) {
                log.warn("Cannot find class: " + name);
                throw new NoSuchClassException(name);
            }
            Command command = null;
            try {
                command = aClass.getConstructor(null).newInstance(null);
            } catch (NoSuchMethodException e) {
                log.warn("No empty constructor for: " + aClass.getName());
                throw new ClassCreationException("No empty constructor for class: " + aClass.getName());
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                log.warn("Cannot create instance of: " + aClass.getName());
                throw new ClassCreationException("Cannot create instance of class: " + aClass.getName());
            }
            return command;
        });
    }
}
