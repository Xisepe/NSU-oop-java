package exceptions.command.math;

import exceptions.command.CommandException;

public class NotFiniteOperandException extends CommandException {
    public NotFiniteOperandException(String command) {
        super(command, "Cannot perform operation with not finite operand");
    }
}
