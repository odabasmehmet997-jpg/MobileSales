package com.proje.mobilesales.core.printutil;

public class PrintAlan {
    private int alanNo;
    private String align;
    private int isCutWord;
    private int textType;
    private String value;
    private int f1190w;
    private int worldLength;
    private int f1191x;
    private int f1192y;
    public int getTextType() {
        return this.textType;
    }
    public void setTextType(int i2) {
        this.textType = i2;
    }
    public int getAlanNo() {
        return this.alanNo;
    }
    public void setAlanNo(int i2) {
        this.alanNo = i2;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String str) {
        this.value = str;
    }
    public int getX() {
        return this.f1191x;
    }
    public void setX(int i2) {
        this.f1191x = i2;
    }
    public int getY() {
        return this.f1192y;
    }
    public void setY(int i2) {
        this.f1192y = i2;
    }
    public int getW() {
        return this.f1190w;
    }
    public void setW(int i2) {
        this.f1190w = i2;
    }
    public String getAlign() {
        return this.align;
    }
    public void setAlign(String str) {
        this.align = str;
    }
    public int getIsCutWord() {
        return this.isCutWord;
    }
    public void setIsCutWord(int i2) {
        this.isCutWord = i2;
    }
    public int getWorldLength() {
        return this.worldLength;
    }
    public void setWorldLength(int i2) {
        this.worldLength = i2;
    }
    @Override
    public String toString() {
        return this.textType + "," + this.alanNo + "," + this.value + "," + this.f1191x + "," + this.f1192y + "," + this.f1190w + "," + this.align + "," + this.isCutWord + "," + this.worldLength;
    }
}
