package models.command.impl.calculator.math;

import lombok.NonNull;
import models.context.CalculatorContext;

public class MultiplyCommand extends MathBiCommand{
    public MultiplyCommand(@NonNull CalculatorContext context) {
        super(context,(a,b)->a*b, "*");
    }
}
