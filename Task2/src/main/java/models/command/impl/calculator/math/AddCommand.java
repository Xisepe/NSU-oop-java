package models.command.impl.calculator.math;

import lombok.NonNull;
import models.context.CalculatorContext;

public class AddCommand extends MathBiCommand{
    public AddCommand(@NonNull CalculatorContext context) {
        super(context, Double::sum, "+");
    }
}
