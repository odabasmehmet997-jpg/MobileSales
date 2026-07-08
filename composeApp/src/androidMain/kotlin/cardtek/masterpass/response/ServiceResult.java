package cardtek.masterpass.response;

import java.io.Serializable;

public class ServiceResult implements Serializable {
    private String cardIssuerName;
    private String cardUniqueId;
    private String maskedPan;
    private String refNo;
    private String responseCode;
    private String responseDesc;
    public String getCardIssuerName() {
        return this.cardIssuerName;
    }
    public String getCardUniqueId() {
        return this.cardUniqueId;
    }
    public String getMaskedPan() {
        return this.maskedPan;
    }
    public String getRefNo() {
        return this.refNo;
    }
    public String getResponseCode() {
        return this.responseCode;
    }
    public String getResponseDesc() {
        return this.responseDesc;
    }
    public void setCardIssuerName(String str) {
        this.cardIssuerName = str;
    }
    public void setCardUniqueId(String str) {
        this.cardUniqueId = str;
    }
    public void setMaskedPan(String str) {
        this.maskedPan = str;
    }
    public void setRefNo(String str) {
        this.refNo = str;
    }
    public void setResponseCode(String str) {
        this.responseCode = str;
    }
    public void setResponseDesc(String str) {
        this.responseDesc = str;
    }
}
