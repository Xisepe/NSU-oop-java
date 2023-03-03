package models.command.impl.math;

import exceptions.context.ContextException;
import lombok.extern.log4j.Log4j;
import models.command.impl.AbstractCommand;
import models.constants.context.MathContext;
import models.context.Context;

import java.util.Stack;

@Log4j
public abstract class MathCommand extends AbstractCommand {
    protected MathCommand(int operandsNumber) {
        super(operandsNumber);
    }
    protected abstract double performOperation(Double... operands);
    @Override
    public void execute(Context context, String args) {
        try {
            Stack<Double> stack = (Stack<Double>) context.getContextElement(MathContext.stack);
            validate(stack);
            stack.push(performOperation(stack.pop(), stack.pop()));
        } catch (ContextException e) {
            log.warn(e.getMessage());
            System.err.println(e.getMessage());
        } catch (ClassCastException e) {
            log.warn("stack is not a Stack<Double>");
            System.err.println("Косяк со стороны программиста");
        }
    }
}
