package command;

import exceptions.command.ForbiddenOperandsException;
import exceptions.command.NotEnoughStackElements;
import models.command.Command;
import models.context.Context;
import models.context.DefaultContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import services.impl.SingletonCommandFactory;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class MathCommandTest {
    public static double delta = 0.000000001;
    public static class AddTest{
        private final Stack<Double> stack = new Stack<>();
        private final Context ctx = new DefaultContext.DefaultContextBuilder().with("stack",stack).build();
        private final Command add = SingletonCommandFactory.getInstance().create("+");
        @After
        public void tearDown(){
            stack.clear();
        }
        @Test
        public void testAdd(){
            stack.add(15.0);
            stack.add(-2.4);
            add.execute(ctx,null);
            assertEquals(15.0-2.4,stack.peek(),delta);
        }
        @Test(expected = NotEnoughStackElements.class)
        public void testAddWithEmptyStack(){
            add.execute(ctx,null);
        }
    }
    public static class SubTest{
        private final Stack<Double> stack = new Stack<>();
        private final Context ctx = new DefaultContext.DefaultContextBuilder().with("stack",stack).build();
        private final Command sub = SingletonCommandFactory.getInstance().create("-");
        @After
        public void tearDown(){
            stack.clear();
        }
        @Test
        public void testSub() {
            stack.add(10.0);
            stack.add(20.4);
            sub.execute(ctx,null);
            assertEquals(10.0-20.4,stack.peek(),delta);
        }
        @Test(expected = NotEnoughStackElements.class)
        public void testSubWithEmptyStack(){
            sub.execute(ctx,null);
        }
    }
    public static class MultiplyTest{
        private final Stack<Double> stack = new Stack<>();
        private final Context ctx = new DefaultContext.DefaultContextBuilder().with("stack",stack).build();
        private final Command mul = SingletonCommandFactory.getInstance().create("*");
        @After
        public void tearDown(){
            stack.clear();
        }
        @Test
        public void testMul() {
            stack.add(-121.423);
            stack.add(20.4);
            mul.execute(ctx,null);
            assertEquals(-121.423*20.4,stack.peek(),delta);
        }
        @Test(expected = NotEnoughStackElements.class)
        public void testMulWithEmptyStack(){
            mul.execute(ctx,null);
        }
    }
    public static class DivTest{
        private final Stack<Double> stack = new Stack<>();
        private final Context ctx = new DefaultContext.DefaultContextBuilder().with("stack",stack).build();
        private final Command div = SingletonCommandFactory.getInstance().create("/");
        @After
        public void tearDown(){
            stack.clear();
        }
        @Test
        public void testDiv() {
            stack.add(-121.423);
            stack.add(20.4);
            div.execute(ctx,null);
            assertEquals(-121.423/20.4,stack.peek(),delta);
        }
        @Test(expected = NotEnoughStackElements.class)
        public void testDivWithEmptyStack(){
            div.execute(ctx,null);
        }
        @Test(expected = ForbiddenOperandsException.class)
        public void testDivByZero(){
            stack.add(-121.423);
            stack.add(0.0);
            div.execute(ctx,null);
            assertEquals(stack.size(),2);
        }
    }
    public static class SqrtTest{
        private final Stack<Double> stack = new Stack<>();
        private final Context ctx = new DefaultContext.DefaultContextBuilder().with("stack",stack).build();
        private final Command sqrt = SingletonCommandFactory.getInstance().create("SQRT");
        @After
        public void tearDown(){
            stack.clear();
        }
        @Test
        public void testSqrt() {
            stack.add(121.423);
            sqrt.execute(ctx,null);
            assertEquals(Math.sqrt(121.423),stack.peek(),delta);
        }
        @Test(expected = NotEnoughStackElements.class)
        public void testDivWithEmptyStack(){
            sqrt.execute(ctx,null);
        }
        @Test(expected = ForbiddenOperandsException.class)
        public void testDivByZero(){
            stack.add(-121.423);
            sqrt.execute(ctx,null);
            assertEquals(stack.size(),1);
        }
    }
}
