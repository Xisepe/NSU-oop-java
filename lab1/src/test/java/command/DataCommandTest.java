package command;

import exceptions.command.ArgumentsValidationException;
import exceptions.command.NotEnoughStackElements;
import models.command.Command;
import models.context.Context;
import models.context.DefaultContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import services.impl.SingletonCommandFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class DataCommandTest {
    public static double delta = 0.000_000_001;
    public static class PushTest{
        private final Stack<Double> stack = new Stack<>();
        private final Map<String, Double> defines = new HashMap<>();
        private final Context ctx = new DefaultContext.DefaultContextBuilder()
                .with("stack",stack)
                .with("defines",defines)
                .build();
        private final Command push = SingletonCommandFactory.getInstance().create("PUSH");
        @Before
        public void setUp(){
            defines.put("a", 123.456);
        }
        @After
        public void tearDown(){
            stack.clear();
            defines.clear();
        }
        @Test
        public void testPush() {
            push.execute(ctx,"121.3");
            assertEquals(121.3, stack.peek(), delta);
        }
        @Test
        public void testPushWithDefine() {
            push.execute(ctx,"a");
            assertEquals(123.456, stack.peek(), delta);
        }
        @Test(expected = ArgumentsValidationException.class)
        public void testPushWithWrongArg() {
            push.execute(ctx,"abcs");
        }
    }
    public static class DefineTest{
        private final Stack<Double> stack = new Stack<>();
        private final Map<String, Double> defines = new HashMap<>();
        private final Context ctx = new DefaultContext.DefaultContextBuilder()
                .with("stack",stack)
                .with("defines",defines)
                .build();
        private final Command define = SingletonCommandFactory.getInstance().create("DEFINE");
        @Before
        public void setUp(){
            defines.put("a", 123.456);
        }
        @After
        public void tearDown(){
            stack.clear();
            defines.clear();
        }
        @Test
        public void testDefine() {
            define.execute(ctx,"b 1000.0");
            assertEquals(1000.0, defines.get("b"), delta);
        }
        @Test
        public void testRedefine() {
            define.execute(ctx,"a 1000.0");
            assertEquals(1000.0, defines.get("a"), delta);
        }
        @Test(expected = ArgumentsValidationException.class)
        public void testDefineWithWrongNotEnoughArgs() {
            define.execute(ctx,"abc");
        }
        @Test(expected = ArgumentsValidationException.class)
        public void testDefineWithWrongArgs() {
            define.execute(ctx,"abc avs");
        }
        @Test
        public void testDefineWithDefine() {
            define.execute(ctx,"b a");
            assertEquals(defines.get("a"), defines.get("b"), delta);
        }
    }
    public static class PopTest{
        private final Stack<Double> stack = new Stack<>();
        private final Map<String, Double> defines = new HashMap<>();
        private final Context ctx = new DefaultContext.DefaultContextBuilder()
                .with("stack",stack)
                .with("defines",defines)
                .build();
        private final Command pop = SingletonCommandFactory.getInstance().create("POP");
        @Before
        public void setUp(){
            defines.put("a", 123.456);
        }
        @After
        public void tearDown(){
            stack.clear();
            defines.clear();
        }
        @Test
        public void testPop() {
            stack.push(121.3);
            pop.execute(ctx, null);
            assertEquals(0,stack.size());
        }
        @Test(expected = NotEnoughStackElements.class)
        public void testPopWithNotEnoughArgs() {
            pop.execute(ctx, null);
        }
    }
}
