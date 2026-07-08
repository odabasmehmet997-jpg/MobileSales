package com.proje.mobilesales.core.expr;

class UnaryExpr extends Expr {
    Expr rand;
    int rator;

    UnaryExpr(int i2, Expr expr) {
        this.rator = i2;
        this.rand = expr;
    }
    public double value() {
        double value = this.rand.value();
        switch (this.rator) {
            case 100:
                return Math.abs(value);
            case 101:
                return Math.acos(value);
            case 102:
                return Math.asin(value);
            case 103:
                return Math.atan(value);
            case 104:
                return Math.ceil(value);
            case 105:
                return Math.cos(value);
            case 106:
                return Math.exp(value);
            case 107:
                return Math.floor(value);
            case 108:
                return Math.log(value);
            case 109:
                return -value;
            case 110:
                return Math.rint(value);
            case 111:
                return Math.sin(value);
            case 112:
                return Math.sqrt(value);
            case 113:
                return Math.tan(value);
            default:
                throw new RuntimeException("BUG: bad rator");
        }
    }
}
