package exceptions.command;

public class ArgumentsValidationException extends CommandException{
    public ArgumentsValidationException(String message) {
        super(message);
    }
}
