package okio;

import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import okio.internal._SegmentedByteStringKt;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class SegmentedByteString extends ByteString {
    private final transient int[] directory;
    private final transient byte[][] segments;

    public byte[][] getSegmentsokio() {
        return segments;
    }

    public int[] getDirectoryokio() {
        return directory;
    }
    public SegmentedByteString(final byte[][] segments, final int[] directory) {
        super(EMPTY.getDataokio());
        Intrinsics.checkNotNullParameter(segments, "segments");
        Intrinsics.checkNotNullParameter(directory, "directory");
        this.segments = segments;
        this.directory = directory;
    }
    public String string(final Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        return this.toByteString().string(charset);
    }
    public String base64() {
        return this.toByteString().base64();
    }
    public String hex() {
        return this.toByteString().hex();
    }
    public ByteString toAsciiLowercase() {
        return this.toByteString().toAsciiLowercase();
    }
    public ByteString toAsciiUppercase() {
        return this.toByteString().toAsciiUppercase();
    }
    public ByteString digestokio(final String algorithm) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        final MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        final int length = this.getSegmentsokio().length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            final int i4 = this.getDirectoryokio()[length + i2];
            final int i5 = this.getDirectoryokio()[i2];
            messageDigest.update(this.getSegmentsokio()[i2], i4, i5 - i3);
            i2++;
            i3 = i5;
        }
        final byte[] digestBytes = messageDigest.digest();
        checkNotNullExpressionValue(digestBytes, "digestBytes");
        return new ByteString(digestBytes);
    }
    public void write(final OutputStream out) throws IOException {
        Intrinsics.checkNotNullParameter(out, "out");
        final int length = this.getSegmentsokio().length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            final int i4 = this.getDirectoryokio()[length + i2];
            final int i5 = this.getDirectoryokio()[i2];
            out.write(this.getSegmentsokio()[i2], i4, i5 - i3);
            i2++;
            i3 = i5;
        }
    }
    public ByteString hmacokio(final String algorithm, final ByteString key) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            final Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            final int length = this.getSegmentsokio().length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                final int i4 = this.getDirectoryokio()[length + i2];
                final int i5 = this.getDirectoryokio()[i2];
                mac.update(this.getSegmentsokio()[i2], i4, i5 - i3);
                i2++;
                i3 = i5;
            }
            final byte[] bArrDoFinal = mac.doFinal();
            checkNotNullExpressionValue(bArrDoFinal, "mac.doFinal()");
            return new ByteString(bArrDoFinal);
        } catch (final InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    
    public String base64Url() {
        return this.toByteString().base64Url();
    }

    
    public void writeokio(final Buffer buffer, int i2, final int i3) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        final int i4 = i2 + i3;
        int iSegment = _SegmentedByteStringKt.segment(this, i2);
        while (i2 < i4) {
            final int i5 = 0 == iSegment ? 0 : this.getDirectoryokio()[iSegment - 1];
            final int i6 = this.getDirectoryokio()[iSegment] - i5;
            final int i7 = this.getDirectoryokio()[this.getSegmentsokio().length + iSegment];
            final int iMin = Math.min(i4, i6 + i5) - i2;
            final int i8 = i7 + (i2 - i5);
            final Segment segment = new Segment(this.getSegmentsokio()[iSegment], i8, i8 + iMin, true, false);
            final Segment segment2 = buffer.head;
            if (null == segment2) {
                segment.prev = segment;
                segment.next = segment;
                buffer.head = segment;
            } else {
                checkNotNull(segment2);
                final Segment segment3 = segment2.prev;
                checkNotNull(segment3);
                segment3.push(segment);
            }
            i2 += iMin;
            iSegment++;
        }
        buffer.setSizeokio(buffer.size() + i3);
    }

    
    public ByteBuffer asByteBuffer() {
        final ByteBuffer byteBufferAsReadOnlyBuffer = ByteBuffer.wrap(this.toByteArray()).asReadOnlyBuffer();
        checkNotNullExpressionValue(byteBufferAsReadOnlyBuffer, "wrap(toByteArray()).asReadOnlyBuffer()");
        return byteBufferAsReadOnlyBuffer;
    }

    
    public ByteString substring(final int i2, final int i3) {
        final int iResolveDefaultParameter = _UtilKt.resolveDefaultParameter(this, i3);
        if (0 > i2) {
            throw new IllegalArgumentException(("beginIndex=" + i2 + " < 0"));
        }
        if (iResolveDefaultParameter > this.size()) {
            throw new IllegalArgumentException(("endIndex=" + iResolveDefaultParameter + " > length(" + this.size() + ')'));
        }
        final int i4 = iResolveDefaultParameter - i2;
        if (0 > i4) {
            throw new IllegalArgumentException(("endIndex=" + iResolveDefaultParameter + " < beginIndex=" + i2));
        }
        if (0 == i2 && iResolveDefaultParameter == this.size()) {
            return this;
        }
        if (i2 == iResolveDefaultParameter) {
            return EMPTY;
        }
        final int iSegment = _SegmentedByteStringKt.segment(this, i2);
        final int iSegment2 = _SegmentedByteStringKt.segment(this, iResolveDefaultParameter - 1);
        final byte[][] bArr = ArraysKt.copyOfRange(this.getSegmentsokio(), iSegment, iSegment2 + 1);
        final int[] iArr = new int[bArr.length * 2];
        if (iSegment <= iSegment2) {
            int i5 = iSegment;
            int i6 = 0;
            while (true) {
                iArr[i6] = Math.min(this.getDirectoryokio()[i5] - i2, i4);
                final int i7 = i6 + 1;
                iArr[i6 + bArr.length] = this.getDirectoryokio()[this.getSegmentsokio().length + i5];
                if (i5 == iSegment2) {
                    break;
                }
                i5++;
                i6 = i7;
            }
        }
        final int i8 = 0 != iSegment ? this.getDirectoryokio()[iSegment - 1] : 0;
        final int length = bArr.length;
        iArr[length] = iArr[length] + (i2 - i8);
        return new SegmentedByteString(bArr, iArr);
    }

    
    public int indexOf(final byte[] other, final int i2) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.toByteString().indexOf(other, i2);
    }

    
    public int lastIndexOf(final byte[] other, final int i2) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.toByteString().lastIndexOf(other, i2);
    }

    private ByteString toByteString() {
        return new ByteString(this.toByteArray());
    }

    
    public byte[] internalArrayokio() {
        return this.toByteArray();
    }

    
    public byte internalGetokio(final int i2) {
        _UtilKt.checkOffsetAndCount(this.getDirectoryokio()[this.getSegmentsokio().length - 1], i2, 1L);
        final int iSegment = _SegmentedByteStringKt.segment(this, i2);
        return this.getSegmentsokio()[iSegment][(i2 - (0 == iSegment ? 0 : this.getDirectoryokio()[iSegment - 1])) + this.getDirectoryokio()[this.getSegmentsokio().length + iSegment]];
    }

    
    public String toString() {
        return this.toByteString().toString();
    }

    private Object writeReplace() {
        return this.toByteString();
    }

    
    public int getSizeokio() {
        return this.getDirectoryokio()[this.getSegmentsokio().length - 1];
    }

    
    public byte[] toByteArray() {
        final byte[] bArr = new byte[this.size()];
        final int length = this.getSegmentsokio().length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            final int i5 = this.getDirectoryokio()[length + i2];
            final int i6 = this.getDirectoryokio()[i2];
            final int i7 = i6 - i3;
            ArraysKt.copyInto(this.getSegmentsokio()[i2], bArr, i4, i5, i5 + i7);
            i4 += i7;
            i2++;
            i3 = i6;
        }
        return bArr;
    }

    
    public boolean rangeEquals(int i2, final ByteString other, int i3, final int i4) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (0 > i2 || i2 > this.size() - i4) {
            return false;
        }
        final int i5 = i4 + i2;
        int iSegment = _SegmentedByteStringKt.segment(this, i2);
        while (i2 < i5) {
            final int i6 = 0 == iSegment ? 0 : this.getDirectoryokio()[iSegment - 1];
            final int i7 = this.getDirectoryokio()[iSegment] - i6;
            final int i8 = this.getDirectoryokio()[this.getSegmentsokio().length + iSegment];
            final int iMin = Math.min(i5, i7 + i6) - i2;
            if (!other.rangeEquals(i3, this.getSegmentsokio()[iSegment], i8 + (i2 - i6), iMin)) {
                return false;
            }
            i3 += iMin;
            i2 += iMin;
            iSegment++;
        }
        return true;
    }

    
    public boolean rangeEquals(int i2, final byte[] other, int i3, final int i4) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (0 > i2 || i2 > this.size() - i4 || 0 > i3 || i3 > other.length - i4) {
            return false;
        }
        final int i5 = i4 + i2;
        int iSegment = _SegmentedByteStringKt.segment(this, i2);
        while (i2 < i5) {
            final int i6 = 0 == iSegment ? 0 : this.getDirectoryokio()[iSegment - 1];
            final int i7 = this.getDirectoryokio()[iSegment] - i6;
            final int i8 = this.getDirectoryokio()[this.getSegmentsokio().length + iSegment];
            final int iMin = Math.min(i5, i7 + i6) - i2;
            if (!_UtilKt.arrayRangeEquals(this.getSegmentsokio()[iSegment], i8 + (i2 - i6), other, i3, iMin)) {
                return false;
            }
            i3 += iMin;
            i2 += iMin;
            iSegment++;
        }
        return true;
    }

    
    public void copyInto(int i2, final byte[] target, int i3, final int i4) {
        Intrinsics.checkNotNullParameter(target, "target");
        final long j2 = i4;
        _UtilKt.checkOffsetAndCount(this.size(), i2, j2);
        _UtilKt.checkOffsetAndCount(target.length, i3, j2);
        final int i5 = i4 + i2;
        int iSegment = _SegmentedByteStringKt.segment(this, i2);
        while (i2 < i5) {
            final int i6 = 0 == iSegment ? 0 : this.getDirectoryokio()[iSegment - 1];
            final int i7 = this.getDirectoryokio()[iSegment] - i6;
            final int i8 = this.getDirectoryokio()[this.getSegmentsokio().length + iSegment];
            final int iMin = Math.min(i5, i7 + i6) - i2;
            final int i9 = i8 + (i2 - i6);
            ArraysKt.copyInto(this.getSegmentsokio()[iSegment], target, i3, i9, i9 + iMin);
            i3 += iMin;
            i2 += iMin;
            iSegment++;
        }
    }

    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString byteString) {
            return byteString.size() == this.size() && this.rangeEquals(0, byteString, 0, this.size());
        }
        return false;
    }

    
    public int hashCode() {
        final int hashCodeokio = this.getHashCodeokio();
        if (0 != hashCodeokio) {
            return hashCodeokio;
        }
        final int length = this.getSegmentsokio().length;
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i2 < length) {
            int i5 = this.getDirectoryokio()[length + i2];
            final int i6 = this.getDirectoryokio()[i2];
            final byte[] bArr = this.getSegmentsokio()[i2];
            final int i7 = (i6 - i4) + i5;
            while (i5 < i7) {
                i3 = (i3 * 31) + bArr[i5];
                i5++;
            }
            i2++;
            i4 = i6;
        }
        this.setHashCodeokio(i3);
        return i3;
    }
}
