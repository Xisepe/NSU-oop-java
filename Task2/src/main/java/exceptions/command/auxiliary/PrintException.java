package exceptions.command.auxiliary;

import exceptions.command.CommandException;

public class PrintException extends CommandException {
    public PrintException(String message) {
        super("PRINT", message);
    }
}
