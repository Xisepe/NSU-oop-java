package exceptions.context;

public class NoSuchContextElement extends ContextException{
    public NoSuchContextElement(String elementName) {
        super("No such context element: " + elementName);
    }
}
