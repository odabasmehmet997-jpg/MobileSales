package com.proje.mobilesales.features.gpsinfo.model.database;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.kxml2.wap.Wbxml;

public final class CustGpsInfo implements Parcelable {

    @Column(name = "CLCODE", netsisName = "CLCODE")
    public String clCode;

    @Column(name = "CLNAME")
    public String clName;

    @Column(isAllSame = false, name = "CLIENTREF", netsis = @ColumnProperty(useProperty = false), shared = @ColumnProperty(isNotNull = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    public int clientRef;

    /* renamed from: id */
    @Column(name = "ID", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int f1250id;

    @Column(name = "ISTRANSFER", netsisName = "ISTRANSFER", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    public int isTransfer;

    @Column(name = "LAT_COS", netsisName = "LAT_COS", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    public double latCos;

    @Column(name = "LAT_SIN", netsisName = "LAT_SIN", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    public double latSin;

    @Column(name = "LATITUDE", netsisName = "LATITUDE", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    public double latitude;

    @Column(name = "LOGICALREF", shared = @ColumnProperty(isAutoIncrement = EmbeddingCompat.DEBUG, isNotNull = EmbeddingCompat.DEBUG, isPrimaryKey = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    public int logicalRef;

    @Column(name = "LONG_COS", netsisName = "LONG_COS", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    public double longCos;

    @Column(name = "LONG_SIN", netsisName = "LONG_SIN", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    public double longSin;

    @Column(name = "LONGTITUDE", netsisName = "LONGTITUDE", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    public double longtitude;

    @Column(name = "SHIPINFOCODE", netsisName = "SHIPINFOCODE", shared = @ColumnProperty(alterVersion = Wbxml.OPAQUE, defaultValue = "", type = Column.ColumnValueTypes.TEXT))
    public String shipInfoCode;

    @Column(name = "SHIPINFOREF", netsisName = "SHIPINFOREFF", shared = @ColumnProperty(alterVersion = Wbxml.OPAQUE, defaultValue = "0", type = Column.ColumnValueTypes.INTEGER))
    public int shippingRef;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<CustGpsInfo> CREATOR = new Parcelable.Creator<CustGpsInfo>() {
        public CustGpsInfo createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new CustGpsInfo(source, null);
        }
        public CustGpsInfo[] newArray(int i2) {
            return new CustGpsInfo[i2];
        }
    };

    public  CustGpsInfo(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int i2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(this.logicalRef);
        dest.writeInt(this.f1250id);
        dest.writeInt(this.clientRef);
        dest.writeString(this.clCode);
        dest.writeString(this.clName);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longtitude);
        dest.writeInt(this.isTransfer);
        dest.writeDouble(this.latCos);
        dest.writeDouble(this.latSin);
        dest.writeDouble(this.longCos);
        dest.writeDouble(this.longSin);
        dest.writeInt(this.shippingRef);
        dest.writeString(this.shipInfoCode);
    }

    public CustGpsInfo() {
    }

    private CustGpsInfo(Parcel parcel) {
        this.logicalRef = parcel.readInt();
        this.f1250id = parcel.readInt();
        this.clientRef = parcel.readInt();
        this.clCode = parcel.readString();
        this.clName = parcel.readString();
        this.latitude = parcel.readDouble();
        this.longtitude = parcel.readDouble();
        this.isTransfer = parcel.readInt();
        this.latCos = parcel.readDouble();
        this.latSin = parcel.readDouble();
        this.longCos = parcel.readDouble();
        this.longSin = parcel.readDouble();
        this.shippingRef = parcel.readInt();
        this.shipInfoCode = parcel.readString();
    }

    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
