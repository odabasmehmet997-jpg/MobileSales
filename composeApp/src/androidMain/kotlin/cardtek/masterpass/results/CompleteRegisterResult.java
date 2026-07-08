package cardtek.masterpass.results;

import java.io.Serializable;

public class CompleteRegisterResult implements Serializable {
    private String refNo;
    private String token;
    public String getRefNo() {
        return this.refNo;
    }
    public String getToken() {
        return this.token;
    }
    public void setRefNo(String str) {
        this.refNo = str;
    }
    public void setToken(String str) {
        this.token = str;
    }
}
