package exceptions.command.auxiliary;

import exceptions.command.CommandException;

public class InvalidArgumentsException extends CommandException {
    public InvalidArgumentsException(String command, String message) {
        super(command, message);
    }
}
