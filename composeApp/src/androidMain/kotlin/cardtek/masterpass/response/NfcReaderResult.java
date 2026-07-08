package cardtek.masterpass.response;

import cardtek.masterpass.attributes.MasterPassEditText;
import cardtek.masterpass.util.CardType;

public class NfcReaderResult {
    private MasterPassEditText cardNumber;
    private CardType cardType;
    private int expireMonth;
    private int expireYear;
    private String issuerIdentificationNumber;
    public MasterPassEditText getCardNumber() {
        return this.cardNumber;
    }
    public CardType getCardType() {
        return this.cardType;
    }
    public int getExpireMonth() {
        return this.expireMonth;
    }
    public int getExpireYear() {
        return this.expireYear;
    }
    public String getIssuerIdentificationNumber() {
        return this.issuerIdentificationNumber;
    }
    public void setCardNumber(MasterPassEditText masterPassEditText) {
        this.cardNumber = masterPassEditText;
    }
    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
    public void setExpireMonth(int i2) {
        this.expireMonth = i2;
    }
    public void setExpireYear(int i2) {
        this.expireYear = i2;
    }
    public void setIssuerIdentificationNumber(String str) {
        this.issuerIdentificationNumber = str;
    }
}
