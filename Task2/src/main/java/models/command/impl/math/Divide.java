package models.command.impl.math;

import exceptions.command.ForbiddenOperandsException;
import lombok.extern.log4j.Log4j;

import java.util.Stack;

@Log4j
public class Divide extends MathCommand{
    public Divide() {
        super(2);
    }

    @Override
    protected void validate(Stack<Double> stack) {
        super.validate(stack);
        if (stack.peek().equals(0.0)) {
            log.warn("Division by zero");
            throw new ForbiddenOperandsException("Divide","0.0", "Cannot divide by zero");
        }
    }

    @Override
    protected double performOperation(Double... operands) {
        log.debug("Dividing " + operands[0] + " by " + operands[1]);
        return operands[0] / operands[1];
    }
}
