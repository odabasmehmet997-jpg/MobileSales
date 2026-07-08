package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.impl.FailingDeserializer;
import com.fasterxml.jackson.databind.deser.impl.NullsConstantProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.ViewMatcher;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public abstract class SettableBeanProperty extends ConcreteBeanPropertyBase {
    protected static final JsonDeserializer<Object> MISSING_VALUE_DESERIALIZER = new FailingDeserializer("No _valueDeserializer assigned");
    protected final transient Annotations _contextAnnotations;
    protected String _managedReferenceName;
    protected final NullValueProvider _nullProvider;
    protected ObjectIdInfo _objectIdInfo;
    protected final PropertyName _propName;
    protected int _propertyIndex;
    protected final JavaType _type;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;
    protected ViewMatcher _viewMatcher;
    protected final PropertyName _wrapperName;
    public abstract void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, IllegalAccessException, InvocationTargetException;
    public abstract Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, IllegalAccessException, InvocationTargetException;
    public void fixAccess(final DeserializationConfig deserializationConfig) { }
    public abstract AnnotatedMember getMember();
    public boolean isIgnorable() {
        return false;
    }
    public boolean isInjectionOnly() {
        return false;
    }
    public void markAsIgnorable() { }
    public abstract void set(Object obj, Object obj2) throws IOException, IllegalAccessException, InvocationTargetException;
    public abstract Object setAndReturn(Object obj, Object obj2) throws IOException, IllegalAccessException, InvocationTargetException;
    public abstract SettableBeanProperty withName(PropertyName propertyName);
    public abstract SettableBeanProperty withNullProvider(NullValueProvider nullValueProvider);
    public abstract SettableBeanProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer);
    protected SettableBeanProperty(final BeanPropertyDefinition beanPropertyDefinition, final JavaType javaType, final TypeDeserializer typeDeserializer, final Annotations annotations) {
        this(beanPropertyDefinition.getFullName(), javaType, beanPropertyDefinition.getWrapperName(), typeDeserializer, annotations, beanPropertyDefinition.getMetadata());
    }
    protected SettableBeanProperty(final PropertyName propertyName, final JavaType javaType, final PropertyName propertyName2, final TypeDeserializer typeDeserializer, final Annotations annotations, final PropertyMetadata propertyMetadata) {
        super(propertyMetadata);
        _propertyIndex = -1;
        if (null == propertyName) {
            _propName = PropertyName.NO_NAME;
        } else {
            _propName = propertyName.internSimpleName();
        }
        _type = javaType;
        _wrapperName = propertyName2;
        _contextAnnotations = annotations;
        _viewMatcher = null;
        _valueTypeDeserializer = null != typeDeserializer ? typeDeserializer.forProperty(this) : typeDeserializer;
        final JsonDeserializer<Object> jsonDeserializer = SettableBeanProperty.MISSING_VALUE_DESERIALIZER;
        _valueDeserializer = jsonDeserializer;
        _nullProvider = jsonDeserializer;
    }
    protected SettableBeanProperty(final PropertyName propertyName, final JavaType javaType, final PropertyMetadata propertyMetadata, final JsonDeserializer<Object> jsonDeserializer) {
        super(propertyMetadata);
        _propertyIndex = -1;
        if (null == propertyName) {
            _propName = PropertyName.NO_NAME;
        } else {
            _propName = propertyName.internSimpleName();
        }
        _type = javaType;
        _wrapperName = null;
        _contextAnnotations = null;
        _viewMatcher = null;
        _valueTypeDeserializer = null;
        _valueDeserializer = jsonDeserializer;
        _nullProvider = jsonDeserializer;
    }
    protected SettableBeanProperty(final SettableBeanProperty settableBeanProperty) {
        super(settableBeanProperty);
        _propertyIndex = -1;
        _propName = settableBeanProperty._propName;
        _type = settableBeanProperty._type;
        _wrapperName = settableBeanProperty._wrapperName;
        _contextAnnotations = settableBeanProperty._contextAnnotations;
        _valueDeserializer = settableBeanProperty._valueDeserializer;
        _valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        _managedReferenceName = settableBeanProperty._managedReferenceName;
        _propertyIndex = settableBeanProperty._propertyIndex;
        _viewMatcher = settableBeanProperty._viewMatcher;
        _nullProvider = settableBeanProperty._nullProvider;
    }
    protected SettableBeanProperty(final SettableBeanProperty settableBeanProperty, final JsonDeserializer<?> jsonDeserializer, final NullValueProvider nullValueProvider) {
        super(settableBeanProperty);
        _propertyIndex = -1;
        _propName = settableBeanProperty._propName;
        _type = settableBeanProperty._type;
        _wrapperName = settableBeanProperty._wrapperName;
        _contextAnnotations = settableBeanProperty._contextAnnotations;
        _valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        _managedReferenceName = settableBeanProperty._managedReferenceName;
        _propertyIndex = settableBeanProperty._propertyIndex;
        if (null == jsonDeserializer) {
            _valueDeserializer = SettableBeanProperty.MISSING_VALUE_DESERIALIZER;
        } else {
            _valueDeserializer = (JsonDeserializer<Object>) jsonDeserializer;
        }
        _viewMatcher = settableBeanProperty._viewMatcher;
        _nullProvider = nullValueProvider == SettableBeanProperty.MISSING_VALUE_DESERIALIZER ? _valueDeserializer : nullValueProvider;
    }
    protected SettableBeanProperty(final SettableBeanProperty settableBeanProperty, final PropertyName propertyName) {
        super(settableBeanProperty);
        _propertyIndex = -1;
        _propName = propertyName;
        _type = settableBeanProperty._type;
        _wrapperName = settableBeanProperty._wrapperName;
        _contextAnnotations = settableBeanProperty._contextAnnotations;
        _valueDeserializer = settableBeanProperty._valueDeserializer;
        _valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        _managedReferenceName = settableBeanProperty._managedReferenceName;
        _propertyIndex = settableBeanProperty._propertyIndex;
        _viewMatcher = settableBeanProperty._viewMatcher;
        _nullProvider = settableBeanProperty._nullProvider;
    }
    public SettableBeanProperty withSimpleName(final String str) {
        final PropertyName propertyName = _propName;
        final PropertyName propertyName2 = null == propertyName ? new PropertyName(str) : propertyName.withSimpleName(str);
        return propertyName2 == _propName ? this : this.withName(propertyName2);
    }
    public void setManagedReferenceName(final String str) {
        _managedReferenceName = str;
    }
    public void setObjectIdInfo(final ObjectIdInfo objectIdInfo) {
        _objectIdInfo = objectIdInfo;
    }
    public void setViews(final Class<?>[] clsArr) {
        if (null == clsArr) {
            _viewMatcher = null;
        } else {
            _viewMatcher = ViewMatcher.construct(clsArr);
        }
    }
    public void assignIndex(final int i2) {
        if (-1 != this._propertyIndex) {
            throw new IllegalStateException("Property '" + this.getName() + "' already had index (" + _propertyIndex + "), trying to assign " + i2);
        }
        _propertyIndex = i2;
    }
    public final String getName() {
        return _propName.getSimpleName();
    }
    public PropertyName getFullName() {
        return _propName;
    }
    public JavaType getType() {
        return _type;
    }
    public PropertyName getWrapperName() {
        return _wrapperName;
    }
    public void depositSchemaProperty(final JsonObjectFormatVisitor jsonObjectFormatVisitor, final SerializerProvider serializerProvider) throws JsonMappingException {
        if (this.isRequired()) {
            jsonObjectFormatVisitor.property(this);
        } else {
            jsonObjectFormatVisitor.optionalProperty(this);
        }
    }
    protected Class<?> getDeclaringClass() {
        return this.getMember().getDeclaringClass();
    }
    public String getManagedReferenceName() {
        return _managedReferenceName;
    }
    public ObjectIdInfo getObjectIdInfo() {
        return _objectIdInfo;
    }
    public boolean hasValueDeserializer() {
        final JsonDeserializer<Object> jsonDeserializer = _valueDeserializer;
        return null != jsonDeserializer && jsonDeserializer != SettableBeanProperty.MISSING_VALUE_DESERIALIZER;
    }
    public boolean hasValueTypeDeserializer() {
        return null != this._valueTypeDeserializer;
    }
    public JsonDeserializer<Object> getValueDeserializer() {
        final JsonDeserializer<Object> jsonDeserializer = _valueDeserializer;
        if (jsonDeserializer == SettableBeanProperty.MISSING_VALUE_DESERIALIZER) {
            return null;
        }
        return jsonDeserializer;
    }
    public TypeDeserializer getValueTypeDeserializer() {
        return _valueTypeDeserializer;
    }
    public NullValueProvider getNullValueProvider() {
        return _nullProvider;
    }
    public boolean visibleInView(final Class<?> cls) {
        final ViewMatcher viewMatcher = _viewMatcher;
        return null == viewMatcher || viewMatcher.isVisibleForView(cls);
    }
    public boolean hasViews() {
        return null != this._viewMatcher;
    }
    public int getPropertyIndex() {
        return _propertyIndex;
    }
    public int getCreatorIndex() {
        throw new IllegalStateException(String.format("Internal error: no creator index for property '%s' (of type %s)", this.getName(), this.getClass().getName()));
    }
    public final Object deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            return _nullProvider.getNullValue(deserializationContext);
        }
        final TypeDeserializer typeDeserializer = _valueTypeDeserializer;
        if (null != typeDeserializer) {
            return _valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
        }
        final Object objDeserialize = _valueDeserializer.deserialize(jsonParser, deserializationContext);
        return null == objDeserialize ? _nullProvider.getNullValue(deserializationContext) : objDeserialize;
    }
    public final Object deserializeWith(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_NULL)) {
            return NullsConstantProvider.isSkipper(_nullProvider) ? obj : _nullProvider.getNullValue(deserializationContext);
        }
        if (null != this._valueTypeDeserializer) {
            deserializationContext.reportBadDefinition(this._type, String.format("Cannot merge polymorphic property '%s'", this.getName()));
        }
        final Object objDeserialize = _valueDeserializer.deserialize(jsonParser, deserializationContext, obj);
        return null == objDeserialize ? NullsConstantProvider.isSkipper(_nullProvider) ? obj : _nullProvider.getNullValue(deserializationContext) : objDeserialize;
    }
    protected void _throwAsIOE(final JsonParser jsonParser, final Exception exc, final Object obj) throws IOException {
        if (exc instanceof IllegalArgumentException) {
            final String strClassNameOf = ClassUtil.classNameOf(obj);
            final StringBuilder sb = new StringBuilder("Problem deserializing property '");
            sb.append(this.getName());
            sb.append("' (expected type: ");
            sb.append(this._type);
            sb.append("; actual type: ");
            sb.append(strClassNameOf);
            sb.append(")");
            final String strExceptionMessage = ClassUtil.exceptionMessage(exc);
            if (null != strExceptionMessage) {
                sb.append(", problem: ");
                sb.append(strExceptionMessage);
            } else {
                sb.append(" (no error message provided)");
            }
            throw JsonMappingException.from(jsonParser, sb.toString(), exc);
        }
        this._throwAsIOE(jsonParser, exc);
    }
    protected IOException _throwAsIOE(final JsonParser jsonParser, final Exception exc) throws IOException {
        ClassUtil.throwIfIOE(exc);
        ClassUtil.throwIfRTE(exc);
        final Throwable rootCause = ClassUtil.getRootCause(exc);
        throw JsonMappingException.from(jsonParser, ClassUtil.exceptionMessage(rootCause), rootCause);
    }
    protected IOException _throwAsIOE(final Exception exc) throws IOException {
        return this._throwAsIOE((JsonParser) null, exc);
    }
    protected void _throwAsIOE(final Exception exc, final Object obj) throws IOException {
        this._throwAsIOE(null, exc, obj);
    }
    public String toString() {
        return "[property '" + this.getName() + "']";
    }
    public static abstract class Delegating extends SettableBeanProperty {
        protected final SettableBeanProperty delegate;
        protected abstract SettableBeanProperty withDelegate(SettableBeanProperty settableBeanProperty);
        protected Delegating(final SettableBeanProperty settableBeanProperty) {
            super(settableBeanProperty);
            delegate = settableBeanProperty;
        }
        protected SettableBeanProperty _with(final SettableBeanProperty settableBeanProperty) {
            return settableBeanProperty == delegate ? this : this.withDelegate(settableBeanProperty);
        }
        public SettableBeanProperty withValueDeserializer(final JsonDeserializer<?> jsonDeserializer) {
            return this._with(delegate.withValueDeserializer(jsonDeserializer));
        }
        public SettableBeanProperty withName(final PropertyName propertyName) {
            return this._with(delegate.withName(propertyName));
        }
        public SettableBeanProperty withNullProvider(final NullValueProvider nullValueProvider) {
            return this._with(delegate.withNullProvider(nullValueProvider));
        }
        public void assignIndex(final int i2) {
            delegate.assignIndex(i2);
        }
        public void fixAccess(final DeserializationConfig deserializationConfig) {
            delegate.fixAccess(deserializationConfig);
        }
        protected Class<?> getDeclaringClass() {
            return delegate.getDeclaringClass();
        }
        public String getManagedReferenceName() {
            return delegate.getManagedReferenceName();
        }
        public ObjectIdInfo getObjectIdInfo() {
            return delegate.getObjectIdInfo();
        }
        public boolean hasValueDeserializer() {
            return delegate.hasValueDeserializer();
        }
        public boolean hasValueTypeDeserializer() {
            return delegate.hasValueTypeDeserializer();
        }
        public JsonDeserializer<Object> getValueDeserializer() {
            return delegate.getValueDeserializer();
        }
        public TypeDeserializer getValueTypeDeserializer() {
            return delegate.getValueTypeDeserializer();
        }
        public boolean visibleInView(final Class<?> cls) {
            return delegate.visibleInView(cls);
        }
        public boolean hasViews() {
            return delegate.hasViews();
        }
        public int getPropertyIndex() {
            return delegate.getPropertyIndex();
        }
        public int getCreatorIndex() {
            return delegate.getCreatorIndex();
        }

        public boolean isInjectionOnly() {
            return delegate.isInjectionOnly();
        }
        public AnnotatedMember getMember() {
            return delegate.getMember();
        }

        public SettableBeanProperty getDelegate() {
            return delegate;
        }
        public void deserializeAndSet(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
            try {
                delegate.deserializeAndSet(jsonParser, deserializationContext, obj);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        public Object deserializeSetAndReturn(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj) throws IOException {
            try {
                return delegate.deserializeSetAndReturn(jsonParser, deserializationContext, obj);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        public void set(final Object obj, final Object obj2) throws IOException {
            try {
                delegate.set(obj, obj2);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        public Object setAndReturn(final Object obj, final Object obj2) throws IOException {
            try {
                return delegate.setAndReturn(obj, obj2);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
