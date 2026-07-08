package net.objecthunter.exp4j.function;

public abstract class Function {
    protected final String name;
    protected final int numArguments;
    public abstract double apply(double... dArr);
    public Function(String str, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("The number of function arguments can not be less than 0 for '" + str + "'");
        }
        if (!isValidFunctionName(str)) {
            throw new IllegalArgumentException("The function name '" + str + "' is invalid");
        }
        this.name = str;
        this.numArguments = i2;
    }
    public Function(String str) {
        this(str, 1);
    }
    public String getName() {
        return this.name;
    }
    public int getNumArguments() {
        return this.numArguments;
    }
    public static boolean isValidFunctionName(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char cCharAt : str.toCharArray()) {
            if (!Character.isLetter(cCharAt) && cCharAt != '_' && (!Character.isDigit(cCharAt) || str.indexOf(cCharAt) < 0)) {
                return false;
            }
        }
        return true;
    }
}
