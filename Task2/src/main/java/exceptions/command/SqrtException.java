package exceptions.command;

public class SqrtException extends CommandException{
    public SqrtException() {
        super("SQRT", "Cannot calculate the square root of a negative number");
    }
}
