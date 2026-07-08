package com.example.privacy_policy_lib.core.model;

import androidx.annotation.NonNull;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class Error {

    private String errorCode;
    private String errorMessage;
    public Error() {
        this(null, null, 3, null);
    }
    public static   Error copydefault(final Error error, String str, String str2, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = error.errorCode;
        }
        if (0 != (i2 & 2)) {
            str2 = error.errorMessage;
        }
        return error.copy(str, str2);
    }
    public String component1() {
        return errorCode;
    }
    public String component2() {
        return errorMessage;
    }
    public Error copy(final String str, final String str2) {
        return new Error(str, str2);
    }
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Error error)) {
            return false;
        }
        return Intrinsics.areEqual(errorCode, error.errorCode) && Intrinsics.areEqual(errorMessage, error.errorMessage);
    }
    public int hashCode() {
        final String str = errorCode;
        int i2 = 0;
        final int hashCode = (null == str ? 0 : str.hashCode()) * 31;
        final String str2 = errorMessage;
        if (null != str2) {
            i2 = str2.hashCode();
        }
        return hashCode + i2;
    }
    public String toString() {
        final String str = errorCode;
        final String str2 = errorMessage;
        return "Error(errorCode=" + str + ", errorMessage=" + str2 + ")" ;
    }
    public Error(final String str, final String str2) {
        errorCode = str;
        errorMessage = str2;
    }
    public   Error(final String str, final String str2, final int i2, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i2 & 1) ? null : str, 0 != (i2 & 2) ? null : str2);
    }
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(final String str) {
        errorCode = str;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(final String str) {
        errorMessage = str;
    }
}
