package models.context;

import lombok.Data;
import lombok.NonNull;

import java.util.Map;
import java.util.Stack;

@Data
public class CalculatorContext {
    @NonNull
    private final Stack<Double> stack;
    @NonNull
    private final Map<String, Double> defines;
}

