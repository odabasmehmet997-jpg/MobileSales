package okio;

import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

import java.util.ArrayList;
import java.util.Map;

public final class FileMetadata {
    final Long createdAtMillis;
    final Map<KClass<?>, Object> extras;
    private final boolean isDirectory;
    private final boolean isRegularFile;
    final Long lastAccessedAtMillis;
    private final Long lastModifiedAtMillis;
    private final Long size;
    private final Path symlinkTarget;
    public FileMetadata() {
        this(false, false, null, null, null, null, null, null, 255, null);
    }
    public FileMetadata(final boolean z, final boolean z2, final Path path, final Long l, final Long l2, final Long l3, final Long l4, final Map<KClass<?>, ? extends Object> extras) {
        Intrinsics.checkNotNullParameter(extras, "extras");
        isRegularFile = z;
        isDirectory = z2;
        symlinkTarget = path;
        size = l;
        createdAtMillis = l2;
        lastModifiedAtMillis = l3;
        lastAccessedAtMillis = l4;
        this.extras = MapsKt.toMap(extras);
    }
    public boolean isRegularFile() {
        return isRegularFile;
    }
    public boolean isDirectory() {
        return isDirectory;
    }
    public Path getSymlinkTarget() {
        return symlinkTarget;
    }
    public Long getSize() {
        return size;
    }
    public Long getLastModifiedAtMillis() {
        return lastModifiedAtMillis;
    }
    public FileMetadata(final boolean z, final boolean z2, final Path path, final Long l, final Long l2, final Long l3, final Long l4, final Map map, final int i2, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 == (i2 & 1) && z, 0 == (i2 & 2) && z2, 0 != (i2 & 4) ? null : path, 0 != (i2 & 8) ? null : l, 0 != (i2 & 16) ? null : l2, 0 != (i2 & 32) ? null : l3, 0 == (i2 & 64) ? l4 : null, 0 != (i2 & 128) ? MapsKt.emptyMap() : map);
    }
    public FileMetadata copy(final boolean z, final boolean z2, final Path path, final Long l, final Long l2, final Long l3, final Long l4, final Map<KClass<?>, ? extends Object> extras) {
        Intrinsics.checkNotNullParameter(extras, "extras");
        return new FileMetadata(z, z2, path, l, l2, l3, l4, extras);
    }
    public String toString() {
        final ArrayList arrayList = new ArrayList();
        if (isRegularFile) {
            arrayList.add("isRegularFile");
        }
        if (isDirectory) {
            arrayList.add("isDirectory");
        }
        if (null != this.size) {
            arrayList.add("byteCount=" + size);
        }
        if (null != this.createdAtMillis) {
            arrayList.add("createdAt=" + createdAtMillis);
        }
        if (null != this.lastModifiedAtMillis) {
            arrayList.add("lastModifiedAt=" + lastModifiedAtMillis);
        }
        if (null != this.lastAccessedAtMillis) {
            arrayList.add("lastAccessedAt=" + lastAccessedAtMillis);
        }
        if (!extras.isEmpty()) {
            arrayList.add("extras=" + extras);
        }
        return CollectionsKt.joinToString(arrayList, ", ", "FileMetadata(", ")", 0, null, null);
    }
}
