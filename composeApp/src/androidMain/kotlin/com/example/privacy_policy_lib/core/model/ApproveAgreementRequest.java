package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;

public record ApproveAgreementRequest(String uniqueInfo, String ipAddress, String contentHash, String extensionFields,
                                      String itemCode, String agreementType, String contractor, String language,
                                      String beginDate, String signedContentBase64Encoded) {

    public String component1() {
        return this.uniqueInfo;
    }

    public String component10() {
        return this.signedContentBase64Encoded;
    }

    public String component2() {
        return this.ipAddress;
    }

    public String component3() {
        return this.contentHash;
    }

    public String component4() {
        return this.extensionFields;
    }

    public String component5() {
        return this.itemCode;
    }

    public String component6() {
        return this.agreementType;
    }

    public String component7() {
        return this.contractor;
    }

    public String component8() {
        return this.language;
    }

    public String component9() {
        return this.beginDate;
    }

    public ApproveAgreementRequest copy(@Element(name = "uniqueInfo") String str, @Element(name = "ipAddress") String str2, @Element(name = "contentHash") String str3, @Element(name = "extensionFields") String str4, @Element(name = "itemCode") String str5, @Element(name = "agreementType") String str6, @Element(name = "contractor") String str7, @Element(name = "language") String str8, @Element(name = "beginDate") String str9, @Element(name = "signedContentBase64Encoded") String str10) {
        Intrinsics.checkNotNullParameter(str, "uniqueInfo");
        Intrinsics.checkNotNullParameter(str2, "ipAddress");
        Intrinsics.checkNotNullParameter(str3, "contentHash");
        Intrinsics.checkNotNullParameter(str4, "extensionFields");
        Intrinsics.checkNotNullParameter(str5, "itemCode");
        Intrinsics.checkNotNullParameter(str6, "agreementType");
        Intrinsics.checkNotNullParameter(str7, "contractor");
        Intrinsics.checkNotNullParameter(str8, "language");
        Intrinsics.checkNotNullParameter(str9, "beginDate");
        Intrinsics.checkNotNullParameter(str10, "signedContentBase64Encoded");
        return new ApproveAgreementRequest(str, str2, str3, str4, str5, str6, str7, str8, str9, str10);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ApproveAgreementRequest approveAgreementRequest)) {
            return false;
        }
        return Intrinsics.areEqual(this.uniqueInfo, approveAgreementRequest.uniqueInfo) && Intrinsics.areEqual(this.ipAddress, approveAgreementRequest.ipAddress) && Intrinsics.areEqual(this.contentHash, approveAgreementRequest.contentHash) && Intrinsics.areEqual(this.extensionFields, approveAgreementRequest.extensionFields) && Intrinsics.areEqual(this.itemCode, approveAgreementRequest.itemCode) && Intrinsics.areEqual(this.agreementType, approveAgreementRequest.agreementType) && Intrinsics.areEqual(this.contractor, approveAgreementRequest.contractor) && Intrinsics.areEqual(this.language, approveAgreementRequest.language) && Intrinsics.areEqual(this.beginDate, approveAgreementRequest.beginDate) && Intrinsics.areEqual(this.signedContentBase64Encoded, approveAgreementRequest.signedContentBase64Encoded);
    }

    public String toString() {
        String str = this.uniqueInfo;
        String str2 = this.ipAddress;
        String str3 = this.contentHash;
        String str4 = this.extensionFields;
        String str5 = this.itemCode;
        String str6 = this.agreementType;
        String str7 = this.contractor;
        String str8 = this.language;
        String str9 = this.beginDate;
        String str10 = this.signedContentBase64Encoded;
        return "ApproveAgreementRequest(uniqueInfo=" + str + ", ipAddress=" + str2 + ", contentHash=" + str3 + ", extensionFields=" + str4 + ", itemCode=" + str5 + ", agreementType=" + str6 + ", contractor=" + str7 + ", language=" + str8 + ", beginDate=" + str9 + ", signedContentBase64Encoded=" + str10 + ")";
    }

    public ApproveAgreementRequest(@Element(name = "uniqueInfo") String uniqueInfo, @Element(name = "ipAddress") String ipAddress, @Element(name = "contentHash") String contentHash, @Element(name = "extensionFields") String extensionFields, @Element(name = "itemCode") String itemCode, @Element(name = "agreementType") String agreementType, @Element(name = "contractor") String contractor, @Element(name = "language") String language, @Element(name = "beginDate") String beginDate, @Element(name = "signedContentBase64Encoded") String signedContentBase64Encoded) {
        Intrinsics.checkNotNullParameter(uniqueInfo, "uniqueInfo");
        Intrinsics.checkNotNullParameter(ipAddress, "ipAddress");
        Intrinsics.checkNotNullParameter(contentHash, "contentHash");
        Intrinsics.checkNotNullParameter(extensionFields, "extensionFields");
        Intrinsics.checkNotNullParameter(itemCode, "itemCode");
        Intrinsics.checkNotNullParameter(agreementType, "agreementType");
        Intrinsics.checkNotNullParameter(contractor, "contractor");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(beginDate, "beginDate");
        Intrinsics.checkNotNullParameter(signedContentBase64Encoded, "signedContentBase64Encoded");
        this.uniqueInfo = uniqueInfo;
        this.ipAddress = ipAddress;
        this.contentHash = contentHash;
        this.extensionFields = extensionFields;
        this.itemCode = itemCode;
        this.agreementType = agreementType;
        this.contractor = contractor;
        this.language = language;
        this.beginDate = beginDate;
        this.signedContentBase64Encoded = signedContentBase64Encoded;
    }
}
