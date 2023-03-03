package models.command.impl;

import exceptions.command.NotEnoughStackElements;
import lombok.extern.log4j.Log4j;
import models.command.Command;

import java.util.Stack;

@Log4j
public abstract class AbstractCommand implements Command {
    protected final int operandsNumber;

    protected AbstractCommand(int operandsNumber) {
        this.operandsNumber = operandsNumber;
    }

    protected void validate(Stack<Double> stack) {
        log.info("validating");
        if (stack.size() < operandsNumber) {
            log.warn("Not enough elements in the stack. Throwing NotEnoughStackElements exception");
            throw new NotEnoughStackElements(operandsNumber, stack.size());
        }
    }
}
