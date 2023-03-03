package models.command.impl.data;

import exceptions.command.ArgumentsValidationExceprion;
import exceptions.context.ContextException;
import lombok.extern.log4j.Log4j;
import models.command.impl.AbstractCommand;
import models.constants.context.MathContext;
import models.context.Context;

import java.util.Stack;
@Log4j
public class Pop extends AbstractCommand {
    public Pop() {
        super(1);
    }
    @Override
    public void execute(Context context, String args) {
        try {
            Stack<Double> stack = (Stack<Double>) context.getContextElement(MathContext.stack);
            validate(stack);
            log.info("Trying to pop from stack");
            stack.pop();
        } catch (ContextException e) {
            log.warn(e.getMessage());
            System.err.println(e.getMessage());
        } catch (ClassCastException e) {
            log.warn("Wrong context element type");
            System.err.println("Косяк со стороны программиста");
        }
    }
}
