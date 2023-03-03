package exceptions.factory;

public class NoFactoryConfigException extends FactoryException{
    public NoFactoryConfigException(String configName) {
        super("Cannot open factory configuration '" + configName + "'.");
    }
}
