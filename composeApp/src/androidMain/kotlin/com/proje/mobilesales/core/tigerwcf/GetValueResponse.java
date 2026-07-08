package com.proje.mobilesales.core.tigerwcf;

import org.simpleframework.xml.Element;



public class GetValueResponse {
    private String mErrorDesc;
    private String message;
    private boolean success;

    @Element(name = "Value", required = false)
    private StringElement value;

    public StringElement getValue() {
        return this.value;
    }

    public void setValue(StringElement stringElement) {
        this.value = stringElement;
    }

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
