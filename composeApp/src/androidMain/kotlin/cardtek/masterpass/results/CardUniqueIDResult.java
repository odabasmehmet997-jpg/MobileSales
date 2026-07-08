package cardtek.masterpass.results;

import java.io.Serializable;

public class CardUniqueIDResult implements Serializable {
    private String cardUniqueId;
    private String refNo;
    public String getCardUniqueId() {
        return this.cardUniqueId;
    }
    public String getRefNo() {
        return this.refNo;
    }
    public void setCardUniqueId(String str) {
        this.cardUniqueId = str;
    }
    public void setRefNo(String str) {
        this.refNo = str;
    }
    public void setResponseCode(String responseCode) {
    }
    public void setResponseDesc(String responseDesc) {
    }
    public void setInternalRespCode(String internalResponseCode) {
    }
    public void setInternalRespDesc(String internalResponseMessage) {

    }
}
