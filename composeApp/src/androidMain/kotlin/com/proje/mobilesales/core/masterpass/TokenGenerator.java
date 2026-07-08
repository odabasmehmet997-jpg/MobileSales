package com.proje.mobilesales.core.masterpass;

import android.content.SharedPreferences;
import android.util.Log;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.ContextUtils;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class TokenGenerator {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static final String TAG = "TokenGenerator";
    private static String clientId = null;
    private static final String datetime = "20160120165502";
    private static String encKey = null;
    private static String encryptionAlgorithmNoPadding = null;
    private static String macKey = null;
    private static final String msiSdnValidated = "01";
    private static final String validationType = "01";

    public static String generateReqRefNo() {
        return String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16)).substring(27, 39);
    }

    private static void initKeys() {
        if (clientId == null || encKey == null || macKey == null) {
            SharedPreferences securePreferences = Preferences.getSecurePreferences(ContextUtils.getmContext());
            clientId = Preferences.getClientId(securePreferences);
            encKey = Preferences.getEncKey(securePreferences);
            macKey = Preferences.getMacKey(securePreferences);
            encryptionAlgorithmNoPadding = Preferences.getEncryptionAlgorithmNoPadding(securePreferences);
        }
    }

    public static String generateTokenWithUserId(String str, String str2, String str3) {
        initKeys();
        Calendar calendar = Calendar.getInstance();
        String timeZone = getTimeZone(calendar);
        String date = getDate(calendar);
        return encryptToken(padToken(((((((Constants.TAG_CLIENT_ID + String.format("%02X", Integer.valueOf(clientId.length())) + ascii2Hex(clientId)) + "FF0201" + timeZone) + Constants.TAG_DATETIME + String.format("%02X", Integer.valueOf(date.length())) + ascii2Hex(date)) + Constants.TAG_MSISDN + String.format("%02X", Integer.valueOf(str2.length())) + ascii2Hex(str2)) + Constants.TAG_REQ_REF_NUMBER + String.format("%02X", Integer.valueOf(str3.length())) + ascii2Hex(str3)) + Constants.TAG_USER_ID + String.format("%02X", Integer.valueOf(str.length())) + ascii2Hex(str)) + "FF0701" + msiSdnValidated));
    }

    public static String generateTokenWithoutUserId(String str, String str2) {
        initKeys();
        Calendar calendar = Calendar.getInstance();
        String timeZone = getTimeZone(calendar);
        String date = getDate(calendar);
        return encryptToken(padToken(((((Constants.TAG_CLIENT_ID + String.format("%02X", Integer.valueOf(clientId.length())) + ascii2Hex(clientId)) + "FF0201" + timeZone) + Constants.TAG_DATETIME + String.format("%02X", Integer.valueOf(date.length())) + ascii2Hex(date)) + Constants.TAG_MSISDN + String.format("%02X", Integer.valueOf(str.length())) + ascii2Hex(str)) + Constants.TAG_REQ_REF_NUMBER + String.format("%02X", Integer.valueOf(str2.length())) + ascii2Hex(str2)));
    }

    public static String generateTokenForPurchase(String str, String str2, String str3) {
        initKeys();
        Calendar calendar = Calendar.getInstance();
        String timeZone = getTimeZone(calendar);
        String date = getDate(calendar);
        return encryptToken(padToken(((((((((Constants.TAG_CLIENT_ID + String.format("%02X", Integer.valueOf(clientId.length())) + ascii2Hex(clientId)) + "FF0201" + timeZone) + Constants.TAG_DATETIME + String.format("%02X", Integer.valueOf(date.length())) + ascii2Hex(date)) + Constants.TAG_MSISDN + String.format("%02X", Integer.valueOf(str2.length())) + ascii2Hex(str2)) + Constants.TAG_REQ_REF_NUMBER + String.format("%02X", Integer.valueOf(str3.length())) + ascii2Hex(str3)) + Constants.TAG_USER_ID + String.format("%02X", Integer.valueOf(str.length())) + ascii2Hex(str)) + "FF0701" + msiSdnValidated) + "FF0801" + validationType) + "FF090100"));
    }

    private static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i2 = 0; i2 < length; i2 += 2) {
            bArr[i2 / 2] = (byte) ((Character.digit(str.charAt(i2), 16) << 4) + Character.digit(str.charAt(i2 + 1), 16));
        }
        return bArr;
    }

    private static String padToken(String str) {
        if (str.length() % 32 == 0) {
            return str;
        }
        return rightPadding(str + "8", '0', ((str.length() / 32) + 1) * 32);
    }

    private static String encryptToken(String str) {
        initKeys();
        try {
            Cipher cipher = Cipher.getInstance(encryptionAlgorithmNoPadding);
            byte[] hexStringToByteArray = hexStringToByteArray(encKey);
            byte[] hexStringToByteArray2 = hexStringToByteArray("00000000000000000000000000000000");
            byte[] hexStringToByteArray3 = hexStringToByteArray(str);
            cipher.init(1, new SecretKeySpec(hexStringToByteArray, "AES"), new IvParameterSpec(hexStringToByteArray2));
            String bytesToHex = bytesToHex(cipher.doFinal(hexStringToByteArray3));
            String str2 = macKey;
            Charset charset = StandardCharsets.US_ASCII;
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(charset), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKeySpec);
            return bytesToHex + bytesToHex(mac.doFinal(bytesToHex.getBytes(charset)));
        } catch (InvalidAlgorithmParameterException e2) {
            Log.e(TAG, "encryptToken", e2);
            return "";
        } catch (InvalidKeyException e3) {
            Log.e(TAG, "encryptToken", e3);
            return "";
        } catch (NoSuchAlgorithmException e4) {
            Log.e(TAG, "encryptToken", e4);
            return "";
        } catch (BadPaddingException e5) {
            Log.e(TAG, "encryptToken", e5);
            return "";
        } catch (IllegalBlockSizeException e6) {
            Log.e(TAG, "encryptToken", e6);
            return "";
        } catch (NoSuchPaddingException e7) {
            Log.e(TAG, "encryptToken", e7);
            return "";
        }
    }

    private static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b2 = bArr[i2];
            int i3 = i2 * 2;
            char[] cArr2 = HEX_ARRAY;
            cArr[i3] = cArr2[(b2 & 255) >>> 4];
            cArr[i3 + 1] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }

    private static String ascii2Hex(String str) {
        String str2 = "";
        for (int i2 = 0; i2 < str.length(); i2++) {
            str2 = str2 + Integer.toHexString(str.charAt(i2)).toUpperCase();
        }
        return str2.replace("-", "");
    }

    private static String getDate(Calendar calendar) {
        try {
            return new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(calendar.getTime());
        } catch (Exception unused) {
            return "";
        }
    }

    private static String getTimeZone(Calendar calendar) {
        int offset = calendar.getTimeZone().getOffset(calendar.getTimeInMillis()) / 3600000;
        if (offset > 0) {
            return "0" + Integer.toHexString(offset);
        }
        if (offset == 0) {
            return "00";
        }
        return ("8") + Integer.toHexString(offset * (-1));
    }

    private static String rightPadding(String str, char c2, int i2) {
        return String.format("%" + (-i2) + "s", str).replace(' ', c2);
    }
}
