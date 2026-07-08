package com.proje.mobilesales.core.netsis;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.base.BaseSelectResult;

public class NetsisSelectResult extends BaseSelectResult {
    public static final Parcelable.Creator<NetsisSelectResult> CREATOR = new Parcelable.Creator<NetsisSelectResult>() {
        public NetsisSelectResult createFromParcel(Parcel parcel) {
            return new NetsisSelectResult(parcel);
        }

        public NetsisSelectResult[] newArray(int i2) {
            return new NetsisSelectResult[i2];
        }
    };

    public int describeContents() {
        return 0;
    }

    private NetsisSelectResult(NetsisBuilder netsisBuilder) {
        super(netsisBuilder);
    }

    public static NetsisBuilder newBuilder() {
        return new NetsisBuilder();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
    }

    protected NetsisSelectResult(Parcel parcel) {
        super(parcel);
    }

    public static class NetsisBuilder extends BaseSelectResult.SelectBuilder<NetsisSelectResult, NetsisBuilder> {
        private NetsisBuilder() {
        }

        public BaseSelectResult build() {
            return new NetsisSelectResult(this);
        }
    }

    public static NetsisSelectResult createErrorResult(String str) {
        NetsisSelectResult build = (NetsisSelectResult) newBuilder().build();
        build.setSuccess(false);
        build.setErrorString(str);
        return build;
    }
}
