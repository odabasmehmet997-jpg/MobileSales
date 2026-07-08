package net.objecthunter.exp4j.shuntingyard;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;
import net.objecthunter.exp4j.tokenizer.OperatorToken;
import net.objecthunter.exp4j.tokenizer.Token;
import net.objecthunter.exp4j.tokenizer.Tokenizer;

public class ShuntingYard {
    public static Token[] convertToRPN(String str, Map<String, Function> map, Map<String, Operator> map2, Set<String> set, boolean z) {
        Stack stack = new Stack();
        ArrayList arrayList = new ArrayList();
        Tokenizer tokenizer = new Tokenizer(str, map, map2, set, z);
        while (tokenizer.hasNext()) {
            Token tokenNextToken = tokenizer.nextToken();
            switch (tokenNextToken.getType()) {
                case 1:
                case 6:
                    arrayList.add(tokenNextToken);
                    break;
                case 2:
                    while (!stack.empty() && ((Token) stack.peek()).getType() == 2) {
                        OperatorToken operatorToken = (OperatorToken) tokenNextToken;
                        OperatorToken operatorToken2 = (OperatorToken) stack.peek();
                        if ((operatorToken.getOperator().getNumOperands() != 1 || operatorToken2.getOperator().getNumOperands() != 2) && ((operatorToken.getOperator().isLeftAssociative() && operatorToken.getOperator().getPrecedence() <= operatorToken2.getOperator().getPrecedence()) || operatorToken.getOperator().getPrecedence() < operatorToken2.getOperator().getPrecedence())) {
                            arrayList.add(stack.pop());
                        } else {
                            stack.push(tokenNextToken);
                        }
                        break;
                    }
                    stack.push(tokenNextToken);
                    break;
                case 3:
                    stack.add(tokenNextToken);
                    break;
                case 4:
                    stack.push(tokenNextToken);
                    break;
                case 5:
                    while (((Token) stack.peek()).getType() != 4) {
                        arrayList.add(stack.pop());
                    }
                    stack.pop();
                    if (!stack.isEmpty() && ((Token) stack.peek()).getType() == 3) {
                        arrayList.add(stack.pop());
                    }
                    break;
                case 7:
                    while (!stack.empty() && ((Token) stack.peek()).getType() != 4) {
                        arrayList.add(stack.pop());
                    }
                    if (stack.empty() || ((Token) stack.peek()).getType() != 4) {
                        throw new IllegalArgumentException("Misplaced function separator ',' or mismatched parentheses");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Token type encountered. This should not happen");
            }
        }
        while (!stack.empty()) {
            Token token = (Token) stack.pop();
            if (token.getType() == 5 || token.getType() == 4) {
                throw new IllegalArgumentException("Mismatched parentheses detected. Please check the expression");
            }
            arrayList.add(token);
        }
        return (Token[]) arrayList.toArray(new Token[arrayList.size()]);
    }
}
