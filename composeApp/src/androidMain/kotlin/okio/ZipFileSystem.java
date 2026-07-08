package okio;

import kotlin.ExceptionsKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.internal.ZipEntry;
import okio.internal.zip2;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;

public final class ZipFileSystem extends FileSystem {
    private static final Companion Companion = new Companion(null);
    private static final Path ROOT = Path.Companion.getdefault(Path.Companion, "/", false, 1, null);
    private final String comment;
    private final Map<Path, ZipEntry> entries;
    private final FileSystem fileSystem;
    private final Path zipPath;
    public ZipFileSystem(final Path zipPath, final FileSystem fileSystem, final Map<Path, ZipEntry> entries, final String str) {
        Intrinsics.checkNotNullParameter(zipPath, "zipPath");
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(entries, "entries");
        this.zipPath = zipPath;
        this.fileSystem = fileSystem;
        this.entries = entries;
        comment = str;
    }
    private Path canonicalizeInternal(final Path path) {
        return ZipFileSystem.ROOT.resolve(path, true);
    }
    public FileMetadata metadataOrNull(final Path path) {
        BufferedSource bufferedSourceBuffer;
        Intrinsics.checkNotNullParameter(path, "path");
        final ZipEntry zipEntry = entries.get(this.canonicalizeInternal(path));
        Throwable th = null;
        if (null == zipEntry) {
            return null;
        }
        final FileMetadata fileMetadata = new FileMetadata(!zipEntry.isDirectory(), zipEntry.isDirectory(), null, zipEntry.isDirectory() ? null : Long.valueOf(zipEntry.getSize()), null, zipEntry.getLastModifiedAtMillis(), null, null, 128, null);
        if (-1 == zipEntry.getOffset()) {
            return fileMetadata;
        }
        FileHandle fileHandleOpenReadOnly = null;
        try {
            fileHandleOpenReadOnly = fileSystem.openReadOnly(zipPath);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bufferedSourceBuffer = Okio.buffer(fileHandleOpenReadOnly.source(zipEntry.getOffset()));
        } catch (final Throwable th2) {
            th = th2;
            bufferedSourceBuffer = null;
        }
        if (null != fileHandleOpenReadOnly) {
            try {
                fileHandleOpenReadOnly.close();
            } catch (final Throwable th3) {
                if (null == th) {
                    th = th3;
                } else {
                    ExceptionsKt.addSuppressed(th, th3);
                }
            }
        }
        if (null != th) {
            try {
                throw th;
            } catch (final Throwable e) {
                throw new RuntimeException(e);
            }
        }
        checkNotNull(bufferedSourceBuffer);
        try {
            return zip2.readLocalHeader(bufferedSourceBuffer, fileMetadata);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    public FileHandle openReadOnly(final Path file) {
        Intrinsics.checkNotNullParameter(file, "file");
        throw new UnsupportedOperationException("not implemented yet!");
    }
    public List<Path> list(final Path dir) throws IOException {
        Intrinsics.checkNotNullParameter(dir, "dir");
        final List<Path> list = this.list(dir, true);
        checkNotNull(list);
        return list;
    }
    public List<Path> listOrNull(final Path dir) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        try {
            return this.list(dir, false);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Path> list(final Path path, final boolean z) throws IOException {
        final ZipEntry zipEntry = entries.get(this.canonicalizeInternal(path));
        if (null != zipEntry) {
            return CollectionsKt.toList(zipEntry.getChildren());
        }
        if (!z) {
            return null;
        }
        throw new IOException("not a directory: " + path);
    }
    private static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
