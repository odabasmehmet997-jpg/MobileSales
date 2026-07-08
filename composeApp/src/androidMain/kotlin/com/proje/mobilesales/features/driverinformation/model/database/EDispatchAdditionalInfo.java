package com.proje.mobilesales.features.driverinformation.model.database;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.window.embedding.EmbeddingCompat;
import com.google.gson.annotations.Expose;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class EDispatchAdditionalInfo implements Parcelable, Cloneable {

    @Column(name = "CARRIERCITY", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String carrierCity;

    @Column(name = "CARRIERCOUNTRY", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String carrierCountry;

    @Column(name = "CARRIERCOUNTY", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String carrierCounty;

    @Column(name = "CARRIERNAME", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String carrierName;

    @Column(name = "CARRIERPOSTCODE", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String carrierPostCode;

    @Column(name = "CARRIERTAXNR", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String carrierTaxNr;

    @Column(name = "FIRSTDRIVERDESCRIPTION", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String firstDriverDescription;

    @Column(name = "FIRSTDRIVERIDENTITYNR", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String firstDriverIdentityNr;

    @Column(name = "FIRSTDRIVERLASTNAME", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String firstDriverLastName;

    @Column(name = "FIRSTDRIVERNAME", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String firstDriverName;

    @Column(name = "PLATE", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String firstDriverPlate;

    @Column(name = "FIRSTTRAILERPLATE", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String firstTrailerPlate;

    @Column(name = "LOGICALREF", shared = @ColumnProperty(isAutoIncrement = EmbeddingCompat.DEBUG, isPrimaryKey = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    private int logicalRef;

    @Column(name = "SALESFICHEID", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int salesFicheId;

    @Column(name = "SALESTYPE", shared = @ColumnProperty(alterVersion = 173, defaultValue = "4", type = Column.ColumnValueTypes.INTEGER))
    public int salesType;

    @Column(name = "SECONDDRIVERDESCRIPTION", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String secondDriverDescription;

    @Column(name = "SECONDDRIVERIDENTITYNR", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String secondDriverIdentityNr;

    @Column(name = "SECONDDRIVERLASTNAME", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String secondDriverLastName;

    @Column(name = "SECONDDRIVERNAME", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String secondDriverName;

    @Column(name = "SECONDDRIVERPLATE", shared = @ColumnProperty(alterVersion = 199, type = Column.ColumnValueTypes.TEXT))
    public String secondDriverPlate;

    @Column(name = "SECONDTRAILERPLATE", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String secondTrailerPlate;

    @Column(name = "SHIPMENTDATE", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String shipmentDate;

    @Column(name = "THIRDDRIVERDESCRIPTION", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String thirdDriverDescription;

    @Column(name = "THIRDDRIVERIDENTITYNR", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String thirdDriverIdentityNr;

    @Column(name = "THIRDDRIVERLASTNAME", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String thirdDriverLastName;

    @Column(name = "THIRDDRIVERNAME", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    public String thirdDriverName;

    @Column(name = "THIRDDRIVEERPLATE", shared = @ColumnProperty(alterVersion = 199, type = Column.ColumnValueTypes.TEXT))
    public String thirdDriverPlate;

    @Column(name = "THIRDTRAILERPLATE", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    @Expose
    public String thirdTrailerPlate;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<EDispatchAdditionalInfo> CREATOR = new Parcelable.Creator<EDispatchAdditionalInfo>() { // from class: com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfoCompanionCREATOR1
        
        
        public EDispatchAdditionalInfo createFromParcel(final Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new EDispatchAdditionalInfo(source, null);
        }

        
        
        public EDispatchAdditionalInfo[] newArray(final int i2) {
            return new EDispatchAdditionalInfo[i2];
        }
    };

    public EDispatchAdditionalInfo(final Parcel parcel, final DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getLogicalRef() {
        return logicalRef;
    }

    public void setLogicalRef(final int i2) {
        logicalRef = i2;
    }

    public EDispatchAdditionalInfo() {
    }

    public EDispatchAdditionalInfo m1391clone() throws CloneNotSupportedException {
        final Object clone = clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type com.proje.mobilesales.features.driverinformation.model.database.EDispatchAdditionalInfo");
        final EDispatchAdditionalInfo eDispatchAdditionalInfo = (EDispatchAdditionalInfo) clone;
        eDispatchAdditionalInfo.logicalRef = 0;
        eDispatchAdditionalInfo.salesFicheId = 0;
        eDispatchAdditionalInfo.shipmentDate = "";
        return eDispatchAdditionalInfo;
    }

    public void writeToParcel(final Parcel dest, final int i2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(logicalRef);
        dest.writeInt(salesFicheId);
        dest.writeString(firstDriverPlate);
        dest.writeString(carrierTaxNr);
        dest.writeString(carrierName);
        dest.writeString(carrierCounty);
        dest.writeString(carrierCity);
        dest.writeString(carrierCountry);
        dest.writeString(carrierPostCode);
        dest.writeString(firstDriverName);
        dest.writeString(firstDriverLastName);
        dest.writeString(firstDriverDescription);
        dest.writeString(firstDriverIdentityNr);
        dest.writeString(secondDriverName);
        dest.writeString(secondDriverLastName);
        dest.writeString(secondDriverDescription);
        dest.writeString(secondDriverIdentityNr);
        dest.writeString(thirdDriverName);
        dest.writeString(thirdDriverLastName);
        dest.writeString(thirdDriverDescription);
        dest.writeString(thirdDriverIdentityNr);
        dest.writeString(shipmentDate);
        dest.writeString(firstTrailerPlate);
        dest.writeString(secondTrailerPlate);
        dest.writeString(thirdTrailerPlate);
        dest.writeString(secondDriverPlate);
        dest.writeString(thirdDriverPlate);
    }

    private EDispatchAdditionalInfo(final Parcel parcel) {
        logicalRef = parcel.readInt();
        salesFicheId = parcel.readInt();
        firstDriverPlate = parcel.readString();
        carrierTaxNr = parcel.readString();
        carrierName = parcel.readString();
        carrierCounty = parcel.readString();
        carrierCity = parcel.readString();
        carrierCountry = parcel.readString();
        carrierPostCode = parcel.readString();
        firstDriverName = parcel.readString();
        firstDriverLastName = parcel.readString();
        firstDriverDescription = parcel.readString();
        firstDriverIdentityNr = parcel.readString();
        secondDriverName = parcel.readString();
        secondDriverLastName = parcel.readString();
        secondDriverDescription = parcel.readString();
        secondDriverIdentityNr = parcel.readString();
        thirdDriverName = parcel.readString();
        thirdDriverLastName = parcel.readString();
        thirdDriverDescription = parcel.readString();
        thirdDriverIdentityNr = parcel.readString();
        shipmentDate = parcel.readString();
        firstTrailerPlate = parcel.readString();
        secondTrailerPlate = parcel.readString();
        thirdTrailerPlate = parcel.readString();
        secondDriverPlate = parcel.readString();
        thirdDriverPlate = parcel.readString();
    }

    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
