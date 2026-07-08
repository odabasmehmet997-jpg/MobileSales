package net.objecthunter.exp4j.operator;

import androidx.webkit.ProxyConfig;

public abstract class Operators {
    private static final Operator[] builtinOperators;
    static {
        String str = "";
        builtinOperators = new Operator[]{new Operator(str, i, true, i) { public double apply(double... dArr) {
                return dArr[0] + dArr[1];
            }
        }, new Operator(str, i, 1 == true ? 1 : 0, i) {
            public double apply(double... dArr) {
                return dArr[0] - dArr[1];
            }
        }, new Operator(ProxyConfig.MATCH_ALL_SCHEMES, i, 1 == true ? 1 : 0, i) {
            public double apply(double... dArr) {
                return dArr[0] * dArr[1];
            }
        }, new Operator("/", i, 1 == true ? 1 : 0, i) {
            public double apply(double... dArr) {
                double d2 = dArr[1];
                if (d2 == 0.0d) {
                    throw new ArithmeticException("Division by zero!");
                }
                return dArr[0] / d2;
            }
        }, new Operator("^", i, z, 10000) {
            public double apply(double... dArr) {
                return Math.pow(dArr[0], dArr[1]);
            }
        }, new Operator("%", i, 1 == true ? 1 : 0, i) {
            public double apply(double... dArr) {
                double d2 = dArr[1];
                if (d2 == 0.0d) {
                    throw new ArithmeticException("Division by zero!");
                }
                return dArr[0] % d2;
            }
        }, new Operator(str, 1 == true ? 1 : 0, z, i) {
            public double apply(double... dArr) {
                return -dArr[0];
            }
        }, new Operator(str, 1 == true ? 1 : 0, z, i) {
            public double apply(double... dArr) {
                return dArr[0];
            }
        }};
        String str = "+";
        int i2 = 2;
        int i3 = 500;
        boolean z = false;
        String str2 = "-";
        int i4 = 5000;
        int i5 = 1000;
    }

    public static Operator getBuiltinOperator(char c2, int i2) {
        if (c2 == '%') {
            return builtinOperators[5];
        }
        if (c2 == '-') {
            if (i2 != 1) {
                return builtinOperators[1];
            }
            return builtinOperators[6];
        }
        if (c2 == '/') {
            return builtinOperators[3];
        }
        if (c2 == '^') {
            return builtinOperators[4];
        }
        if (c2 == '*') {
            return builtinOperators[2];
        }
        if (c2 != '+') {
            return null;
        }
        if (i2 != 1) {
            return builtinOperators[0];
        }
        return builtinOperators[7];
    }
}
