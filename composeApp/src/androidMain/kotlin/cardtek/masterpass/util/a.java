package cardtek.masterpass.util;

/* loaded from: classes.dex */
public enum a {
    E000("E000", "Internal Error"),
    E001("E001", "Connection Error"),
    E002("E002", "Card Number is empty"),
    E003("E003", "Card Number is invalid"),
    E004("E004", "Cvv/Cvc2 is empty"),
    E005("E005", "Cvv/Cvc2 is invalid"),
    E006("E006", "Card Name is empty"),
    E007("E007", "Validation Code is empty"),
    E008("E008", "Validation Code is invalid"),
    E009("E009", "3D Url is empty"),
    E010("E010", "3D Secure is not validated"),
    E011("E011", "Terms&Condition checkbox is not selected"),
    E012("E012", "Expire Year is invalid"),
    E013("E013", "Terms&Condition checkbox is null"),
    E014("E014", "Terms&Condition checkbox was not selected for Card Registration on Direct Purchase"),
    E015("E015", "Device does not have NFC reader"),
    E016("E016", "NFC is disabled"),
    E017("E017", "Unsupported Contactless Card"),
    E018("E018", "Sender Alias Name is empty"),
    E019("E019", "Recipient Alias Name is empty"),
    E020("E020", "Money Send Type is not valid");
    public final String name;
    public final String value;
    a(String str, String str2) {
        this.name = str;
        this.value = str2;
    }
}
