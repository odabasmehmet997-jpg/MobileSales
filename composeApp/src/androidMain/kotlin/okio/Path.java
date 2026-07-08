package okio;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.internal._PathKt;

import java.io.EOFException;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.areEqual;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;


public final class Path implements Comparable<Path> {
    public static final Companion Companion = new Companion(null);
    public static final String DIRECTORY_SEPARATOR;
    private final ByteString bytes;

    public Path(ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        this.bytes = bytes;
    }

    public ByteString getBytesokio() {
        return this.bytes;
    }

    public Path getRoot() {
        int iRootLength = _PathKt.rootLength(this);
        if (-1 == iRootLength) {
            return null;
        }
        return new Path(bytes.substring(0, iRootLength));
    }

    public List<ByteString> getSegmentsBytes() {
        ArrayList arrayList = new ArrayList();
        int iRootLength = _PathKt.rootLength(this);
        if (-1 == iRootLength) {
            iRootLength = 0;
        } else if (iRootLength < bytes.size() && ((byte) 92) == this.bytes.getByte(iRootLength)) {
            iRootLength++;
        }
        int size = bytes.size();
        int i2 = iRootLength;
        while (iRootLength < size) {
            if (((byte) 47) == this.bytes.getByte(iRootLength) || ((byte) 92) == this.bytes.getByte(iRootLength)) {
                arrayList.add(bytes.substring(i2, iRootLength));
                i2 = iRootLength + 1;
            }
            iRootLength++;
        }
        if (i2 < bytes.size()) {
            arrayList.add(bytes.substring(i2, bytes.size()));
        }
        return arrayList;
    }

    public Path resolve(Path child) {
        Intrinsics.checkNotNullParameter(child, "child");
        return _PathKt.commonResolve(this, child, false);
    }

    public Path resolve(Path child, boolean z) {
        Intrinsics.checkNotNullParameter(child, "child");
        return _PathKt.commonResolve(this, child, z);
    }
    public File toFile() {
        return new File(toString());
    }
    public java.nio.file.Path toNioPath() {
        java.nio.file.Path path = Paths.get(toString());
        checkNotNullExpressionValue(path, "get(toString())");
        return path;
    }

    public boolean isAbsolute() {
        return -1 != _PathKt.rootLength(this);
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
        public static Path getdefault(Companion companion, String str, boolean z, int i2, Object obj) {
            if (0 != (i2 & 1)) {
                z = false;
            }
            return companion.get(str, z);
        }

        public Path get(String str, boolean z) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            return _PathKt.commonToPath(str, z);
        }

        public static Path getdefault(Companion companion, File file, boolean z, int i2, Object obj) {
            if (0 != (i2 & 1)) {
                z = false;
            }
            return companion.get(file, z);
        }

        public Path get(File file, boolean z) {
            Intrinsics.checkNotNullParameter(file, "<this>");
            String string = file.toString();
            checkNotNullExpressionValue(string, "toString()");
            return get(string, z);
        }

