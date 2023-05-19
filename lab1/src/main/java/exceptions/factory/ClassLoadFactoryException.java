package exceptions.factory;

public class ClassLoadFactoryException extends FactoryException{
    public ClassLoadFactoryException(String className) {
        super("Cannot load class: " + className);
    }
}
