package org.ksoap2.transport;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.springframework.http.HttpHeaders;
import org.xmlpull.v1.XmlPullParserException;

public class HttpTransportSE extends Transport {
    public HttpTransportSE(String str) {
        super(null, str);
    }
    public HttpTransportSE(Proxy proxy, String str) {
        super(proxy, str);
    }
    public HttpTransportSE(String str, int i2) {
        super(str, i2);
    }
    public HttpTransportSE(Proxy proxy, String str, int i2) {
        super(proxy, str, i2);
    }
    public HttpTransportSE(String str, int i2, int i3) {
        super(str, i2);
    }
    public HttpTransportSE(Proxy proxy, String str, int i2, int i3) {
        super(proxy, str, i2);
    }
    public void call(String str, SoapEnvelope soapEnvelope) throws XmlPullParserException, IOException {
        call(str, soapEnvelope, null);
    }
    public List call(String str, SoapEnvelope soapEnvelope, List list) throws XmlPullParserException, IOException {
        return call(str, soapEnvelope, list, null);
    }
    public List call(String str, SoapEnvelope soapEnvelope, List list, File file) throws XmlPullParserException, IOException {
        boolean z;
        int i2;
        List responseProperties;
        InputStream bufferedInputStream;
        int i3 = 0;
        int i4 = 0;
        String str2 = str == null ? "\"\"" : str;
        byte[] bArrCreateRequestData = createRequestData(soapEnvelope, "UTF-8");
        this.requestDump = this.debug ? new String(bArrCreateRequestData) : null;
        this.responseDump = null;
        ServiceConnection serviceConnection = getServiceConnection();
        serviceConnection.setRequestProperty(HttpHeaders.USER_AGENT, "ksoap2-android/2.6.0+");
        if (soapEnvelope.version != 120) {
            serviceConnection.setRequestProperty("SOAPAction", str2);
        }
        if (soapEnvelope.version == 120) {
            serviceConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/soap+xml;charset=utf-8");
        } else {
            serviceConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "text/xml;charset=utf-8");
        }
        serviceConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "gzip");
        int i5 = 0;
        if (list != null) {
            for (int i6 = 0; i6 < list.size(); i6++) {
                HeaderProperty headerProperty = (HeaderProperty) list.get(i6);
                serviceConnection.setRequestProperty(headerProperty.getKey(), headerProperty.getValue());
            }
        }
        serviceConnection.setRequestMethod("POST");
        sendData(bArrCreateRequestData, serviceConnection, soapEnvelope);
        int responseCode = serviceConnection.getResponseCode();
        IOException e;
        try {
            responseProperties = serviceConnection.getResponseProperties();
            i4 = 0;
            z = false;
            i3 = 8192;
            while (i5 < responseProperties.size()) {
                HeaderProperty headerProperty2 = (HeaderProperty) responseProperties.get(i5);
                if (headerProperty2.getKey() != null) {
                    if (headerProperty2.getKey().equalsIgnoreCase("content-length") && headerProperty2.getValue() != null) {
                        try {
                            i3 = Integer.parseInt(headerProperty2.getValue());
                        } catch (NumberFormatException unused) {
                            i3 = 8192;
                        }
                    }
                    if (headerProperty2.getKey().equalsIgnoreCase(HttpHeaders.CONTENT_TYPE) && headerProperty2.getValue().contains("xml")) {
                        z = true;
                    }
                    if (headerProperty2.getKey().equalsIgnoreCase(HttpHeaders.CONTENT_ENCODING) && headerProperty2.getValue().equalsIgnoreCase("gzip")) {
                        i4 = 1;
                    }
                }
                i5++;
            }
        } catch (IOException e3) {
            e = e3;
            z = false;
            i2 = 8192;
            responseProperties = null;
        }
        if (responseCode != 200) {
            throw new HttpResponseException("HTTP request failed, HTTP status: " + responseCode, responseCode, responseProperties);
        }
        if (i3 <= 0) {
            bufferedInputStream = null;
        } else if (i4 != 0) {
            bufferedInputStream = getUnZippedInputStream(new BufferedInputStream(serviceConnection.openInputStream(), i3));
        } else {
            bufferedInputStream = new BufferedInputStream(serviceConnection.openInputStream(), i3);
        }
        if (this.debug) {
            bufferedInputStream = readDebug(bufferedInputStream, i3, file);
        }
        parseResponse(soapEnvelope, bufferedInputStream, responseProperties);
        serviceConnection.disconnect();
        return responseProperties;
    }
    protected void sendData(byte[] bArr, ServiceConnection serviceConnection, SoapEnvelope soapEnvelope) throws IOException {
        serviceConnection.setRequestProperty(HttpHeaders.CONTENT_LENGTH, "" + bArr.length);
        serviceConnection.setFixedLengthStreamingMode(bArr.length);
        OutputStream outputStreamOpenOutputStream = serviceConnection.openOutputStream();
        outputStreamOpenOutputStream.write(bArr, 0, bArr.length);
        outputStreamOpenOutputStream.flush();
        outputStreamOpenOutputStream.close();
    }
    protected void parseResponse(SoapEnvelope soapEnvelope, InputStream inputStream, List list) throws XmlPullParserException, IOException {
        parseResponse(soapEnvelope, inputStream);
    }
    private InputStream readDebug(InputStream inputStream, int i2, File file) throws IOException {
        OutputStream byteArrayOutputStream;
        if (file != null) {
            byteArrayOutputStream = new FileOutputStream(file);
        } else {
            if (i2 <= 0) {
                i2 = 262144;
            }
            byteArrayOutputStream = new ByteArrayOutputStream(i2);
        }
        byte[] byteArray = new byte[256];
        while (true) {
            int i3 = inputStream.read(byteArray, 0, 256);
            if (i3 == -1) {
                break;
            }
            byteArrayOutputStream.write(byteArray, 0, i3);
        }
        byteArrayOutputStream.flush();
        if (byteArrayOutputStream instanceof ByteArrayOutputStream) {
            byteArray = ((ByteArrayOutputStream) byteArrayOutputStream).toByteArray();
        }
        this.responseDump = new String(byteArray);
        inputStream.close();
        if (file != null) {
            return new FileInputStream(file);
        }
        return new ByteArrayInputStream(byteArray);
    }
    private InputStream getUnZippedInputStream(InputStream inputStream) throws IOException {
        try {
            return inputStream;
        } catch (ClassCastException unused) {
            return new GZIPInputStream(inputStream);
        }
    }
    public ServiceConnection getServiceConnection() throws IOException {
        return new ServiceConnectionSE(this.proxy, this.url, this.timeout);
    }
}
