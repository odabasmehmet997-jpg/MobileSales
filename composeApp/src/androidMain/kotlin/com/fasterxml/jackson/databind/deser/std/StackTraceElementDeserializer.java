package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;

import java.io.IOException;
import java.util.Set;

public class StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
    private static final long serialVersionUID = 1;
    public StackTraceElementDeserializer() {
        super(StackTraceElement.class);
    }
    public StackTraceElement deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        int i_parseIntPrimitive;
        final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (JsonToken.START_OBJECT == jsonTokenCurrentToken) {
            String text = null;
            String text2 = null;
            String text3 = null;
            String text4 = "";
            String text5 = text4;
            String text6 = text5;
            int i2 = -1;
            while (true) {
                final JsonToken jsonTokenNextValue = jsonParser.nextValue();
                if (JsonToken.END_OBJECT != jsonTokenNextValue) {
                    final String strCurrentName = jsonParser.currentName();
                    if ("className".equals(strCurrentName)) {
                        text4 = jsonParser.getText();
                    } else if ("classLoaderName".equals(strCurrentName)) {
                        text3 = jsonParser.getText();
                    } else if ("fileName".equals(strCurrentName)) {
                        text6 = jsonParser.getText();
                    } else if ("lineNumber".equals(strCurrentName)) {
                        if (jsonTokenNextValue.isNumeric()) {
                            i_parseIntPrimitive = jsonParser.getIntValue();
                        } else {
                            i_parseIntPrimitive = this._parseIntPrimitive(jsonParser, deserializationContext);
                        }
                        i2 = i_parseIntPrimitive;
                    } else if ("methodName".equals(strCurrentName)) {
                        text5 = jsonParser.getText();
                    } else if (!"nativeMethod".equals(strCurrentName)) {
                        if ("moduleName".equals(strCurrentName)) {
                            text = jsonParser.getText();
                        } else if ("moduleVersion".equals(strCurrentName)) {
                            text2 = jsonParser.getText();
                        } else if (!"declaringClass".equals(strCurrentName) && !"format".equals(strCurrentName)) {
                            this.handleUnknownProperty(jsonParser, deserializationContext, _valueClass, strCurrentName);
                        }
                    }
                    jsonParser.skipChildren();
                } else {
                    return this.constructValue(deserializationContext, text4, text5, text6, i2, text, text2, text3);
                }
            }
        } else {
            if (JsonToken.START_ARRAY == jsonTokenCurrentToken && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                final StackTraceElement stackTraceElementDeserialize = this.deserialize(jsonParser, deserializationContext);
                if (JsonToken.END_ARRAY != jsonParser.nextToken()) {
                    this.handleMissingEndArrayForSingle(jsonParser, deserializationContext);
                }
                return stackTraceElementDeserialize;
            }
            return (StackTraceElement) deserializationContext.handleUnexpectedToken(_valueClass, jsonParser);
        }
    }
    protected StackTraceElement constructValue(final DeserializationContext deserializationContext, final String str, final String str2, final String str3, final int i2, final String str4, final String str5) {
        return this.constructValue(deserializationContext, str, str2, str3, i2, str4, str5, null);
    }
    protected StackTraceElement constructValue(final DeserializationContext deserializationContext, final String str, final String str2, final String str3, final int i2, final String str4, final String str5, final String str6) {
        return new StackTraceElement(str, str2, str3, i2);
    }
    protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return null;
    }
}
