package models.command.impl.data;

import exceptions.command.ArgumentsValidationException;
import exceptions.context.ContextException;
import lombok.extern.log4j.Log4j;
import models.command.Command;
import models.constants.context.MathContext;
import models.context.Context;

import java.util.Map;

@Log4j
public class Define implements Command {
    @Override
    public void execute(Context context, String args) {
        try {
            if (args == null) {
                throw new ArgumentsValidationException("No arguments provided. At least two argument must be provided.");
            }
            Map<String, Double> defines = (Map<String, Double>) context.getContextElement(MathContext.defines);
            String[] s = args.split(" ");
            if (s.length != 2) {
                log.warn("Define: Wrong number of arguments");
                throw new ArgumentsValidationException("Wrong number of arguments");
            }
            log.info("Trying to define: " + s[0] + " = " + s[1]);
            if (defines.containsKey(s[1])) {
                defines.put(s[0], defines.get(s[1]));
            } else {
                defines.put(s[0], Double.parseDouble(s[1]));
            }
        } catch (ContextException e) {
            log.warn(e.getMessage());
            System.err.println(e.getMessage());
        } catch (ClassCastException e) {
            log.warn("defines is not a Map<String, Double>");
            System.err.println("Косяк со стороны программиста");
        } catch (NumberFormatException e) {
            log.warn("trying to define not a number");
            throw new ArgumentsValidationException("Wrong arguments");
        }
    }
}
