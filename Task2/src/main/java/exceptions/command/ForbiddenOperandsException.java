package exceptions.command;

public class ForbiddenOperandsException extends CommandException{
    public ForbiddenOperandsException(String operation, String operands, String msg) {
        super("Cannot perform operation: "+ operation + " with operands: " + operands + "\nMessage: " + msg);
    }
}
