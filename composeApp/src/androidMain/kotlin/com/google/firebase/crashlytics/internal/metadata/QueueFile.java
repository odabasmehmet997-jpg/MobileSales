package com.google.firebase.crashlytics.internal.metadata;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*  INFO: loaded from: classes2.dex */
class QueueFile implements Closeable {
    static final int HEADER_LENGTH = 16;
    private static final int INITIAL_LENGTH = 4096;
    private static final Logger LOGGER = Logger.getLogger(QueueFile.class.getName());
    private final byte[] buffer;
    private int elementCount;
    int fileLength;
    private Element first;
    private Element last;
    private final RandomAccessFile raf;

    public interface ElementReader {
        void read(InputStream inputStream, int i2) throws IOException;
    }

    public QueueFile(File file) throws IOException {
        this.buffer = new byte[16];
        if (!file.exists()) {
            initialize(file);
        }
        this.raf = open(file);
        readHeader();
    }

    QueueFile(RandomAccessFile randomAccessFile) throws IOException {
        this.buffer = new byte[16];
        this.raf = randomAccessFile;
        readHeader();
    }

    private static void writeInt(byte[] bArr, int i2, int i3) {
        bArr[i2] = (byte) (i3 >> 24);
        bArr[i2 + 1] = (byte) (i3 >> 16);
        bArr[i2 + 2] = (byte) (i3 >> 8);
        bArr[i2 + 3] = (byte) i3;
    }

    private static void writeInts(byte[] bArr, int... iArr) {
        int i2 = 0;
        for (int i3 : iArr) {
            writeInt(bArr, i2, i3);
            i2 += 4;
        }
    }

    private static int readInt(byte[] bArr, int i2) {
        return ((bArr[i2] & 255) << 24) + ((bArr[i2 + 1] & 255) << 16) + ((bArr[i2 + 2] & 255) << 8) + (bArr[i2 + 3] & 255);
    }

    private void readHeader() throws IOException {
        this.raf.seek(0L);
        this.raf.readFully(this.buffer);
        int i2 = readInt(this.buffer, 0);
        this.fileLength = i2;
        if (i2 > this.raf.length()) {
            throw new IOException("File is truncated. Expected length: " + this.fileLength + ", Actual length: " + this.raf.length());
        }
        this.elementCount = readInt(this.buffer, 4);
        int i3 = readInt(this.buffer, 8);
        int i4 = readInt(this.buffer, 12);
        this.first = readElement(i3);
        this.last = readElement(i4);
    }

    private void writeHeader(int i2, int i3, int i4, int i5) throws IOException {
        writeInts(this.buffer, i2, i3, i4, i5);
        this.raf.seek(0L);
        this.raf.write(this.buffer);
    }

    private Element readElement(int i2) throws IOException {
        if (i2 == 0) {
            return Element.NULL;
        }
        this.raf.seek(i2);
        return new Element(i2, this.raf.readInt());
    }

    private static void initialize(File file) throws IOException {
        File file2 = new File(file.getPath() + ".tmp");
        RandomAccessFile randomAccessFileOpen = open(file2);
        try {
            randomAccessFileOpen.setLength(4096L);
            randomAccessFileOpen.seek(0L);
            byte[] bArr = new byte[16];
            writeInts(bArr, 4096, 0, 0, 0);
            randomAccessFileOpen.write(bArr);
            randomAccessFileOpen.close();
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } catch (Throwable th) {
            randomAccessFileOpen.close();
            throw th;
        }
    }

    private static RandomAccessFile open(File file) throws FileNotFoundException {
        return new RandomAccessFile(file, "rwd");
    }

    /*  INFO: Access modifiers changed from: private */
    public int wrapPosition(int i2) {
        int i3 = this.fileLength;
        return i2 < i3 ? i2 : (i2 + 16) - i3;
    }

