package models.command.impl.calculator.math;

import exceptions.command.NotEnoughOperandsException;
import exceptions.command.NotFiniteOperandException;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import models.command.impl.calculator.NoArgCalculatorContextCommand;
import models.context.CalculatorContext;

import java.util.function.Function;

@Log4j
public class MathUnaryCommand extends NoArgCalculatorContextCommand {
    private final Function<Double,Double> function;
    protected final double val;
    public MathUnaryCommand(@NonNull  CalculatorContext context,
                            @NonNull Function<Double, Double> function,
                            @NonNull String commandName) {
        super(context);
        log.info(String.format("Creating MathUnaryCommand with commandName %s", commandName));
        this.function = function;
        log.info("Validating command...");
        if (context.getStack().size() < 1) {
            log.warn(String.format("%s: stack is empty", commandName));
            throw new NotEnoughOperandsException(commandName, context.getStack().size(),1);
        }
        this.val = context.getStack().pop();
        if (!Double.isFinite(val)) {
            log.warn(String.format("Invalid current stack element for command %s", commandName));
            throw new NotFiniteOperandException(commandName);
        }
        log.info("Validation complete");
    }

    @Override
    public void execute() {
        context
                .getStack()
                .push(function.apply(val));
    }
}
