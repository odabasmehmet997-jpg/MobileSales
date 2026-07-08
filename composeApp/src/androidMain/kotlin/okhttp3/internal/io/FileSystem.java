package okhttp3.internal.io;

import androidx.annotation.NonNull;
import kotlin.jvm.internal.Intrinsics;
import okio.JvmOkio2;
import okio.Okio;
import okio.Sink;
import okio.Source;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;
import static kotlin.jvm.internal.Intrinsics.stringPlus;

public interface FileSystem {
    FileSystem SYSTEM = new Companion.SystemFileSystem();
    Companion Companion = FileSystem.Companion.INSTANCE;
    Sink appendingSink(File file) throws FileNotFoundException;
    void delete(File file) throws IOException;
    void deleteContents(File file) throws IOException;
    boolean exists(File file);
    void rename(File file, File file2) throws IOException;
    Sink sink(File file) throws FileNotFoundException;
    long size(File file);
    Source source(File file) throws FileNotFoundException;
    final class Companion {
        static final Companion INSTANCE = new Companion();
        private Companion() {
        }
        private static final class SystemFileSystem implements FileSystem {
            public Source source(final File file) throws FileNotFoundException {
                Intrinsics.checkNotNullParameter(file, "file");
                return Okio.source(file);
            }
            public Sink sink(final File file) throws FileNotFoundException {
                Intrinsics.checkNotNullParameter(file, "file");
                try {
                    return JvmOkio2.sink(file, false, 1, null);
                } catch (final FileNotFoundException unused) {
                    file.getParentFile().mkdirs();
                    return JvmOkio2.sink(file, false, 1, null);
                }
            }
            public Sink appendingSink(final File file) throws FileNotFoundException {
                Intrinsics.checkNotNullParameter(file, "file");
                try {
                    return Okio.appendingSink(file);
                } catch (final FileNotFoundException unused) {
                    Objects.requireNonNull(file.getParentFile()).mkdirs();
                    return Okio.appendingSink(file);
                }
            }
            public void delete(final File file) throws IOException {
                Intrinsics.checkNotNullParameter(file, "file");
                if (!file.delete() && file.exists()) {
                    throw new IOException(stringPlus("failed to delete ", file));
                }
            }
            public boolean exists(final File file) {
                Intrinsics.checkNotNullParameter(file, "file");
                return file.exists();
            }
            public long size(final File file) {
                Intrinsics.checkNotNullParameter(file, "file");
                return file.length();
            }
            public void rename(final File from, final File to) throws IOException {
                Intrinsics.checkNotNullParameter(from, "from");
                Intrinsics.checkNotNullParameter(to, "to");
                this.delete(to);
                if (from.renameTo(to)) {
                    return;
                }
                throw new IOException("failed to rename " + from + " to " + to);
            }
            public void deleteContents(final File directory) throws IOException {
                Intrinsics.checkNotNullParameter(directory, "directory");
                final File[] fileArrListFiles = directory.listFiles();
                if (null == fileArrListFiles) {
                    throw new IOException(stringPlus("not a readable directory: ", directory));
                }
                for (final File file : fileArrListFiles) {
                    if (file.isDirectory()) {
                        checkNotNullExpressionValue(file, "file");
                        this.deleteContents(file);
                    }
                    if (!file.delete()) {
                        throw new IOException(stringPlus("failed to delete ", file));
                    }
                }
            }
            @NonNull
            public String toString() {
                return "FileSystem.SYSTEM";
            }
        }
    }
}
