package net.objecthunter.exp4j.tokenizer;

import net.objecthunter.exp4j.operator.Operator;

public class OperatorToken extends Token {
    private final Operator operator;
    public OperatorToken(Operator operator) {
        super (2);
        if (null == operator) {
            throw new IllegalArgumentException ("Operator is unknown for token.");
        }
        this.operator = operator;
    }
    public Operator getOperator() {
        return this.operator;
    }
}
