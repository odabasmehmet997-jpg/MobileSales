package com.proje.mobilesales.core.netsis;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.base.BaseServiceResult;

public class NetsisServiceResult<T, S> extends BaseServiceResult<T, S> {
    public static final Parcelable.Creator<NetsisServiceResult> CREATOR = new Parcelable.Creator<NetsisServiceResult>() {
        public NetsisServiceResult createFromParcel(Parcel parcel) {
            return new NetsisServiceResult(parcel);
        }

        public NetsisServiceResult[] newArray(int i2) {
            return new NetsisServiceResult[i2];
        }
    };
    private String ficheNo;

    public static void createErrorResult(String message) {

    }

    public int describeContents() {
        return 0;
    }

    private NetsisServiceResult(NetsisServiceBuilder netsisServiceBuilder) {
        super(netsisServiceBuilder);
    }

    public static NetsisServiceBuilder newBuilder() {
        return new NetsisServiceBuilder();
    }

    public String getFicheNo() {
        return this.ficheNo;
    }

    public void setFicheNo(String str) {
        this.ficheNo = str;
    }

    public Object getProcessType() {
        return null;
    }

    public static class NetsisServiceBuilder extends BaseServiceResult.ServiceBuilder<NetsisServiceResult, NetsisServiceBuilder> {
        public BaseServiceResult build() {
            return new NetsisServiceResult(this);
        }
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.ficheNo);
    }

    protected NetsisServiceResult(Parcel parcel) {
        super(parcel);
        this.ficheNo = parcel.readString();
    }
}
