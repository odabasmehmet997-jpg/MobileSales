package com.journeyapps.barcodescanner;


public record Size(int width, int height) implements Comparable<Size> {
    public Size rotate() {
        return new Size(this.height, this.width);
    }

    public Size scaleFit(Size size) {
        int r0 = this.width;
        int r1 = size.height;
        int r2 = r0 * r1;
        int r5 = size.width;
        int r4 = this.height;
        if (r2 >= r5 * r4) {
            return new Size(r5, (r4 * r5) / r0);
        }
        return new Size((r0 * r1) / r4, r1);
    }

    public Size scaleCrop(Size size) {
        int r0 = this.width;
        int r1 = size.height;
        int r2 = r0 * r1;
        int r5 = size.width;
        int r4 = this.height;
        if (r2 <= r5 * r4) {
            return new Size(r5, (r4 * r5) / r0);
        }
        return new Size((r0 * r1) / r4, r1);
    }

    public int compareTo(Size size) {
        int r0 = this.height * this.width;
        int r1 = size.height * size.width;
        if (r1 < r0) {
            return 1;
        }
        return r1 > r0 ? -1 : 0;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }
}
