package com.proje.mobilesales.core.utils;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.util.Base64;
import com.proje.mobilesales.core.preferences.Preferences;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesEncryption {
    private static final String INITVECTOR = "smpNuAR=jKJ#h9lg";
    private final byte[] _iv;
    private final byte[] _key;
    private final Cipher cipher;
    public SharedPreferences prefs;
    private enum EncryptMode {
        ENCRYPT,
        DECRYPT
    }
    public AesEncryption() throws NoSuchAlgorithmException, NoSuchPaddingException {
        SharedPreferences securePreferences = Preferences.getSecurePreferences(ContextUtils.getmContext());
        this.prefs = securePreferences;
        this.cipher = Cipher.getInstance(Preferences.getEncryptionAlgorithm(securePreferences));
        this._key = new byte[24];
        this._iv = new byte[16];
    }
    @SuppressLint({"TrulyRandom"})
    private String encryptDecrypt(String str, EncryptMode encryptMode) throws UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        String str2;
        int length = Preferences.getEncString(this.prefs).getBytes(StandardCharsets.UTF_8).length;
        int length2 = Preferences.getEncString(this.prefs).getBytes(StandardCharsets.UTF_8).length;
        byte[] bArr = this._key;
        if (length2 > bArr.length) {
            length = bArr.length;
        }
        int length3 = INITVECTOR.getBytes(StandardCharsets.UTF_8).length;
        int length4 = INITVECTOR.getBytes(StandardCharsets.UTF_8).length;
        byte[] bArr2 = this._iv;
        if (length4 > bArr2.length) {
            length3 = bArr2.length;
        }
        System.arraycopy(Preferences.getEncString(this.prefs).getBytes(StandardCharsets.UTF_8), 0, this._key, 0, length);
        System.arraycopy(INITVECTOR.getBytes(StandardCharsets.UTF_8), 0, this._iv, 0, length3);
        SecretKeySpec secretKeySpec = new SecretKeySpec(this._key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(this._iv);
        if (!encryptMode.equals(EncryptMode.ENCRYPT)) {
            str2 = "";
        } else {
            this.cipher.init(1, secretKeySpec, ivParameterSpec);
            str2 = Base64.encodeToString(this.cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)), 0);
            if (str2.charAt(str2.length() - 1) == '\n') {
                str2 = str2.substring(0, str2.length() - 1);
            }
        }
        if (!encryptMode.equals(EncryptMode.DECRYPT)) {
            return str2;
        }
        this.cipher.init(2, secretKeySpec, ivParameterSpec);
        return new String(this.cipher.doFinal(Base64.decode(str.getBytes(), 0)));
    }
    public String encrypt(String str) throws InvalidKeyException, UnsupportedEncodingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        return encryptDecrypt(str, EncryptMode.ENCRYPT);
    }
    public String decrypt(String str) throws InvalidKeyException, UnsupportedEncodingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        return encryptDecrypt(str, EncryptMode.DECRYPT);
    }
}
