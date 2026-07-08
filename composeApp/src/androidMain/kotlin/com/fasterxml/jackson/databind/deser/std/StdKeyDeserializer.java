package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.*;

public class StdKeyDeserializer extends KeyDeserializer implements Serializable {
    public static final int TYPE_BOOLEAN = 1;
    public static final int TYPE_BYTE = 2;
    public static final int TYPE_BYTE_ARRAY = 17;
    public static final int TYPE_CALENDAR = 11;
    public static final int TYPE_CHAR = 4;
    public static final int TYPE_CLASS = 15;
    public static final int TYPE_CURRENCY = 16;
    public static final int TYPE_DATE = 10;
    public static final int TYPE_DOUBLE = 8;
    public static final int TYPE_FLOAT = 7;
    public static final int TYPE_INT = 5;
    public static final int TYPE_LOCALE = 9;
    public static final int TYPE_LONG = 6;
    public static final int TYPE_SHORT = 3;
    public static final int TYPE_URI = 13;
    public static final int TYPE_URL = 14;
    public static final int TYPE_UUID = 12;
    private static final long serialVersionUID = 1;
    protected final FromStringDeserializer<?> _deser;
    protected final Class<?> _keyClass;
    protected final int _kind;

    protected StdKeyDeserializer(final int i2, final Class<?> cls) {
        this(i2, cls, null);
    }

    protected StdKeyDeserializer(final int i2, final Class<?> cls, final FromStringDeserializer<?> fromStringDeserializer) {
        _kind = i2;
        _keyClass = cls;
        _deser = fromStringDeserializer;
    }

    public static StdKeyDeserializer forType(final Class<?> cls) {
        final int i2;
        if (String.class == cls || Object.class == cls || CharSequence.class == cls || Serializable.class == cls) {
            return StringKD.forType(cls);
        }
        if (UUID.class == cls) {
            i2 = 12;
        } else if (Integer.class == cls) {
            i2 = 5;
        } else if (Long.class == cls) {
            i2 = 6;
        } else if (Date.class == cls) {
            i2 = 10;
        } else if (Calendar.class == cls) {
            i2 = 11;
        } else if (Boolean.class == cls) {
            i2 = 1;
        } else if (Byte.class == cls) {
            i2 = 2;
        } else if (Character.class == cls) {
            i2 = 4;
        } else if (Short.class == cls) {
            i2 = 3;
        } else if (Float.class == cls) {
            i2 = 7;
        } else if (Double.class == cls) {
            i2 = 8;
        } else if (URI.class == cls) {
            i2 = 13;
        } else if (URL.class == cls) {
            i2 = 14;
        } else if (Class.class == cls) {
            i2 = 15;
        } else {
            if (Locale.class == cls) {
                return new StdKeyDeserializer(9, cls, FromStringDeserializer.findDeserializer(Locale.class));
            }
            if (Currency.class == cls) {
                return new StdKeyDeserializer(16, cls, FromStringDeserializer.findDeserializer(Currency.class));
            }
            if (byte[].class != cls) {
                return null;
            }
            i2 = 17;
        }
        return new StdKeyDeserializer(i2, cls);
    }

    public Object deserializeKey(final String str, final DeserializationContext deserializationContext) throws IOException {
        if (null == str) {
            return null;
        }
        try {
            final Object obj_parse = this._parse(str, deserializationContext);
            if (null != obj_parse) {
                return obj_parse;
            }
            if (ClassUtil.isEnumType(_keyClass) && deserializationContext.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return null;
            }
            return deserializationContext.handleWeirdKey(_keyClass, str, "not a valid representation");
        } catch (final Exception e2) {
            return deserializationContext.handleWeirdKey(_keyClass, str, "not a valid representation, problem: (%s) %s", e2.getClass().getName(), ClassUtil.exceptionMessage(e2));
        }
    }

    public Class<?> getKeyClass() {
        return _keyClass;
    }

