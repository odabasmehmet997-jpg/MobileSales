package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FicheDiscountRefProp extends FicheDiscountProp implements FicheStringProp.FicheAttributes {
    public static final Parcelable.Creator<FicheDiscountRefProp> CREATOR = new Parcelable.Creator<FicheDiscountRefProp>() {
        public FicheDiscountRefProp createFromParcel(final Parcel parcel) {
            return new FicheDiscountRefProp(parcel);
        }

        public FicheDiscountRefProp[] newArray(final int i2) {
            return new FicheDiscountRefProp[i2];
        }
    };
    private String code;
    private int logicalRef;
    private final PropertyChangeSupport pcs;
    private int which;

    public FicheDiscountRefProp() {
        pcs = new PropertyChangeSupport(this);
        this.setLogicalRef(-1);
        which = -1;
        code = "";
    }

    public FicheDiscountRefProp(final int i2, final int i3, final String str, final String str2) {
        super(str);
        pcs = new PropertyChangeSupport(this);
        this.setLogicalRef(i2);
        which = i3;
        code = str2;
    }

    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        pcs.addPropertyChangeListener(propertyChangeListener);
    }

    public boolean hasPropertyChangeListener(final String str) {
        final PropertyChangeListener[] propertyChangeListeners = pcs.getPropertyChangeListeners(str);
        return null != propertyChangeListeners && 0 < propertyChangeListeners.length;
    }

    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        pcs.removePropertyChangeListener(propertyChangeListener);
    }

    public int getLogicalRef() {
        return logicalRef;
    }

    public void setLogicalRef(final int i2) {
        final int i3 = logicalRef;
        logicalRef = i2;
        pcs.firePropertyChange("logicalRef", i3, i2);
    }
    public int getWhich() {
        return which;
    }
    public void setWhich(final int i2) {
        which = i2;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String str) {
        code = str;
    }
    public void reset() {
        super.reset();
        this.setLogicalRef(-1);
        which = -1;
        code = "";
        this.setBoundedToCard(false);
    }

    protected FicheDiscountRefProp(final Parcel parcel) {
        super(parcel);
        pcs = new PropertyChangeSupport(this);
        this.setLogicalRef(parcel.readInt());
        which = parcel.readInt();
        code = parcel.readString();
    }
     public int describeContents() {
        super.describeContents();
        return 0;
    }
     public void writeToParcel(final Parcel parcel, final int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(logicalRef);
        parcel.writeInt(which);
        parcel.writeString(code);
    }

    public FicheDiscountRefProp mo1410clone() throws CloneNotSupportedException {
        final FicheDiscountRefProp ficheDiscountRefProp = (FicheDiscountRefProp) super.mo1410clone();
        final String str = code;
        ficheDiscountRefProp.code = null != str ? str : "";
        return ficheDiscountRefProp;
    }
}
