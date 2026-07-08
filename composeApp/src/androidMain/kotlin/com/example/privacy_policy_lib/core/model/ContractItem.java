package com.example.privacy_policy_lib.core.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
public final class ContractItem implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private String contractItemContent;
    private String contractItemText;
    public ContractItem() {
        this(null, null, 3, null);
    }
    public static ContractItem copydefault(ContractItem contractItem, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = contractItem.contractItemText;
        }
        if ((i2 & 2) != 0) {
            str2 = contractItem.contractItemContent;
        }
        return contractItem.copy(str, str2);
    }
    public String component1() {
        return this.contractItemText;
    }
    public String component2() {
        return this.contractItemContent;
    }
    public ContractItem copy(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "contractItemText");
        Intrinsics.checkNotNullParameter(str2, "contractItemContent");
        return new ContractItem(str, str2);
    }
    public int describeContents() {
        return 0;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContractItem contractItem)) {
            return false;
        }
        return Intrinsics.areEqual(this.contractItemText, contractItem.contractItemText) && Intrinsics.areEqual(this.contractItemContent, contractItem.contractItemContent);
    }
    public int hashCode() {
        return (this.contractItemText.hashCode() * 31) + this.contractItemContent.hashCode();
    }
    public String toString() {
        String str = this.contractItemText;
        String str2 = this.contractItemContent;
        return "ContractItem(contractItemText=" + str + ", contractItemContent=" + str2 + ")";
    }
    public ContractItem(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "contractItemText");
        Intrinsics.checkNotNullParameter(str2, "contractItemContent");
        this.contractItemText = str;
        this.contractItemContent = str2;
    }
    public ContractItem(String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2);
    }
    public String getContractItemText() {
        return this.contractItemText;
    }
    public void setContractItemText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.contractItemText = str;
    }
    public String getContractItemContent() {
        return this.contractItemContent;
    }
    public void setContractItemContent(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.contractItemContent = str;
    }
    public ContractItem(Parcel parcel) {
        this(String.valueOf(parcel.readString()), null, 2, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
    }
    public void writeToParcel(Parcel parcel, int i2) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.contractItemText);
        parcel.writeString(this.contractItemContent);
    }
    public static final class CREATOR implements Creator<ContractItem> {
        public CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        public ContractItem createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ContractItem(parcel);
        }
        public ContractItem[] newArray(int i2) {
            return new ContractItem[i2];
        }
    }
}
