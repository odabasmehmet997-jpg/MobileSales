package cardtek.masterpass.results;

import cardtek.masterpass.data.Card;
import java.io.Serializable;

public class DirectPurchaseResult implements Serializable {
    private Card card;
    private String cardUniqueId;
    private String refNo;
    private boolean result;
    private String token;
    public Card getCard() {
        return this.card;
    }
    public String getCardUniqueId() {
        return this.cardUniqueId;
    }
    public String getRefNo() {
        return this.refNo;
    }
    public boolean getResult() {
        return this.result;
    }
    public String getToken() {
        return this.token;
    }
    public void setCard(Card card) {
        this.card = card;
    }
    public void setCardUniqueId(String str) {
        this.cardUniqueId = str;
    }
    public void setRefNo(String str) {
        this.refNo = str;
    }
    public void setResult(boolean z) {
        this.result = z;
    }
    public void setToken(String str) {
        this.token = str;
    }
}
