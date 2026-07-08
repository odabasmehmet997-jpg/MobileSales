package okio.internal;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import okio.Path;

import java.io.EOFException;
import java.util.ArrayList;

import static kotlin.jvm.internal.Intrinsics.areEqual;
import static kotlin.jvm.internal.Intrinsics.checkNotNull;

public class _PathKt {
    private static final ByteString ANY_SLASH;
    public static final ByteString BACKSLASH;
    public static final ByteString DOT;
    public static final ByteString DOT_DOT;
    public static final ByteString SLASH;
    static {
        final ByteString.Companion companion = ByteString.Companion;
        SLASH = companion.encodeUtf8("/");
        BACKSLASH = companion.encodeUtf8("\\");
        ANY_SLASH = companion.encodeUtf8("/\\");
        DOT = companion.encodeUtf8(".");
        DOT_DOT = companion.encodeUtf8("..");
    }
    public static int rootLength(final Path path) {
        if (0 == path.getBytesokio().size()) {
            return -1;
        }
        if (((byte) 47) == path.getBytesokio().getByte(0)) {
            return 1;
        }
        final byte b2 = 92;
        if (b2 == path.getBytesokio().getByte(0)) {
            if (2 >= path.getBytesokio().size() || b2 != path.getBytesokio().getByte(1)) {
                return 1;
            }
            final int iIndexOf = path.getBytesokio().indexOf(_PathKt.BACKSLASH, 2);
            return -1 == iIndexOf ? path.getBytesokio().size() : iIndexOf;
        }
        if (2 < path.getBytesokio().size() && ((byte) 58) == path.getBytesokio().getByte(1) && b2 == path.getBytesokio().getByte(2)) {
            final char c2 = (char) path.getBytesokio().getByte(0);
            if ('a' <= c2 && '{' > c2) {
                return 3;
            }
            if ('A' <= c2 && '[' > c2) {
                return 3;
            }
        }
        return -1;
    }
    public static int getIndexOfLastSlash(final Path path) {
        final int iLastIndexOfdefault = ByteString.lastIndexOfdefault(path.getBytesokio(), _PathKt.SLASH, 0, 2, null);
        return -1 != iLastIndexOfdefault ? iLastIndexOfdefault : ByteString.lastIndexOfdefault(path.getBytesokio(), _PathKt.BACKSLASH, 0, 2, null);
    }
    public static boolean lastSegmentIsDotDot(final Path path) {
        return path.getBytesokio().endsWith(_PathKt.DOT_DOT) && (2 == path.getBytesokio().size() || path.getBytesokio().rangeEquals(path.getBytesokio().size() + (-3), _PathKt.SLASH, 0, 1) || path.getBytesokio().rangeEquals(path.getBytesokio().size() + (-3), _PathKt.BACKSLASH, 0, 1));
    }
    public static Path commonResolve(final Path path, final Path child, final boolean z) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(child, "child");
        if (child.isAbsolute() || null != child.volumeLetter()) {
            return child;
        }
        ByteString slash = _PathKt.getSlash(path);
        if (null == slash && null == (slash = getSlash(child))) {
            slash = _PathKt.toSlash(Path.DIRECTORY_SEPARATOR);
        }
        final Buffer buffer = new Buffer();
        buffer.write(path.getBytesokio());
        if (0 < buffer.size()) {
            buffer.write(slash);
        }
        buffer.write(child.getBytesokio());
        try {
            return _PathKt.toPath(buffer, z);
        } catch (final EOFException e) {
            throw new RuntimeException(e);
        }
    }
    public static ByteString getSlash(final Path path) {
        final ByteString bytesokio = path.getBytesokio();
        final ByteString byteString = _PathKt.SLASH;
        if (-1 != ByteString.indexOfdefault(bytesokio, byteString, 0, 2, null)) {
            return byteString;
        }
        final ByteString bytesokio2 = path.getBytesokio();
        final ByteString byteString2 = _PathKt.BACKSLASH;
        if (-1 != ByteString.indexOfdefault(bytesokio2, byteString2, 0, 2, null)) {
            return byteString2;
        }
        return null;
    }
    public static Path commonToPath(final String str, final boolean z) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            return _PathKt.toPath(new Buffer().writeUtf8(str), z);
        } catch (final EOFException e) {
            throw new RuntimeException(e);
        }
    }
    public static Path toPath(final Buffer buffer, final boolean z) throws EOFException {
        ByteString byteString;
        ByteString byteString2;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        final Buffer buffer2 = new Buffer();
        ByteString slash = null;
        int i2 = 0;
        while (true) {
            if (!buffer.rangeEquals(0L, _PathKt.SLASH)) {
                byteString = _PathKt.BACKSLASH;
                if (!buffer.rangeEquals(0L, byteString)) {
                    break;
                }
            }
            final byte b2 = buffer.readByte();
            if (null == slash) {
                slash = _PathKt.toSlash(b2);
            }
            i2++;
        }
        final boolean z2 = 2 <= i2 && areEqual(slash, byteString);
        if (z2) {
            checkNotNull(slash);
            buffer2.write(slash);
            buffer2.write(slash);
        } else if (0 < i2) {
            checkNotNull(slash);
            buffer2.write(slash);
        } else {
            final long jIndexOfElement = buffer.indexOfElement(_PathKt.ANY_SLASH);
            if (null == slash) {
                if (-1 == jIndexOfElement) {
                    slash = _PathKt.toSlash(Path.DIRECTORY_SEPARATOR);
                } else {
                    slash = _PathKt.toSlash(buffer.getByte(jIndexOfElement));
                }
            }
            if (_PathKt.startsWithVolumeLetterAndColon(buffer, slash)) {
                if (2 == jIndexOfElement) {
                    buffer2.write(buffer, 3L);
                } else {
                    buffer2.write(buffer, 2L);
                }
            }
        }
        final boolean z3 = 0 < buffer2.size();
        final ArrayList arrayList = new ArrayList();
        while (!buffer.exhausted()) {
            final long jIndexOfElement2 = buffer.indexOfElement(_PathKt.ANY_SLASH);
            if (-1 == jIndexOfElement2) {
                byteString2 = buffer.readByteString();
            } else {
                byteString2 = buffer.readByteString(jIndexOfElement2);
                buffer.readByte();
            }
            final ByteString byteString3 = _PathKt.DOT_DOT;
            if (areEqual(byteString2, byteString3)) {
                if (!z3 || !arrayList.isEmpty()) {
                    if (!z || (!z3 && (arrayList.isEmpty() || areEqual(CollectionsKt.last(arrayList), byteString3)))) {
                        arrayList.add(byteString2);
                    } else if (!z2 || 1 != arrayList.size()) {
                        CollectionsKt.removeLastOrNull(arrayList);
                    }
                }
            } else if (!areEqual(byteString2, _PathKt.DOT) && !areEqual(byteString2, ByteString.EMPTY)) {
                arrayList.add(byteString2);
            }
        }
        final int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (0 < i3) {
                buffer2.write(slash);
            }
            buffer2.write((ByteString) arrayList.get(i3));
        }
        if (0 == buffer2.size()) {
            buffer2.write(_PathKt.DOT);
        }
        return new Path(buffer2.readByteString());
    }
    public static ByteString toSlash(final String str) {
        if (areEqual(str, "/")) {
            return _PathKt.SLASH;
        }
        if (areEqual(str, "\\")) {
            return _PathKt.BACKSLASH;
        }
        throw new IllegalArgumentException("not a directory separator: " + str);
    }
    private static ByteString toSlash(final byte b2) {
        if (47 == b2) {
            return _PathKt.SLASH;
        }
        if (92 == b2) {
            return _PathKt.BACKSLASH;
        }
        throw new IllegalArgumentException("not a directory separator: " + b2);
    }
    private static boolean startsWithVolumeLetterAndColon(final Buffer buffer, final ByteString byteString) {
        if (!areEqual(byteString, _PathKt.BACKSLASH) || 2 > buffer.size() || ((byte) 58) != buffer.getByte(1L)) {
            return false;
        }
        final char c2 = (char) buffer.getByte(0L);
        return ('a' <= c2 && '{' > c2) || ('A' <= c2 && '[' > c2);
    }
}
