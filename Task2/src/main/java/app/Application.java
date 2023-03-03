package app;

import exceptions.command.CommandException;
import exceptions.factory.FactoryException;
import exceptions.factory.NoSuchClassException;
import models.command.Command;
import models.constants.context.MathContext;
import models.context.Context;
import models.context.DefaultContext;
import services.impl.SingletonCommandFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = initScanner(args);
        Stack<Double> stack = new Stack<>();
        Map<String, Double> defines = new HashMap<>();
        Context context = new DefaultContext.DefaultContextBuilder()
                .with(MathContext.stack, stack)
                .with(MathContext.defines, defines)
                .build();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("#") || line.isEmpty())
                continue;
            Command cmd;
            String[] cmdArgs = line.split(" ", 2);
            try{
                if (cmdArgs.length > 0) {
                    cmd = SingletonCommandFactory.getInstance().create(cmdArgs[0]);
                } else {
                    System.err.println("Command is empty");
                    continue;
                }
            } catch (NoSuchClassException e) {
                System.err.println(e.getMessage());
                continue;
            }
            catch (FactoryException e) {
                System.err.println("Error: " + e.getMessage()+"\nCannot continue work.");
                return;
            }
            try {
                if (cmdArgs.length > 1) {
                    cmd.execute(context,cmdArgs[1]);
                } else {
                    cmd.execute(context, null);
                }
            } catch (CommandException e) {
                System.err.println(e.getMessage());
            }
        }

    }
    private static Scanner initScanner(String[] args) {
        Scanner scanner;
        if (args.length > 0) {
            try {
                scanner = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + args[0]+" starting in console mode.");
                scanner = new Scanner(System.in);
            }
        } else {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
