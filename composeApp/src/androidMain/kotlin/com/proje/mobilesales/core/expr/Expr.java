package com.proje.mobilesales.core.expr;

public abstract class Expr {
    public static final int ABS = 100;
    public static final int ACOS = 101;
    public static final int ADD = 0;
    public static final int AND = 14;
    public static final int ASIN = 102;
    public static final int ATAN = 103;
    public static final int ATAN2 = 5;
    public static final int CEIL = 104;
    public static final int COS = 105;
    public static final int DIV = 3; 
    public static final int f1175EQ = 10;
    public static final int EXP = 106;
    public static final int FLOOR = 107;
    public static final int f1176GE = 12;
    public static final int f1177GT = 13;
    public static final int f1178LE = 9;
    public static final int LOG = 108;
    public static final int f1179LT = 8;
    public static final int MAX = 6;
    public static final int MIN = 7;
    public static final int MUL = 2;
    public static final int f1180NE = 11;
    public static final int NEG = 109;
    public static final int f1181OR = 15;
    public static final int POW = 4;
    public static final int ROUND = 110;
    public static final int SIN = 111;
    public static final int SQRT = 112;
    public static final int SUB = 1;
    public static final int TAN = 113;

    public abstract double value();

    public static Expr makeLiteral(double d2) {
        return new LiteralExpr(d2);
    }

    public static Expr makeApp1(int i2, Expr expr) {
        UnaryExpr unaryExpr = new UnaryExpr(i2, expr);
        return expr instanceof LiteralExpr ? new LiteralExpr(unaryExpr.value()) : unaryExpr;
    }

    public static Expr makeApp2(int i2, Expr expr, Expr expr2) {
        BinaryExpr binaryExpr = new BinaryExpr(i2, expr, expr2);
        return ((expr instanceof LiteralExpr) && (expr2 instanceof LiteralExpr)) ? new LiteralExpr(binaryExpr.value()) : binaryExpr;
    }

    public static Expr makeIfThenElse(Expr expr, Expr expr2, Expr expr3) {
        return expr instanceof LiteralExpr ? expr.value() != 0.0d ? expr2 : expr3 : new ConditionalExpr(expr, expr2, expr3);
    }
}
