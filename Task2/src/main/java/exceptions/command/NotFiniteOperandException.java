package exceptions.command;

public class NotFiniteOperandException extends CommandException{
    public NotFiniteOperandException(String command) {
        super(command, "Cannot perform operation with not finite operand");
    }
}
