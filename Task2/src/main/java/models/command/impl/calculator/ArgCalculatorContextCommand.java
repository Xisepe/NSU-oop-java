package models.command.impl.calculator;

import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public abstract class ArgCalculatorContextCommand extends CalculatorContextCommand{
    private final List<Object> args;
}
