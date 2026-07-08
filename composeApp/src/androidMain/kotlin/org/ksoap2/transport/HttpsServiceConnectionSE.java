package org.ksoap2.transport;
import org.ksoap2.HeaderProperty;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class HttpsServiceConnectionSE implements ServiceConnection {
    private final HttpsURLConnection connection;
    public HttpsServiceConnectionSE(final String str, final int i2, final String str2, final int i3) throws IOException {
        this(null, str, i2, str2, i3);
    }
    public HttpsServiceConnectionSE(final Proxy proxy, final String str, final int i2, final String str2, final int i3) throws IOException {
        if (null == proxy) {
            connection = (HttpsURLConnection) new URL("https", str, i2, str2).openConnection();
        } else {
            connection = (HttpsURLConnection) new URL("https", str, i2, str2).openConnection(proxy);
        }
        this.updateConnectionParameters(i3);
    }
    private void updateConnectionParameters(final int i2) {
        connection.setConnectTimeout(i2);
        connection.setReadTimeout(i2);
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);
    }
    public void connect() throws IOException {
        connection.connect();
    }
    public void disconnect() {
        connection.disconnect();
    }
    public List getResponseProperties() {
        final Map<String, List<String>> headerFields = connection.getHeaderFields();
        final Set<String> keySet = headerFields.keySet();
        final LinkedList linkedList = new LinkedList();
        for (final String str : keySet) {
            final List<String> list = headerFields.get(str);
            for (int i2 = 0; i2 < list.size(); i2++) {
                linkedList.add(new HeaderProperty(str, list.get(i2)));
            }
        }
        return linkedList;
    }
    public int getResponseCode() throws IOException {
        return connection.getResponseCode();
    }
    public void setRequestProperty(final String str, final String str2) {
        connection.setRequestProperty(str, str2);
    }
    public void setRequestMethod(final String str) throws IOException {
        connection.setRequestMethod(str);
    }
    public void setFixedLengthStreamingMode(final int i2) {
        connection.setFixedLengthStreamingMode(i2);
    }
    public void setChunkedStreamingMode() {
        connection.setChunkedStreamingMode(0);
    }
    public OutputStream openOutputStream() throws IOException {
        return connection.getOutputStream();
    }
    public InputStream openInputStream() throws IOException {
        return connection.getInputStream();
    }
    public InputStream getErrorStream() {
        return connection.getErrorStream();
    }
    public String getHost() {
        return connection.getURL().getHost();
    }
    public int getPort() {
        return connection.getURL().getPort();
    }
    public String getPath() {
        return connection.getURL().getPath();
    }
    public void setSSLSocketFactory(final SSLSocketFactory sSLSocketFactory) {
        connection.setSSLSocketFactory(sSLSocketFactory);
    }
}
