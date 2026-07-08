package net.objecthunter.exp4j.tokenizer;

public class UnknownFunctionOrVariableException extends IllegalArgumentException {
    private static final long serialVersionUID = 1;
    private final String expression;
    private final String message;
    private final int position;
    private final String token;
    public UnknownFunctionOrVariableException(String str, int i2, int i3) {
        this.expression = str;
        String str2 = token(str, i2, i3);
        this.token = str2;
        this.position = i2;
        this.message = "Unknown function or variable '" + str2 + "' at pos " + i2 + " in expression '" + str + "'";
    }
    private static String token(String str, int i2, int i3) {
        int length = str.length ();
        int i4 = (i3 + i2) - 1;
        if (length >= i4) {
            length = i4;
        }
        return str.substring (i2, length);
    }
    public String getMessage() {
        return this.message;
    }
    public String getExpression() {
        return this.expression;
    }
    public String getToken() {
        return this.token;
    }
    public int getPosition() {
        return this.position;
    }
    public String toString() {
        return this.message;
    }
}
