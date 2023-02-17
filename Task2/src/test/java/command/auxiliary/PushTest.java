package command.auxiliary;

import exceptions.command.auxiliary.InvalidArgumentsException;
import exceptions.command.auxiliary.NotEnoughArgumentsException;
import models.command.Command;
import models.command.impl.calculator.auxiliary.PushCommand;
import models.context.CalculatorContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class PushTest {
    private static final double DELTA = 0.0000001;
    private final Stack<Double> stack = new Stack<>();
    private final Map<String, Double> defines = new HashMap<>();
    private final CalculatorContext context = new CalculatorContext(stack, defines);

    {
        defines.put("pi", Math.PI);
    }

    @After
    public void tearDown() throws Exception {
        stack.clear();
    }

    @Test
    public void testPushDouble() {
        double d = 2.71;
        List<Object> list = new ArrayList<>();
        list.add(d + "");
        Command c = new PushCommand(context, list);
        c.execute();
        assertEquals(2.71, stack.peek(), DELTA);
    }

    @Test
    public void testPushDefine() {
        List<Object> list = new ArrayList<>();
        list.add("pi");
        Command c = new PushCommand(context, list);
        c.execute();
        assertEquals(Math.PI, stack.peek(), DELTA);
    }

    @Test(expected = NotEnoughArgumentsException.class)
    public void testPushNotEnoughArguments() {
        List<Object> list = new ArrayList<>();
        Command c = new PushCommand(context, list);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void testPushInvalidArguments() {
        List<Object> list = new ArrayList<>();
        list.add("invalid");
        Command c = new PushCommand(context, list);
    }


}
