package models.command.impl.data;

import exceptions.command.ArgumentsValidationException;
import exceptions.context.ContextException;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import models.command.Command;
import models.constants.context.MathContext;
import models.context.Context;

import java.util.Map;
import java.util.Stack;

@Log4j
@NoArgsConstructor
public class Push implements Command {
    @Override
    public void execute(Context context, String args) {
        try {
            if (args == null) {
                throw new ArgumentsValidationException("No arguments provided. At least one argument must be provided.");
            }
            Stack<Double> stack = (Stack<Double>) context.getContextElement(MathContext.stack);
            Map<String, Double> defines = (Map<String, Double>) context.getContextElement(MathContext.defines);
            log.info("Pushing " + args);
            if (!defines.containsKey(args)) {
                stack.push(Double.parseDouble(args));
            } else {
                stack.push(defines.get(args));
            }
        } catch (ContextException e) {
            log.warn(e.getMessage());
            System.err.println(e.getMessage());
        } catch (ClassCastException e) {
            log.warn("Wrong context element type");
            System.err.println("Косяк со стороны программиста");
        } catch (NumberFormatException e) {
            log.warn("Args: " + args + " is not a number");
            throw new ArgumentsValidationException("Wrong arguments");
        }
    }
}
