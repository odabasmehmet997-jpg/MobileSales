package net.objecthunter.exp4j.tokenizer;


public abstract class Token {
    protected final int type;
    Token(int i2) {
        type = i2;
    }
    public int getType() {
        return type;
    }
}
