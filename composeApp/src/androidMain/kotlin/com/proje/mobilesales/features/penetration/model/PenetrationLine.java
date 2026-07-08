package com.proje.mobilesales.features.penetration.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.FicheBooleanProp;
import com.proje.mobilesales.features.model.FicheDiscountRefProp;
import com.proje.mobilesales.features.model.FicheImageProp;
import com.proje.mobilesales.features.model.FicheStringProp;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class PenetrationLine implements Parcelable {
    public static final Companion Companion = new Companion(null);
    private static final Creator<PenetrationLine> cREATOR = new Creator<PenetrationLine>() {
        public PenetrationLine createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "in");
            return new PenetrationLine(parcel);
        }
        public PenetrationLine[] newArray(int i) {
            return new PenetrationLine[i];
        }
    };
    private FicheStringProp amount;
    private FicheDiscountRefProp currency;
    private String date;
    private FicheBooleanProp exist;
    private int f1257id;
    private FicheImageProp image;
    private boolean isImageActive;
    private boolean isNotActive;
    private boolean isPriceActive;
    private int lineNr;
    private FicheStringProp not;
    private int penetrationDetailId;
    private FicheStringProp price;
    private String productCode;
    private String productName;
    private int productRef;
    private long uniqueId;
    private String unit;
    public int describeContents() {
        return 0;
    }

    public long getUniqueId() {
        return this.uniqueId;
    }

    public void setUniqueId(long j) {
        this.uniqueId = j;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public int getId() {
        return this.f1257id;
    }

    public void setId(int i) {
        this.f1257id = i;
    }

    public int getLineNr() {
        return this.lineNr;
    }

    public void setLineNr(int i) {
        this.lineNr = i;
    }

    public int getProductRef() {
        return this.productRef;
    }

    public void setProductRef(int i) {
        this.productRef = i;
    }

    public int getPenetrationDetailId() {
        return this.penetrationDetailId;
    }

    public void setPenetrationDetailId(int i) {
        this.penetrationDetailId = i;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String str) {
        this.productCode = str;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public FicheBooleanProp getExist() {
        return this.exist;
    }

    public void setExist(FicheBooleanProp ficheBooleanProp) {
        this.exist = ficheBooleanProp;
    }

    public FicheStringProp getAmount() {
        return this.amount;
    }

    public void setAmount(FicheStringProp ficheStringProp) {
        this.amount = ficheStringProp;
    }

    public FicheStringProp getPrice() {
        return this.price;
    }

    public void setPrice(FicheStringProp ficheStringProp) {
        this.price = ficheStringProp;
    }

    public FicheDiscountRefProp getCurrency() {
        return this.currency;
    }

    public void setCurrency(FicheDiscountRefProp ficheDiscountRefProp) {
        this.currency = ficheDiscountRefProp;
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

    public boolean isPriceActive() {
        return this.isPriceActive;
    }

    public void setPriceActive(boolean z) {
        this.isPriceActive = z;
    }

    public boolean isImageActive() {
        return this.isImageActive;
    }

    public void setImageActive(boolean z) {
        this.isImageActive = z;
    }

    public boolean isNotActive() {
        return this.isNotActive;
    }

    public void setNotActive(boolean z) {
        this.isNotActive = z;
    }
    public PenetrationLine(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "in");
        this.uniqueId = IdGenerator.INSTANCE.generateNextId();
        this.uniqueId = parcel.readLong();
        this.date = parcel.readString();
        this.f1257id = parcel.readInt();
        this.lineNr = parcel.readInt();
        this.productRef = parcel.readInt();
        this.penetrationDetailId = parcel.readInt();
        this.productName = parcel.readString();
        this.productCode = parcel.readString();
        this.unit = parcel.readString();
        this.exist = parcel.readParcelable(FicheBooleanProp.class.getClassLoader());
        this.amount = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.price = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        this.currency = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        this.image = parcel.readParcelable(FicheImageProp.class.getClassLoader());
        this.not = parcel.readParcelable(FicheStringProp.class.getClassLoader());
        boolean z = false;
        this.isPriceActive = parcel.readByte() != 0;
        this.isImageActive = parcel.readByte() != 0;
        this.isNotActive = parcel.readByte() != 0 || z;
    }
    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "dest");
        parcel.writeLong(this.uniqueId);
        parcel.writeString(this.date);
        parcel.writeInt(this.f1257id);
        parcel.writeInt(this.lineNr);
        parcel.writeInt(this.productRef);
        parcel.writeInt(this.penetrationDetailId);
        parcel.writeString(this.productName);
        parcel.writeString(this.productCode);
        parcel.writeString(this.unit);
        parcel.writeParcelable(this.exist, i);
        parcel.writeParcelable(this.amount, i);
        parcel.writeParcelable(this.price, i);
        parcel.writeParcelable(this.currency, i);
        parcel.writeParcelable(this.image, i);
        parcel.writeParcelable(this.not, i);
        parcel.writeByte(this.isPriceActive ? (byte) 1 : 0);
        parcel.writeByte(this.isImageActive ? (byte) 1 : 0);
        parcel.writeByte(this.isNotActive ? (byte) 1 : 0);
    }

    public PenetrationLine() {
        this.uniqueId = IdGenerator.INSTANCE.generateNextId();
    }

    public void plusAmount() {
        FicheStringProp ficheStringProp = this.amount;
        Intrinsics.checkNotNull(ficheStringProp);
        FicheStringProp ficheStringProp2 = this.amount;
        Intrinsics.checkNotNull(ficheStringProp2);
        FicheStringProp.setDefinition(StringUtils.convertIntToString(((int) StringUtils.convertStringToDouble(ficheStringProp.getDefinition())) + 1));
    }

    public void minusAmount() {
        FicheStringProp ficheStringProp = this.amount;
        Intrinsics.checkNotNull(ficheStringProp);
        int convertStringToDouble = ((int) StringUtils.convertStringToDouble(ficheStringProp.getDefinition())) - 1;
        if (convertStringToDouble < 0) {
            convertStringToDouble = 0;
        }
        FicheStringProp ficheStringProp2 = this.amount;
        Intrinsics.checkNotNull(ficheStringProp2);
        FicheStringProp.setDefinition(StringUtils.convertIntToString(convertStringToDouble));
    }
    public static final class IdGenerator {
        public static final IdGenerator INSTANCE = new IdGenerator();
        private static final AtomicLong NEXT_ID = new AtomicLong(0);

        private IdGenerator() {
        }

        public long generateNextId() {
            return NEXT_ID.getAndIncrement();
        }
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Creator<PenetrationLine> getCREATOR() {
            return PenetrationLine.cREATOR;
        }
    }
}
