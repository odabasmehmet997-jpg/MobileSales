package okio;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ForwardingFileSystem extends FileSystem {
    private final FileSystem delegate;
    protected ForwardingFileSystem(FileSystem delegate) {
        this.delegate = delegate;
    }
    public Path onPathParameter(Path path, String functionName, String parameterName) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(functionName, "functionName");
        Intrinsics.checkNotNullParameter(parameterName, "parameterName");
        return path;
    }
    public Path onPathResult(Path path, String functionName) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(functionName, "functionName");
        return path;
    }
    public FileMetadata metadataOrNull(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "path");
        FileMetadata fileMetadataMetadataOrNull = this.delegate.metadataOrNull(onPathParameter(path, "metadataOrNull", "path"));
        if (null == fileMetadataMetadataOrNull) {
            return null;
        }
        if (null == fileMetadataMetadataOrNull.getSymlinkTarget()) {
            return fileMetadataMetadataOrNull;
        }
        return fileMetadataMetadataOrNull.copy(fileMetadataMetadataOrNull.isRegularFile(), fileMetadataMetadataOrNull.isDirectory(), (251 & 4) != 0 ? fileMetadataMetadataOrNull.getSymlinkTarget() :
                        onPathResult(fileMetadataMetadataOrNull.getSymlinkTarget(), "metadataOrNull"),
                (251 & 8) != 0 ? fileMetadataMetadataOrNull.getSize() : null, (251 & 16) != 0 ?
                        fileMetadataMetadataOrNull.createdAtMillis : null, (251 & 32) != 0 ?
                        fileMetadataMetadataOrNull.getLastModifiedAtMillis() : null, (251 & 64) != 0 ?
                        fileMetadataMetadataOrNull.lastAccessedAtMillis : null, (251 & 128) != 0 ?
                        fileMetadataMetadataOrNull.extras : null);
    }
    public List<Path> list(Path dir) throws IOException {
        Intrinsics.checkNotNullParameter(dir, "dir");
        List<Path> list = this.delegate.list(onPathParameter(dir, "list", "dir"));
        ArrayList arrayList = new ArrayList();
        Iterator<Path> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(onPathResult(it.next(), "list"));
        }
        CollectionsKt.sort(arrayList);
        return arrayList;
    }
    public List<Path> listOrNull(Path dir) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        List<Path> listListOrNull = this.delegate.listOrNull(onPathParameter(dir, "listOrNull", "dir"));
        if (null == listListOrNull) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Path> it = listListOrNull.iterator();
        while (it.hasNext()) {
            arrayList.add(onPathResult(it.next(), "listOrNull"));
        }
        CollectionsKt.sort(arrayList);
        return arrayList;
    }
    public FileHandle openReadOnly(Path file) throws IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        return this.delegate.openReadOnly(onPathParameter(file, "openReadOnly", "file"));
    }
    public String toString() {
        return Reflection.getOrCreateKotlinClass(getClass()).getSimpleName() + '(' + this.delegate + ')';
    }
}
