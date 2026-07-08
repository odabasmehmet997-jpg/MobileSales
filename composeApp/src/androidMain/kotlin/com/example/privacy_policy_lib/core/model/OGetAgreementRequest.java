package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;

public record OGetAgreementRequest(String contractor, String itemCode, String language, String agreementType) {
    public static OGetAgreementRequest copydefault(OGetAgreementRequest oGetAgreementRequest, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = oGetAgreementRequest.contractor;
        }
        if ((i2 & 2) != 0) {
            str2 = oGetAgreementRequest.itemCode;
        }
        if ((i2 & 4) != 0) {
            str3 = oGetAgreementRequest.language;
        }
        if ((i2 & 8) != 0) {
            str4 = oGetAgreementRequest.agreementType;
        }
        return oGetAgreementRequest.copy(str, str2, str3, str4);
    }

    public String component1() {
        return this.contractor;
    }

    public String component2() {
        return this.itemCode;
    }

    public String component3() {
        return this.language;
    }

    public String component4() {
        return this.agreementType;
    }

    public OGetAgreementRequest copy(@Element(name = "contractor") String str, @Element(name = "itemCode", required = false) String str2, @Element(name = "language") String str3, @Element(name = "agreementType") String str4) {
        Intrinsics.checkNotNullParameter(str, "contractor");
        Intrinsics.checkNotNullParameter(str3, "language");
        Intrinsics.checkNotNullParameter(str4, "agreementType");
        return new OGetAgreementRequest(str, str2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OGetAgreementRequest oGetAgreementRequest)) {
            return false;
        }
        return Intrinsics.areEqual(this.contractor, oGetAgreementRequest.contractor) && Intrinsics.areEqual(this.itemCode, oGetAgreementRequest.itemCode) && Intrinsics.areEqual(this.language, oGetAgreementRequest.language) && Intrinsics.areEqual(this.agreementType, oGetAgreementRequest.agreementType);
    }

    public int hashCode() {
        int hashCode = this.contractor.hashCode() * 31;
        String str = this.itemCode;
        return ((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.language.hashCode()) * 31) + this.agreementType.hashCode();
    }

    public String toString() {
        String str = this.contractor;
        String str2 = this.itemCode;
        String str3 = this.language;
        String str4 = this.agreementType;
        return "OGetAgreementRequest(contractor=" + str + ", itemCode=" + str2 + ", language=" + str3 + ", agreementType=" + str4 + ")";
    }

    public OGetAgreementRequest(@Element(name = "contractor") String contractor, @Element(name = "itemCode", required = false) String itemCode, @Element(name = "language") String language, @Element(name = "agreementType") String agreementType) {
        Intrinsics.checkNotNullParameter(contractor, "contractor");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(agreementType, "agreementType");
        this.contractor = contractor;
        this.itemCode = itemCode;
        this.language = language;
        this.agreementType = agreementType;
    }
}
