package com.google.zxing.qrcode.encoder;

import java.lang.reflect.Array;

public final class ByteMatrix {
    private final byte[][] bytes;
    private final int height;
    private final int width;
    public ByteMatrix(int width, int height) {
        this.bytes = (byte[][]) Array.newInstance(Byte.TYPE, height, width);
        this.width = width;
        this.height = height;
    }
    public int getHeight() {
        return this.height;
    }
    public int getWidth() {
        return this.width;
    }
    public byte get(int x, int y) {
        return this.bytes[y][x];
    }
    public byte[][] getArray() {
        return this.bytes;
    }
    public void set(int x, int y, int value) {
        this.bytes[y][x] = (byte) value;
    }
    public void set(int x, int y, boolean value) {
        this.bytes[y][x] = value ? (byte) 1 : (byte) 0;
    }
    public void clear(byte value) {
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.bytes[y][x] = value;
            }
        }
    }
    public String toString() {
        StringBuilder sb = new StringBuilder((this.width * 2 * this.height) + 2);
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                byte b = this.bytes[y][x];
                if (b == 0) {
                    sb.append(" 0");
                } else if (b == 1) {
                    sb.append(" 1");
                } else {
                    sb.append("  ");
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
