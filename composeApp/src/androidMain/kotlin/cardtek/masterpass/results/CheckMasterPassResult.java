package cardtek.masterpass.results;

import java.io.Serializable;

public class CheckMasterPassResult implements Serializable {
    private String accountStatus;
    private String refNo;
    public String getAccountStatus() {
        return this.accountStatus;
    }
    public String getRefNo() {
        return this.refNo;
    }
    public void setAccountStatus(String str) {
        this.accountStatus = str;
    }
    public void setRefNo(String str) {
        this.refNo = str;
    }
}
