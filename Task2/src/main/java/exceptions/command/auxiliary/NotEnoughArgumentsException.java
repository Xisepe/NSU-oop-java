package exceptions.command.auxiliary;

import exceptions.command.CommandException;

public class NotEnoughArgumentsException extends CommandException {
    public NotEnoughArgumentsException(String command, int current, int required) {
        super(command, String.format("Not enough arguments for command '%s'. Current arguments: %d, Required arguments:%s", command, current, required));
    }
}
