package cardtek.masterpass.data;

import java.io.Serializable;

public class MasterPassCard implements Serializable {
    private String bankIca;
    private String cardStatus;
    private String eftCode;
    private String isMasterPassMember;
    private String loyaltyCode;
    private String maskedPan;
    private String name;
    private String productName;
    private String promtCpin;
    private String uniqueId;
    private String value2;
    public String getBankIca() {
        return this.bankIca;
    }
    public String getCardStatus() {
        return this.cardStatus;
    }
    public String getEftCode() {
        return this.eftCode;
    }
    public String getIsMasterPassMember() {
        return this.isMasterPassMember;
    }
    public String getLoyaltyCode() {
        return this.loyaltyCode;
    }
    public String getMaskedPan() {
        return this.maskedPan;
    }
    public String getName() {
        return this.name;
    }
    public String getProductName() {
        return this.productName;
    }
    public String getPromtCpin() {
        return this.promtCpin;
    }
    public String getUniqueId() {
        return this.uniqueId;
    }
    public String getValue2() {
        return this.value2;
    }
    public void setBankIca(String str) {
        this.bankIca = str;
    }
    public void setCardStatus(String str) {
        this.cardStatus = str;
    }
    public void setEftCode(String str) {
        this.eftCode = str;
    }
    public void setIsMasterPassMember(String str) {
        this.isMasterPassMember = str;
    }
    public void setLoyaltyCode(String str) {
        this.loyaltyCode = str;
    }
    public void setMaskedPan(String str) {
        this.maskedPan = str;
    }
    public void setName(String str) {
        this.name = str;
    }
    public void setProductName(String str) {
        this.productName = str;
    }
    public void setPromtCpin(String str) {
        this.promtCpin = str;
    }
    public void setUniqueId(String str) {
        this.uniqueId = str;
    }
    public void setValue2(String str) {
    }
}
