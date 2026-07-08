package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.enums.ErpType;

public class ErpRights implements Parcelable {
    public static final Parcelable.Creator<ErpRights> CREATOR = new Parcelable.Creator<ErpRights>() {
        public ErpRights createFromParcel(final Parcel parcel) {
            return new ErpRights(parcel);
        }

        public ErpRights[] newArray(final int i2) {
            return new ErpRights[i2];
        }
    };
    private boolean demo;
    private boolean gps;
    private boolean kampanya;
    private boolean kota;
    private ErpType mErpType;
    private boolean penetrasyon;
    private boolean pro;
    private boolean raporTasarimcisi;
    private boolean sevkiyat;
    private boolean todoMsj;
    private boolean useCase;
    private boolean yaziciBaski;
    private boolean ziyaret;

    public int describeContents() {
        return 0;
    }

    public ErpRights(final ErpType erpType, final boolean z) {
        this.mErpType = erpType;
        this.pro = z;
        kampanya = z;
        gps = z;
        demo = false;
        sevkiyat = z;
        kota = z;
        useCase = z;
        ziyaret = z;
        todoMsj = z;
        if (erpType == ErpType.TIGER) {
            penetrasyon = true;
            yaziciBaski = true;
            raporTasarimcisi = true;
        } else if (erpType == ErpType.GO) {
            penetrasyon = false;
            yaziciBaski = true;
            raporTasarimcisi = false;
        }
    }

    public ErpRights() {
    }

    public boolean isKampanya() {
        return kampanya;
    }

    public void setKampanya(final boolean z) {
        kampanya = z;
    }

    public boolean isGps() {
        return gps;
    }

    public void setGps(final boolean z) {
        gps = z;
    }

    public boolean isSevkiyat() {
        return sevkiyat;
    }

    public void setSevkiyat(final boolean z) {
        sevkiyat = z;
    }

    public boolean isPenetrasyon() {
        return penetrasyon;
    }

    public void setPenetrasyon(final boolean z) {
        penetrasyon = z;
    }

    public boolean isTodoMsj() {
        return todoMsj;
    }

    public void setTodoMsj(final boolean z) {
        todoMsj = z;
    }

    public boolean isKota() {
        return kota;
    }

    public void setKota(final boolean z) {
        kota = z;
    }

    public boolean isUseCase() {
        return useCase;
    }

    public void setUseCase(final boolean z) {
        useCase = z;
    }

    public boolean isZiyaret() {
        return ziyaret;
    }

    public void setZiyaret(final boolean z) {
        ziyaret = z;
    }

    public boolean isRaporTasarimcisi() {
        return raporTasarimcisi;
    }

    public void setRaporTasarimcisi(final boolean z) {
        raporTasarimcisi = z;
    }

    public boolean isYaziciBaski() {
        return yaziciBaski;
    }

    public void setYaziciBaski(final boolean z) {
        yaziciBaski = z;
    }

    public boolean isDemo() {
        return demo;
    }

    public void setDemo(final boolean z) {
        demo = z;
    }

    public boolean isPro() {
        return pro;
    }

    public void setPro(final boolean z) {
        pro = z;
    }

    public ErpType getErpType() {
        return mErpType;
    }

    public void setErpType(final ErpType erpType) {
        mErpType = erpType;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeByte(kampanya ? (byte) 1 : (byte) 0);
        parcel.writeByte(gps ? (byte) 1 : (byte) 0);
        parcel.writeByte(sevkiyat ? (byte) 1 : (byte) 0);
        parcel.writeByte(penetrasyon ? (byte) 1 : (byte) 0);
        parcel.writeByte(todoMsj ? (byte) 1 : (byte) 0);
        parcel.writeByte(kota ? (byte) 1 : (byte) 0);
        parcel.writeByte(useCase ? (byte) 1 : (byte) 0);
        parcel.writeByte(ziyaret ? (byte) 1 : (byte) 0);
        parcel.writeByte(raporTasarimcisi ? (byte) 1 : (byte) 0);
        parcel.writeByte(yaziciBaski ? (byte) 1 : (byte) 0);
        parcel.writeByte(demo ? (byte) 1 : (byte) 0);
        parcel.writeByte(pro ? (byte) 1 : (byte) 0);
        final ErpType erpType = mErpType;
        parcel.writeInt(null == erpType ? -1 : erpType.ordinal());
    }

    protected ErpRights(final Parcel parcel) {
        kampanya = 0 != parcel.readByte();
        gps = 0 != parcel.readByte();
        sevkiyat = 0 != parcel.readByte();
        penetrasyon = 0 != parcel.readByte();
        todoMsj = 0 != parcel.readByte();
        kota = 0 != parcel.readByte();
        useCase = 0 != parcel.readByte();
        ziyaret = 0 != parcel.readByte();
        raporTasarimcisi = 0 != parcel.readByte();
        yaziciBaski = 0 != parcel.readByte();
        demo = 0 != parcel.readByte();
        pro = 0 != parcel.readByte();
        final int readInt = parcel.readInt();
        mErpType = -1 == readInt ? null : ErpType.values()[readInt];
    }
}
