package okio;

import com.google.firebase.messaging.Constants;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.internal._ByteStringKt;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public class ByteString implements Serializable, Comparable<ByteString> {
    public static final Companion Companion = new Companion(null);
    public static final ByteString EMPTY = new ByteString(new byte[0]);
    private static final long serialVersionUID = 1;
    private final byte[] data;
    private transient int hashCode;
    private transient String utf8;
    public static ByteString decodeBase64(String str) {
        return Companion.decodeBase64(str);
    }
    public static ByteString decodeHex(String str) {
        return Companion.decodeHex(str);
    }
    public static ByteString encodeString(String str, Charset charset) {
        return Companion.encodeString(str, charset);
    }
    public static ByteString encodeUtf8(String str) {
        return Companion.encodeUtf8(str);
    }
    public static ByteString m653of(ByteBuffer byteBuffer) {
        return Companion.m656of(byteBuffer);
    }
    public static ByteString m654of(byte... bArr) {
        return Companion.m657of(bArr);
    }
    public static ByteString m655of(byte[] bArr, int i2, int i3) {
        return Companion.m658of(bArr, i2, i3);
    }
    public static ByteString read(InputStream inputStream, int i2) throws IOException {
        return Companion.read(inputStream, i2);
    }
    public final int indexOf(ByteString other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return indexOfdefault(this, other, 0, 2, null);
    }
    public final int indexOf(byte[] other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return indexOfdefault(this, other, 0, 2, null);
    }
    public final int lastIndexOf(ByteString other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return lastIndexOfdefault(this, other, 0, 2, null);
    }
    public final int lastIndexOf(byte[] other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return lastIndexOfdefault(this, other, 0, 2, null);
    }
    public final ByteString substring() {
        return substringdefault(this, 0, 0, 3, null);
    }
    public final ByteString substring(int i2) {
        return substringdefault(this, i2, 0, 2, null);
    }
    public String utf8() {
        String utf8okio = utf8;
        if (null != utf8okio) {
            return utf8okio;
        }
        String utf8String = _JvmPlatformKt.toUtf8String(internalArrayokio());
        utf8 = utf8String;
        return utf8String;
    }
    public String base64() {
        return _Base64Kt.encodeBase64default(getDataokio(), null, 1, null);
    }
    public String base64Url() {
        return _Base64Kt.encodeBase64(getDataokio(), _Base64Kt.getBASE64_URL_SAFE());
    }
    public ByteString(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }
    public final byte[] getDataokio() {
        return this.data;
    }
    public String hex() {
        char[] cArr = new char[getDataokio().length * 2];
        int i2 = 0;
        for (byte b2 : getDataokio()) {
            int i3 = i2 + 1;
            cArr[i2] = _ByteStringKt.getHEX_DIGIT_CHARS()[(b2 >> 4) & 15];
            i2 += 2;
            cArr[i3] = _ByteStringKt.getHEX_DIGIT_CHARS()[b2 & 15];
        }
        return StringsKt.concatToString(cArr);
    }
    public final int getHashCodeokio() {
        return this.hashCode;
    }
    public final void setHashCodeokio(int i2) {
        this.hashCode = i2;
    }
    public final String getUtf8okio() {
        return this.utf8;
    }
    public final void setUtf8okio(String str) {
        this.utf8 = str;
    }
    public String string(Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        return new String(this.data, charset);
    }
    public final ByteString md5() {
        try {
            return digestokio("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public final ByteString sha1() {
        try {
            return digestokio("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public ByteString toAsciiLowercase() {
        byte b2;
        for (int i2 = 0; i2 < getDataokio().length; i2++) {
            byte b3 = getDataokio()[i2];
            final byte b4 = 65;
            if (b4 <= b3 && b3 <= (b2 = 90)) {
                byte[] dataokio = getDataokio();
                byte[] bArrCopyOf = Arrays.copyOf(dataokio, dataokio.length);
                checkNotNullExpressionValue(bArrCopyOf, "copyOf(this, size)");
                bArrCopyOf[i2] = (byte) (b3 + 32);
                for (int i3 = i2 + 1; i3 < bArrCopyOf.length; i3++) {
                    byte b5 = bArrCopyOf[i3];
                    if (b4 <= b5 && b5 <= b2) {
                        bArrCopyOf[i3] = (byte) (b5 + 32);
                    }
                }
                return new ByteString(bArrCopyOf);
            }
        }
        return this;
    }
    public final ByteString sha256() {
        try {
            return digestokio("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public final ByteString sha512() {
        try {
            return digestokio("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public ByteString digestokio(String algorithm) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(this.data, 0, size());
        byte[] digestBytes = messageDigest.digest();
        checkNotNullExpressionValue(digestBytes, "digestBytes");
        return new ByteString(digestBytes);
    }
    public ByteString hmacSha1(ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            return hmacokio("HmacSHA1", key);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    public ByteString hmacSha256(ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            return hmacokio("HmacSHA256", key);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    public ByteString hmacSha512(ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            return hmacokio("HmacSHA512", key);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    public ByteString hmacokio(String algorithm, ByteString key) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            byte[] bArrDoFinal = mac.doFinal(this.data);
            checkNotNullExpressionValue(bArrDoFinal, "mac.doFinal(data)");
            return new ByteString(bArrDoFinal);
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        }
    }
    public ByteString toAsciiUppercase() {
        byte b2;
        for (int i2 = 0; i2 < getDataokio().length; i2++) {
            byte b3 = getDataokio()[i2];
            final byte b4 = 97;
            if (b4 <= b3 && b3 <= (b2 = 122)) {
                byte[] dataokio = getDataokio();
                byte[] bArrCopyOf = Arrays.copyOf(dataokio, dataokio.length);
                checkNotNullExpressionValue(bArrCopyOf, "copyOf(this, size)");
                bArrCopyOf[i2] = (byte) (b3 - 32);
                for (int i3 = i2 + 1; i3 < bArrCopyOf.length; i3++) {
                    byte b5 = bArrCopyOf[i3];
                    if (b4 <= b5 && b5 <= b2) {
                        bArrCopyOf[i3] = (byte) (b5 - 32);
                    }
                }
                return new ByteString(bArrCopyOf);
            }
        }
        return this;
    }
    public static ByteString substringdefault(ByteString byteString, int i2, int i3, int i4, Object obj) {
        if (null != obj) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: substring");
        }
        if (0 != (i4 & 1)) {
            i2 = 0;
        }
        if (0 != (i4 & 2)) {
            i3 = _UtilKt.getDEFAULT__ByteString_size();
        }
        return byteString.substring(i2, i3);
    }
    public final byte getByte(int i2) {
        return internalGetokio(i2);
    }
    public final int size() {
        return getSizeokio();
    }
    public ByteString substring(int i2, int i3) {
        int iResolveDefaultParameter = _UtilKt.resolveDefaultParameter(this, i3);
        if (0 > i2) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        if (iResolveDefaultParameter <= getDataokio().length) {
            if (0 <= iResolveDefaultParameter - i2) {
                return (0 == i2 && iResolveDefaultParameter == getDataokio().length) ? this : new ByteString(ArraysKt.copyOfRange(getDataokio(), i2, iResolveDefaultParameter));
            }
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        throw new IllegalArgumentException(("endIndex > length(" + getDataokio().length + ')'));
    }
    public ByteBuffer asByteBuffer() {
        ByteBuffer byteBufferAsReadOnlyBuffer = ByteBuffer.wrap(this.data).asReadOnlyBuffer();
        checkNotNullExpressionValue(byteBufferAsReadOnlyBuffer, "wrap(data).asReadOnlyBuffer()");
        return byteBufferAsReadOnlyBuffer;
    }
    public void write(OutputStream out) throws IOException {
        Intrinsics.checkNotNullParameter(out, "out");
        out.write(this.data);
    }
    public byte internalGetokio(int i2) {
        return getDataokio()[i2];
    }
    public void writeokio(Buffer buffer, int i2, int i3) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        _ByteStringKt.commonWrite(this, buffer, i2, i3);
    }
    public int getSizeokio() {
        return getDataokio().length;
    }
    public byte[] toByteArray() {
        byte[] dataokio = getDataokio();
        byte[] bArrCopyOf = Arrays.copyOf(dataokio, dataokio.length);
        checkNotNullExpressionValue(bArrCopyOf, "copyOf(this, size)");
        return bArrCopyOf;
    }
    public byte[] internalArrayokio() {
        return getDataokio();
    }
    public static void copyIntodefault(ByteString byteString, int i2, byte[] bArr, int i3, int i4, int i5, Object obj) {
        if (null != obj) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: copyInto");
        }
        if (0 != (i5 & 1)) {
            i2 = 0;
        }
        if (0 != (i5 & 4)) {
            i3 = 0;
        }
        byteString.copyInto(i2, bArr, i3, i4);
    }
    public boolean rangeEquals(int i2, ByteString other, int i3, int i4) {
        Intrinsics.checkNotNullParameter(other, "other");
        return other.rangeEquals(i3, getDataokio(), i2, i4);
    }
    public boolean rangeEquals(int i2, byte[] other, int i3, int i4) {
        Intrinsics.checkNotNullParameter(other, "other");
        return 0 <= i2 && i2 <= getDataokio().length - i4 && 0 <= i3 && i3 <= other.length - i4 && _UtilKt.arrayRangeEquals(getDataokio(), i2, other, i3, i4);
    }
    public static int indexOfdefault(ByteString byteString, ByteString byteString2, int i2, int i3, Object obj) {
        if (null != obj) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: indexOf");
        }
        if (0 != (i3 & 2)) {
            i2 = 0;
        }
        return byteString.indexOf(byteString2, i2);
    }
    public final int indexOf(ByteString other, int i2) {
        Intrinsics.checkNotNullParameter(other, "other");
        return indexOf(other.internalArrayokio(), i2);
    }
    public static int indexOfdefault(ByteString byteString, byte[] bArr, int i2, int i3, Object obj) {
        if (null != obj) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: indexOf");
        }
        if (0 != (i3 & 2)) {
            i2 = 0;
        }
        return byteString.indexOf(bArr, i2);
    }
    public void copyInto(int i2, byte[] target, int i3, int i4) {
        Intrinsics.checkNotNullParameter(target, "target");
        ArraysKt.copyInto(getDataokio(), target, i3, i2, i4 + i2);
    }
    public static int lastIndexOfdefault(ByteString byteString, ByteString byteString2, int i2, int i3, Object obj) {
        if (null != obj) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lastIndexOf");
        }
        if (0 != (i3 & 2)) {
            i2 = _UtilKt.getDEFAULT__ByteString_size();
        }
        return byteString.lastIndexOf(byteString2, i2);
    }
    public static int lastIndexOfdefault(ByteString byteString, byte[] bArr, int i2, int i3, Object obj) {
        if (null != obj) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lastIndexOf");
        }
        if (0 != (i3 & 2)) {
            i2 = _UtilKt.getDEFAULT__ByteString_size();
        }
        return byteString.lastIndexOf(bArr, i2);
    }
    public final boolean startsWith(ByteString prefix) {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return rangeEquals(0, prefix, 0, prefix.size());
    }
    public final boolean startsWith(byte[] prefix) {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return rangeEquals(0, prefix, 0, prefix.length);
    }
    public final boolean endsWith(ByteString suffix) {
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return rangeEquals(size() - suffix.size(), suffix, 0, suffix.size());
    }
    private void readObject(ObjectInputStream objectInputStream) throws IllegalAccessException, NoSuchFieldException, IOException, SecurityException, IllegalArgumentException {
        ByteString byteString = Companion.read(objectInputStream, objectInputStream.readInt());
        Field declaredField = ByteString.class.getDeclaredField( Constants.ScionAnalytics.MessageType.DATA_MESSAGE );
        declaredField.setAccessible(true);
        declaredField.set(this, byteString.data);
    }
    public final boolean endsWith(byte[] suffix) {
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return rangeEquals(size() - suffix.length, suffix, 0, suffix.length);
    }
    public int indexOf(byte[] other, int i2) {
        Intrinsics.checkNotNullParameter(other, "other");
        int length = getDataokio().length - other.length;
        int iMax = Math.max(i2, 0);
        if (iMax <= length) {
            while (!_UtilKt.arrayRangeEquals(getDataokio(), iMax, other, 0, other.length)) {
                if (iMax != length) {
                    iMax++;
                }
            }
            return iMax;
        }
        return -1;
    }
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.write(this.data);
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static ByteString ofdefault(Companion companion, byte[] bArr, int i2, int i3, int i4, Object obj) {
            if (0 != (i4 & 1)) {
                i2 = 0;
            }
            if (0 != (i4 & 2)) {
                i3 = _UtilKt.getDEFAULT__ByteString_size();
            }
            return companion.m658of(bArr, i2, i3);
        }
        private Companion() {
        }
        public ByteString m656of(ByteBuffer byteBuffer) {
            Intrinsics.checkNotNullParameter(byteBuffer, "<this>");
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            return new ByteString(bArr);
        }
        public ByteString encodeString(String str, Charset charset) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            Intrinsics.checkNotNullParameter(charset, "charset");
            byte[] bytes = str.getBytes(charset);
            checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            return new ByteString(bytes);
        }
        public ByteString m657of(byte... data) {
            Intrinsics.checkNotNullParameter(data, "data");
            byte[] bArrCopyOf = Arrays.copyOf(data, data.length);
            checkNotNullExpressionValue(bArrCopyOf, "copyOf(this, size)");
            return new ByteString(bArrCopyOf);
        }
        public ByteString m658of(byte[] bArr, int i2, int i3) {
            Intrinsics.checkNotNullParameter(bArr, "<this>");
            int iResolveDefaultParameter = _UtilKt.resolveDefaultParameter(bArr, i3);
            _UtilKt.checkOffsetAndCount(bArr.length, i2, iResolveDefaultParameter);
            return new ByteString(ArraysKt.copyOfRange(bArr, i2, iResolveDefaultParameter + i2));
        }
        public ByteString read(InputStream inputStream, int i2) throws IOException {
            Intrinsics.checkNotNullParameter(inputStream, "<this>");
            if (0 > i2) {
                throw new IllegalArgumentException(("byteCount < 0: " + i2));
            }
            byte[] bArr = new byte[i2];
            int i3 = 0;
            while (i3 < i2) {
                int i4 = inputStream.read(bArr, i3, i2 - i3);
                if (-1 == i4) {
                    throw new EOFException();
                }
                i3 += i4;
            }
            return new ByteString(bArr);
        }
        public ByteString encodeUtf8(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            ByteString byteString = new ByteString(_JvmPlatformKt.asUtf8ToByteArray(str));
            byteString.setUtf8okio(str);
            return byteString;
        }

        public ByteString decodeBase64(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            byte[] bArrDecodeBase64ToArray = _Base64Kt.decodeBase64ToArray(str);
            if (null != bArrDecodeBase64ToArray) {
                return new ByteString(bArrDecodeBase64ToArray);
            }
            return null;
        }

        public ByteString decodeHex(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            if (0 != str.length() % 2) {
                throw new IllegalArgumentException(("Unexpected hex string: " + str));
            }
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ((_ByteStringKt.decodeHexDigit(str.charAt(i3)) << 4) + _ByteStringKt.decodeHexDigit(str.charAt(i3 + 1)));
            }
            return new ByteString(bArr);
        }
    }
    public final byte m1853deprecated_getByte(int i2) {
        return getByte(i2);
    }
    public final int lastIndexOf(ByteString other, int i2) {
        Intrinsics.checkNotNullParameter(other, "other");
        return lastIndexOf(other.internalArrayokio(), i2);
    }
    public int lastIndexOf(byte[] other, int i2) {
        Intrinsics.checkNotNullParameter(other, "other");
        for (int iMin = Math.min(_UtilKt.resolveDefaultParameter(this, i2), getDataokio().length - other.length); -1 < iMin; iMin--) {
            if (_UtilKt.arrayRangeEquals(getDataokio(), iMin, other, 0, other.length)) {
                return iMin;
            }
        }
        return -1;
    }
    public final int m1854deprecated_size() {
        return size();
    }
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof final ByteString byteString) {
            return byteString.size() == getDataokio().length && byteString.rangeEquals(0, getDataokio(), 0, getDataokio().length);
        }
        return false;
    }
    public int hashCode() {
        int hashCodeokio = hashCode;
        if (0 != hashCodeokio) {
            return hashCodeokio;
        }
        int iHashCode = Arrays.hashCode(getDataokio());
        hashCode = iHashCode;
        return iHashCode;
    }
    public int compareTo(ByteString other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int size = size();
        int size2 = other.size();
        int iMin = Math.min(size, size2);
        for (int i2 = 0; i2 < iMin; i2++) {
            int i3 = getByte(i2) & 255;
            int i4 = other.getByte(i2) & 255;
            if (i3 == i4) {
            }
        }
        if (size == size2) {
            return 0;
        }
        return size;
    }
    public String toString() {
        String str;
        if (0 == this.getDataokio().length) {
            str = "[size=0]";
        } else {
            int iCodePointIndexToCharIndex = _ByteStringKt.codePointIndexToCharIndex(getDataokio(), 64);
            if (-1 == iCodePointIndexToCharIndex) {
                if (64 >= this.getDataokio().length) {
                    str = "[hex=" + hex() + ']';
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("[size=");
                    sb.append(getDataokio().length);
                    sb.append(" hex=");
                    int iResolveDefaultParameter = _UtilKt.resolveDefaultParameter(this, 64);
                    if (iResolveDefaultParameter <= getDataokio().length) {
                        if (0 > iResolveDefaultParameter) {
                            throw new IllegalArgumentException("endIndex < beginIndex");
                        }
                        sb.append((iResolveDefaultParameter == getDataokio().length ? this : new ByteString(ArraysKt.copyOfRange(getDataokio(), 0, iResolveDefaultParameter))).hex());
                        sb.append("\u2026]");
                        return sb.toString();
                    }
                    throw new IllegalArgumentException(("endIndex > length(" + getDataokio().length + ')'));
                }
            } else {
                String strUtf8 = utf8();
                String strSubstring = strUtf8.substring(0, iCodePointIndexToCharIndex);
                checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
                String strReplacedefault = StringsKt.replace(StringsKt.replace(StringsKt.replace(strSubstring, "\\", "\\\\", false), SqlLiteVariable._NEW_LINE, "\\n", false), "\r", "\\r", false);
                if (iCodePointIndexToCharIndex < strUtf8.length()) {
                    return "[size=" + getDataokio().length + " text=" + strReplacedefault + "\u2026]";
                }
                return "[text=" + strReplacedefault + ']';
            }
        }
        return str;
    }
}
