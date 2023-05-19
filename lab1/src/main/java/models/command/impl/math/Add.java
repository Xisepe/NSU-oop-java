package models.command.impl.math;

import lombok.extern.log4j.Log4j;

@Log4j
public class Add extends MathCommand {
    public Add() {
        super(2);
    }

    @Override
    protected double performOperation(Double... operands) {
        log.info("Adding " + operands[0] + " and " + operands[1]);
        return operands[0] + operands[1];
    }
}
