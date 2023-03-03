package exceptions.factory;

public class NoSuchClassException extends FactoryException{
    public NoSuchClassException(String name) {
        super("Cannot create command by name: " + name);
    }
}
