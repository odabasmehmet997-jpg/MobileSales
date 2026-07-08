package org.ksoap2.transport;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import org.ksoap2.SoapEnvelope;
import org.kxml2.io.KXmlParser;
import org.kxml2.io.KXmlSerializer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
public abstract class Transport {
    protected static final String CONTENT_TYPE_SOAP_XML_CHARSET_UTF_8 = "application/soap+xml;charset=utf-8";
    protected static final String CONTENT_TYPE_XML_CHARSET_UTF_8 = "text/xml;charset=utf-8";
    protected static final String USER_AGENT = "ksoap2-android/2.6.0+";
    private int bufferLength;
    public boolean debug;
    private final HashMap prefixes;
    protected Proxy proxy;
    public String requestDump;
    public String responseDump;
    protected int timeout;
    protected String url;
    private String xmlVersionTag;
    public abstract List call(String str, SoapEnvelope soapEnvelope, List list) throws XmlPullParserException, IOException;
    public abstract List call(String str, SoapEnvelope soapEnvelope, List list, File file) throws XmlPullParserException, IOException;
    public abstract ServiceConnection getServiceConnection() throws IOException;
    public void reset() {
    }
    public HashMap getPrefixes() {
        return this.prefixes;
    }
    public Transport() {
        this.timeout = 20000;
        this.xmlVersionTag = "";
        this.bufferLength = 262144;
        this.prefixes = new HashMap();
    }
    public Transport(String str) {
        this(null, str);
    }
    public Transport(String str, int i2) {
        this.timeout = 20000;
        this.xmlVersionTag = "";
        this.bufferLength = 262144;
        this.prefixes = new HashMap();
        this.url = str;
        this.timeout = i2;
    }
    public Transport(String str, int i2, int i3) {
        this.timeout = 20000;
        this.xmlVersionTag = "";
        this.bufferLength = 262144;
        this.prefixes = new HashMap();
        this.url = str;
        this.timeout = i2;
        this.bufferLength = i3;
    }
    public Transport(Proxy proxy, String str) {
        this.timeout = 20000;
        this.xmlVersionTag = "";
        this.bufferLength = 262144;
        this.prefixes = new HashMap();
        this.proxy = proxy;
        this.url = str;
    }
    public Transport(Proxy proxy, String str, int i2) {
        this.timeout = 20000;
        this.xmlVersionTag = "";
        this.bufferLength = 262144;
        this.prefixes = new HashMap();
        this.proxy = proxy;
        this.url = str;
        this.timeout = i2;
    }
    public Transport(Proxy proxy, String str, int i2, int i3) {
        this.timeout = 20000;
        this.xmlVersionTag = "";
        this.bufferLength = 262144;
        this.prefixes = new HashMap();
        this.proxy = proxy;
        this.url = str;
        this.timeout = i2;
        this.bufferLength = i3;
    }
    protected void parseResponse(SoapEnvelope soapEnvelope, InputStream inputStream) throws XmlPullParserException, IOException {
        KXmlParser kXmlParser = new KXmlParser();
        kXmlParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
        kXmlParser.setInput(inputStream, null);
        soapEnvelope.parse(kXmlParser);
        inputStream.close();
    }
    protected byte[] createRequestData(SoapEnvelope soapEnvelope, String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(this.bufferLength);
        byteArrayOutputStream.write(this.xmlVersionTag.getBytes());
        XmlSerializer kXmlSerializer = new KXmlSerializer();
        kXmlSerializer.setOutput(byteArrayOutputStream, str);
        for (Object str2 : this.prefixes.keySet()) {
            kXmlSerializer.setPrefix((String) str2, (String) this.prefixes.get(str2));
        }
        soapEnvelope.write(kXmlSerializer);
        kXmlSerializer.flush();
        byteArrayOutputStream.write(13);
        byteArrayOutputStream.write(10);
        byteArrayOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }
    protected byte[] createRequestData(SoapEnvelope soapEnvelope) throws IOException {
        return createRequestData(soapEnvelope, null);
    }
    public void setUrl(String str) {
        this.url = str;
    }
    public String getUrl() {
        return this.url;
    }
    public void setXmlVersionTag(String str) {
        this.xmlVersionTag = str;
    }
    public void call(String str, SoapEnvelope soapEnvelope) throws XmlPullParserException, IOException {
        call(str, soapEnvelope, null);
    }
    public String getHost() throws MalformedURLException {
        return new URL(this.url).getHost();
    }
    public int getPort() throws MalformedURLException {
        return new URL(this.url).getPort();
    }
    public String getPath() throws MalformedURLException {
        return new URL(this.url).getPath();
    }
}
