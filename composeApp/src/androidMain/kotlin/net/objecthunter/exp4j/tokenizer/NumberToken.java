package net.objecthunter.exp4j.tokenizer;

public final class NumberToken extends Token {
    private final double value;

    public NumberToken(double d2) {
        super (1);
        value = d2;
    }

    NumberToken(char[] cArr, int i2, int i3) {
        this (Double.parseDouble (String.valueOf (cArr, i2, i3)));
    }

    public double getValue() {
        return value;
    }
}
