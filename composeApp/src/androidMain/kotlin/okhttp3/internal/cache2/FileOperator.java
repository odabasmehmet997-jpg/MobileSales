package okhttp3.internal.cache2;

import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import java.io.IOException;
import java.nio.channels.FileChannel;

public final class FileOperator {
    private final FileChannel fileChannel;
    public FileOperator(final FileChannel fileChannel) {
        Intrinsics.checkNotNullParameter(fileChannel, "fileChannel");
        this.fileChannel = fileChannel;
    }
    public void write(long j2, final Buffer source, long j3) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (0 > j3 || j3 > source.size()) {
            throw new IndexOutOfBoundsException();
        }
        while (0 < j3) {
            final long jTransferFrom = fileChannel.transferFrom(source, j2, j3);
            j2 += jTransferFrom;
            j3 -= jTransferFrom;
        }
    }
    public void read(long j2, final Buffer sink, long j3) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (0 > j3) {
            throw new IndexOutOfBoundsException();
        }
        while (0 < j3) {
            final long jTransferTo = fileChannel.transferTo(j2, j3, sink);
            j2 += jTransferTo;
            j3 -= jTransferTo;
        }
    }
}
