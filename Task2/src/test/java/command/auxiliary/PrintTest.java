package command.auxiliary;

import exceptions.command.auxiliary.PrintException;
import models.command.Command;
import models.command.impl.calculator.auxiliary.PrintCommand;
import models.context.CalculatorContext;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class PrintTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void testPrint() {
        System.setOut(new PrintStream(outContent));
        Stack<Double> stack = new Stack<>();
        stack.push(124.123);
        CalculatorContext context = new CalculatorContext(stack, new HashMap<>());
        Command command = new PrintCommand(context);
        command.execute();
        assertEquals("124.123\r\n", outContent.toString());
    }
    @Test(expected = PrintException.class)
    public void testPrintException() {
        Stack<Double> stack = new Stack<>();
        CalculatorContext context = new CalculatorContext(stack, new HashMap<>());
        Command command = new PrintCommand(context);
        command.execute();
    }
    @Test(expected = NullPointerException.class)
    public void testNullPointerException() {
        Command command = new PrintCommand(null);
        command.execute();
    }
}