    private void ringWrite(int i2, byte[] bArr, int i3, int i4) throws IOException {
        int iWrapPosition = wrapPosition(i2);
        int i5 = iWrapPosition + i4;
        int i6 = this.fileLength;
        if (i5 <= i6) {
            this.raf.seek(iWrapPosition);
            this.raf.write(bArr, i3, i4);
            return;
        }
        int i7 = i6 - iWrapPosition;
        this.raf.seek(iWrapPosition);
        this.raf.write(bArr, i3, i7);
        this.raf.seek(16L);
        this.raf.write(bArr, i3 + i7, i4 - i7);
    }

    /*  INFO: Access modifiers changed from: private */
    public void ringRead(int i2, byte[] bArr, int i3, int i4) throws IOException {
        int iWrapPosition = wrapPosition(i2);
        int i5 = iWrapPosition + i4;
        int i6 = this.fileLength;
        if (i5 <= i6) {
            this.raf.seek(iWrapPosition);
            this.raf.readFully(bArr, i3, i4);
            return;
        }
        int i7 = i6 - iWrapPosition;
        this.raf.seek(iWrapPosition);
        this.raf.readFully(bArr, i3, i7);
        this.raf.seek(16L);
        this.raf.readFully(bArr, i3 + i7, i4 - i7);
    }

    public void add(byte[] bArr) throws IOException {
        add(bArr, 0, bArr.length);
    }

