package models.command.impl.calculator.math;

import exceptions.command.math.NotEnoughOperandsException;
import exceptions.command.math.NotFiniteOperandException;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import models.command.impl.calculator.NoArgCalculatorContextCommand;
import models.context.CalculatorContext;

import java.util.Stack;
import java.util.function.BiFunction;

@Log4j
public class MathBiCommand extends NoArgCalculatorContextCommand {
    private final String commandName;
    protected final BiFunction<Double, Double, Double> function;
    protected final double first;
    protected final double second;
    public MathBiCommand(@NonNull CalculatorContext context,
                         @NonNull BiFunction<Double, Double, Double> function,
                         @NonNull String commandName) {
        super(context);
        this.commandName = commandName;
        log.info(String.format("Creating MathBiCommand with command name %s", commandName));
        log.info("Performing validation...");
        Stack<Double> stack = context.getStack();
        if (stack.size() < 2) {
            log.warn(String.format("Not enough operands for command %s", commandName));
            throw new NotEnoughOperandsException(commandName, stack.size(),2);
        }
        this.second = stack.pop();
        this.first = stack.pop();
        checkOperands();
        this.function = function;
        log.info("Validation complete");
    }

    private void checkOperands() {
        if (!Double.isFinite(second) || !Double.isFinite(first)) {
            log.warn(String.format("Invalid current stack element for command %s", commandName));
            throw new NotFiniteOperandException(commandName);
        }
    }

    @Override
    public void execute() {
        log.info(String.format("Executing MathBiCommand with command name %s", commandName));
        double res = function.apply(first, second);
        log.info(String.format("Result of MathBiCommand with command name %s is %f", commandName, res));
        context.getStack().push(res);
    }
}
