package com.proje.mobilesales.core.expr;

class ConditionalExpr extends Expr {
    Expr alternative;
    Expr consequent;
    Expr test;

    ConditionalExpr(Expr expr, Expr expr2, Expr expr3) {
        this.test = expr;
        this.consequent = expr2;
        this.alternative = expr3;
    }

    public double value() {
        return (this.test.value() != 0.0d ? this.consequent : this.alternative).value();
    }
}
