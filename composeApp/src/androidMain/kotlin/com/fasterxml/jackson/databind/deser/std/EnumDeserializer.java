package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.CompactStringObjectMap;
import com.fasterxml.jackson.databind.util.EnumResolver;

import java.io.IOException;
import java.util.Objects;

public class EnumDeserializer extends StdScalarDeserializer<Object> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final Boolean _caseInsensitive;
    private final Enum<?> _enumDefaultValue;
    protected Object[] _enumsByIndex;
    protected final CompactStringObjectMap _lookupByName;
    protected CompactStringObjectMap _lookupByToString;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return true;
    }

    public EnumDeserializer(final EnumResolver enumResolver, final Boolean bool) {
        super(enumResolver.getEnumClass());
        _lookupByName = enumResolver.constructLookup();
        _enumsByIndex = enumResolver.getRawEnums();
        _enumDefaultValue = enumResolver.getDefaultValue();
        _caseInsensitive = bool;
    }

    protected EnumDeserializer(final EnumDeserializer enumDeserializer, final Boolean bool) {
        super(enumDeserializer);
        _lookupByName = enumDeserializer._lookupByName;
        _enumsByIndex = enumDeserializer._enumsByIndex;
        _enumDefaultValue = enumDeserializer._enumDefaultValue;
        _caseInsensitive = bool;
    }

    @Deprecated
    public EnumDeserializer(final EnumResolver enumResolver) {
        this(enumResolver, null);
    }

    @Deprecated
    public static JsonDeserializer<?> deserializerForCreator(final DeserializationConfig deserializationConfig, final Class<?> cls, final AnnotatedMethod annotatedMethod) {
        return EnumDeserializer.deserializerForCreator(deserializationConfig, cls, annotatedMethod, null, null);
    }

    public static JsonDeserializer<?> deserializerForCreator(final DeserializationConfig deserializationConfig, final Class<?> cls, final AnnotatedMethod annotatedMethod, final ValueInstantiator valueInstantiator, final SettableBeanProperty[] settableBeanPropertyArr) {
        if (deserializationConfig.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(annotatedMethod.getMember(), deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        return new FactoryBasedEnumDeserializer(cls, annotatedMethod, annotatedMethod.getParameterType(0), valueInstantiator, settableBeanPropertyArr);
    }

    public static JsonDeserializer<?> deserializerForNoArgsCreator(final DeserializationConfig deserializationConfig, final Class<?> cls, final AnnotatedMethod annotatedMethod) {
        if (deserializationConfig.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(annotatedMethod.getMember(), deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        return new FactoryBasedEnumDeserializer(cls, annotatedMethod);
    }

    public EnumDeserializer withResolved(final Boolean bool) {
        return Objects.equals(_caseInsensitive, bool) ? this : new EnumDeserializer(this, bool);
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        Boolean boolFindFormatFeature = this.findFormatFeature(deserializationContext, beanProperty, this.handledType(), JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        if (null == boolFindFormatFeature) {
            boolFindFormatFeature = _caseInsensitive;
        }
        return this.withResolved(boolFindFormatFeature);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Enum;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        return _enumDefaultValue;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            return this._fromString(jsonParser, deserializationContext, jsonParser.getText());
        }
        if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
            return this._fromInteger(jsonParser, deserializationContext, jsonParser.getIntValue());
        }
        if (jsonParser.isExpectedStartObjectToken()) {
            return this._fromString(jsonParser, deserializationContext, deserializationContext.extractScalarFromObject(jsonParser, this, _valueClass));
        }
        return this._deserializeOther(jsonParser, deserializationContext);
    }

    protected Object _fromString(final JsonParser jsonParser, final DeserializationContext deserializationContext, final String str) throws IOException {
        final Object objFind;
        final CompactStringObjectMap compactStringObjectMap_getToStringLookup = deserializationContext.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING) ? this._getToStringLookup(deserializationContext) : _lookupByName;
        final Object objFind2 = compactStringObjectMap_getToStringLookup.find(str);
        if (null != objFind2) {
            return objFind2;
        }
        final String strTrim = str.trim();
        return (strTrim == str || null == (objFind = compactStringObjectMap_getToStringLookup.find(strTrim))) ? this._deserializeAltString(jsonParser, deserializationContext, compactStringObjectMap_getToStringLookup, strTrim) : objFind;
    }

    protected Object _fromInteger(final JsonParser jsonParser, final DeserializationContext deserializationContext, final int i2) throws IOException {
        final CoercionAction coercionActionFindCoercionAction = deserializationContext.findCoercionAction(this.logicalType(), this.handledType(), CoercionInputShape.Integer);
        if (CoercionAction.Fail == coercionActionFindCoercionAction) {
            if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
                return deserializationContext.handleWeirdNumberValue(this._enumClass(), Integer.valueOf(i2), "not allowed to deserialize Enum value out of number: disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow");
            }
            this._checkCoercionFail(deserializationContext, CoercionAction.Fail, this.handledType(), Integer.valueOf(i2), "Integer value (" + i2 + ")");
        }
        final int i3 = C11971.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[coercionActionFindCoercionAction.ordinal()];
        if (1 == i3) {
            return null;
        }
        if (2 == i3) {
            return this.getEmptyValue(deserializationContext);
        }
        if (0 <= i2) {
            final Object[] objArr = _enumsByIndex;
            if (i2 < objArr.length) {
                return objArr[i2];
            }
        }
        if (null != this._enumDefaultValue && deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)) {
            return _enumDefaultValue;
        }
        if (deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
            return null;
        }
        return deserializationContext.handleWeirdNumberValue(this._enumClass(), Integer.valueOf(i2), "index value outside legal index range [0..%s]", Integer.valueOf(_enumsByIndex.length - 1));
    }

    /* renamed from: com.fasterxml.jackson.databind.deser.std.EnumDeserializer1 */
    enum C11971 {
        ;
        static final int[] SwitchMapcomfasterxmljacksondatabindcfgCoercionAction;

        static {
            final int[] iArr = new int[CoercionAction.values().length];
            SwitchMapcomfasterxmljacksondatabindcfgCoercionAction = iArr;
            try {
                iArr[CoercionAction.AsNull.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C11971.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[CoercionAction.AsEmpty.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C11971.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[CoercionAction.TryConvert.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
        }
    }

    private final Object _deserializeAltString(final JsonParser jsonParser, final DeserializationContext deserializationContext, final CompactStringObjectMap compactStringObjectMap, final String str) throws IOException, NumberFormatException {
        final char cCharAt;
        final CoercionAction coercionAction_checkCoercionFail;
        final String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            if (str.isEmpty()) {
                coercionAction_checkCoercionFail = this._checkCoercionFail(deserializationContext, this._findCoercionFromEmptyString(deserializationContext), this.handledType(), str, "empty String (\"\")");
            } else {
                coercionAction_checkCoercionFail = this._checkCoercionFail(deserializationContext, this._findCoercionFromBlankString(deserializationContext), this.handledType(), str, "blank String (all whitespace)");
            }
            final int i2 = C11971.SwitchMapcomfasterxmljacksondatabindcfgCoercionAction[coercionAction_checkCoercionFail.ordinal()];
            if (2 == i2 || 3 == i2) {
                return this.getEmptyValue(deserializationContext);
            }
            return null;
        }
        if (Boolean.TRUE.equals(_caseInsensitive)) {
            final Object objFindCaseInsensitive = compactStringObjectMap.findCaseInsensitive(strTrim);
            if (null != objFindCaseInsensitive) {
                return objFindCaseInsensitive;
            }
        } else if (!deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS) && '0' <= (cCharAt = strTrim.charAt(0)) && '9' >= cCharAt) {
            try {
                final int i3 = Integer.parseInt(strTrim);
                if (!deserializationContext.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
                    return deserializationContext.handleWeirdStringValue(this._enumClass(), strTrim, "value looks like quoted Enum index, but `MapperFeature.ALLOW_COERCION_OF_SCALARS` prevents use");
                }
                if (0 <= i3) {
                    final Object[] objArr = _enumsByIndex;
                    if (i3 < objArr.length) {
                        return objArr[i3];
                    }
                }
            } catch (final NumberFormatException unused) {
            }
        }
        if (null != this._enumDefaultValue && deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)) {
            return _enumDefaultValue;
        }
        if (deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
            return null;
        }
        return deserializationContext.handleWeirdStringValue(this._enumClass(), strTrim, "not one of the values accepted for Enum class: %s", compactStringObjectMap.keys());
    }

    protected Object _deserializeOther(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.START_ARRAY)) {
            return this._deserializeFromArray(jsonParser, deserializationContext);
        }
        return deserializationContext.handleUnexpectedToken(this._enumClass(), jsonParser);
    }

    protected Class<?> _enumClass() {
        return this.handledType();
    }

    protected CompactStringObjectMap _getToStringLookup(final DeserializationContext deserializationContext) {
        CompactStringObjectMap compactStringObjectMapConstructLookup = _lookupByToString;
        if (null == compactStringObjectMapConstructLookup) {
            synchronized (this) {
                compactStringObjectMapConstructLookup = EnumResolver.constructUsingToString(deserializationContext.getConfig(), this._enumClass()).constructLookup();
            }
            _lookupByToString = compactStringObjectMapConstructLookup;
        }
        return compactStringObjectMapConstructLookup;
    }
}
