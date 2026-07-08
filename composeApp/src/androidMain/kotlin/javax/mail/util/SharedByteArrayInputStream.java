package javax.mail.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.mail.internet.SharedInputStream;

public class SharedByteArrayInputStream extends ByteArrayInputStream implements SharedInputStream {
    protected int start;
    public SharedByteArrayInputStream(byte[] bArr) {
        super(bArr);
        this.start = 0;
    }
    public SharedByteArrayInputStream(byte[] bArr, int i2, int i3) {
        super(bArr, i2, i3);
        this.start = i2;
    }
    public long getPosition() {
        return this.pos - this.start;
    }
    public InputStream newStream(long j2, long j3) {
        if (0 <= j2) {
            if (-1 == j3) {
                j3 = this.count - this.start;
            }
            return new SharedByteArrayInputStream(this.buf, this.start + ((int) j2), (int) (j3 - j2));
        }
        throw new IllegalArgumentException("start < 0");
    }
}
