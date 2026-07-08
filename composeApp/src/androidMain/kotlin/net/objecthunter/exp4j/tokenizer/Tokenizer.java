package net.objecthunter.exp4j.tokenizer;

import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.function.Functions;
import net.objecthunter.exp4j.operator.Operator;
import net.objecthunter.exp4j.operator.Operators;

import java.util.Map;
import java.util.Set;

public class Tokenizer {
    private final char[] expression;
    private final int expressionLength;
    private final boolean implicitMultiplication;
    private final Map<String, Function> userFunctions;
    private final Map<String, Operator> userOperators;
    private final Set<String> variableNames;
    private Token lastToken;
    private int pos;
    public Tokenizer(String str, Map<String, Function> map, Map<String, Operator> map2, Set<String> set, boolean z) {
        char[] charArray = str.trim ().toCharArray ();
        this.expression = charArray;
        this.expressionLength = charArray.length;
        this.userFunctions = map;
        this.userOperators = map2;
        this.variableNames = set;
        this.implicitMultiplication = z;
    }
    private static boolean isNumeric(char c2, boolean z) {
        return Character.isDigit (c2) || '.' == c2 || 'e' == c2 || 'E' == c2 || (z && ('-' == c2 || '+' == c2));
    }
    public static boolean isAlphabetic(int i2) {
        return Character.isLetter (i2);
    }
    public static boolean isVariableOrFunctionCharacter(int i2) {
        return isAlphabetic(i2) || Character.isDigit (i2) || 95 == i2 || 46 == i2;
    }
    private boolean isArgumentSeparator(char c2) {
        return ',' == c2;
    }
    private boolean isCloseParentheses(char c2) {
        return ')' == c2 || '}' == c2 || ']' == c2;
    }
    private boolean isOpenParentheses(char c2) {
        return '(' == c2 || '{' == c2 || '[' == c2;
    }
    public boolean hasNext() {
        return this.expression.length > this.pos;
    }
    public Token nextToken() {
        char c2 = this.expression[this.pos];
        while (Character.isWhitespace (c2)) {
            char[] cArr = this.expression;
            int i2 = this.pos + 1;
            this.pos = i2;
            c2 = cArr[i2];
        }
        if (Character.isDigit (c2) || '.' == c2) {
            Token token = this.lastToken;
            if (null != token) {
                if (1 == token.getType ()) {
                    throw new IllegalArgumentException ("Unable to parse char '" + c2 + "' (Code:" + ((int) c2) + ") at [" + this.pos + "]");
                }
                if (this.implicitMultiplication && 2 != lastToken.getType () && 4 != lastToken.getType () && 3 != lastToken.getType () && 7 != lastToken.getType ()) {
                    OperatorToken operatorToken = new OperatorToken (Operators.getBuiltinOperator ('*', 2));
                    this.lastToken = operatorToken;
                    return operatorToken;
                }
            }
            return parseNumberToken(c2);
        }
        if (isArgumentSeparator(c2)) {
            return parseArgumentSeparatorToken(c2);
        }
        if (isOpenParentheses(c2)) {
            Token token2 = this.lastToken;
            if (null != token2 && this.implicitMultiplication && 2 != token2.getType () && 4 != lastToken.getType () && 3 != lastToken.getType () && 7 != lastToken.getType ()) {
                OperatorToken operatorToken2 = new OperatorToken (Operators.getBuiltinOperator ('*', 2));
                this.lastToken = operatorToken2;
                return operatorToken2;
            }
            return parseParentheses(true);
        }
        if (isCloseParentheses(c2)) {
            return parseParentheses(false);
        }
        if (Operator.isAllowedOperatorChar (c2)) {
            return parseOperatorToken(c2);
        }
        if (isAlphabetic(c2) || '_' == c2) {
            Token token3 = this.lastToken;
            if (null != token3 && this.implicitMultiplication && 2 != token3.getType () && 4 != lastToken.getType () && 3 != lastToken.getType () && 7 != lastToken.getType ()) {
                OperatorToken operatorToken3 = new OperatorToken (Operators.getBuiltinOperator ('*', 2));
                this.lastToken = operatorToken3;
                return operatorToken3;
            }
            return parseFunctionOrVariable();
        }
        throw new IllegalArgumentException ("Unable to parse char '" + c2 + "' (Code:" + ((int) c2) + ") at [" + this.pos + "]");
    }
    private Token parseArgumentSeparatorToken(char c2) {
        this.pos++;
        ArgumentSeparatorToken argumentSeparatorToken = new ArgumentSeparatorToken ();
        this.lastToken = argumentSeparatorToken;
        return argumentSeparatorToken;
    }
    private Token parseParentheses(boolean z) {
        if (z) {
            this.lastToken = new OpenParenthesesToken ();
        } else {
            this.lastToken = new CloseParenthesesToken ();
        }
        this.pos++;
        return this.lastToken;
    }
    private Token parseFunctionOrVariable() {
        int i2 = this.pos;
        if (isEndOfExpression(i2)) {
            this.pos++;
        }
        Token token = null;
        int i3 = i2;
        int i4 = 1;
        int i5 = 1;
        while (!isEndOfExpression(i3) && isVariableOrFunctionCharacter(this.expression[i3])) {
            String str = new String (this.expression, i2, i5);
            Set<String> set = this.variableNames;
            if (null != set && set.contains (str)) {
                token = new VariableToken (str);
            } else {
                Function function = getFunction(str);
                if (null != function) {
                    token = new FunctionToken (function);
                } else {
                    i5++;
                    i3 = (i2 + i5) - 1;
                }
            }
            i4 = i5;
            i5++;
            i3 = (i2 + i5) - 1;
        }
        if (null == token) {
            throw new UnknownFunctionOrVariableException (new String (this.expression), this.pos, i5);
        }
        this.pos += i4;
        this.lastToken = token;
        return token;
    }
    private Function getFunction(String str) {
        Map<String, Function> map = this.userFunctions;
        Function function = null != map ? map.get (str) : null;
        return null == function ? Functions.getBuiltinFunction (str) : function;
    }
    private Token parseOperatorToken(char c2) {
        Operator operator;
        int i2 = this.pos;
        StringBuilder sb = new StringBuilder ();
        sb.append (c2);
        int i3 = 1;
        while (true) {
            int i4 = i2 + i3;
            if (isEndOfExpression(i4) || !Operator.isAllowedOperatorChar (this.expression[i4])) {
                break;
            }
            i3++;
            sb.append (this.expression[i4]);
        }
        while (true) {
            if (0 >= sb.length ()) {
                operator = null;
                break;
            }
            operator = getOperator(sb.toString ());
            if (null != operator) {
                break;
            }
            sb.setLength (sb.length () - 1);
        }
        this.pos += sb.length ();
        OperatorToken operatorToken = new OperatorToken (operator);
        this.lastToken = operatorToken;
        return operatorToken;
    }
    private Operator getOperator(String str) {
        final int type = 0;
        Map<String, Operator> map = this.userOperators;
        Operator operator = null != map ? map.get (str) : null;
        if (null != operator) {
            return operator;
        }
        int i2 = 1;
        if (1 != str.length ()) {
            return operator;
        }
        Token token = this.lastToken;
        if (null != token && type != token.getType () && 7 != type) {
            if (2 == type) {
                Operator operator2 = ((OperatorToken) this.lastToken).getOperator ();
                if (2 != operator2.getNumOperands ()) {
                    if (1 == operator2.getNumOperands ()) {
                    }
                }
            }
            i2 = 2;
        }
        return Operators.getBuiltinOperator (str.charAt (0), i2);
    }
    private Token parseNumberToken(char c2) {
        int i2;
        int i3 = this.pos;
        this.pos = i3 + 1;
        if (isEndOfExpression(i3 + 1)) {
            NumberToken numberToken = new NumberToken (Double.parseDouble (String.valueOf (c2)));
            this.lastToken = numberToken;
            return numberToken;
        }
        int i4 = 1;
        while (true) {
            i2 = i3 + i4;
            if (!isEndOfExpression(i2)) {
                char[] cArr = this.expression;
                char c3 = cArr[i2];
                char c4 = cArr[i2 - 1];
                if (!isNumeric(c3, 'e' == c4 || 'E' == c4)) {
                    break;
                }
                i4++;
                this.pos++;
            } else {
                break;
            }
        }
        char[] cArr2 = this.expression;
        char c5 = cArr2[i2 - 1];
        if ('e' == c5 || 'E' == c5) {
            i4--;
            this.pos--;
        }
        NumberToken numberToken2 = new NumberToken (cArr2, i3, i4);
        this.lastToken = numberToken2;
        return numberToken2;
    }
    private boolean isEndOfExpression(int i2) {
        return this.expressionLength <= i2;
    }
}
