package models.command.impl.math;

import exceptions.command.ForbiddenOperandsException;
import lombok.extern.log4j.Log4j;

import java.util.Stack;

@Log4j
public class Sqrt extends MathCommand{
    public Sqrt() {
        super(1);
    }

    @Override
    protected void validate(Stack<Double> stack) {
        super.validate(stack);
        if (stack.peek() < 0.0) {
            log.warn("Sqrt command can't be applied to negative numbers");
            throw new ForbiddenOperandsException("SQRT",""+stack.peek(),"Cannot take the square root of a negative number");
        }
    }

    @Override
    protected double performOperation(Double... operands) {
        log.info("Take the square root of " + operands[0]);
        return Math.sqrt(operands[0]);
    }
}
