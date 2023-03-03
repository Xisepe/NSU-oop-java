package models.command.impl.math;

import lombok.extern.log4j.Log4j;

@Log4j
public class Multiply extends MathCommand{
    public Multiply() {
        super(2);
    }

    @Override
    protected double performOperation(Double... operands) {
        log.info("Multiplying " + operands[0] + " and " + operands[1]);
        return operands[0] * operands[1];
    }
}
