package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.ClassUtil;

import java.io.IOException;

class FactoryBasedEnumDeserializer extends StdDeserializer<Object> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final SettableBeanProperty[] _creatorProps;
    protected final JsonDeserializer<?> _deser;
    protected final AnnotatedMethod _factory;
    protected final boolean _hasArgs;
    protected final JavaType _inputType;
    private transient PropertyBasedCreator _propCreator;
    protected final ValueInstantiator _valueInstantiator;
    public boolean isCachable() {
        return true;
    }

    public FactoryBasedEnumDeserializer(final Class<?> cls, final AnnotatedMethod annotatedMethod, final JavaType javaType, final ValueInstantiator valueInstantiator, final SettableBeanProperty[] settableBeanPropertyArr) {
        super(cls);
        _factory = annotatedMethod;
        _hasArgs = true;
        _inputType = javaType.hasRawClass(String.class) ? null : javaType;
        _deser = null;
        _valueInstantiator = valueInstantiator;
        _creatorProps = settableBeanPropertyArr;
    }

    public FactoryBasedEnumDeserializer(final Class<?> cls, final AnnotatedMethod annotatedMethod) {
        super(cls);
        _factory = annotatedMethod;
        _hasArgs = false;
        _inputType = null;
        _deser = null;
        _valueInstantiator = null;
        _creatorProps = null;
    }

    protected FactoryBasedEnumDeserializer(final FactoryBasedEnumDeserializer factoryBasedEnumDeserializer, final JsonDeserializer<?> jsonDeserializer) {
        super(factoryBasedEnumDeserializer._valueClass);
        _inputType = factoryBasedEnumDeserializer._inputType;
        _factory = factoryBasedEnumDeserializer._factory;
        _hasArgs = factoryBasedEnumDeserializer._hasArgs;
        _valueInstantiator = factoryBasedEnumDeserializer._valueInstantiator;
        _creatorProps = factoryBasedEnumDeserializer._creatorProps;
        _deser = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.deser.ContextualDeserializer
    public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        final JavaType javaType;
        return (null == this._deser && null != (javaType = this._inputType) && null == this._creatorProps) ? new FactoryBasedEnumDeserializer(this, deserializationContext.findContextualValueDeserializer(javaType, beanProperty)) : this;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return Boolean.FALSE;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Enum;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer
    public ValueInstantiator getValueInstantiator() {
        return _valueInstantiator;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final Object text;
        final JsonDeserializer<?> jsonDeserializer = _deser;
        if (null != jsonDeserializer) {
            text = jsonDeserializer.deserialize(jsonParser, deserializationContext);
        } else if (_hasArgs) {
            final JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            if (null != this._creatorProps) {
                if (!jsonParser.isExpectedStartObjectToken()) {
                    final JavaType valueType = this.getValueType(deserializationContext);
                    deserializationContext.reportInputMismatch(valueType, "Input mismatch reading Enum %s: properties-based `@JsonCreator` (%s) expects JSON Object (JsonToken.START_OBJECT), got JsonToken.%s", ClassUtil.getTypeDescription(valueType), _factory, jsonParser.currentToken());
                }
                if (null == this._propCreator) {
                    _propCreator = PropertyBasedCreator.construct(deserializationContext, _valueInstantiator, _creatorProps, deserializationContext.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
                }
                jsonParser.nextToken();
                return this.deserializeEnumUsingPropertyBased(jsonParser, deserializationContext, _propCreator);
            }
            if (JsonToken.VALUE_STRING == jsonTokenCurrentToken || JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
                text = jsonParser.getText();
            } else if (JsonToken.VALUE_NUMBER_INT == jsonTokenCurrentToken) {
                text = jsonParser.getNumberValue();
            } else {
                text = jsonParser.getValueAsString();
            }
        } else {
            jsonParser.skipChildren();
            try {
                return _factory.call();
            } catch (final Exception e2) {
                return deserializationContext.handleInstantiationProblem(_valueClass, null, ClassUtil.throwRootCauseIfIOE(e2));
            }
        }
        try {
            return _factory.callOnWith(_valueClass, text);
        } catch (final Exception e3) {
            final Throwable thThrowRootCauseIfIOE = ClassUtil.throwRootCauseIfIOE(e3);
            if (deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL) && (thThrowRootCauseIfIOE instanceof IllegalArgumentException)) {
                return null;
            }
            return deserializationContext.handleInstantiationProblem(_valueClass, text, thThrowRootCauseIfIOE);
        }
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        if (null == this._deser) {
            return this.deserialize(jsonParser, deserializationContext);
        }
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    protected Object deserializeEnumUsingPropertyBased(final JsonParser jsonParser, final DeserializationContext deserializationContext, final PropertyBasedCreator propertyBasedCreator) throws IOException {
        final PropertyValueBuffer propertyValueBufferStartBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, null);
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        while (JsonToken.FIELD_NAME == jsonTokenCurrentToken) {
            final String strCurrentName = jsonParser.currentName();
            jsonParser.nextToken();
            final SettableBeanProperty settableBeanPropertyFindCreatorProperty = propertyBasedCreator.findCreatorProperty(strCurrentName);
            if ((!propertyValueBufferStartBuilding.readIdProperty(strCurrentName) || null != settableBeanPropertyFindCreatorProperty) && null != settableBeanPropertyFindCreatorProperty) {
                propertyValueBufferStartBuilding.assignParameter(settableBeanPropertyFindCreatorProperty, this._deserializeWithErrorWrapping(jsonParser, deserializationContext, settableBeanPropertyFindCreatorProperty));
            }
            jsonTokenCurrentToken = jsonParser.nextToken();
        }
        return propertyBasedCreator.build(deserializationContext, propertyValueBufferStartBuilding);
    }

    protected final Object _deserializeWithErrorWrapping(final JsonParser jsonParser, final DeserializationContext deserializationContext, final SettableBeanProperty settableBeanProperty) throws IOException {
        try {
            return settableBeanProperty.deserialize(jsonParser, deserializationContext);
        } catch (final Exception e2) {
            return this.wrapAndThrow(e2, this.handledType(), settableBeanProperty.getName(), deserializationContext);
        }
    }

    protected Object wrapAndThrow(final Throwable th, final Object obj, final String str, final DeserializationContext deserializationContext) throws IOException {
        throw JsonMappingException.wrapWithPath(this.throwOrReturnThrowable(th, deserializationContext), obj, str);
    }

    private Throwable throwOrReturnThrowable(final Throwable th, final DeserializationContext deserializationContext) throws IOException {
        final Throwable rootCause = ClassUtil.getRootCause(th);
        ClassUtil.throwIfError(rootCause);
        final boolean z = null == deserializationContext || deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
        if (rootCause instanceof IOException) {
            if (!z || !(rootCause instanceof JsonProcessingException)) {
                throw ((IOException) rootCause);
            }
        } else if (!z) {
            ClassUtil.throwIfRTE(rootCause);
        }
        return rootCause;
    }
}
