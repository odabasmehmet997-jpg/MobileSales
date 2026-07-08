package cardtek.masterpass.response;

import java.io.Serializable;

public class InternalError implements Serializable {
    private String errorCode;
    private String errorDesc;
    public String getErrorCode() {
        return this.errorCode;
    }
    public String getErrorDesc() {
        return this.errorDesc;
    }
    public void setErrorCode(String str) {
        this.errorCode = str;
    }
    public void setErrorDesc(String str) {
        this.errorDesc = str;
    }
}
