package com.proje.mobilesales.core.tigerwcf;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.base.BaseSelectResult;

public class TigerSelectResult extends BaseSelectResult implements Parcelable {
    public static final Parcelable.Creator<TigerSelectResult> CREATOR = new Parcelable.Creator<TigerSelectResult>() { // from class: com.proje.mobilesales.core.tigerwcf.TigerSelectResult.1
        
        
        public TigerSelectResult createFromParcel(Parcel parcel) {
            return new TigerSelectResult(parcel);
        }

        public TigerSelectResult[] newArray(int i2) {
            return new TigerSelectResult[i2];
        }
    };
    private String resultXML;
    public int describeContents() {
        return 0;
    }
    private TigerSelectResult(TigerBuilder tigerBuilder) {
        super(tigerBuilder);
        this.resultXML = "";
    }
    public static TigerBuilder newBuilder() {
        return new TigerBuilder();
    }
    public String getResultXML() {
        return this.resultXML;
    }
    public void setResultXML(String str) {
        this.resultXML = str;
    }
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.resultXML);
    }
    protected TigerSelectResult(Parcel parcel) {
        super(parcel);
        this.resultXML = "";
        this.resultXML = parcel.readString();
    }
    public String getErrorMessage() {
        return "";
    }
    public static final class TigerBuilder extends BaseSelectResult.SelectBuilder<TigerSelectResult, TigerBuilder> {
        private TigerBuilder() {
        }

        public BaseSelectResult build() {
            return new TigerSelectResult(this);
        }
    }
    public static TigerSelectResult createErrorResult(String str) {
        TigerSelectResult build = ( TigerSelectResult ) newBuilder().build();
        build.setSuccess(false);
        build.setErrorString(str);
        return build;
    }
}
