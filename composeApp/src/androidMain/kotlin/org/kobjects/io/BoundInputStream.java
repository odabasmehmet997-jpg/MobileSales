package org.kobjects.io;

import java.io.IOException;
import java.io.InputStream;

public class BoundInputStream extends InputStream {
    InputStream f894is;
    int remaining;
    public BoundInputStream(InputStream inputStream, int i2) {
        this.f894is = inputStream;
        this.remaining = i2;
    }
    public int available() throws IOException {
        int available = this.f894is.available();
        int i2 = this.remaining;
        return available < i2 ? available : i2;
    }
    public int read() throws IOException {
        int i2 = this.remaining;
        if (0 >= i2) {
            return -1;
        }
        this.remaining = i2 - 1;
        return this.f894is.read();
    }
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        int i4 = this.remaining;
        if (i3 > i4) {
            i3 = i4;
        }
        int read = this.f894is.read(bArr, i2, i3);
        if (0 < read) {
            this.remaining -= read;
        }
        return read;
    }
    public void close() {
        try {
            this.f894is.close();
        } catch (IOException unused) {
        }
    }
}
