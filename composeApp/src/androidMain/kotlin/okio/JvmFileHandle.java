package okio;

import kotlin.jvm.internal.Intrinsics;

import java.io.IOException;
import java.io.RandomAccessFile;

public final class JvmFileHandle extends FileHandle {
    private final RandomAccessFile randomAccessFile;
    public JvmFileHandle(final boolean z, final RandomAccessFile randomAccessFile) {
        super(z);
        Intrinsics.checkNotNullParameter(randomAccessFile, "randomAccessFile");
        this.randomAccessFile = randomAccessFile;
    }
    protected synchronized long protectedSize() {
        try {
            return randomAccessFile.length();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected synchronized int protectedRead(final long j2, final byte[] array, final int i2, final int i3) {
        Intrinsics.checkNotNullParameter(array, "array");
        try {
            randomAccessFile.seek(j2);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        int i4 = 0;
        while (true) {
            if (i4 >= i3) {
                break;
            }
            int i5 = 0;
            try {
                i5 = randomAccessFile.read(array, i2, i3 - i4);
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
            if (-1 != i5) {
                i4 += i5;
            } else if (0 == i4) {
                return -1;
            }
        }
        return i4;
    }
    protected synchronized void protectedClose() {
        try {
            randomAccessFile.close();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
