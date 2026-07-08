package javax.mail;

import java.io.IOException;
import java.io.InputStream;

interface StreamLoader {
    void load(InputStream inputStream) throws IOException;
}
