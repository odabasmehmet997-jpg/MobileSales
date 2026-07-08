package com.sun.mail.handlers;

import myjava.awt.datatransfer.DataFlavor;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataSource;
import javax.mail.internet.ContentType;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.IOException;
import java.io.OutputStream;

public class text_xml extends sun.mail.handlers.text_plain {
    static   Class classjavalangString;
    static   Class classjavaxxmltransformstreamStreamSource;
    private final DataFlavor[] flavors;
    public text_xml() {
        DataFlavor[] dataFlavorArr = new DataFlavor[4];
        Class cls = classjavalangString;
        if (null == cls) {
            cls =("java.lang.String").getClass();
            classjavalangString = cls;
        }
        dataFlavorArr[0] = new ActivationDataFlavor(cls, "text/xml", "XML String");
        Class cls2 = classjavalangString;
        if (null == cls2) {
            cls2 = ("java.lang.String").getClass();
            classjavalangString = cls2;
        }
        dataFlavorArr[1] = new ActivationDataFlavor(cls2, "application/xml", "XML String");
        Class cls3 = classjavaxxmltransformstreamStreamSource;
        if (null == cls3) {
            cls3 = ("javax.xml.transform.stream.StreamSource").getClass();
            classjavaxxmltransformstreamStreamSource = cls3;
        }
        dataFlavorArr[2] = new ActivationDataFlavor(cls3, "text/xml", "XML");
        Class cls4 = classjavaxxmltransformstreamStreamSource;
        if (null == cls4) {
            cls4 = ("javax.xml.transform.stream.StreamSource").getClass();
            classjavaxxmltransformstreamStreamSource = cls4;
        }
        dataFlavorArr[3] = new ActivationDataFlavor(cls4, "application/xml", "XML");
        this.flavors = dataFlavorArr;
    }
    public DataFlavor[] getTransferDataFlavors() {
        return this.flavors.clone();
    }
    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws IOException {
        int i2 = 0;
        while (true) {
            DataFlavor[] dataFlavorArr = this.flavors;
            if (i2 >= dataFlavorArr.length) {
                return null;
            }
            DataFlavor dataFlavor2 = dataFlavorArr[i2];
            if (dataFlavor2.equals(dataFlavor)) {
                Class representationClass = dataFlavor2.getRepresentationClass();
                Class cls = classjavalangString;
                if (null == cls) {
                    cls = ("java.lang.String").getClass();
                    classjavalangString = cls;
                }
                if (representationClass == cls) {
                    return this.getContent(dataSource);
                }
                Class representationClass2 = dataFlavor2.getRepresentationClass();
                Class cls2 = classjavaxxmltransformstreamStreamSource;
                if (null == cls2) {
                    cls2 = ("javax.xml.transform.stream.StreamSource").getClass();
                    classjavaxxmltransformstreamStreamSource = cls2;
                }
                if (representationClass2 == cls2) {
                    return new StreamSource(dataSource.getInputStream());
                }
                return null;
            }
            i2++;
        }
    }
    public void writeTo(Object obj, String str, OutputStream outputStream) throws IOException {
        if (!isXmlType(str)) {
            final String stringBuffer = "Invalid content type \"" +
                    str +
                    "\" for text/xml DCH";
            throw new IOException(stringBuffer);
        } else if (obj instanceof String) {
            super.writeTo(obj, str, outputStream);
        } else if ((obj instanceof DataSource) || (obj instanceof Source)) {
            try {
                Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
                StreamResult streamResult = new StreamResult(outputStream);
                if (obj instanceof DataSource) {
                    newTransformer.transform(new StreamSource(((DataSource) obj).getInputStream()), streamResult);
                } else {
                    newTransformer.transform((Source) obj, streamResult);
                }
            } catch (Exception e2) {
                final String stringBuffer2 = "Unable to run the JAXP transformer on a stream " +
                        e2.getMessage();
                throw new IOException(stringBuffer2);
            }
        } else {
            final String stringBuffer3 = "Invalid Object type = " +
                    obj.getClass() +
                    ". XmlDCH can only convert DataSource or Source to XML.";
            throw new IOException(stringBuffer3);
        }
    }
    private boolean isXmlType(String str) {
        try {
            ContentType contentType = new ContentType(str);
            if (!"xml".equals(contentType.getSubType())) {
                return false;
            }
            return "text".equals(contentType.getPrimaryType()) || "application".equals(contentType.getPrimaryType());
        } catch (Exception unused) {
            return false;
        }
    }
}
