package com.proje.mobilesales.features.customer.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class Customer implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private String average;
    private double balance;
    private String beforeBalance;
    private int cabinRef;
    private String city;
    private String cityCode;
    private String code;
    private String country;
    private String countryCode;
    private double credit;
    private double debit;
    private boolean eInvoiceUser;
    private String email;
    private String email2;
    private boolean hasDailyOperation;
    private Bitmap image;
    private String infoNote;
    private String lastOrderDate;
    private double latitude;
    private int logicalRef;
    private double longtitude;
    private String name;
    private String payPlan;
    private String person;
    private String postCode;
    private int routeDayRef;
    private int routePlanRef;
    private int routeUserCustomerRef;
    private String secondTitle;
    private String shipName;
    private int shipRef;
    private String surname;
    private String taxNr;
    private String tel;
    private String tel2;
    private String title;
    private String town;
    private String townCode;
    public int describeContents() {
        return 0;
    }
    public final int getLogicalRef() {
        return this.logicalRef;
    }
    public final void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public final String getTitle() {
        return this.title;
    }
    public final void setTitle(String str) {
        this.title = str;
    }
    public final String getCode() {
        return this.code;
    }
    public final void setCode(String str) {
        this.code = str;
    }
    public final String getInfoNote() {
        return this.infoNote;
    }
    public final void setInfoNote(String str) {
        this.infoNote = str;
    }
    public final String getPerson() {
        return this.person;
    }
    public final void setPerson(String str) {
        this.person = str;
    }
    public final String getTel() {
        return this.tel;
    }
    public final void setTel(String str) {
        this.tel = str;
    }
    public final String getTel2() {
        return this.tel2;
    }
    public final void setTel2(String str) {
        this.tel2 = str;
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
    public final String getEmail() {
        return this.email;
    }
    public final void setEmail(String str) {
        this.email = str;
    }
    public final String getEmail2() {
        return this.email2;
    }
    public final void setEmail2(String str) {
        this.email2 = str;
    }
    public final double getDebit() {
        return this.debit;
    }
    public final void setDebit(double d2) {
        this.debit = d2;
    }
    public final double getCredit() {
        return this.credit;
    }
    public final void setCredit(double d2) {
        this.credit = d2;
    }
    public final double getBalance() {
        return this.balance;
    }
    public final void setBalance(double d2) {
        this.balance = d2;
    }
    public final String getLastOrderDate() {
        return this.lastOrderDate;
    }
    public final void setLastOrderDate(String str) {
        this.lastOrderDate = str;
    }
    public final String getPayPlan() {
        return this.payPlan;
    }
    public final void setPayPlan(String str) {
        this.payPlan = str;
    }
    public final String getAverage() {
        return this.average;
    }
    public final void setAverage(String str) {
        this.average = str;
    }
    public final double getLongtitude() {
        return this.longtitude;
    }

    public final void setLongtitude(double d2) {
        this.longtitude = d2;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final void setLatitude(double d2) {
        this.latitude = d2;
    }

    public final boolean getEInvoiceUser() {
        return this.eInvoiceUser;
    }

    public final void setEInvoiceUser(boolean z) {
        this.eInvoiceUser = z;
    }

    public final Bitmap getImage() {
        return this.image;
    }

    public final void setImage(Bitmap bitmap) {
        this.image = bitmap;
    }

    public final int getShipRef() {
        return this.shipRef;
    }

    public final void setShipRef(int i2) {
        this.shipRef = i2;
    }

    public final String getShipName() {
        return this.shipName;
    }

    public final void setShipName(String str) {
        this.shipName = str;
    }

    public final int getRoutePlanRef() {
        return this.routePlanRef;
    }

    public final void setRoutePlanRef(int i2) {
        this.routePlanRef = i2;
    }

    public final int getRouteDayRef() {
        return this.routeDayRef;
    }

    public final void setRouteDayRef(int i2) {
        this.routeDayRef = i2;
    }

    public final int getRouteUserCustomerRef() {
        return this.routeUserCustomerRef;
    }

    public final void setRouteUserCustomerRef(int i2) {
        this.routeUserCustomerRef = i2;
    }

    public final int getCabinRef() {
        return this.cabinRef;
    }

    public final void setCabinRef(int i2) {
        this.cabinRef = i2;
    }

    public final String getBeforeBalance() {
        return this.beforeBalance;
    }

    public final void setBeforeBalance(String str) {
        this.beforeBalance = str;
    }

    public final String getTaxNr() {
        return this.taxNr;
    }

    public final void setTaxNr(String str) {
        this.taxNr = str;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String str) {
        this.town = str;
    }

    public final String getTownCode() {
        return this.townCode;
    }

    public final void setTownCode(String str) {
        this.townCode = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public final String getCityCode() {
        return this.cityCode;
    }

    public final void setCityCode(String str) {
        this.cityCode = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final void setCountryCode(String str) {
        this.countryCode = str;
    }
    public final String getPostCode() {
        return this.postCode;
    }
    public final void setPostCode(String str) {
        this.postCode = str;
    }
    public final String getSecondTitle() {
        return this.secondTitle;
    }
    public final void setSecondTitle(String str) {
        this.secondTitle = str;
    }
    public final boolean getHasDailyOperation() {
        return this.hasDailyOperation;
    }
    public final void setHasDailyOperation(boolean z) {
        this.hasDailyOperation = z;
    }
    public Customer() {
    }
    public Customer(Parcel parcel) {
        this();
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.logicalRef = parcel.readInt();
        this.title = parcel.readString();
        this.code = parcel.readString();
        this.infoNote = parcel.readString();
        this.person = parcel.readString();
        this.tel = parcel.readString();
        this.email = parcel.readString();
        this.email2 = parcel.readString();
        setName(parcel.readString());
        setSurname(parcel.readString());
        this.debit = parcel.readDouble();
        this.credit = parcel.readDouble();
        this.balance = parcel.readDouble();
        this.lastOrderDate = parcel.readString();
        this.payPlan = parcel.readString();
        this.average = parcel.readString();
        this.longtitude = parcel.readDouble();
        this.latitude = parcel.readDouble();
        this.eInvoiceUser = parcel.readByte() != 0;
        this.image = parcel.readParcelable(Bitmap.class.getClassLoader());
        this.shipRef = parcel.readInt();
        this.shipName = parcel.readString();
        this.routeDayRef = parcel.readInt();
        this.routePlanRef = parcel.readInt();
        this.routeUserCustomerRef = parcel.readInt();
        this.cabinRef = parcel.readInt();
        this.beforeBalance = parcel.readString();
        this.taxNr = parcel.readString();
        setTown(parcel.readString());
        this.townCode = parcel.readString();
        setCity(parcel.readString());
        this.cityCode = parcel.readString();
        setCountry(parcel.readString());
        this.countryCode = parcel.readString();
        this.postCode = parcel.readString();
        this.secondTitle = parcel.readString();
        this.hasDailyOperation = parcel.readByte() != 0;
    }
    public void writeToParcel(Parcel parcel, int i2) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(this.logicalRef);
        parcel.writeString(this.title);
        parcel.writeString(this.code);
        parcel.writeString(this.infoNote);
        parcel.writeString(this.person);
        parcel.writeString(this.tel);
        parcel.writeString(this.email);
        parcel.writeString(this.email2);
        parcel.writeString(getName());
        parcel.writeString(getSurname());
        parcel.writeDouble(this.debit);
        parcel.writeDouble(this.credit);
        parcel.writeDouble(this.balance);
        parcel.writeString(this.lastOrderDate);
        parcel.writeString(this.payPlan);
        parcel.writeString(this.average);
        parcel.writeDouble(this.longtitude);
        parcel.writeDouble(this.latitude);
        parcel.writeByte(this.eInvoiceUser ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.image, i2);
        parcel.writeInt(this.shipRef);
        parcel.writeString(this.shipName);
        parcel.writeInt(this.routeDayRef);
        parcel.writeInt(this.routePlanRef);
        parcel.writeInt(this.routeUserCustomerRef);
        parcel.writeInt(this.cabinRef);
        parcel.writeString(this.beforeBalance);
        parcel.writeString(this.taxNr);
        parcel.writeString(getTown());
        parcel.writeString(this.townCode);
        parcel.writeString(getCity());
        parcel.writeString(this.cityCode);
        parcel.writeString(getCountry());
        parcel.writeString(this.countryCode);
        parcel.writeString(this.postCode);
        parcel.writeString(this.secondTitle);
        parcel.writeByte(this.hasDailyOperation ? (byte) 1 : (byte) 0);
    }
    public static final class CREATOR implements Parcelable.Creator<Customer> {
        public   CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private CREATOR() {
        }
        public Customer createFromParcel(Parcel source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new Customer(source);
        }
        public Customer[] newArray(int i2) {
            return new Customer[i2];
        }
    }
}