    protected Object _parse(final String str, final DeserializationContext deserializationContext) throws Exception {
        switch (_kind) {
            case 1:
                if ("true".equals(str)) {
                    return Boolean.TRUE;
                }
                if ("false".equals(str)) {
                    return Boolean.FALSE;
                }
                return deserializationContext.handleWeirdKey(_keyClass, str, "value not 'true' or 'false'");
            case 2:
                final int i_parseInt = this._parseInt(str);
                if (-128 > i_parseInt || 255 < i_parseInt) {
                    return deserializationContext.handleWeirdKey(_keyClass, str, "overflow, value cannot be represented as 8-bit value");
                }
                return Byte.valueOf((byte) i_parseInt);
            case 3:
                final int i_parseInt2 = this._parseInt(str);
                if (-32768 > i_parseInt2 || 32767 < i_parseInt2) {
                    return deserializationContext.handleWeirdKey(_keyClass, str, "overflow, value cannot be represented as 16-bit value");
                }
                return Short.valueOf((short) i_parseInt2);
            case 4:
                if (1 == str.length()) {
                    return Character.valueOf(str.charAt(0));
                }
                return deserializationContext.handleWeirdKey(_keyClass, str, "can only convert 1-character Strings");
            case 5:
                return Integer.valueOf(this._parseInt(str));
            case 6:
                return Long.valueOf(this._parseLong(str));
            case 7:
                return Float.valueOf((float) this._parseDouble(str));
            case 8:
                return Double.valueOf(this._parseDouble(str));
            case 9:
                try {
                    return _deser._deserialize(str, deserializationContext);
                } catch (final IllegalArgumentException e2) {
                    return this._weirdKey(deserializationContext, str, e2);
                }
            case 10:
                return deserializationContext.parseDate(str);
            case 11:
                return deserializationContext.constructCalendar(deserializationContext.parseDate(str));
            case 12:
                try {
                    return UUID.fromString(str);
                } catch (final Exception e3) {
                    return this._weirdKey(deserializationContext, str, e3);
                }
            case 13:
                try {
                    return URI.create(str);
                } catch (final Exception e4) {
                    return this._weirdKey(deserializationContext, str, e4);
                }
            case 14:
                try {
                    return new URL(str);
                } catch (final MalformedURLException e5) {
                    return this._weirdKey(deserializationContext, str, e5);
                }
            case 15:
                try {
                    return deserializationContext.findClass(str);
                } catch (final Exception unused) {
                    return deserializationContext.handleWeirdKey(_keyClass, str, "unable to parse key as Class");
                }
            case 16:
                try {
                    return _deser._deserialize(str, deserializationContext);
                } catch (final IllegalArgumentException e6) {
                    return this._weirdKey(deserializationContext, str, e6);
                }
            case 17:
                try {
                    return deserializationContext.getConfig().getBase64Variant().decode(str);
                } catch (final IllegalArgumentException e7) {
                    return this._weirdKey(deserializationContext, str, e7);
                }
            default:
                throw new IllegalStateException("Internal error: unknown key type " + _keyClass);
        }
    }

    protected int _parseInt(final String str) throws IllegalArgumentException {
        return Integer.parseInt(str);
    }

    protected long _parseLong(final String str) throws IllegalArgumentException {
        return Long.parseLong(str);
    }

    protected double _parseDouble(final String str) throws IllegalArgumentException {
        return NumberInput.parseDouble(str);
    }

    protected Object _weirdKey(final DeserializationContext deserializationContext, final String str, final Exception exc) throws IOException {
        return deserializationContext.handleWeirdKey(_keyClass, str, "problem: %s", ClassUtil.exceptionMessage(exc));
    }

    static final class StringKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        private static final StringKD sString = new StringKD(String.class);
        private static final StringKD sObject = new StringKD(Object.class);

        public Object deserializeKey(final String str, final DeserializationContext deserializationContext) throws IOException {
            return str;
        }

        private StringKD(final Class<?> cls) {
            super(-1, cls);
        }

