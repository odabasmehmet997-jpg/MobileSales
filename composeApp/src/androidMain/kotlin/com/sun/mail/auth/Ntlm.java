package com.sun.mail.auth;

import com.sun.mail.util.BASE64EncoderStream;
import com.sun.mail.util.MailLogger;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.logging.Level;

public class Ntlm {
    static boolean assertionsDisabled = true;
    static Class classcomsunmailauthNtlm = ("com.sun.mail.auth.Ntlm").getClass();
    private static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private Cipher cipher;
    private SecretKeyFactory fac;
    private final String hostname;
    private final MailLogger logger;
    private MD4 md4;
    private final String ntdomain;
    private final String password;
    private byte[] type1;
    private byte[] type3;
    private final String username;
    private void init0() {
        final byte[] bArr = new byte[256];
        type1 = bArr;
        type3 = new byte[256];
        System.arraycopy(new byte[]{78, 84, 76, 77, 83, 83, 80, 0, 1}, 0, bArr, 0, 9);
        final byte[] bArr2 = type1;
        bArr2[12] = 3;
        bArr2[13] = -78;
        bArr2[28] = 32;
        System.arraycopy(new byte[]{78, 84, 76, 77, 83, 83, 80, 0, 3}, 0, type3, 0, 9);
        final byte[] bArr3 = type3;
        bArr3[12] = 24;
        bArr3[14] = 24;
        bArr3[20] = 24;
        bArr3[22] = 24;
        bArr3[32] = 64;
        bArr3[60] = 1;
        bArr3[61] = -126;
        try {
            fac = SecretKeyFactory.getInstance("DES");
            cipher = Cipher.getInstance("DES/ECB/NoPadding");
            md4 = new MD4();
        } catch (final NoSuchPaddingException unused) {
            if (!assertionsDisabled) {
                throw new AssertionError();
            }
        } catch (final NoSuchAlgorithmException unused2) {
            if (!assertionsDisabled) {
                throw new AssertionError();
            }
        }
    }
    public Ntlm(String str, String str2, String str3, final String str4, final MailLogger mailLogger) {
        final int indexOf = str2.indexOf(46);
        str2 = -1 != indexOf ? str2.substring(0, indexOf) : str2;
        final int indexOf2 = str3.indexOf(92);
        if (-1 != indexOf2) {
            str = str3.substring(0, indexOf2).toUpperCase();
            str3 = str3.substring(indexOf2 + 1);
        } else if (null == str) {
            str = "";
        }
        ntdomain = str;
        hostname = str2;
        username = str3;
        password = str4;
        logger = mailLogger.getLogger(this.getClass(), "DEBUG NTLM");
        this.init0();
    }
    private void copybytes(final byte[] bArr, final int i2, final String str, final String str2) {
        try {
            final byte[] bytes = str.getBytes(str2);
            System.arraycopy(bytes, 0, bArr, i2, bytes.length);
        } catch (final UnsupportedEncodingException unused) {
            if (!Ntlm.assertionsDisabled) {
                throw new AssertionError();
            }
        }
    }
    public String generateType1Msg(final int i2) {
        final int length = ntdomain.length();
        final byte[] bArr = type1;
        final byte b2 = (byte) (length % 256);
        bArr[16] = b2;
        final byte b3 = (byte) (length / 256);
        bArr[17] = b3;
        bArr[18] = b2;
        bArr[19] = b3;
        if (0 == length) {
            bArr[13] = (byte) (bArr[13] & -17);
        }
        final int length2 = hostname.length();
        final byte[] bArr2 = type1;
        final byte b4 = (byte) (length2 % 256);
        bArr2[24] = b4;
        final byte b5 = (byte) (length2 / 256);
        bArr2[25] = b5;
        bArr2[26] = b4;
        bArr2[27] = b5;
        this.copybytes(bArr2, 32, hostname, "iso-8859-1");
        final int i3 = length2 + 32;
        this.copybytes(type1, i3, ntdomain, "iso-8859-1");
        final byte[] bArr3 = type1;
        bArr3[20] = (byte) (i3 % 256);
        bArr3[21] = (byte) (i3 / 256);
        final int i4 = i3 + length;
        final byte[] bArr4 = new byte[i4];
        System.arraycopy(bArr3, 0, bArr4, 0, i4);
        if (logger.isLoggable(Level.FINE)) {
            final MailLogger mailLogger = logger;
            String stringBuffer = "type 1 message: " +
                    Ntlm.toHex(bArr4);
            mailLogger.fine(stringBuffer);
        }
        return new String(BASE64EncoderStream.encode(bArr4), StandardCharsets.ISO_8859_1);
    }
    private byte[] makeDesKey(final byte[] bArr, final int i2) {
        final byte[] bArr2 = bArr;
        final int length = bArr2.length;
        final int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            final byte b2 = bArr2[i3];
            iArr[i3] = 0 > b2 ? b2 + 256 : b2;
        }
        final int i4 = iArr[i2];
        final int i5 = iArr[i2 + 1];
        final int i6 = iArr[i2 + 2];
        final int i7 = iArr[i2 + 3];
        final int i8 = iArr[i2 + 4];
        final int i9 = iArr[i2 + 5];
        final int i10 = iArr[i2 + 6];
        return new byte[]{(byte) i4, (byte) (((i4 << 7) & 255) | (i5 >> 1)), (byte) (((i5 << 6) & 255) | (i6 >> 2)), (byte) (((i6 << 5) & 255) | (i7 >> 3)), (byte) (((i7 << 4) & 255) | (i8 >> 4)), (byte) (((i8 << 3) & 255) | (i9 >> 5)), (byte) (((i9 << 2) & 255) | (i10 >> 6)), (byte) ((i10 << 1) & 255)};
    }
    private byte[] calcLMHash() throws GeneralSecurityException {
        byte[] bArr;
        final byte[] bArr2 = {75, 71, 83, 33, 64, 35, 36, 37};
        bArr = this.password.toUpperCase(Locale.ENGLISH).getBytes(StandardCharsets.ISO_8859_1);
        int i2 = 14;
        final byte[] bArr3 = new byte[14];
        final int length = password.length();
        if (14 >= length) {
            i2 = length;
        }
        System.arraycopy(bArr, 0, bArr3, 0, i2);
        final DESKeySpec dESKeySpec = new DESKeySpec(this.makeDesKey(bArr3, 0));
        final DESKeySpec dESKeySpec2 = new DESKeySpec(this.makeDesKey(bArr3, 7));
        final SecretKey generateSecret = fac.generateSecret(dESKeySpec);
        final SecretKey generateSecret2 = fac.generateSecret(dESKeySpec2);
        cipher.init(1, generateSecret);
        final byte[] doFinal = cipher.doFinal(bArr2, 0, 8);
        cipher.init(1, generateSecret2);
        final byte[] doFinal2 = cipher.doFinal(bArr2, 0, 8);
        final byte[] bArr4 = new byte[21];
        System.arraycopy(doFinal, 0, bArr4, 0, 8);
        System.arraycopy(doFinal2, 0, bArr4, 8, 8);
        return bArr4;
    }
    private byte[] calcNTHash() throws GeneralSecurityException {
        byte[] bArr;
        try {
            bArr = password.getBytes("UnicodeLittleUnmarked");
        } catch (final UnsupportedEncodingException unused) {
            if (Ntlm.assertionsDisabled) {
                bArr = null;
            } else {
                throw new AssertionError();
            }
        }
        final byte[] bArr2 = new byte[21];
        System.arraycopy(md4.digest(bArr), 0, bArr2, 0, 16);
        return bArr2;
    }
    private byte[] calcResponse(final byte[] bArr, final byte[] bArr2) throws GeneralSecurityException {
        if (Ntlm.assertionsDisabled || 21 == bArr.length) {
            final DESKeySpec dESKeySpec = new DESKeySpec(this.makeDesKey(bArr, 0));
            final DESKeySpec dESKeySpec2 = new DESKeySpec(this.makeDesKey(bArr, 7));
            final DESKeySpec dESKeySpec3 = new DESKeySpec(this.makeDesKey(bArr, 14));
            final SecretKey generateSecret = fac.generateSecret(dESKeySpec);
            final SecretKey generateSecret2 = fac.generateSecret(dESKeySpec2);
            final SecretKey generateSecret3 = fac.generateSecret(dESKeySpec3);
            cipher.init(1, generateSecret);
            final byte[] doFinal = cipher.doFinal(bArr2, 0, 8);
            cipher.init(1, generateSecret2);
            final byte[] doFinal2 = cipher.doFinal(bArr2, 0, 8);
            cipher.init(1, generateSecret3);
            final byte[] doFinal3 = cipher.doFinal(bArr2, 0, 8);
            final byte[] bArr3 = new byte[24];
            System.arraycopy(doFinal, 0, bArr3, 0, 8);
            System.arraycopy(doFinal2, 0, bArr3, 8, 8);
            System.arraycopy(doFinal3, 0, bArr3, 16, 8);
            return bArr3;
        }
        throw new AssertionError();
    }
    public String generateType3Msg(final String r12) {
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.auth.Ntlm.generateType3Msg(java.lang.String):java.lang.String");
    }
    private static String toHex(final byte[] bArr) {
        final StringBuffer stringBuffer = new StringBuffer(bArr.length * 3);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            stringBuffer.append(Ntlm.hex[(bArr[i2] >> 4) & 15]);
            stringBuffer.append(Ntlm.hex[bArr[i2] & 15]);
            stringBuffer.append(' ');
        }
        return stringBuffer.toString();
    }
}
