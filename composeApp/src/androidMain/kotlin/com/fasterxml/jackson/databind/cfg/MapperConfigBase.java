package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.*;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.RootNameLookup;

import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public abstract class MapperConfigBase<CFG extends ConfigFeature, T extends MapperConfigBase<CFG, T>> extends MapperConfig<T> {
    protected final ContextAttributes _attributes;
    protected final ConfigOverrides _configOverrides;
    protected final SimpleMixInResolver _mixIns;
    protected final PropertyName _rootName;
    protected final RootNameLookup _rootNames;
    protected final SubtypeResolver _subtypeResolver;
    protected final Class<?> _view;
    protected static final ConfigOverride EMPTY_OVERRIDE = ConfigOverride.empty();
    private static final int DEFAULT_MAPPER_FEATURES = collectFeatureDefaults(MapperFeature.class);
    private static final int AUTO_DETECT_MASK = (((MapperFeature.AUTO_DETECT_FIELDS.getMask() | MapperFeature.AUTO_DETECT_GETTERS.getMask()) | MapperFeature.AUTO_DETECT_IS_GETTERS.getMask()) | MapperFeature.AUTO_DETECT_SETTERS.getMask()) | MapperFeature.AUTO_DETECT_CREATORS.getMask();
    protected abstract T _withBase(BaseSettings baseSettings);
    protected abstract T _withMapperFeatures(int i2);
    public abstract T with(ContextAttributes contextAttributes);
    public abstract T with(SubtypeResolver subtypeResolver);
    public abstract T withRootName(PropertyName propertyName);
    public abstract MapperConfigBase withView(Class<?> cls);
    protected MapperConfigBase(final BaseSettings baseSettings, final SubtypeResolver subtypeResolver, final SimpleMixInResolver simpleMixInResolver, final RootNameLookup rootNameLookup, final ConfigOverrides configOverrides) {
        super(baseSettings, MapperConfigBase.DEFAULT_MAPPER_FEATURES);
        _mixIns = simpleMixInResolver;
        _subtypeResolver = subtypeResolver;
        _rootNames = rootNameLookup;
        _rootName = null;
        _view = null;
        _attributes = ContextAttributes.getEmpty();
        _configOverrides = configOverrides;
    }
    protected MapperConfigBase(final MapperConfigBase<CFG, T> mapperConfigBase, final SubtypeResolver subtypeResolver, final SimpleMixInResolver simpleMixInResolver, final RootNameLookup rootNameLookup, final ConfigOverrides configOverrides) {
        super(mapperConfigBase, mapperConfigBase._base.copy());
        _mixIns = simpleMixInResolver;
        _subtypeResolver = subtypeResolver;
        _rootNames = rootNameLookup;
        _rootName = mapperConfigBase._rootName;
        _view = mapperConfigBase._view;
        _attributes = mapperConfigBase._attributes;
        _configOverrides = configOverrides;
    }
    protected MapperConfigBase(final MapperConfigBase<CFG, T> mapperConfigBase) {
        super(mapperConfigBase);
        _mixIns = mapperConfigBase._mixIns;
        _subtypeResolver = mapperConfigBase._subtypeResolver;
        _rootNames = mapperConfigBase._rootNames;
        _rootName = mapperConfigBase._rootName;
        _view = mapperConfigBase._view;
        _attributes = mapperConfigBase._attributes;
        _configOverrides = mapperConfigBase._configOverrides;
    }
    protected MapperConfigBase(final MapperConfigBase<CFG, T> mapperConfigBase, final BaseSettings baseSettings) {
        super(mapperConfigBase, baseSettings);
        _mixIns = mapperConfigBase._mixIns;
        _subtypeResolver = mapperConfigBase._subtypeResolver;
        _rootNames = mapperConfigBase._rootNames;
        _rootName = mapperConfigBase._rootName;
        _view = mapperConfigBase._view;
        _attributes = mapperConfigBase._attributes;
        _configOverrides = mapperConfigBase._configOverrides;
    }
    protected MapperConfigBase(final MapperConfigBase<CFG, T> mapperConfigBase, final int i2) {
        super(mapperConfigBase, i2);
        _mixIns = mapperConfigBase._mixIns;
        _subtypeResolver = mapperConfigBase._subtypeResolver;
        _rootNames = mapperConfigBase._rootNames;
        _rootName = mapperConfigBase._rootName;
        _view = mapperConfigBase._view;
        _attributes = mapperConfigBase._attributes;
        _configOverrides = mapperConfigBase._configOverrides;
    }
    protected MapperConfigBase(final MapperConfigBase<CFG, T> mapperConfigBase, final SubtypeResolver subtypeResolver) {
        super(mapperConfigBase);
        _mixIns = mapperConfigBase._mixIns;
        _subtypeResolver = subtypeResolver;
        _rootNames = mapperConfigBase._rootNames;
        _rootName = mapperConfigBase._rootName;
        _view = mapperConfigBase._view;
        _attributes = mapperConfigBase._attributes;
        _configOverrides = mapperConfigBase._configOverrides;
    }
    protected MapperConfigBase(final MapperConfigBase<CFG, T> mapperConfigBase, final PropertyName propertyName) {
        super(mapperConfigBase);
        _mixIns = mapperConfigBase._mixIns;
        _subtypeResolver = mapperConfigBase._subtypeResolver;
        _rootNames = mapperConfigBase._rootNames;
        _rootName = propertyName;
        _view = mapperConfigBase._view;
        _attributes = mapperConfigBase._attributes;
        _configOverrides = mapperConfigBase._configOverrides;
    }
    protected MapperConfigBase(final MapperConfigBase<CFG, T> mapperConfigBase, final Class<?> cls) {
        super(mapperConfigBase);
        _mixIns = mapperConfigBase._mixIns;
        _subtypeResolver = mapperConfigBase._subtypeResolver;
        _rootNames = mapperConfigBase._rootNames;
        _rootName = mapperConfigBase._rootName;
        _view = cls;
        _attributes = mapperConfigBase._attributes;
        _configOverrides = mapperConfigBase._configOverrides;
    }
    protected MapperConfigBase(final MapperConfigBase<CFG, T> mapperConfigBase, final SimpleMixInResolver simpleMixInResolver) {
        super(mapperConfigBase);
        _mixIns = simpleMixInResolver;
        _subtypeResolver = mapperConfigBase._subtypeResolver;
        _rootNames = mapperConfigBase._rootNames;
        _rootName = mapperConfigBase._rootName;
        _view = mapperConfigBase._view;
        _attributes = mapperConfigBase._attributes;
        _configOverrides = mapperConfigBase._configOverrides;
    }
    protected MapperConfigBase(final MapperConfigBase<CFG, T> mapperConfigBase, final ContextAttributes contextAttributes) {
        super(mapperConfigBase);
        _mixIns = mapperConfigBase._mixIns;
        _subtypeResolver = mapperConfigBase._subtypeResolver;
        _rootNames = mapperConfigBase._rootNames;
        _rootName = mapperConfigBase._rootName;
        _view = mapperConfigBase._view;
        _attributes = contextAttributes;
        _configOverrides = mapperConfigBase._configOverrides;
    }
    public final T with(final MapperFeature... mapperFeatureArr) {
        int mask = _mapperFeatures;
        for (final MapperFeature mapperFeature : mapperFeatureArr) {
            mask |= mapperFeature.getMask();
        }
        return mask == _mapperFeatures ? (T) this : this._withMapperFeatures(mask);
    }
    public final T without(final MapperFeature... mapperFeatureArr) {
        int i2 = _mapperFeatures;
        for (final MapperFeature mapperFeature : mapperFeatureArr) {
            i2 &= ~mapperFeature.getMask();
        }
        return i2 == _mapperFeatures ? (T) this : this._withMapperFeatures(i2);
    }
    public final T with(final MapperFeature mapperFeature, final boolean z) {
        final int mask;
        if (z) {
            mask = mapperFeature.getMask() | _mapperFeatures;
        } else {
            mask = (~mapperFeature.getMask()) & _mapperFeatures;
        }
        return mask == _mapperFeatures ? (T) this : this._withMapperFeatures(mask);
    }
    public final T with(final AnnotationIntrospector annotationIntrospector) {
        return this._withBase(_base.withAnnotationIntrospector(annotationIntrospector));
    }
    public final T withAppendedAnnotationIntrospector(final AnnotationIntrospector annotationIntrospector) {
        return this._withBase(_base.withAppendedAnnotationIntrospector(annotationIntrospector));
    }
    public final T withInsertedAnnotationIntrospector(final AnnotationIntrospector annotationIntrospector) {
        return this._withBase(_base.withInsertedAnnotationIntrospector(annotationIntrospector));
    }
    public final T with(final ClassIntrospector classIntrospector) {
        return this._withBase(_base.withClassIntrospector(classIntrospector));
    }
    public T withAttributes(final Map<?, ?> map) {
        return this.with(this._attributes.withSharedAttributes(map));
    }
    public T withAttribute(final Object obj, final Object obj2) {
        return this.with(this._attributes.withSharedAttribute(obj, obj2));
    }
    public T withoutAttribute(final Object obj) {
        return this.with(this._attributes.withoutSharedAttribute(obj));
    }
    public final T with(final TypeFactory typeFactory) {
        return this._withBase(_base.withTypeFactory(typeFactory));
    }
    public final T with(final TypeResolverBuilder<?> typeResolverBuilder) {
        return this._withBase(_base.withTypeResolverBuilder(typeResolverBuilder));
    }
    public final T with(final PropertyNamingStrategy propertyNamingStrategy) {
        return this._withBase(_base.withPropertyNamingStrategy(propertyNamingStrategy));
    }
    public final T with(final AccessorNamingStrategy.Provider provider) {
        return this._withBase(_base.withAccessorNaming(provider));
    }
    public final T with(final HandlerInstantiator handlerInstantiator) {
        return this._withBase(_base.withHandlerInstantiator(handlerInstantiator));
    }
    public final T with(final Base64Variant base64Variant) {
        return this._withBase(_base.with(base64Variant));
    }
    public T with(final DateFormat dateFormat) {
        return this._withBase(_base.withDateFormat(dateFormat));
    }
    public final T with(final Locale locale) {
        return this._withBase(_base.with(locale));
    }
    public final T with(final TimeZone timeZone) {
        return this._withBase(_base.with(timeZone));
    }
    public T withRootName(final String str) {
        if (null == str) {
            return this.withRootName((PropertyName) null);
        }
        return this.withRootName(PropertyName.construct(str));
    }
    public final SubtypeResolver getSubtypeResolver() {
        return _subtypeResolver;
    }
    public final String getRootName() {
        final PropertyName propertyName = _rootName;
        if (null == propertyName) {
            return null;
        }
        return propertyName.getSimpleName();
    }
    public final PropertyName getFullRootName() {
        return _rootName;
    }
    public final Class<?> getActiveView() {
        return _view;
    }
    public final ContextAttributes getAttributes() {
        return _attributes;
    }
    public final ConfigOverride getConfigOverride(final Class<?> cls) {
        final ConfigOverride configOverrideFindOverride = _configOverrides.findOverride(cls);
        return null == configOverrideFindOverride ? MapperConfigBase.EMPTY_OVERRIDE : configOverrideFindOverride;
    }
    public final ConfigOverride findConfigOverride(final Class<?> cls) {
        return _configOverrides.findOverride(cls);
    }
    public final JsonInclude.Value getDefaultPropertyInclusion() {
        return _configOverrides.getDefaultInclusion();
    }
    public final JsonInclude.Value getDefaultPropertyInclusion(final Class<?> cls) {
        final JsonInclude.Value include = this.getConfigOverride(cls).getInclude();
        final JsonInclude.Value defaultPropertyInclusion = this.getDefaultPropertyInclusion();
        return null == defaultPropertyInclusion ? include : defaultPropertyInclusion.withOverrides(include);
    }
    public final JsonInclude.Value getDefaultInclusion(final Class<?> cls, final Class<?> cls2) {
        final JsonInclude.Value includeAsProperty = this.getConfigOverride(cls2).getIncludeAsProperty();
        final JsonInclude.Value defaultPropertyInclusion = this.getDefaultPropertyInclusion(cls);
        return null == defaultPropertyInclusion ? includeAsProperty : defaultPropertyInclusion.withOverrides(includeAsProperty);
    }
    public final JsonFormat.Value getDefaultPropertyFormat(final Class<?> cls) {
        return _configOverrides.findFormatDefaults(cls);
    }
    public final JsonIgnoreProperties.Value getDefaultPropertyIgnorals(final Class<?> cls) {
        final JsonIgnoreProperties.Value ignorals;
        final ConfigOverride configOverrideFindOverride = _configOverrides.findOverride(cls);
        if (null == configOverrideFindOverride || null == (ignorals = configOverrideFindOverride.getIgnorals())) {
            return null;
        }
        return ignorals;
    }
    public final JsonIgnoreProperties.Value getDefaultPropertyIgnorals(final Class<?> cls, final AnnotatedClass annotatedClass) {
        final AnnotationIntrospector annotationIntrospector = this.getAnnotationIntrospector();
        return JsonIgnoreProperties.Value.merge(null == annotationIntrospector ? null : annotationIntrospector.findPropertyIgnoralByName(this, annotatedClass), this.getDefaultPropertyIgnorals(cls));
    }
    public final JsonIncludeProperties.Value getDefaultPropertyInclusions(final Class<?> cls, final AnnotatedClass annotatedClass) {
        final AnnotationIntrospector annotationIntrospector = this.getAnnotationIntrospector();
        if (null == annotationIntrospector) {
            return null;
        }
        return annotationIntrospector.findPropertyInclusionByName(this, annotatedClass);
    }
    public final VisibilityChecker<?> getDefaultVisibilityChecker() {
        VisibilityChecker<?> defaultVisibility = _configOverrides.getDefaultVisibility();
        final int i2 = _mapperFeatures;
        final int i3 = MapperConfigBase.AUTO_DETECT_MASK;
        if ((i2 & i3) == i3) {
            return defaultVisibility;
        }
        if (!this.isEnabled(MapperFeature.AUTO_DETECT_FIELDS)) {
            defaultVisibility = defaultVisibility.withFieldVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!this.isEnabled(MapperFeature.AUTO_DETECT_GETTERS)) {
            defaultVisibility = defaultVisibility.withGetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!this.isEnabled(MapperFeature.AUTO_DETECT_IS_GETTERS)) {
            defaultVisibility = defaultVisibility.withIsGetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!this.isEnabled(MapperFeature.AUTO_DETECT_SETTERS)) {
            defaultVisibility = defaultVisibility.withSetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        return !this.isEnabled(MapperFeature.AUTO_DETECT_CREATORS) ? defaultVisibility.withCreatorVisibility(JsonAutoDetect.Visibility.NONE) : defaultVisibility;
   }
    public final VisibilityChecker<?> getDefaultVisibilityChecker(final Class<?> cls, final AnnotatedClass annotatedClass) {
        VisibilityChecker<?> defaultVisibilityChecker = this.getDefaultVisibilityChecker();
        final AnnotationIntrospector annotationIntrospector = this.getAnnotationIntrospector();
        if (null != annotationIntrospector) {
            defaultVisibilityChecker = annotationIntrospector.findAutoDetectVisibility(annotatedClass, defaultVisibilityChecker);
        }
        final ConfigOverride configOverrideFindOverride = _configOverrides.findOverride(cls);
        return null != configOverrideFindOverride ? defaultVisibilityChecker.withOverrides(configOverrideFindOverride.getVisibility()) : defaultVisibilityChecker;
    }
    public final JsonSetter.Value getDefaultSetterInfo() {
        return _configOverrides.getDefaultSetterInfo();
    }
    public Boolean getDefaultMergeable() {
        return _configOverrides.getDefaultMergeable();
    }
    public Boolean getDefaultMergeable(final Class<?> cls) {
        final Boolean mergeable;
        final ConfigOverride configOverrideFindOverride = _configOverrides.findOverride(cls);
        return (null == configOverrideFindOverride || null == (mergeable = configOverrideFindOverride.getMergeable())) ? _configOverrides.getDefaultMergeable() : mergeable;
    }
    public PropertyName findRootName(final JavaType javaType) {
        final PropertyName propertyName = _rootName;
        return null != propertyName ? propertyName : _rootNames.findRootName(javaType, this);
    }
    public PropertyName findRootName(final Class<?> cls) {
        final PropertyName propertyName = _rootName;
        return null != propertyName ? propertyName : _rootNames.findRootName(cls, this);
    }
    public final Class<?> findMixInClassFor(final Class cls) {
        return _mixIns.findMixInClassFor(cls);
    }
    public ClassIntrospector.MixInResolver copy() {
        throw new UnsupportedOperationException();
    }
    public final int mixInCount() {
        return _mixIns.localSize();
    }
    public abstract DeserializationConfig withDefaultPrettyPrinter(PrettyPrinter prettyPrinter);
    protected SerializationConfig _withoutJsonWriteFeatures(FormatFeature[] formatFeatureArr) {
        return null;
    }
}
