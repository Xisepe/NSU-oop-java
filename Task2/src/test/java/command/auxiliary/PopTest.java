package command.auxiliary;

import exceptions.command.CommandException;
import models.command.Command;
import models.command.impl.calculator.auxiliary.PopCommand;
import models.context.CalculatorContext;
import org.junit.After;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

import static org.junit.Assert.assertTrue;

public class PopTest {
    private final Stack<Double> stack = new Stack<>();
    private final CalculatorContext context = new CalculatorContext(stack,new HashMap<>());

    @Test
    public void testPop() {
        stack.push(10.2);
        Command command = new PopCommand(context);
        command.execute();
        assertTrue(stack.isEmpty());
    }
    @Test(expected = CommandException.class)
    public void testPopException() {
        Command command = new PopCommand(context);
    }
}
