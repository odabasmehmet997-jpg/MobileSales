package okio;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public class JvmSystemFileSystem extends FileSystem {
    public FileMetadata metadataOrNull(Path path) {
        Intrinsics.checkNotNullParameter(path, "path");
        File file = path.toFile();
        boolean zIsFile = file.isFile();
        boolean zIsDirectory = file.isDirectory();
        long jLastModified = file.lastModified();
        long length = file.length();
        if (!zIsFile && !zIsDirectory && 0 == jLastModified && 0 == length && !file.exists()) {
            return null;
        }
        return new FileMetadata(zIsFile, zIsDirectory, null, Long.valueOf(length), null, Long.valueOf(jLastModified), null, null, 128, null);
    }
    public List<Path> list(Path dir) throws IOException {
        Intrinsics.checkNotNullParameter(dir, "dir");
        List<Path> list = list(dir, true);
        checkNotNull(list);
        return list;
    }
    public List<Path> listOrNull(Path dir) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        try {
            return list(dir, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Path> list(Path path, boolean z) throws IOException {
        File file = path.toFile();
        String[] list = file.list();
        if (null != list) {
            ArrayList arrayList = new ArrayList();
            for (String it : list) {
                checkNotNullExpressionValue(it, "it");
                arrayList.add(path.resolve(it));
            }
            CollectionsKt.sort(arrayList);
            return arrayList;
        }
        if (!z) {
            return null;
        }
        if (file.exists()) {
            throw new IOException("failed to list " + path);
        }
        throw new FileNotFoundException("no such file: " + path);
    }
    public FileHandle openReadOnly(Path file) {
        Intrinsics.checkNotNullParameter(file, "file");
        try {
            return new JvmFileHandle(false, new RandomAccessFile(file.toFile(), "r"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public String toString() {
        return "JvmSystemFileSystem";
    }
}
