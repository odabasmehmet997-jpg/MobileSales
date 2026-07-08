package org.ksoap2.transport;

import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.springframework.http.HttpHeaders;
import org.xmlpull.v1.XmlPullParserException;

public class KeepAliveHttpTransportSE extends HttpTransportSE {
    public KeepAliveHttpTransportSE(String str) {
        super(null, str);
    }
    public KeepAliveHttpTransportSE(Proxy proxy, String str) {
        super(proxy, str);
    }
    public KeepAliveHttpTransportSE(String str, int i2) {
        super(str, i2);
    }
    public KeepAliveHttpTransportSE(Proxy proxy, String str, int i2) {
        super(proxy, str, i2);
    }
    public KeepAliveHttpTransportSE(String str, int i2, int i3) {
        super(str, i2);
    }
    public KeepAliveHttpTransportSE(Proxy proxy, String str, int i2, int i3) {
        super(proxy, str, i2);
    }
    public List call(String str, SoapEnvelope soapEnvelope, List list) throws XmlPullParserException, IOException {
        if (list == null) {
            list = new ArrayList();
        }
        HeaderProperty header = getHeader(list, HttpHeaders.CONNECTION);
        if (header == null) {
            list.add(new HeaderProperty(HttpHeaders.CONNECTION, "keep-alive"));
        } else {
            header.setValue("keep-alive");
        }
        return super.call(str, soapEnvelope, list);
    }
    protected HeaderProperty getHeader(List list, String str) {
        if (list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                HeaderProperty headerProperty = (HeaderProperty) list.get(i2);
                if (str.equals(headerProperty.getKey())) {
                    return headerProperty;
                }
            }
        }
        return null;
    }
}
