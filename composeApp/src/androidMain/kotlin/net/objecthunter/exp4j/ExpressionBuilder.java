package net.objecthunter.exp4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.function.Functions;
import net.objecthunter.exp4j.operator.Operator;
import net.objecthunter.exp4j.shuntingyard.ShuntingYard;

public class ExpressionBuilder {
    private final String expression;
    private final boolean implicitMultiplication = true;
    private final Map<String, Function> userFunctions;
    private final Map<String, Operator> userOperators;
    private final Set<String> variableNames;
    public ExpressionBuilder(String str) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("Expression can not be empty");
        }
        this.expression = str;
        this.userOperators = new HashMap(4);
        this.userFunctions = new HashMap(4);
        this.variableNames = new HashSet(4);
    }
    public Expression build() {
        if (this.expression.length() == 0) {
            throw new IllegalArgumentException("The expression can not be empty");
        }
        this.variableNames.add("pi");
        this.variableNames.add("π");
        this.variableNames.add("e");
        this.variableNames.add("φ");
        for (String str : this.variableNames) {
            if (Functions.getBuiltinFunction(str) != null || this.userFunctions.containsKey(str)) {
                throw new IllegalArgumentException("A variable can not have the same name as a function [" + str + "]");
            }
        }
        return new Expression(ShuntingYard.convertToRPN(this.expression, this.userFunctions, this.userOperators, this.variableNames, this.implicitMultiplication), this.userFunctions.keySet());
    }
}
