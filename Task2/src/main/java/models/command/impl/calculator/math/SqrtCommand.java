package models.command.impl.calculator.math;

import exceptions.command.SqrtException;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import models.context.CalculatorContext;

@Log4j
public class SqrtCommand extends MathUnaryCommand{
    public SqrtCommand(@NonNull CalculatorContext context) {
        super(context, Math::sqrt,"SQRT");
        if (val < 0.0) {
            log.warn("Sqrt command can't be applied to negative numbers");
            throw new SqrtException();
        }
    }
}
