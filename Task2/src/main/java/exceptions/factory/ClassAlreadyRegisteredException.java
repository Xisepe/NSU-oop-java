package exceptions.factory;

public class ClassAlreadyRegisteredException extends FactoryException{
    public ClassAlreadyRegisteredException(String className) {
        super("Class " + className + " is already registered");
    }
}
