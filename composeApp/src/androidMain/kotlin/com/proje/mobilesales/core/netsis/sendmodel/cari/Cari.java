package com.proje.mobilesales.core.netsis.sendmodel.cari;


public class Cari {

    private CariEkBilgi cariEkBilgi;
    private CariTemelBilgi cariTemelBilgi;
    private boolean isletmelerdeOrtak;
    private boolean muhasebelesmisBelge;
    private boolean subelerdeOrtak;
    private boolean transactSupport;
    public CariTemelBilgi getCariTemelBilgi() {
        return this.cariTemelBilgi;
    }
    public Cari setCariTemelBilgi(CariTemelBilgi cariTemelBilgi) {
        this.cariTemelBilgi = cariTemelBilgi;
        return this;
    }
    public boolean isSubelerdeOrtak() {
        return this.subelerdeOrtak;
    }
    public Cari setSubelerdeOrtak(boolean subelerdeOrtak) {
        this.subelerdeOrtak = subelerdeOrtak;
        return this;
    }
    public boolean isIsletmelerdeOrtak() {
        return this.isletmelerdeOrtak;
    }
    public Cari setIsletmelerdeOrtak(boolean isletmelerdeOrtak) {
        this.isletmelerdeOrtak = isletmelerdeOrtak;
        return this;
    }
    public boolean isTransactSupport() {
        return this.transactSupport;
    }
    public Cari setTransactSupport(boolean transactSupport) {
        this.transactSupport = transactSupport;
        return this;
    }
    public boolean isMuhasebelesmisBelge() {
        return this.muhasebelesmisBelge;
    }
    public Cari setMuhasebelesmisBelge(boolean muhasebelesmisBelge) {
        this.muhasebelesmisBelge = muhasebelesmisBelge;
        return this;
    }
    public CariEkBilgi getCariEkBilgi() {
        return this.cariEkBilgi;
    }
    public Cari setCariEkBilgi(CariEkBilgi cariEkBilgi) {
        this.cariEkBilgi = cariEkBilgi;
        return this;
    }
    public String getCode() {
        return "";
    }
    public char[] getCariKodu() {
        return new char[0];
    }
}
