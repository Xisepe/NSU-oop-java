package command.auxiliary;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PrintTest.class,
        DefineTest.class,
        PushTest.class,
        PopTest.class
})
public class AuxililaryCommandsTest {
}
