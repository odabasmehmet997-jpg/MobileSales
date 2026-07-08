package cardtek.masterpass.util;

import java.util.HashMap;

public class MasterPassInfo {
    public static String TAG = "MasterPass";
    private static HashMap<String, Object> additionalParameters = null;
    private static int cardIOFrameColor = -1;
    private static String cardIOInfoLanguage = "";
    private static String clientID = "";
    private static boolean cvvRequire = false;
    private static String language = "eng";
    private static String macroMerchantId = "";
    private static String resultUrl3D = "";
    private static String sendSms = "N";
    private static String systemID = "";
    private static String systemKey = "";
    private static String url = "";
    public static HashMap<String, Object> getAdditionalParameters() {
        return additionalParameters;
    }
    public static int getCardIOFrameColor() {
        return cardIOFrameColor;
    }
    public static String getCardIOInfoLanguage() {
        return cardIOInfoLanguage;
    }
    public static String getClientID() {
        return clientID;
    }
    public static String getLanguage() {
        return language;
    }
    public static String getMacroMerchantId() {
        return macroMerchantId;
    }
    public static String getResultUrl3D() {
        return resultUrl3D;
    }
    public static String getSendSms() {
        return sendSms;
    }
    public static String getSystemID() {
        return systemID;
    }
    public static String getSystemKey() {
        return systemKey;
    }
    public static String getUrl() {
        return url;
    }
    public static boolean isCvvRequire() {
        return cvvRequire;
    }
    public static void setAdditionalParameters(HashMap<String, Object> hashMap) {
        additionalParameters = hashMap;
    }
    public static void setCardIOFrameColor(int i2) {
        cardIOFrameColor = i2;
    }
    public static void setCardIOInfoLanguage(String str) {
        cardIOInfoLanguage = str;
    }
    public static void setClientID(String str) {
        clientID = str;
    }
    public static void setCvvRequire(boolean z) {
        cvvRequire = z;
    }
    public static void setLanguage(String str) {
        language = str;
    }
    public static void setMacroMerchantId(String str) {
        macroMerchantId = str;
    }
    public static void setResultUrl3D(String str) {
        resultUrl3D = str;
    }
    public static void setSendSms(String str) {
        sendSms = str;
    }
    public static void setSystemID(String str) {
        systemID = str;
    }
    public static void setSystemKey(String str) {
        systemKey = str;
    }
    public static void setUrl(String str) {
        url = str;
    }
}
