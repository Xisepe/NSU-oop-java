package exceptions.command;

public class DivisionByZero extends CommandException{
    public DivisionByZero() {
        super("/","Cannot divide by zero");
    }
}
