package com.proje.mobilesales.core.enums;
 
public enum BaskiAlanlariNumber {
    BASLIK_SABIT_ALANLAR(0, "BASLIK_SABIT_ALANLAR"),
    FIS_BASLIK(1, "FIS_BASLIK"),
    DETAY_SABIT_ALANLAR(2, "DETAY_SABIT_ALANLAR"),
    FIS_DETAY(3, "FIS_DETAY"),
    SAYFASONU_SABIT_ALANLAR(4, "SAYFASONU_SABIT_ALANLAR"),
    SAYFA_SONU(5, "SAYFA_SONU"),
    BASKISONU_SABIT_ALANLAR(6, "BASKISONU_SABIT_ALANLAR"),
    BASKI_SONU(7, "BASKI_SONU");
    private String name;
    private int number;
    BaskiAlanlariNumber(int i2, String str) {
        this.number = i2;
        this.name = str;
    }
    public int getNumber() {
        return this.number;
    }
    public void setNumber(int i2) {
        this.number = i2;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String str) {
        this.name = str;
    }
}
