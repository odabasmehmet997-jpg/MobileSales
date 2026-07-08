package com.proje.mobilesales.core.tigerwcf;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.base.BaseServiceResult;

public class TigerServiceResult extends BaseServiceResult implements Parcelable {
    public static final Parcelable.Creator<TigerServiceResult> CREATOR = new Parcelable.Creator<TigerServiceResult>() {

        public TigerServiceResult createFromParcel(Parcel parcel) {
            return new TigerServiceResult(parcel);
        }

        public TigerServiceResult[] newArray(int i2) {
            return new TigerServiceResult[i2];
        }
    };
    private int applyCampaign;
    private int applySalesCondition;
    private String dataXML;
    private String paramXML;

    public int describeContents() {
        return 0;
    }

    private TigerServiceResult(TigerServiceBuilder tigerServiceBuilder) {
        super(tigerServiceBuilder);
        this.paramXML = "";
        this.dataXML = "";
        this.paramXML = tigerServiceBuilder.paramXML;
        this.dataXML = tigerServiceBuilder.dataXML;
        this.applyCampaign = tigerServiceBuilder.applyCampaign;
        this.applySalesCondition = tigerServiceBuilder.applySalesCondition;
    }

    public static TigerServiceBuilder newBuilder() {
        return new TigerServiceBuilder();
    }

    public String getParamXML() {
        return this.paramXML;
    }

    public void setParamXML(String str) {
        this.paramXML = str;
    }

    public String getDataXML() {
        return this.dataXML;
    }

    public void setDataXML(String str) {
        this.dataXML = str;
    }

    public int getApplyCampaign() {
        return this.applyCampaign;
    }

    public void setApplyCampaign(int i2) {
        this.applyCampaign = i2;
    }

    public int getApplySalesCondition() {
        return this.applySalesCondition;
    }

    public void setApplySalesCondition(int i2) {
        this.applySalesCondition = i2;
    }

    public String getSendData() {
        return (String) super.getSendData();
    }

    public static final class TigerServiceBuilder extends BaseServiceResult.ServiceBuilder<TigerServiceResult, TigerServiceBuilder> {
        private int applyCampaign;
        private int applySalesCondition;
        private String dataXML;
        private String paramXML;

        private TigerServiceBuilder() {
        }

        public TigerServiceBuilder withParamXML(String str) {
            this.paramXML = str;
            return this;
        }

        public TigerServiceBuilder withDataXML(String str) {
            this.dataXML = str;
            return this;
        }

        public TigerServiceBuilder withApplyCampaign(int i2) {
            this.applyCampaign = i2;
            return this;
        }

        public TigerServiceBuilder withApplySalesCondition(int i2) {
            this.applySalesCondition = i2;
            return this;
        }
        public BaseServiceResult build() {
            return new TigerServiceResult(this);
        }
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.paramXML);
        parcel.writeString(this.dataXML);
        parcel.writeInt(this.applyCampaign);
        parcel.writeInt(this.applySalesCondition);
    }

    protected TigerServiceResult(Parcel parcel) {
        super(parcel);
        this.paramXML = "";
        this.dataXML = "";
        this.paramXML = parcel.readString();
        this.dataXML = parcel.readString();
        this.applyCampaign = parcel.readInt();
        this.applySalesCondition = parcel.readInt();
    }
}
