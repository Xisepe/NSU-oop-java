package factory;

import lombok.SneakyThrows;
import models.command.Command;
import models.command.impl.data.Define;
import models.command.impl.data.Pop;
import models.command.impl.data.Print;
import models.command.impl.data.Push;
import models.command.impl.math.*;
import org.junit.Test;
import services.impl.SingletonCommandFactory;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class FactoryTest {
    @Test
    public void testGettingInstance() {
        SingletonCommandFactory i1 = SingletonCommandFactory.getInstance();
        SingletonCommandFactory i2 = SingletonCommandFactory.getInstance();
        assertSame(i1, i2);
    }
    @SneakyThrows
    @Test
    public void testRegister() {
        SingletonCommandFactory.getInstance().register("test", (Class<Command>) Class.forName("models.command.impl.data.Print"));
    }
    @Test
    public void testCreateAdd() {
        assertTrue(SingletonCommandFactory.getInstance().create("+") instanceof Add);
    }
    @Test
    public void testCreateSubtract() {
        assertTrue(SingletonCommandFactory.getInstance().create("-") instanceof Subtract);
    }
    @Test
    public void testCreateMultiply() {
        assertTrue(SingletonCommandFactory.getInstance().create("*") instanceof Multiply);
    }
    @Test
    public void testCreateDivide() {
        assertTrue(SingletonCommandFactory.getInstance().create("/") instanceof Divide);
    }
    @Test
    public void testCreateSqrt() {
        assertTrue(SingletonCommandFactory.getInstance().create("SQRT") instanceof Sqrt);
    }
    @Test
    public void testCreatePop() {
        assertTrue(SingletonCommandFactory.getInstance().create("POP") instanceof Pop);
    }
    @Test
    public void testCreatePush() {
        assertTrue(SingletonCommandFactory.getInstance().create("PUSH") instanceof Push);
    }
    @Test
    public void testCreatePrint() {
        assertTrue(SingletonCommandFactory.getInstance().create("PRINT") instanceof Print);
    }
    @Test
    public void testCreateDefine() {
        assertTrue(SingletonCommandFactory.getInstance().create("DEFINE") instanceof Define);
    }

}
