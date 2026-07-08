package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.NullValueProvider;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ClassUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class ContainerDeserializerBase<T> extends StdDeserializer<T> {
    protected final JavaType _containerType;
    protected final NullValueProvider _nullProvider;
    protected final boolean _skipNullValues;
    protected final Boolean _unwrapSingle;

    public abstract JsonDeserializer<Object> getContentDeserializer();

    protected ContainerDeserializerBase(final JavaType javaType, final NullValueProvider nullValueProvider, final Boolean bool) {
        super(javaType);
        _containerType = javaType;
        _unwrapSingle = bool;
        _nullProvider = nullValueProvider;
        _skipNullValues = NullsConstantProvider.isSkipper(nullValueProvider);
    }

    protected ContainerDeserializerBase(final JavaType javaType) {
        this(javaType, null, null);
    }

    protected ContainerDeserializerBase(final ContainerDeserializerBase<?> containerDeserializerBase) {
        this(containerDeserializerBase, containerDeserializerBase._nullProvider, containerDeserializerBase._unwrapSingle);
    }

    protected ContainerDeserializerBase(final ContainerDeserializerBase<?> containerDeserializerBase, final NullValueProvider nullValueProvider, final Boolean bool) {
        super(containerDeserializerBase._containerType);
        _containerType = containerDeserializerBase._containerType;
        _nullProvider = nullValueProvider;
        _unwrapSingle = bool;
        _skipNullValues = NullsConstantProvider.isSkipper(nullValueProvider);
    }
    public JavaType getValueType() {
        return _containerType;
    }
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return Boolean.TRUE;
    }

    public SettableBeanProperty findBackReference(final String str) {
        final JsonDeserializer<Object> contentDeserializer = this.getContentDeserializer();
        if (null == contentDeserializer) {
            throw new IllegalArgumentException(String.format("Cannot handle managed/back reference '%s': type: container deserializer of type %s returned null for 'getContentDeserializer()'", str, this.getClass().getName()));
        }
        return contentDeserializer.findBackReference(str);
    }

    public JavaType getContentType() {
        final JavaType javaType = _containerType;
        if (null == javaType) {
            return TypeFactory.unknownType();
        }
        return javaType.getContentType();
    }
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.DYNAMIC;
    }
    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        final ValueInstantiator valueInstantiator = this.getValueInstantiator();
        if (null == valueInstantiator || !valueInstantiator.canCreateUsingDefault()) {
            final JavaType valueType = this.getValueType();
            deserializationContext.reportBadDefinition(valueType, String.format("Cannot create empty instance of %s, no default Creator", valueType));
        }
        try {
            return valueInstantiator.createUsingDefault(deserializationContext);
        } catch (final IOException e2) {
            return ClassUtil.throwAsMappingException(deserializationContext, e2);
        }
    }
    protected <BOGUS> BOGUS wrapAndThrow(final Throwable th, final Object obj, final String str) throws IOException {
        return this.wrapAndThrow(null, th, obj, str);
    }

    protected <BOGUS> BOGUS wrapAndThrow(final DeserializationContext deserializationContext, Throwable th, final Object obj, final String str) throws IOException {
        while ((th instanceof InvocationTargetException) && null != th.getCause()) {
            th = th.getCause();
        }
        ClassUtil.throwIfError(th);
        if (null != deserializationContext && !deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS)) {
            ClassUtil.throwIfRTE(th);
        }
        if ((th instanceof IOException) && !(th instanceof JsonMappingException)) {
            throw ((IOException) th);
        }
        throw JsonMappingException.wrapWithPath(th, obj, ClassUtil.nonNull(str, "N/A"));
    }
}
