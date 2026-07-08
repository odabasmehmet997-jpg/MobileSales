package com.proje.mobilesales.features.visit.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.model.FicheImageProp;
import com.proje.mobilesales.features.model.FicheRefProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class Visit implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private int auto;
    private String clCode;
    private int clRef;
    private String date;
    private int f1284id;
    private FicheImageProp image;
    private String latitude;
    private String longitude;
    private FicheStringProp note;
    private FicheRefProp reason;
    private FicheRefProp shipAddress;
    private FicheRefProp userTitle;
    public int describeContents() {
        return 0;
    }
    public Visit() {
    }
    public int getId() {
        return f1284id;
    }
    public void setId(final int i2) {
        f1284id = i2;
    }
    public int getClRef() {
        return clRef;
    }
    public void setClRef(final int i2) {
        clRef = i2;
    }
    public String getClCode() {
        return clCode;
    }
    public void setClCode(final String str) {
        clCode = str;
    }
    public FicheRefProp getShipAddress() {
        return shipAddress;
    }
    public void setShipAddress(final FicheRefProp ficheRefProp) {
        shipAddress = ficheRefProp;
    }
    public FicheRefProp getReason() {
        return reason;
    }
    public void setReason(final FicheRefProp ficheRefProp) {
        reason = ficheRefProp;
    }
    public String getDate() {
        return date;
    }
    public void setDate(final String str) {
        date = str;
    }
    public FicheRefProp getUserTitle() {
        return userTitle;
    }
    public void setUserTitle(final FicheRefProp ficheRefProp) {
        userTitle = ficheRefProp;
    }
    public FicheStringProp getNote() {
        return note;
    }

    public void setNote(final FicheStringProp ficheStringProp) {
        note = ficheStringProp;
    }

    public FicheImageProp getImage() {
        return image;
    }

    public void setImage(final FicheImageProp ficheImageProp) {
        image = ficheImageProp;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(final String str) {
        latitude = str;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(final String str) {
        longitude = str;
    }

    public int getAuto() {
        return auto;
    }

    public void setAuto(final int i2) {
        auto = i2;
    }

    public Visit(final Parcel parcel) {
        this();
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        f1284id = parcel.readInt();
        clRef = parcel.readInt();
        clCode = parcel.readString();
        shipAddress = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        reason = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        date = parcel.readString();
        userTitle = parcel.readParcelable(FicheRefProp.class.getClassLoader());
        note = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        image = parcel.readParcelable(FicheImageProp.class.getClassLoader());
        latitude = parcel.readString();
        longitude = parcel.readString();
        auto = parcel.readInt();
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(f1284id);
        parcel.writeInt(clRef);
        parcel.writeString(clCode);
        parcel.writeParcelable(shipAddress, i2);
        parcel.writeParcelable(reason, i2);
        parcel.writeString(date);
        parcel.writeParcelable(userTitle, i2);
        parcel.writeParcelable(note, i2);
        parcel.writeParcelable(image, i2);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeInt(auto);
    }
    public static final class CREATOR implements Parcelable.Creator<Visit> {
        public CREATOR(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        public Visit createFromParcel(final Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new Visit(parcel);
        }

        public Visit[] newArray(final int i2) {
            return new Visit[i2];
        }
    }

    public String visitSaveControl() {
        String str = "";
        try {
            try {
                final FicheRefProp ficheRefProp = reason;
                if (TextUtils.isEmpty(null != ficheRefProp ? ficheRefProp.toString() : null)) {
                    str = ContextUtils.getStringResource(R.string.exp_36_visit_reason_not_select) + '\n';
                }
                final FicheRefProp ficheRefProp2 = userTitle;
                if (!TextUtils.isEmpty(null != ficheRefProp2 ? ficheRefProp2.toString() : null)) {
                    return str;
                }
                return str + ContextUtils.getStringResource(R.string.str_question_enter_definition);
            } catch (final Exception e2) {
                Log.e(MobileSales.TAG, "visitSaveControl: ", e2);
                return "";
            }
        } catch (final Throwable unused) {
            return "";
        }
    }
}