        public static StringKD forType(final Class<?> cls) {
            if (String.class == cls) {
                return StringKD.sString;
            }
            if (Object.class == cls) {
                return StringKD.sObject;
            }
            return new StringKD(cls);
        }
    }

    static final class DelegatingKD extends KeyDeserializer implements Serializable {
        private static final long serialVersionUID = 1;
        private final JsonDeserializer<?> _delegate;
        private final Class<?> _keyClass;

        DelegatingKD(final Class<?> cls, final JsonDeserializer<?> jsonDeserializer) {
            _keyClass = cls;
            _delegate = jsonDeserializer;
        }
        public Object deserializeKey(final String str, final DeserializationContext deserializationContext) throws IOException {
            if (null == str) {
                return null;
            }
            final TokenBuffer tokenBuffer = new TokenBuffer(deserializationContext.getParser(), deserializationContext);
            tokenBuffer.writeString(str);
            try {
                final JsonParser jsonParserAsParser = tokenBuffer.asParser();
                jsonParserAsParser.nextToken();
                final Object objDeserialize = _delegate.deserialize(jsonParserAsParser, deserializationContext);
                return null != objDeserialize ? objDeserialize : deserializationContext.handleWeirdKey(_keyClass, str, "not a valid representation");
            } catch (final Exception e2) {
                return deserializationContext.handleWeirdKey(_keyClass, str, "not a valid representation: %s", e2.getMessage());
            }
        }

        public Class<?> getKeyClass() {
            return _keyClass;
        }
    }
    static final class EnumKD extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        private final EnumResolver _byNameResolver;
        private EnumResolver _byToStringResolver;
        private final Enum<?> _enumDefaultValue;
        private final AnnotatedMethod _factory;

        EnumKD(final EnumResolver enumResolver, final AnnotatedMethod annotatedMethod) {
            super(-1, enumResolver.getEnumClass());
            _byNameResolver = enumResolver;
            _factory = annotatedMethod;
            _enumDefaultValue = enumResolver.getDefaultValue();
        }

        @Override // com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer
        public Object _parse(final String str, final DeserializationContext deserializationContext) throws IOException {
            final AnnotatedMethod annotatedMethod = _factory;
            if (null != annotatedMethod) {
                try {
                    return annotatedMethod.call1(str);
                } catch (final Exception e2) {
                    ClassUtil.unwrapAndThrowAsIAE(e2);
                }
            }
            final EnumResolver enumResolver_getToStringResolver = deserializationContext.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING) ? this._getToStringResolver(deserializationContext) : _byNameResolver;
            final Enum<?> enumFindEnum = enumResolver_getToStringResolver.findEnum(str);
            if (null != enumFindEnum) {
                return enumFindEnum;
            }
            if (null == this._enumDefaultValue || !deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)) {
                return !deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL) ? deserializationContext.handleWeirdKey(_keyClass, str, "not one of the values accepted for Enum class: %s", enumResolver_getToStringResolver.getEnumIds()) : enumFindEnum;
            }
            return _enumDefaultValue;
        }

        private EnumResolver _getToStringResolver(final DeserializationContext deserializationContext) {
            EnumResolver enumResolverConstructUsingToString = _byToStringResolver;
            if (null == enumResolverConstructUsingToString) {
                synchronized (this) {
                    enumResolverConstructUsingToString = EnumResolver.constructUsingToString(deserializationContext.getConfig(), _byNameResolver.getEnumClass());
                    _byToStringResolver = enumResolverConstructUsingToString;
                }
            }
            return enumResolverConstructUsingToString;
        }
    }

    static final class StringCtorKeyDeserializer extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        private final Constructor<?> _ctor;

        public StringCtorKeyDeserializer(final Constructor<?> constructor) {
            super(-1, constructor.getDeclaringClass());
            _ctor = constructor;
        }
        public Object _parse(final String str, final DeserializationContext deserializationContext) throws Exception {
            return _ctor.newInstance(str);
        }
    }

    static final class StringFactoryKeyDeserializer extends StdKeyDeserializer {
        private static final long serialVersionUID = 1;
        final Method _factoryMethod;

        public StringFactoryKeyDeserializer(final Method method) {
            super(-1, method.getDeclaringClass());
            _factoryMethod = method;
        }
        public Object _parse(final String str, final DeserializationContext deserializationContext) throws Exception {
            return _factoryMethod.invoke(null, str);
        }
    }
}
