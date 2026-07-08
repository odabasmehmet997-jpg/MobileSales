package com.proje.mobilesales.features.sales.model.database;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class LastOrderProducts implements Parcelable {
    private String amount;
    private String date;
    private String discount;
    private String lineNet;
    private String price;
    private String shippedAmount;
    private String stockCode;
    private String stockName;
    public LastOrderProducts() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }
    protected LastOrderProducts(Parcel in) {
        amount = in.readString();
        date = in.readString();
        discount = in.readString();
        lineNet = in.readString();
        price = in.readString();
        shippedAmount = in.readString();
        stockCode = in.readString();
        stockName = in.readString();
    }
    public static final Creator<LastOrderProducts> CREATOR = new Creator<LastOrderProducts>() {
        @Override
        public LastOrderProducts createFromParcel(Parcel in) {
            return new LastOrderProducts(in);
        }

        @Override
        public LastOrderProducts[] newArray(int size) {
            return new LastOrderProducts[size];
        }
    };
    public final String component1() {
        return this.amount;
    }
    public final String component2() {
        return this.shippedAmount;
    }
    public final String component3() {
        return this.price;
    }
    public final String component4() {
        return this.discount;
    }
    public final String component5() {
        return this.lineNet;
    }
    public final String component6() {
        return this.date;
    }
    public final String component7() {
        return this.stockName;
    }
    public final String component8() {
        return this.stockCode;
    }
    public final LastOrderProducts copy(String amount, String shippedAmount, String price, String discount, String lineNet, String date, String stockName, String stockCode) {
        Intrinsics.checkNotNullParameter(amount, "amount");
        Intrinsics.checkNotNullParameter(shippedAmount, "shippedAmount");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(discount, "discount");
        Intrinsics.checkNotNullParameter(lineNet, "lineNet");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(stockName, "stockName");
        Intrinsics.checkNotNullParameter(stockCode, "stockCode");
        return new LastOrderProducts(amount, shippedAmount, price, discount, lineNet, date, stockName, stockCode);
    }
    public int describeContents() {
        return 0;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LastOrderProducts lastOrderProducts)) {
            return false;
        }
        return Intrinsics.areEqual(this.amount, lastOrderProducts.amount) && Intrinsics.areEqual(this.shippedAmount, lastOrderProducts.shippedAmount) && Intrinsics.areEqual(this.price, lastOrderProducts.price) && Intrinsics.areEqual(this.discount, lastOrderProducts.discount) && Intrinsics.areEqual(this.lineNet, lastOrderProducts.lineNet) && Intrinsics.areEqual(this.date, lastOrderProducts.date) && Intrinsics.areEqual(this.stockName, lastOrderProducts.stockName) && Intrinsics.areEqual(this.stockCode, lastOrderProducts.stockCode);
    }
    public int hashCode() {
        return (((((((((((((this.amount.hashCode() * 31) + this.shippedAmount.hashCode()) * 31) + this.price.hashCode()) * 31) + this.discount.hashCode()) * 31) + this.lineNet.hashCode()) * 31) + this.date.hashCode()) * 31) + this.stockName.hashCode()) * 31) + this.stockCode.hashCode();
    }
    public String toString() {
        return "LastOrderProducts(amount=" + this.amount + ", shippedAmount=" + this.shippedAmount + ", price=" + this.price + ", discount=" + this.discount + ", lineNet=" + this.lineNet + ", date=" + this.date + ", stockName=" + this.stockName + ", stockCode=" + this.stockCode + ')';
    }
    public LastOrderProducts(String amount, String shippedAmount, String price, String discount, String lineNet, String date, String stockName, String stockCode) {
        Intrinsics.checkNotNullParameter(amount, "amount");
        Intrinsics.checkNotNullParameter(shippedAmount, "shippedAmount");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(discount, "discount");
        Intrinsics.checkNotNullParameter(lineNet, "lineNet");
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(stockName, "stockName");
        Intrinsics.checkNotNullParameter(stockCode, "stockCode");
        this.amount = amount;
        this.shippedAmount = shippedAmount;
        this.price = price;
        this.discount = discount;
        this.lineNet = lineNet;
        this.date = date;
        this.stockName = stockName;
        this.stockCode = stockCode;
    }
    public  LastOrderProducts(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? "" : str3, (i2 & 8) != 0 ? "" : str4, (i2 & 16) != 0 ? "" : str5, (i2 & 32) != 0 ? "" : str6, (i2 & 64) != 0 ? "" : str7, (i2 & 128) == 0 ? str8 : "");
    }
    public final String getAmount() {
        return this.amount;
    }
    public final void setAmount(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.amount = str;
    }
    public final String getShippedAmount() {
        return this.shippedAmount;
    }
    public final void setShippedAmount(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.shippedAmount = str;
    }
    public final String getPrice() {
        return this.price;
    }
    public final void setPrice(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.price = str;
    }
    public final String getDiscount() {
        return this.discount;
    }
    public final void setDiscount(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.discount = str;
    }
    public final String getLineNet() {
        return this.lineNet;
    }
    public final void setLineNet(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.lineNet = str;
    }
    public final String getDate() {
        return this.date;
    }
    public final void setDate(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.date = str;
    }
    public final String getStockName() {
        return this.stockName;
    }
    public final void setStockName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stockName = str;
    }
    public final String getStockCode() {
        return this.stockCode;
    }
    public final void setStockCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.stockCode = str;
    }
    public void writeToParcel(Parcel dest, int i2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.amount);
        dest.writeString(this.shippedAmount);
        dest.writeString(this.price);
        dest.writeString(this.discount);
        dest.writeString(this.lineNet);
        dest.writeString(this.date);
        dest.writeString(this.stockName);
        dest.writeString(this.stockCode);
    }
}
