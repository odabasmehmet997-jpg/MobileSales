package com.proje.mobilesales.core.expr;

class BinaryExpr extends Expr {
    Expr rand0;
    Expr rand1;
    int rator;
    BinaryExpr(int i2, Expr expr, Expr expr2) {
        this.rator = i2;
        this.rand0 = expr;
        this.rand1 = expr2;
    }
    public double value() {
        double value = this.rand0.value();
        double value2 = this.rand1.value();
        switch (this.rator) {
            case 0:
                return value + value2;
            case 1:
                return value - value2;
            case 2:
                return value * value2;
            case 3:
                return value / value2;
            case 4:
                return Math.pow(value, value2);
            case 5:
                return Math.atan2(value, value2);
            case 6:
                return value < value2 ? value2 : value;
            case 7:
                return value < value2 ? value : value2;
            case 8:
                return value < value2 ? 1.0d : 0.0d;
            case 9:
                return value <= value2 ? 1.0d : 0.0d;
            case 10:
                return value == value2 ? 1.0d : 0.0d;
            case 11:
                return value != value2 ? 1.0d : 0.0d;
            case 12:
                return value >= value2 ? 1.0d : 0.0d;
            case 13:
                return value > value2 ? 1.0d : 0.0d;
            case 14:
                return (value == 0.0d || value2 == 0.0d) ? 0.0d : 1.0d;
            case 15:
                return (value == 0.0d && value2 == 0.0d) ? 0.0d : 1.0d;
            default:
                throw new RuntimeException("BUG: bad rator");
        }
    }
}
