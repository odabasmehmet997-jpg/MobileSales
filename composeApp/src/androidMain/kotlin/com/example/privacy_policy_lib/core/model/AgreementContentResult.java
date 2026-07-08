package com.example.privacy_policy_lib.core.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

public final class AgreementContentResult {

    private Integer agreementPeriod;
    private String agreementType;
    private String beginDate;
    private String content;
    private String contentHash;
    private String contractor;
    private String description;
    private String endDate;
    private String error;
    private String itemCode;
    private String language;
    public AgreementContentResult() {
        this(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
    }
    public String component1() {
        return this.content;
    }
    public Integer component10() {
        return this.agreementPeriod;
    }
    public String component11() {
        return this.error;
    }
    public String component2() {
        return this.contentHash;
    }
    public String component3() {
        return this.itemCode;
    }
    public String component4() {
        return this.agreementType;
    }
    public String component5() {
        return this.contractor;
    }
    public String component6() {
        return this.language;
    }
    public String component7() {
        return this.beginDate;
    }
    public String component8() {
        return this.endDate;
    }
    public String component9() {
        return this.description;
    }
    public AgreementContentResult copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Integer num, String str10) {
        return new AgreementContentResult(str, str2, str3, str4, str5, str6, str7, str8, str9, num, str10);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AgreementContentResult agreementContentResult)) {
            return false;
        }
        return Intrinsics.areEqual(this.content, agreementContentResult.content) && Intrinsics.areEqual(this.contentHash, agreementContentResult.contentHash) && Intrinsics.areEqual(this.itemCode, agreementContentResult.itemCode) && Intrinsics.areEqual(this.agreementType, agreementContentResult.agreementType) && Intrinsics.areEqual(this.contractor, agreementContentResult.contractor) && Intrinsics.areEqual(this.language, agreementContentResult.language) && Intrinsics.areEqual(this.beginDate, agreementContentResult.beginDate) && Intrinsics.areEqual(this.endDate, agreementContentResult.endDate) && Intrinsics.areEqual(this.description, agreementContentResult.description) && Intrinsics.areEqual(this.agreementPeriod, agreementContentResult.agreementPeriod) && Intrinsics.areEqual(this.error, agreementContentResult.error);
    }
    public int hashCode() {
        String str = this.content;
        int i2 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.contentHash;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.itemCode;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.agreementType;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.contractor;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.language;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.beginDate;
        int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.endDate;
        int hashCode8 = (hashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.description;
        int hashCode9 = (hashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        Integer num = this.agreementPeriod;
        int hashCode10 = (hashCode9 + (num == null ? 0 : num.hashCode())) * 31;
        String str10 = this.error;
        if (str10 != null) {
            i2 = str10.hashCode();
        }
        return hashCode10 + i2;
    }
    public String toString() {
        String str = this.content;
        String str2 = this.contentHash;
        String str3 = this.itemCode;
        String str4 = this.agreementType;
        String str5 = this.contractor;
        String str6 = this.language;
        String str7 = this.beginDate;
        String str8 = this.endDate;
        String str9 = this.description;
        Integer num = this.agreementPeriod;
        String str10 = this.error;
        return "AgreementContentResult(content=" + str + ", contentHash=" + str2 + ", itemCode=" + str3 + ", agreementType=" + str4 + ", contractor=" + str5 + ", language=" + str6 + ", beginDate=" + str7 + ", endDate=" + str8 + ", description=" + str9 + ", agreementPeriod=" + num + ", error=" + str10 + ")";
    }
    public AgreementContentResult(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Integer num, String str10) {
        this.content = str;
        this.contentHash = str2;
        this.itemCode = str3;
        this.agreementType = str4;
        this.contractor = str5;
        this.language = str6;
        this.beginDate = str7;
        this.endDate = str8;
        this.description = str9;
        this.agreementPeriod = num;
        this.error = str10;
    }
    public AgreementContentResult(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Integer num, String str10, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : str6, (i2 & 64) != 0 ? null : str7, (i2 & 128) != 0 ? null : str8, (i2 & 256) != 0 ? null : str9, (i2 & 512) != 0 ? null : num, (i2 & 1024) == 0 ? str10 : null);
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String str) {
        this.content = str;
    }
    public String getContentHash() {
        return this.contentHash;
    }
    public void setContentHash(String str) {
        this.contentHash = str;
    }
    public String getItemCode() {
        return this.itemCode;
    }
    public void setItemCode(String str) {
        this.itemCode = str;
    }
    public String getAgreementType() {
        return this.agreementType;
    }
    public void setAgreementType(String str) {
        this.agreementType = str;
    }
    public String getContractor() {
        return this.contractor;
    }
    public void setContractor(String str) {
        this.contractor = str;
    }
    public String getLanguage() {
        return this.language;
    }
    public void setLanguage(String str) {
        this.language = str;
    }
    public String getBeginDate() {
        return this.beginDate;
    }
    public void setBeginDate(String str) {
        this.beginDate = str;
    }
    public String getEndDate() {
        return this.endDate;
    }
    public void setEndDate(String str) {
        this.endDate = str;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String str) {
        this.description = str;
    }
    public Integer getAgreementPeriod() {
        return this.agreementPeriod;
    }
    public void setAgreementPeriod(Integer num) {
        this.agreementPeriod = num;
    }
    public String getError() {
        return this.error;
    }
    public void setError(String str) {
        this.error = str;
    }
}
