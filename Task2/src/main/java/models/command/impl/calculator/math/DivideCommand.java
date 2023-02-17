package models.command.impl.calculator.math;

import exceptions.command.math.DivisionByZero;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import models.context.CalculatorContext;

@Log4j
public class DivideCommand extends MathBiCommand{
    public DivideCommand(@NonNull CalculatorContext context) {
        super(context, (a,b)->a/b, "/");
        if (second == 0.0) {
            log.warn("Division by zero");
            throw new DivisionByZero();
        }
    }
}
