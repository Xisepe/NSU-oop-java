package models.command.impl.calculator.math;

import lombok.experimental.SuperBuilder;
import models.command.impl.calculator.NoArgCalculatorContextCommand;

import java.util.function.BiFunction;

@SuperBuilder
public abstract class MathBiCommand extends NoArgCalculatorContextCommand {
    protected final BiFunction<Double, Double, Double> function;
}
