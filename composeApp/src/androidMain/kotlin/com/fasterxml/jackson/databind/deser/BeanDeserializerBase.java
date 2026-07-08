package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.impl.*;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.*;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class BeanDeserializerBase extends StdDeserializer<Object> implements ContextualDeserializer, ResolvableDeserializer {
    protected static final PropertyName TEMP_PROPERTY_NAME = new PropertyName("#temporary-name");
    private static final long serialVersionUID = 1;
    protected SettableAnyProperty _anySetter;
    protected JsonDeserializer<Object> _arrayDelegateDeserializer;
    protected final Map<String, SettableBeanProperty> _backRefs;
    protected final BeanPropertyMap _beanProperties;
    protected final JavaType _beanType;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected ExternalTypeHandler _externalTypeIdHandler;
    protected final Set<String> _ignorableProps;
    protected final boolean _ignoreAllUnknown;
    protected final Set<String> _includableProps;
    protected final ValueInjector[] _injectables;
    protected final boolean _needViewProcesing;
    protected boolean _nonStandardCreation;
    protected final ObjectIdReader _objectIdReader;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected final JsonFormat.Shape _serializationShape;
    protected transient HashMap<ClassKey, JsonDeserializer<Object>> _subDeserializers;
    protected UnwrappedPropertyHandler _unwrappedPropertyHandler;
    protected final ValueInstantiator _valueInstantiator;
    protected boolean _vanillaProcessing;
    protected abstract Object _deserializeUsingPropertyBased(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;
    protected abstract BeanDeserializerBase asArrayDeserializer();
    public abstract Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;
    public boolean isCachable() {
        return true;
    }
    public abstract JsonDeserializer<Object> unwrappingDeserializer(NameTransformer nameTransformer);
    public abstract BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2);
    public abstract BeanDeserializerBase withIgnoreAllUnknown(boolean z);
    public abstract BeanDeserializerBase withObjectIdReader(ObjectIdReader objectIdReader);
    protected BeanDeserializerBase(final BeanDeserializerBuilder beanDeserializerBuilder, final BeanDescription beanDescription, final BeanPropertyMap beanPropertyMap, final Map<String, SettableBeanProperty> map, final Set<String> set, final boolean z, final Set<String> set2, final boolean z2) {
        super(beanDescription.getType());
        _beanType = beanDescription.getType();
        final ValueInstantiator valueInstantiator = beanDeserializerBuilder.getValueInstantiator();
        _valueInstantiator = valueInstantiator;
        _delegateDeserializer = null;
        _arrayDelegateDeserializer = null;
        _propertyBasedCreator = null;
        _beanProperties = beanPropertyMap;
        _backRefs = map;
        _ignorableProps = set;
        _ignoreAllUnknown = z;
        _includableProps = set2;
        _anySetter = beanDeserializerBuilder.getAnySetter();
        final List<ValueInjector> injectables = beanDeserializerBuilder.getInjectables();
        final ValueInjector[] valueInjectorArr = (null == injectables || injectables.isEmpty()) ? null : injectables.toArray(new ValueInjector[injectables.size()]);
        _injectables = valueInjectorArr;
        final ObjectIdReader objectIdReader = beanDeserializerBuilder.getObjectIdReader();
        _objectIdReader = objectIdReader;
        _nonStandardCreation = null != this._unwrappedPropertyHandler || valueInstantiator.canCreateUsingDelegate() || valueInstantiator.canCreateFromObjectWith() || !valueInstantiator.canCreateUsingDefault();
        _serializationShape = beanDescription.findExpectedFormat(null).getShape();
        _needViewProcesing = z2;
        _vanillaProcessing = !_nonStandardCreation && null == valueInjectorArr && !z2 && null == objectIdReader;
    }
    protected BeanDeserializerBase(final BeanDeserializerBase beanDeserializerBase) {
        this(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
    }
    protected BeanDeserializerBase(final BeanDeserializerBase beanDeserializerBase, final boolean z) {
        super(beanDeserializerBase._beanType);
        _beanType = beanDeserializerBase._beanType;
        _valueInstantiator = beanDeserializerBase._valueInstantiator;
        _delegateDeserializer = beanDeserializerBase._delegateDeserializer;
        _arrayDelegateDeserializer = beanDeserializerBase._arrayDelegateDeserializer;
        _propertyBasedCreator = beanDeserializerBase._propertyBasedCreator;
        _beanProperties = beanDeserializerBase._beanProperties;
        _backRefs = beanDeserializerBase._backRefs;
        _ignorableProps = beanDeserializerBase._ignorableProps;
        _ignoreAllUnknown = z;
        _includableProps = beanDeserializerBase._includableProps;
        _anySetter = beanDeserializerBase._anySetter;
        _injectables = beanDeserializerBase._injectables;
        _objectIdReader = beanDeserializerBase._objectIdReader;
        _nonStandardCreation = beanDeserializerBase._nonStandardCreation;
        _unwrappedPropertyHandler = beanDeserializerBase._unwrappedPropertyHandler;
        _needViewProcesing = beanDeserializerBase._needViewProcesing;
        _serializationShape = beanDeserializerBase._serializationShape;
        _vanillaProcessing = beanDeserializerBase._vanillaProcessing;
    }
    protected BeanDeserializerBase(final BeanDeserializerBase beanDeserializerBase, final NameTransformer nameTransformer) {
        super(beanDeserializerBase._beanType);
        _beanType = beanDeserializerBase._beanType;
        _valueInstantiator = beanDeserializerBase._valueInstantiator;
        _delegateDeserializer = beanDeserializerBase._delegateDeserializer;
        _arrayDelegateDeserializer = beanDeserializerBase._arrayDelegateDeserializer;
        _propertyBasedCreator = beanDeserializerBase._propertyBasedCreator;
        _backRefs = beanDeserializerBase._backRefs;
        _ignorableProps = beanDeserializerBase._ignorableProps;
        _ignoreAllUnknown = null != nameTransformer || beanDeserializerBase._ignoreAllUnknown;
        _includableProps = beanDeserializerBase._includableProps;
        _anySetter = beanDeserializerBase._anySetter;
        _injectables = beanDeserializerBase._injectables;
        _objectIdReader = beanDeserializerBase._objectIdReader;
        _nonStandardCreation = beanDeserializerBase._nonStandardCreation;
        UnwrappedPropertyHandler unwrappedPropertyHandlerRenameAll = beanDeserializerBase._unwrappedPropertyHandler;
        if (null != nameTransformer) {
            unwrappedPropertyHandlerRenameAll = null != unwrappedPropertyHandlerRenameAll ? unwrappedPropertyHandlerRenameAll.renameAll(nameTransformer) : unwrappedPropertyHandlerRenameAll;
            _beanProperties = beanDeserializerBase._beanProperties.renameAll(nameTransformer);
        } else {
            _beanProperties = beanDeserializerBase._beanProperties;
        }
        _unwrappedPropertyHandler = unwrappedPropertyHandlerRenameAll;
        _needViewProcesing = beanDeserializerBase._needViewProcesing;
        _serializationShape = beanDeserializerBase._serializationShape;
        _vanillaProcessing = false;
    }
    protected BeanDeserializerBase(final BeanDeserializerBase beanDeserializerBase, final ObjectIdReader objectIdReader) {
        super(beanDeserializerBase._beanType);
        _beanType = beanDeserializerBase._beanType;
        _valueInstantiator = beanDeserializerBase._valueInstantiator;
        _delegateDeserializer = beanDeserializerBase._delegateDeserializer;
        _arrayDelegateDeserializer = beanDeserializerBase._arrayDelegateDeserializer;
        _propertyBasedCreator = beanDeserializerBase._propertyBasedCreator;
        _backRefs = beanDeserializerBase._backRefs;
        _ignorableProps = beanDeserializerBase._ignorableProps;
        _ignoreAllUnknown = beanDeserializerBase._ignoreAllUnknown;
        _includableProps = beanDeserializerBase._includableProps;
        _anySetter = beanDeserializerBase._anySetter;
        _injectables = beanDeserializerBase._injectables;
        _nonStandardCreation = beanDeserializerBase._nonStandardCreation;
        _unwrappedPropertyHandler = beanDeserializerBase._unwrappedPropertyHandler;
        _needViewProcesing = beanDeserializerBase._needViewProcesing;
        _serializationShape = beanDeserializerBase._serializationShape;
        _objectIdReader = objectIdReader;
        if (null == objectIdReader) {
            _beanProperties = beanDeserializerBase._beanProperties;
            _vanillaProcessing = beanDeserializerBase._vanillaProcessing;
        } else {
            _beanProperties = beanDeserializerBase._beanProperties.withProperty(new ObjectIdValueProperty(objectIdReader, PropertyMetadata.STD_REQUIRED));
            _vanillaProcessing = false;
        }
    }
    protected BeanDeserializerBase(final BeanDeserializerBase beanDeserializerBase, final Set<String> set, final Set<String> set2) {
        super(beanDeserializerBase._beanType);
        _beanType = beanDeserializerBase._beanType;
        _valueInstantiator = beanDeserializerBase._valueInstantiator;
        _delegateDeserializer = beanDeserializerBase._delegateDeserializer;
        _arrayDelegateDeserializer = beanDeserializerBase._arrayDelegateDeserializer;
        _propertyBasedCreator = beanDeserializerBase._propertyBasedCreator;
        _backRefs = beanDeserializerBase._backRefs;
        _ignorableProps = set;
        _ignoreAllUnknown = beanDeserializerBase._ignoreAllUnknown;
        _includableProps = set2;
        _anySetter = beanDeserializerBase._anySetter;
        _injectables = beanDeserializerBase._injectables;
        _nonStandardCreation = beanDeserializerBase._nonStandardCreation;
        _unwrappedPropertyHandler = beanDeserializerBase._unwrappedPropertyHandler;
        _needViewProcesing = beanDeserializerBase._needViewProcesing;
        _serializationShape = beanDeserializerBase._serializationShape;
        _vanillaProcessing = beanDeserializerBase._vanillaProcessing;
        _objectIdReader = beanDeserializerBase._objectIdReader;
        _beanProperties = beanDeserializerBase._beanProperties.withoutProperties(set, set2);
    }
    protected BeanDeserializerBase(final BeanDeserializerBase beanDeserializerBase, final BeanPropertyMap beanPropertyMap) {
        super(beanDeserializerBase._beanType);
        _beanType = beanDeserializerBase._beanType;
        _valueInstantiator = beanDeserializerBase._valueInstantiator;
        _delegateDeserializer = beanDeserializerBase._delegateDeserializer;
        _arrayDelegateDeserializer = beanDeserializerBase._arrayDelegateDeserializer;
        _propertyBasedCreator = beanDeserializerBase._propertyBasedCreator;
        _beanProperties = beanPropertyMap;
        _backRefs = beanDeserializerBase._backRefs;
        _ignorableProps = beanDeserializerBase._ignorableProps;
        _ignoreAllUnknown = beanDeserializerBase._ignoreAllUnknown;
        _includableProps = beanDeserializerBase._includableProps;
        _anySetter = beanDeserializerBase._anySetter;
        _injectables = beanDeserializerBase._injectables;
        _objectIdReader = beanDeserializerBase._objectIdReader;
        _nonStandardCreation = beanDeserializerBase._nonStandardCreation;
        _unwrappedPropertyHandler = beanDeserializerBase._unwrappedPropertyHandler;
        _needViewProcesing = beanDeserializerBase._needViewProcesing;
        _serializationShape = beanDeserializerBase._serializationShape;
        _vanillaProcessing = beanDeserializerBase._vanillaProcessing;
    }
    protected BeanDeserializerBase(final BeanDeserializerBase beanDeserializerBase, final Set<String> set) {
        this(beanDeserializerBase, set, beanDeserializerBase._includableProps);
    }
    public BeanDeserializerBase withBeanProperties(final BeanPropertyMap beanPropertyMap) {
        throw new UnsupportedOperationException("Class " + this.getClass().getName() + " does not override `withBeanProperties()`, needs to");
    }
    public BeanDeserializerBase withIgnorableProperties(final Set<String> set) {
        return this.withByNameInclusion(set, _includableProps);
    }
    public void resolve(final DeserializationContext deserializationContext) throws SecurityException, JsonMappingException {
        final SettableBeanProperty[] fromObjectArguments;
        JsonDeserializer<Object> valueDeserializer;
        JsonDeserializer<Object> jsonDeserializerUnwrappingDeserializer;
        boolean z = false;
        ExternalTypeHandler.Builder builder = null;
        if (_valueInstantiator.canCreateFromObjectWith()) {
            fromObjectArguments = _valueInstantiator.getFromObjectArguments(deserializationContext.getConfig());
            if (null != this._ignorableProps || null != this._includableProps) {
                final int length = fromObjectArguments.length;
                for (int i2 = 0; i2 < length; i2++) {
                    if (IgnorePropertiesUtil.shouldIgnore(fromObjectArguments[i2].getName(), _ignorableProps, _includableProps)) {
                        fromObjectArguments[i2].markAsIgnorable();
                    }
                }
            }
        } else {
            fromObjectArguments = null;
        }
        final Iterator<SettableBeanProperty> it = _beanProperties.iterator();
        while (it.hasNext()) {
            final SettableBeanProperty next = it.next();
            if (!next.hasValueDeserializer()) {
                JsonDeserializer<Object> jsonDeserializerFindConvertingDeserializer = this.findConvertingDeserializer(deserializationContext, next);
                if (null == jsonDeserializerFindConvertingDeserializer) {
                    jsonDeserializerFindConvertingDeserializer = deserializationContext.findNonContextualValueDeserializer(next.getType());
                }
                this._replaceProperty(_beanProperties, fromObjectArguments, next, next.withValueDeserializer(jsonDeserializerFindConvertingDeserializer));
            }
        }
        final Iterator<SettableBeanProperty> it2 = _beanProperties.iterator();
        UnwrappedPropertyHandler unwrappedPropertyHandler = null;
        while (it2.hasNext()) {
            final SettableBeanProperty next2 = it2.next();
            SettableBeanProperty settableBeanProperty_resolveManagedReferenceProperty = this._resolveManagedReferenceProperty(deserializationContext, next2.withValueDeserializer(deserializationContext.handlePrimaryContextualization(next2.getValueDeserializer(), next2, next2.getType())));
            if (!(settableBeanProperty_resolveManagedReferenceProperty instanceof ManagedReferenceProperty)) {
                settableBeanProperty_resolveManagedReferenceProperty = this._resolvedObjectIdProperty(deserializationContext, settableBeanProperty_resolveManagedReferenceProperty);
            }
            final NameTransformer nameTransformer_findPropertyUnwrapper = this._findPropertyUnwrapper(deserializationContext, settableBeanProperty_resolveManagedReferenceProperty);
            if (null != nameTransformer_findPropertyUnwrapper && (jsonDeserializerUnwrappingDeserializer = (valueDeserializer = settableBeanProperty_resolveManagedReferenceProperty.getValueDeserializer()).unwrappingDeserializer(nameTransformer_findPropertyUnwrapper)) != valueDeserializer && null != jsonDeserializerUnwrappingDeserializer) {
                final SettableBeanProperty settableBeanPropertyWithValueDeserializer = settableBeanProperty_resolveManagedReferenceProperty.withValueDeserializer(jsonDeserializerUnwrappingDeserializer);
                if (null == unwrappedPropertyHandler) {
                    unwrappedPropertyHandler = new UnwrappedPropertyHandler();
                }
                unwrappedPropertyHandler.addProperty(settableBeanPropertyWithValueDeserializer);
                _beanProperties.remove(settableBeanPropertyWithValueDeserializer);
            } else {
                final SettableBeanProperty settableBeanProperty_resolveInnerClassValuedProperty = this._resolveInnerClassValuedProperty(deserializationContext, this._resolveMergeAndNullSettings(deserializationContext, settableBeanProperty_resolveManagedReferenceProperty, settableBeanProperty_resolveManagedReferenceProperty.getMetadata()));
                if (settableBeanProperty_resolveInnerClassValuedProperty != next2) {
                    this._replaceProperty(_beanProperties, fromObjectArguments, next2, settableBeanProperty_resolveInnerClassValuedProperty);
                }
                if (settableBeanProperty_resolveInnerClassValuedProperty.hasValueTypeDeserializer()) {
                    final TypeDeserializer valueTypeDeserializer = settableBeanProperty_resolveInnerClassValuedProperty.getValueTypeDeserializer();
                    if (JsonTypeInfo.EnumC1184As.EXTERNAL_PROPERTY == valueTypeDeserializer.getTypeInclusion()) {
                        if (null == builder) {
                            builder = ExternalTypeHandler.builder(_beanType);
                        }
                        builder.addExternal(settableBeanProperty_resolveInnerClassValuedProperty, valueTypeDeserializer);
                        _beanProperties.remove(settableBeanProperty_resolveInnerClassValuedProperty);
                    }
                }
            }
        }
        final SettableAnyProperty settableAnyProperty = _anySetter;
        if (null != settableAnyProperty && !settableAnyProperty.hasValueDeserializer()) {
            final SettableAnyProperty settableAnyProperty2 = _anySetter;
            _anySetter = settableAnyProperty2.withValueDeserializer(this.findDeserializer(deserializationContext, settableAnyProperty2.getType(), _anySetter.getProperty()));
        }
        if (_valueInstantiator.canCreateUsingDelegate()) {
            final JavaType delegateType = _valueInstantiator.getDelegateType(deserializationContext.getConfig());
            if (null == delegateType) {
                final JavaType javaType = _beanType;
                deserializationContext.reportBadDefinition(javaType, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", ClassUtil.getTypeDescription(javaType), ClassUtil.classNameOf(_valueInstantiator)));
            }
            _delegateDeserializer = this._findDelegateDeserializer(deserializationContext, delegateType, _valueInstantiator.getDelegateCreator());
        }
        if (_valueInstantiator.canCreateUsingArrayDelegate()) {
            final JavaType arrayDelegateType = _valueInstantiator.getArrayDelegateType(deserializationContext.getConfig());
            if (null == arrayDelegateType) {
                final JavaType javaType2 = _beanType;
                deserializationContext.reportBadDefinition(javaType2, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", ClassUtil.getTypeDescription(javaType2), ClassUtil.classNameOf(_valueInstantiator)));
            }
            _arrayDelegateDeserializer = this._findDelegateDeserializer(deserializationContext, arrayDelegateType, _valueInstantiator.getArrayDelegateCreator());
        }
        if (null != fromObjectArguments) {
            _propertyBasedCreator = PropertyBasedCreator.construct(deserializationContext, _valueInstantiator, fromObjectArguments, _beanProperties);
        }
        if (null != builder) {
            _externalTypeIdHandler = builder.build(_beanProperties);
            _nonStandardCreation = true;
        }
        _unwrappedPropertyHandler = unwrappedPropertyHandler;
        if (null != unwrappedPropertyHandler) {
            _nonStandardCreation = true;
        }
        if (_vanillaProcessing && !_nonStandardCreation) {
            z = true;
        }
        _vanillaProcessing = z;
    }
    protected void _replaceProperty(final BeanPropertyMap beanPropertyMap, final SettableBeanProperty[] settableBeanPropertyArr, final SettableBeanProperty settableBeanProperty, final SettableBeanProperty settableBeanProperty2) {
        beanPropertyMap.replace(settableBeanProperty, settableBeanProperty2);
        if (null != settableBeanPropertyArr) {
            final int length = settableBeanPropertyArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (settableBeanPropertyArr[i2] == settableBeanProperty) {
                    settableBeanPropertyArr[i2] = settableBeanProperty2;
                    return;
                }
            }
        }
    }
    private JsonDeserializer<Object> _findDelegateDeserializer(final DeserializationContext deserializationContext, final JavaType javaType, final AnnotatedWithParams annotatedWithParams) throws JsonMappingException {
        final JsonDeserializer<?> jsonDeserializerHandleSecondaryContextualization;
        final BeanProperty.Std std = new BeanProperty.Std(BeanDeserializerBase.TEMP_PROPERTY_NAME, javaType, null, annotatedWithParams, PropertyMetadata.STD_OPTIONAL);
        TypeDeserializer typeDeserializerFindTypeDeserializer = javaType.getTypeHandler();
        if (null == typeDeserializerFindTypeDeserializer) {
            typeDeserializerFindTypeDeserializer = deserializationContext.getConfig().findTypeDeserializer(javaType);
        }
        final JsonDeserializer<?> jsonDeserializer = javaType.getValueHandler();
        if (null == jsonDeserializer) {
            jsonDeserializerHandleSecondaryContextualization = this.findDeserializer(deserializationContext, javaType, std);
        } else {
            jsonDeserializerHandleSecondaryContextualization = deserializationContext.handleSecondaryContextualization(jsonDeserializer, std, javaType);
        }
        return null != typeDeserializerFindTypeDeserializer ? new TypeWrappedDeserializer(typeDeserializerFindTypeDeserializer.forProperty(std), jsonDeserializerHandleSecondaryContextualization) : (JsonDeserializer<Object>) jsonDeserializerHandleSecondaryContextualization;
    }
    protected JsonDeserializer<Object> findConvertingDeserializer(final DeserializationContext deserializationContext, final SettableBeanProperty settableBeanProperty) throws JsonMappingException {
        final Object objFindDeserializationConverter;
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (null == annotationIntrospector || null == (objFindDeserializationConverter = annotationIntrospector.findDeserializationConverter(settableBeanProperty.getMember()))) {
            return null;
        }
        final Converter<Object, Object> converterConverterInstance = deserializationContext.converterInstance(settableBeanProperty.getMember(), objFindDeserializationConverter);
        final JavaType inputType = converterConverterInstance.getInputType(deserializationContext.getTypeFactory());
        return new StdDelegatingDeserializer(converterConverterInstance, inputType, deserializationContext.findNonContextualValueDeserializer(inputType));
    }
    public JsonDeserializer<?> createContextual(final DeserializationContext deserializationContext, final BeanProperty beanProperty) throws JsonMappingException {
        final BeanPropertyMap beanPropertyMap;
        final BeanPropertyMap beanPropertyMapWithCaseInsensitivity;
        final ObjectIdInfo objectIdInfoFindObjectIdInfo;
        final JavaType type;
        final SettableBeanProperty settableBeanProperty;
        final ObjectIdGenerator<?> objectIdGeneratorObjectIdGeneratorInstance;
        ObjectIdReader objectIdReaderConstruct = _objectIdReader;
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        final AnnotatedMember member = _neitherNull(beanProperty, annotationIntrospector) ? beanProperty.getMember() : null;
        if (null != member && null != (objectIdInfoFindObjectIdInfo = annotationIntrospector.findObjectIdInfo(member))) {
            final ObjectIdInfo objectIdInfoFindObjectReferenceInfo = annotationIntrospector.findObjectReferenceInfo(member, objectIdInfoFindObjectIdInfo);
            final Class<? extends ObjectIdGenerator<?>> generatorType = objectIdInfoFindObjectReferenceInfo.getGeneratorType();
            final ObjectIdResolver objectIdResolverObjectIdResolverInstance = deserializationContext.objectIdResolverInstance(member, objectIdInfoFindObjectReferenceInfo);
            if (ObjectIdGeneratorsPropertyGenerator.class == generatorType) {
                final PropertyName propertyName = objectIdInfoFindObjectReferenceInfo.getPropertyName();
                final SettableBeanProperty settableBeanPropertyFindProperty = this.findProperty(propertyName);
                if (null == settableBeanPropertyFindProperty) {
                    deserializationContext.reportBadDefinition(_beanType, String.format("Invalid Object Id definition for %s: cannot find property with name %s", ClassUtil.nameOf(this.handledType()), ClassUtil.name(propertyName)));
                }
                type = settableBeanPropertyFindProperty.getType();
                settableBeanProperty = settableBeanPropertyFindProperty;
                objectIdGeneratorObjectIdGeneratorInstance = new PropertyBasedObjectIdGenerator(objectIdInfoFindObjectReferenceInfo.getScope());
            } else {
                type = deserializationContext.getTypeFactory().findTypeParameters(deserializationContext.constructType(generatorType), ObjectIdGenerator.class)[0];
                settableBeanProperty = null;
                objectIdGeneratorObjectIdGeneratorInstance = deserializationContext.objectIdGeneratorInstance(member, objectIdInfoFindObjectReferenceInfo);
            }
            final JavaType javaType = type;
            objectIdReaderConstruct = ObjectIdReader.construct(javaType, objectIdInfoFindObjectReferenceInfo.getPropertyName(), objectIdGeneratorObjectIdGeneratorInstance, deserializationContext.findRootValueDeserializer(javaType), settableBeanProperty, objectIdResolverObjectIdResolverInstance);
        }
        BeanDeserializerBase beanDeserializerBaseWithObjectIdReader = (null == objectIdReaderConstruct || objectIdReaderConstruct == _objectIdReader) ? this : this.withObjectIdReader(objectIdReaderConstruct);
        if (null != member) {
            beanDeserializerBaseWithObjectIdReader = this._handleByNameInclusion(deserializationContext, annotationIntrospector, beanDeserializerBaseWithObjectIdReader, member);
        }
        final JsonFormat.Value valueFindFormatOverrides = this.findFormatOverrides(deserializationContext, beanProperty, this.handledType());
        if (null != valueFindFormatOverrides) {
            shape = valueFindFormatOverrides.hasShape() ? valueFindFormatOverrides.getShape() : null;
            final Boolean feature = valueFindFormatOverrides.getFeature(JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
            if (null != feature && (beanPropertyMapWithCaseInsensitivity = (beanPropertyMap = _beanProperties).withCaseInsensitivity(feature.booleanValue())) != beanPropertyMap) {
                beanDeserializerBaseWithObjectIdReader = beanDeserializerBaseWithObjectIdReader.withBeanProperties(beanPropertyMapWithCaseInsensitivity);
            }
        }
        if (null == shape) {
            shape = _serializationShape;
        }
        return JsonFormat.Shape.ARRAY == shape ? beanDeserializerBaseWithObjectIdReader.asArrayDeserializer() : beanDeserializerBaseWithObjectIdReader;
    }
    protected BeanDeserializerBase _handleByNameInclusion(final DeserializationContext deserializationContext, final AnnotationIntrospector annotationIntrospector, BeanDeserializerBase beanDeserializerBase, final AnnotatedMember annotatedMember) throws JsonMappingException {
        final DeserializationConfig config = deserializationContext.getConfig();
        final JsonIgnoreProperties.Value valueFindPropertyIgnoralByName = annotationIntrospector.findPropertyIgnoralByName(config, annotatedMember);
        if (valueFindPropertyIgnoralByName.getIgnoreUnknown() && !_ignoreAllUnknown) {
            beanDeserializerBase = beanDeserializerBase.withIgnoreAllUnknown(true);
        }
        Set<String> setFindIgnoredForDeserialization = valueFindPropertyIgnoralByName.findIgnoredForDeserialization();
        final Set<String> set = beanDeserializerBase._ignorableProps;
        if (setFindIgnoredForDeserialization.isEmpty()) {
            setFindIgnoredForDeserialization = set;
        } else if (null != set && !set.isEmpty()) {
            final HashSet hashSet = new HashSet(set);
            hashSet.addAll(setFindIgnoredForDeserialization);
            setFindIgnoredForDeserialization = hashSet;
        }
        final Set<String> set2 = beanDeserializerBase._includableProps;
        final Set<String> setCombineNamesToInclude = IgnorePropertiesUtil.combineNamesToInclude(set2, annotationIntrospector.findPropertyInclusionByName(config, annotatedMember).getIncluded());
        return (setFindIgnoredForDeserialization == set && setCombineNamesToInclude == set2) ? beanDeserializerBase : beanDeserializerBase.withByNameInclusion(setFindIgnoredForDeserialization, setCombineNamesToInclude);
    }
    protected SettableBeanProperty _resolveManagedReferenceProperty(final DeserializationContext deserializationContext, final SettableBeanProperty settableBeanProperty) throws JsonMappingException {
        final String managedReferenceName = settableBeanProperty.getManagedReferenceName();
        if (null == managedReferenceName) {
            return settableBeanProperty;
        }
        final SettableBeanProperty settableBeanPropertyFindBackReference = settableBeanProperty.getValueDeserializer().findBackReference(managedReferenceName);
        if (null == settableBeanPropertyFindBackReference) {
            deserializationContext.reportBadDefinition(_beanType, String.format("Cannot handle managed/back reference %s: no back reference property found from type %s", ClassUtil.name(managedReferenceName), ClassUtil.getTypeDescription(settableBeanProperty.getType())));
        }
        final JavaType javaType = _beanType;
        final JavaType type = settableBeanPropertyFindBackReference.getType();
        final boolean zIsContainerType = settableBeanProperty.getType().isContainerType();
        if (!type.getRawClass().isAssignableFrom(javaType.getRawClass())) {
            deserializationContext.reportBadDefinition(_beanType, String.format("Cannot handle managed/back reference %s: back reference type (%s) not compatible with managed type (%s)", ClassUtil.name(managedReferenceName), ClassUtil.getTypeDescription(type), javaType.getRawClass().getName()));
        }
        return new ManagedReferenceProperty(settableBeanProperty, managedReferenceName, settableBeanPropertyFindBackReference, zIsContainerType);
    }
    protected SettableBeanProperty _resolvedObjectIdProperty(final DeserializationContext deserializationContext, final SettableBeanProperty settableBeanProperty) throws JsonMappingException {
        final ObjectIdInfo objectIdInfo = settableBeanProperty.getObjectIdInfo();
        final JsonDeserializer<Object> valueDeserializer = settableBeanProperty.getValueDeserializer();
        return (null == objectIdInfo && null == (valueDeserializer == null ? null : valueDeserializer.getObjectIdReader())) ? settableBeanProperty : new ObjectIdReferenceProperty(settableBeanProperty, objectIdInfo);
    }
    protected NameTransformer _findPropertyUnwrapper(final DeserializationContext deserializationContext, final SettableBeanProperty settableBeanProperty) throws JsonMappingException {
        final NameTransformer nameTransformerFindUnwrappingNameTransformer;
        final AnnotatedMember member = settableBeanProperty.getMember();
        if (null == member || null == (nameTransformerFindUnwrappingNameTransformer = deserializationContext.getAnnotationIntrospector().findUnwrappingNameTransformer(member))) {
            return null;
        }
        if (settableBeanProperty instanceof CreatorProperty) {
            deserializationContext.reportBadDefinition(this._beanType, String.format("Cannot define Creator property \"%s\" as `@JsonUnwrapped`: combination not yet supported", settableBeanProperty.getName()));
        }
        return nameTransformerFindUnwrappingNameTransformer;
    }
    protected SettableBeanProperty _resolveInnerClassValuedProperty(final DeserializationContext deserializationContext, final SettableBeanProperty settableBeanProperty) throws SecurityException {
        final Class<?> rawClass;
        final Class<?> outerClass;
        final JsonDeserializer<Object> valueDeserializer = settableBeanProperty.getValueDeserializer();
        if ((valueDeserializer instanceof BeanDeserializerBase) && !((BeanDeserializerBase) valueDeserializer)._valueInstantiator.canCreateUsingDefault() && null != (outerClass = ClassUtil.getOuterClass((rawClass = settableBeanProperty.getType().getRawClass()))) && outerClass == _beanType.getRawClass()) {
            for (final Constructor<?> constructor : rawClass.getConstructors()) {
                final Class<?>[] parameterTypes = constructor.getParameterTypes();
                if (1 == parameterTypes.length && outerClass.equals(parameterTypes[0])) {
                    if (deserializationContext.canOverrideAccessModifiers()) {
                        ClassUtil.checkAndFixAccess(constructor, deserializationContext.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
                    }
                    return new InnerClassProperty(settableBeanProperty, constructor);
                }
            }
        }
        return settableBeanProperty;
    }
    protected SettableBeanProperty _resolveMergeAndNullSettings(final DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty, final PropertyMetadata propertyMetadata) throws JsonMappingException {
        final PropertyMetadata.MergeInfo mergeInfo = propertyMetadata.getMergeInfo();
        if (null != mergeInfo) {
            final JsonDeserializer<Object> valueDeserializer = settableBeanProperty.getValueDeserializer();
            final Boolean boolSupportsUpdate = valueDeserializer.supportsUpdate(deserializationContext.getConfig());
            if (null == boolSupportsUpdate) {
                if (mergeInfo.fromDefaults) {
                    return settableBeanProperty;
                }
            } else if (!boolSupportsUpdate.booleanValue()) {
                if (!mergeInfo.fromDefaults) {
                    deserializationContext.handleBadMerge(valueDeserializer);
                }
                return settableBeanProperty;
            }
            final AnnotatedMember annotatedMember = mergeInfo.getter;
            annotatedMember.fixAccess(deserializationContext.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            if (!(settableBeanProperty instanceof SetterlessProperty)) {
                settableBeanProperty = MergingSettableBeanProperty.construct(settableBeanProperty, annotatedMember);
            }
        }
        final NullValueProvider nullValueProviderFindValueNullProvider = this.findValueNullProvider(deserializationContext, settableBeanProperty, propertyMetadata);
        return null != nullValueProviderFindValueNullProvider ? settableBeanProperty.withNullProvider(nullValueProviderFindValueNullProvider) : settableBeanProperty;
    }
    public AccessPattern getNullAccessPattern() {
        return AccessPattern.ALWAYS_NULL;
    }
    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.DYNAMIC;
    }
    public Object getEmptyValue(final DeserializationContext deserializationContext) throws JsonMappingException {
        try {
            return _valueInstantiator.createUsingDefault(deserializationContext);
        } catch (final IOException e2) {
            return ClassUtil.throwAsMappingException(deserializationContext, e2);
        }
    }
    public boolean isCaseInsensitive() {
        return _beanProperties.isCaseInsensitive();
    }
    public Boolean supportsUpdate(final DeserializationConfig deserializationConfig) {
        return Boolean.TRUE;
    }
    public Class<?> handledType() {
        return _beanType.getRawClass();
    }
    public ObjectIdReader getObjectIdReader() {
        return _objectIdReader;
    }
    public boolean hasProperty(final String str) {
        return null != this._beanProperties.find(str);
    }
    public boolean hasViews() {
        return _needViewProcesing;
    }
    public int getPropertyCount() {
        return _beanProperties.size();
    }
    public Collection<Object> getKnownPropertyNames() {
        final ArrayList arrayList = new ArrayList();
        final Iterator<SettableBeanProperty> it = _beanProperties.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getName());
        }
        return arrayList;
    }
    public final Class<?> getBeanClass() {
        return _beanType.getRawClass();
    }
    public JavaType getValueType() {
        return _beanType;
    }
    public LogicalType logicalType() {
        return LogicalType.POJO;
    }
    public Iterator<SettableBeanProperty> properties() {
        final BeanPropertyMap beanPropertyMap = _beanProperties;
        if (null == beanPropertyMap) {
            throw new IllegalStateException("Can only call after BeanDeserializer has been resolved");
        }
        return beanPropertyMap.iterator();
    }
    public Object creatorProperties() {
        final PropertyBasedCreator propertyBasedCreator = _propertyBasedCreator;
        if (null == propertyBasedCreator) {
            return Collections.emptyIterator();
        }
        return propertyBasedCreator.properties().iterator();
    }
    public SettableBeanProperty findProperty(final PropertyName propertyName) {
        return this.findProperty(propertyName.getSimpleName());
    }
    public SettableBeanProperty findProperty(final String str) {
        final PropertyBasedCreator propertyBasedCreator;
        final BeanPropertyMap beanPropertyMap = _beanProperties;
        final SettableBeanProperty settableBeanPropertyFind = null == beanPropertyMap ? null : beanPropertyMap.find(str);
        return (null != settableBeanPropertyFind || null == (propertyBasedCreator = this._propertyBasedCreator)) ? settableBeanPropertyFind : propertyBasedCreator.findCreatorProperty(str);
    }
    public SettableBeanProperty findProperty(final int i2) {
        final PropertyBasedCreator propertyBasedCreator;
        final BeanPropertyMap beanPropertyMap = _beanProperties;
        final SettableBeanProperty settableBeanPropertyFind = null == beanPropertyMap ? null : beanPropertyMap.find(i2);
        return (null != settableBeanPropertyFind || null == (propertyBasedCreator = this._propertyBasedCreator)) ? settableBeanPropertyFind : propertyBasedCreator.findCreatorProperty(i2);
    }
    public SettableBeanProperty findBackReference(final String str) {
        final Map<String, SettableBeanProperty> map = _backRefs;
        if (null == map) {
            return null;
        }
        return map.get(str);
    }
    public ValueInstantiator getValueInstantiator() {
        return _valueInstantiator;
    }
    public void replaceProperty(final SettableBeanProperty settableBeanProperty, final SettableBeanProperty settableBeanProperty2) {
        _beanProperties.replace(settableBeanProperty, settableBeanProperty2);
    }
    public Object deserializeWithType(final JsonParser jsonParser, final DeserializationContext deserializationContext, final TypeDeserializer typeDeserializer) throws IOException {
        final Object objectId;
        if (null != this._objectIdReader) {
            if (jsonParser.canReadObjectId() && null != (objectId = jsonParser.getObjectId())) {
                return this._handleTypedObjectId(jsonParser, deserializationContext, typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext), objectId);
            }
            JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            if (null != jsonTokenCurrentToken) {
                if (jsonTokenCurrentToken.isScalarValue()) {
                    return this.deserializeFromObjectId(jsonParser, deserializationContext);
                }
                if (JsonToken.START_OBJECT == jsonTokenCurrentToken) {
                    jsonTokenCurrentToken = jsonParser.nextToken();
                }
                if (JsonToken.FIELD_NAME == jsonTokenCurrentToken && _objectIdReader.maySerializeAsObject() && _objectIdReader.isValidReferencePropertyName(jsonParser.currentName(), jsonParser)) {
                    return this.deserializeFromObjectId(jsonParser, deserializationContext);
                }
            }
        }
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }
    protected Object _handleTypedObjectId(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj, Object obj2) throws IOException {
        final JsonDeserializer<Object> deserializer = _objectIdReader.getDeserializer();
        if (deserializer.handledType() != obj2.getClass()) {
            obj2 = this._convertObjectId(jsonParser, deserializationContext, obj2, deserializer);
        }
        final ObjectIdReader objectIdReader = _objectIdReader;
        deserializationContext.findObjectId(obj2, objectIdReader.generator, objectIdReader.resolver).bindItem(obj);
        final SettableBeanProperty settableBeanProperty = _objectIdReader.idProperty;
        try {
            return null != settableBeanProperty ? settableBeanProperty.setAndReturn(obj, obj2) : obj;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    protected Object _convertObjectId(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj, final JsonDeserializer<Object> jsonDeserializer) throws IOException {
        final TokenBuffer tokenBuffer = new TokenBuffer(jsonParser, deserializationContext);
        if (obj instanceof String) {
            tokenBuffer.writeString((String) obj);
        } else if (obj instanceof Long) {
            tokenBuffer.writeNumber(((Long) obj).longValue());
        } else if (obj instanceof Integer) {
            tokenBuffer.writeNumber(((Integer) obj).intValue());
        } else {
            tokenBuffer.writeObject(obj);
        }
        final JsonParser jsonParserAsParser = tokenBuffer.asParser();
        jsonParserAsParser.nextToken();
        return jsonDeserializer.deserialize(jsonParserAsParser, deserializationContext);
    }
    protected Object deserializeWithObjectId(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return this.deserializeFromObject(jsonParser, deserializationContext);
    }
    protected Object deserializeFromObjectId(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final Object objectReference = _objectIdReader.readObjectReference(jsonParser, deserializationContext);
        final ObjectIdReader objectIdReader = _objectIdReader;
        final ReadableObjectId readableObjectIdFindObjectId = deserializationContext.findObjectId(objectReference, objectIdReader.generator, objectIdReader.resolver);
        final Object objResolve = readableObjectIdFindObjectId.resolve();
        if (null != objResolve) {
            return objResolve;
        }
        throw new UnresolvedForwardReference(jsonParser, "Could not resolve Object Id [" + objectReference + "] (for " + _beanType + ").", jsonParser.getCurrentLocation(), readableObjectIdFindObjectId);
    }
    protected Object deserializeFromObjectUsingNonDefault(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        final JsonDeserializer<Object> jsonDeserializer_delegateDeserializer = this._delegateDeserializer();
        if (null != jsonDeserializer_delegateDeserializer) {
            final Object objCreateUsingDelegate = _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer_delegateDeserializer.deserialize(jsonParser, deserializationContext));
            if (null != this._injectables) {
                this.injectValues(deserializationContext, objCreateUsingDelegate);
            }
            return objCreateUsingDelegate;
        }
        if (null != this._propertyBasedCreator) {
            return this._deserializeUsingPropertyBased(jsonParser, deserializationContext);
        }
        final Class<?> rawClass = _beanType.getRawClass();
        if (ClassUtil.isNonStaticInnerClass(rawClass)) {
            return deserializationContext.handleMissingInstantiator(rawClass, null, jsonParser, "non-static inner classes like this can only by instantiated using default, no-argument constructor");
        }
        return deserializationContext.handleMissingInstantiator(rawClass, this._valueInstantiator, jsonParser, "cannot deserialize from Object value (no delegate- or property-based Creator)");
    }
    public Object deserializeFromNumber(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        if (null != this._objectIdReader) {
            return this.deserializeFromObjectId(jsonParser, deserializationContext);
        }
        final JsonDeserializer<Object> jsonDeserializer_delegateDeserializer = this._delegateDeserializer();
        final JsonParser.NumberType numberType = jsonParser.getNumberType();
        if (JsonParser.NumberType.INT == numberType) {
            if (null != jsonDeserializer_delegateDeserializer && !_valueInstantiator.canCreateFromInt()) {
                final Object objCreateUsingDelegate = _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer_delegateDeserializer.deserialize(jsonParser, deserializationContext));
                if (null != this._injectables) {
                    this.injectValues(deserializationContext, objCreateUsingDelegate);
                }
                return objCreateUsingDelegate;
            }
            return _valueInstantiator.createFromInt(deserializationContext, jsonParser.getIntValue());
        }
        if (JsonParser.NumberType.LONG == numberType) {
            if (null != jsonDeserializer_delegateDeserializer && !_valueInstantiator.canCreateFromInt()) {
                final Object objCreateUsingDelegate2 = _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer_delegateDeserializer.deserialize(jsonParser, deserializationContext));
                if (null != this._injectables) {
                    this.injectValues(deserializationContext, objCreateUsingDelegate2);
                }
                return objCreateUsingDelegate2;
            }
            return _valueInstantiator.createFromLong(deserializationContext, jsonParser.getLongValue());
        }
        if (JsonParser.NumberType.BIG_INTEGER == numberType) {
            if (null != jsonDeserializer_delegateDeserializer && !_valueInstantiator.canCreateFromBigInteger()) {
                final Object objCreateUsingDelegate3 = _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer_delegateDeserializer.deserialize(jsonParser, deserializationContext));
                if (null != this._injectables) {
                    this.injectValues(deserializationContext, objCreateUsingDelegate3);
                }
                return objCreateUsingDelegate3;
            }
            return _valueInstantiator.createFromBigInteger(deserializationContext, jsonParser.getBigIntegerValue());
        }
        return deserializationContext.handleMissingInstantiator(this.handledType(), this._valueInstantiator, jsonParser, "no suitable creator method found to deserialize from Number value (%s)", jsonParser.getNumberValue());
    }
    public Object deserializeFromString(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        if (null != this._objectIdReader) {
            return this.deserializeFromObjectId(jsonParser, deserializationContext);
        }
        final JsonDeserializer<Object> jsonDeserializer_delegateDeserializer = this._delegateDeserializer();
        if (null != jsonDeserializer_delegateDeserializer && !_valueInstantiator.canCreateFromString()) {
            final Object objCreateUsingDelegate = _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer_delegateDeserializer.deserialize(jsonParser, deserializationContext));
            if (null != this._injectables) {
                this.injectValues(deserializationContext, objCreateUsingDelegate);
            }
            return objCreateUsingDelegate;
        }
        return this._deserializeFromString(jsonParser, deserializationContext);
    }
    public Object deserializeFromDouble(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        final JsonParser.NumberType numberType = jsonParser.getNumberType();
        if (JsonParser.NumberType.DOUBLE == numberType || JsonParser.NumberType.FLOAT == numberType) {
            final JsonDeserializer<Object> jsonDeserializer_delegateDeserializer = this._delegateDeserializer();
            if (null != jsonDeserializer_delegateDeserializer && !_valueInstantiator.canCreateFromDouble()) {
                final Object objCreateUsingDelegate = _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer_delegateDeserializer.deserialize(jsonParser, deserializationContext));
                if (null != this._injectables) {
                    this.injectValues(deserializationContext, objCreateUsingDelegate);
                }
                return objCreateUsingDelegate;
            }
            return _valueInstantiator.createFromDouble(deserializationContext, jsonParser.getDoubleValue());
        }
        if (JsonParser.NumberType.BIG_DECIMAL == numberType) {
            final JsonDeserializer<Object> jsonDeserializer_delegateDeserializer2 = this._delegateDeserializer();
            if (null != jsonDeserializer_delegateDeserializer2 && !_valueInstantiator.canCreateFromBigDecimal()) {
                final Object objCreateUsingDelegate2 = _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer_delegateDeserializer2.deserialize(jsonParser, deserializationContext));
                if (null != this._injectables) {
                    this.injectValues(deserializationContext, objCreateUsingDelegate2);
                }
                return objCreateUsingDelegate2;
            }
            return _valueInstantiator.createFromBigDecimal(deserializationContext, jsonParser.getDecimalValue());
        }
        return deserializationContext.handleMissingInstantiator(this.handledType(), this._valueInstantiator, jsonParser, "no suitable creator method found to deserialize from Number value (%s)", jsonParser.getNumberValue());
    }
    public Object deserializeFromBoolean(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        final JsonDeserializer<Object> jsonDeserializer_delegateDeserializer = this._delegateDeserializer();
        if (null != jsonDeserializer_delegateDeserializer && !_valueInstantiator.canCreateFromBoolean()) {
            final Object objCreateUsingDelegate = _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer_delegateDeserializer.deserialize(jsonParser, deserializationContext));
            if (null != this._injectables) {
                this.injectValues(deserializationContext, objCreateUsingDelegate);
            }
            return objCreateUsingDelegate;
        }
        return _valueInstantiator.createFromBoolean(deserializationContext, JsonToken.VALUE_TRUE == jsonParser.currentToken());
    }
    public Object deserializeFromArray(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        return this._deserializeFromArray(jsonParser, deserializationContext);
    }
    public Object deserializeFromEmbedded(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        if (null != this._objectIdReader) {
            return this.deserializeFromObjectId(jsonParser, deserializationContext);
        }
        final JsonDeserializer<Object> jsonDeserializer_delegateDeserializer = this._delegateDeserializer();
        if (null != jsonDeserializer_delegateDeserializer && !_valueInstantiator.canCreateFromString()) {
            final Object objCreateUsingDelegate = _valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer_delegateDeserializer.deserialize(jsonParser, deserializationContext));
            if (null != this._injectables) {
                this.injectValues(deserializationContext, objCreateUsingDelegate);
            }
            return objCreateUsingDelegate;
        }
        final Object embeddedObject = jsonParser.getEmbeddedObject();
        return (null == embeddedObject || _beanType.isTypeOrSuperTypeOf(embeddedObject.getClass())) ? embeddedObject : deserializationContext.handleWeirdNativeValue(_beanType, embeddedObject, jsonParser);
    }
    protected final JsonDeserializer<Object> _delegateDeserializer() {
        final JsonDeserializer<Object> jsonDeserializer = _delegateDeserializer;
        return null == jsonDeserializer ? _arrayDelegateDeserializer : jsonDeserializer;
    }
    protected void injectValues(final DeserializationContext deserializationContext, final Object obj) throws UnsupportedOperationException, IOException, IllegalArgumentException {
        for (final ValueInjector valueInjector : _injectables) {
            valueInjector.inject(deserializationContext, obj);
        }
    }
    protected Object handleUnknownProperties(final DeserializationContext deserializationContext, final Object obj, final TokenBuffer tokenBuffer) throws IOException {
        tokenBuffer.writeEndObject();
        final JsonParser jsonParserAsParser = tokenBuffer.asParser();
        while (JsonToken.END_OBJECT != jsonParserAsParser.nextToken()) {
            final String strCurrentName = jsonParserAsParser.currentName();
            jsonParserAsParser.nextToken();
            this.handleUnknownProperty(jsonParserAsParser, deserializationContext, obj, strCurrentName);
        }
        return obj;
    }
    protected void handleUnknownVanilla(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj, final String str) throws IOException {
        if (IgnorePropertiesUtil.shouldIgnore(str, _ignorableProps, _includableProps)) {
            this.handleIgnoredProperty(jsonParser, deserializationContext, obj, str);
            return;
        }
        final SettableAnyProperty settableAnyProperty = _anySetter;
        if (null != settableAnyProperty) {
            try {
                settableAnyProperty.deserializeAndSet(jsonParser, deserializationContext, obj, str);
                return;
            } catch (final Exception e2) {
                this.wrapAndThrow(e2, obj, str, deserializationContext);
                return;
            }
        }
        this.handleUnknownProperty(jsonParser, deserializationContext, obj, str);
    }
    protected void handleUnknownProperty(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj, final String str) throws IOException {
        if (_ignoreAllUnknown) {
            jsonParser.skipChildren();
            return;
        }
        if (IgnorePropertiesUtil.shouldIgnore(str, _ignorableProps, _includableProps)) {
            this.handleIgnoredProperty(jsonParser, deserializationContext, obj, str);
        }
        super.handleUnknownProperty(jsonParser, deserializationContext, obj, str);
    }
    protected void handleIgnoredProperty(final JsonParser jsonParser, final DeserializationContext deserializationContext, final Object obj, final String str) throws IOException {
        if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)) {
            throw IgnoredPropertyException.from(jsonParser, obj, str, this.getKnownPropertyNames());
        }
        jsonParser.skipChildren();
    }
    protected Object handlePolymorphic(final JsonParser jsonParser, final DeserializationContext deserializationContext, Object obj, final TokenBuffer tokenBuffer) throws IOException {
        final JsonDeserializer<Object> jsonDeserializer_findSubclassDeserializer = this._findSubclassDeserializer(deserializationContext, obj, tokenBuffer);
        if (null == jsonDeserializer_findSubclassDeserializer) {
            if (null != tokenBuffer) {
                obj = this.handleUnknownProperties(deserializationContext, obj, tokenBuffer);
            }
            return null != jsonParser ? this.deserialize(jsonParser, deserializationContext, obj) : obj;
        }
        if (null != tokenBuffer) {
            tokenBuffer.writeEndObject();
            final JsonParser jsonParserAsParser = tokenBuffer.asParser();
            jsonParserAsParser.nextToken();
            obj = jsonDeserializer_findSubclassDeserializer.deserialize(jsonParserAsParser, deserializationContext, obj);
        }
        return null != jsonParser ? jsonDeserializer_findSubclassDeserializer.deserialize(jsonParser, deserializationContext, obj) : obj;
    }
    protected JsonDeserializer<Object> _findSubclassDeserializer(final DeserializationContext deserializationContext, final Object obj, final TokenBuffer tokenBuffer) throws IOException {
        final JsonDeserializer<Object> jsonDeserializer;
        synchronized (this) {
            final HashMap<ClassKey, JsonDeserializer<Object>> map = _subDeserializers;
            jsonDeserializer = null == map ? null : map.get(new ClassKey(obj.getClass()));
        }
        if (null != jsonDeserializer) {
            return jsonDeserializer;
        }
        final JsonDeserializer<Object> jsonDeserializerFindRootValueDeserializer = deserializationContext.findRootValueDeserializer(deserializationContext.constructType(obj.getClass()));
        if (null != jsonDeserializerFindRootValueDeserializer) {
            synchronized (this) {
                if (this._subDeserializers == null) {
                    this._subDeserializers = new HashMap<>();
                }
                this._subDeserializers.put(new ClassKey(obj.getClass()), jsonDeserializerFindRootValueDeserializer);
            }
        }
        return jsonDeserializerFindRootValueDeserializer;
    }
    public void wrapAndThrow(final Throwable th, final Object obj, final String str, final DeserializationContext deserializationContext) throws IOException {
        throw JsonMappingException.wrapWithPath(this.throwOrReturnThrowable(th, deserializationContext), obj, str);
    }
    private Throwable throwOrReturnThrowable(Throwable th, final DeserializationContext deserializationContext) throws IOException {
        while ((th instanceof InvocationTargetException) && null != th.getCause()) {
            th = th.getCause();
        }
        ClassUtil.throwIfError(th);
        final boolean z = null == deserializationContext || deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
        if (th instanceof IOException) {
            if (!z || !(th instanceof JsonProcessingException)) {
                throw ((IOException) th);
            }
        } else if (!z) {
            ClassUtil.throwIfRTE(th);
        }
        return th;
    }
    protected Object wrapInstantiationProblem(Throwable th, final DeserializationContext deserializationContext) throws IOException {
        while ((th instanceof InvocationTargetException) && null != th.getCause()) {
            th = th.getCause();
        }
        ClassUtil.throwIfError(th);
        if (th instanceof IOException) {
            throw ((IOException) th);
        }
        if (null != deserializationContext && !deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS)) {
            ClassUtil.throwIfRTE(th);
        }
        return deserializationContext.handleInstantiationProblem(_beanType.getRawClass(), null, th);
    }
}
