package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.*;
import com.fasterxml.jackson.databind.jsontype.*;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public abstract class MapperConfig<T extends MapperConfig<T>> implements ClassIntrospector.MixInResolver, Serializable {
    private static final long serialVersionUID = 2;
    protected final BaseSettings _base;
    protected final int _mapperFeatures;
    protected static final JsonInclude.Value EMPTY_INCLUDE = JsonInclude.Value.empty();
    protected static final JsonFormat.Value EMPTY_FORMAT = JsonFormat.Value.empty();
    public abstract ClassIntrospector.MixInResolver copy();
    public abstract ConfigOverride findConfigOverride(Class<?> cls);
    public abstract Class findMixInClassFor(Class cls);
    public abstract PropertyName findRootName(JavaType javaType);
    public abstract PropertyName findRootName(Class<?> cls);
    public abstract Class<?> getActiveView();
    public abstract ContextAttributes getAttributes();
    public abstract ConfigOverride getConfigOverride(Class<?> cls);
    public abstract JsonInclude.Value getDefaultInclusion(Class<?> cls, Class<?> cls2);
    public abstract Boolean getDefaultMergeable();
    public abstract Boolean getDefaultMergeable(Class<?> cls);
    public abstract JsonFormat.Value getDefaultPropertyFormat(Class<?> cls);
    public abstract JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class<?> cls);
    public abstract JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class<?> cls, AnnotatedClass annotatedClass);
    public abstract JsonInclude.Value getDefaultPropertyInclusion();
    public abstract JsonInclude.Value getDefaultPropertyInclusion(Class<?> cls);
    public abstract JsonIncludeProperties.Value getDefaultPropertyInclusions(Class<?> cls, AnnotatedClass annotatedClass);
    public abstract JsonSetter.Value getDefaultSetterInfo();
    public abstract VisibilityChecker<?> getDefaultVisibilityChecker();
    public abstract VisibilityChecker<?> getDefaultVisibilityChecker(Class<?> cls, AnnotatedClass annotatedClass);
    public abstract SubtypeResolver getSubtypeResolver();
    public abstract boolean useRootWrapping();
    public abstract T with(MapperFeature mapperFeature, boolean z);
    public abstract T with(MapperFeature... mapperFeatureArr);
    public abstract T without(MapperFeature... mapperFeatureArr);
    protected MapperConfig(final BaseSettings baseSettings, final int i2) {
        _base = baseSettings;
        _mapperFeatures = i2;
    }
    protected MapperConfig(final MapperConfig<T> mapperConfig, final int i2) {
        _base = mapperConfig._base;
        _mapperFeatures = i2;
    }
    protected MapperConfig(final MapperConfig<T> mapperConfig, final BaseSettings baseSettings) {
        _base = baseSettings;
        _mapperFeatures = mapperConfig._mapperFeatures;
    }
    protected MapperConfig(final MapperConfig<T> mapperConfig) {
        _base = mapperConfig._base;
        _mapperFeatures = mapperConfig._mapperFeatures;
    }
    public static <F extends Enum<F> & ConfigFeature> int collectFeatureDefaults(final Class<F> cls) {
        int mask = 0;
        for (final Object obj : cls.getEnumConstants()) {
            final ConfigFeature configFeature = (ConfigFeature) obj;
            if (configFeature.enabledByDefault()) {
                mask |= configFeature.getMask();
            }
        }
        return mask;
    }
    public final boolean isEnabled(final MapperFeature mapperFeature) {
        return mapperFeature.enabledIn(_mapperFeatures);
    }
    public final boolean hasMapperFeatures(final int i2) {
        return (_mapperFeatures & i2) == i2;
    }
    public final boolean isAnnotationProcessingEnabled() {
        return this.isEnabled(MapperFeature.USE_ANNOTATIONS);
    }
    public final boolean canOverrideAccessModifiers() {
        return this.isEnabled(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS);
    }
    public final boolean shouldSortPropertiesAlphabetically() {
        return this.isEnabled(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
    }
    public SerializableString compileString(final String str) {
        return new SerializedString(str);
    }
    public ClassIntrospector getClassIntrospector() {
        return _base.getClassIntrospector();
    }
    public AnnotationIntrospector getAnnotationIntrospector() {
        if (this.isEnabled(MapperFeature.USE_ANNOTATIONS)) {
            return _base.getAnnotationIntrospector();
        }
        return NopAnnotationIntrospector.instance;
    }
    public final PropertyNamingStrategy getPropertyNamingStrategy() {
        return _base.getPropertyNamingStrategy();
    }
    public final AccessorNamingStrategy.Provider getAccessorNaming() {
        return _base.getAccessorNaming();
    }
    public final HandlerInstantiator getHandlerInstantiator() {
        return _base.getHandlerInstantiator();
    }
    public final TypeResolverBuilder<?> getDefaultTyper(final JavaType javaType) {
        return _base.getTypeResolverBuilder();
    }
    public PolymorphicTypeValidator getPolymorphicTypeValidator() {
        final PolymorphicTypeValidator polymorphicTypeValidator = _base.getPolymorphicTypeValidator();
        return (polymorphicTypeValidator == LaissezFaireSubTypeValidator.instance && this.isEnabled(MapperFeature.BLOCK_UNSAFE_POLYMORPHIC_BASE_TYPES)) ? new DefaultBaseTypeLimitingValidator() : polymorphicTypeValidator;
    }
    public final TypeFactory getTypeFactory() {
        return _base.getTypeFactory();
    }
    public final JavaType constructType(final Class<?> cls) {
        return this.getTypeFactory().constructType(cls);
    }
    public final JavaType constructType(final TypeReference<?> typeReference) {
        return this.getTypeFactory().constructType(typeReference.getType());
    }
    public JavaType constructSpecializedType(final JavaType javaType, final Class<?> cls) {
        return this.getTypeFactory().constructSpecializedType(javaType, cls, true);
    }
    public BeanDescription introspectClassAnnotations(final Class<?> cls) {
        return this.introspectClassAnnotations(this.constructType(cls));
    }
    public BeanDescription introspectClassAnnotations(final JavaType javaType) {
        return this.getClassIntrospector().forClassAnnotations(this, javaType, this);
    }
    public BeanDescription introspectDirectClassAnnotations(final Class<?> cls) {
        return this.introspectDirectClassAnnotations(this.constructType(cls));
    }
    public final BeanDescription introspectDirectClassAnnotations(final JavaType javaType) {
        return this.getClassIntrospector().forDirectClassAnnotations(this, javaType, this);
    }
    public JsonInclude.Value getDefaultPropertyInclusion(final Class<?> cls, final JsonInclude.Value value) {
        final JsonInclude.Value include = this.getConfigOverride(cls).getInclude();
        return null != include ? include : value;
    }
    public JsonInclude.Value getDefaultInclusion(final Class<?> cls, final Class<?> cls2, final JsonInclude.Value value) {
        return JsonInclude.Value.mergeAll(value, this.getConfigOverride(cls).getInclude(), this.getConfigOverride(cls2).getIncludeAsProperty());
    }
    public final DateFormat getDateFormat() {
        return _base.getDateFormat();
    }
    public final Locale getLocale() {
        return _base.getLocale();
    }
    public final TimeZone getTimeZone() {
        return _base.getTimeZone();
    }
    public boolean hasExplicitTimeZone() {
        return _base.hasExplicitTimeZone();
    }
    public Base64Variant getBase64Variant() {
        return _base.getBase64Variant();
    }
    public TypeResolverBuilder<?> typeResolverBuilderInstance(final Annotated annotated, final Class<? extends TypeResolverBuilder<?>> cls) {
        final TypeResolverBuilder<?> typeResolverBuilderTypeResolverBuilderInstance;
        final HandlerInstantiator handlerInstantiator = this.getHandlerInstantiator();
        try {
            return (null == handlerInstantiator || null == (typeResolverBuilderTypeResolverBuilderInstance = handlerInstantiator.typeResolverBuilderInstance(this, annotated, cls))) ? ClassUtil.createInstance(cls, this.canOverrideAccessModifiers()) : typeResolverBuilderTypeResolverBuilderInstance;
        } catch (final NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    public TypeIdResolver typeIdResolverInstance(final Annotated annotated, final Class<? extends TypeIdResolver> cls) {
        final TypeIdResolver typeIdResolverTypeIdResolverInstance;
        final HandlerInstantiator handlerInstantiator = this.getHandlerInstantiator();
        try {
            return (null == handlerInstantiator || null == (typeIdResolverTypeIdResolverInstance = handlerInstantiator.typeIdResolverInstance(this, annotated, cls))) ? ClassUtil.createInstance(cls, this.canOverrideAccessModifiers()) : typeIdResolverTypeIdResolverInstance;
        } catch (final NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    public abstract MapperConfigBase withView(Class<?> cls);
}
