package com.github.mikephil.charting.matrix;

public final class Vector3 {

    public static final Vector3 ZERO = new Vector3 (0.0f, 0.0f, 0.0f);
    public static final Vector3 UNIT_X = new Vector3 (1.0f, 0.0f, 0.0f);
    public static final Vector3 UNIT_Y = new Vector3 (0.0f, 1.0f, 0.0f);
    public static final Vector3 UNIT_Z = new Vector3 (0.0f, 0.0f, 1.0f);
    public float f819x;
    public float f820y;
    public float f821z;

    public Vector3() {
    }

    public Vector3(final float[] fArr) {
        this.set(fArr[0], fArr[1], fArr[2]);
    }

    public Vector3(final float f, final float f2, final float f3) {
        this.set(f, f2, f3);
    }

    public Vector3(final Vector3 vector3) {
        this.set(vector3);
    }

    public void add(final Vector3 vector3) {
        f819x += vector3.f819x;
        f820y += vector3.f820y;
        f821z += vector3.f821z;
    }

    public void add(final float f, final float f2, final float f3) {
        f819x += f;
        f820y += f2;
        f821z += f3;
    }

    public void subtract(final Vector3 vector3) {
        f819x -= vector3.f819x;
        f820y -= vector3.f820y;
        f821z -= vector3.f821z;
    }

    public void subtractMultiple(final Vector3 vector3, final float f) {
        f819x -= vector3.f819x * f;
        f820y -= vector3.f820y * f;
        f821z -= vector3.f821z * f;
    }

    public void multiply(final float f) {
        f819x *= f;
        f820y *= f;
        f821z *= f;
    }

    public void multiply(final Vector3 vector3) {
        f819x *= vector3.f819x;
        f820y *= vector3.f820y;
        f821z *= vector3.f821z;
    }

    public void divide(final float f) {
        if (0.0f != f) {
            f819x /= f;
            f820y /= f;
            f821z /= f;
        }
    }

    public void set(final Vector3 vector3) {
        f819x = vector3.f819x;
        f820y = vector3.f820y;
        f821z = vector3.f821z;
    }

    public void set(final float f, final float f2, final float f3) {
        f819x = f;
        f820y = f2;
        f821z = f3;
    }

    public float dot(final Vector3 vector3) {
        return (f819x * vector3.f819x) + (f820y * vector3.f820y) + (f821z * vector3.f821z);
    }

    public Vector3 cross(final Vector3 vector3) {
        final float f = f820y;
        final float f2 = vector3.f821z;
        final float f3 = f821z;
        final float f4 = vector3.f820y;
        final float f5 = (f * f2) - (f3 * f4);
        final float f6 = vector3.f819x;
        final float f7 = f819x;
        return new Vector3 (f5, (f3 * f6) - (f2 * f7), (f7 * f4) - (f * f6));
    }

    public float length() {
        return (float) Math.sqrt (this.length2());
    }

    public float length2() {
        final float f = f819x;
        final float f2 = f820y;
        final float f3 = (f * f) + (f2 * f2);
        final float f4 = f821z;
        return f3 + (f4 * f4);
    }

    public float distance2(final Vector3 vector3) {
        final float f = f819x - vector3.f819x;
        final float f2 = f820y - vector3.f820y;
        final float f3 = f821z - vector3.f821z;
        return (f * f) + (f2 * f2) + (f3 * f3);
    }

    public float normalize() {
        final float length = this.length();
        if (0.0f != length) {
            f819x /= length;
            f820y /= length;
            f821z /= length;
        }
        return length;
    }

    public void zero() {
        this.set(0.0f, 0.0f, 0.0f);
    }

    public boolean pointsInSameDirection(final Vector3 vector3) {
        return 0.0f < dot(vector3);
    }
}
