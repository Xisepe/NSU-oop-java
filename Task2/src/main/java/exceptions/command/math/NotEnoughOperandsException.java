package exceptions.command.math;

import exceptions.command.CommandException;

public class NotEnoughOperandsException extends CommandException {
    public NotEnoughOperandsException(String command, int current, int required) {
        super(command, String.format("Not enough operands for command '%s'. Required %d, current %d", command, required));
    }
}
