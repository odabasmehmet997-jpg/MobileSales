package com.proje.mobilesales.core.printutil;

import java.util.ArrayList;
import java.util.List;

public class PrintBaslik {
    private int baslangicY;
    private int detaySatirSayisi;
    private int f1193id;
    private List<PrintAlan> printAlanList = new ArrayList();
    public int getId() {
        return this.f1193id;
    }
    public void setId(int i2) {
        this.f1193id = i2;
    }
    public int getBaslangicY() {
        return this.baslangicY;
    }
    public void setBaslangicY(int i2) {
        this.baslangicY = i2;
    }
    public int getDetaySatirSayisi() {
        return this.detaySatirSayisi;
    }
    public void setDetaySatirSayisi(int i2) {
        this.detaySatirSayisi = i2;
    }
    public List<PrintAlan> getPrintAlanList() {
        return this.printAlanList;
    }
    public void setPrintAlanList(List<PrintAlan> list) {
        this.printAlanList = list;
    }
}
