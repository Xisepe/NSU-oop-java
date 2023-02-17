package models.command.impl.calculator.auxiliary;

import exceptions.command.auxiliary.PrintException;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import models.command.impl.calculator.NoArgCalculatorContextCommand;
import models.context.CalculatorContext;

@Log4j
public class PrintCommand extends NoArgCalculatorContextCommand {
    public PrintCommand(@NonNull CalculatorContext context) {
        super(context);
        log.info("Creating PrintCommand");
        log.info("Validating PrintCommand");
        if (context.getStack().isEmpty()) {
            log.warn("Stack is empty");
            throw new PrintException("Stack is empty");
        }
        log.info("Validations complete");
    }

    @Override
    public void execute() {
        log.info("Executing PrintCommand");
        System.out.println(context.getStack().peek());
        log.info("Executed PrintCommand");
    }
}
