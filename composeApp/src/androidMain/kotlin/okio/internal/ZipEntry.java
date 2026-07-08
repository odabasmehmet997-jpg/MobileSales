package okio.internal;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Path;

import java.util.ArrayList;
import java.util.List;

public final class ZipEntry {
    private final Path canonicalPath;
    private final List<Path> children;
    private final String comment;
    private final long compressedSize;
    private final int compressionMethod;
    private final long crc;
    private final boolean isDirectory;
    private final Long lastModifiedAtMillis;
    private final long offset;
    private final long size;
    public ZipEntry(Path canonicalPath, boolean z, String comment, long j2, long j3, long j4, int i2, Long l, long j5) {
        Intrinsics.checkNotNullParameter(canonicalPath, "canonicalPath");
        Intrinsics.checkNotNullParameter(comment, "comment");
        this.canonicalPath = canonicalPath;
        this.isDirectory = z;
        this.comment = comment;
        this.crc = j2;
        this.compressedSize = j3;
        this.size = j4;
        this.compressionMethod = i2;
        this.lastModifiedAtMillis = l;
        this.offset = j5;
        this.children = new ArrayList();
    }
    public Path getCanonicalPath() {
        return this.canonicalPath;
    }
    public boolean isDirectory() {
        return this.isDirectory;
    }
    public ZipEntry(Path path, boolean z, String str, long j2, long j3, long j4, int i2, Long l, long j5, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(path, 0 == (i3 & 2) && z, 0 != (i3 & 4) ? "" : str, 0 != (i3 & 8) ? -1L : j2, 0 != (i3 & 16) ? -1L : j3, 0 != (i3 & 32) ? -1L : j4, 0 != (i3 & 64) ? -1 : i2, 0 != (i3 & 128) ? null : l, 0 == (i3 & 256) ? j5 : -1L);
    }
    public long getSize() {
        return this.size;
    }
    public Long getLastModifiedAtMillis() {
        return this.lastModifiedAtMillis;
    }
    public long getOffset() {
        return this.offset;
    }
    public List<Path> getChildren() {
        return this.children;
    }
}
