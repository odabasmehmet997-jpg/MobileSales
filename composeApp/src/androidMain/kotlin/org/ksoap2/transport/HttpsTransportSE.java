package org.ksoap2.transport;
import java.io.IOException;
import java.net.Proxy;
public class HttpsTransportSE extends HttpTransportSE {
    static final String PROTOCOL = "https";
    private static final String PROTOCOL_FULL = "https://";
    private HttpsServiceConnectionSE connection;
    protected final String file;
    protected final String host;
    protected final int port;
    public HttpsTransportSE(String str, int i2, String str2, int i3) {
        super("https://" + str + ":" + i2 + str2, i3);
        this.host = str;
        this.port = i2;
        this.file = str2;
    }
    public HttpsTransportSE(Proxy proxy, String str, int i2, String str2, int i3) {
        super(proxy, "https://" + str + ":" + i2 + str2);
        this.host = str;
        this.port = i2;
        this.file = str2;
        this.timeout = i3;
    }
    public ServiceConnection getServiceConnection() throws IOException {
        HttpsServiceConnectionSE httpsServiceConnectionSE = this.connection;
        if (httpsServiceConnectionSE != null) {
            return httpsServiceConnectionSE;
        }
        HttpsServiceConnectionSE httpsServiceConnectionSE2 = new HttpsServiceConnectionSE(this.proxy, this.host, this.port, this.file, this.timeout);
        this.connection = httpsServiceConnectionSE2;
        return httpsServiceConnectionSE2;
    }
}
