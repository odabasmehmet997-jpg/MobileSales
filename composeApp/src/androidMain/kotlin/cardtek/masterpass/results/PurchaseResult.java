package cardtek.masterpass.results;

import java.io.Serializable;

public class PurchaseResult implements Serializable {
    private String refNo;
    private String token;
    public String getRefNo() {
        return this.refNo;
    }
    public String getToken() {
        return this.token;
    }
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
