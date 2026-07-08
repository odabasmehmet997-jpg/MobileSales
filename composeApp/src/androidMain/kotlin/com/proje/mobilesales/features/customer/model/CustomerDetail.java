package com.proje.mobilesales.features.customer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerDetail extends Customer implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private String address1;
    private String address2;
    private String city;
    private String country;
    private double discRate;
    private int dueDate;
    private String groupCode;
    private String invoiceAddress;
    private boolean isPersonalCompany;
    private String name;
    private int paymentType;
    private double pendingOrderTotal;
    private String person2;
    private String person3;
    private String sAddress1;
    private String sAddress2;
    private String sCity;
    private String sCode;
    private int sRef;
    private String shipAddress;
    private String speCode1;
    private String speCode2;
    private String speCode3;
    private String speCode4;
    private String speCode5;
    private String surname;
    private String taxNo;
    private String taxOffice;
    private String tcNo;
    private String town;
    private String tradingGroup;
    private String warrantyCode;

    public int describeContents() {
        return 0;
    }

    public void setShipAddress(String str) {
        this.shipAddress = str;
    }

    public String getShipAddress() {
        return StringUtils.catStringNewLine(this.sCode, this.sAddress1, this.sAddress2, this.sCity);
    }

    public void setInvoiceAddress(String str) {
        this.invoiceAddress = str;
    }

    public String getInvoiceAddress() {
        return StringUtils.catStringNewLine(this.address1, this.address2, getTown(), getCity());
    }

    public String getSpeCode1() {
        return this.speCode1;
    }

    public void setSpeCode1(String str) {
        this.speCode1 = str;
    }

    public String getSpeCode2() {
        return this.speCode2;
    }

    public void setSpeCode2(String str) {
        this.speCode2 = str;
    }

    public String getSpeCode3() {
        return this.speCode3;
    }

    public void setSpeCode3(String str) {
        this.speCode3 = str;
    }

    public String getSpeCode4() {
        return this.speCode4;
    }

    public void setSpeCode4(String str) {
        this.speCode4 = str;
    }

    public String getSpeCode5() {
        return this.speCode5;
    }

    public void setSpeCode5(String str) {
        this.speCode5 = str;
    }

    public String getGroupCode() {
        return this.groupCode;
    }

    public void setGroupCode(String str) {
        this.groupCode = str;
    }

    public String getTaxOffice() {
        return this.taxOffice;
    }

    public void setTaxOffice(String str) {
        this.taxOffice = str;
    }

    public String getTaxNo() {
        return this.taxNo;
    }

    public void setTaxNo(String str) {
        this.taxNo = str;
    }

    public String getTcNo() {
        return this.tcNo;
    }

    public void setTcNo(String str) {
        this.tcNo = str;
    }

    public String getWarrantyCode() {
        return this.warrantyCode;
    }

    public void setWarrantyCode(String str) {
        this.warrantyCode = str;
    }

    public boolean isPersonalCompany() {
        return this.isPersonalCompany;
    }

    public void setPersonalCompany(boolean z) {
        this.isPersonalCompany = z;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String str) {
        this.surname = str;
    }

    public String getTradingGroup() {
        return this.tradingGroup;
    }

    public void setTradingGroup(String str) {
        this.tradingGroup = str;
    }

    public String getPerson2() {
        return this.person2;
    }

    public void setPerson2(String str) {
        this.person2 = str;
    }

    public String getPerson3() {
        return this.person3;
    }

    public void setPerson3(String str) {
        this.person3 = str;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String str) {
        this.address1 = str;
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String str) {
        this.address2 = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String str) {
        this.town = str;
    }

    public String getSAddress1() {
        return this.sAddress1;
    }

    public void setSAddress1(String str) {
        this.sAddress1 = str;
    }

    public String getSAddress2() {
        return this.sAddress2;
    }

    public void setSAddress2(String str) {
        this.sAddress2 = str;
    }

    public String getSCity() {
        return this.sCity;
    }

    public void setSCity(String str) {
        this.sCity = str;
    }

    public String getSCode() {
        return this.sCode;
    }

    public void setSCode(String str) {
        this.sCode = str;
    }

    public int getSRef() {
        return this.sRef;
    }

    public void setSRef(int i2) {
        this.sRef = i2;
    }

    public double getDiscRate() {
        return this.discRate;
    }

    public void setDiscRate(double d2) {
        this.discRate = d2;
    }

    public double getPendingOrderTotal() {
        return this.pendingOrderTotal;
    }

    public void setPendingOrderTotal(double d2) {
        this.pendingOrderTotal = d2;
    }

    public int getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(int i2) {
        this.paymentType = i2;
    }

    public int getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(int i2) {
        this.dueDate = i2;
    }

    public CustomerDetail() {
        this.shipAddress = "";
        this.invoiceAddress = "";
        this.speCode1 = "";
        this.speCode2 = "";
        this.speCode3 = "";
        this.speCode4 = "";
        this.speCode5 = "";
        this.groupCode = "";
        this.taxOffice = "";
        this.taxNo = "";
        this.tcNo = "";
        this.warrantyCode = "";
        this.name = "";
        this.surname = "";
        this.tradingGroup = "";
        this.person2 = "";
        this.person3 = "";
        this.address1 = "";
        this.address2 = "";
        this.country = "";
        this.city = "";
        this.town = "";
        this.sAddress1 = "";
        this.sAddress2 = "";
        this.sCity = "";
        this.sCode = "";
    }

    public CustomerDetail(Parcel parcel) {
        super(parcel);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.shipAddress = "";
        this.invoiceAddress = "";
        this.speCode1 = "";
        this.speCode2 = "";
        this.speCode3 = "";
        this.speCode4 = "";
        this.speCode5 = "";
        this.groupCode = "";
        this.taxOffice = "";
        this.taxNo = "";
        this.tcNo = "";
        this.warrantyCode = "";
        this.name = "";
        this.surname = "";
        this.tradingGroup = "";
        this.person2 = "";
        this.person3 = "";
        this.address1 = "";
        this.address2 = "";
        this.country = "";
        this.city = "";
        this.town = "";
        this.sAddress1 = "";
        this.sAddress2 = "";
        this.sCity = "";
        this.sCode = "";
        this.shipAddress = parcel.readString();
        this.invoiceAddress = parcel.readString();
        this.speCode1 = parcel.readString();
        this.speCode2 = parcel.readString();
        this.speCode3 = parcel.readString();
        this.speCode4 = parcel.readString();
        this.speCode5 = parcel.readString();
        this.groupCode = parcel.readString();
        this.taxOffice = parcel.readString();
        this.taxNo = parcel.readString();
        this.tcNo = parcel.readString();
        this.warrantyCode = parcel.readString();
        this.isPersonalCompany = parcel.readByte() != 0;
        setName(parcel.readString());
        setSurname(parcel.readString());
        this.tradingGroup = parcel.readString();
        this.person2 = parcel.readString();
        this.person3 = parcel.readString();
        setCountry(parcel.readString());
        setCity(parcel.readString());
        setTown(parcel.readString());
        this.address1 = parcel.readString();
        this.address2 = parcel.readString();
        this.sCity = parcel.readString();
        this.sAddress1 = parcel.readString();
        this.sAddress2 = parcel.readString();
        this.sCode = parcel.readString();
        this.sRef = parcel.readInt();
        this.pendingOrderTotal = parcel.readDouble();
        this.paymentType = parcel.readInt();
        this.dueDate = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        super.writeToParcel(parcel, i2);
        parcel.writeString(getShipAddress());
        parcel.writeString(getInvoiceAddress());
        parcel.writeString(this.speCode1);
        parcel.writeString(this.speCode2);
        parcel.writeString(this.speCode3);
        parcel.writeString(this.speCode4);
        parcel.writeString(this.speCode5);
        parcel.writeString(this.groupCode);
        parcel.writeString(this.taxOffice);
        parcel.writeString(this.taxNo);
        parcel.writeString(this.tcNo);
        parcel.writeString(this.warrantyCode);
        parcel.writeByte(this.isPersonalCompany ? (byte) 1 : (byte) 0);
        parcel.writeString(getName());
        parcel.writeString(getSurname());
        parcel.writeString(this.tradingGroup);
        parcel.writeString(this.person2);
        parcel.writeString(this.person3);
        parcel.writeString(getCountry());
        parcel.writeString(getCity());
        parcel.writeString(getTown());
        parcel.writeString(this.address1);
        parcel.writeString(this.address2);
        parcel.writeString(this.sCity);
        parcel.writeString(this.sAddress1);
        parcel.writeString(this.sAddress2);
        parcel.writeString(this.sCode);
        parcel.writeInt(this.sRef);
        parcel.writeDouble(this.pendingOrderTotal);
        parcel.writeInt(this.paymentType);
        parcel.writeInt(this.dueDate);
    }

    public String getPaymentTypeDefinition() {
        return ContextUtils.getStringArrayResource(R.array.array_fiche_netsis_paytype)[this.paymentType];
    }
    public static final class CREATOR implements Parcelable.Creator<CustomerDetail> {
        public  CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }
        public CustomerDetail createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new CustomerDetail(parcel);
        }
        public CustomerDetail[] newArray(int i2) {
            return new CustomerDetail[i2];
        }
    }
}
