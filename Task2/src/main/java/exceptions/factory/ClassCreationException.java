package exceptions.factory;

public class ClassCreationException extends FactoryException{
    public ClassCreationException(String message) {
        super("Cannot create class: " + message);
    }
}
