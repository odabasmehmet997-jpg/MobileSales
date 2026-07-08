package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.interfaces.FicheProp;
import com.proje.mobilesales.core.interfaces.IFichePropChangeListener;
import com.proje.mobilesales.core.utils.StringUtils;

public class FicheStringProp implements Parcelable, FicheProp, Cloneable {
    public static final Parcelable.Creator<FicheStringProp> CREATOR = new Parcelable.Creator<FicheStringProp>() {
        public FicheStringProp createFromParcel(final Parcel parcel) {
            return new FicheStringProp(parcel);
        }

        public FicheStringProp[] newArray(final int i2) {
            return new FicheStringProp[i2];
        }
    };
    private String definition;
    private IFichePropChangeListener propChangeListener;

    public interface FicheAttributes {
        String getDefinition();

        int getLogicalRef();

        int getWhich();

        void reset();

        void setDefinition(String str);

        void setLogicalRef(int i2);

        void setWhich(int i2);
    }

    public int describeContents() {
        return 0;
    }

    public FicheStringProp(final String str) {
        definition = str;
    }

    public FicheStringProp() {
        this.definition = "";
    }

    public String toString() {
        if (null == getDefinition()) {
            return "";
        }
        return this.definition;
    }

    public float getDefinitionFloat() {
        return StringUtils.convertStringToFloat(definition);
    }

    public double getDefinitionDouble() {
        return StringUtils.convertStringToDouble(definition);
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(final String str) {
        this.definition = str;
    }

    public void reset() {
        definition = "";
    }

    public FicheStringProp mo1410clone() throws CloneNotSupportedException {
        final FicheStringProp ficheStringProp = (FicheStringProp) clone();
        final String str = definition;
        ficheStringProp.definition = null == str ? "" : str;
        return ficheStringProp;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeString(definition);
    }

    protected FicheStringProp(final Parcel parcel) {
        definition = parcel.readString();
    }

    public IFichePropChangeListener getPropChangeListener() {
        return propChangeListener;
    }

    public void setPropChangeListener(final IFichePropChangeListener iFichePropChangeListener) {
        propChangeListener = iFichePropChangeListener;
    }
}
