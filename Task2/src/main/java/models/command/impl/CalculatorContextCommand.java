package models.command.impl;

import lombok.AllArgsConstructor;
import models.command.Command;
import models.context.CalculatorContext;

@AllArgsConstructor
public abstract class CalculatorContextCommand implements Command {
    protected final CalculatorContext context;
}
