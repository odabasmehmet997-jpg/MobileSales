package cardtek.masterpass.data;

public class Card {
    private String cardNumber;
    private String cardholderName;
    private String cvv;
    private int expiryMonth;
    private int expiryYear;
    public String getCardNumber() {
        return this.cardNumber;
    }
    public String getCardholderName() {
        return this.cardholderName;
    }
    public String getCvv() {
        return this.cvv;
    }
    public int getExpiryMonth() {
        return this.expiryMonth;
    }
    public int getExpiryYear() {
        return this.expiryYear;
    }
    public void setCardNumber(String str) {
        this.cardNumber = str;
    }
    public void setCardholderName(String str) {
        this.cardholderName = str;
    }
    public void setCvv(String str) {
        this.cvv = str;
    }
    public void setExpiryMonth(int i2) {
        this.expiryMonth = i2;
    }
    public void setExpiryYear(int i2) {
        this.expiryYear = i2;
    }
}
