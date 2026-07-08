package com.proje.mobilesales.features.visit.model.database;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.enums.BinaryType;
import com.proje.mobilesales.core.interfaces.ConvertDb;
import com.proje.mobilesales.features.dbmodel.ConvertBinaries;
import com.proje.mobilesales.features.model.FicheImageProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import com.proje.mobilesales.features.visit.model.Visit;
import java.io.IOException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class VisitInfo extends ConvertBinaries implements Parcelable, ConvertDb<Visit> {
    public int auto;

    public String boylam;
    public String clCode;
    public int clRef;
    public String endDate;
    public String enlem;
    public int  id;
    private int isTransfer;
    public int logicalRef;
    public String note;
    public String reason;
    public String shipAddrCode;
    public int shipAddrRef;
    public String startDate;
    public int userId;
    public int userTitle;
    public String visitDate;
    public byte[] visitImage;
    public static final Companion Companion = new Companion(null);
    private static final Parcelable.Creator<VisitInfo> CREATOR = new Parcelable.Creator<VisitInfo>() {
        public VisitInfo createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new VisitInfo(source);
        }
        public VisitInfo[] newArray(int i2) {
            return new VisitInfo[i2];
        }
    };
    public int describeContents() {
        return 0;
    }
    public int getTransfer() {
        return this.isTransfer;
    }
    public void setTransfer(int i2) {
        this.isTransfer = i2;
    }
    public VisitInfo() {
    }
    public void convertDbType(Visit object) {
        Intrinsics.checkNotNullParameter(object, "object");
        try {
            this.id = object.getId();
            FicheRefProp shipAddress = object.getShipAddress();
            this.shipAddrCode = shipAddress != null ? shipAddress.getDefinition() : null;
            FicheRefProp shipAddress2 = object.getShipAddress();
            this.shipAddrRef = shipAddress2 != null ? shipAddress2.getLogicalRef() : 0;
            this.visitDate = object.getDate();
            this.clRef = object.getClRef();
            this.clCode = object.getClCode();
            this.reason = String.valueOf(object.getReason());
            this.note = String.valueOf(object.getNote());
            FicheImageProp image = object.getImage();
            this.visitImage = image != null ? image.getImageArray() : null;
            FicheRefProp userTitle = object.getUserTitle();
            this.userTitle = userTitle != null ? userTitle.getLogicalRef() : 0;
            this.enlem = object.getLatitude();
            this.boylam = object.getLongitude();
            this.auto = object.getAuto();
        } catch (Exception e2) {
            Log.e(MobileSales.TAG, "convertDbType: ", e2);
        }
    }
    public Visit convert() {
        Visit visit = new Visit();
        visit.setId(this.id);
        visit.setReason(new FicheRefProp());
        FicheRefProp reason = visit.getReason();
        Intrinsics.checkNotNull(reason);
        FicheStringProp.setDefinition(this.reason);
        visit.setNote(new FicheStringProp());
        FicheStringProp note = visit.getNote();
        Intrinsics.checkNotNull(note);
        FicheStringProp.setDefinition(this.note);
        visit.setDate(this.visitDate);
        visit.setClRef(this.clRef);
        visit.setClCode(this.clCode);
        visit.setShipAddress(new FicheRefProp());
        FicheRefProp shipAddress = visit.getShipAddress();
        Intrinsics.checkNotNull(shipAddress);
        shipAddress.setLogicalRef(this.shipAddrRef);
        FicheRefProp shipAddress2 = visit.getShipAddress();
        Intrinsics.checkNotNull(shipAddress2);
        FicheStringProp.setDefinition(this.shipAddrCode);
        visit.setUserTitle(new FicheRefProp());
        FicheRefProp userTitle = visit.getUserTitle();
        Intrinsics.checkNotNull(userTitle);
        userTitle.setLogicalRef(this.userTitle);
        visit.setImage(new FicheImageProp());
        FicheImageProp image = visit.getImage();
        Intrinsics.checkNotNull(image);
        image.setImageArray(this.visitImage);
        visit.setLatitude(this.enlem);
        visit.setLongitude(this.boylam);
        visit.setAuto(this.auto);
        return visit;
    }
    public void writeToParcel(Parcel dest, int i2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(this.id);
        dest.writeString(this.visitDate);
        dest.writeInt(this.clRef);
        dest.writeInt(this.shipAddrRef);
        dest.writeString(this.shipAddrCode);
        dest.writeString(this.reason);
        dest.writeString(this.note);
        dest.writeInt(this.isTransfer);
        dest.writeString(this.enlem);
        dest.writeString(this.boylam);
        dest.writeInt(this.userTitle);
        dest.writeByteArray(this.visitImage);
        dest.writeInt(this.logicalRef);
        dest.writeInt(this.userId);
        dest.writeString(this.startDate);
        dest.writeString(this.endDate);
        dest.writeInt(this.auto);
    }
    private VisitInfo(Parcel in) {
        Intrinsics.checkNotNullParameter(in, "in");
        this.id = in.readInt();
        this.visitDate = in.readString();
        this.clRef = in.readInt();
        this.shipAddrRef = in.readInt();
        this.shipAddrCode = in.readString();
        this.reason = in.readString();
        this.note = in.readString();
        this.isTransfer = in.readInt();
        this.enlem = in.readString();
        this.boylam = in.readString();
        this.userTitle = in.readInt();
        this.visitImage = in.createByteArray();
        this.logicalRef = in.readInt();
        this.userId = in.readInt();
        this.auto = in.readInt();
        this.startDate = in.readString();
        this.endDate = in.readString();
    }
    public void checkBinaries() {
        try {
            this.visitImage = Convert(BinaryType.btIMAGE, this.visitImage);
        } catch (IOException e2) {
            String message = e2.getMessage();
            Intrinsics.checkNotNull(message);
            Log.e("CustomerImage.VisitInfo", message);
        }
    }
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Parcelable.Creator<VisitInfo> getCREATOR() {
            return VisitInfo.CREATOR;
        }
    }
}
