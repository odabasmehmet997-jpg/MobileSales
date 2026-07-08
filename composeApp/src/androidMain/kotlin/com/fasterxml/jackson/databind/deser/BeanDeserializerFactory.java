package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.impl.*;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.introspect.*;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.SubTypeValidator;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;

import java.util.*;

public class BeanDeserializerFactory extends BasicDeserializerFactory {
    private static final Class<?>[] INIT_CAUSE_PARAMS = {Throwable.class};
    public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(new DeserializerFactoryConfig());
    private static final long serialVersionUID = 1;
    public BeanDeserializerFactory(final DeserializerFactoryConfig deserializerFactoryConfig) {
        super(deserializerFactoryConfig);
    }
    public DeserializerFactory withConfig(final DeserializerFactoryConfig deserializerFactoryConfig) {
        if (_factoryConfig == deserializerFactoryConfig) {
            return this;
        }
        ClassUtil.verifyMustOverride(BeanDeserializerFactory.class, this, "withConfig");
        return new BeanDeserializerFactory(deserializerFactoryConfig);
    }
    public JsonDeserializer<Object> createBeanDeserializer(final DeserializationContext deserializationContext, final JavaType javaType, final BeanDescription beanDescription) throws JsonMappingException {
        final JavaType javaTypeMaterializeAbstractType;
        final DeserializationConfig config = deserializationContext.getConfig();
        JsonDeserializer<?> jsonDeserializer_findCustomBeanDeserializer = this._findCustomBeanDeserializer(javaType, config, beanDescription);
        if (null != jsonDeserializer_findCustomBeanDeserializer) {
            if (_factoryConfig.hasDeserializerModifiers()) {
                final Iterator<BeanDeserializerModifier> it = _factoryConfig.deserializerModifiers().iterator();
                while (it.hasNext()) {
                    jsonDeserializer_findCustomBeanDeserializer = it.next().modifyDeserializer(deserializationContext.getConfig(), beanDescription, jsonDeserializer_findCustomBeanDeserializer);
                }
            }
            return (JsonDeserializer<Object>) jsonDeserializer_findCustomBeanDeserializer;
        }
        if (javaType.isThrowable()) {
            return this.buildThrowableDeserializer(deserializationContext, javaType, beanDescription);
        }
        if (javaType.isAbstract() && !javaType.isPrimitive() && !javaType.isEnumType() && null != (javaTypeMaterializeAbstractType = materializeAbstractType(deserializationContext, javaType, beanDescription))) {
            return this.buildBeanDeserializer(deserializationContext, javaTypeMaterializeAbstractType, config.introspect(javaTypeMaterializeAbstractType));
        }
        final JsonDeserializer<?> jsonDeserializerFindStdDeserializer = this.findStdDeserializer(deserializationContext, javaType, beanDescription);
        if (null != jsonDeserializerFindStdDeserializer) {
            return (JsonDeserializer<Object>) jsonDeserializerFindStdDeserializer;
        }
        if (!this.isPotentialBeanType(javaType.getRawClass())) {
            return null;
        }
        this._validateSubType(deserializationContext, javaType, beanDescription);
        final JsonDeserializer<Object> jsonDeserializer_findUnsupportedTypeDeserializer = this._findUnsupportedTypeDeserializer(deserializationContext, javaType, beanDescription);
        return null != jsonDeserializer_findUnsupportedTypeDeserializer ? jsonDeserializer_findUnsupportedTypeDeserializer : this.buildBeanDeserializer(deserializationContext, javaType, beanDescription);
    }
    public JsonDeserializer<Object> createBuilderBasedDeserializer(final DeserializationContext deserializationContext, final JavaType javaType, final BeanDescription beanDescription, final Class<?> cls) throws JsonMappingException {
        final JavaType javaTypeConstructType;
        if (deserializationContext.isEnabled(MapperFeature.INFER_BUILDER_TYPE_BINDINGS)) {
            javaTypeConstructType = deserializationContext.getTypeFactory().constructParametricType(cls, javaType.getBindings());
        } else {
            javaTypeConstructType = deserializationContext.constructType(cls);
        }
        return this.buildBuilderBasedDeserializer(deserializationContext, javaType, deserializationContext.getConfig().introspectForBuilder(javaTypeConstructType, beanDescription));
    }
    protected JsonDeserializer<?> findStdDeserializer(final DeserializationContext deserializationContext, final JavaType javaType, final BeanDescription beanDescription) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializerFindDefaultDeserializer = this.findDefaultDeserializer(deserializationContext, javaType, beanDescription);
        if (null != jsonDeserializerFindDefaultDeserializer && _factoryConfig.hasDeserializerModifiers()) {
            final Iterator<BeanDeserializerModifier> it = _factoryConfig.deserializerModifiers().iterator();
            while (it.hasNext()) {
                jsonDeserializerFindDefaultDeserializer = it.next().modifyDeserializer(deserializationContext.getConfig(), beanDescription, jsonDeserializerFindDefaultDeserializer);
            }
        }
        return jsonDeserializerFindDefaultDeserializer;
    }
    protected JsonDeserializer<Object> _findUnsupportedTypeDeserializer(final DeserializationContext deserializationContext, final JavaType javaType, final BeanDescription beanDescription) throws JsonMappingException {
        final String strCheckUnsupportedType = BeanUtil.checkUnsupportedType(javaType);
        if (null == strCheckUnsupportedType || null != deserializationContext.getConfig().findMixInClassFor(javaType.getRawClass())) {
            return null;
        }
        return new UnsupportedTypeDeserializer(javaType, strCheckUnsupportedType);
    }
    protected JavaType materializeAbstractType(final DeserializationContext deserializationContext, final JavaType javaType, final BeanDescription beanDescription) throws JsonMappingException {
        final Iterator<AbstractTypeResolver> it = _factoryConfig.abstractTypeResolvers().iterator();
        while (it.hasNext()) {
            final JavaType javaTypeResolveAbstractType = it.next().resolveAbstractType(deserializationContext.getConfig(), beanDescription);
            if (null != javaTypeResolveAbstractType) {
                return javaTypeResolveAbstractType;
            }
        }
        return null;
    }
    public JsonDeserializer<Object> buildBeanDeserializer(final DeserializationContext deserializationContext, final JavaType javaType, final BeanDescription beanDescription) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializerBuild;
        try {
            final ValueInstantiator valueInstantiatorFindValueInstantiator = this.findValueInstantiator(deserializationContext, beanDescription);
            BeanDeserializerBuilder beanDeserializerBuilderConstructBeanDeserializerBuilder = this.constructBeanDeserializerBuilder(deserializationContext, beanDescription);
            beanDeserializerBuilderConstructBeanDeserializerBuilder.setValueInstantiator(valueInstantiatorFindValueInstantiator);
            this.addBeanProps(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            this.addObjectIdReader(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            this.addBackReferenceProperties(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            this.addInjectables(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            final DeserializationConfig config = deserializationContext.getConfig();
            if (_factoryConfig.hasDeserializerModifiers()) {
                final Iterator<BeanDeserializerModifier> it = _factoryConfig.deserializerModifiers().iterator();
                while (it.hasNext()) {
                    beanDeserializerBuilderConstructBeanDeserializerBuilder = it.next().updateBuilder(config, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
                }
            }
            if (javaType.isAbstract() && !valueInstantiatorFindValueInstantiator.canInstantiate()) {
                jsonDeserializerBuild = beanDeserializerBuilderConstructBeanDeserializerBuilder.buildAbstract();
            } else {
                jsonDeserializerBuild = beanDeserializerBuilderConstructBeanDeserializerBuilder.build();
            }
            if (_factoryConfig.hasDeserializerModifiers()) {
                final Iterator<BeanDeserializerModifier> it2 = _factoryConfig.deserializerModifiers().iterator();
                while (it2.hasNext()) {
                    jsonDeserializerBuild = it2.next().modifyDeserializer(config, beanDescription, jsonDeserializerBuild);
                }
            }
            return (JsonDeserializer<Object>) jsonDeserializerBuild;
        } catch (final IllegalArgumentException e2) {
            final InvalidDefinitionException invalidDefinitionExceptionFrom = InvalidDefinitionException.from(deserializationContext.getParser(), ClassUtil.exceptionMessage(e2), beanDescription, null);
            invalidDefinitionExceptionFrom.initCause(e2);
            throw invalidDefinitionExceptionFrom;
        } catch (final NoClassDefFoundError e3) {
            return new ErrorThrowingDeserializer(e3);
        }
    }
    protected JsonDeserializer<Object> buildBuilderBasedDeserializer(final DeserializationContext deserializationContext, final JavaType javaType, final BeanDescription beanDescription) throws JsonMappingException {
        try {
            final ValueInstantiator valueInstantiatorFindValueInstantiator = this.findValueInstantiator(deserializationContext, beanDescription);
            final DeserializationConfig config = deserializationContext.getConfig();
            BeanDeserializerBuilder beanDeserializerBuilderConstructBeanDeserializerBuilder = this.constructBeanDeserializerBuilder(deserializationContext, beanDescription);
            beanDeserializerBuilderConstructBeanDeserializerBuilder.setValueInstantiator(valueInstantiatorFindValueInstantiator);
            this.addBeanProps(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            this.addObjectIdReader(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            this.addBackReferenceProperties(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            this.addInjectables(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            final JsonPOJOBuilder.Value valueFindPOJOBuilderConfig = (JsonPOJOBuilder.Value) beanDescription.findPOJOBuilderConfig();
            final String str = null == valueFindPOJOBuilderConfig ? "build" : valueFindPOJOBuilderConfig.buildMethodName();
            final AnnotatedMethod annotatedMethodFindMethod = beanDescription.findMethod(str, null);
            if (null != annotatedMethodFindMethod && config.canOverrideAccessModifiers()) {
                ClassUtil.checkAndFixAccess(annotatedMethodFindMethod.getMember(), config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            }
            beanDeserializerBuilderConstructBeanDeserializerBuilder.setPOJOBuilder(annotatedMethodFindMethod, valueFindPOJOBuilderConfig);
            if (_factoryConfig.hasDeserializerModifiers()) {
                final Iterator<BeanDeserializerModifier> it = _factoryConfig.deserializerModifiers().iterator();
                while (it.hasNext()) {
                    beanDeserializerBuilderConstructBeanDeserializerBuilder = it.next().updateBuilder(config, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
                }
            }
            JsonDeserializer<?> jsonDeserializerBuildBuilderBased = beanDeserializerBuilderConstructBeanDeserializerBuilder.buildBuilderBased(javaType, str);
            if (_factoryConfig.hasDeserializerModifiers()) {
                final Iterator<BeanDeserializerModifier> it2 = _factoryConfig.deserializerModifiers().iterator();
                while (it2.hasNext()) {
                    jsonDeserializerBuildBuilderBased = it2.next().modifyDeserializer(config, beanDescription, jsonDeserializerBuildBuilderBased);
                }
            }
            return (JsonDeserializer<Object>) jsonDeserializerBuildBuilderBased;
        } catch (final IllegalArgumentException e2) {
            throw InvalidDefinitionException.from(deserializationContext.getParser(), ClassUtil.exceptionMessage(e2), beanDescription, null);
        } catch (final NoClassDefFoundError e3) {
            return new ErrorThrowingDeserializer(e3);
        }
    }
    protected void addObjectIdReader(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        final SettableBeanProperty settableBeanPropertyFindProperty;
        final ObjectIdGenerator<?> objectIdGeneratorObjectIdGeneratorInstance;
        final JavaType javaType;
        final ObjectIdInfo objectIdInfo = beanDescription.getObjectIdInfo();
        if (null == objectIdInfo) {
            return;
        }
        final Class<? extends ObjectIdGenerator<?>> generatorType = objectIdInfo.getGeneratorType();
        final ObjectIdResolver objectIdResolverObjectIdResolverInstance = deserializationContext.objectIdResolverInstance(beanDescription.getClassInfo(), objectIdInfo);
        if (ObjectIdGeneratorsPropertyGenerator.class == generatorType) {
            final PropertyName propertyName = objectIdInfo.getPropertyName();
            settableBeanPropertyFindProperty = beanDeserializerBuilder.findProperty(propertyName);
            if (null == settableBeanPropertyFindProperty) {
                throw new IllegalArgumentException(String.format("Invalid Object Id definition for %s: cannot find property with name %s", ClassUtil.getTypeDescription(beanDescription.getType()), ClassUtil.name(propertyName)));
            }
            final JavaType type = settableBeanPropertyFindProperty.getType();
            javaType = type;
            objectIdGeneratorObjectIdGeneratorInstance = new PropertyBasedObjectIdGenerator(objectIdInfo.getScope());
        } else {
            final JavaType javaType2 = deserializationContext.getTypeFactory().findTypeParameters(deserializationContext.constructType(generatorType), ObjectIdGenerator.class)[0];
            settableBeanPropertyFindProperty = null;
            objectIdGeneratorObjectIdGeneratorInstance = deserializationContext.objectIdGeneratorInstance(beanDescription.getClassInfo(), objectIdInfo);
            javaType = javaType2;
        }
        final SettableBeanProperty settableBeanProperty = settableBeanPropertyFindProperty;
        beanDeserializerBuilder.setObjectIdReader(ObjectIdReader.construct(javaType, objectIdInfo.getPropertyName(), objectIdGeneratorObjectIdGeneratorInstance, deserializationContext.findRootValueDeserializer(javaType), settableBeanProperty, objectIdResolverObjectIdResolverInstance));
    }
    public JsonDeserializer<Object> buildThrowableDeserializer(final DeserializationContext deserializationContext, final JavaType javaType, final BeanDescription beanDescription) throws JsonMappingException {
        final SettableBeanProperty settableBeanPropertyConstructSettableProperty;
        final DeserializationConfig config = deserializationContext.getConfig();
        BeanDeserializerBuilder beanDeserializerBuilderConstructBeanDeserializerBuilder = this.constructBeanDeserializerBuilder(deserializationContext, beanDescription);
        beanDeserializerBuilderConstructBeanDeserializerBuilder.setValueInstantiator(this.findValueInstantiator(deserializationContext, beanDescription));
        this.addBeanProps(deserializationContext, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
        final AnnotatedMethod annotatedMethodFindMethod = beanDescription.findMethod("initCause", BeanDeserializerFactory.INIT_CAUSE_PARAMS);
        if (null != annotatedMethodFindMethod && null != (settableBeanPropertyConstructSettableProperty = constructSettableProperty(deserializationContext, beanDescription, SimpleBeanPropertyDefinition.construct(deserializationContext.getConfig(), annotatedMethodFindMethod, new PropertyName("cause")), annotatedMethodFindMethod.getParameterType(0)))) {
            beanDeserializerBuilderConstructBeanDeserializerBuilder.addOrReplaceProperty(settableBeanPropertyConstructSettableProperty, true);
        }
        beanDeserializerBuilderConstructBeanDeserializerBuilder.addIgnorable("localizedMessage");
        beanDeserializerBuilderConstructBeanDeserializerBuilder.addIgnorable("suppressed");
        if (_factoryConfig.hasDeserializerModifiers()) {
            final Iterator<BeanDeserializerModifier> it = _factoryConfig.deserializerModifiers().iterator();
            while (it.hasNext()) {
                beanDeserializerBuilderConstructBeanDeserializerBuilder = it.next().updateBuilder(config, beanDescription, beanDeserializerBuilderConstructBeanDeserializerBuilder);
            }
        }
        JsonDeserializer<?> jsonDeserializerBuild = beanDeserializerBuilderConstructBeanDeserializerBuilder.build();
        if (jsonDeserializerBuild instanceof BeanDeserializer) {
            jsonDeserializerBuild = new ThrowableDeserializer((BeanDeserializer) jsonDeserializerBuild);
        }
        if (_factoryConfig.hasDeserializerModifiers()) {
            final Iterator<BeanDeserializerModifier> it2 = _factoryConfig.deserializerModifiers().iterator();
            while (it2.hasNext()) {
                jsonDeserializerBuild = it2.next().modifyDeserializer(config, beanDescription, jsonDeserializerBuild);
            }
        }
        return (JsonDeserializer<Object>) jsonDeserializerBuild;
    }
    protected BeanDeserializerBuilder constructBeanDeserializerBuilder(final DeserializationContext deserializationContext, final BeanDescription beanDescription) {
        return new BeanDeserializerBuilder(beanDescription, deserializationContext);
    }
    protected void addBeanProps(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        final Set<String> setEmptySet;
        final Set<String> set;
        SettableBeanProperty settableBeanPropertyConstructSetterlessProperty = null;
        CreatorProperty creatorProperty;
        final CreatorProperty[] fromObjectArguments = !beanDescription.getType().isAbstract() ? (CreatorProperty[]) beanDeserializerBuilder.getValueInstantiator().getFromObjectArguments(deserializationContext.getConfig()) : null;
        final boolean z = null != fromObjectArguments;
        final JsonIgnoreProperties.Value defaultPropertyIgnorals = deserializationContext.getConfig().getDefaultPropertyIgnorals(beanDescription.getBeanClass(), beanDescription.getClassInfo());
        if (null != defaultPropertyIgnorals) {
            beanDeserializerBuilder.setIgnoreUnknownProperties(defaultPropertyIgnorals.getIgnoreUnknown());
            setEmptySet = defaultPropertyIgnorals.findIgnoredForDeserialization();
            final Iterator<String> it = setEmptySet.iterator();
            while (it.hasNext()) {
                beanDeserializerBuilder.addIgnorable(it.next());
            }
        } else {
            setEmptySet = Collections.emptySet();
        }
        final Set<String> set2 = setEmptySet;
        final JsonIncludeProperties.Value defaultPropertyInclusions = deserializationContext.getConfig().getDefaultPropertyInclusions(beanDescription.getBeanClass(), beanDescription.getClassInfo());
        if (null != defaultPropertyInclusions) {
            final Set<String> included = defaultPropertyInclusions.getIncluded();
            if (null != included) {
                final Iterator<String> it2 = included.iterator();
                while (it2.hasNext()) {
                    beanDeserializerBuilder.addIncludable(it2.next());
                }
            }
            set = included;
        } else {
            set = null;
        }
        final AnnotatedMember annotatedMemberFindAnySetterAccessor = beanDescription.findAnySetterAccessor();
        if (null != annotatedMemberFindAnySetterAccessor) {
            beanDeserializerBuilder.setAnySetter(this.constructAnySetter(deserializationContext, beanDescription, annotatedMemberFindAnySetterAccessor));
        } else {
            final Set<String> ignoredPropertyNames = beanDescription.getIgnoredPropertyNames();
            if (null != ignoredPropertyNames) {
                final Iterator<String> it3 = ignoredPropertyNames.iterator();
                while (it3.hasNext()) {
                    beanDeserializerBuilder.addIgnorable(it3.next());
                }
            }
        }
        final boolean z2 = deserializationContext.isEnabled(MapperFeature.USE_GETTERS_AS_SETTERS) && deserializationContext.isEnabled(MapperFeature.AUTO_DETECT_GETTERS);
        List<BeanPropertyDefinition> listFilterBeanProps = this.filterBeanProps(deserializationContext, beanDescription, beanDeserializerBuilder, beanDescription.findProperties(), set2, set);
        if (_factoryConfig.hasDeserializerModifiers()) {
            final Iterator<BeanDeserializerModifier> it4 = _factoryConfig.deserializerModifiers().iterator();
            while (it4.hasNext()) {
                listFilterBeanProps = it4.next().updateProperties(deserializationContext.getConfig(), beanDescription, listFilterBeanProps);
            }
        }
        for (final BeanPropertyDefinition beanPropertyDefinition : listFilterBeanProps) {
            if (beanPropertyDefinition.hasSetter()) {
                settableBeanPropertyConstructSetterlessProperty = this.constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getSetter().getParameterType(0));
            } else if (beanPropertyDefinition.hasField()) {
                settableBeanPropertyConstructSetterlessProperty = this.constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getField().getType());
            } else {
                final AnnotatedMethod getter = beanPropertyDefinition.getGetter();
                if (null == getter) {
                    settableBeanPropertyConstructSetterlessProperty = null;
                } else if (z2 && this._isSetterlessType(getter.getRawType())) {
                    if (!beanDeserializerBuilder.hasIgnorable(beanPropertyDefinition.getName())) {
                        settableBeanPropertyConstructSetterlessProperty = this.constructSetterlessProperty(deserializationContext, beanDescription, beanPropertyDefinition);
                    }
                } else if (!beanPropertyDefinition.hasConstructorParameter() && null != beanPropertyDefinition.getMetadata().getMergeInfo()) {
                    settableBeanPropertyConstructSetterlessProperty = this.constructSetterlessProperty(deserializationContext, beanDescription, beanPropertyDefinition);
                }
            }
            if (z && beanPropertyDefinition.hasConstructorParameter()) {
                final String name = beanPropertyDefinition.getName();
                final int length = fromObjectArguments.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        creatorProperty = null;
                        break;
                    }
                    final CreatorProperty creatorProperty2 = fromObjectArguments[i2];
                    if (name.equals(creatorProperty2.getName()) && (creatorProperty2 instanceof CreatorProperty)) {
                        creatorProperty = creatorProperty2;
                        break;
                    }
                    i2++;
                }
                if (null == creatorProperty) {
                    final ArrayList arrayList = new ArrayList();
                    for (final CreatorProperty creatorProperty3 : fromObjectArguments) {
                        arrayList.add(creatorProperty3.getName());
                    }
                    deserializationContext.reportBadPropertyDefinition(beanDescription, beanPropertyDefinition, "Could not find creator property with name %s (known Creator properties: %s)", ClassUtil.name(name), arrayList);
                } else {
                    if (null != settableBeanPropertyConstructSetterlessProperty) {
                        creatorProperty.setFallbackSetter(settableBeanPropertyConstructSetterlessProperty);
                    }
                    Class<?>[] clsArrFindViews = beanPropertyDefinition.findViews();
                    if (null == clsArrFindViews) {
                        clsArrFindViews = beanDescription.findDefaultViews();
                    }
                    creatorProperty.setViews(clsArrFindViews);
                    beanDeserializerBuilder.addCreatorProperty(creatorProperty);
                }
            } else if (null != settableBeanPropertyConstructSetterlessProperty) {
                Class<?>[] clsArrFindViews2 = beanPropertyDefinition.findViews();
                if (null == clsArrFindViews2) {
                    clsArrFindViews2 = beanDescription.findDefaultViews();
                }
                settableBeanPropertyConstructSetterlessProperty.setViews(clsArrFindViews2);
                beanDeserializerBuilder.addProperty(settableBeanPropertyConstructSetterlessProperty);
            }
        }
    }
    private boolean _isSetterlessType(final Class<?> cls) {
        return Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls);
    }
    protected List<BeanPropertyDefinition> filterBeanProps(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final BeanDeserializerBuilder beanDeserializerBuilder, final List<BeanPropertyDefinition> list, final Set<String> set) throws JsonMappingException {
        return this.filterBeanProps(deserializationContext, beanDescription, beanDeserializerBuilder, list, set, null);
    }
    protected List<BeanPropertyDefinition> filterBeanProps(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final BeanDeserializerBuilder beanDeserializerBuilder, final List<BeanPropertyDefinition> list, final Set<String> set, final Set<String> set2) {
        Class<?> rawPrimaryType;
        final ArrayList arrayList = new ArrayList(Math.max(4, list.size()));
        final HashMap map = new HashMap();
        for (final BeanPropertyDefinition beanPropertyDefinition : list) {
            final String name = beanPropertyDefinition.getName();
            if (!IgnorePropertiesUtil.shouldIgnore(name, set, set2)) {
                if (!beanPropertyDefinition.hasConstructorParameter() && null != (rawPrimaryType = beanPropertyDefinition.getRawPrimaryType()) && this.isIgnorableType(deserializationContext.getConfig(), beanPropertyDefinition, rawPrimaryType, map)) {
                    beanDeserializerBuilder.addIgnorable(name);
                } else {
                    arrayList.add(beanPropertyDefinition);
                }
            }
        }
        return arrayList;
    }
    protected void addBackReferenceProperties(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        final List<BeanPropertyDefinition> listFindBackReferences = beanDescription.findBackReferences();
        if (null != listFindBackReferences) {
            for (final BeanPropertyDefinition beanPropertyDefinition : listFindBackReferences) {
                beanDeserializerBuilder.addBackReferenceProperty(beanPropertyDefinition.findReferenceName(), this.constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getPrimaryType()));
            }
        }
    }
    protected void addReferenceProperties(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        this.addBackReferenceProperties(deserializationContext, beanDescription, beanDeserializerBuilder);
    }
    protected void addInjectables(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        final Map<Object, AnnotatedMember> mapFindInjectables = beanDescription.findInjectables();
        if (null != mapFindInjectables) {
            for (final Map.Entry<Object, AnnotatedMember> entry : mapFindInjectables.entrySet()) {
                final AnnotatedMember value = entry.getValue();
                beanDeserializerBuilder.addInjectable(PropertyName.construct(value.getName()), value.getType(), beanDescription.getClassAnnotations(), value, entry.getKey());
            }
        }
    }
    protected SettableAnyProperty constructAnySetter(DeserializationContext deserializationContext, BeanDescription beanDescription, AnnotatedMember annotatedMember) throws JsonMappingException {
        JavaType keyType;
        BeanProperty.Std std;
        JavaType javaType;
        KeyDeserializer keyDeserializer;
        if (annotatedMember instanceof AnnotatedMethod annotatedMethod) {
            keyType = annotatedMethod.getParameterType(0);
            javaType = resolveMemberAndTypeAnnotations(deserializationContext, annotatedMember, annotatedMethod.getParameterType(1));
            std = new BeanProperty.Std(PropertyName.construct(annotatedMember.getName()), javaType, null, annotatedMember, PropertyMetadata.STD_OPTIONAL);
        } else if (annotatedMember instanceof AnnotatedField) {
            JavaType resolveMemberAndTypeAnnotations = resolveMemberAndTypeAnnotations(deserializationContext, annotatedMember, annotatedMember.getType());
            keyType = resolveMemberAndTypeAnnotations.getKeyType();
            JavaType contentType = resolveMemberAndTypeAnnotations.getContentType();
            std = new BeanProperty.Std(PropertyName.construct(annotatedMember.getName()), resolveMemberAndTypeAnnotations, null, annotatedMember, PropertyMetadata.STD_OPTIONAL);
            javaType = contentType;
        } else {
            return deserializationContext.reportBadDefinition(beanDescription.getType(), String.format("Unrecognized mutator type for any setter: %s", annotatedMember.getClass()));
        }
        KeyDeserializer findKeyDeserializerFromAnnotation = findKeyDeserializerFromAnnotation(deserializationContext, annotatedMember);
        KeyDeserializer r2 = findKeyDeserializerFromAnnotation;
        if (findKeyDeserializerFromAnnotation == null) {
            r2 = keyType.getValueHandler();
        }
        if (false) {
            keyDeserializer = deserializationContext.findKeyDeserializer(keyType, std);
        }    else {
            boolean z = r2 instanceof ContextualKeyDeserializer;
            keyDeserializer = r2;
            if (z) {
                keyDeserializer = ((ContextualKeyDeserializer) r2).createContextual(deserializationContext, std);
            }
        }
        KeyDeserializer keyDeserializer2 = keyDeserializer;
        JsonDeserializer<?> findContentDeserializerFromAnnotation = findContentDeserializerFromAnnotation(deserializationContext, annotatedMember);
        if (findContentDeserializerFromAnnotation == null) {
            findContentDeserializerFromAnnotation = javaType.getValueHandler();
        }
        return new SettableAnyProperty(std, annotatedMember, javaType, keyDeserializer2, findContentDeserializerFromAnnotation != null ? (JsonDeserializer<Object>) deserializationContext.handlePrimaryContextualization(findContentDeserializerFromAnnotation, std, javaType) : (JsonDeserializer<Object>) findContentDeserializerFromAnnotation, javaType.getTypeHandler());
    }
    protected SettableBeanProperty constructSettableProperty(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final BeanPropertyDefinition beanPropertyDefinition, final JavaType javaType) throws JsonMappingException {
        SettableBeanProperty fieldProperty;
        final AnnotatedMember nonConstructorMutator = beanPropertyDefinition.getNonConstructorMutator();
        if (null == nonConstructorMutator) {
            deserializationContext.reportBadPropertyDefinition(beanDescription, beanPropertyDefinition, "No non-constructor mutator available");
        }
        final JavaType javaTypeResolveMemberAndTypeAnnotations = this.resolveMemberAndTypeAnnotations(deserializationContext, nonConstructorMutator, javaType);
        final TypeDeserializer typeDeserializer = javaTypeResolveMemberAndTypeAnnotations.getTypeHandler();
        if (nonConstructorMutator instanceof AnnotatedMethod) {
            fieldProperty = new MethodProperty(beanPropertyDefinition, javaTypeResolveMemberAndTypeAnnotations, typeDeserializer, beanDescription.getClassAnnotations(), (AnnotatedMethod) nonConstructorMutator);
        } else {
            fieldProperty = new FieldProperty(beanPropertyDefinition, javaTypeResolveMemberAndTypeAnnotations, typeDeserializer, beanDescription.getClassAnnotations(), (AnnotatedField) nonConstructorMutator);
        }
        JsonDeserializer<?> jsonDeserializerFindDeserializerFromAnnotation = this.findDeserializerFromAnnotation(deserializationContext, nonConstructorMutator);
        if (null == jsonDeserializerFindDeserializerFromAnnotation) {
            jsonDeserializerFindDeserializerFromAnnotation = javaTypeResolveMemberAndTypeAnnotations.getValueHandler();
        }
        if (null != jsonDeserializerFindDeserializerFromAnnotation) {
            fieldProperty = fieldProperty.withValueDeserializer(deserializationContext.handlePrimaryContextualization(jsonDeserializerFindDeserializerFromAnnotation, fieldProperty, javaTypeResolveMemberAndTypeAnnotations));
        }
        final AnnotationIntrospector.ReferenceProperty referencePropertyFindReferenceType = beanPropertyDefinition.findReferenceType();
        if (null != referencePropertyFindReferenceType && referencePropertyFindReferenceType.isManagedReference()) {
            fieldProperty.setManagedReferenceName(referencePropertyFindReferenceType.getName());
        }
        final ObjectIdInfo objectIdInfoFindObjectIdInfo = beanPropertyDefinition.findObjectIdInfo();
        if (null != objectIdInfoFindObjectIdInfo) {
            fieldProperty.setObjectIdInfo(objectIdInfoFindObjectIdInfo);
        }
        return fieldProperty;
    }
    protected SettableBeanProperty constructSetterlessProperty(final DeserializationContext deserializationContext, final BeanDescription beanDescription, final BeanPropertyDefinition beanPropertyDefinition) throws JsonMappingException {
        final AnnotatedMethod getter = beanPropertyDefinition.getGetter();
        final JavaType javaTypeResolveMemberAndTypeAnnotations = this.resolveMemberAndTypeAnnotations(deserializationContext, getter, getter.getType());
        final SetterlessProperty setterlessProperty = new SetterlessProperty(beanPropertyDefinition, javaTypeResolveMemberAndTypeAnnotations, javaTypeResolveMemberAndTypeAnnotations.getTypeHandler(), beanDescription.getClassAnnotations(), getter);
        JsonDeserializer<?> jsonDeserializerFindDeserializerFromAnnotation = this.findDeserializerFromAnnotation(deserializationContext, getter);
        if (null == jsonDeserializerFindDeserializerFromAnnotation) {
            jsonDeserializerFindDeserializerFromAnnotation = javaTypeResolveMemberAndTypeAnnotations.getValueHandler();
        }
        return null != jsonDeserializerFindDeserializerFromAnnotation ? setterlessProperty.withValueDeserializer(deserializationContext.handlePrimaryContextualization(jsonDeserializerFindDeserializerFromAnnotation, setterlessProperty, javaTypeResolveMemberAndTypeAnnotations)) : setterlessProperty;
    }
    protected boolean isPotentialBeanType(final Class<?> cls) {
        final String strCanBeABeanType = ClassUtil.canBeABeanType(cls);
        if (null != strCanBeABeanType) {
            throw new IllegalArgumentException("Cannot deserialize Class " + cls.getName() + " (of type " + strCanBeABeanType + ") as a Bean");
        }
        if (ClassUtil.isProxyType(cls)) {
            throw new IllegalArgumentException("Cannot deserialize Proxy class " + cls.getName() + " as a Bean");
        }
        final String strIsLocalType = ClassUtil.isLocalType(cls, true);
        if (null == strIsLocalType) {
            return true;
        }
        throw new IllegalArgumentException("Cannot deserialize Class " + cls.getName() + " (of type " + strIsLocalType + ") as a Bean");
    }
    protected boolean isIgnorableType(final DeserializationConfig deserializationConfig, final BeanPropertyDefinition beanPropertyDefinition, final Class<?> cls, final Map<Class<?>, Boolean> map) {
        Boolean isIgnoredType;
        final Boolean bool = map.get(cls);
        if (null != bool) {
            return bool.booleanValue();
        }
        if (String.class == cls || cls.isPrimitive()) {
            isIgnoredType = Boolean.FALSE;
        } else {
            isIgnoredType = deserializationConfig.getConfigOverride(cls).getIsIgnoredType();
            if (null == isIgnoredType) {
                isIgnoredType = deserializationConfig.getAnnotationIntrospector().isIgnorableType(deserializationConfig.introspectClassAnnotations(cls).getClassInfo());
                if (null == isIgnoredType) {
                    isIgnoredType = Boolean.FALSE;
                }
            }
        }
        map.put(cls, isIgnoredType);
        return isIgnoredType.booleanValue();
    }
    protected void _validateSubType(final DeserializationContext deserializationContext, final JavaType javaType, final BeanDescription beanDescription) throws JsonMappingException {
        SubTypeValidator.instance().validateSubType(deserializationContext, javaType, beanDescription);
    }
}