        public static Path getdefault(Companion companion, java.nio.file.Path path, boolean z, int i2, Object obj) {
            if (0 != (i2 & 1)) {
                z = false;
            }
            return companion.get(path, z);
        }
        public Path get(java.nio.file.Path path, boolean z) {
            Intrinsics.checkNotNullParameter(path, "<this>");
            return get(path.toString(), z);
        }
    }
    static {
        String separator = File.separator;
        checkNotNullExpressionValue(separator, "separator");
        DIRECTORY_SEPARATOR = separator;
    }
    public Character volumeLetter() {
        if (-1 != ByteString.indexOfdefault(this.bytes, _PathKt.SLASH, 0, 2, null) || 2 > this.bytes.size() || ((byte) 58) != this.bytes.getByte(1)) {
            return null;
        }
        char c2 = (char) bytes.getByte(0);
        if (('a' > c2 || '{' <= c2) && ('A' > c2 || '[' <= c2)) {
            return null;
        }
        return Character.valueOf(c2);
    }
    public ByteString nameBytes() {
        int indexOfLastSlash = _PathKt.getIndexOfLastSlash(this);
        if (-1 != indexOfLastSlash) {
            return ByteString.substringdefault(bytes, indexOfLastSlash + 1, 0, 2, null);
        }
        return (null == this.volumeLetter() || 2 != this.bytes.size()) ? bytes : ByteString.EMPTY;
    }
    public String name() {
        return nameBytes().utf8();
    }
    public Path parent() {
        Path path;
        if (areEqual(bytes, _PathKt.DOT) || areEqual(bytes, _PathKt.SLASH) || areEqual(bytes, _PathKt.BACKSLASH) || _PathKt.lastSegmentIsDotDot(this)) {
            return null;
        }
        int indexOfLastSlash = _PathKt.getIndexOfLastSlash(this);
        if (2 != indexOfLastSlash || null == this.volumeLetter()) {
            if (1 == indexOfLastSlash && bytes.startsWith(_PathKt.BACKSLASH)) {
                return null;
            }
            if (-1 != indexOfLastSlash || null == this.volumeLetter()) {
                if (-1 == indexOfLastSlash) {
                    return new Path(_PathKt.DOT);
                }
                if (0 == indexOfLastSlash) {
                    path = new Path(ByteString.substringdefault(bytes, 0, 1, 1, null));
                } else {
                    return new Path(ByteString.substringdefault(bytes, 0, indexOfLastSlash, 1, null));
                }
            } else {
                if (2 == this.bytes.size()) {
                    return null;
                }
                path = new Path(ByteString.substringdefault(bytes, 0, 2, 1, null));
            }
        } else {
            if (3 == this.bytes.size()) {
                return null;
            }
            path = new Path(ByteString.substringdefault(bytes, 0, 3, 1, null));
        }
        return path;
    }

    public Path resolve(String child) {
        Intrinsics.checkNotNullParameter(child, "child");
        try {
            return _PathKt.commonResolve(this, _PathKt.toPath(new Buffer().writeUtf8(child), false), false);
        } catch (EOFException e) {
            throw new RuntimeException(e);
        }
    }

    public Path relativeTo(Path other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (!areEqual(getRoot(), other.getRoot())) {
            throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + this + " and " + other));
        }
        List<ByteString> segmentsBytes = getSegmentsBytes();
        List<ByteString> segmentsBytes2 = other.getSegmentsBytes();
        int iMin = Math.min(segmentsBytes.size(), segmentsBytes2.size());
        int i2 = 0;
        while (i2 < iMin && areEqual(segmentsBytes.get(i2), segmentsBytes2.get(i2))) {
            i2++;
        }
        if (i2 != iMin || bytes.size() != other.bytes.size()) {
            if (segmentsBytes2.subList(i2, segmentsBytes2.size()).contains(_PathKt.DOT_DOT)) {
                throw new IllegalArgumentException(("Impossible relative path to resolve: " + this + " and " + other));
            }
            Buffer buffer = new Buffer();
            ByteString slash = _PathKt.getSlash(other);
            if (null == slash && null == (slash = _PathKt.getSlash(this))) {
                slash = _PathKt.toSlash(DIRECTORY_SEPARATOR);
            }
            int size = segmentsBytes2.size();
            for (int i3 = i2; i3 < size; i3++) {
                buffer.write(_PathKt.DOT_DOT);
                buffer.write(slash);
            }
            int size2 = segmentsBytes.size();
            while (i2 < size2) {
                buffer.write(segmentsBytes.get(i2));
                buffer.write(slash);
                i2++;
            }
            try {
                return _PathKt.toPath(buffer, false);
            } catch (EOFException e) {
                throw new RuntimeException(e);
            }
        }
        return Path.Path.Companion.getdefault(Companion, ".", false, 1, null);
    }
    public int compareTo(Path other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return bytes.compareTo(other.bytes);
    }

    public boolean equals(Object obj) {
        return (obj instanceof Path) && areEqual(((Path) obj).bytes, bytes);
    }

    public int hashCode() {
        return bytes.hashCode();
    }

    public String toString() {
        return bytes.utf8();
    }
}
