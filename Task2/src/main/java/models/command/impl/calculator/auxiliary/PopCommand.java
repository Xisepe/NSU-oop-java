package models.command.impl.calculator.auxiliary;

import exceptions.command.CommandException;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import models.command.impl.calculator.NoArgCalculatorContextCommand;
import models.context.CalculatorContext;

@Log4j
public class PopCommand extends NoArgCalculatorContextCommand {
    public PopCommand(@NonNull CalculatorContext context) {
        super(context);
        log.info("Created PopCommand");
        log.info("Validating PopCommand");
        if (context.getStack().size() == 0) {
            log.warn("PopCommand: Stack is empty");
            throw new CommandException("POP", "Cannot pop from empty stack");
        }
        log.info("Validation complete");
    }

    @Override
    public void execute() {
        log.info("Executing PopCommand");
        context.getStack().pop();
        log.info("PopCommand executed");
    }
}
