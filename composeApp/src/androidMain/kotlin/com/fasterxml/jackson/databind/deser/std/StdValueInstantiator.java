package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.util.ClassUtil;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class StdValueInstantiator extends ValueInstantiator implements Serializable {
    private static final long serialVersionUID = 1;
    protected SettableBeanProperty[] _arrayDelegateArguments;
    protected AnnotatedWithParams _arrayDelegateCreator;
    protected JavaType _arrayDelegateType;
    protected SettableBeanProperty[] _constructorArguments;
    protected AnnotatedWithParams _defaultCreator;
    protected SettableBeanProperty[] _delegateArguments;
    protected AnnotatedWithParams _delegateCreator;
    protected JavaType _delegateType;
    protected AnnotatedWithParams _fromBigDecimalCreator;
    protected AnnotatedWithParams _fromBigIntegerCreator;
    protected AnnotatedWithParams _fromBooleanCreator;
    protected AnnotatedWithParams _fromDoubleCreator;
    protected AnnotatedWithParams _fromIntCreator;
    protected AnnotatedWithParams _fromLongCreator;
    protected AnnotatedWithParams _fromStringCreator;
    protected final Class<?> _valueClass;
    protected final String _valueTypeDesc;
    protected AnnotatedWithParams _withArgsCreator;

    public StdValueInstantiator(final DeserializationConfig deserializationConfig, final Class cls) {
        _valueTypeDesc = ClassUtil.nameOf(cls);
        _valueClass = null == cls ? Object.class : cls;
    }

    public StdValueInstantiator(final DeserializationConfig deserializationConfig, final JavaType javaType) {
        _valueTypeDesc = null == javaType ? "UNKNOWN TYPE" : javaType.toString();
        _valueClass = null == javaType ? Object.class : javaType.getRawClass();
    }

    protected StdValueInstantiator(final StdValueInstantiator stdValueInstantiator) {
        _valueTypeDesc = stdValueInstantiator._valueTypeDesc;
        _valueClass = stdValueInstantiator._valueClass;
        _defaultCreator = stdValueInstantiator._defaultCreator;
        _constructorArguments = stdValueInstantiator._constructorArguments;
        _withArgsCreator = stdValueInstantiator._withArgsCreator;
        _delegateType = stdValueInstantiator._delegateType;
        _delegateCreator = stdValueInstantiator._delegateCreator;
        _delegateArguments = stdValueInstantiator._delegateArguments;
        _arrayDelegateType = stdValueInstantiator._arrayDelegateType;
        _arrayDelegateCreator = stdValueInstantiator._arrayDelegateCreator;
        _arrayDelegateArguments = stdValueInstantiator._arrayDelegateArguments;
        _fromStringCreator = stdValueInstantiator._fromStringCreator;
        _fromIntCreator = stdValueInstantiator._fromIntCreator;
        _fromLongCreator = stdValueInstantiator._fromLongCreator;
        _fromBigIntegerCreator = stdValueInstantiator._fromBigIntegerCreator;
        _fromDoubleCreator = stdValueInstantiator._fromDoubleCreator;
        _fromBigDecimalCreator = stdValueInstantiator._fromBigDecimalCreator;
        _fromBooleanCreator = stdValueInstantiator._fromBooleanCreator;
    }

    public void configureFromObjectSettings(final AnnotatedWithParams annotatedWithParams, final AnnotatedWithParams annotatedWithParams2, final JavaType javaType, final SettableBeanProperty[] settableBeanPropertyArr, final AnnotatedWithParams annotatedWithParams3, final SettableBeanProperty[] settableBeanPropertyArr2) {
        _defaultCreator = annotatedWithParams;
        _delegateCreator = annotatedWithParams2;
        _delegateType = javaType;
        _delegateArguments = settableBeanPropertyArr;
        _withArgsCreator = annotatedWithParams3;
        _constructorArguments = settableBeanPropertyArr2;
    }

    public void configureFromArraySettings(final AnnotatedWithParams annotatedWithParams, final JavaType javaType, final SettableBeanProperty[] settableBeanPropertyArr) {
        _arrayDelegateCreator = annotatedWithParams;
        _arrayDelegateType = javaType;
        _arrayDelegateArguments = settableBeanPropertyArr;
    }

    public void configureFromStringCreator(final AnnotatedWithParams annotatedWithParams) {
        _fromStringCreator = annotatedWithParams;
    }

    public void configureFromIntCreator(final AnnotatedWithParams annotatedWithParams) {
        _fromIntCreator = annotatedWithParams;
    }

    public void configureFromLongCreator(final AnnotatedWithParams annotatedWithParams) {
        _fromLongCreator = annotatedWithParams;
    }

    public void configureFromBigIntegerCreator(final AnnotatedWithParams annotatedWithParams) {
        _fromBigIntegerCreator = annotatedWithParams;
    }

    public void configureFromDoubleCreator(final AnnotatedWithParams annotatedWithParams) {
        _fromDoubleCreator = annotatedWithParams;
    }

    public void configureFromBigDecimalCreator(final AnnotatedWithParams annotatedWithParams) {
        _fromBigDecimalCreator = annotatedWithParams;
    }

    public void configureFromBooleanCreator(final AnnotatedWithParams annotatedWithParams) {
        _fromBooleanCreator = annotatedWithParams;
    }

    
    public String getValueTypeDesc() {
        return _valueTypeDesc;
    }

    
    public Class<?> getValueClass() {
        return _valueClass;
    }

    
    public boolean canCreateFromString() {
        return null != this._fromStringCreator;
    }

    
    public boolean canCreateFromInt() {
        return null != this._fromIntCreator;
    }

    
    public boolean canCreateFromLong() {
        return null != this._fromLongCreator;
    }

    
    public boolean canCreateFromBigInteger() {
        return null != this._fromBigIntegerCreator;
    }

    
    public boolean canCreateFromDouble() {
        return null != this._fromDoubleCreator;
    }

    
    public boolean canCreateFromBigDecimal() {
        return null != this._fromBigDecimalCreator;
    }

    
    public boolean canCreateFromBoolean() {
        return null != this._fromBooleanCreator;
    }

    
    public boolean canCreateUsingDefault() {
        return null != this._defaultCreator;
    }

    
    public boolean canCreateUsingDelegate() {
        return null != this._delegateType;
    }

    
    public boolean canCreateUsingArrayDelegate() {
        return null != this._arrayDelegateType;
    }

    
    public boolean canCreateFromObjectWith() {
        return null != this._withArgsCreator;
    }

    
    public boolean canInstantiate() {
        return this.canCreateUsingDefault() || this.canCreateUsingDelegate() || this.canCreateUsingArrayDelegate() || this.canCreateFromObjectWith() || this.canCreateFromString() || this.canCreateFromInt() || this.canCreateFromLong() || this.canCreateFromDouble() || this.canCreateFromBoolean();
    }

    
    public JavaType getDelegateType(final DeserializationConfig deserializationConfig) {
        return _delegateType;
    }

    
    public JavaType getArrayDelegateType(final DeserializationConfig deserializationConfig) {
        return _arrayDelegateType;
    }

    
    public SettableBeanProperty[] getFromObjectArguments(final DeserializationConfig deserializationConfig) {
        return _constructorArguments;
    }

    
    public Object createUsingDefault(final DeserializationContext deserializationContext) throws IOException {
        final AnnotatedWithParams annotatedWithParams = _defaultCreator;
        if (null == annotatedWithParams) {
            return super.createUsingDefault(deserializationContext);
        }
        try {
            return annotatedWithParams.call();
        } catch (final Exception e2) {
            return deserializationContext.handleInstantiationProblem(_valueClass, null, this.rewrapCtorProblem(deserializationContext, e2));
        }
    }

    
    public Object createFromObjectWith(final DeserializationContext deserializationContext, final Object[] objArr) throws IOException {
        final AnnotatedWithParams annotatedWithParams = _withArgsCreator;
        if (null == annotatedWithParams) {
            return super.createFromObjectWith(deserializationContext, objArr);
        }
        try {
            return annotatedWithParams.call(objArr);
        } catch (final Exception e2) {
            return deserializationContext.handleInstantiationProblem(_valueClass, objArr, this.rewrapCtorProblem(deserializationContext, e2));
        }
    }

    
    public Object createUsingDelegate(final DeserializationContext deserializationContext, final Object obj) throws IOException {
        final AnnotatedWithParams annotatedWithParams;
        final AnnotatedWithParams annotatedWithParams2 = _delegateCreator;
        if (null == annotatedWithParams2 && null != (annotatedWithParams = this._arrayDelegateCreator)) {
            return this._createUsingDelegate(annotatedWithParams, _arrayDelegateArguments, deserializationContext, obj);
        }
        return this._createUsingDelegate(annotatedWithParams2, _delegateArguments, deserializationContext, obj);
    }

    
    public Object createUsingArrayDelegate(final DeserializationContext deserializationContext, final Object obj) throws IOException {
        final AnnotatedWithParams annotatedWithParams = _arrayDelegateCreator;
        if (null == annotatedWithParams && null != this._delegateCreator) {
            return this.createUsingDelegate(deserializationContext, obj);
        }
        return this._createUsingDelegate(annotatedWithParams, _arrayDelegateArguments, deserializationContext, obj);
    }

    
    public Object createFromString(final DeserializationContext deserializationContext, final String str) throws IOException {
        final AnnotatedWithParams annotatedWithParams = _fromStringCreator;
        if (null != annotatedWithParams) {
            try {
                return annotatedWithParams.call1(str);
            } catch (final Throwable th) {
                return deserializationContext.handleInstantiationProblem(_fromStringCreator.getDeclaringClass(), str, this.rewrapCtorProblem(deserializationContext, th));
            }
        }
        return super.createFromString(deserializationContext, str);
    }

    
    public Object createFromInt(final DeserializationContext deserializationContext, final int i2) throws IOException {
        if (null != this._fromIntCreator) {
            final Integer numValueOf = Integer.valueOf(i2);
            try {
                return _fromIntCreator.call1(numValueOf);
            } catch (final Throwable th) {
                return deserializationContext.handleInstantiationProblem(_fromIntCreator.getDeclaringClass(), numValueOf, this.rewrapCtorProblem(deserializationContext, th));
            }
        }
        if (null != this._fromLongCreator) {
            final Long lValueOf = Long.valueOf(i2);
            try {
                return _fromLongCreator.call1(lValueOf);
            } catch (final Throwable th2) {
                return deserializationContext.handleInstantiationProblem(_fromLongCreator.getDeclaringClass(), lValueOf, this.rewrapCtorProblem(deserializationContext, th2));
            }
        }
        if (null != this._fromBigIntegerCreator) {
            final BigInteger bigIntegerValueOf = BigInteger.valueOf(i2);
            try {
                return _fromBigIntegerCreator.call1(bigIntegerValueOf);
            } catch (final Throwable th3) {
                return deserializationContext.handleInstantiationProblem(_fromBigIntegerCreator.getDeclaringClass(), bigIntegerValueOf, this.rewrapCtorProblem(deserializationContext, th3));
            }
        }
        return super.createFromInt(deserializationContext, i2);
    }

    
    public Object createFromLong(final DeserializationContext deserializationContext, final long j2) throws IOException {
        if (null != this._fromLongCreator) {
            final Long lValueOf = Long.valueOf(j2);
            try {
                return _fromLongCreator.call1(lValueOf);
            } catch (final Throwable th) {
                return deserializationContext.handleInstantiationProblem(_fromLongCreator.getDeclaringClass(), lValueOf, this.rewrapCtorProblem(deserializationContext, th));
            }
        }
        if (null != this._fromBigIntegerCreator) {
            final BigInteger bigIntegerValueOf = BigInteger.valueOf(j2);
            try {
                return _fromBigIntegerCreator.call1(bigIntegerValueOf);
            } catch (final Throwable th2) {
                return deserializationContext.handleInstantiationProblem(_fromBigIntegerCreator.getDeclaringClass(), bigIntegerValueOf, this.rewrapCtorProblem(deserializationContext, th2));
            }
        }
        return super.createFromLong(deserializationContext, j2);
    }

    
    public Object createFromBigInteger(final DeserializationContext deserializationContext, final BigInteger bigInteger) throws IOException {
        final AnnotatedWithParams annotatedWithParams = _fromBigIntegerCreator;
        if (null != annotatedWithParams) {
            try {
                return annotatedWithParams.call1(bigInteger);
            } catch (final Throwable th) {
                return deserializationContext.handleInstantiationProblem(_fromBigIntegerCreator.getDeclaringClass(), bigInteger, this.rewrapCtorProblem(deserializationContext, th));
            }
        }
        return super.createFromBigInteger(deserializationContext, bigInteger);
    }

    
    public Object createFromDouble(final DeserializationContext deserializationContext, final double d2) throws IOException {
        if (null != this._fromDoubleCreator) {
            final Double dValueOf = Double.valueOf(d2);
            try {
                return _fromDoubleCreator.call1(dValueOf);
            } catch (final Throwable th) {
                return deserializationContext.handleInstantiationProblem(_fromDoubleCreator.getDeclaringClass(), dValueOf, this.rewrapCtorProblem(deserializationContext, th));
            }
        }
        if (null != this._fromBigDecimalCreator) {
            final BigDecimal bigDecimalValueOf = BigDecimal.valueOf(d2);
            try {
                return _fromBigDecimalCreator.call1(bigDecimalValueOf);
            } catch (final Throwable th2) {
                return deserializationContext.handleInstantiationProblem(_fromBigDecimalCreator.getDeclaringClass(), bigDecimalValueOf, this.rewrapCtorProblem(deserializationContext, th2));
            }
        }
        return super.createFromDouble(deserializationContext, d2);
    }

    
    public Object createFromBigDecimal(final DeserializationContext deserializationContext, final BigDecimal bigDecimal) throws IOException {
        final Double dTryConvertToDouble;
        final AnnotatedWithParams annotatedWithParams = _fromBigDecimalCreator;
        if (null != annotatedWithParams) {
            try {
                return annotatedWithParams.call1(bigDecimal);
            } catch (final Throwable th) {
                return deserializationContext.handleInstantiationProblem(_fromBigDecimalCreator.getDeclaringClass(), bigDecimal, this.rewrapCtorProblem(deserializationContext, th));
            }
        }
        if (null != this._fromDoubleCreator && null != (dTryConvertToDouble = tryConvertToDouble(bigDecimal))) {
            try {
                return _fromDoubleCreator.call1(dTryConvertToDouble);
            } catch (final Throwable th2) {
                return deserializationContext.handleInstantiationProblem(_fromDoubleCreator.getDeclaringClass(), dTryConvertToDouble, this.rewrapCtorProblem(deserializationContext, th2));
            }
        }
        return super.createFromBigDecimal(deserializationContext, bigDecimal);
    }

    static Double tryConvertToDouble(final BigDecimal bigDecimal) {
        final double dDoubleValue = bigDecimal.doubleValue();
        if (Double.isInfinite(dDoubleValue)) {
            return null;
        }
        return Double.valueOf(dDoubleValue);
    }

    
    public Object createFromBoolean(final DeserializationContext deserializationContext, final boolean z) throws IOException {
        if (null == this._fromBooleanCreator) {
            return super.createFromBoolean(deserializationContext, z);
        }
        final Boolean boolValueOf = Boolean.valueOf(z);
        try {
            return _fromBooleanCreator.call1(boolValueOf);
        } catch (final Throwable th) {
            return deserializationContext.handleInstantiationProblem(_fromBooleanCreator.getDeclaringClass(), boolValueOf, this.rewrapCtorProblem(deserializationContext, th));
        }
    }

    
    public AnnotatedWithParams getDelegateCreator() {
        return _delegateCreator;
    }

    
    public AnnotatedWithParams getArrayDelegateCreator() {
        return _arrayDelegateCreator;
    }

    
    public AnnotatedWithParams getDefaultCreator() {
        return _defaultCreator;
    }

    
    public AnnotatedWithParams getWithArgsCreator() {
        return _withArgsCreator;
    }

    @Deprecated
    protected JsonMappingException wrapException(final Throwable th) {
        for (Throwable cause = th; null != cause; cause = cause.getCause()) {
            if (cause instanceof JsonMappingException) {
                return (JsonMappingException) cause;
            }
        }
        return new JsonMappingException(null, "Instantiation of " + this._valueTypeDesc + " value failed: " + ClassUtil.exceptionMessage(th), th);
    }

    @Deprecated
    protected JsonMappingException unwrapAndWrapException(final DeserializationContext deserializationContext, final Throwable th) {
        for (Throwable cause = th; null != cause; cause = cause.getCause()) {
            if (cause instanceof JsonMappingException) {
                return (JsonMappingException) cause;
            }
        }
        return deserializationContext.instantiationException(this._valueClass, th);
    }

    protected JsonMappingException wrapAsJsonMappingException(final DeserializationContext deserializationContext, final Throwable th) {
        if (th instanceof JsonMappingException) {
            return (JsonMappingException) th;
        }
        return deserializationContext.instantiationException(this._valueClass, th);
    }

    protected JsonMappingException rewrapCtorProblem(final DeserializationContext deserializationContext, Throwable th) {
        final Throwable cause;
        if (((th instanceof ExceptionInInitializerError) || (th instanceof InvocationTargetException)) && null != (cause = th.getCause())) {
            th = cause;
        }
        return this.wrapAsJsonMappingException(deserializationContext, th);
    }

    private Object _createUsingDelegate(final AnnotatedWithParams annotatedWithParams, final SettableBeanProperty[] settableBeanPropertyArr, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        if (null == annotatedWithParams) {
            throw new IllegalStateException("No delegate constructor for " + this._valueTypeDesc);
        }
        try {
            if (null == settableBeanPropertyArr) {
                return annotatedWithParams.call1(obj);
            }
            final int length = settableBeanPropertyArr.length;
            final Object[] objArr = new Object[length];
            for (int i2 = 0; i2 < length; i2++) {
                final SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i2];
                if (null == settableBeanProperty) {
                    objArr[i2] = obj;
                } else {
                    objArr[i2] = deserializationContext.findInjectableValue(settableBeanProperty.getInjectableValueId(), settableBeanProperty, null);
                }
            }
            return annotatedWithParams.call(objArr);
        } catch (final Throwable th) {
            throw this.rewrapCtorProblem(deserializationContext, th);
        }
    }
}
