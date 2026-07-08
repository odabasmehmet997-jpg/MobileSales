package net.objecthunter.exp4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.objecthunter.exp4j.tokenizer.FunctionToken;
import net.objecthunter.exp4j.tokenizer.NumberToken;
import net.objecthunter.exp4j.tokenizer.OperatorToken;
import net.objecthunter.exp4j.tokenizer.Token;
import net.objecthunter.exp4j.tokenizer.VariableToken;

public class Expression {
    private final Token[] tokens;
    private final Set<String> userFunctionNames;
    private final Map<String, Double> variables = createDefaultVariables();
    private static Map<String, Double> createDefaultVariables() {
        HashMap map = new HashMap(4);
        Double dValueOf = Double.valueOf(3.141592653589793d);
        map.put("pi", dValueOf);
        map.put("π", dValueOf);
        map.put("φ", Double.valueOf(1.61803398874d));
        map.put("e", Double.valueOf(2.718281828459045d));
        return map;
    }
    Expression(Token[] tokenArr, Set<String> set) {
        this.tokens = tokenArr;
        this.userFunctionNames = set;
    }
    public double evaluate() {
        ArrayStack arrayStack = new ArrayStack();
        int i2 = 0;
        while (true) {
            Token[] tokenArr = this.tokens;
            if (i2 < tokenArr.length) {
                Token token = tokenArr[i2];
                if (token.getType() == 1) {
                    arrayStack.push(((NumberToken) token).getValue());
                } else if (token.getType() == 6) {
                    String name = ((VariableToken) token).getName();
                    Double d2 = this.variables.get(name);
                    if (d2 == null) {
                        throw new IllegalArgumentException("No value has been set for the setVariable '" + name + "'.");
                    }
                    arrayStack.push(d2.doubleValue());
                } else if (token.getType() == 2) {
                    OperatorToken operatorToken = (OperatorToken) token;
                    if (arrayStack.size() < operatorToken.getOperator().getNumOperands()) {
                        throw new IllegalArgumentException("Invalid number of operands available for '" + operatorToken.getOperator().getSymbol() + "' operator");
                    }
                    if (operatorToken.getOperator().getNumOperands() == 2) {
                        arrayStack.push(operatorToken.getOperator().apply(arrayStack.pop(), arrayStack.pop()));
                    } else if (operatorToken.getOperator().getNumOperands() == 1) {
                        arrayStack.push(operatorToken.getOperator().apply(arrayStack.pop()));
                    }
                } else if (token.getType() == 3) {
                    FunctionToken functionToken = (FunctionToken) token;
                    int numArguments = functionToken.getFunction().getNumArguments();
                    if (arrayStack.size() < numArguments) {
                        throw new IllegalArgumentException("Invalid number of arguments available for '" + functionToken.getFunction().getName() + "' function");
                    }
                    double[] dArr = new double[numArguments];
                    for (int i3 = numArguments - 1; i3 >= 0; i3--) {
                        dArr[i3] = arrayStack.pop();
                    }
                    arrayStack.push(functionToken.getFunction().apply(dArr));
                } else {
                    continue;
                }
                i2++;
            } else {
                if (arrayStack.size() > 1) {
                    throw new IllegalArgumentException("Invalid number of items on the output queue. Might be caused by an invalid number of arguments for a function.");
                }
                return arrayStack.pop();
            }
        }
    }
}
