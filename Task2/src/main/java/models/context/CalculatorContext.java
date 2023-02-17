package models.context;

import lombok.Data;

import java.util.Map;
import java.util.Stack;

@Data
public class CalculatorContext {
    private final Stack<Double> stack;
    private final Map<String, Double> defines;
}

