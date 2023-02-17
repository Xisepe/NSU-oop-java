package models.command.impl.calculator.auxiliary;

import exceptions.command.auxiliary.InvalidArgumentsException;
import exceptions.command.auxiliary.NotEnoughArgumentsException;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import models.command.impl.calculator.ArgCalculatorContextCommand;
import models.context.CalculatorContext;

import java.util.List;

@Log4j
public class PushCommand extends ArgCalculatorContextCommand {
    private final double value;

    public PushCommand(@NonNull CalculatorContext context,
                       @NonNull List<Object> args) {
        super(context, args);
        log.info("Creating PushCommand");
        log.info("Validating args");
        if (args.size() < 1) {
            log.warn("Not enough arguments");
            throw new NotEnoughArgumentsException("PushCommand", 0, 1);
        }
        try{
            if (context.getDefines().containsKey((String) args.get(0))) {
                this.value = context.getDefines().get((String) args.get(0));
            } else {
                this.value = Double.parseDouble((String)args.get(0));
            }
        } catch (ClassCastException| NumberFormatException e) {
            log.warn("Invalid argument");
            throw new InvalidArgumentsException("PushCommand","Invalid argument");
        }
        log.info("Validation complete");

    }

    @Override
    public void execute() {
        log.info("Executing PushCommand");
        context.getStack().push(value);
        log.info("PushCommand executed");
    }
}
