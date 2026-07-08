package com.proje.mobilesales.features.product.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;
import com.proje.mobilesales.core.base.BaseDbSalesFiche;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parceler;
public class ProductGroupModel implements Parcelable {
    private String grpCode;
    private int grpCount;
    private String grpName;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<ProductGroupModel> CREATOR = new Creator();

    public static final class Creator implements Parcelable.Creator<ProductGroupModel> {

        public ProductGroupModel createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return ProductGroupModel.Companion.create(parcel);
        }
        public ProductGroupModel[] newArray(int i) {
            return new ProductGroupModel[i];
        }
    }

    public ProductGroupModel() {
        this(null, 0, null, 7, null);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        Companion.write(this, parcel, i);
    }

    public ProductGroupModel(String str, int i, String str2) {
        this.grpCode = str;
        this.grpCount = i;
        this.grpName = str2;
    }

    public  ProductGroupModel(String str, int i, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? null : str2);
    }

    public final String getGrpCode() {
        return this.grpCode;
    }

    public final void setGrpCode(String str) {
        this.grpCode = str;
    }

    public final int getGrpCount() {
        return this.grpCount;
    }

    public final void setGrpCount(int i) {
        this.grpCount = i;
    }

    public final String getGrpName() {
        return this.grpName;
    }

    public final void setGrpName(String str) {
        this.grpName = str;
    }
    protected ProductGroupModel(Parcel parcel) {
        this(null, 0, null, 7, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.grpCode = parcel.readString();
        this.grpCount = parcel.readInt();
        this.grpName = parcel.readString();
    }

    public final String getGrpDescription() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.grpCode);
        String str2 = this.grpName;
        if (str2 != null) {
            Intrinsics.checkNotNull(str2);
            if (str2.length() != 0) {
                str = SqlLiteVariable._COMMA_SEP + this.grpName;
                sb.append(str);
                return sb.toString();
            }
        }
        str = "";
        sb.append(str);
        return sb.toString();
    }

    public static final class Companion implements Parceler<ProductGroupModel> {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public BaseDbSalesFiche[] newArray(int i) {
            return   DefaultImpls.newArray(this, i);
        }

        public void write(ProductGroupModel productGroupModel, Parcel parcel, int i) {
            Intrinsics.checkNotNullParameter(productGroupModel, "<this>");
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.writeString(productGroupModel.getGrpCode());
            parcel.writeInt(productGroupModel.getGrpCount());
            parcel.writeString(productGroupModel.getGrpName());
        }

        public ProductGroupModel create(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ProductGroupModel(parcel);
        }
    }
}
