package cardtek.masterpass.attributes;

public interface CardNumberTextListener {
    void cancelInstallment();
    void getFirst6Chars(String str);
    void getFirstChar(char c2);
}
