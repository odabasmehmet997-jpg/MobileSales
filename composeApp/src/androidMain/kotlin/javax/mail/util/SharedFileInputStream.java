package javax.mail.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import javax.mail.internet.SharedInputStream;

public class SharedFileInputStream extends BufferedInputStream implements SharedInputStream {
    private static final int defaultBufferSize = 2048;
    protected long bufpos;
    protected int bufsize;
    protected long datalen;
    protected RandomAccessFile in;
    private final boolean master;
    private SharedFile sf;
    protected long start;
    public boolean markSupported() {
        return true;
    }
    static class SharedFile {
        private int cnt;
        private final RandomAccessFile in;

        SharedFile(String str) throws IOException {
            this.in = new RandomAccessFile(str, "r");
        }

        SharedFile(File file) throws IOException {
            this.in = new RandomAccessFile(file, "r");
        }

        public synchronized RandomAccessFile open() {
            this.cnt++;
            return this.in;
        }

        public synchronized void close() throws IOException {
            int i2 = this.cnt;
            if (0 < i2) {
                int i3 = i2 - 1;
                this.cnt = i3;
                if (0 >= i3) {
                    this.in.close();
                }
            }
        }

        public synchronized void forceClose() throws IOException {
            if (0 < cnt) {
                this.cnt = 0;
                this.in.close();
            } else {
                try {
                    this.in.close();
                } catch (IOException unused) {
                }
            }
        }

        
        protected void finalize() throws Throwable {
            super.finalize();
            this.in.close();
        }
    }
    private void ensureOpen() throws IOException {
        if (null == in) {
            throw new IOException("Stream closed");
        }
    }
    public SharedFileInputStream(File file) throws IOException {
        this(file, defaultBufferSize);
    }
    public SharedFileInputStream(String str) throws IOException {
        this(str, defaultBufferSize);
    }
    public SharedFileInputStream(File file, int i2) throws IOException {
        super(null);
        this.start = 0;
        this.master = true;
        if (0 < i2) {
            init(new SharedFile(file), i2);
            return;
        }
        throw new IllegalArgumentException("Buffer size <= 0");
    }
    public SharedFileInputStream(String str, int i2) throws IOException {
        super(null);
        this.start = 0;
        this.master = true;
        if (0 < i2) {
            init(new SharedFile(str), i2);
            return;
        }
        throw new IllegalArgumentException("Buffer size <= 0");
    }
    private void init(SharedFile sharedFile, int i2) throws IOException {
        this.sf = sharedFile;
        RandomAccessFile open = sharedFile.open();
        this.in = open;
        this.start = 0;
        this.datalen = open.length();
        this.bufsize = i2;
        this.buf = new byte[i2];
    }
    private SharedFileInputStream(SharedFile sharedFile, long j2, long j3, int i2) {
        super(null);
        this.start = 0;
        this.master = false;
        this.sf = sharedFile;
        this.in = sharedFile.open();
        this.start = j2;
        this.bufpos = j2;
        this.datalen = j3;
        this.bufsize = i2;
        this.buf = new byte[i2];
    }
    private void fill() throws IOException {
        if (0 > markpos) {
            this.pos = 0;
            this.bufpos += this.count;
        } else if (this.pos >= this.buf.length) {
            int i2 = this.markpos;
            if (0 < i2) {
                int i3 = this.pos - i2;
                System.arraycopy(this.buf, this.markpos, this.buf, 0, i3);
                this.pos = i3;
                this.bufpos += this.markpos;
                this.markpos = 0;
            } else {
                int length = this.buf.length;
                int i4 = this.marklimit;
                if (length >= i4) {
                    this.markpos = -1;
                    this.pos = 0;
                    this.bufpos += this.count;
                } else {
                    int i5 = this.pos * 2;
                    if (i5 <= i4) {
                        i4 = i5;
                    }
                    byte[] bArr = new byte[i4];
                    System.arraycopy(this.buf, 0, bArr, 0, this.pos);
                    this.buf = bArr;
                }
            }
        }
        int i6 = this.pos;
        this.count = i6;
        this.in.seek(this.bufpos + i6);
        int length2 = this.buf.length;
        int i7 = this.pos;
        int i8 = length2 - i7;
        long j2 = this.bufpos;
        long j3 = this.start;
        long j4 = (j2 - j3) + i7 + i8;
        long j5 = this.datalen;
        if (j4 > j5) {
            i8 = (int) (j5 - ((j2 - j3) + i7));
        }
        int read = this.in.read(this.buf, this.pos, i8);
        if (0 < read) {
            this.count = read + this.pos;
        }
    }
    public synchronized int read() throws IOException {
        ensureOpen();
        if (this.pos >= this.count) {
            fill();
            if (this.pos >= this.count) {
                return -1;
            }
        }
        byte[] bArr = this.buf;
        int i2 = this.pos;
        this.pos = i2 + 1;
        return bArr[i2] & 255;
    }
    private int read1(byte[] bArr, int i2, int i3) throws IOException {
        int i4 = this.count - this.pos;
        if (0 >= i4) {
            fill();
            i4 = this.count - this.pos;
            if (0 >= i4) {
                return -1;
            }
        }
        if (i4 < i3) {
            i3 = i4;
        }
        System.arraycopy(this.buf, this.pos, bArr, i2, i3);
        this.pos += i3;
        return i3;
    }
    public synchronized int read(byte[] r4, int r5, int r6) throws IOException {
        /*
            r3 = this;
            monitor-enter(r3)
            r3.ensureOpen()     // Catch:{ all -> 0x002a }
            r0 = r5 | r6
            int r1 = r5 + r6
            r0 = r0 | r1
            int r2 = r4.length     // Catch:{ all -> 0x002a }
            int r2 = r2 - r1
            r0 = r0 | r2
            if (r0 < 0) goto L_0x002e
            if (r6 != 0) goto L_0x0013
            monitor-exit(r3)
            r4 = 0
            return r4
        L_0x0013:
            int r0 = r3.read1(r4, r5, r6)     // Catch:{ all -> 0x002a }
            if (r0 > 0) goto L_0x001b
            monitor-exit(r3)
            return r0
        L_0x001b:
            if (r0 >= r6) goto L_0x002c
            int r1 = r5 + r0
            int r2 = r6 - r0
            int r1 = r3.read1(r4, r1, r2)     // Catch:{ all -> 0x002a }
            if (r1 > 0) goto L_0x0028
            goto L_0x002c
        L_0x0028:
            int r0 = r0 + r1
            goto L_0x001b
        L_0x002a:
            r4 = move-exception
            goto L_0x0034
        L_0x002c:
            monitor-exit(r3)
            return r0
        L_0x002e:
            java.lang.IndexOutOfBoundsException r4 = new java.lang.IndexOutOfBoundsException     // Catch:{ all -> 0x002a }
            r4.<init>()     // Catch:{ all -> 0x002a }
            throw r4     // Catch:{ all -> 0x002a }
        L_0x0034:
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.util.SharedFileInputStream.read(byte[], int, int):int");
    }
    public synchronized long skip(long j2) throws IOException {
        ensureOpen();
        if (0 >= j2) {
            return 0;
        }
        long j3 = this.count - this.pos;
        if (0 >= j3) {
            fill();
            j3 = this.count - this.pos;
            if (0 >= j3) {
                return 0;
            }
        }
        if (j3 < j2) {
            j2 = j3;
        }
        this.pos = (int) (this.pos + j2);
        return j2;
    }
    public synchronized int available() throws IOException {
        ensureOpen();
        return (this.count - this.pos) + in_available();
    }
    private int in_available() throws IOException {
        return (int) ((this.start + this.datalen) - (this.bufpos + this.count));
    }
    public synchronized void mark(int i2) {
        this.marklimit = i2;
        this.markpos = this.pos;
    }
    public synchronized void reset() throws IOException {
        ensureOpen();
        int i2 = this.markpos;
        if (0 <= i2) {
            this.pos = i2;
        } else {
            throw new IOException("Resetting to invalid mark");
        }
    }
    public void close() throws IOException {
        if (null != in) {
            try {
                if (this.master) {
                    this.sf.forceClose();
                } else {
                    this.sf.close();
                }
            } finally {
                this.sf = null;
                this.in = null;
                this.buf = null;
            }
        }
    }
    public long getPosition() {
        if (null != in) {
            return (this.bufpos + this.pos) - this.start;
        }
        throw new RuntimeException("Stream closed");
    }
    public synchronized InputStream newStream(long j2, long j3) {
        try {
            if (null == in) {
                throw new RuntimeException("Stream closed");
            }
            if (0 <= j2) {
                if (-1 == j3) {
                    j3 = this.datalen;
                }
            } else {
                throw new IllegalArgumentException("start < 0");
            }
        } catch (Throwable th) {
            throw th;
        }
        return new SharedFileInputStream(this.sf, this.start + ((int) j2), (int) (j3 - j2), this.bufsize);
    }
    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }
}
