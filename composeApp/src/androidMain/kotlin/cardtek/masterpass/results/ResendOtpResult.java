package cardtek.masterpass.results;

import java.io.Serializable;

public class ResendOtpResult implements Serializable {
    private String cardIssuerName;
    private String maskedPan;
    private String refNo;
    public String getCardIssuerName() {
        return this.cardIssuerName;
    }
    public String getMaskedPan() {
        return this.maskedPan;
    }
    public String getRefNo() {
        return this.refNo;
    }
    public void setCardIssuerName(String cardIssuerName) {
        this.cardIssuerName = cardIssuerName;
    }
    public void setMaskedPan(String maskedPan) {
        this.maskedPan = maskedPan;
    }
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }
}
