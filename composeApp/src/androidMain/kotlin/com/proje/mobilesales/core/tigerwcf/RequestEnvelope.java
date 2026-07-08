package com.proje.mobilesales.core.tigerwcf;

import org.ksoap2.SoapEnvelope;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;


@Element(name = "Envelope")
@Namespace(prefix = "v", reference = SoapEnvelope.ENV)
@NamespaceList({@Namespace(prefix = "i", reference = "http://www.w3.org/2001/XMLSchema-sInstance"), @Namespace(prefix = "d", reference = SoapEnvelope.XSD), @Namespace(prefix = "c", reference = SoapEnvelope.ENC), @Namespace(prefix = "v", reference = SoapEnvelope.ENV)})
@Root(name = "Envelope")

public class RequestEnvelope {

    @Element(name = "Header")
    @Namespace(prefix = "v", reference = SoapEnvelope.ENV)
    private String aheader = "";

    @Element(name = "Body")
    @Namespace(prefix = "v", reference = SoapEnvelope.ENV)
    private RequestBody body;

    public String getHeader() {
        return this.aheader;
    }

    public void setHeader(String str) {
        this.aheader = str;
    }

    public RequestBody getBody() {
        return this.body;
    }

    public void setBody(RequestBody requestBody) {
        this.body = requestBody;
    }
}
