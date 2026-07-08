package cardtek.masterpass.response;

import java.io.Serializable;

public class ServiceError implements Serializable {
    private String cardIssuerName;
    private String cardUniqueId;
    private String internalRespCode;
    private String internalRespDesc;
    private String maskedPan;
    private String mdErrorMsg;
    private String mdStatus;
    private String refNo;
    private String responseCode;
    private String responseDesc;
    public String getCardIssuerName() {
        return this.cardIssuerName;
    }
    public String getCardUniqueId() {
        return this.cardUniqueId;
    }
    public String getInternalRespCode() {
        return this.internalRespCode;
    }
    public String getInternalRespDesc() {
        return this.internalRespDesc;
    }
    public String getMaskedPan() {
        return this.maskedPan;
    }
    public String getMdErrorMsg() {
        return this.mdErrorMsg;
    }
    public String getMdStatus() {
        return this.mdStatus;
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
    public void setInternalRespCode(String str) {
        this.internalRespCode = str;
    }
    public void setInternalRespDesc(String str) {
        this.internalRespDesc = str;
    }
    public void setMaskedPan(String str) {
        this.maskedPan = str;
    }
    public void setMdErrorMsg(String str) {
        this.mdErrorMsg = str;
    }
    public void setMdStatus(String str) {
        this.mdStatus = str;
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

    public void setErrorCode(String jsonParseError) {

    }
}
