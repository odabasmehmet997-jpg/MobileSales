package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import java.io.IOException;

public abstract class TypeDeserializer {
    public abstract Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    public abstract Object deserializeTypedFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    public abstract Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    public abstract Object deserializeTypedFromScalar(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    public abstract TypeDeserializer forProperty(BeanProperty beanProperty);

    public abstract Class<?> getDefaultImpl();

    public abstract String getPropertyName();

    public abstract TypeIdResolver getTypeIdResolver();

    public abstract JsonTypeInfo.EnumC1184As getTypeInclusion();

    public boolean hasDefaultImpl() {
        return null != getDefaultImpl();
    }

    public static Object deserializeIfNatural(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JavaType javaType) throws IOException {
        return TypeDeserializer.deserializeIfNatural(jsonParser, deserializationContext, javaType.getRawClass());
    }

    public static Object deserializeIfNatural(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Class<?> cls) throws IOException {
        final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (null == jsonTokenCurrentToken) {
            return null;
        }
        final int i2 = C12091.SwitchMapcomfasterxmljacksoncoreJsonToken[jsonTokenCurrentToken.ordinal()];
        if (1 != i2) {
            if (2 != i2) {
                if (3 != i2) {
                    if (4 == i2) {
                        if (cls.isAssignableFrom(Boolean.class)) {
                            return Boolean.TRUE;
                        }
                    } else if (5 == i2 && cls.isAssignableFrom(Boolean.class)) {
                        return Boolean.FALSE;
                    }
                } else if (cls.isAssignableFrom(Double.class)) {
                    return Double.valueOf(jsonParser.getDoubleValue());
                }
            } else if (cls.isAssignableFrom(Integer.class)) {
                return Integer.valueOf(jsonParser.getIntValue());
            }
        } else if (cls.isAssignableFrom(String.class)) {
            return jsonParser.getText();
        }
        return null;
    }

    enum C12091 {
        ;
        static final int[] SwitchMapcomfasterxmljacksoncoreJsonToken;

        static {
            final int[] iArr = new int[JsonToken.values().length];
            SwitchMapcomfasterxmljacksoncoreJsonToken = iArr;
            try {
                iArr[JsonToken.VALUE_STRING.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C12091.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C12091.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C12091.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_TRUE.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                C12091.SwitchMapcomfasterxmljacksoncoreJsonToken[JsonToken.VALUE_FALSE.ordinal()] = 5;
            } catch (final NoSuchFieldError unused5) {
            }
        }
    }
}
