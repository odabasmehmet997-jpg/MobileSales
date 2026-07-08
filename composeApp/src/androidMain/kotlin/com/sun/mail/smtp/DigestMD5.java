package com.sun.mail.smtp;

import android.os.Build;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import com.sun.mail.util.MailLogger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.logging.Level;

public class DigestMD5 {
    private static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private String clientResponse;
    private final MailLogger logger;
    private MessageDigest md5;
    private String uri;
    public DigestMD5(final MailLogger mailLogger) {
        logger = mailLogger.getLogger(this.getClass(), "DEBUG DIGEST-MD5");
        mailLogger.config("DIGEST-MD5 Loaded");
    }
    public byte[] authClient(String str, final String str2, final String str3, String str4, final String str5) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final BASE64EncoderStream bASE64EncoderStream = new BASE64EncoderStream(byteArrayOutputStream, Integer.MAX_VALUE);
        try {
            final SecureRandom secureRandom = new SecureRandom();
            md5 = MessageDigest.getInstance("MD5");
            final StringBuffer stringBuffer = new StringBuffer();
            String stringBuffer2 = "smtp/" +
                    str;
            uri = stringBuffer2;
            final byte[] bArr = new byte[32];
            logger.fine("Begin authentication ...");
            final Hashtable hashtable = this.tokenize(str5);
            if (null == str4) {
                final String str6 = (String) hashtable.get("realm");
                if (null != str6) {
                    str = new StringTokenizer(str6, ",").nextToken();
                }
                str4 = str;
            }
            final String str7 = (String) hashtable.get("nonce");
            secureRandom.nextBytes(bArr);
            bASE64EncoderStream.write(bArr);
            bASE64EncoderStream.flush();
            String byteArrayOutputStream2 = "";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                byteArrayOutputStream2 = byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1);
            }
            byteArrayOutputStream.reset();
            final MessageDigest messageDigest = md5;
            String stringBuffer3 = str2 +
                    ":" +
                    str4 +
                    ":" +
                    str3;
            messageDigest.update(messageDigest.digest(ASCIIUtility.getBytes(stringBuffer3)));
            final MessageDigest messageDigest2 = md5;
            String stringBuffer4 = ":" +
                    str7 +
                    ":" +
                    byteArrayOutputStream2;
            messageDigest2.update(ASCIIUtility.getBytes(stringBuffer4));
            String stringBuffer5 = DigestMD5.toHex(md5.digest()) +
                    ":" +
                    str7 +
                    ":" +
                    "00000001" +
                    ":" +
                    byteArrayOutputStream2 +
                    ":" +
                    "auth" +
                    ":";
            clientResponse = stringBuffer5;
            final MessageDigest messageDigest3 = md5;
            String stringBuffer6 = "AUTHENTICATE:" +
                    uri;
            messageDigest3.update(ASCIIUtility.getBytes(stringBuffer6));
            final MessageDigest messageDigest4 = md5;
            String stringBuffer7 = clientResponse +
                    DigestMD5.toHex(md5.digest());
            messageDigest4.update(ASCIIUtility.getBytes(stringBuffer7));
            String stringBuffer8 = "username=\"" +
                    str2 +
                    "\"";
            stringBuffer.append(stringBuffer8);
            String stringBuffer9 = ",realm=\"" +
                    str4 +
                    "\"";
            stringBuffer.append(stringBuffer9);
            final String stringBuffer10 = ",qop=" +
                    "auth";
            stringBuffer.append(stringBuffer10);
            final String stringBuffer11 = ",nc=" +
                    "00000001";
            stringBuffer.append(stringBuffer11);
            String stringBuffer12 = ",nonce=\"" +
                    str7 +
                    "\"";
            stringBuffer.append(stringBuffer12);
            String stringBuffer13 = ",cnonce=\"" +
                    byteArrayOutputStream2 +
                    "\"";
            stringBuffer.append(stringBuffer13);
            String stringBuffer14 = ",digest-uri=\"" +
                    uri +
                    "\"";
            stringBuffer.append(stringBuffer14);
            String stringBuffer15 = ",response=" +
                    DigestMD5.toHex(md5.digest());
            stringBuffer.append(stringBuffer15);
            if (logger.isLoggable(Level.FINE)) {
                final MailLogger mailLogger = logger;
                String stringBuffer16 = "Response => " +
                        stringBuffer;
                mailLogger.fine(stringBuffer16);
            }
            bASE64EncoderStream.write(ASCIIUtility.getBytes(stringBuffer.toString()));
            bASE64EncoderStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (final NoSuchAlgorithmException e2) {
            logger.log(Level.FINE, "NoSuchAlgorithmException", e2);
            throw new IOException(e2.toString());
        }
    }
    public boolean authServer(final String str) throws IOException {
        final Hashtable hashtable = this.tokenize(str);
        final MessageDigest messageDigest = md5;
        String stringBuffer = ":" +
                uri;
        messageDigest.update(ASCIIUtility.getBytes(stringBuffer));
        final MessageDigest messageDigest2 = md5;
        String stringBuffer2 = clientResponse +
                DigestMD5.toHex(md5.digest());
        messageDigest2.update(ASCIIUtility.getBytes(stringBuffer2));
        final String hex = DigestMD5.toHex(md5.digest());
        if (hex.equals(hashtable.get("rspauth"))) {
            return true;
        }
        if (!logger.isLoggable(Level.FINE)) {
            return false;
        }
        final MailLogger mailLogger = logger;
        String stringBuffer3 = "Expected => rspauth=" +
                hex;
        mailLogger.fine(stringBuffer3);
        return false;
    }
    private Hashtable tokenize(final String str) throws IOException {
        final Hashtable hashtable = new Hashtable();
        final byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
        final StreamTokenizer streamTokenizer = new StreamTokenizer(new InputStreamReader(new BASE64DecoderStream(new ByteArrayInputStream(bytes, 4, bytes.length - 4)), StandardCharsets.ISO_8859_1));
        streamTokenizer.ordinaryChars(48, 57);
        streamTokenizer.wordChars(48, 57);
        while (true) {
            String str2 = null;
            while (true) {
                final int nextToken = streamTokenizer.nextToken();
                if (-1 != nextToken) {
                    if (-3 == nextToken) {
                        if (null != str2) {
                            break;
                        }
                        str2 = streamTokenizer.sval;
                    } else if (34 == nextToken) {
                        break;
                    }
                } else {
                    return hashtable;
                }
            }
            if (logger.isLoggable(Level.FINE)) {
                final MailLogger mailLogger = logger;
                String stringBuffer = "Received => " +
                        str2 +
                        "='" +
                        streamTokenizer.sval +
                        "'";
                mailLogger.fine(stringBuffer);
            }
            if (hashtable.containsKey(str2)) {
                String stringBuffer2 = hashtable.get(str2) +
                        "," +
                        streamTokenizer.sval;
                hashtable.put(str2, stringBuffer2);
            } else {
                hashtable.put(str2, streamTokenizer.sval);
            }
        }
    }
    private static String toHex(final byte[] bArr) {
        final char[] cArr = new char[(bArr.length * 2)];
        int i2 = 0;
        for (final byte b2 : bArr) {
            final int i3 = i2 + 1;
            final char[] cArr2 = DigestMD5.digits;
            cArr[i2] = cArr2[(b2 & 255) >> 4];
            i2 += 2;
            cArr[i3] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }
}
