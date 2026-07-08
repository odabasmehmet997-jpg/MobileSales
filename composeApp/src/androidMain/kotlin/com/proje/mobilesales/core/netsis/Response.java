package com.proje.mobilesales.core.netsis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Response {

    @SerializedName("ErrorCode")
    @Expose
    private String errorCode;

    @SerializedName("ErrorDesc")
    @Expose
    private String errorDesc;

    @SerializedName("IsSuccessful")
    @Expose
    private boolean isSuccessful;

    @SerializedName("Message")
    @Expose
    private String message;

    public boolean isSuccessful() {
        return this.isSuccessful;
    }

    public void setSuccessful(boolean z) {
        this.isSuccessful = z;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }

    public void setErrorDesc(String str) {
        this.errorDesc = str;
    }
}
