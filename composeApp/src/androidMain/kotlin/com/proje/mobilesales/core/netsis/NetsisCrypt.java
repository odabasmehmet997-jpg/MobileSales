package com.proje.mobilesales.core.netsis;

import android.content.SharedPreferences;
import android.util.Base64;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.ContextUtils;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class NetsisCrypt {
    private NetsisCrypt() {
    }

    private static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i2 = 0; i2 < length; i2 += 2) {
            bArr[i2 / 2] = (byte) ((Character.digit(str.charAt(i2), 16) << 4) + Character.digit(str.charAt(i2 + 1), 16));
        }
        return bArr;
    }

    public static String generateTrustedKey(String str) {
        try {
            SharedPreferences securePreferences = Preferences.getSecurePreferences(ContextUtils.getmContext());
            byte[] encode = Base64.encode(llfInitiateString(str).getBytes(StandardCharsets.UTF_8), 2);
            Cipher cipher = Cipher.getInstance(Preferences.getEncryptionAlgorithm(securePreferences));
            cipher.init(1, new SecretKeySpec(hexStringToByteArray(Preferences.getNetsisEncryptionKey(securePreferences)), "AES"), new IvParameterSpec(hexStringToByteArray(Preferences.getNetsisEncryptionVector(securePreferences))));
            return Base64.encodeToString(cipher.doFinal(encode), 2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String llfMakeDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i2 = calendar.get(1);
        int i3 = calendar.get(2) + 1;
        int i4 = calendar.get(5);
        return Integer.valueOf(i2 + i3 + i4 + calendar.get(11) + calendar.get(12)).toString();
    }

    private static String llfMakeWord(String str) throws UnsupportedEncodingException {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer valueOf = Integer.valueOf(calendar.get(1));
        Integer valueOf2 = Integer.valueOf(calendar.get(2) + 1);
        new String("L0G0.C.R.M.P4.".getBytes(), StandardCharsets.UTF_8);
        return (((("L0G0." + str + ".P4.") + valueOf) + ".-") + valueOf2) + ".-144";
    }

    private static String llfRamdomString() {
        SecureRandom secureRandom = new SecureRandom();
        String str = "";
        for (int i2 = 0; i2 < 16; i2++) {
            int nextInt = secureRandom.nextInt(35);
            if (nextInt == 0) {
                nextInt++;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            if (nextInt > 36) {
                nextInt--;
            }
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(nextInt));
            str = sb.toString();
        }
        return str;
    }

    private static String llfInitiateString(String str) throws UnsupportedEncodingException {
        return llfMakeWord(str) + "|" + llfMakeDate() + "|" + llfRamdomString();
    }
}
