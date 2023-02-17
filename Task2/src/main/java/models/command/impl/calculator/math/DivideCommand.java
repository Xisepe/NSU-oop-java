package models.command.impl.calculator.math;

import exceptions.command.DivisionByZero;
import lombok.NonNull;
import models.context.CalculatorContext;

public class DivideCommand extends MathBiCommand{
    public DivideCommand(@NonNull CalculatorContext context) {
        super(context, (a,b)->a/b, "/");
        if (second == 0.0) {
            throw new DivisionByZero();
        }
    }
}
