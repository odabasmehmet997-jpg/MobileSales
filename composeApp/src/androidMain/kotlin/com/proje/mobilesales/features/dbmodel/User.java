package com.proje.mobilesales.features.dbmodel;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.utils.StringUtils;
import org.kxml2.wap.Wbxml;

public class User implements Parcelable {
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        public User[] newArray(int i2) {
            return new User[i2];
        }
    };
    private String addres;
    private int branchNr;
    private String code;
    private int costGrp;
    private String cyphCode;
    private String dbName;
    private int doShip;
    private int eInvoiceTypSGK;
    private String email;
    private int factNr;
    private String firmName;
    private String firmNr;
    private String groupCode;
    private String intSalesAddr;
    private boolean loggedIn;
    private int logoLogicalRef;
    private int logoNr;
    private String logoUserName;
    private String name;
    private int panelVersion;
    private String password;
    private String peridodNr;
    private String periodEnd;
    private String periodStart;
    private int salesPersonRef;
    private String speCode;
    private String taxPayerCode;
    private String taxPayerName;
    private String type;
    private int userId;
    private String userName;
    private int userRoleId;
    private int vehicleRef;
    private int warehouseNr;
    private int warehouseRef;
    private String workTime;
    public int describeContents() {
        return 0;
    }

    protected User(Parcel parcel) {
        boolean readBoolean;
        this.userRoleId = 0;
        this.salesPersonRef = 0;
        this.firmName = "";
        this.groupCode = "";
        this.userId = parcel.readInt();
        this.code = parcel.readString();
        this.userName = parcel.readString();
        this.password = parcel.readString();
        this.name = parcel.readString();
        this.firmNr = parcel.readString();
        this.addres = parcel.readString();
        this.type = parcel.readString();
        this.peridodNr = parcel.readString();
        this.workTime = parcel.readString();
        this.userRoleId = parcel.readInt();
        this.salesPersonRef = parcel.readInt();
        this.firmName = parcel.readString();
        this.groupCode = parcel.readString();
        this.logoNr = parcel.readInt();
        this.logoLogicalRef = parcel.readInt();
        this.dbName = parcel.readString();
        this.doShip = parcel.readInt();
        this.logoUserName = parcel.readString();
        this.eInvoiceTypSGK = parcel.readInt();
        this.taxPayerCode = parcel.readString();
        this.taxPayerName = parcel.readString();
        this.vehicleRef = parcel.readInt();
        this.email = parcel.readString();
        this.speCode = parcel.readString();
        this.intSalesAddr = parcel.readString();
        this.cyphCode = parcel.readString();
        this.warehouseRef = parcel.readInt();
        setWarehouseNr(parcel.readInt());
        setBranchNr(parcel.readInt());
        setFactNr(parcel.readInt());
        setCostGrp(parcel.readInt());
        setPanelVersion(parcel.readInt());
        readBoolean = parcel.readBoolean();
        this.loggedIn = readBoolean;
        setPeriodStart(parcel.readString());
        setPeriodEnd(parcel.readString());
    }
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.userId);
        parcel.writeString(this.code);
        parcel.writeString(this.userName);
        parcel.writeString(this.password);
        parcel.writeString(this.name);
        parcel.writeString(this.firmNr);
        parcel.writeString(this.addres);
        parcel.writeString(this.type);
        parcel.writeString(this.peridodNr);
        parcel.writeString(this.workTime);
        parcel.writeInt(this.userRoleId);
        parcel.writeInt(this.salesPersonRef);
        parcel.writeString(this.firmName);
        parcel.writeString(this.groupCode);
        parcel.writeInt(this.logoNr);
        parcel.writeInt(this.logoLogicalRef);
        parcel.writeString(this.dbName);
        parcel.writeInt(this.doShip);
        parcel.writeString(this.logoUserName);
        parcel.writeInt(this.eInvoiceTypSGK);
        parcel.writeString(this.taxPayerCode);
        parcel.writeString(this.taxPayerName);
        parcel.writeInt(this.vehicleRef);
        parcel.writeString(this.email);
        parcel.writeString(this.speCode);
        parcel.writeString(this.intSalesAddr);
        parcel.writeString(this.cyphCode);
        parcel.writeInt(this.warehouseRef);
        parcel.writeInt(getWarehouseNr());
        parcel.writeInt(getBranchNr());
        parcel.writeInt(getFactNr());
        parcel.writeInt(getCostGrp());
        parcel.writeInt(this.panelVersion);
        parcel.writeBoolean(this.loggedIn);
        parcel.writeString(getPeriodStart());
        parcel.writeString(getPeriodEnd());
    }
    class C26971 implements Parcelable.Creator<User> {
        C26971() {
        }

        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }  
        public User[] newArray(int i2) {
            return new User[i2];
        }
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int i2) {
        this.userId = i2;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getFirmNr() {
        return this.firmNr;
    }

    public void setFirmNr(String str) {
        this.firmNr = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getPeridodNr() {
        return this.peridodNr;
    }

    public void setPeridodNr(String str) {
        this.peridodNr = str;
    }

    public String getWorkTime() {
        return this.workTime;
    }

    public void setWorkTime(String str) {
        this.workTime = str;
    }

    public String getAddres() {
        return this.addres;
    }

    public void setAddres(String str) {
        this.addres = str;
    }

    public int getSalesPersonRef() {
        return this.salesPersonRef;
    }

    public void setSalesPersonRef(int i2) {
        this.salesPersonRef = i2;
    }

    public int getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(int i2) {
        this.userRoleId = i2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getFirmName() {
        return this.firmName;
    }

    public void setFirmName(String str) {
        this.firmName = str;
    }

    public String getGroupCode() {
        return this.groupCode;
    }

    public void setGroupCode(String str) {
        this.groupCode = str;
    }

    public int getLogoLogicalRef() {
        return this.logoLogicalRef;
    }

    public void setLogoLogicalRef(int i2) {
        this.logoLogicalRef = i2;
    }

    public int getLogoNr() {
        return this.logoNr;
    }

    public void setLogoNr(int i2) {
        this.logoNr = i2;
    }

    public String getDbName() {
        if (TextUtils.isEmpty(this.dbName)) {
            return "";
        }
        return String.format("%s.%s.", this.dbName, "dbo");
    }

    public void setDbName(String str) {
        this.dbName = str;
    }

    public boolean isDoShip() {
        return this.doShip == 1;
    }

    public void setDoShip(int i2) {
        this.doShip = i2;
    }

    public String getLogoUserName() {
        return this.logoUserName;
    }

    public void setLogoUserName(String str) {
        this.logoUserName = str;
    }

    public int geteInvoiceTypSGK() {
        return this.eInvoiceTypSGK;
    }

    public void seteInvoiceTypSGK(int i2) {
        this.eInvoiceTypSGK = i2;
    }

    public String getTaxPayerCode() {
        return this.taxPayerCode;
    }

    public void setTaxPayerCode(String str) {
        this.taxPayerCode = str;
    }

    public String getTaxPayerName() {
        return this.taxPayerName;
    }

    public void setTaxPayerName(String str) {
        this.taxPayerName = str;
    }

    public User() {
        this.userRoleId = 0;
        this.salesPersonRef = 0;
        this.firmName = "";
        this.groupCode = "";
    }

    public int getVehicleRef() {
        return this.vehicleRef;
    }

    public void setVehicleRef(int i2) {
        this.vehicleRef = i2;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getSpeCode() {
        return this.speCode;
    }

    public void setSpeCode(String str) {
        this.speCode = str;
    }

    public String getIntSalesAddr() {
        return this.intSalesAddr;
    }

    public void setIntSalesAddr(String str) {
        this.intSalesAddr = str;
    }

    public String getCyphCode() {
        return this.cyphCode;
    }

    public void setCyphCode(String str) {
        this.cyphCode = str;
    }

    public int getWarehouseRef() {
        return this.warehouseRef;
    }

    public void setWarehouseRef(int i2) {
        this.warehouseRef = i2;
    }

    public int getWarehouseNr() {
        return this.warehouseNr;
    }

    public void setWarehouseNr(int i2) {
        this.warehouseNr = i2;
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

    public int getCostGrp() {
        return this.costGrp;
    }

    public void setCostGrp(int i2) {
        this.costGrp = i2;
    }

    public int getPanelVersion() {
        return this.panelVersion;
    }

    public void setPanelVersion(int i2) {
        this.panelVersion = i2;
    }

    public int getPeridodNrInt() {
        return StringUtils.convertStringToInt(this.peridodNr);
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public void setLoggedIn(boolean z) {
        this.loggedIn = z;
    }

    public boolean isSalesManager() {
        return this.type.equals(ExifInterface.GPS_MEASUREMENT_2D);
    }

    public String getPeriodStart() {
        return this.periodStart;
    }

    public void setPeriodStart(String str) {
        this.periodStart = str;
    }

    public String getPeriodEnd() {
        return this.periodEnd;
    }

    public void setPeriodEnd(String str) {
        this.periodEnd = str;
    }
}
