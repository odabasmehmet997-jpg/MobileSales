package net.objecthunter.exp4j.function;

public class Functions {
    private static Function[] builtinFunctions = new Function[0];
    private final String str = "";
    {
        Function function = null;
        int i = 0;
        builtinFunctions = new Function[]{new Function("sin") {
            public double apply(double... dArr) {
                return Math.sin(dArr[0]);
            }
        }, new Function("cos") {
            public double apply(double... dArr) {
                return Math.cos(dArr[0]);
            }
        }, function, function, new Function("cot") {
            public double apply(double... dArr) {
                if (Math.tan(dArr[0]) == 0.0d) {
                    throw new ArithmeticException("Division by zero in cotangent!");
                }
                return 1.0d / Math.tan(dArr[0]);
            }
        }, new Function("log") {
            public double apply(double... dArr) {
                return Math.log(dArr[0]);
            }
        }, new Function("log1p") {
            public double apply(double... dArr) {
                return Math.log1p(dArr[0]);
            }
        }, new Function("abs") {
            public double apply(double... dArr) {
                return Math.abs(dArr[0]);
            }
        }, new Function("acos") {
            public double apply(double... dArr) {
                return Math.acos(dArr[0]);
            }
        }, new Function("asin") {
            public double apply(double... dArr) {
                return Math.asin(dArr[0]);
            }
        }, new Function("atan") {
            public double apply(double... dArr) {
                return Math.atan(dArr[0]);
            }
        }, new Function("cbrt") {
            public double apply(double... dArr) {
                return Math.cbrt(dArr[0]);
            }
        }, new Function("ceil") {
            public double apply(double... dArr) {
                return Math.ceil(dArr[0]);
            }
        }, new Function("floor") {
            public double apply(double... dArr) {
                return Math.floor(dArr[0]);
            }
        }, new Function("sinh") {
            public double apply(double... dArr) {
                return Math.sinh(dArr[0]);
            }
        }, new Function("sqrt") {
            public double apply(double... dArr) {
                return Math.sqrt(dArr[0]);
            }
        }, new Function("tanh") {
            public double apply(double... dArr) {
                return Math.tanh(dArr[0]);
            }
        }, new Function("cosh") {
            public double apply(double... dArr) {
                return Math.cosh(dArr[0]);
            }
        }, new Function(str, 2) {
            public double apply(double... dArr) {
                return Math.pow(dArr[0], dArr[1]);
            }
        }, new Function("exp", i) {
            public double apply(double... dArr) {
                return Math.exp(dArr[0]);
            }
        }, new Function("expm1", i) {
            public double apply(double... dArr) {
                return Math.expm1(dArr[0]);
            }
        }, new Function("log10") {
            public double apply(double... dArr) {
                return Math.log10(dArr[0]);
            }
        }, new Function("log2") {
            public double apply(double... dArr) {
                return Math.log(dArr[0]) / Math.log(2.0d);
            }
        }, new Function("signum", i) {
            public double apply(double... dArr) {
                double d2 = dArr[0];
                if (d2 > 0.0d) {
                    return 1.0d;
                }
                return d2 < 0.0d ? -1.0d : 0.0d;
            }
        }};
        int i2 = 1;
        Function function2 = new Function("tan") {
            public double apply(double... dArr) {
                return Math.tan(dArr[0]);
            }
        };
        String str = "pow";
    }
    public static Function getBuiltinFunction(String str) {
        if (str.equals("sin")) {
            return builtinFunctions[0];
        }
        if (str.equals("cos")) {
            return builtinFunctions[1];
        }
        if (str.equals("tan")) {
            return builtinFunctions[2];
        }
        if (str.equals("cot")) {
            return builtinFunctions[3];
        }
        if (str.equals("asin")) {
            return builtinFunctions[8];
        }
        if (str.equals("acos")) {
            return builtinFunctions[7];
        }
        if (str.equals("atan")) {
            return builtinFunctions[9];
        }
        if (str.equals("sinh")) {
            return builtinFunctions[13];
        }
        if (str.equals("cosh")) {
            return builtinFunctions[16];
        }
        if (str.equals("tanh")) {
            return builtinFunctions[15];
        }
        if (str.equals("abs")) {
            return builtinFunctions[6];
        }
        if (str.equals("log")) {
            return builtinFunctions[4];
        }
        if (str.equals("log10")) {
            return builtinFunctions[20];
        }
        if (str.equals("log2")) {
            return builtinFunctions[21];
        }
        if (str.equals("log1p")) {
            return builtinFunctions[5];
        }
        if (str.equals("ceil")) {
            return builtinFunctions[11];
        }
        if (str.equals("floor")) {
            return builtinFunctions[12];
        }
        if (str.equals("sqrt")) {
            return builtinFunctions[14];
        }
        if (str.equals("cbrt")) {
            return builtinFunctions[10];
        }
        if (str.equals("pow")) {
            return builtinFunctions[17];
        }
        if (str.equals("exp")) {
            return builtinFunctions[18];
        }
        if (str.equals("expm1")) {
            return builtinFunctions[19];
        }
        if (str.equals("signum")) {
            return builtinFunctions[22];
        }
        return null;
    }
}
