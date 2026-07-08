package com.proje.mobilesales.core.event;

public class ResponseEvent {
    private String errorMessage;
    private boolean success;
    public ResponseEvent() {
    }
    public ResponseEvent(String str, boolean z) {
        this.errorMessage = str;
        this.success = z;
    }
    public String getErrorMessage() {
        return this.errorMessage;
    }
    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }
    public boolean isSuccess() {
        return this.success;
    }
    public void setSuccess(boolean z) {
        this.success = z;
    }
    public void setFailure  (String str) {
        this.success = false;
        this.errorMessage = str;
    }
}
