package org.kobjects.xmlrpc;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.proje.mobilesales.BuildConfig;
import org.kobjects.base64.Base64;
import org.kobjects.isodate.IsoDate;
import org.kobjects.xml.XmlReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

public class XmlRpcParser {
    private final XmlReader parser;
    public XmlRpcParser(XmlReader xmlReader) {
        this.parser = xmlReader;
    }
    private Hashtable parseStruct() throws IOException {
        Hashtable hashtable = new Hashtable();
        int nextTag = nextTag();
        while (3 != nextTag) {
            nextTag();
            String nextText = nextText();
            nextTag();
            hashtable.put(nextText, parseValue());
            nextTag = nextTag();
        }
        nextTag();
        return hashtable;
    }
    private Object parseValue() throws IOException {
        Object obj;
        int next = this.parser.next();
        if (4 == next) {
            String text = this.parser.getText();
            obj = text;
            next = this.parser.next();
        } else {
            obj = null;
        }
        if (2 == next) {
            String name = this.parser.getName();
            if ("array".equals(name)) {
                obj = parseArray();
            } else if ("struct".equals(name)) {
                obj = parseStruct();
            } else {
                if (name.equals(TypedValues.Custom.S_STRING)) {
                    obj = nextText();
                } else if ("i4".equals(name) || "int".equals(name)) {
                    obj = Integer.valueOf(Integer.parseInt(nextText().trim()));
                } else if (name.equals(TypedValues.Custom.S_BOOLEAN)) {
                    obj = Boolean.valueOf(nextText().trim().equals(BuildConfig.NETSIS_DEMO_PASSWORD));
                } else if ("dateTime.iso8601".equals(name)) {
                    obj = IsoDate.stringToDate(nextText(), 3);
                } else if ("base64".equals(name)) {
                    obj = Base64.decode(nextText());
                } else if ("double".equals(name)) {
                    obj = nextText();
                }
                nextTag();
            }
        }
        nextTag();
        return obj;
    }
    private Vector parseArray() throws IOException {
        nextTag();
        int nextTag = nextTag();
        Vector vector = new Vector();
        while (3 != nextTag) {
            vector.addElement(parseValue());
            nextTag = this.parser.getType();
        }
        nextTag();
        nextTag();
        return vector;
    }
    private Object parseFault() throws IOException {
        nextTag();
        Object parseValue = parseValue();
        nextTag();
        return parseValue;
    }
    private Object parseParams() throws IOException {
        Vector vector = new Vector();
        int nextTag = nextTag();
        while (3 != nextTag) {
            nextTag();
            vector.addElement(parseValue());
            nextTag = nextTag();
        }
        nextTag();
        return vector;
    }
    public Object parseResponse() throws IOException {
        nextTag();
        if (2 == this.nextTag()) {
            if ("fault".equals(this.parser.getName())) {
                return parseFault();
            }
            if ("params".equals(this.parser.getName())) {
                return parseParams();
            }
        }
        return null;
    }
    private int nextTag() throws IOException {
        this.parser.getType();
        var next = this.parser.next();
        if (4 == next && this.parser.isWhitespace()) {
            next = this.parser.next();
        }
        if (3 == next || 2 == next) {
            return next;
        }
        throw new IOException("unexpected type: " + next);
    }
    private String nextText() throws IOException {
        String str;
        if (2 != parser.getType()) {
            throw new IOException("precondition: START_TAG");
        }
        var next = this.parser.next();
        if (4 == next) {
            String text = this.parser.getText();
            str = text;
            next = this.parser.next();
        } else {
            str = "";
        }
        if (3 == next) {
            return str;
        }
        throw new IOException("END_TAG expected");
    }
}
