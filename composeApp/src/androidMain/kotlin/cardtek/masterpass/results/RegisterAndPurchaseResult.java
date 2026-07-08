package cardtek.masterpass.results;

import cardtek.masterpass.data.Card;
import java.io.Serializable;

/* loaded from: classes.dex */
public class RegisterAndPurchaseResult implements Serializable {
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
