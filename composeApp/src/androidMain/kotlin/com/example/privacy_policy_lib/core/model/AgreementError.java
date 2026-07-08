package com.example.privacy_policy_lib.core.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class AgreementError {
    private String errorCode;
    private String errorMessage;
    public AgreementError() {
        this(null, null, 3, null);
    }
    public static AgreementError copydefault(AgreementError agreementError, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = agreementError.errorCode;
        }
        if ((i2 & 2) != 0) {
            str2 = agreementError.errorMessage;
        }
        return agreementError.copy(str, str2);
    }
    public String component1() {
        return this.errorCode;
    }
    public String component2() {
        return this.errorMessage;
    }
    public AgreementError copy(String str, String str2) {
        return new AgreementError(str, str2);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AgreementError agreementError)) {
            return false;
        }
        return Intrinsics.areEqual(this.errorCode, agreementError.errorCode) && Intrinsics.areEqual(this.errorMessage, agreementError.errorMessage);
    }
    public int hashCode() {
        String str = this.errorCode;
        int i2 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.errorMessage;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return hashCode + i2;
    }
    public String toString() {
        String str = this.errorCode;
        String str2 = this.errorMessage;
        return "AgreementError(errorCode=" + str + ", errorMessage=" + str2 + ")";
    }
    public AgreementError(String str, String str2) {
        this.errorCode = str;
        this.errorMessage = str2;
    }
    public AgreementError(String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2);
    }
    public String getErrorCode() {
        return this.errorCode;
    }
    public void setErrorCode(String str) {
        this.errorCode = str;
    }
    public String getErrorMessage() {
        return this.errorMessage;
    }
    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }
}
