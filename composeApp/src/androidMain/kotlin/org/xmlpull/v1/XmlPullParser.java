package org.xmlpull.v1;

import com.proje.mobilesales.core.sql.SqlLiteVariable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public interface XmlPullParser {
    int CDSECT = 5;
    int COMMENT = 9;
    int DOCDECL = 10;
    int END_DOCUMENT = 1;
    int END_TAG = 3;
    int ENTITY_REF = 6;
    String FEATURE_PROCESS_DOCDECL = "http://xmlpull.org/v1/doc/features.html#process-docdecl";
    String FEATURE_PROCESS_NAMESPACES = "http://xmlpull.org/v1/doc/features.html#process-namespaces";
    String FEATURE_REPORT_NAMESPACE_ATTRIBUTES = "http://xmlpull.org/v1/doc/features.html#report-namespace-prefixes";
    String FEATURE_VALIDATION = "http://xmlpull.org/v1/doc/features.html#validation";
    int IGNORABLE_WHITESPACE = 7;
    String NO_NAMESPACE = "";
    int PROCESSING_INSTRUCTION = 8;
    int START_DOCUMENT = 0;
    int START_TAG = 2;
    int TEXT = 4;
    String[] TYPES = {"START_DOCUMENT", "END_DOCUMENT", "START_TAG", "END_TAG", SqlLiteVariable._TEXT, "CDSECT", "ENTITY_REF", "IGNORABLE_WHITESPACE", "PROCESSING_INSTRUCTION", "COMMENT", "DOCDECL"};

    void defineEntityReplacementText(String str, String str2) throws XmlPullParserException;

    int getAttributeCount();

    String getAttributeName(int i2);

    String getAttributeNamespace(int i2);

    String getAttributePrefix(int i2);

    String getAttributeType(int i2);

    String getAttributeValue(int i2);

    String getAttributeValue(String str, String str2);

    int getColumnNumber();

    int getDepth();

    int getEventType() throws XmlPullParserException;

    boolean getFeature(String str);

    String getInputEncoding();

    int getLineNumber();

    String getName();

    String getNamespace();

    String getNamespace(String str);

    int getNamespaceCount(int i2) throws XmlPullParserException;

    String getNamespacePrefix(int i2) throws XmlPullParserException;

    String getNamespaceUri(int i2) throws XmlPullParserException;

    String getPositionDescription();

    String getPrefix();

    Object getProperty(String str);

    String getText();

    char[] getTextCharacters(int[] iArr);

    boolean isAttributeDefault(int i2);

    boolean isEmptyElementTag() throws XmlPullParserException;

    boolean isWhitespace() throws XmlPullParserException;

    int next() throws XmlPullParserException, IOException;

    int nextTag() throws XmlPullParserException, IOException;

    String nextText() throws XmlPullParserException, IOException;

    int nextToken() throws XmlPullParserException, IOException;

    void require(int i2, String str, String str2) throws XmlPullParserException, IOException;

    void setFeature(String str, boolean z) throws XmlPullParserException;

    void setInput(InputStream inputStream, String str) throws XmlPullParserException;

    void setInput(Reader reader) throws XmlPullParserException;

    void setProperty(String str, Object obj) throws XmlPullParserException;
}
