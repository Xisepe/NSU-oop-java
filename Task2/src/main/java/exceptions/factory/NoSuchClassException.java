package exceptions.factory;

public class NoSuchClassException extends FactoryException{
    public NoSuchClassException(String name) {
        super("Cannot create class by name: " + name);
    }
}
