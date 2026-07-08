package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import com.fasterxml.jackson.databind.deser.impl.ValueInjector;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;

import java.util.*;

public class BeanDeserializerBuilder {
    protected SettableAnyProperty _anySetter;
    protected HashMap<String, SettableBeanProperty> _backRefProperties;
    protected final BeanDescription _beanDesc;
    protected AnnotatedMethod _buildMethod;
    protected JsonPOJOBuilder.Value _builderConfig;
    protected final DeserializationConfig _config;
    protected final DeserializationContext _context;
    protected HashSet<String> _ignorableProps;
    protected boolean _ignoreAllUnknown;
    protected HashSet<String> _includableProps;
    protected List<ValueInjector> _injectables;
    protected ObjectIdReader _objectIdReader;
    protected final Map<String, SettableBeanProperty> _properties = new LinkedHashMap();
    protected ValueInstantiator _valueInstantiator;
    public BeanDeserializerBuilder(final BeanDescription beanDescription, final DeserializationContext deserializationContext) {
        _beanDesc = beanDescription;
        _context = deserializationContext;
        _config = deserializationContext.getConfig();
    }
    public void addOrReplaceProperty(final SettableBeanProperty settableBeanProperty, final boolean z) {
        _properties.put(settableBeanProperty.getName(), settableBeanProperty);
    }
    public void addProperty(final SettableBeanProperty settableBeanProperty) {
        final SettableBeanProperty settableBeanPropertyPut = _properties.put(settableBeanProperty.getName(), settableBeanProperty);
        if (null == settableBeanPropertyPut || settableBeanPropertyPut == settableBeanProperty) {
            return;
        }
        throw new IllegalArgumentException("Duplicate property '" + settableBeanProperty.getName() + "' for " + _beanDesc.getType());
    }
    public void addBackReferenceProperty(final String str, final SettableBeanProperty settableBeanProperty) {
        if (null == this._backRefProperties) {
            _backRefProperties = new HashMap<>(4);
        }
        if (_config.canOverrideAccessModifiers()) {
            settableBeanProperty.fixAccess(_config);
        }
        _backRefProperties.put(str, settableBeanProperty);
    }
    public void addInjectable(final PropertyName propertyName, final JavaType javaType, final Annotations annotations, final AnnotatedMember annotatedMember, final Object obj) {
        if (null == this._injectables) {
            _injectables = new ArrayList();
        }
        if (_config.canOverrideAccessModifiers()) {
            annotatedMember.fixAccess(_config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        _injectables.add(new ValueInjector(propertyName, javaType, annotatedMember, obj));
    }
    public void addIgnorable(final String str) {
        if (null == this._ignorableProps) {
            _ignorableProps = new HashSet<>();
        }
        _ignorableProps.add(str);
    }
    public void addIncludable(final String str) {
        if (null == this._includableProps) {
            _includableProps = new HashSet<>();
        }
        _includableProps.add(str);
    }
    public void addCreatorProperty(final SettableBeanProperty settableBeanProperty) {
        this.addProperty(settableBeanProperty);
    }
    public void setAnySetter(final SettableAnyProperty settableAnyProperty) {
        if (null != this._anySetter && null != settableAnyProperty) {
            throw new IllegalStateException("_anySetter already set to non-null");
        }
        _anySetter = settableAnyProperty;
    }
    public void setIgnoreUnknownProperties(final boolean z) {
        _ignoreAllUnknown = z;
    }
    public void setValueInstantiator(final ValueInstantiator valueInstantiator) {
        _valueInstantiator = valueInstantiator;
    }
    public void setObjectIdReader(final ObjectIdReader objectIdReader) {
        _objectIdReader = objectIdReader;
    }
    public void setPOJOBuilder(final AnnotatedMethod annotatedMethod, final JsonPOJOBuilder.Value value) {
        _buildMethod = annotatedMethod;
        _builderConfig = value;
    }
    public SettableBeanProperty findProperty(final PropertyName propertyName) {
        return _properties.get(propertyName.getSimpleName());
    }
    public SettableAnyProperty getAnySetter() {
        return _anySetter;
    }
    public ValueInstantiator getValueInstantiator() {
        return _valueInstantiator;
    }
    public List<ValueInjector> getInjectables() {
        return _injectables;
    }
    public ObjectIdReader getObjectIdReader() {
        return _objectIdReader;
    }
    public AnnotatedMethod getBuildMethod() {
        return _buildMethod;
    }
    public boolean hasIgnorable(final String str) {
        return IgnorePropertiesUtil.shouldIgnore(str, _ignorableProps, _includableProps);
    }
    public JsonDeserializer<?> build() {
        final Collection<SettableBeanProperty> collectionValues = _properties.values();
        this._fixAccess(collectionValues);
        BeanPropertyMap beanPropertyMapConstruct = BeanPropertyMap.construct(_config, collectionValues, this._collectAliases(collectionValues), this._findCaseInsensitivity());
        beanPropertyMapConstruct.assignIndexes();
        final boolean zIsEnabled = _config.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
        boolean z = !zIsEnabled;
        if (zIsEnabled) {
            final Iterator<SettableBeanProperty> it = collectionValues.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (it.next().hasViews()) {
                    z = true;
                    break;
                }
            }
        }
        final boolean z2 = z;
        if (null != this._objectIdReader) {
            beanPropertyMapConstruct = beanPropertyMapConstruct.withProperty(new ObjectIdValueProperty(_objectIdReader, PropertyMetadata.STD_REQUIRED));
        }
        return new BeanDeserializer(this, _beanDesc, beanPropertyMapConstruct, _backRefProperties, _ignorableProps, _ignoreAllUnknown, _includableProps, z2);
    }
    public AbstractDeserializer buildAbstract() {
        return new AbstractDeserializer(this, _beanDesc, _backRefProperties, _properties);
    }
    public JsonDeserializer<?> buildBuilderBased(final JavaType javaType, final String str) throws JsonMappingException {
        final AnnotatedMethod annotatedMethod = _buildMethod;
        if (null == annotatedMethod) {
            if (!str.isEmpty()) {
                _context.reportBadDefinition(_beanDesc.getType(), String.format("Builder class %s does not have build method (name: '%s')", ClassUtil.getTypeDescription(_beanDesc.getType()), str));
            }
        } else {
            final Class<?> rawReturnType = annotatedMethod.getRawReturnType();
            final Class<?> rawClass = javaType.getRawClass();
            if (rawReturnType != rawClass && !rawReturnType.isAssignableFrom(rawClass) && !rawClass.isAssignableFrom(rawReturnType)) {
                _context.reportBadDefinition(_beanDesc.getType(), String.format("Build method `%s` has wrong return type (%s), not compatible with POJO type (%s)", _buildMethod.getFullName(), ClassUtil.getClassDescription(rawReturnType), ClassUtil.getTypeDescription(javaType)));
            }
        }
        final Collection<SettableBeanProperty> collectionValues = _properties.values();
        this._fixAccess(collectionValues);
        BeanPropertyMap beanPropertyMapConstruct = BeanPropertyMap.construct(_config, collectionValues, this._collectAliases(collectionValues), this._findCaseInsensitivity());
        beanPropertyMapConstruct.assignIndexes();
        final boolean zIsEnabled = _config.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
        boolean z = !zIsEnabled;
        if (zIsEnabled) {
            final Iterator<SettableBeanProperty> it = collectionValues.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (it.next().hasViews()) {
                    z = true;
                    break;
                }
            }
        }
        if (null != this._objectIdReader) {
            beanPropertyMapConstruct = beanPropertyMapConstruct.withProperty(new ObjectIdValueProperty(_objectIdReader, PropertyMetadata.STD_REQUIRED));
        }
        return this.createBuilderBasedDeserializer(javaType, beanPropertyMapConstruct, z);
    }
    protected JsonDeserializer<?> createBuilderBasedDeserializer(final JavaType javaType, final BeanPropertyMap beanPropertyMap, final boolean z) {
        return new BuilderBasedDeserializer(this, _beanDesc, javaType, beanPropertyMap, _backRefProperties, _ignorableProps, _ignoreAllUnknown, _includableProps, z);
    }
    protected void _fixAccess(final Collection<SettableBeanProperty> collection) {
        if (_config.canOverrideAccessModifiers()) {
            final Iterator<SettableBeanProperty> it = collection.iterator();
            while (it.hasNext()) {
                it.next().fixAccess(_config);
            }
        }
        final SettableAnyProperty settableAnyProperty = _anySetter;
        if (null != settableAnyProperty) {
            settableAnyProperty.fixAccess(_config);
        }
        final AnnotatedMethod annotatedMethod = _buildMethod;
        if (null != annotatedMethod) {
            annotatedMethod.fixAccess(_config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
    }
    protected Map<String, List<PropertyName>> _collectAliases(final Collection<SettableBeanProperty> collection) {
        final AnnotationIntrospector annotationIntrospector = _config.getAnnotationIntrospector();
        HashMap map = null;
        if (null != annotationIntrospector) {
            for (final SettableBeanProperty settableBeanProperty : collection) {
                final List<PropertyName> listFindPropertyAliases = annotationIntrospector.findPropertyAliases(settableBeanProperty.getMember());
                if (null != listFindPropertyAliases && !listFindPropertyAliases.isEmpty()) {
                    if (null == map) {
                        map = new HashMap();
                    }
                    map.put(settableBeanProperty.getName(), listFindPropertyAliases);
                }
            }
        }
        return null == map ? Collections.emptyMap() : map;
    }
    protected boolean _findCaseInsensitivity() {
        final Boolean feature = _beanDesc.findExpectedFormat(null).getFeature(JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        if (null == feature) {
            return _config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        }
        return feature.booleanValue();
    }
}
