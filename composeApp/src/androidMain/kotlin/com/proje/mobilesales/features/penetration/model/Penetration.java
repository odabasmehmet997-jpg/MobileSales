package com.proje.mobilesales.features.penetration.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.features.model.FicheImageProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
    
public final class Penetration implements Parcelable {
    private String clCode;
    private int clRef;
    private String ficheDate;
    private int f1256id;
    private FicheImageProp image;
    private boolean isExist;
    private boolean isTransfer;
    private FicheStringProp not;
    private int penetrationId;
    private String penetrationName;
    private List<PenetrationLine> penetrations;
    private String pnt_GUID;
    private int visitInfoId;
    public static final Companion Companion = new Companion(null);
    private static final Creator<Penetration> CREATOR = new Creator<Penetration>() {
        public Penetration createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "in");
            return new Penetration(parcel);
        }
        public Penetration[] newArray(int i) {
            return new Penetration[i];
        }
    };
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return this.f1256id;
    }

    public void setId(int i) {
        this.f1256id = i;
    }

    public int getPenetrationId() {
        return this.penetrationId;
    }

    public void setPenetrationId(int i) {
        this.penetrationId = i;
    }

    public int getClRef() {
        return this.clRef;
    }

    public void setClRef(int i) {
        this.clRef = i;
    }

    public String getClCode() {
        return this.clCode;
    }

    public void setClCode(String str) {
        this.clCode = str;
    }

    public String getFicheDate() {
        return this.ficheDate;
    }

    public void setFicheDate(String str) {
        this.ficheDate = str;
    }

    public String getPenetrationName() {
        return this.penetrationName;
    }

    public void setPenetrationName(String str) {
        this.penetrationName = str;
    }

    public boolean isTransfer() {
        return this.isTransfer;
    }

    public void setTransfer(boolean z) {
        this.isTransfer = z;
    }

    public List<PenetrationLine> getPenetrations() {
        return this.penetrations;
    }

    public void setPenetrations(List<PenetrationLine> list) {
        this.penetrations = list;
    }

    public FicheImageProp getImage() {
        return this.image;
    }

    public void setImage(FicheImageProp ficheImageProp) {
        this.image = ficheImageProp;
    }

    public FicheStringProp getNot() {
        return this.not;
    }

    public void setNot(FicheStringProp ficheStringProp) {
        this.not = ficheStringProp;
    }

    public boolean isExist() {
        return this.isExist;
    }

    public void setExist(boolean z) {
        this.isExist = z;
    }

    public String getPnt_GUID() {
        return this.pnt_GUID;
    }

    public void setPnt_GUID(String str) {
        this.pnt_GUID = str;
    }

    public int getVisitInfoId() {
        return this.visitInfoId;
    }

    public void setVisitInfoId(int i) {
        this.visitInfoId = i;
    }

    public Penetration() {
    }

    
    public Penetration(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "in");
        this.f1256id = parcel.readInt();
        this.penetrationId = parcel.readInt();
        this.clRef = parcel.readInt();
        this.clCode = parcel.readString();
        this.ficheDate = parcel.readString();
        this.penetrationName = parcel.readString();
        boolean z = false;
        this.isTransfer = parcel.readByte() != 0;
        this.penetrations = parcel.createTypedArrayList(PenetrationLine.Companion.getCREATOR());
        this.image = parcel.readParcelable(FicheImageProp.class.getClassLoader());
        this.not = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.isExist = parcel.readByte() != 0 || z;
        this.pnt_GUID = parcel.readString();
    }
    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeInt(this.f1256id);
        parcel.writeInt(this.penetrationId);
        parcel.writeInt(this.clRef);
        parcel.writeString(this.clCode);
        parcel.writeString(this.ficheDate);
        parcel.writeString(this.penetrationName);
        parcel.writeByte(this.isTransfer ? (byte) 1 : 0);
        parcel.writeTypedList(this.penetrations);
        parcel.writeParcelable(this.image, i);
        parcel.writeParcelable(this.not, i);
        parcel.writeByte(this.isExist ? (byte) 1 : 0);
        parcel.writeString(this.pnt_GUID);
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Creator<Penetration> getCREATOR() {
            return Penetration.CREATOR;
        }
    }
}
