package org.ksoap2.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.ksoap2.HeaderProperty;

public class ServiceConnectionSE implements ServiceConnection {
    private final HttpURLConnection connection;
    public ServiceConnectionSE(String str) throws IOException {
        this(null, str, 20000);
    }
    public ServiceConnectionSE(Proxy proxy, String str) throws IOException {
        this(proxy, str, 20000);
    }
    public ServiceConnectionSE(String str, int i2) throws IOException {
        this(null, str, i2);
    }
    public ServiceConnectionSE(Proxy proxy, String str, int i2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) (proxy == null ? new URL(str).openConnection() : new URL(str).openConnection(proxy));
        this.connection = httpURLConnection;
        httpURLConnection.setUseCaches(false);
        this.connection.setDoOutput(true);
        this.connection.setDoInput(true);
        this.connection.setConnectTimeout(i2);
        this.connection.setReadTimeout(i2);
    }
    public void connect() throws IOException {
        this.connection.connect();
    }
    public void disconnect() {
        this.connection.disconnect();
    }
    public List getResponseProperties() throws IOException {
        LinkedList linkedList = new LinkedList();
        Map<String, List<String>> headerFields = this.connection.getHeaderFields();
        if (headerFields != null) {
            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                String key = entry.getKey();
                List<String> list = entry.getValue();
                for (String value : list) {
                    linkedList.add(new HeaderProperty(key, value));
                }
            }
        }
        return linkedList;
    }
    public int getResponseCode() throws IOException {
        return this.connection.getResponseCode();
    }
    public void setRequestProperty(String str, String str2) {
        this.connection.setRequestProperty(str, str2);
    }
    public void setRequestMethod(String str) throws IOException {
        this.connection.setRequestMethod(str);
    }
    public void setFixedLengthStreamingMode(int i2) {
        this.connection.setFixedLengthStreamingMode(i2);
    }
    public void setChunkedStreamingMode() {
        this.connection.setChunkedStreamingMode(0);
    }
    public OutputStream openOutputStream() throws IOException {
        return this.connection.getOutputStream();
    }
    public InputStream openInputStream() throws IOException {
        return this.connection.getInputStream();
    }
    public InputStream getErrorStream() {
        return this.connection.getErrorStream();
    }
    public String getHost() {
        return this.connection.getURL().getHost();
    }
    public int getPort() {
        return this.connection.getURL().getPort();
    }
    public String getPath() {
        return this.connection.getURL().getPath();
    }
}
