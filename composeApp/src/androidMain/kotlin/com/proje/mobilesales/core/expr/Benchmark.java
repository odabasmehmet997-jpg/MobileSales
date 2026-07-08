package com.proje.mobilesales.core.expr;

import android.util.Log;

public class Benchmark {
    static final int NPARSES = 1000;
    static final int NRUNS = 1000000;

    public static void main(String[] strArr) {
        double d2 = 1.0d;
        double d3 = 1.0d;
        for (int i2 = 0; i2 < strArr.length; i2++) {
            long timeParse = timeParse(strArr[i2]);
            long timeRun = timeRun(strArr[i2]);
            double d4 = timeParse;
            double d5 = timeRun;
            String sb = msec(d4) +
                    " ms(parse) " +
                    msec(d5) +
                    " ms(run): " +
                    strArr[i2];
            Log.i("AA", sb);
            d2 *= d4;
            d3 *= d5;
        }
        if (strArr.length > 0) {
            double pow = Math.pow(d3, 1.0d / strArr.length);
            Log.i("AA", msec(Math.pow(d2, 1.0d / strArr.length)) + " ms(parse) " + msec(pow) + " ms(run): (geometric mean)");
        }
    }

    static long msec(double d2) {
        return (long) Math.rint(d2 * 1.0E-6d);
    }

    static long timeRun(String str) {
        Variable make = Variable.make("x");
        Expr parse = parse(str);
        long nanoTime = System.nanoTime();
        for (double d2 = 0.0d; d2 <= 4.0d; d2 += 4.0E-6d) {
            make.setValue(d2);
            parse.value();
        }
        return System.nanoTime() - nanoTime;
    }

    static long timeParse(String str) {
        long nanoTime = System.nanoTime();
        for (int i2 = 0; i2 < 1000; i2++) {
            parse(str);
        }
        return System.nanoTime() - nanoTime;
    }

    static Expr parse(String str) {
        try {
            return Parser.parse(str);
        } catch (SyntaxException e2) {
            Log.e("AA", "parse: ", e2);
            throw new Error(e2);
        }
    }
}
