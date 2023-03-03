package exceptions.command;

public class NotEnoughStackElements extends CommandException{
    public NotEnoughStackElements(int required, int current) {
        super("Not enough elements in stack, required: " + required + ", current: " + current);
    }
}
