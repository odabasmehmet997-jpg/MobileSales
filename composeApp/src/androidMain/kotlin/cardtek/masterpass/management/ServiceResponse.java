package cardtek.masterpass.management;

public class ServiceResponse {
    private String cardIssuerName;
    private String cardUniqueId;
    private String maskedPan;
    private String refNo;
    private String responseCode;
    private String responseDesc;
    private String token;
    private String url3D;
    private String url3DError;
    private String url3DSuccess;
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
    public String getToken() {
        return this.token;
    }
    public String getUrl3D() {
        return this.url3D;
    }
    public String getUrl3DError() {
        return this.url3DError;
    }
    public String getUrl3DSuccess() {
        return this.url3DSuccess;
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
    public void setToken(String str) {
        this.token = str;
    }
    public void setUrl3D(String str) {
        this.url3D = str;
    }
    public void setUrl3DError(String str) {
        this.url3DError = str;
    }
    public void setUrl3DSuccess(String str) {
        this.url3DSuccess = str;
    }
}
