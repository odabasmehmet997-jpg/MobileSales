package com.proje.mobilesales.core.edocument;

public class EDocumentResponse {
    private String mErrorDesc;
    private String message;
    private boolean success;
    public String getErrorDesc() {
        return this.mErrorDesc;
    }
    public void setErrorDesc(String str) {
        this.mErrorDesc = str;
    }
    public boolean isSuccess() {
        return this.success;
    }
    public void setSuccess(boolean z) {
        this.success = z;
    }
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String str) {
        this.message = str;
    }
}
