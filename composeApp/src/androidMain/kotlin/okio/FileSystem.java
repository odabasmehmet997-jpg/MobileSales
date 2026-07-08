package okio;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.internal.ResourceFileSystem;
import okio.internal._FileSystemKt;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public abstract class FileSystem {
    public static final Companion Companion = new Companion(null);
    public static final FileSystem RESOURCES;
    public static final FileSystem SYSTEM;
    public static final Path SYSTEM_TEMPORARY_DIRECTORY;
    public abstract List<Path> list(Path path) throws IOException;
    public abstract List<Path> listOrNull(Path path);
    public abstract FileMetadata metadataOrNull(Path path) throws IOException;
    public abstract FileHandle openReadOnly(Path path) throws IOException;
    public final FileMetadata metadata(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "path");
        return _FileSystemKt.commonMetadata(this, path);
    }
    public Sink source(File journalFile) { return null; }
    public Sink appendingSink(File journalFile) { return null; }
    public void delete(File file) { }
    public Sink sink(File journalFileTmp) {  return null; }
    public boolean exists(File journalFile) { return false;  }
    public void rename(File journalFile, File journalFileBackup) { }
    public long size(File file2) { return 0; }
    public void deleteContents(File directory) {  }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }
    }
    static {
        FileSystem jvmSystemFileSystem;
        try {
            Class.forName("java.nio.file.Files");
            jvmSystemFileSystem = new NioSystemFileSystem();
        } catch (ClassNotFoundException unused) {
            jvmSystemFileSystem = new JvmSystemFileSystem();
        }
        SYSTEM = jvmSystemFileSystem;
        Path.Companion companion = Path.Companion;
        String property = System.getProperty("java.io.tmpdir");
        checkNotNullExpressionValue(property, "getProperty(\"java.io.tmpdir\")");
        SYSTEM_TEMPORARY_DIRECTORY = Path.Companion.getdefault(companion, property, false, 1, null);
        ClassLoader classLoader = ResourceFileSystem.class.getClassLoader();
        checkNotNullExpressionValue(classLoader, "ResourceFileSystem::class.java.classLoader");
        RESOURCES = new ResourceFileSystem(classLoader, false);
    }
}
