package javax.mail.internet;

import java.io.InputStream;

public interface SharedInputStream {
    long getPosition();

    InputStream newStream(long j2, long j3);
}
