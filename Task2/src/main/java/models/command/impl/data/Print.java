package models.command.impl.data;

import exceptions.context.ContextException;
import lombok.extern.log4j.Log4j;
import models.command.impl.AbstractCommand;
import models.constants.context.MathContext;
import models.context.Context;

import java.util.Stack;

@Log4j
public class Print extends AbstractCommand {
    public Print() {
        super(1);
    }
    @Override
    public void execute(Context context, String args) {
        try {
            Stack<Double> stack = (Stack<Double>) context.getContextElement(MathContext.stack);
            validate(stack);
            log.info("Trying to print top of stack");
            System.out.println(stack.peek());
        } catch (ContextException e) {
            log.warn(e.getMessage());
            System.err.println(e.getMessage());
        } catch (ClassCastException e) {
            log.warn("stack is not a Stack<Double>");
            System.err.println("Косяк со стороны программиста");
        }
    }

}
