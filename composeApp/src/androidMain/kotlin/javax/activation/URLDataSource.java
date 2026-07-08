package javax.activation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLDataSource implements DataSource {
    private final URL url;
    private URLConnection url_conn;

    public URLDataSource(URL url2) {
        this.url = url2;
    }

    public String getContentType() {
        try {
            if (null == url_conn) {
                this.url_conn = this.url.openConnection();
            }
        } catch (IOException unused) {
        }
        URLConnection uRLConnection = this.url_conn;
        String contentType = null != uRLConnection ? uRLConnection.getContentType() : null;
        return null == contentType ? "application/octet-stream" : contentType;
    }

    public String getName() {
        return this.url.getFile();
    }

    public InputStream getInputStream() throws IOException {
        return this.url.openStream();
    }

    public OutputStream getOutputStream() throws IOException {
        URLConnection openConnection = this.url.openConnection();
        this.url_conn = openConnection;
        if (null == openConnection) {
            return null;
        }
        openConnection.setDoOutput(true);
        return this.url_conn.getOutputStream();
    }

    public URL getURL() {
        return this.url;
    }
}
