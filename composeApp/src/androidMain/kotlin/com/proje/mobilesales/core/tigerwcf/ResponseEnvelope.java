package com.proje.mobilesales.core.tigerwcf;

import org.ksoap2.SoapEnvelope;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;


@Element(name = "Envelope")
@Namespace(prefix = "s", reference = SoapEnvelope.ENV)
@NamespaceList({@Namespace(prefix = "s", reference = SoapEnvelope.ENV)})
@Root(name = "Envelope")

public class ResponseEnvelope {

    @Element(name = "Body")
    @Namespace(prefix = "s", reference = SoapEnvelope.ENV)
    private ResponseBody body;

    public ResponseBody getBody() {
        return this.body;
    }

    public void setBody(ResponseBody responseBody) {
        this.body = responseBody;
    }
}
