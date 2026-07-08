package cardtek.masterpass.results;

import java.io.Serializable;

public class ParseQrCodeResult implements Serializable {
    private String accountStatus;
    private String amount;
    private String installmentCount;
    private String orderNo;
    private String refNo;
    public String getAccountStatus() {
        return this.accountStatus;
    }
    public String getAmount() {
        return this.amount;
    }
    public String getInstallmentCount() {
        return this.installmentCount;
    }
    public String getOrderNo() {
        return this.orderNo;
    }
    public String getRefNo() {
        return this.refNo;
    }
    public void setAccountStatus(String str) {
        this.accountStatus = str;
    }
    public void setAmount(String str) {
        this.amount = str;
    }
    public void setInstallmentCount(String str) {
        this.installmentCount = str;
    }
    public void setOrderNo(String str) {
        this.orderNo = str;
    }
    public void setRefNo(String str) {
        this.refNo = str;
    }
}
