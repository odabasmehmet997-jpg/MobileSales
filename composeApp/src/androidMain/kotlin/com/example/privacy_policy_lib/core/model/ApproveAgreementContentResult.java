package com.example.privacy_policy_lib.core.model;

import com.google.firebase.messaging.Constants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "ApproveAgreementContentResult", strict = false)

public final class ApproveAgreementContentResult {
    @Element(name = "agreementToken", required = false)
    private String agreementToken;
    @Element(name = Constants.IPC_BUNDLE_KEY_SEND_ERROR, required = false)
    private Error error;
    public ApproveAgreementContentResult() {
        this(null, null, 3, null);
    }
    public static ApproveAgreementContentResult copydefault(ApproveAgreementContentResult approveAgreementContentResult, Error error, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            error = approveAgreementContentResult.error;
        }
        if ((i2 & 2) != 0) {
            str = approveAgreementContentResult.agreementToken;
        }
        return approveAgreementContentResult.copy(error, str);
    }
    public Error component1() {
        return this.error;
    }
    public String component2() {
        return this.agreementToken;
    }
    public ApproveAgreementContentResult copy(Error error, String str) {
        return new ApproveAgreementContentResult(error, str);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ApproveAgreementContentResult approveAgreementContentResult)) {
            return false;
        }
        return Intrinsics.areEqual(this.error, approveAgreementContentResult.error) && Intrinsics.areEqual(this.agreementToken, approveAgreementContentResult.agreementToken);
    }
    public int hashCode() {
        Error error = this.error;
        int i2 = 0;
        int hashCode = (error == null ? 0 : error.hashCode()) * 31;
        String str = this.agreementToken;
        if (str != null) {
            i2 = str.hashCode();
        }
        return hashCode + i2;
    }
    public String toString() {
        Error error = this.error;
        String str = this.agreementToken;
        return "ApproveAgreementContentResult(error=" + error + ", agreementToken=" + str + ")";
    }
    public ApproveAgreementContentResult(Error error, String str) {
        this.error = error;
        this.agreementToken = str;
    }
    public ApproveAgreementContentResult(Error error, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : error, (i2 & 2) != 0 ? null : str);
    }
    public Error getError() {
        return this.error;
    }
    public void setError(Error error) {
        this.error = error;
    }
    public String getAgreementToken() {
        return this.agreementToken;
    }
    public void setAgreementToken(String str) {
        this.agreementToken = str;
    }
}
