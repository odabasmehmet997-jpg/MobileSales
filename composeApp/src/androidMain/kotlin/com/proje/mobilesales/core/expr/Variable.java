package com.proje.mobilesales.core.expr;

import java.util.HashMap;

public class Variable extends Expr {
    private static final HashMap variables = new HashMap();
    private final String name;
    private double val = 0.0d;
    public static synchronized Variable make(String str) {
        Variable variable;
        synchronized (Variable.class) {
            variable = (Variable) variables.get(str);
            if (variable == null) {
                HashMap hashMap = variables;
                Variable variable2 = new Variable(str);
                hashMap.put(str, variable2);
                variable = variable2;
            }
        }
        return variable;
    }
    public Variable(String str) {
        this.name = str;
    }
    public String toString() {
        return this.name;
    }
    public double value() {
        return this.val;
    }
    public void setValue(double d2) {
        this.val = d2;
    }
}
