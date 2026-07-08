package org.ksoap2.transport;

import java.io.IOException;
import org.springframework.http.HttpHeaders;

public class KeepAliveHttpsTransportSE extends HttpsTransportSE {
    public KeepAliveHttpsTransportSE(String str, int i2, String str2, int i3) {
        super(str, i2, str2, i3);
    }
    public ServiceConnection getServiceConnection() throws IOException {
        HttpsServiceConnectionSEIgnoringConnectionClose httpsServiceConnectionSEIgnoringConnectionClose = new HttpsServiceConnectionSEIgnoringConnectionClose(this.host, this.port, this.file, this.timeout);
        httpsServiceConnectionSEIgnoringConnectionClose.setRequestProperty(HttpHeaders.CONNECTION, "keep-alive");
        return httpsServiceConnectionSEIgnoringConnectionClose;
    }
}
