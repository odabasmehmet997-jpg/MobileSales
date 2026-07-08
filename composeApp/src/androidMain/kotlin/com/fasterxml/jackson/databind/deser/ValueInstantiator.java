package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.type.LogicalType;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class ValueInstantiator {
    public boolean canCreateFromBigDecimal() {
        return false;
    }
    public boolean canCreateFromBigInteger() {
        return false;
    }
    public boolean canCreateFromBoolean() {
        return false;
    }
    public boolean canCreateFromDouble() {
        return false;
    }
    public boolean canCreateFromInt() {
        return false;
    }
    public boolean canCreateFromLong() {
        return false;
    }
    public boolean canCreateFromObjectWith() {
        return false;
    }
    public boolean canCreateFromString() {
        return false;
    }
    public boolean canCreateUsingArrayDelegate() {
        return false;
    }
    public boolean canCreateUsingDelegate() {
        return false;
    }
    public ValueInstantiator createContextual(final DeserializationContext deserializationContext, final BeanDescription beanDescription) throws JsonMappingException {
        return this;
    }
    public AnnotatedWithParams getArrayDelegateCreator() {
        return null;
    }
    public JavaType getArrayDelegateType(final DeserializationConfig deserializationConfig) {
        return null;
    }
    public AnnotatedWithParams getDefaultCreator() {
        return null;
    }
    public AnnotatedWithParams getDelegateCreator() {
        return null;
    }
    public JavaType getDelegateType(final DeserializationConfig deserializationConfig) {
        return null;
    }
    public SettableBeanProperty[] getFromObjectArguments(final DeserializationConfig deserializationConfig) {
        return null;
    }
    public AnnotatedWithParams getWithArgsCreator() {
        return null;
    }
    public Class<?> getValueClass() {
        return Object.class;
    }
    public String getValueTypeDesc() {
        final Class<?> valueClass = this.getValueClass();
        if (null == valueClass) {
            return "UNKNOWN";
        }
        return valueClass.getName();
    }
    public boolean canInstantiate() {
        return this.canCreateUsingDefault() || this.canCreateUsingDelegate() || this.canCreateUsingArrayDelegate() || this.canCreateFromObjectWith() || this.canCreateFromString() || this.canCreateFromInt() || this.canCreateFromLong() || this.canCreateFromDouble() || this.canCreateFromBoolean();
    }
    public boolean canCreateUsingDefault() {
        return null != getDefaultCreator();
    }
    public Object createUsingDefault(final DeserializationContext deserializationContext) throws IOException {
        return deserializationContext.handleMissingInstantiator(this.getValueClass(), this, null, "no default no-arguments constructor found");
    }
    public Object createFromObjectWith(final DeserializationContext deserializationContext, final Object[] objArr) throws IOException {
        return deserializationContext.handleMissingInstantiator(this.getValueClass(), this, null, "no creator with arguments specified");
    }
    public Object createFromObjectWith(final DeserializationContext deserializationContext, final SettableBeanProperty[] settableBeanPropertyArr, final PropertyValueBuffer propertyValueBuffer) throws IOException {
        return this.createFromObjectWith(deserializationContext, propertyValueBuffer.getParameters(settableBeanPropertyArr));
    }
    public Object createUsingDelegate(final DeserializationContext deserializationContext, final Object obj) throws IOException {
        return deserializationContext.handleMissingInstantiator(this.getValueClass(), this, null, "no delegate creator specified");
    }
    public Object createUsingArrayDelegate(final DeserializationContext deserializationContext, final Object obj) throws IOException {
        return deserializationContext.handleMissingInstantiator(this.getValueClass(), this, null, "no array delegate creator specified");
    }
    public Object createFromString(final DeserializationContext deserializationContext, final String str) throws IOException {
        return deserializationContext.handleMissingInstantiator(this.getValueClass(), this, deserializationContext.getParser(), "no String-argument constructor/factory method to deserialize from String value ('%s')", str);
    }
    public Object createFromInt(final DeserializationContext deserializationContext, final int i2) throws IOException {
        return deserializationContext.handleMissingInstantiator(this.getValueClass(), this, null, "no int/Int-argument constructor/factory method to deserialize from Number value (%s)", Integer.valueOf(i2));
    }
    public Object createFromLong(final DeserializationContext deserializationContext, final long j2) throws IOException {
        return deserializationContext.handleMissingInstantiator(this.getValueClass(), this, null, "no long/Long-argument constructor/factory method to deserialize from Number value (%s)", Long.valueOf(j2));
    }
    public Object createFromBigInteger(final DeserializationContext deserializationContext, final BigInteger bigInteger) throws IOException {
        return deserializationContext.handleMissingInstantiator(this.getValueClass(), this, null, "no BigInteger-argument constructor/factory method to deserialize from Number value (%s)", bigInteger);
    }
    public Object createFromDouble(final DeserializationContext deserializationContext, final double d2) throws IOException {
        return deserializationContext.handleMissingInstantiator(this.getValueClass(), this, null, "no double/Double-argument constructor/factory method to deserialize from Number value (%s)", Double.valueOf(d2));
    }
    public Object createFromBigDecimal(final DeserializationContext deserializationContext, final BigDecimal bigDecimal) throws IOException {
        return deserializationContext.handleMissingInstantiator(this.getValueClass(), this, null, "no BigDecimal/double/Double-argument constructor/factory method to deserialize from Number value (%s)", bigDecimal);
    }
    public Object createFromBoolean(final DeserializationContext deserializationContext, final boolean z) throws IOException {
        return deserializationContext.handleMissingInstantiator(this.getValueClass(), this, null, "no boolean/Boolean-argument constructor/factory method to deserialize from boolean value (%s)", Boolean.valueOf(z));
    }
    protected Object _createFromStringFallbacks(final DeserializationContext deserializationContext, final String str) throws IOException {
        if (str.isEmpty() && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) {
            return null;
        }
        if (this.canCreateFromBoolean() && CoercionAction.TryConvert == deserializationContext.findCoercionAction(LogicalType.Boolean, Boolean.class, CoercionInputShape.String)) {
            final String strTrim = str.trim();
            if ("true".equals(strTrim)) {
                return this.createFromBoolean(deserializationContext, true);
            }
            if ("false".equals(strTrim)) {
                return this.createFromBoolean(deserializationContext, false);
            }
        }
        return deserializationContext.handleMissingInstantiator(this.getValueClass(), this, deserializationContext.getParser(), "no String-argument constructor/factory method to deserialize from String value ('%s')", str);
    }
    public static class Base extends ValueInstantiator implements Serializable {
        private static final long serialVersionUID = 1;
        protected final Class<?> _valueType;

        public Base(final Class<?> cls) {
            _valueType = cls;
        }

        public Base(final JavaType javaType) {
            _valueType = javaType.getRawClass();
        }

        
        public String getValueTypeDesc() {
            return _valueType.getName();
        }

        
        public Class<?> getValueClass() {
            return _valueType;
        }
    }
    public static class Delegating extends ValueInstantiator implements Serializable {
        private static final long serialVersionUID = 1;
        protected final ValueInstantiator _delegate;

        protected Delegating(final ValueInstantiator valueInstantiator) {
            _delegate = valueInstantiator;
        }

        
        public ValueInstantiator createContextual(final DeserializationContext deserializationContext, final BeanDescription beanDescription) throws JsonMappingException {
            final ValueInstantiator valueInstantiatorCreateContextual = _delegate.createContextual(deserializationContext, beanDescription);
            return valueInstantiatorCreateContextual == _delegate ? this : new Delegating(valueInstantiatorCreateContextual);
        }

        protected ValueInstantiator delegate() {
            return _delegate;
        }

        
        public Class<?> getValueClass() {
            return this.delegate().getValueClass();
        }

        
        public String getValueTypeDesc() {
            return this.delegate().getValueTypeDesc();
        }

        
        public boolean canInstantiate() {
            return this.delegate().canInstantiate();
        }

        
        public boolean canCreateFromString() {
            return this.delegate().canCreateFromString();
        }

        
        public boolean canCreateFromInt() {
            return this.delegate().canCreateFromInt();
        }

        
        public boolean canCreateFromLong() {
            return this.delegate().canCreateFromLong();
        }

        
        public boolean canCreateFromDouble() {
            return this.delegate().canCreateFromDouble();
        }

        
        public boolean canCreateFromBoolean() {
            return this.delegate().canCreateFromBoolean();
        }

        
        public boolean canCreateUsingDefault() {
            return this.delegate().canCreateUsingDefault();
        }

        
        public boolean canCreateUsingDelegate() {
            return this.delegate().canCreateUsingDelegate();
        }

        
        public boolean canCreateUsingArrayDelegate() {
            return this.delegate().canCreateUsingArrayDelegate();
        }

        
        public boolean canCreateFromObjectWith() {
            return this.delegate().canCreateFromObjectWith();
        }

        
        public SettableBeanProperty[] getFromObjectArguments(final DeserializationConfig deserializationConfig) {
            return this.delegate().getFromObjectArguments(deserializationConfig);
        }

        
        public JavaType getDelegateType(final DeserializationConfig deserializationConfig) {
            return this.delegate().getDelegateType(deserializationConfig);
        }

        
        public JavaType getArrayDelegateType(final DeserializationConfig deserializationConfig) {
            return this.delegate().getArrayDelegateType(deserializationConfig);
        }

        
        public Object createUsingDefault(final DeserializationContext deserializationContext) throws IOException {
            return this.delegate().createUsingDefault(deserializationContext);
        }

        
        public Object createFromObjectWith(final DeserializationContext deserializationContext, final Object[] objArr) throws IOException {
            return this.delegate().createFromObjectWith(deserializationContext, objArr);
        }

        
        public Object createFromObjectWith(final DeserializationContext deserializationContext, final SettableBeanProperty[] settableBeanPropertyArr, final PropertyValueBuffer propertyValueBuffer) throws IOException {
            return this.delegate().createFromObjectWith(deserializationContext, settableBeanPropertyArr, propertyValueBuffer);
        }

        
        public Object createUsingDelegate(final DeserializationContext deserializationContext, final Object obj) throws IOException {
            return this.delegate().createUsingDelegate(deserializationContext, obj);
        }

        
        public Object createUsingArrayDelegate(final DeserializationContext deserializationContext, final Object obj) throws IOException {
            return this.delegate().createUsingArrayDelegate(deserializationContext, obj);
        }

        
        public Object createFromString(final DeserializationContext deserializationContext, final String str) throws IOException {
            return this.delegate().createFromString(deserializationContext, str);
        }

        
        public Object createFromInt(final DeserializationContext deserializationContext, final int i2) throws IOException {
            return this.delegate().createFromInt(deserializationContext, i2);
        }

        
        public Object createFromLong(final DeserializationContext deserializationContext, final long j2) throws IOException {
            return this.delegate().createFromLong(deserializationContext, j2);
        }

        
        public Object createFromBigInteger(final DeserializationContext deserializationContext, final BigInteger bigInteger) throws IOException {
            return this.delegate().createFromBigInteger(deserializationContext, bigInteger);
        }

        
        public Object createFromDouble(final DeserializationContext deserializationContext, final double d2) throws IOException {
            return this.delegate().createFromDouble(deserializationContext, d2);
        }

        
        public Object createFromBigDecimal(final DeserializationContext deserializationContext, final BigDecimal bigDecimal) throws IOException {
            return this.delegate().createFromBigDecimal(deserializationContext, bigDecimal);
        }

        
        public Object createFromBoolean(final DeserializationContext deserializationContext, final boolean z) throws IOException {
            return this.delegate().createFromBoolean(deserializationContext, z);
        }

        
        public AnnotatedWithParams getDefaultCreator() {
            return this.delegate().getDefaultCreator();
        }

        
        public AnnotatedWithParams getDelegateCreator() {
            return this.delegate().getDelegateCreator();
        }

        
        public AnnotatedWithParams getArrayDelegateCreator() {
            return this.delegate().getArrayDelegateCreator();
        }

        
        public AnnotatedWithParams getWithArgsCreator() {
            return this.delegate().getWithArgsCreator();
        }
    }
}
