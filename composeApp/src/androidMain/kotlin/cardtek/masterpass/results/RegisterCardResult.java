package cardtek.masterpass.results;

import cardtek.masterpass.data.Card;
import java.io.Serializable;

public class RegisterCardResult implements Serializable {
    private Card card;
    private String refNo;
    private boolean result;
    private String token;
    public Card getCard() {
        return this.card;
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
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }
    public void setResult(boolean result) {
        this.result = result;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
