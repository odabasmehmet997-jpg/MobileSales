package net.objecthunter.exp4j;

import java.util.EmptyStackException;

class ArrayStack {
    private double[] data;
    private int idx;
    ArrayStack() {
        this(5);
    }
    ArrayStack(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("Stack's capacity must be positive");
        }
        this.data = new double[i2];
        this.idx = -1;
    }
    void push(double d2) {
        int i2 = this.idx + 1;
        double[] dArr = this.data;
        if (i2 == dArr.length) {
            double[] dArr2 = new double[((int) (((double) dArr.length) * 1.2d)) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
            this.data = dArr2;
        }
        double[] dArr3 = this.data;
        int i3 = this.idx + 1;
        this.idx = i3;
        dArr3[i3] = d2;
    }
    double pop() {
        int i2 = this.idx;
        if (i2 == -1) {
            throw new EmptyStackException();
        }
        double[] dArr = this.data;
        this.idx = i2 - 1;
        return dArr[i2];
    }
    int size() {
        return this.idx + 1;
    }
}
