package org.ksoap2.transport;

import java.io.IOException;

public class HttpsServiceConnectionSEIgnoringConnectionClose extends HttpsServiceConnectionSE {
    public HttpsServiceConnectionSEIgnoringConnectionClose(final String str, final int i2, final String str2, final int i3) throws IOException {
        super(str, i2, str2, i3);
    }
    public void setRequestProperty(final String str, final String str2) {
        if ("Connection".equalsIgnoreCase(str) && "close".equalsIgnoreCase(str2)) {
            return;
        }
        super.setRequestProperty(str, str2);
    }
}
