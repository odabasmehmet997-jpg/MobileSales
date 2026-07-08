package javax.activation;

import com.fasterxml.jackson.core.JsonFactory;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;

public class MimeTypeParameterList {
    private static final String TSPECIALS = "()<>@,;:/[]?=\\\"";
    private final Hashtable parameters = new Hashtable();
    public MimeTypeParameterList() {
    }
    public MimeTypeParameterList(String str) throws MimeTypeParseException {
        parse(str);
    }
    public void parse(String str) throws MimeTypeParseException {
        int length;
        int i2;
        String str2;
        if (null != str && 0 < (length = str.length())) {
            int skipWhiteSpace = skipWhiteSpace(str, 0);
            while (skipWhiteSpace < length && ';' == str.charAt(skipWhiteSpace)) {
                int skipWhiteSpace2 = skipWhiteSpace(str, skipWhiteSpace + 1);
                if (skipWhiteSpace2 < length) {
                    int i3 = skipWhiteSpace2;
                    while (i3 < length && isTokenChar(str.charAt(i3))) {
                        i3++;
                    }
                    String lowerCase = str.substring(skipWhiteSpace2, i3).toLowerCase(Locale.ENGLISH);
                    int skipWhiteSpace3 = skipWhiteSpace(str, i3);
                    if (skipWhiteSpace3 >= length || '=' != str.charAt(skipWhiteSpace3)) {
                        throw new MimeTypeParseException("Couldn't find the '=' that separates a parameter name from its value.");
                    }
                    int skipWhiteSpace4 = skipWhiteSpace(str, skipWhiteSpace3 + 1);
                    if (skipWhiteSpace4 < length) {
                        char charAt = str.charAt(skipWhiteSpace4);
                        if ('\"' == charAt) {
                            int i4 = skipWhiteSpace4 + 1;
                            if (i4 < length) {
                                int i5 = i4;
                                while (i5 < length) {
                                    charAt = str.charAt(i5);
                                    if ('\"' == charAt) {
                                        break;
                                    }
                                    if ('\\' == charAt) {
                                        i5++;
                                    }
                                    i5++;
                                }
                                if ('\"' == charAt) {
                                    str2 = unquote(str.substring(i4, i5));
                                    i2 = i5 + 1;
                                } else {
                                    throw new MimeTypeParseException("Encountered unterminated quoted parameter value.");
                                }
                            } else {
                                throw new MimeTypeParseException("Encountered unterminated quoted parameter value.");
                            }
                        } else if (isTokenChar(charAt)) {
                            i2 = skipWhiteSpace4;
                            while (i2 < length && isTokenChar(str.charAt(i2))) {
                                i2++;
                            }
                            str2 = str.substring(skipWhiteSpace4, i2);
                        } else {
                            throw new MimeTypeParseException("Unexpected character encountered at index " + skipWhiteSpace4);
                        }
                        this.parameters.put(lowerCase, str2);
                        skipWhiteSpace = skipWhiteSpace(str, i2);
                    } else {
                        throw new MimeTypeParseException("Couldn't find a value for parameter named " + lowerCase);
                    }
                } else {
                    return;
                }
            }
            if (skipWhiteSpace < length) {
                throw new MimeTypeParseException("More characters encountered in input than expected.");
            }
        }
    }
    public int size() {
        return this.parameters.size();
    }
    public boolean isEmpty() {
        return this.parameters.isEmpty();
    }
    public String get(String str) {
        return (String) this.parameters.get(str.trim().toLowerCase(Locale.ENGLISH));
    }
    public void set(String str, String str2) {
        this.parameters.put(str.trim().toLowerCase(Locale.ENGLISH), str2);
    }
    public void remove(String str) {
        this.parameters.remove(str.trim().toLowerCase(Locale.ENGLISH));
    }
    public Enumeration getNames() {
        return this.parameters.keys();
    }
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.ensureCapacity(this.parameters.size() * 16);
        final Iterator iterator = parameters.keySet().iterator();
        while (iterator.hasNext()) {
            String str = (String) iterator.next();
            stringBuffer.append("; ");
            stringBuffer.append(str);
            stringBuffer.append('=');
            stringBuffer.append(quote((String) this.parameters.get(str)));
        }
        return stringBuffer.toString();
    }
    private static boolean isTokenChar(char c2) {
        return ' ' < c2 && 127 > c2 && 0 > MimeTypeParameterList.TSPECIALS.indexOf(c2);
    }
    private static int skipWhiteSpace(String str, int i2) {
        int length = str.length();
        while (i2 < length && Character.isWhitespace(str.charAt(i2))) {
            i2++;
        }
        return i2;
    }
    private static String quote(String str) {
        int length = str.length();
        boolean z = false;
        for (int i2 = 0; i2 < length && !z; i2++) {
            z = !isTokenChar(str.charAt(i2));
        }
        if (!z) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.ensureCapacity((int) (length * 1.5d));
        stringBuffer.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if ('\\' == charAt || '\"' == charAt) {
                stringBuffer.append('\\');
            }
            stringBuffer.append(charAt);
        }
        stringBuffer.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        return stringBuffer.toString();
    }
    private static String unquote(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.ensureCapacity(length);
        boolean z = false;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (!z && '\\' != charAt) {
                stringBuffer.append(charAt);
            } else if (z) {
                stringBuffer.append(charAt);
                z = false;
            } else {
                z = true;
            }
        }
        return stringBuffer.toString();
    }
}
