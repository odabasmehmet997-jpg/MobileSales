package com.proje.mobilesales.core.tigerwcf.xml;
import androidx.annotation.NonNull;
import com.proje.mobilesales.features.model.GenericData;
import com.proje.mobilesales.features.model.GenericDataPrimitive;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;
public class GenericResultHandler extends DefaultHandler2 {
    private static final String RESULTLINE = "RESULTLINE";
    private static final String RESULTXML = "RESULTXML";
    private static final String TAG = "ResultHandler";
    private StringBuilder builder;
    private GenericData instance;
    private List<GenericData> tList;
    public void startDocument() throws SAXException {
        super.startDocument();
        settList(new ArrayList<>());
        this.builder = new StringBuilder();
    }
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        super.startElement(str, str2, str3, attributes);
        if (str2.equalsIgnoreCase(RESULTLINE)) {
            try {
                GenericData genericData = new GenericData();
                this.instance = genericData;
                genericData.setGenericDataPrimitives(new ArrayList<GenericDataPrimitive>());
            } catch (Exception unused) {
            }
        }
    }
    public void endElement(String str, String str2, String str3) throws SAXException {
        super.endElement(str, str2, str3);
        if (str2.equalsIgnoreCase(RESULTLINE)) {
            this.tList.add(this.instance);
            this.builder.setLength(0);
        } else {
            if (str2.equalsIgnoreCase(RESULTXML)) {
                return;
            }
            setData(this.instance, str2, this.builder.toString());
            this.builder.setLength(0);
        }
    }
    public void endDocument() throws SAXException {
        super.endDocument();
    }
    public void characters(char[] cArr, int i2, int i3) throws SAXException {
        super.characters(cArr, i2, i3);
        this.builder.append(cArr, i2, i3);
    }
    private void setData(GenericData genericData,  String str,  String str2) {
        try {
            genericData.getGenericDataPrimitives().add(new GenericDataPrimitive(str, str2));
        } catch (Exception unused) {
        }
    }
    public List<GenericData> gettList() {
        return this.tList;
    }
    private void settList(List<GenericData> list) {
        this.tList = list;
    }
}
