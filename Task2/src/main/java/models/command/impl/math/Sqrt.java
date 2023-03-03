package models.command.impl.math;

import lombok.extern.log4j.Log4j;

@Log4j
public class Sqrt extends MathCommand{
    protected Sqrt() {
        super(1);
    }

    @Override
    protected double performOperation(Double... operands) {
        log.info("Take the square root of " + operands[0]);
        return Math.sqrt(operands[0]);
    }
}
