package exceptions.command.math;

import exceptions.command.CommandException;

public class DivisionByZero extends CommandException {
    public DivisionByZero() {
        super("/","Cannot divide by zero");
    }
}
