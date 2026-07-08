package com.proje.mobilesales.features.dbmodel;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import java.util.concurrent.atomic.AtomicLong;

public class ManagerOrder implements Parcelable {
    private double availableAMOUNT;
    private int branchNr;
    private String cCode;
    private String cCode2;
    private String cName;
    private String cName2;
    private String date;
    private int divisNr;
    private String docTrackingNr;
    private String explanation;
    private int factNr;
    private String ficheNo;
    long f1242id;
    private int logicalRef;
    private String orderDetailRef;
    private String paymentDefinition;
    private String sCode;
    private String sName;
    private String slsCode;
    private String slsDefinition;
    private String speCode;
    private String stockCode;
    private String stockName;
    private int time;
    private double total;
    private String tradingGrp;
    private int wareHouseNr;
    static final AtomicLong NEXT_ID = new AtomicLong(0);
    public static final Parcelable.Creator<ManagerOrder> CREATOR = new Parcelable.Creator<ManagerOrder>() {
        public ManagerOrder createFromParcel(Parcel parcel) {
            return new ManagerOrder(parcel);
        }
        public ManagerOrder[] newArray(int i2) {
            return new ManagerOrder[i2];
        }
    };
    public int describeContents() {
        return 0;
    }
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public String getFicheNo() {
        return this.ficheNo;
    }
    public void setFicheNo(String str) {
        this.ficheNo = str;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String str) {
        this.date = str;
    }
    public String getcCode() {
        return this.cCode;
    }
    public void setcCode(String str) {
        this.cCode = str;
    }
    public String getcName() {
        return this.cName;
    }
    public void setcName(String str) {
        this.cName = str;
    }
    public String getcCode2() {
        return this.cCode2;
    }
    public void setcCode2(String str) {
        this.cCode2 = str;
    }
    public String getcName2() {
        return this.cName2;
    }
    public void setcName2(String str) {
        this.cName2 = str;
    }
    public String getsCode() {
        return this.sCode;
    }
    public void setsCode(String str) {
        this.sCode = str;
    }
    public String getsName() {
        return this.sName;
    }
    public void setsName(String str) {
        this.sName = str;
    }
    public String getSlsCode() {
        return this.slsCode;
    }
    public void setSlsCode(String str) {
        this.slsCode = str;
    }
    public double getTotal() {
        return this.total;
    }
    public void setTotal(double d2) {
        this.total = d2;
    }
    public ManagerOrder() {
        this.f1242id = NEXT_ID.getAndIncrement();
    }
    public String getExplanation() {
        return this.explanation;
    }
    public void setExplanation(String str) {
        this.explanation = str;
    }
    public String getDocTrackingNr() {
        return this.docTrackingNr;
    }
    public void setDocTrackingNr(String str) {
        this.docTrackingNr = str;
    }
    public String getTradingGrp() {
        return this.tradingGrp;
    }
    public void setTradingGrp(String str) {
        this.tradingGrp = str;
    }
    public String getPaymentDefinition() {
        return this.paymentDefinition;
    }
    public void setPaymentDefinition(String str) {
        this.paymentDefinition = str;
    }
    public String getSpeCode() {
        return this.speCode;
    }
    public void setSpeCode(String str) {
        this.speCode = str;
    }
    public int getTime() {
        return this.time;
    }
    public void setTime(int i2) {
        this.time = i2;
    }
    public long getId() {
        return this.f1242id;
    }
    public String getSlsDefinition() {
        return this.slsDefinition;
    }
    public void setSlsDefinition(String str) {
        this.slsDefinition = str;
    }
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.f1242id);
        parcel.writeInt(this.logicalRef);
        parcel.writeString(this.ficheNo);
        parcel.writeString(this.date);
        parcel.writeInt(this.time);
        parcel.writeString(this.cCode);
        parcel.writeString(this.cName);
        parcel.writeString(this.cCode2);
        parcel.writeString(this.cName2);
        parcel.writeString(this.sCode);
        parcel.writeString(this.sName);
        parcel.writeString(this.slsCode);
        parcel.writeDouble(this.total);
        parcel.writeString(this.explanation);
        parcel.writeString(this.docTrackingNr);
        parcel.writeString(this.tradingGrp);
        parcel.writeString(this.paymentDefinition);
        parcel.writeString(this.speCode);
        parcel.writeString(this.stockCode);
        parcel.writeString(this.stockName);
        parcel.writeDouble(this.availableAMOUNT);
        parcel.writeString(this.orderDetailRef);
        parcel.writeString(this.slsDefinition);
    }

    protected ManagerOrder(Parcel parcel) {
        this.f1242id = NEXT_ID.getAndIncrement();
        this.f1242id = parcel.readLong();
        this.logicalRef = parcel.readInt();
        this.ficheNo = parcel.readString();
        this.date = parcel.readString();
        this.time = parcel.readInt();
        this.cCode = parcel.readString();
        this.cName = parcel.readString();
        this.cCode2 = parcel.readString();
        this.cName2 = parcel.readString();
        this.sCode = parcel.readString();
        this.sName = parcel.readString();
        this.slsCode = parcel.readString();
        this.total = parcel.readDouble();
        this.explanation = parcel.readString();
        this.docTrackingNr = parcel.readString();
        this.tradingGrp = parcel.readString();
        this.paymentDefinition = parcel.readString();
        this.speCode = parcel.readString();
        this.stockCode = parcel.readString();
        this.stockName = parcel.readString();
        this.availableAMOUNT = parcel.readDouble();
        this.orderDetailRef = parcel.readString();
        this.slsDefinition = parcel.readString();
    }

    public int getDivisNr() {
        return this.divisNr;
    }

    public void setDivisNr(int i2) {
        this.divisNr = i2;
    }

    public int getBranchNr() {
        return this.branchNr;
    }

    public void setBranchNr(int i2) {
        this.branchNr = i2;
    }

    public int getFactNr() {
        return this.factNr;
    }

    public void setFactNr(int i2) {
        this.factNr = i2;
    }

    public int getWareHouseNr() {
        return this.wareHouseNr;
    }

    public void setWareHouseNr(int i2) {
        this.wareHouseNr = i2;
    }

    public String getStockCode() {
        return this.stockCode;
    }

    public void setStockCode(String str) {
        this.stockCode = str;
    }

    public String getStockName() {
        return this.stockName;
    }

    public void setStockName(String str) {
        this.stockName = str;
    }

    public double getAvailableAMOUNT() {
        return this.availableAMOUNT;
    }

    public void setAvailableAMOUNT(double d2) {
        this.availableAMOUNT = d2;
    }

    public String getOrderDetailRef() {
        return this.orderDetailRef;
    }

    public void setOrderDetailRef(String str) {
        this.orderDetailRef = str;
    }
}
