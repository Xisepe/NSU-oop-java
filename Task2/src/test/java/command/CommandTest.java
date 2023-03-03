package command;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MathCommandTest.class,
        DataCommandTest.class
})
public class CommandTest {
}
