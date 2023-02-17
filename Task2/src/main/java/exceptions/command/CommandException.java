package exceptions.command;

import exceptions.UserException;

public class CommandException extends UserException {
    public CommandException(String command, String message) {
        super(String.format("Cannot execute command:%s\nMessage:\n%s",command,message));
    }
}
