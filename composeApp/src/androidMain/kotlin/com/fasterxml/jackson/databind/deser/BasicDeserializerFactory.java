package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.databind.deser.impl.JDKValueInstantiators;
import com.fasterxml.jackson.databind.deser.impl.JavaUtilCollectionsDeserializers;
import com.fasterxml.jackson.databind.deser.std.*;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.*;
import com.fasterxml.jackson.databind.jdk14.JDK14Util;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.*;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BasicDeserializerFactory extends DeserializerFactory implements Serializable {
    protected final DeserializerFactoryConfig _factoryConfig;
    private static final Class<?> CLASS_OBJECT = Object.class;
    private static final Class<?> CLASS_STRING = String.class;
    private static final Class<?> CLASS_CHAR_SEQUENCE = CharSequence.class;
    private static final Class<?> CLASS_ITERABLE = Iterable.class;
    private static final Class<?> CLASS_MAP_ENTRY = Map.Entry.class;
    private static final Class<?> CLASS_SERIALIZABLE = Serializable.class;
    protected static final PropertyName UNWRAPPED_CREATOR_PARAM_NAME = new PropertyName("@JsonUnwrapped");
    protected abstract DeserializerFactory withConfig(DeserializerFactoryConfig deserializerFactoryConfig);
    protected BasicDeserializerFactory(final DeserializerFactoryConfig deserializerFactoryConfig) {
        _factoryConfig = deserializerFactoryConfig;
    }
    public DeserializerFactoryConfig getFactoryConfig() {
        return _factoryConfig;
    }
    public final DeserializerFactory withAdditionalDeserializers(final Deserializers deserializers) {
        return this.withConfig(_factoryConfig.withAdditionalDeserializers(deserializers));
    }
    public final DeserializerFactory withAdditionalKeyDeserializers(final KeyDeserializers keyDeserializers) {
        return this.withConfig(_factoryConfig.withAdditionalKeyDeserializers(keyDeserializers));
    }
    public final DeserializerFactory withDeserializerModifier(final BeanDeserializerModifier beanDeserializerModifier) {
        return this.withConfig(_factoryConfig.withDeserializerModifier(beanDeserializerModifier));
    }
    public final DeserializerFactory withAbstractTypeResolver(final AbstractTypeResolver abstractTypeResolver) {
        return this.withConfig(_factoryConfig.withAbstractTypeResolver(abstractTypeResolver));
    }
    public final DeserializerFactory withValueInstantiators(final ValueInstantiators valueInstantiators) {
        return this.withConfig(_factoryConfig.withValueInstantiators(valueInstantiators));
    }
    public JavaType mapAbstractType(final DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        JavaType javaType_mapAbstractType2;
        while (true) {
            javaType_mapAbstractType2 = this._mapAbstractType2(deserializationConfig, javaType);
            if (null == javaType_mapAbstractType2) {
                return javaType;
            }
            final Class<?> rawClass = javaType.getRawClass();
            final Class<?> rawClass2 = javaType_mapAbstractType2.getRawClass();
            if (rawClass == rawClass2 || !rawClass.isAssignableFrom(rawClass2)) {
                break;
            }
            javaType = javaType_mapAbstractType2;
        }
        throw new IllegalArgumentException("Invalid abstract type resolution from " + javaType + " to " + javaType_mapAbstractType2 + ": latter is not a subtype of former");
    }
    private JavaType _mapAbstractType2(final DeserializationConfig deserializationConfig, final JavaType javaType) throws JsonMappingException {
        final Class<?> rawClass = javaType.getRawClass();
        if (!_factoryConfig.hasAbstractTypeResolvers()) {
            return null;
        }
        final Iterator<AbstractTypeResolver> it = _factoryConfig.abstractTypeResolvers().iterator();
        while (it.hasNext()) {
            final JavaType javaTypeFindTypeMapping = it.next().findTypeMapping(deserializationConfig, javaType);
            if (null != javaTypeFindTypeMapping && !javaTypeFindTypeMapping.hasRawClass(rawClass)) {
                return javaTypeFindTypeMapping;
            }
        }
        return null;
    }
    public ValueInstantiator findValueInstantiator(final DeserializationContext deserializationContext, final BeanDescription beanDescription) throws JsonMappingException {
        final DeserializationConfig config = deserializationContext.getConfig();
        final AnnotatedClass classInfo = beanDescription.getClassInfo();
        final Object objFindValueInstantiator = deserializationContext.getAnnotationIntrospector().findValueInstantiator(classInfo);
        ValueInstantiator valueInstantiator_valueInstantiatorInstance = null != objFindValueInstantiator ? this._valueInstantiatorInstance(config, classInfo, objFindValueInstantiator) : null;
        if (null == valueInstantiator_valueInstantiatorInstance && null == (valueInstantiator_valueInstantiatorInstance = JDKValueInstantiators.findStdValueInstantiator(config, beanDescription.getBeanClass()))) {
            valueInstantiator_valueInstantiatorInstance = this._constructDefaultValueInstantiator(deserializationContext, beanDescription);
        }
        if (_factoryConfig.hasValueInstantiators()) {
            for (final ValueInstantiators valueInstantiators : _factoryConfig.valueInstantiators()) {
                valueInstantiator_valueInstantiatorInstance = valueInstantiators.findValueInstantiator(config, beanDescription, valueInstantiator_valueInstantiatorInstance);
                if (null == valueInstantiator_valueInstantiatorInstance) {
                    deserializationContext.reportBadTypeDefinition(beanDescription, "Broken registered ValueInstantiators (of type %s): returned null ValueInstantiator", valueInstantiators.getClass().getName());
                }
            }
        }
        return null != valueInstantiator_valueInstantiatorInstance ? valueInstantiator_valueInstantiatorInstance.createContextual(deserializationContext, beanDescription) : valueInstantiator_valueInstantiatorInstance;
    }
    protected ValueInstantiator _constructDefaultValueInstantiator(final DeserializationContext deserializationContext, final BeanDescription beanDescription) throws JsonMappingException {
        final ArrayList arrayList;
        final AnnotatedConstructor annotatedConstructorFindRecordConstructor;
        final DeserializationConfig config = deserializationContext.getConfig();
        final VisibilityChecker<?> defaultVisibilityChecker = config.getDefaultVisibilityChecker(beanDescription.getBeanClass(), beanDescription.getClassInfo());
        final ConstructorDetector constructorDetector = config.getConstructorDetector();
        final CreatorCollectionState creatorCollectionState = new CreatorCollectionState(deserializationContext, beanDescription, defaultVisibilityChecker, new CreatorCollector(beanDescription, config), this._findCreatorsFromProperties(deserializationContext, beanDescription));
        this._addExplicitFactoryCreators(deserializationContext, creatorCollectionState, !constructorDetector.requireCtorAnnotation());
        if (beanDescription.getType().isConcrete()) {
            if (beanDescription.getType().isRecordType() && null != (annotatedConstructorFindRecordConstructor = JDK14Util.findRecordConstructor(deserializationContext, beanDescription, (arrayList = new ArrayList())))) {
                this._addRecordConstructor(deserializationContext, creatorCollectionState, annotatedConstructorFindRecordConstructor, arrayList);
                return creatorCollectionState.creators.constructValueInstantiator(deserializationContext);
            }
            if (!beanDescription.isNonStaticInnerClass()) {
                this._addExplicitConstructorCreators(deserializationContext, creatorCollectionState, constructorDetector.shouldIntrospectorImplicitConstructors(beanDescription.getBeanClass()));
                if (creatorCollectionState.hasImplicitConstructorCandidates() && !creatorCollectionState.hasExplicitConstructors()) {
                    this._addImplicitConstructorCreators(deserializationContext, creatorCollectionState, creatorCollectionState.implicitConstructorCandidates());
                }
            }
        }
        if (creatorCollectionState.hasImplicitFactoryCandidates() && !creatorCollectionState.hasExplicitFactories() && !creatorCollectionState.hasExplicitConstructors()) {
            this._addImplicitFactoryCreators(deserializationContext, creatorCollectionState, creatorCollectionState.implicitFactoryCandidates());
        }
        return creatorCollectionState.creators.constructValueInstantiator(deserializationContext);
    }
    protected Map<AnnotatedWithParams, BeanPropertyDefinition[]> _findCreatorsFromProperties(final DeserializationContext deserializationContext, final BeanDescription beanDescription) throws JsonMappingException {
        Map<AnnotatedWithParams, BeanPropertyDefinition[]> mapEmptyMap = Collections.emptyMap();
        for (final BeanPropertyDefinition beanPropertyDefinition : beanDescription.findProperties()) {
            final Iterator<AnnotatedParameter> constructorParameters = beanPropertyDefinition.getConstructorParameters();
            while (constructorParameters.hasNext()) {
                final AnnotatedParameter next = constructorParameters.next();
                final AnnotatedWithParams owner = next.getOwner();
                BeanPropertyDefinition[] beanPropertyDefinitionArr = mapEmptyMap.get(owner);
                final int index = next.getIndex();
                if (null == beanPropertyDefinitionArr) {
                    if (mapEmptyMap.isEmpty()) {
                        mapEmptyMap = new LinkedHashMap<>();
                    }
                    beanPropertyDefinitionArr = new BeanPropertyDefinition[owner.getParameterCount()];
                    mapEmptyMap.put(owner, beanPropertyDefinitionArr);
                } else if (null != beanPropertyDefinitionArr[index]) {
                    deserializationContext.reportBadTypeDefinition(beanDescription, "Conflict: parameter #%d of %s bound to more than one property; %s vs %s", Integer.valueOf(index), owner, beanPropertyDefinitionArr[index], beanPropertyDefinition);
                }
                beanPropertyDefinitionArr[index] = beanPropertyDefinition;
            }
        }
        return mapEmptyMap;
    }
    public ValueInstantiator _valueInstantiatorInstance(final DeserializationConfig deserializationConfig, final Annotated annotated, final Object obj) throws JsonMappingException {
        final ValueInstantiator valueInstantiatorValueInstantiatorInstance;
        if (null == obj) {
            return null;
        }
        if (obj instanceof ValueInstantiator) {
            return (ValueInstantiator) obj;
        }
        if (!(obj instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + obj.getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead");
        }
        final Class<?> cls = (Class) obj;
        if (ClassUtil.isBogusClass(cls)) {
            return null;
        }
        if (!ValueInstantiator.class.isAssignableFrom(cls)) {
            throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<ValueInstantiator>");
        }
        final HandlerInstantiator handlerInstantiator = deserializationConfig.getHandlerInstantiator();
        try {
            return (null == handlerInstantiator || null == (valueInstantiatorValueInstantiatorInstance = handlerInstantiator.valueInstantiatorInstance(deserializationConfig, annotated, cls))) ? (ValueInstantiator) ClassUtil.createInstance(cls, deserializationConfig.canOverrideAccessModifiers()) : valueInstantiatorValueInstantiatorInstance;
        } catch (final NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    protected void _addRecordConstructor(final DeserializationContext deserializationContext, final CreatorCollectionState creatorCollectionState, final AnnotatedConstructor annotatedConstructor, final List<String> list) throws JsonMappingException {
        final int parameterCount = annotatedConstructor.getParameterCount();
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        final SettableBeanProperty[] settableBeanPropertyArr = new SettableBeanProperty[parameterCount];
        for (int i2 = 0; i2 < parameterCount; i2++) {
            final AnnotatedParameter parameter = annotatedConstructor.getParameter(i2);
            final JacksonInject.Value valueFindInjectableValue = annotationIntrospector.findInjectableValue(parameter);
            PropertyName propertyNameFindNameForDeserialization = annotationIntrospector.findNameForDeserialization(parameter);
            if (null == propertyNameFindNameForDeserialization || propertyNameFindNameForDeserialization.isEmpty()) {
                propertyNameFindNameForDeserialization = PropertyName.construct(list.get(i2));
            }
            settableBeanPropertyArr[i2] = this.constructCreatorProperty(deserializationContext, creatorCollectionState.beanDesc, propertyNameFindNameForDeserialization, i2, parameter, valueFindInjectableValue);
        }
        creatorCollectionState.creators.addPropertyCreator(annotatedConstructor, false, settableBeanPropertyArr);
    }
    protected void _addExplicitConstructorCreators(final DeserializationContext deserializationContext, final CreatorCollectionState creatorCollectionState, final boolean z) throws JsonMappingException {
        final BeanDescription beanDescription = creatorCollectionState.beanDesc;
        final CreatorCollector creatorCollector = creatorCollectionState.creators;
        final AnnotationIntrospector annotationIntrospector = creatorCollectionState.annotationIntrospector();
        final VisibilityChecker<?> visibilityChecker = creatorCollectionState.vchecker;
        final Map<AnnotatedWithParams, BeanPropertyDefinition[]> map = creatorCollectionState.creatorParams;
        final AnnotatedConstructor annotatedConstructorFindDefaultConstructor = beanDescription.findDefaultConstructor();
        if (null != annotatedConstructorFindDefaultConstructor && (!creatorCollector.hasDefaultCreator() || this._hasCreatorAnnotation(deserializationContext, annotatedConstructorFindDefaultConstructor))) {
            creatorCollector.setDefaultCreator(annotatedConstructorFindDefaultConstructor);
        }
        for (final AnnotatedConstructor annotatedConstructor : beanDescription.getConstructors()) {
            final JsonCreator.Mode modeFindCreatorAnnotation = annotationIntrospector.findCreatorAnnotation(deserializationContext.getConfig(), annotatedConstructor);
            if (JsonCreator.Mode.DISABLED != modeFindCreatorAnnotation) {
                if (null == modeFindCreatorAnnotation) {
                    if (z && visibilityChecker.isCreatorVisible(annotatedConstructor)) {
                        creatorCollectionState.addImplicitConstructorCandidate(CreatorCandidate.construct(annotationIntrospector, annotatedConstructor, map.get(annotatedConstructor)));
                    }
                } else {
                    final int i2 = C11931.SwitchMapcomfasterxmljacksonannotationJsonCreatorMode[modeFindCreatorAnnotation.ordinal()];
                    if (1 == i2) {
                        this._addExplicitDelegatingCreator(deserializationContext, beanDescription, creatorCollector, CreatorCandidate.construct(annotationIntrospector, annotatedConstructor, null));
                    } else if (2 == i2) {
                        this._addExplicitPropertyCreator(deserializationContext, beanDescription, creatorCollector, CreatorCandidate.construct(annotationIntrospector, annotatedConstructor, map.get(annotatedConstructor)));
                    } else {
                        this._addExplicitAnyCreator(deserializationContext, beanDescription, creatorCollector, CreatorCandidate.construct(annotationIntrospector, annotatedConstructor, map.get(annotatedConstructor)), deserializationContext.getConfig().getConstructorDetector());
                    }
                    creatorCollectionState.increaseExplicitConstructorCount();
                }
            }
        }
    }
    protected void _addImplicitConstructorCreators(final DeserializationContext deserializationContext, final CreatorCollectionState creatorCollectionState, final List<CreatorCandidate> list) throws JsonMappingException {
        VisibilityChecker<?> visibilityChecker;
        boolean z;
        Iterator<CreatorCandidate> it;
        int i2;
        int i3;
        CreatorCandidate creatorCandidate;
        VisibilityChecker<?> visibilityChecker2;
        boolean z2;
        Iterator<CreatorCandidate> it2;
        SettableBeanProperty[] settableBeanPropertyArr;
        AnnotatedWithParams annotatedWithParams;
        int i4;
        final DeserializationConfig config = deserializationContext.getConfig();
        final BeanDescription beanDescription = creatorCollectionState.beanDesc;
        final CreatorCollector creatorCollector = creatorCollectionState.creators;
        final AnnotationIntrospector annotationIntrospector = creatorCollectionState.annotationIntrospector();
        VisibilityChecker<?> visibilityChecker3 = creatorCollectionState.vchecker;
        boolean zSingleArgCreatorDefaultsToProperties = config.getConstructorDetector().singleArgCreatorDefaultsToProperties();
        Iterator<CreatorCandidate> it3 = list.iterator();
        LinkedList linkedList = null;
        while (it3.hasNext()) {
            CreatorCandidate next = it3.next();
            int iParamCount = next.paramCount();
            AnnotatedWithParams annotatedWithParamsCreator = next.creator();
            if (1 == iParamCount) {
                final BeanPropertyDefinition beanPropertyDefinitionPropertyDef = next.propertyDef(0);
                if (zSingleArgCreatorDefaultsToProperties || this._checkIfCreatorPropertyBased(annotationIntrospector, annotatedWithParamsCreator, beanPropertyDefinitionPropertyDef)) {
                    final JacksonInject.Value valueInjection = next.injection(0);
                    PropertyName propertyNameParamName = next.paramName(0);
                    if (null != propertyNameParamName || null != (propertyNameParamName = next.findImplicitParamName(0)) || null != valueInjection) {
                        creatorCollector.addPropertyCreator(annotatedWithParamsCreator, false, new SettableBeanProperty[]{this.constructCreatorProperty(deserializationContext, beanDescription, propertyNameParamName, 0, next.parameter(0), valueInjection)});
                    }
                } else {
                    this._handleSingleArgumentCreator(creatorCollector, annotatedWithParamsCreator, false, visibilityChecker3.isCreatorVisible(annotatedWithParamsCreator));
                    if (null != beanPropertyDefinitionPropertyDef) {
                        ((POJOPropertyBuilder) beanPropertyDefinitionPropertyDef).removeConstructors();
                    }
                }
                visibilityChecker = visibilityChecker3;
                z = zSingleArgCreatorDefaultsToProperties;
                it = it3;
            } else {
                SettableBeanProperty[] settableBeanPropertyArr2 = new SettableBeanProperty[iParamCount];
                int i5 = -1;
                int i6 = 0;
                int i7 = 0;
                int i8 = 0;
                while (i6 < iParamCount) {
                    final AnnotatedParameter parameter = annotatedWithParamsCreator.getParameter(i6);
                    final BeanPropertyDefinition beanPropertyDefinitionPropertyDef2 = next.propertyDef(i6);
                    final JacksonInject.Value valueFindInjectableValue = annotationIntrospector.findInjectableValue(parameter);
                    final PropertyName fullName = null == beanPropertyDefinitionPropertyDef2 ? null : beanPropertyDefinitionPropertyDef2.getFullName();
                    if (null == beanPropertyDefinitionPropertyDef2 || !beanPropertyDefinitionPropertyDef2.isExplicitlyNamed()) {
                        i2 = i6;
                        i3 = i5;
                        creatorCandidate = next;
                        visibilityChecker2 = visibilityChecker3;
                        z2 = zSingleArgCreatorDefaultsToProperties;
                        it2 = it3;
                        settableBeanPropertyArr = settableBeanPropertyArr2;
                        annotatedWithParams = annotatedWithParamsCreator;
                        i4 = iParamCount;
                        if (null != valueFindInjectableValue) {
                            i8++;
                            settableBeanPropertyArr[i2] = this.constructCreatorProperty(deserializationContext, beanDescription, fullName, i2, parameter, valueFindInjectableValue);
                        } else if (null != annotationIntrospector.findUnwrappingNameTransformer(parameter)) {
                            this._reportUnwrappedCreatorProperty(deserializationContext, beanDescription, parameter);
                        } else {
                            if (0 > i3) {
                                i5 = i2;
                            }
                            i6 = i2 + 1;
                            iParamCount = i4;
                            settableBeanPropertyArr2 = settableBeanPropertyArr;
                            annotatedWithParamsCreator = annotatedWithParams;
                            zSingleArgCreatorDefaultsToProperties = z2;
                            it3 = it2;
                            visibilityChecker3 = visibilityChecker2;
                            next = creatorCandidate;
                        }
                    } else {
                        i7++;
                        i2 = i6;
                        i3 = i5;
                        z2 = zSingleArgCreatorDefaultsToProperties;
                        settableBeanPropertyArr = settableBeanPropertyArr2;
                        it2 = it3;
                        annotatedWithParams = annotatedWithParamsCreator;
                        visibilityChecker2 = visibilityChecker3;
                        i4 = iParamCount;
                        creatorCandidate = next;
                        settableBeanPropertyArr[i2] = this.constructCreatorProperty(deserializationContext, beanDescription, fullName, i2, parameter, valueFindInjectableValue);
                    }
                    i5 = i3;
                    i6 = i2 + 1;
                    iParamCount = i4;
                    settableBeanPropertyArr2 = settableBeanPropertyArr;
                    annotatedWithParamsCreator = annotatedWithParams;
                    zSingleArgCreatorDefaultsToProperties = z2;
                    it3 = it2;
                    visibilityChecker3 = visibilityChecker2;
                    next = creatorCandidate;
                }
                final int i9 = i5;
                final CreatorCandidate creatorCandidate2 = next;
                visibilityChecker = visibilityChecker3;
                z = zSingleArgCreatorDefaultsToProperties;
                it = it3;
                final SettableBeanProperty[] settableBeanPropertyArr3 = settableBeanPropertyArr2;
                final AnnotatedWithParams annotatedWithParams2 = annotatedWithParamsCreator;
                final int i10 = iParamCount;
                if (0 < i7 || 0 < i8) {
                    if (i7 + i8 == i10) {
                        creatorCollector.addPropertyCreator(annotatedWithParams2, false, settableBeanPropertyArr3);
                    } else if (0 == i7 && i8 + 1 == i10) {
                        creatorCollector.addDelegatingCreator(annotatedWithParams2, false, settableBeanPropertyArr3, 0);
                    } else {
                        final PropertyName propertyNameFindImplicitParamName = creatorCandidate2.findImplicitParamName(i9);
                        if (null == propertyNameFindImplicitParamName || propertyNameFindImplicitParamName.isEmpty()) {
                            deserializationContext.reportBadTypeDefinition(beanDescription, "Argument #%d of constructor %s has no property name annotation; must have name when multiple-parameter constructor annotated as Creator", Integer.valueOf(i9), annotatedWithParams2);
                        }
                        if (!creatorCollector.hasDefaultCreator()) {
                            if (null == linkedList) {
                                linkedList = new LinkedList();
                            }
                            final LinkedList linkedList2 = linkedList;
                            linkedList2.add(annotatedWithParams2);
                            linkedList = linkedList2;
                        }
                    }
                } else if (!creatorCollector.hasDefaultCreator()) {
                }
            }
            zSingleArgCreatorDefaultsToProperties = z;
            it3 = it;
            visibilityChecker3 = visibilityChecker;
        }
        final VisibilityChecker<?> visibilityChecker4 = visibilityChecker3;
        if (null == linkedList || creatorCollector.hasDelegatingCreator() || creatorCollector.hasPropertyBasedCreator()) {
            return;
        }
        this._checkImplicitlyNamedConstructors(deserializationContext, beanDescription, visibilityChecker4, annotationIntrospector, creatorCollector, linkedList);
    }
    protected void _addExplicitFactoryCreators(final DeserializationContext deserializationContext, final CreatorCollectionState creatorCollectionState, final boolean z) throws JsonMappingException {
        final BeanDescription beanDescription = creatorCollectionState.beanDesc;
        final CreatorCollector creatorCollector = creatorCollectionState.creators;
        final AnnotationIntrospector annotationIntrospector = creatorCollectionState.annotationIntrospector();
        final VisibilityChecker<?> visibilityChecker = creatorCollectionState.vchecker;
        final Map<AnnotatedWithParams, BeanPropertyDefinition[]> map = creatorCollectionState.creatorParams;
        for (final AnnotatedMethod annotatedMethod : beanDescription.getFactoryMethods()) {
            final JsonCreator.Mode modeFindCreatorAnnotation = annotationIntrospector.findCreatorAnnotation(deserializationContext.getConfig(), annotatedMethod);
            final int parameterCount = annotatedMethod.getParameterCount();
            if (null == modeFindCreatorAnnotation) {
                if (z && 1 == parameterCount && visibilityChecker.isCreatorVisible(annotatedMethod)) {
                    creatorCollectionState.addImplicitFactoryCandidate(CreatorCandidate.construct(annotationIntrospector, annotatedMethod, null));
                }
            } else if (JsonCreator.Mode.DISABLED != modeFindCreatorAnnotation) {
                if (0 == parameterCount) {
                    creatorCollector.setDefaultCreator(annotatedMethod);
                } else {
                    final int i2 = C11931.SwitchMapcomfasterxmljacksonannotationJsonCreatorMode[modeFindCreatorAnnotation.ordinal()];
                    if (1 == i2) {
                        this._addExplicitDelegatingCreator(deserializationContext, beanDescription, creatorCollector, CreatorCandidate.construct(annotationIntrospector, annotatedMethod, null));
                    } else if (2 == i2) {
                        this._addExplicitPropertyCreator(deserializationContext, beanDescription, creatorCollector, CreatorCandidate.construct(annotationIntrospector, annotatedMethod, map.get(annotatedMethod)));
                    } else {
                        this._addExplicitAnyCreator(deserializationContext, beanDescription, creatorCollector, CreatorCandidate.construct(annotationIntrospector, annotatedMethod, map.get(annotatedMethod)), ConstructorDetector.DEFAULT);
                    }
                    creatorCollectionState.increaseExplicitFactoryCount();
                }
            }
        }
    }
    protected void _addImplicitFactoryCreators(final DeserializationContext deserializationContext, final CreatorCollectionState creatorCollectionState, final List<CreatorCandidate> list) throws JsonMappingException {
        int i2;
        VisibilityChecker<?> visibilityChecker;
        Map<AnnotatedWithParams, BeanPropertyDefinition[]> map;
        Iterator<CreatorCandidate> it;
        SettableBeanProperty[] settableBeanPropertyArr;
        boolean z;
        AnnotatedWithParams annotatedWithParams;
        final BeanDescription beanDescription = creatorCollectionState.beanDesc;
        final CreatorCollector creatorCollector = creatorCollectionState.creators;
        final AnnotationIntrospector annotationIntrospector = creatorCollectionState.annotationIntrospector();
        VisibilityChecker<?> visibilityChecker2 = creatorCollectionState.vchecker;
        Map<AnnotatedWithParams, BeanPropertyDefinition[]> map2 = creatorCollectionState.creatorParams;
        Iterator<CreatorCandidate> it2 = list.iterator();
        while (it2.hasNext()) {
            final CreatorCandidate next = it2.next();
            final int iParamCount = next.paramCount();
            AnnotatedWithParams annotatedWithParamsCreator = next.creator();
            final BeanPropertyDefinition[] beanPropertyDefinitionArr = map2.get(annotatedWithParamsCreator);
            if (1 == iParamCount) {
                boolean z2 = false;
                final BeanPropertyDefinition beanPropertyDefinitionPropertyDef = next.propertyDef(0);
                if (!this._checkIfCreatorPropertyBased(annotationIntrospector, annotatedWithParamsCreator, beanPropertyDefinitionPropertyDef)) {
                    this._handleSingleArgumentCreator(creatorCollector, annotatedWithParamsCreator, false, visibilityChecker2.isCreatorVisible(annotatedWithParamsCreator));
                    if (null != beanPropertyDefinitionPropertyDef) {
                        ((POJOPropertyBuilder) beanPropertyDefinitionPropertyDef).removeConstructors();
                    }
                } else {
                    SettableBeanProperty[] settableBeanPropertyArr2 = new SettableBeanProperty[1];
                    int i3 = 0;
                    int i4 = 0;
                    int i5 = 0;
                    AnnotatedParameter annotatedParameter = null;
                    while (i3 < iParamCount) {
                        final AnnotatedParameter parameter = annotatedWithParamsCreator.getParameter(i3);
                        final BeanPropertyDefinition beanPropertyDefinition = null == beanPropertyDefinitionArr ? null : beanPropertyDefinitionArr[i3];
                        final JacksonInject.Value valueFindInjectableValue = annotationIntrospector.findInjectableValue(parameter);
                        final PropertyName fullName = null == beanPropertyDefinition ? null : beanPropertyDefinition.getFullName();
                        if (null == beanPropertyDefinition || !beanPropertyDefinition.isExplicitlyNamed()) {
                            i2 = i3;
                            visibilityChecker = visibilityChecker2;
                            map = map2;
                            it = it2;
                            settableBeanPropertyArr = settableBeanPropertyArr2;
                            z = z2;
                            annotatedWithParams = annotatedWithParamsCreator;
                            if (null != valueFindInjectableValue) {
                                i5++;
                                settableBeanPropertyArr[i2] = this.constructCreatorProperty(deserializationContext, beanDescription, fullName, i2, parameter, valueFindInjectableValue);
                            } else if (null != annotationIntrospector.findUnwrappingNameTransformer(parameter)) {
                                this._reportUnwrappedCreatorProperty(deserializationContext, beanDescription, parameter);
                            } else if (null == annotatedParameter) {
                                annotatedParameter = parameter;
                            }
                        } else {
                            i4++;
                            i2 = i3;
                            visibilityChecker = visibilityChecker2;
                            settableBeanPropertyArr = settableBeanPropertyArr2;
                            map = map2;
                            z = z2;
                            it = it2;
                            annotatedWithParams = annotatedWithParamsCreator;
                            settableBeanPropertyArr[i2] = this.constructCreatorProperty(deserializationContext, beanDescription, fullName, i2, parameter, valueFindInjectableValue);
                        }
                        i3 = i2 + 1;
                        settableBeanPropertyArr2 = settableBeanPropertyArr;
                        z2 = z;
                        annotatedWithParamsCreator = annotatedWithParams;
                        visibilityChecker2 = visibilityChecker;
                        map2 = map;
                        it2 = it;
                    }
                    final VisibilityChecker<?> visibilityChecker3 = visibilityChecker2;
                    final Map<AnnotatedWithParams, BeanPropertyDefinition[]> map3 = map2;
                    final Iterator<CreatorCandidate> it3 = it2;
                    final SettableBeanProperty[] settableBeanPropertyArr3 = settableBeanPropertyArr2;
                    final boolean z3 = z2;
                    final AnnotatedWithParams annotatedWithParams2 = annotatedWithParamsCreator;
                    if (0 < i4 || 0 < i5) {
                        if (i4 + i5 == iParamCount) {
                            creatorCollector.addPropertyCreator(annotatedWithParams2, z3, settableBeanPropertyArr3);
                        } else if (0 == i4 && i5 + 1 == iParamCount) {
                            creatorCollector.addDelegatingCreator(annotatedWithParams2, z3, settableBeanPropertyArr3, z3 ? 1 : 0);
                        } else {
                            deserializationContext.reportBadTypeDefinition(beanDescription, "Argument #%d of factory method %s has no property name annotation; must have name when multiple-parameter constructor annotated as Creator", Integer.valueOf(annotatedParameter.getIndex()), annotatedWithParams2);
                        }
                    }
                    it2 = it3;
                    visibilityChecker2 = visibilityChecker3;
                    map2 = map3;
                }
            }
        }
    }
    protected void _addExplicitDelegatingCreator(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final CreatorCollector creatorCollector, final CreatorCandidate creatorCandidate) throws JsonMappingException {
        final int iParamCount = creatorCandidate.paramCount();
        final SettableBeanProperty[] settableBeanPropertyArr = new SettableBeanProperty[iParamCount];
        int i2 = -1;
        for (int i3 = 0; i3 < iParamCount; i3++) {
            final AnnotatedParameter annotatedParameterParameter = creatorCandidate.parameter(i3);
            final JacksonInject.Value valueInjection = creatorCandidate.injection(i3);
            if (null != valueInjection) {
                settableBeanPropertyArr[i3] = this.constructCreatorProperty(deserializationContext, beanDescription, null, i3, annotatedParameterParameter, valueInjection);
            } else if (0 > i2) {
                i2 = i3;
            } else {
                deserializationContext.reportBadTypeDefinition(beanDescription, "More than one argument (#%d and #%d) left as delegating for Creator %s: only one allowed", Integer.valueOf(i2), Integer.valueOf(i3), creatorCandidate);
            }
        }
        if (0 > i2) {
            deserializationContext.reportBadTypeDefinition(beanDescription, "No argument left as delegating for Creator %s: exactly one required", creatorCandidate);
        }
        if (1 == iParamCount) {
            this._handleSingleArgumentCreator(creatorCollector, creatorCandidate.creator(), true, true);
            final BeanPropertyDefinition beanPropertyDefinitionPropertyDef = creatorCandidate.propertyDef(0);
            if (null != beanPropertyDefinitionPropertyDef) {
                ((POJOPropertyBuilder) beanPropertyDefinitionPropertyDef).removeConstructors();
                return;
            }
            return;
        }
        creatorCollector.addDelegatingCreator(creatorCandidate.creator(), true, settableBeanPropertyArr, i2);
    }
    protected void _addExplicitPropertyCreator(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final CreatorCollector creatorCollector, final CreatorCandidate creatorCandidate) throws JsonMappingException {
        final int iParamCount = creatorCandidate.paramCount();
        final SettableBeanProperty[] settableBeanPropertyArr = new SettableBeanProperty[iParamCount];
        int i2 = 0;
        while (i2 < iParamCount) {
            final JacksonInject.Value valueInjection = creatorCandidate.injection(i2);
            final AnnotatedParameter annotatedParameterParameter = creatorCandidate.parameter(i2);
            PropertyName propertyNameParamName = creatorCandidate.paramName(i2);
            if (null == propertyNameParamName) {
                if (null != deserializationContext.getAnnotationIntrospector().findUnwrappingNameTransformer(annotatedParameterParameter)) {
                    this._reportUnwrappedCreatorProperty(deserializationContext, beanDescription, annotatedParameterParameter);
                }
                final PropertyName propertyNameFindImplicitParamName = creatorCandidate.findImplicitParamName(i2);
                this._validateNamedPropertyParameter(deserializationContext, beanDescription, creatorCandidate, i2, propertyNameFindImplicitParamName, valueInjection);
                propertyNameParamName = propertyNameFindImplicitParamName;
            }
            final int i3 = i2;
            settableBeanPropertyArr[i3] = this.constructCreatorProperty(deserializationContext, beanDescription, propertyNameParamName, i2, annotatedParameterParameter, valueInjection);
            i2 = i3 + 1;
        }
        creatorCollector.addPropertyCreator(creatorCandidate.creator(), true, settableBeanPropertyArr);
    }
    protected void _addExplicitAnyCreator(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final CreatorCollector creatorCollector, final CreatorCandidate creatorCandidate) throws JsonMappingException {
        this._addExplicitAnyCreator(deserializationContext, beanDescription, creatorCollector, creatorCandidate, deserializationContext.getConfig().getConstructorDetector());
    }
    protected void _addExplicitAnyCreator(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final CreatorCollector creatorCollector, final CreatorCandidate creatorCandidate, final ConstructorDetector constructorDetector) throws JsonMappingException {
        final PropertyName propertyName;
        boolean z;
        final int iFindOnlyParamWithoutInjection;
        if (1 != creatorCandidate.paramCount()) {
            if (!constructorDetector.singleArgCreatorDefaultsToProperties() && 0 <= (iFindOnlyParamWithoutInjection = creatorCandidate.findOnlyParamWithoutInjectionX()) && (constructorDetector.singleArgCreatorDefaultsToDelegating() || null == creatorCandidate.paramName(iFindOnlyParamWithoutInjection))) {
                this._addExplicitDelegatingCreator(deserializationContext, beanDescription, creatorCollector, creatorCandidate);
                return;
            } else {
                this._addExplicitPropertyCreator(deserializationContext, beanDescription, creatorCollector, creatorCandidate);
                return;
            }
        }
        final AnnotatedParameter annotatedParameterParameter = creatorCandidate.parameter(0);
        final JacksonInject.Value valueInjection = creatorCandidate.injection(0);
        final int i2 = C11931.f800x8e3a543[constructorDetector.singleArgMode().ordinal()];
        if (1 == i2) {
            propertyName = null;
            z = false;
        } else if (2 == i2) {
            final PropertyName propertyNameParamName = creatorCandidate.paramName(0);
            if (null == propertyNameParamName) {
                this._validateNamedPropertyParameter(deserializationContext, beanDescription, creatorCandidate, 0, propertyNameParamName, valueInjection);
            }
            z = true;
            propertyName = propertyNameParamName;
        } else {
            if (3 == i2) {
                deserializationContext.reportBadTypeDefinition(beanDescription, "Single-argument constructor (%s) is annotated but no 'mode' defined; `CreatorDetector`configured with `SingleArgConstructor.REQUIRE_MODE`", creatorCandidate.creator());
                return;
            }
            final BeanPropertyDefinition beanPropertyDefinitionPropertyDef = creatorCandidate.propertyDef(0);
            PropertyName propertyNameExplicitParamName = creatorCandidate.explicitParamName(0);
            z = null != propertyNameExplicitParamName || null != valueInjection;
            if (!z && null != beanPropertyDefinitionPropertyDef) {
                propertyNameExplicitParamName = creatorCandidate.paramName(0);
                z = null != propertyNameExplicitParamName && beanPropertyDefinitionPropertyDef.couldSerialize();
            }
            propertyName = propertyNameExplicitParamName;
        }
        if (z) {
            creatorCollector.addPropertyCreator(creatorCandidate.creator(), true, new SettableBeanProperty[]{this.constructCreatorProperty(deserializationContext, beanDescription, propertyName, 0, annotatedParameterParameter, valueInjection)});
            return;
        }
        this._handleSingleArgumentCreator(creatorCollector, creatorCandidate.creator(), true, true);
        final BeanPropertyDefinition beanPropertyDefinitionPropertyDef2 = creatorCandidate.propertyDef(0);
        if (null != beanPropertyDefinitionPropertyDef2) {
            ((POJOPropertyBuilder) beanPropertyDefinitionPropertyDef2).removeConstructors();
        }
    }
    enum C11931 {
        ;
        static final int[] SwitchMapcomfasterxmljacksonannotationJsonCreatorMode;
        static final int[] f800x8e3a543;

        static {
            final int[] iArr = new int[ConstructorDetector.SingleArgConstructor.values().length];
            f800x8e3a543 = iArr;
            try {
                iArr[ConstructorDetector.SingleArgConstructor.DELEGATING.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C11931.f800x8e3a543[ConstructorDetector.SingleArgConstructor.PROPERTIES.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C11931.f800x8e3a543[ConstructorDetector.SingleArgConstructor.REQUIRE_MODE.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C11931.f800x8e3a543[ConstructorDetector.SingleArgConstructor.HEURISTIC.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            final int[] iArr2 = new int[JsonCreator.Mode.values().length];
            SwitchMapcomfasterxmljacksonannotationJsonCreatorMode = iArr2;
            try {
                iArr2[JsonCreator.Mode.DELEGATING.ordinal()] = 1;
            } catch (final NoSuchFieldError unused5) {
            }
            try {
                C11931.SwitchMapcomfasterxmljacksonannotationJsonCreatorMode[JsonCreator.Mode.PROPERTIES.ordinal()] = 2;
            } catch (final NoSuchFieldError unused6) {
            }
            try {
                C11931.SwitchMapcomfasterxmljacksonannotationJsonCreatorMode[JsonCreator.Mode.DEFAULT.ordinal()] = 3;
            } catch (final NoSuchFieldError unused7) {
            }
        }
    }
    private boolean _checkIfCreatorPropertyBased(final AnnotationIntrospector annotationIntrospector, final AnnotatedWithParams annotatedWithParams, final BeanPropertyDefinition beanPropertyDefinition) {
        final String name;
        if ((null == beanPropertyDefinition || !beanPropertyDefinition.isExplicitlyNamed()) && null == annotationIntrospector.findInjectableValue(annotatedWithParams.getParameter(0))) {
            return null != beanPropertyDefinition && null != (name = beanPropertyDefinition.getName()) && !name.isEmpty() && beanPropertyDefinition.couldSerialize();
        }
        return true;
    }
    private void _checkImplicitlyNamedConstructors(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final VisibilityChecker<?> visibilityChecker, final AnnotationIntrospector annotationIntrospector, final CreatorCollector creatorCollector, final List<AnnotatedWithParams> list) throws JsonMappingException {
        int i2;
        final Iterator<AnnotatedWithParams> it = list.iterator();
        AnnotatedWithParams annotatedWithParams = null;
        AnnotatedWithParams annotatedWithParams2 = null;
        SettableBeanProperty[] settableBeanPropertyArr = null;
        while (true) {
            if (!it.hasNext()) {
                annotatedWithParams = annotatedWithParams2;
                break;
            }
            final AnnotatedWithParams next = it.next();
            if (visibilityChecker.isCreatorVisible(next)) {
                final int parameterCount = next.getParameterCount();
                final SettableBeanProperty[] settableBeanPropertyArr2 = new SettableBeanProperty[parameterCount];
                int i3 = 0;
                while (true) {
                    if (i3 < parameterCount) {
                        final AnnotatedParameter parameter = next.getParameter(i3);
                        final PropertyName propertyName_findParamName = this._findParamName(parameter, annotationIntrospector);
                        if (null == propertyName_findParamName || propertyName_findParamName.isEmpty()) {
                            break;
                        }
                        settableBeanPropertyArr2[i3] = this.constructCreatorProperty(deserializationContext, beanDescription, propertyName_findParamName, parameter.getIndex(), parameter, null);
                        i3++;
                    } else {
                        if (null != annotatedWithParams2) {
                            break;
                        }
                        annotatedWithParams2 = next;
                        settableBeanPropertyArr = settableBeanPropertyArr2;
                    }
                }
            }
        }
        if (null != annotatedWithParams) {
            creatorCollector.addPropertyCreator(annotatedWithParams, false, settableBeanPropertyArr);
            final BasicBeanDescription basicBeanDescription = (BasicBeanDescription) beanDescription;
            for (final SettableBeanProperty settableBeanProperty : settableBeanPropertyArr) {
                final PropertyName fullName = settableBeanProperty.getFullName();
                if (!basicBeanDescription.hasProperty(fullName)) {
                    basicBeanDescription.addProperty(SimpleBeanPropertyDefinition.construct(deserializationContext.getConfig(), settableBeanProperty.getMember(), fullName));
                }
            }
        }
    }
    protected boolean _handleSingleArgumentCreator(final CreatorCollector creatorCollector, final AnnotatedWithParams annotatedWithParams, final boolean z, final boolean z2) {
        final Class<?> rawParameterType = annotatedWithParams.getRawParameterType(0);
        if (String.class == rawParameterType || BasicDeserializerFactory.CLASS_CHAR_SEQUENCE == rawParameterType) {
            if (z || z2) {
                creatorCollector.addStringCreator(annotatedWithParams, z);
            }
            return true;
        }
        if (rawParameterType == Integer.TYPE || Integer.class == rawParameterType) {
            if (z || z2) {
                creatorCollector.addIntCreator(annotatedWithParams, z);
            }
            return true;
        }
        if (rawParameterType == Long.TYPE || Long.class == rawParameterType) {
            if (z || z2) {
                creatorCollector.addLongCreator(annotatedWithParams, z);
            }
            return true;
        }
        if (rawParameterType == Double.TYPE || Double.class == rawParameterType) {
            if (z || z2) {
                creatorCollector.addDoubleCreator(annotatedWithParams, z);
            }
            return true;
        }
        if (rawParameterType == Boolean.TYPE || Boolean.class == rawParameterType) {
            if (z || z2) {
                creatorCollector.addBooleanCreator(annotatedWithParams, z);
            }
            return true;
        }
        if (BigInteger.class == rawParameterType && (z || z2)) {
            creatorCollector.addBigIntegerCreator(annotatedWithParams, z);
        }
        if (BigDecimal.class == rawParameterType && (z || z2)) {
            creatorCollector.addBigDecimalCreator(annotatedWithParams, z);
        }
        if (!z) {
            return false;
        }
        creatorCollector.addDelegatingCreator(annotatedWithParams, z, null, 0);
        return true;
    }
    protected void _validateNamedPropertyParameter(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final CreatorCandidate creatorCandidate, final int i2, final PropertyName propertyName, final JacksonInject.Value value) throws JsonMappingException {
        if (null == propertyName && null == value) {
            deserializationContext.reportBadTypeDefinition(beanDescription, "Argument #%d of constructor %s has no property name (and is not Injectable): can not use as property-based Creator", Integer.valueOf(i2), creatorCandidate);
        }
    }
    protected void _reportUnwrappedCreatorProperty(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final AnnotatedParameter annotatedParameter) throws JsonMappingException {
        deserializationContext.reportBadTypeDefinition(beanDescription, "Cannot define Creator parameter %d as `@JsonUnwrapped`: combination not yet supported", Integer.valueOf(annotatedParameter.getIndex()));
    }
    protected SettableBeanProperty constructCreatorProperty(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final PropertyName propertyName, final int i2, final AnnotatedParameter annotatedParameter, final JacksonInject.Value value) throws JsonMappingException {
        final PropertyMetadata propertyMetadataConstruct;
        final DeserializationConfig config = deserializationContext.getConfig();
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (null == annotationIntrospector) {
            propertyMetadataConstruct = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
        } else {
            propertyMetadataConstruct = PropertyMetadata.construct(annotationIntrospector.hasRequiredMarker(annotatedParameter), annotationIntrospector.findPropertyDescription(annotatedParameter), annotationIntrospector.findPropertyIndex(annotatedParameter), annotationIntrospector.findPropertyDefaultValue(annotatedParameter));
        }
        final PropertyMetadata propertyMetadata = propertyMetadataConstruct;
        final JavaType javaTypeResolveMemberAndTypeAnnotations = this.resolveMemberAndTypeAnnotations(deserializationContext, annotatedParameter, annotatedParameter.getType());
        final BeanProperty.Std std = new BeanProperty.Std(propertyName, javaTypeResolveMemberAndTypeAnnotations, annotationIntrospector.findWrapperName(annotatedParameter), annotatedParameter, propertyMetadata);
        TypeDeserializer typeDeserializerFindTypeDeserializer = javaTypeResolveMemberAndTypeAnnotations.getTypeHandler();
        if (null == typeDeserializerFindTypeDeserializer) {
            typeDeserializerFindTypeDeserializer = this.findTypeDeserializer(config, javaTypeResolveMemberAndTypeAnnotations);
        }
        final CreatorProperty creatorPropertyConstruct = CreatorProperty.construct(propertyName, javaTypeResolveMemberAndTypeAnnotations, std.getWrapperName(), typeDeserializerFindTypeDeserializer, beanDescription.getClassAnnotations(), annotatedParameter, i2, value, this._getSetterInfo(deserializationContext, std, propertyMetadata));
        JsonDeserializer<?> jsonDeserializerFindDeserializerFromAnnotation = this.findDeserializerFromAnnotation(deserializationContext, annotatedParameter);
        if (null == jsonDeserializerFindDeserializerFromAnnotation) {
            jsonDeserializerFindDeserializerFromAnnotation = javaTypeResolveMemberAndTypeAnnotations.getValueHandler();
        }
        return null != jsonDeserializerFindDeserializerFromAnnotation ? creatorPropertyConstruct.withValueDeserializer(deserializationContext.handlePrimaryContextualization(jsonDeserializerFindDeserializerFromAnnotation, creatorPropertyConstruct, javaTypeResolveMemberAndTypeAnnotations)) : creatorPropertyConstruct;
    }
    private PropertyName _findParamName(final AnnotatedParameter annotatedParameter, final AnnotationIntrospector annotationIntrospector) {
        if (null == annotationIntrospector) {
            return null;
        }
        final PropertyName propertyNameFindNameForDeserialization = annotationIntrospector.findNameForDeserialization(annotatedParameter);
        if (null != propertyNameFindNameForDeserialization && !propertyNameFindNameForDeserialization.isEmpty()) {
            return propertyNameFindNameForDeserialization;
        }
        final String strFindImplicitPropertyName = annotationIntrospector.findImplicitPropertyName(annotatedParameter);
        if (null == strFindImplicitPropertyName || strFindImplicitPropertyName.isEmpty()) {
            return null;
        }
        return PropertyName.construct(strFindImplicitPropertyName);
    }
    protected PropertyMetadata _getSetterInfo(final DeserializationContext deserializationContext, final BeanProperty beanProperty, final PropertyMetadata propertyMetadata) {
        Nulls nullsNonDefaultContentNulls;
        final JsonSetter.Value valueFindSetterInfo;
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        final DeserializationConfig config = deserializationContext.getConfig();
        final AnnotatedMember member = beanProperty.getMember();
        Nulls nullsNonDefaultValueNulls = null;
        if (null != member) {
            if (null == annotationIntrospector || null == (valueFindSetterInfo = annotationIntrospector.findSetterInfo(member))) {
                nullsNonDefaultContentNulls = null;
            } else {
                nullsNonDefaultValueNulls = valueFindSetterInfo.nonDefaultValueNulls();
                nullsNonDefaultContentNulls = valueFindSetterInfo.nonDefaultContentNulls();
            }
            final JsonSetter.Value setterInfo = config.getConfigOverride(beanProperty.getType().getRawClass()).getSetterInfo();
            if (null != setterInfo) {
                if (null == nullsNonDefaultValueNulls) {
                    nullsNonDefaultValueNulls = setterInfo.nonDefaultValueNulls();
                }
                if (null == nullsNonDefaultContentNulls) {
                    nullsNonDefaultContentNulls = setterInfo.nonDefaultContentNulls();
                }
            }
        } else {
            nullsNonDefaultContentNulls = null;
        }
        final JsonSetter.Value defaultSetterInfo = config.getDefaultSetterInfo();
        if (null == nullsNonDefaultValueNulls) {
            nullsNonDefaultValueNulls = defaultSetterInfo.nonDefaultValueNulls();
        }
        if (null == nullsNonDefaultContentNulls) {
            nullsNonDefaultContentNulls = defaultSetterInfo.nonDefaultContentNulls();
        }
        return (null == nullsNonDefaultValueNulls && null == nullsNonDefaultContentNulls) ? propertyMetadata : propertyMetadata.withNulls(nullsNonDefaultValueNulls, nullsNonDefaultContentNulls);
    }
    public JsonDeserializer<?> createArrayDeserializer(final DeserializationContext deserializationContext, final ArrayType arrayType, final BeanDescription beanDescription) throws JsonMappingException {
        final DeserializationConfig config = deserializationContext.getConfig();
        final JavaType contentType = arrayType.getContentType();
        final JsonDeserializer<?> jsonDeserializer = contentType.getValueHandler();
        TypeDeserializer typeDeserializerFindTypeDeserializer = contentType.getTypeHandler();
        if (null == typeDeserializerFindTypeDeserializer) {
            typeDeserializerFindTypeDeserializer = this.findTypeDeserializer(config, contentType);
        }
        final TypeDeserializer typeDeserializer = typeDeserializerFindTypeDeserializer;
        JsonDeserializer<?> jsonDeserializer_findCustomArrayDeserializer = this._findCustomArrayDeserializer(arrayType, config, beanDescription, typeDeserializer, jsonDeserializer);
        if (null == jsonDeserializer_findCustomArrayDeserializer) {
            if (null == jsonDeserializer) {
                final Class<?> rawClass = contentType.getRawClass();
                if (contentType.isPrimitive()) {
                    return PrimitiveArrayDeserializers.forType(rawClass);
                }
                if (String.class == rawClass) {
                    return StringArrayDeserializer.instance;
                }
            }
            jsonDeserializer_findCustomArrayDeserializer = new ObjectArrayDeserializer(arrayType, (JsonDeserializer<Object>) jsonDeserializer, typeDeserializer);
        }
        if (_factoryConfig.hasDeserializerModifiers()) {
            final Iterator<BeanDeserializerModifier> it = _factoryConfig.deserializerModifiers().iterator();
            while (it.hasNext()) {
                jsonDeserializer_findCustomArrayDeserializer = it.next().modifyArrayDeserializer(config, arrayType, beanDescription, jsonDeserializer_findCustomArrayDeserializer);
            }
        }
        return jsonDeserializer_findCustomArrayDeserializer;
    }
    public JsonDeserializer<?> createCollectionDeserializer(final DeserializationContext deserializationContext, CollectionType collectionType, BeanDescription beanDescription) throws JsonMappingException {
        final JsonDeserializer<?> collectionDeserializer;
        final JavaType contentType = collectionType.getContentType();
        final JsonDeserializer<?> jsonDeserializer = contentType.getValueHandler();
        final DeserializationConfig config = deserializationContext.getConfig();
        TypeDeserializer typeDeserializerFindTypeDeserializer = contentType.getTypeHandler();
        if (null == typeDeserializerFindTypeDeserializer) {
            typeDeserializerFindTypeDeserializer = this.findTypeDeserializer(config, contentType);
        }
        final TypeDeserializer typeDeserializer = typeDeserializerFindTypeDeserializer;
        JsonDeserializer<?> jsonDeserializer_findCustomCollectionDeserializer = this._findCustomCollectionDeserializer(collectionType, config, beanDescription, typeDeserializer, jsonDeserializer);
        if (null == jsonDeserializer_findCustomCollectionDeserializer) {
            final Class<?> rawClass = collectionType.getRawClass();
            if (null == jsonDeserializer && EnumSet.class.isAssignableFrom(rawClass)) {
                jsonDeserializer_findCustomCollectionDeserializer = new EnumSetDeserializer(contentType, null);
            }
        }
        if (null == jsonDeserializer_findCustomCollectionDeserializer) {
            if (collectionType.isInterface() || collectionType.isAbstract()) {
                final CollectionType collectionType_mapAbstractCollectionType = this._mapAbstractCollectionType(collectionType, config);
                if (null == collectionType_mapAbstractCollectionType) {
                    if (null == collectionType.getTypeHandler()) {
                        throw new IllegalArgumentException("Cannot find a deserializer for non-concrete Collection type " + collectionType);
                    }
                    jsonDeserializer_findCustomCollectionDeserializer = AbstractDeserializer.constructForNonPOJO(beanDescription);
                } else {
                    beanDescription = config.introspectForCreation(collectionType_mapAbstractCollectionType);
                    collectionType = collectionType_mapAbstractCollectionType;
                }
            }
            if (null == jsonDeserializer_findCustomCollectionDeserializer) {
                final ValueInstantiator valueInstantiatorFindValueInstantiator = this.findValueInstantiator(deserializationContext, beanDescription);
                if (!valueInstantiatorFindValueInstantiator.canCreateUsingDefault()) {
                    if (collectionType.hasRawClass(ArrayBlockingQueue.class)) {
                        return new ArrayBlockingQueueDeserializer(collectionType, (JsonDeserializer<Object>) jsonDeserializer, typeDeserializer, valueInstantiatorFindValueInstantiator);
                    }
                    final JsonDeserializer<?> jsonDeserializerFindForCollection = JavaUtilCollectionsDeserializers.findForCollection(deserializationContext, collectionType);
                    if (null != jsonDeserializerFindForCollection) {
                        return jsonDeserializerFindForCollection;
                    }
                }
                if (contentType.hasRawClass(String.class)) {
                    collectionDeserializer = new StringCollectionDeserializer(collectionType, jsonDeserializer, valueInstantiatorFindValueInstantiator);
                } else {
                    collectionDeserializer = new CollectionDeserializer(collectionType, (JsonDeserializer<Object>) jsonDeserializer, typeDeserializer, valueInstantiatorFindValueInstantiator);
                }
                jsonDeserializer_findCustomCollectionDeserializer = collectionDeserializer;
            }
        }
        if (_factoryConfig.hasDeserializerModifiers()) {
            final Iterator<BeanDeserializerModifier> it = _factoryConfig.deserializerModifiers().iterator();
            while (it.hasNext()) {
                jsonDeserializer_findCustomCollectionDeserializer = it.next().modifyCollectionDeserializer(config, collectionType, beanDescription, jsonDeserializer_findCustomCollectionDeserializer);
            }
        }
        return jsonDeserializer_findCustomCollectionDeserializer;
    }
    protected CollectionType _mapAbstractCollectionType(final JavaType javaType, final DeserializationConfig deserializationConfig) {
        final Class<?> clsFindCollectionFallback = ContainerDefaultMappings.findCollectionFallback(javaType);
        if (null != clsFindCollectionFallback) {
            return (CollectionType) deserializationConfig.getTypeFactory().constructSpecializedType(javaType, clsFindCollectionFallback, true);
        }
        return null;
    }
    public JsonDeserializer<?> createCollectionLikeDeserializer(final DeserializationContext deserializationContext, final CollectionLikeType collectionLikeType, final BeanDescription beanDescription) throws JsonMappingException {
        final JavaType contentType = collectionLikeType.getContentType();
        final JsonDeserializer<?> jsonDeserializer = contentType.getValueHandler();
        final DeserializationConfig config = deserializationContext.getConfig();
        final TypeDeserializer typeDeserializer = contentType.getTypeHandler();
        JsonDeserializer<?> jsonDeserializer_findCustomCollectionLikeDeserializer = this._findCustomCollectionLikeDeserializer(collectionLikeType, config, beanDescription, null == typeDeserializer ? this.findTypeDeserializer(config, contentType) : typeDeserializer, jsonDeserializer);
        if (null != jsonDeserializer_findCustomCollectionLikeDeserializer && _factoryConfig.hasDeserializerModifiers()) {
            final Iterator<BeanDeserializerModifier> it = _factoryConfig.deserializerModifiers().iterator();
            while (it.hasNext()) {
                jsonDeserializer_findCustomCollectionLikeDeserializer = it.next().modifyCollectionLikeDeserializer(config, collectionLikeType, beanDescription, jsonDeserializer_findCustomCollectionLikeDeserializer);
            }
        }
        return jsonDeserializer_findCustomCollectionLikeDeserializer;
    }
    public JsonDeserializer<?> createMapDeserializer(final DeserializationContext deserializationContext, final MapType mapType, final BeanDescription beanDescription) throws JsonMappingException {
        BeanDescription beanDescriptionIntrospectForCreation;
        MapType mapType_mapAbstractMapType = null;
        final JsonDeserializer<?> jsonDeserializer;
        final ValueInstantiator valueInstantiatorFindValueInstantiator;
        final DeserializationConfig config = deserializationContext.getConfig();
        final JavaType keyType = mapType.getKeyType();
        final JavaType contentType = mapType.getContentType();
        final JsonDeserializer<?> jsonDeserializer2 = contentType.getValueHandler();
        final KeyDeserializer keyDeserializer = keyType.getValueHandler();
        final TypeDeserializer typeDeserializer = contentType.getTypeHandler();
        final TypeDeserializer typeDeserializerFindTypeDeserializer = null == typeDeserializer ? this.findTypeDeserializer(config, contentType) : typeDeserializer;
        JsonDeserializer<?> jsonDeserializer_findCustomMapDeserializer = this._findCustomMapDeserializer(mapType, config, beanDescription, keyDeserializer, typeDeserializerFindTypeDeserializer, jsonDeserializer2);
        if (null == jsonDeserializer_findCustomMapDeserializer) {
            final Class<?> rawClass = mapType.getRawClass();
            if (EnumMap.class.isAssignableFrom(rawClass)) {
                if (EnumMap.class == rawClass) {
                    beanDescriptionIntrospectForCreation = beanDescription;
                    valueInstantiatorFindValueInstantiator = null;
                } else {
                    beanDescriptionIntrospectForCreation = beanDescription;
                    valueInstantiatorFindValueInstantiator = this.findValueInstantiator(deserializationContext, beanDescriptionIntrospectForCreation);
                }
                if (!keyType.isEnumImplType()) {
                    throw new IllegalArgumentException("Cannot construct EnumMap; generic (key) type not available");
                }
                jsonDeserializer_findCustomMapDeserializer = new EnumMapDeserializer(mapType, valueInstantiatorFindValueInstantiator, null, jsonDeserializer2, typeDeserializerFindTypeDeserializer, null);
            } else {
                beanDescriptionIntrospectForCreation = beanDescription;
            }
            if (null == jsonDeserializer_findCustomMapDeserializer) {
                if (mapType.isInterface() || mapType.isAbstract()) {
                    mapType_mapAbstractMapType = this._mapAbstractMapType(mapType, config);
                    if (null != mapType_mapAbstractMapType) {
                        mapType_mapAbstractMapType.getRawClass();
                        beanDescriptionIntrospectForCreation = config.introspectForCreation(mapType_mapAbstractMapType);
                    } else {
                        if (null == mapType.getTypeHandler()) {
                            throw new IllegalArgumentException("Cannot find a deserializer for non-concrete Map type " + mapType);
                        }
                        jsonDeserializer_findCustomMapDeserializer = AbstractDeserializer.constructForNonPOJO(beanDescription);
                        mapType_mapAbstractMapType = mapType;
                    }
                    jsonDeserializer = jsonDeserializer_findCustomMapDeserializer;
                } else {
                    final JsonDeserializer<?> jsonDeserializerFindForMap = JavaUtilCollectionsDeserializers.findForMap(deserializationContext, mapType);
                    if (null != jsonDeserializerFindForMap) {
                        return jsonDeserializerFindForMap;
                    }
                    jsonDeserializer = jsonDeserializerFindForMap;
                    mapType_mapAbstractMapType = mapType;
                }
                final BeanDescription beanDescription2 = beanDescriptionIntrospectForCreation;
                JsonDeserializer<?> jsonDeserializer3 = jsonDeserializer;
                if (null == jsonDeserializer) {
                    final MapDeserializer mapDeserializer = new MapDeserializer(mapType_mapAbstractMapType, this.findValueInstantiator(deserializationContext, beanDescription2), keyDeserializer, (JsonDeserializer<Object>) jsonDeserializer2, typeDeserializerFindTypeDeserializer);
                    final JsonIgnoreProperties.Value defaultPropertyIgnorals = config.getDefaultPropertyIgnorals(Map.class, beanDescription2.getClassInfo());
                    mapDeserializer.setIgnorableProperties(null == defaultPropertyIgnorals ? null : defaultPropertyIgnorals.findIgnoredForDeserialization());
                    final JsonIncludeProperties.Value defaultPropertyInclusions = config.getDefaultPropertyInclusions(Map.class, beanDescription2.getClassInfo());
                    mapDeserializer.setIncludableProperties(null != defaultPropertyInclusions ? defaultPropertyInclusions.getIncluded() : null);
                    jsonDeserializer3 = mapDeserializer;
                }
                beanDescriptionIntrospectForCreation = beanDescription2;
                jsonDeserializer_findCustomMapDeserializer = jsonDeserializer3;
            }
            if (_factoryConfig.hasDeserializerModifiers()) {
                final Iterator<BeanDeserializerModifier> it = _factoryConfig.deserializerModifiers().iterator();
                while (it.hasNext()) {
                    jsonDeserializer_findCustomMapDeserializer = it.next().modifyMapDeserializer(config, mapType_mapAbstractMapType, beanDescriptionIntrospectForCreation, jsonDeserializer_findCustomMapDeserializer);
                }
            }
            return jsonDeserializer_findCustomMapDeserializer;
        }
        beanDescriptionIntrospectForCreation = beanDescription;
        mapType_mapAbstractMapType = mapType;
        if (_factoryConfig.hasDeserializerModifiers()) {
        }
        return jsonDeserializer_findCustomMapDeserializer;
    }
    protected MapType _mapAbstractMapType(final JavaType javaType, final DeserializationConfig deserializationConfig) {
        final Class<?> clsFindMapFallback = ContainerDefaultMappings.findMapFallback(javaType);
        if (null != clsFindMapFallback) {
            return (MapType) deserializationConfig.getTypeFactory().constructSpecializedType(javaType, clsFindMapFallback, true);
        }
        return null;
    }
    public JsonDeserializer<?> createMapLikeDeserializer(final DeserializationContext deserializationContext, final MapLikeType mapLikeType, final BeanDescription beanDescription) throws JsonMappingException {
        final JavaType keyType = mapLikeType.getKeyType();
        final JavaType contentType = mapLikeType.getContentType();
        final DeserializationConfig config = deserializationContext.getConfig();
        final JsonDeserializer<?> jsonDeserializer = contentType.getValueHandler();
        final KeyDeserializer keyDeserializer = keyType.getValueHandler();
        TypeDeserializer typeDeserializerFindTypeDeserializer = contentType.getTypeHandler();
        if (null == typeDeserializerFindTypeDeserializer) {
            typeDeserializerFindTypeDeserializer = this.findTypeDeserializer(config, contentType);
        }
        JsonDeserializer<?> jsonDeserializer_findCustomMapLikeDeserializer = this._findCustomMapLikeDeserializer(mapLikeType, config, beanDescription, keyDeserializer, typeDeserializerFindTypeDeserializer, jsonDeserializer);
        if (null != jsonDeserializer_findCustomMapLikeDeserializer && _factoryConfig.hasDeserializerModifiers()) {
            final Iterator<BeanDeserializerModifier> it = _factoryConfig.deserializerModifiers().iterator();
            while (it.hasNext()) {
                jsonDeserializer_findCustomMapLikeDeserializer = it.next().modifyMapLikeDeserializer(config, mapLikeType, beanDescription, jsonDeserializer_findCustomMapLikeDeserializer);
            }
        }
        return jsonDeserializer_findCustomMapLikeDeserializer;
    }
    public JsonDeserializer<?> createEnumDeserializer(final DeserializationContext deserializationContext, final JavaType javaType, final BeanDescription beanDescription) throws JsonMappingException {
        final DeserializationConfig config = deserializationContext.getConfig();
        final Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer<?> jsonDeserializer_findCustomEnumDeserializer = this._findCustomEnumDeserializer(rawClass, config, beanDescription);
        if (null == jsonDeserializer_findCustomEnumDeserializer) {
            if (Enum.class == rawClass) {
                return AbstractDeserializer.constructForNonPOJO(beanDescription);
            }
            final ValueInstantiator valueInstantiator_constructDefaultValueInstantiator = this._constructDefaultValueInstantiator(deserializationContext, beanDescription);
            final SettableBeanProperty[] fromObjectArguments = null == valueInstantiator_constructDefaultValueInstantiator ? null : valueInstantiator_constructDefaultValueInstantiator.getFromObjectArguments(deserializationContext.getConfig());
            final Iterator<AnnotatedMethod> it = beanDescription.getFactoryMethods().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                final AnnotatedMethod next = it.next();
                if (this._hasCreatorAnnotation(deserializationContext, next)) {
                    if (0 == next.getParameterCount()) {
                        jsonDeserializer_findCustomEnumDeserializer = EnumDeserializer.deserializerForNoArgsCreator(config, rawClass, next);
                    } else {
                        if (!next.getRawReturnType().isAssignableFrom(rawClass)) {
                            deserializationContext.reportBadDefinition(javaType, String.format("Invalid `@JsonCreator` annotated Enum factory method [%s]: needs to return compatible type", next));
                        }
                        jsonDeserializer_findCustomEnumDeserializer = EnumDeserializer.deserializerForCreator(config, rawClass, next, valueInstantiator_constructDefaultValueInstantiator, fromObjectArguments);
                    }
                }
            }
            if (null == jsonDeserializer_findCustomEnumDeserializer) {
                jsonDeserializer_findCustomEnumDeserializer = new EnumDeserializer(this.constructEnumResolver(rawClass, config, beanDescription.findJsonValueAccessor()), Boolean.valueOf(config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)));
            }
        }
        if (_factoryConfig.hasDeserializerModifiers()) {
            final Iterator<BeanDeserializerModifier> it2 = _factoryConfig.deserializerModifiers().iterator();
            while (it2.hasNext()) {
                jsonDeserializer_findCustomEnumDeserializer = it2.next().modifyEnumDeserializer(config, javaType, beanDescription, jsonDeserializer_findCustomEnumDeserializer);
            }
        }
        return jsonDeserializer_findCustomEnumDeserializer;
    }
    public JsonDeserializer<?> createTreeDeserializer(final DeserializationConfig deserializationConfig, final JavaType javaType, final BeanDescription beanDescription) throws JsonMappingException {
        final Class<?> rawClass = javaType.getRawClass();
        final JsonDeserializer<?> jsonDeserializer_findCustomTreeNodeDeserializer = this._findCustomTreeNodeDeserializer((Class<? extends JsonNode>) rawClass, deserializationConfig, beanDescription);
        return null != jsonDeserializer_findCustomTreeNodeDeserializer ? jsonDeserializer_findCustomTreeNodeDeserializer : JsonNodeDeserializer.getDeserializer(rawClass);
    }
    public JsonDeserializer<?> createReferenceDeserializer(final DeserializationContext deserializationContext, final ReferenceType referenceType, final BeanDescription beanDescription) throws JsonMappingException {
        final JavaType contentType = referenceType.getContentType();
        final JsonDeserializer<?> jsonDeserializer = contentType.getValueHandler();
        final DeserializationConfig config = deserializationContext.getConfig();
        TypeDeserializer typeDeserializerFindTypeDeserializer = contentType.getTypeHandler();
        if (null == typeDeserializerFindTypeDeserializer) {
            typeDeserializerFindTypeDeserializer = this.findTypeDeserializer(config, contentType);
        }
        final TypeDeserializer typeDeserializer = typeDeserializerFindTypeDeserializer;
        JsonDeserializer<?> jsonDeserializer_findCustomReferenceDeserializer = this._findCustomReferenceDeserializer(referenceType, config, beanDescription, typeDeserializer, jsonDeserializer);
        if (null == jsonDeserializer_findCustomReferenceDeserializer && referenceType.isTypeOrSubTypeOf(AtomicReference.class)) {
            return new AtomicReferenceDeserializer(referenceType, AtomicReference.class == referenceType.getRawClass() ? null : this.findValueInstantiator(deserializationContext, beanDescription), typeDeserializer, jsonDeserializer);
        }
        if (null != jsonDeserializer_findCustomReferenceDeserializer && _factoryConfig.hasDeserializerModifiers()) {
            final Iterator<BeanDeserializerModifier> it = _factoryConfig.deserializerModifiers().iterator();
            while (it.hasNext()) {
                jsonDeserializer_findCustomReferenceDeserializer = it.next().modifyReferenceDeserializer(config, referenceType, beanDescription, jsonDeserializer_findCustomReferenceDeserializer);
            }
        }
        return jsonDeserializer_findCustomReferenceDeserializer;
    }
    public TypeDeserializer findTypeDeserializer(final DeserializationConfig deserializationConfig, final JavaType javaType) throws JsonMappingException {
        final Collection<NamedType> collectionCollectAndResolveSubtypesByTypeId;
        final JavaType javaTypeMapAbstractType;
        final AnnotatedClass classInfo = deserializationConfig.introspectClassAnnotations(javaType.getRawClass()).getClassInfo();
        TypeResolverBuilder<?> typeResolverBuilderFindTypeResolver = deserializationConfig.getAnnotationIntrospector().findTypeResolver(deserializationConfig, classInfo, javaType);
        if (null == typeResolverBuilderFindTypeResolver) {
            typeResolverBuilderFindTypeResolver = deserializationConfig.getDefaultTyper(javaType);
            if (null == typeResolverBuilderFindTypeResolver) {
                return null;
            }
            collectionCollectAndResolveSubtypesByTypeId = null;
        } else {
            collectionCollectAndResolveSubtypesByTypeId = deserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(deserializationConfig, classInfo);
        }
        if (null == typeResolverBuilderFindTypeResolver.getDefaultImpl() && javaType.isAbstract() && null != (javaTypeMapAbstractType = mapAbstractType(deserializationConfig, javaType)) && !javaTypeMapAbstractType.hasRawClass(javaType.getRawClass())) {
            typeResolverBuilderFindTypeResolver = typeResolverBuilderFindTypeResolver.defaultImpl(javaTypeMapAbstractType.getRawClass());
        }
        try {
            return typeResolverBuilderFindTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, collectionCollectAndResolveSubtypesByTypeId);
        } catch (final IllegalArgumentException | IllegalStateException e2) {
            final InvalidDefinitionException invalidDefinitionExceptionFrom = InvalidDefinitionException.from((JsonParser) null, ClassUtil.exceptionMessage(e2), javaType);
            invalidDefinitionExceptionFrom.initCause(e2);
            throw invalidDefinitionExceptionFrom;
        }
    }
    protected JsonDeserializer<?> findOptionalStdDeserializer(final DeserializationContext deserializationContext, final JavaType javaType, final BeanDescription beanDescription) throws JsonMappingException {
        return OptionalHandlerFactory.instance.findDeserializer(javaType, deserializationContext.getConfig(), beanDescription);
    }
    public KeyDeserializer createKeyDeserializer(final DeserializationContext deserializationContext, final JavaType javaType) throws JsonMappingException {
        BeanDescription beanDescriptionIntrospectClassAnnotations;
        final DeserializationConfig config = deserializationContext.getConfig();
        KeyDeserializer keyDeserializerModifyKeyDeserializer = null;
        if (_factoryConfig.hasKeyDeserializers()) {
            beanDescriptionIntrospectClassAnnotations = config.introspectClassAnnotations(javaType);
            final Iterator<KeyDeserializers> it = _factoryConfig.keyDeserializers().iterator();
            while (it.hasNext() && null == (keyDeserializerModifyKeyDeserializer = it.next().findKeyDeserializer(javaType, config, beanDescriptionIntrospectClassAnnotations))) {
            }
        } else {
            beanDescriptionIntrospectClassAnnotations = null;
        }
        if (null == keyDeserializerModifyKeyDeserializer) {
            if (null == beanDescriptionIntrospectClassAnnotations) {
                beanDescriptionIntrospectClassAnnotations = config.introspectClassAnnotations(javaType.getRawClass());
            }
            keyDeserializerModifyKeyDeserializer = this.findKeyDeserializerFromAnnotation(deserializationContext, beanDescriptionIntrospectClassAnnotations.getClassInfo());
            if (null == keyDeserializerModifyKeyDeserializer) {
                if (javaType.isEnumType()) {
                    keyDeserializerModifyKeyDeserializer = this._createEnumKeyDeserializer(deserializationContext, javaType);
                } else {
                    keyDeserializerModifyKeyDeserializer = StdKeyDeserializers.findStringBasedKeyDeserializer(config, javaType);
                }
            }
        }
        if (null != keyDeserializerModifyKeyDeserializer && _factoryConfig.hasDeserializerModifiers()) {
            final Iterator<BeanDeserializerModifier> it2 = _factoryConfig.deserializerModifiers().iterator();
            while (it2.hasNext()) {
                keyDeserializerModifyKeyDeserializer = it2.next().modifyKeyDeserializer(config, javaType, keyDeserializerModifyKeyDeserializer);
            }
        }
        return keyDeserializerModifyKeyDeserializer;
    }
    private KeyDeserializer _createEnumKeyDeserializer(final DeserializationContext deserializationContext, final JavaType javaType) throws JsonMappingException {
        final DeserializationConfig config = deserializationContext.getConfig();
        final Class<?> rawClass = javaType.getRawClass();
        final BeanDescription beanDescriptionIntrospect = config.introspect(javaType);
        final KeyDeserializer keyDeserializerFindKeyDeserializerFromAnnotation = this.findKeyDeserializerFromAnnotation(deserializationContext, beanDescriptionIntrospect.getClassInfo());
        if (null != keyDeserializerFindKeyDeserializerFromAnnotation) {
            return keyDeserializerFindKeyDeserializerFromAnnotation;
        }
        final JsonDeserializer<?> jsonDeserializer_findCustomEnumDeserializer = this._findCustomEnumDeserializer(rawClass, config, beanDescriptionIntrospect);
        if (null != jsonDeserializer_findCustomEnumDeserializer) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, javaType, jsonDeserializer_findCustomEnumDeserializer);
        }
        final JsonDeserializer<Object> jsonDeserializerFindDeserializerFromAnnotation = this.findDeserializerFromAnnotation(deserializationContext, beanDescriptionIntrospect.getClassInfo());
        if (null != jsonDeserializerFindDeserializerFromAnnotation) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, javaType, jsonDeserializerFindDeserializerFromAnnotation);
        }
        final EnumResolver enumResolverConstructEnumResolver = this.constructEnumResolver(rawClass, config, beanDescriptionIntrospect.findJsonValueAccessor());
        for (final AnnotatedMethod annotatedMethod : beanDescriptionIntrospect.getFactoryMethods()) {
            if (this._hasCreatorAnnotation(deserializationContext, annotatedMethod)) {
                if (1 == annotatedMethod.getParameterCount() && annotatedMethod.getRawReturnType().isAssignableFrom(rawClass)) {
                    if (String.class == annotatedMethod.getRawParameterType(0)) {
                        if (config.canOverrideAccessModifiers()) {
                            ClassUtil.checkAndFixAccess(annotatedMethod.getMember(), deserializationContext.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
                        }
                        return StdKeyDeserializers.constructEnumKeyDeserializer(enumResolverConstructEnumResolver, annotatedMethod);
                    }
                } else {
                    throw new IllegalArgumentException("Unsuitable method (" + annotatedMethod + ") decorated with @JsonCreator (for Enum type " + rawClass.getName() + ")");
                }
            }
        }
        return StdKeyDeserializers.constructEnumKeyDeserializer(enumResolverConstructEnumResolver);
    }
    public boolean hasExplicitDeserializerFor(final DeserializationConfig deserializationConfig, Class<?> cls) {
        while (cls.isArray()) {
            cls = cls.getComponentType();
        }
        if (Enum.class.isAssignableFrom(cls)) {
            return true;
        }
        final String name = cls.getName();
        if (name.startsWith("java.")) {
            if (Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls)) {
                return true;
            }
            return Number.class.isAssignableFrom(cls) ? null != NumberDeserializers.find(cls, name) : JdkDeserializers.hasDeserializerFor(cls) || BasicDeserializerFactory.CLASS_STRING == cls || Boolean.class == cls || EnumMap.class == cls || AtomicReference.class == cls || DateDeserializers.hasDeserializerFor(cls);
        }
        if (name.startsWith("com.fasterxml.")) {
            return JsonNode.class.isAssignableFrom(cls) || TokenBuffer.class == cls;
        }
        return OptionalHandlerFactory.instance.hasDeserializerFor(cls);
    }
    public TypeDeserializer findPropertyTypeDeserializer(final DeserializationConfig deserializationConfig, final JavaType javaType, final AnnotatedMember annotatedMember) throws JsonMappingException {
        final TypeResolverBuilder<?> typeResolverBuilderFindPropertyTypeResolver = deserializationConfig.getAnnotationIntrospector().findPropertyTypeResolver(deserializationConfig, annotatedMember, javaType);
        if (null == typeResolverBuilderFindPropertyTypeResolver) {
            return this.findTypeDeserializer(deserializationConfig, javaType);
        }
        try {
            return typeResolverBuilderFindPropertyTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(deserializationConfig, annotatedMember, javaType));
        } catch (final IllegalArgumentException | IllegalStateException e2) {
            final InvalidDefinitionException invalidDefinitionExceptionFrom = InvalidDefinitionException.from((JsonParser) null, ClassUtil.exceptionMessage(e2), javaType);
            invalidDefinitionExceptionFrom.initCause(e2);
            throw invalidDefinitionExceptionFrom;
        }
    }
    public TypeDeserializer findPropertyContentTypeDeserializer(final DeserializationConfig deserializationConfig, final JavaType javaType, final AnnotatedMember annotatedMember) throws JsonMappingException {
        final TypeResolverBuilder<?> typeResolverBuilderFindPropertyContentTypeResolver = deserializationConfig.getAnnotationIntrospector().findPropertyContentTypeResolver(deserializationConfig, annotatedMember, javaType);
        final JavaType contentType = javaType.getContentType();
        if (null == typeResolverBuilderFindPropertyContentTypeResolver) {
            return this.findTypeDeserializer(deserializationConfig, contentType);
        }
        return typeResolverBuilderFindPropertyContentTypeResolver.buildTypeDeserializer(deserializationConfig, contentType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(deserializationConfig, annotatedMember, contentType));
    }
    public JsonDeserializer<?> findDefaultDeserializer(final DeserializationContext deserializationContext, final JavaType javaType, final BeanDescription beanDescription) throws JsonMappingException {
        final JavaType javaType_findRemappedType;
        final JavaType javaType_findRemappedType2;
        final Class<?> rawClass = javaType.getRawClass();
        if (BasicDeserializerFactory.CLASS_OBJECT == rawClass || BasicDeserializerFactory.CLASS_SERIALIZABLE == rawClass) {
            final DeserializationConfig config = deserializationContext.getConfig();
            if (_factoryConfig.hasAbstractTypeResolvers()) {
                javaType_findRemappedType = this._findRemappedType(config, List.class);
                javaType_findRemappedType2 = this._findRemappedType(config, Map.class);
            } else {
                javaType_findRemappedType = null;
                javaType_findRemappedType2 = null;
            }
            return new UntypedObjectDeserializer(javaType_findRemappedType, javaType_findRemappedType2);
        }
        if (BasicDeserializerFactory.CLASS_STRING == rawClass || BasicDeserializerFactory.CLASS_CHAR_SEQUENCE == rawClass) {
            return StringDeserializer.instance;
        }
        final Class<?> cls = BasicDeserializerFactory.CLASS_ITERABLE;
        if (rawClass == cls) {
            final TypeFactory typeFactory = deserializationContext.getTypeFactory();
            final JavaType[] javaTypeArrFindTypeParameters = typeFactory.findTypeParameters(javaType, cls);
            return this.createCollectionDeserializer(deserializationContext, typeFactory.constructCollectionType(Collection.class, (null == javaTypeArrFindTypeParameters || 1 != javaTypeArrFindTypeParameters.length) ? TypeFactory.unknownType() : javaTypeArrFindTypeParameters[0]), beanDescription);
        }
        if (BasicDeserializerFactory.CLASS_MAP_ENTRY == rawClass) {
            final JavaType javaTypeContainedTypeOrUnknown = javaType.containedTypeOrUnknown(0);
            final JavaType javaTypeContainedTypeOrUnknown2 = javaType.containedTypeOrUnknown(1);
            TypeDeserializer typeDeserializerFindTypeDeserializer = javaTypeContainedTypeOrUnknown2.getTypeHandler();
            if (null == typeDeserializerFindTypeDeserializer) {
                typeDeserializerFindTypeDeserializer = this.findTypeDeserializer(deserializationContext.getConfig(), javaTypeContainedTypeOrUnknown2);
            }
            return new MapEntryDeserializer(javaType, javaTypeContainedTypeOrUnknown.getValueHandler(), javaTypeContainedTypeOrUnknown2.getValueHandler(), typeDeserializerFindTypeDeserializer);
        }
        final String name = rawClass.getName();
        if (rawClass.isPrimitive() || name.startsWith("java.")) {
            JsonDeserializer<?> jsonDeserializerFind = NumberDeserializers.find(rawClass, name);
            if (null == jsonDeserializerFind) {
                jsonDeserializerFind = DateDeserializers.find(rawClass, name);
            }
            if (null != jsonDeserializerFind) {
                return jsonDeserializerFind;
            }
        }
        if (TokenBuffer.class == rawClass) {
            return new TokenBufferDeserializer();
        }
        final JsonDeserializer<?> jsonDeserializerFindOptionalStdDeserializer = this.findOptionalStdDeserializer(deserializationContext, javaType, beanDescription);
        return null != jsonDeserializerFindOptionalStdDeserializer ? jsonDeserializerFindOptionalStdDeserializer : JdkDeserializers.find(rawClass, name);
    }
    protected JavaType _findRemappedType(final DeserializationConfig deserializationConfig, final Class<?> cls) throws JsonMappingException {
        final JavaType javaTypeMapAbstractType = this.mapAbstractType(deserializationConfig, deserializationConfig.constructType(cls));
        if (null == javaTypeMapAbstractType || javaTypeMapAbstractType.hasRawClass(cls)) {
            return null;
        }
        return javaTypeMapAbstractType;
    }
    protected JsonDeserializer<?> _findCustomTreeNodeDeserializer(final Class<? extends JsonNode> cls, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription) throws JsonMappingException {
        final Iterator<Deserializers> it = _factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            final JsonDeserializer<?> jsonDeserializerFindTreeNodeDeserializer = it.next().findTreeNodeDeserializer(cls, deserializationConfig, beanDescription);
            if (null != jsonDeserializerFindTreeNodeDeserializer) {
                return jsonDeserializerFindTreeNodeDeserializer;
            }
        }
        return null;
    }
    protected JsonDeserializer<?> _findCustomReferenceDeserializer(final ReferenceType referenceType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        final Iterator<Deserializers> it = _factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            final JsonDeserializer<?> jsonDeserializerFindReferenceDeserializer = it.next().findReferenceDeserializer(referenceType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (null != jsonDeserializerFindReferenceDeserializer) {
                return jsonDeserializerFindReferenceDeserializer;
            }
        }
        return null;
    }
    protected JsonDeserializer<Object> _findCustomBeanDeserializer(final JavaType javaType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription) throws JsonMappingException {
        final Iterator<Deserializers> it = _factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            final JsonDeserializer<?> jsonDeserializerFindBeanDeserializer = it.next().findBeanDeserializer(javaType, deserializationConfig, beanDescription);
            if (null != jsonDeserializerFindBeanDeserializer) {
                return (JsonDeserializer<Object>) jsonDeserializerFindBeanDeserializer;
            }
        }
        return null;
    }
    protected JsonDeserializer<?> _findCustomArrayDeserializer(final ArrayType arrayType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        final Iterator<Deserializers> it = _factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            final JsonDeserializer<?> jsonDeserializerFindArrayDeserializer = it.next().findArrayDeserializer(arrayType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (null != jsonDeserializerFindArrayDeserializer) {
                return jsonDeserializerFindArrayDeserializer;
            }
        }
        return null;
    }
    protected JsonDeserializer<?> _findCustomCollectionDeserializer(final CollectionType collectionType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        final Iterator<Deserializers> it = _factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            final JsonDeserializer<?> jsonDeserializerFindCollectionDeserializer = it.next().findCollectionDeserializer(collectionType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (null != jsonDeserializerFindCollectionDeserializer) {
                return jsonDeserializerFindCollectionDeserializer;
            }
        }
        return null;
    }
    protected JsonDeserializer<?> _findCustomCollectionLikeDeserializer(final CollectionLikeType collectionLikeType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        final Iterator<Deserializers> it = _factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            final JsonDeserializer<?> jsonDeserializerFindCollectionLikeDeserializer = it.next().findCollectionLikeDeserializer(collectionLikeType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (null != jsonDeserializerFindCollectionLikeDeserializer) {
                return jsonDeserializerFindCollectionLikeDeserializer;
            }
        }
        return null;
    }
    protected JsonDeserializer<?> _findCustomEnumDeserializer(final Class<?> cls, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription) throws JsonMappingException {
        final Iterator<Deserializers> it = _factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            final JsonDeserializer<?> jsonDeserializerFindEnumDeserializer = it.next().findEnumDeserializer(cls, deserializationConfig, beanDescription);
            if (null != jsonDeserializerFindEnumDeserializer) {
                return jsonDeserializerFindEnumDeserializer;
            }
        }
        return null;
    }
    protected JsonDeserializer<?> _findCustomMapDeserializer(final MapType mapType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription, final KeyDeserializer keyDeserializer, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        final Iterator<Deserializers> it = _factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            final JsonDeserializer<?> jsonDeserializerFindMapDeserializer = it.next().findMapDeserializer(mapType, deserializationConfig, beanDescription, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (null != jsonDeserializerFindMapDeserializer) {
                return jsonDeserializerFindMapDeserializer;
            }
        }
        return null;
    }
    protected JsonDeserializer<?> _findCustomMapLikeDeserializer(final MapLikeType mapLikeType, final DeserializationConfig deserializationConfig, final BeanDescription beanDescription, final KeyDeserializer keyDeserializer, final TypeDeserializer typeDeserializer, final JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        final Iterator<Deserializers> it = _factoryConfig.deserializers().iterator();
        while (it.hasNext()) {
            final JsonDeserializer<?> jsonDeserializerFindMapLikeDeserializer = it.next().findMapLikeDeserializer(mapLikeType, deserializationConfig, beanDescription, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (null != jsonDeserializerFindMapLikeDeserializer) {
                return jsonDeserializerFindMapLikeDeserializer;
            }
        }
        return null;
    }
    protected JsonDeserializer<Object> findDeserializerFromAnnotation(final DeserializationContext deserializationContext, final Annotated annotated) throws JsonMappingException {
        final Object objFindDeserializer;
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (null == annotationIntrospector || null == (objFindDeserializer = annotationIntrospector.findDeserializer(annotated))) {
            return null;
        }
        return deserializationContext.deserializerInstance(annotated, objFindDeserializer);
    }
    protected KeyDeserializer findKeyDeserializerFromAnnotation(final DeserializationContext deserializationContext, final Annotated annotated) throws JsonMappingException {
        final Object objFindKeyDeserializer;
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (null == annotationIntrospector || null == (objFindKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotated))) {
            return null;
        }
        return deserializationContext.keyDeserializerInstance(annotated, objFindKeyDeserializer);
    }
    protected JsonDeserializer<Object> findContentDeserializerFromAnnotation(final DeserializationContext deserializationContext, final Annotated annotated) throws JsonMappingException {
        final Object objFindContentDeserializer;
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (null == annotationIntrospector || null == (objFindContentDeserializer = annotationIntrospector.findContentDeserializer(annotated))) {
            return null;
        }
        return deserializationContext.deserializerInstance(annotated, objFindContentDeserializer);
    }
    protected JavaType resolveMemberAndTypeAnnotations(final DeserializationContext deserializationContext, final AnnotatedMember annotatedMember, JavaType javaType) throws JsonMappingException {
        final KeyDeserializer keyDeserializerKeyDeserializerInstance;
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (null == annotationIntrospector) {
            return javaType;
        }
        if (javaType.isMapLikeType() && null != javaType.getKeyType() && null != (keyDeserializerKeyDeserializerInstance = deserializationContext.keyDeserializerInstance(annotatedMember, annotationIntrospector.findKeyDeserializer(annotatedMember)))) {
            javaType = ((MapLikeType) javaType).withKeyValueHandler(keyDeserializerKeyDeserializerInstance);
            javaType.getKeyType();
        }
        if (javaType.hasContentType()) {
            final JsonDeserializer<Object> jsonDeserializerDeserializerInstance = deserializationContext.deserializerInstance(annotatedMember, annotationIntrospector.findContentDeserializer(annotatedMember));
            if (null != jsonDeserializerDeserializerInstance) {
                javaType = javaType.withContentValueHandler(jsonDeserializerDeserializerInstance);
            }
            final TypeDeserializer typeDeserializerFindPropertyContentTypeDeserializer = this.findPropertyContentTypeDeserializer(deserializationContext.getConfig(), javaType, annotatedMember);
            if (null != typeDeserializerFindPropertyContentTypeDeserializer) {
                javaType = javaType.withContentTypeHandler(typeDeserializerFindPropertyContentTypeDeserializer);
            }
        }
        final TypeDeserializer typeDeserializerFindPropertyTypeDeserializer = this.findPropertyTypeDeserializer(deserializationContext.getConfig(), javaType, annotatedMember);
        if (null != typeDeserializerFindPropertyTypeDeserializer) {
            javaType = javaType.withTypeHandler(typeDeserializerFindPropertyTypeDeserializer);
        }
        return annotationIntrospector.refineDeserializationType(deserializationContext.getConfig(), annotatedMember, javaType);
    }
    protected EnumResolver constructEnumResolver(final Class<?> cls, final DeserializationConfig deserializationConfig, final AnnotatedMember annotatedMember) {
        if (null != annotatedMember) {
            if (deserializationConfig.canOverrideAccessModifiers()) {
                ClassUtil.checkAndFixAccess(annotatedMember.getMember(), deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            }
            return EnumResolver.constructUsingMethod(deserializationConfig, cls, annotatedMember);
        }
        return EnumResolver.constructFor(deserializationConfig, cls);
    }
    protected boolean _hasCreatorAnnotation(final DeserializationContext deserializationContext, final Annotated annotated) {
        final JsonCreator.Mode modeFindCreatorAnnotation;
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        return null != annotationIntrospector && null != (modeFindCreatorAnnotation = annotationIntrospector.findCreatorAnnotation(deserializationContext.getConfig(), annotated)) && JsonCreator.Mode.DISABLED != modeFindCreatorAnnotation;
    }
    protected JavaType modifyTypeByAnnotation(final DeserializationContext deserializationContext, final Annotated annotated, final JavaType javaType) throws JsonMappingException {
        final AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        return null == annotationIntrospector ? javaType : annotationIntrospector.refineDeserializationType(deserializationContext.getConfig(), annotated, javaType);
    }
    protected JavaType resolveType(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final JavaType javaType, final AnnotatedMember annotatedMember) throws JsonMappingException {
        return this.resolveMemberAndTypeAnnotations(deserializationContext, annotatedMember, javaType);
    }
    protected AnnotatedMethod _findJsonValueFor(final DeserializationConfig deserializationConfig, final JavaType javaType) {
        if (null == javaType) {
            return null;
        }
        return deserializationConfig.introspect(javaType).findJsonValueMethod();
    }
    protected static class ContainerDefaultMappings {
        static final HashMap<String, Class<? extends Collection>> _collectionFallbacks;
        static final HashMap<String, Class<? extends Map>> _mapFallbacks;

        protected ContainerDefaultMappings() {
        }

        static {
            final HashMap<String, Class<? extends Collection>> map = new HashMap<>();
            map.put(Collection.class.getName(), ArrayList.class);
            map.put(List.class.getName(), ArrayList.class);
            map.put(Set.class.getName(), HashSet.class);
            map.put(SortedSet.class.getName(), TreeSet.class);
            map.put(Queue.class.getName(), LinkedList.class);
            map.put(AbstractList.class.getName(), ArrayList.class);
            map.put(AbstractSet.class.getName(), HashSet.class);
            map.put(Deque.class.getName(), LinkedList.class);
            map.put(NavigableSet.class.getName(), TreeSet.class);
            _collectionFallbacks = map;
            final HashMap<String, Class<? extends Map>> map2 = new HashMap<>();
            map2.put(Map.class.getName(), LinkedHashMap.class);
            map2.put(AbstractMap.class.getName(), LinkedHashMap.class);
            map2.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
            map2.put(SortedMap.class.getName(), TreeMap.class);
            map2.put(NavigableMap.class.getName(), TreeMap.class);
            map2.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
            _mapFallbacks = map2;
        }

        public static Class<?> findCollectionFallback(final JavaType javaType) {
            return ContainerDefaultMappings._collectionFallbacks.get(javaType.getRawClass().getName());
        }

        public static Class<?> findMapFallback(final JavaType javaType) {
            return ContainerDefaultMappings._mapFallbacks.get(javaType.getRawClass().getName());
        }
    }
    protected static class CreatorCollectionState {
        private int _explicitConstructorCount;
        private int _explicitFactoryCount;
        private List<CreatorCandidate> _implicitConstructorCandidates;
        private List<CreatorCandidate> _implicitFactoryCandidates;
        public final BeanDescription beanDesc;
        public final DeserializationContext context;
        public final Map<AnnotatedWithParams, BeanPropertyDefinition[]> creatorParams;
        public final CreatorCollector creators;
        public final VisibilityChecker<?> vchecker;

        public CreatorCollectionState(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final VisibilityChecker<?> visibilityChecker, final CreatorCollector creatorCollector, final Map<AnnotatedWithParams, BeanPropertyDefinition[]> map) {
            context = deserializationContext;
            beanDesc = beanDescription;
            vchecker = visibilityChecker;
            creators = creatorCollector;
            creatorParams = map;
        }

        public AnnotationIntrospector annotationIntrospector() {
            return context.getAnnotationIntrospector();
        }

        public void addImplicitFactoryCandidate(final CreatorCandidate creatorCandidate) {
            if (null == this._implicitFactoryCandidates) {
                _implicitFactoryCandidates = new LinkedList();
            }
            _implicitFactoryCandidates.add(creatorCandidate);
        }

        public void increaseExplicitFactoryCount() {
            _explicitFactoryCount++;
        }

        public boolean hasExplicitFactories() {
            return 0 < this._explicitFactoryCount;
        }

        public boolean hasImplicitFactoryCandidates() {
            return null != this._implicitFactoryCandidates;
        }

        public List<CreatorCandidate> implicitFactoryCandidates() {
            return _implicitFactoryCandidates;
        }

        public void addImplicitConstructorCandidate(final CreatorCandidate creatorCandidate) {
            if (null == this._implicitConstructorCandidates) {
                _implicitConstructorCandidates = new LinkedList();
            }
            _implicitConstructorCandidates.add(creatorCandidate);
        }

        public void increaseExplicitConstructorCount() {
            _explicitConstructorCount++;
        }

        public boolean hasExplicitConstructors() {
            return 0 < this._explicitConstructorCount;
        }

        public boolean hasImplicitConstructorCandidates() {
            return null != this._implicitConstructorCandidates;
        }

        public List<CreatorCandidate> implicitConstructorCandidates() {
            return _implicitConstructorCandidates;
        }
    }
}
