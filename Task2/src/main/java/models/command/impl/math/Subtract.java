package models.command.impl.math;

import lombok.extern.log4j.Log4j;

@Log4j
public class Subtract extends MathCommand{
    public Subtract() {
        super(2);
    }

    @Override
    protected double performOperation(Double... operands) {
        log.info("Subtracting " + operands[0] + " - " + operands[1]);
        return operands[0] - operands[1];
    }
}
