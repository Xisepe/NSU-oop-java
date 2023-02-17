package models.command.impl.calculator;

import models.context.CalculatorContext;

public abstract class NoArgCalculatorContextCommand extends CalculatorContextCommand {
    public NoArgCalculatorContextCommand(CalculatorContext context) {
        super(context);
    }
}