    public synchronized void add(byte[] bArr, int i2, int i3) throws IOException {
        int iWrapPosition;
        try {
            nonNull(bArr, "buffer");
            if ((i2 | i3) < 0 || i3 > bArr.length - i2) {
                throw new IndexOutOfBoundsException();
            }
            expandIfNecessary(i3);
            boolean zIsEmpty = isEmpty();
            if (zIsEmpty) {
                iWrapPosition = 16;
            } else {
                Element element = this.last;
                iWrapPosition = wrapPosition(element.position + 4 + element.length);
            }
            Element element2 = new Element(iWrapPosition, i3);
            writeInt(this.buffer, 0, i3);
            ringWrite(element2.position, this.buffer, 0, 4);
            ringWrite(element2.position + 4, bArr, i2, i3);
            writeHeader(this.fileLength, this.elementCount + 1, zIsEmpty ? element2.position : this.first.position, element2.position);
            this.last = element2;
            this.elementCount++;
            if (zIsEmpty) {
                this.first = element2;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public int usedBytes() {
        if (this.elementCount == 0) {
            return 16;
        }
        Element element = this.last;
        int i2 = element.position;
        int i3 = this.first.position;
        if (i2 >= i3) {
            return (i2 - i3) + 4 + element.length + 16;
        }
        return (((i2 + 4) + element.length) + this.fileLength) - i3;
    }

    private int remainingBytes() {
        return this.fileLength - usedBytes();
    }

    public synchronized boolean isEmpty() {
        return this.elementCount == 0;
    }

    private void expandIfNecessary(int i2) throws IOException {
        int i3 = i2 + 4;
        int iRemainingBytes = remainingBytes();
        if (iRemainingBytes >= i3) {
            return;
        }
        int i4 = this.fileLength;
        do {
            iRemainingBytes += i4;
            i4 <<= 1;
        } while (iRemainingBytes < i3);
        setLength(i4);
        Element element = this.last;
        int iWrapPosition = wrapPosition(element.position + 4 + element.length);
        if (iWrapPosition < this.first.position) {
            FileChannel channel = this.raf.getChannel();
            channel.position(this.fileLength);
            long j2 = iWrapPosition - 4;
            if (channel.transferTo(16L, j2, channel) != j2) {
                throw new AssertionError("Copied insufficient number of bytes!");
            }
        }
        int i5 = this.last.position;
        int i6 = this.first.position;
        if (i5 < i6) {
            int i7 = (this.fileLength + i5) - 16;
            writeHeader(i4, this.elementCount, i6, i7);
            this.last = new Element(i7, this.last.length);
        } else {
            writeHeader(i4, this.elementCount, i6, i5);
        }
        this.fileLength = i4;
    }

    private void setLength(int i2) throws IOException {
        this.raf.setLength(i2);
        this.raf.getChannel().force(true);
    }

    public synchronized byte[] peek() throws IOException {
        if (isEmpty()) {
            return null;
        }
        Element element = this.first;
        int i2 = element.length;
        byte[] bArr = new byte[i2];
        ringRead(element.position + 4, bArr, 0, i2);
        return bArr;
    }

    public synchronized void peek(ElementReader elementReader) throws IOException {
        if (this.elementCount > 0) {
            elementReader.read(new ElementInputStream(this.first), this.first.length);
        }
    }

    public synchronized void forEach(ElementReader elementReader) throws IOException {
        int iWrapPosition = this.first.position;
        for (int i2 = 0; i2 < this.elementCount; i2++) {
            Element element = readElement(iWrapPosition);
            elementReader.read(new ElementInputStream(element), element.length);
            iWrapPosition = wrapPosition(element.position + 4 + element.length);
        }
    }

    /*  INFO: Access modifiers changed from: private */
    public static <T> T nonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    private final class ElementInputStream extends InputStream {
        private int position;
        private int remaining;

        private ElementInputStream(Element element) {
            this.position = QueueFile.this.wrapPosition(element.position + 4);
            this.remaining = element.length;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) throws IOException {
            QueueFile.nonNull(bArr, "buffer");
            if ((i2 | i3) < 0 || i3 > bArr.length - i2) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i4 = this.remaining;
            if (i4 <= 0) {
                return -1;
            }
            if (i3 > i4) {
                i3 = i4;
            }
            QueueFile.this.ringRead(this.position, bArr, i2, i3);
            this.position = QueueFile.this.wrapPosition(this.position + i3);
            this.remaining -= i3;
            return i3;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.remaining == 0) {
                return -1;
            }
            QueueFile.this.raf.seek(this.position);
            int i2 = QueueFile.this.raf.read();
            this.position = QueueFile.this.wrapPosition(this.position + 1);
            this.remaining--;
            return i2;
        }
    }

    public synchronized int size() {
        return this.elementCount;
    }

    public synchronized void remove() throws IOException {
        try {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            if (this.elementCount == 1) {
                clear();
            } else {
                Element element = this.first;
                int iWrapPosition = wrapPosition(element.position + 4 + element.length);
                ringRead(iWrapPosition, this.buffer, 0, 4);
                int i2 = readInt(this.buffer, 0);
                writeHeader(this.fileLength, this.elementCount - 1, iWrapPosition, this.last.position);
                this.elementCount--;
                this.first = new Element(iWrapPosition, i2);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void clear() throws IOException {
        try {
            writeHeader(4096, 0, 0, 0);
            this.elementCount = 0;
            Element element = Element.NULL;
            this.first = element;
            this.last = element;
            if (this.fileLength > 4096) {
                setLength(4096);
            }
            this.fileLength = 4096;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.raf.close();
    }

    public boolean hasSpaceFor(int i2, int i3) {
        return (usedBytes() + 4) + i2 <= i3;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append('[');
        sb.append("fileLength=");
        sb.append(this.fileLength);
        sb.append(", size=");
        sb.append(this.elementCount);
        sb.append(", first=");
        sb.append(this.first);
        sb.append(", last=");
        sb.append(this.last);
        sb.append(", element lengths=[");
        try {
            forEach(new ElementReader() { // from class: com.google.firebase.crashlytics.internal.metadata.QueueFile.1
                boolean first = true;

                @Override // com.google.firebase.crashlytics.internal.metadata.QueueFile.ElementReader
                public void read(InputStream inputStream, int i2) throws IOException {
                    if (this.first) {
                        this.first = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(i2);
                }
            });
        } catch (IOException e2) {
            LOGGER.log(Level.WARNING, "read error", (Throwable) e2);
        }
        sb.append("]]");
        return sb.toString();
    }

    static class Element {
        static final int HEADER_LENGTH = 4;
        static final Element NULL = new Element(0, 0);
        final int length;
        final int position;

        Element(int i2, int i3) {
            this.position = i2;
            this.length = i3;
        }

        public String toString() {
            return getClass().getSimpleName() + "[position = " + this.position + ", length = " + this.length + "]";
        }
    }
}
