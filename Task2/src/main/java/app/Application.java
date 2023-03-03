package app;

import exceptions.command.CommandException;
import exceptions.factory.ClassLoadFactoryException;
import exceptions.factory.FactoryException;
import exceptions.factory.NoFactoryConfigException;
import models.command.Command;
import models.constants.context.MathContext;
import models.context.Context;
import models.context.DefaultContext;
import services.Factory;
import services.impl.SingletonCommandFactory;

import java.io.*;
import java.util.*;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = initScanner(args);
        PrintStream printStream = initPrintStream(args);
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
            StringTokenizer tokenizer = new StringTokenizer(line);
            try{
                if (tokenizer.hasMoreTokens()) {
                    cmd = SingletonCommandFactory.getInstance().create(tokenizer.nextToken());
                } else {
                    System.err.println("Command is empty");
                    continue;
                }
            } catch (NoFactoryConfigException | ClassLoadFactoryException e) {
                System.err.println("Error: " + e.getMessage()+"\nCannot continue work.");
                return;
            } catch (FactoryException e) {
                System.err.println(e.getMessage());
                continue;
            }
            try {
                if (tokenizer.hasMoreTokens()) {
                    cmd.execute(context,tokenizer.nextToken());
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
    private static PrintStream initPrintStream(String[] args) {
        PrintStream printStream;
        if (args.length > 1) {
            try {
                printStream = new PrintStream(new FileOutputStream(args[1]));
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + args[1]+" starting in console mode.");
                printStream = System.out;
            }
        } else {
            printStream = System.out;
        }
        return printStream;
    }
}
