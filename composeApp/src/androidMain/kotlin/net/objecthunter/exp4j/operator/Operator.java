package net.objecthunter.exp4j.operator;

public abstract class Operator {
    public static final char[] ALLOWED_OPERATOR_CHARS = {'+', '-', '*', '/', '%', '^', '!', '#', 167, '$', '&', ';', ':', '~', '<', '>', '|', '='};
    protected final boolean leftAssociative;
    protected final int numOperands;
    protected final int precedence;
    protected final String symbol;
    public abstract double apply(double... dArr);
    public Operator(String str, int i2, boolean z, int i3) {
        this.numOperands = i2;
        this.leftAssociative = z;
        this.symbol = str;
        this.precedence = i3;
    }
    public static boolean isAllowedOperatorChar(char c2) {
        for (char c3 : ALLOWED_OPERATOR_CHARS) {
            if (c2 == c3) {
                return true;
            }
        }
        return false;
    }
    public boolean isLeftAssociative() {
        return this.leftAssociative;
    }
    public int getPrecedence() {
        return this.precedence;
    }
    public String getSymbol() {
        return this.symbol;
    }
    public int getNumOperands() {
        return this.numOperands;
    }
}
