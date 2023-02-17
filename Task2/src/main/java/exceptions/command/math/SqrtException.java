package exceptions.command.math;

import exceptions.command.CommandException;

public class SqrtException extends CommandException {
    public SqrtException() {
        super("SQRT", "Cannot calculate the square root of a negative number");
    }
}
