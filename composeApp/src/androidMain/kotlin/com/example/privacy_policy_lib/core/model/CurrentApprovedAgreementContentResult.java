package com.example.privacy_policy_lib.core.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

public final class CurrentApprovedAgreementContentResult {
    private Integer agreementPeriod;
    private String approveDate;
    private String beginDate;
    private String concatInfo;
    private String content;
    private String contentHash;
    private String contentTimestamp;
    private String description;
    private String endDate;
    private AgreementError error;
    private String signedContentBase64;
    public CurrentApprovedAgreementContentResult() {
        this(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
    }
    public String component1() {
        return this.content;
    }
    public Integer component10() {
        return this.agreementPeriod;
    }
    public AgreementError component11() {
        return this.error;
    }
    public String component2() {
        return this.contentHash;
    }
    public String component3() {
        return this.contentTimestamp;
    }
    public String component4() {
        return this.signedContentBase64;
    }
    public String component5() {
        return this.concatInfo;
    }
    public String component6() {
        return this.beginDate;
    }
    public String component7() {
        return this.endDate;
    }
    public String component8() {
        return this.approveDate;
    }
    public String component9() {
        return this.description;
    }
    public CurrentApprovedAgreementContentResult copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Integer num, AgreementError agreementError) {
        return new CurrentApprovedAgreementContentResult(str, str2, str3, str4, str5, str6, str7, str8, str9, num, agreementError);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CurrentApprovedAgreementContentResult currentApprovedAgreementContentResult)) {
            return false;
        }
        return Intrinsics.areEqual(this.content, currentApprovedAgreementContentResult.content) && Intrinsics.areEqual(this.contentHash, currentApprovedAgreementContentResult.contentHash) && Intrinsics.areEqual(this.contentTimestamp, currentApprovedAgreementContentResult.contentTimestamp) && Intrinsics.areEqual(this.signedContentBase64, currentApprovedAgreementContentResult.signedContentBase64) && Intrinsics.areEqual(this.concatInfo, currentApprovedAgreementContentResult.concatInfo) && Intrinsics.areEqual(this.beginDate, currentApprovedAgreementContentResult.beginDate) && Intrinsics.areEqual(this.endDate, currentApprovedAgreementContentResult.endDate) && Intrinsics.areEqual(this.approveDate, currentApprovedAgreementContentResult.approveDate) && Intrinsics.areEqual(this.description, currentApprovedAgreementContentResult.description) && Intrinsics.areEqual(this.agreementPeriod, currentApprovedAgreementContentResult.agreementPeriod) && Intrinsics.areEqual(this.error, currentApprovedAgreementContentResult.error);
    }
    public int hashCode() {
        String str = this.content;
        int i2 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.contentHash;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.contentTimestamp;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.signedContentBase64;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.concatInfo;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.beginDate;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.endDate;
        int hashCode7 = (hashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.approveDate;
        int hashCode8 = (hashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.description;
        int hashCode9 = (hashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        Integer num = this.agreementPeriod;
        int hashCode10 = (hashCode9 + (num == null ? 0 : num.hashCode())) * 31;
        AgreementError agreementError = this.error;
        if (agreementError != null) {
            i2 = agreementError.hashCode();
        }
        return hashCode10 + i2;
    }
    public String toString() {
        String str = this.content;
        String str2 = this.contentHash;
        String str3 = this.contentTimestamp;
        String str4 = this.signedContentBase64;
        String str5 = this.concatInfo;
        String str6 = this.beginDate;
        String str7 = this.endDate;
        String str8 = this.approveDate;
        String str9 = this.description;
        Integer num = this.agreementPeriod;
        AgreementError agreementError = this.error;
        return "CurrentApprovedAgreementContentResult(content=" + str + ", contentHash=" + str2 + ", contentTimestamp=" + str3 + ", signedContentBase64=" + str4 + ", concatInfo=" + str5 + ", beginDate=" + str6 + ", endDate=" + str7 + ", approveDate=" + str8 + ", description=" + str9 + ", agreementPeriod=" + num + ", error=" + agreementError + ")";
    }
    public CurrentApprovedAgreementContentResult(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Integer num, AgreementError agreementError) {
        this.content = str;
        this.contentHash = str2;
        this.contentTimestamp = str3;
        this.signedContentBase64 = str4;
        this.concatInfo = str5;
        this.beginDate = str6;
        this.endDate = str7;
        this.approveDate = str8;
        this.description = str9;
        this.agreementPeriod = num;
        this.error = agreementError;
    }
    public  CurrentApprovedAgreementContentResult(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Integer num, AgreementError agreementError, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : str6, (i2 & 64) != 0 ? null : str7, (i2 & 128) != 0 ? null : str8, (i2 & 256) != 0 ? null : str9, (i2 & 512) != 0 ? null : num, (i2 & 1024) == 0 ? agreementError : null);
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
    public String getContentTimestamp() {
        return this.contentTimestamp;
    }
    public void setContentTimestamp(String str) {
        this.contentTimestamp = str;
    }
    public String getSignedContentBase64() {
        return this.signedContentBase64;
    }
    public void setSignedContentBase64(String str) {
        this.signedContentBase64 = str;
    }
    public String getConcatInfo() {
        return this.concatInfo;
    }
    public void setConcatInfo(String str) {
        this.concatInfo = str;
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
    public String getApproveDate() {
        return this.approveDate;
    }
    public void setApproveDate(String str) {
        this.approveDate = str;
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
    public AgreementError getError() {
        return this.error;
    }
    public void setError(AgreementError agreementError) {
        this.error = agreementError;
    }
}
