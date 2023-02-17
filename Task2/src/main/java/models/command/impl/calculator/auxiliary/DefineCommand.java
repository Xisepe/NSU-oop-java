package models.command.impl.calculator.auxiliary;

import exceptions.command.auxiliary.InvalidArgumentsException;
import exceptions.command.auxiliary.NotEnoughArgumentsException;
import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import models.command.impl.calculator.ArgCalculatorContextCommand;
import models.context.CalculatorContext;

import java.util.List;

@Log4j
public class DefineCommand extends ArgCalculatorContextCommand {
    private final String key;
    private final double val;
    public DefineCommand(@NonNull CalculatorContext context,
                         @NonNull List<Object> args) {
        super(context, args);
        log.info("creating DefineCommand");
        log.info("Validating arguments");
        if (args.size()!= 2) {
            throw new NotEnoughArgumentsException("DEFINE",args.size(),2);
        }
        try{
            this.key = (String)args.get(0);
            this.val = Double.parseDouble((String)args.get(1));
        } catch (ClassCastException | NumberFormatException e) {
            log.warn(e.getMessage());
            throw new InvalidArgumentsException("DEFINE","Invalid arguments");
        }
        log.info("Done validating arguments");
    }

    @Override
    public void execute() {
        log.info("executing DefineCommand");
        context.getDefines().put(key,val);
        log.info(String.format("define %s = %s",key,val));
    }
}
