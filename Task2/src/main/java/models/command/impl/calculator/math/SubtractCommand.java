package models.command.impl.calculator.math;

import lombok.NonNull;
import models.context.CalculatorContext;

public class SubtractCommand extends MathBiCommand{
    public SubtractCommand(@NonNull CalculatorContext context) {
        super(context, (a,b)->a-b, "-");
    }
}
