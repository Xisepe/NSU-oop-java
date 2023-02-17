package models.command.impl.calculator;

import models.context.CalculatorContext;

import java.util.List;


public abstract class ArgCalculatorContextCommand extends CalculatorContextCommand{
    private final List<Object> args;

    public ArgCalculatorContextCommand(CalculatorContext context, List<Object> args) {
        super(context);
        this.args = args;
    }
}
