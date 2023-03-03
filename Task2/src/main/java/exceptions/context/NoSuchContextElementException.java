package exceptions.context;

public class NoSuchContextElementException extends ContextException{
    public NoSuchContextElementException(String elementName) {
        super("No such context element: " + elementName);
    }
}
