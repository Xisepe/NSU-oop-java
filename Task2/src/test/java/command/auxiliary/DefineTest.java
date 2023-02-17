package command.auxiliary;

import exceptions.command.auxiliary.InvalidArgumentsException;
import exceptions.command.auxiliary.NotEnoughArgumentsException;
import models.command.Command;
import models.command.impl.calculator.auxiliary.DefineCommand;
import models.context.CalculatorContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefineTest {
    private static final double DELTA = 0.00000001;
    private Map<String,Double> defines;
    private CalculatorContext context;

    @Before
    public void setUp() {
        defines = new HashMap<>();
        defines.put("x", 1.0);
        defines.put("y", 2.0);
        defines.put("z", 3.0);
        context = new CalculatorContext(new Stack<>(), defines);
    }
    @After
    public void tearDown() {
        defines.clear();
        context= null;
    }
    @Test
    public void testDefineIfAlreadyExist() {
        List<Object> existed = new ArrayList<>();
        existed.add("x");
        double e = 2.71;
        existed.add(e+"");
        Command command = new DefineCommand(context, existed);
        command.execute();
        assertEquals(e, defines.get("x"),DELTA);
    }
    @Test
    public void testDefineIfNotExist() {
        List<Object> existed = new ArrayList<>();
        existed.add("m");
        double e = 2.71;
        existed.add(""+e);
        Command command = new DefineCommand(context, existed);
        command.execute();
        assertTrue(defines.containsKey("m"));
        assertEquals(e, defines.get("m"),DELTA);
    }
    @Test(expected = NotEnoughArgumentsException.class)
    public void testDefineIfNotEnoughArguments() {
        List<Object> existed = new ArrayList<>();
        existed.add("m");
        Command command = new DefineCommand(context, existed);
    }
    @Test(expected = InvalidArgumentsException.class)
    public void testDefineIfInvalidArguments() {
        List<Object> existed = new ArrayList<>();
        existed.add("m");
        existed.add("hello");
        Command command = new DefineCommand(context, existed);
    }
}
