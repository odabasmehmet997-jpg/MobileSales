package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionConfigs;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.cfg.ConfigOverrides;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import io.reactivex.Observable;

import java.util.Collection;

public final class DeserializationConfig extends MapperConfigBase<DeserializationFeature, DeserializationConfig> {
    private static final int DESER_FEATURE_DEFAULTS = collectFeatureDefaults(DeserializationFeature.class);
    private static final long serialVersionUID = 2;
    private final CoercionConfigs _coercionConfigs;
    private final ConstructorDetector _ctorDetector;
    private final int _deserFeatures;
    private final int _formatReadFeatures;
    private final int _formatReadFeaturesToChange;
    private final JsonNodeFactory _nodeFactory;
    private final int _parserFeatures;
    private final int _parserFeaturesToChange;
    private final LinkedNode<DeserializationProblemHandler> _problemHandlers;
    private Observable<Object> r0;
    public DeserializationConfig(final BaseSettings baseSettings, final SubtypeResolver subtypeResolver, final SimpleMixInResolver simpleMixInResolver, final RootNameLookup rootNameLookup, final ConfigOverrides configOverrides, final CoercionConfigs coercionConfigs) {
        super(baseSettings, subtypeResolver, simpleMixInResolver, rootNameLookup, configOverrides);
        _deserFeatures = DeserializationConfig.DESER_FEATURE_DEFAULTS;
        _problemHandlers = null;
        _nodeFactory = JsonNodeFactory.instance;
        _ctorDetector = null;
        _coercionConfigs = coercionConfigs;
        _parserFeatures = 0;
        _parserFeaturesToChange = 0;
        _formatReadFeatures = 0;
        _formatReadFeaturesToChange = 0;
    }
    DeserializationConfig(final DeserializationConfig deserializationConfig, final SubtypeResolver subtypeResolver, final SimpleMixInResolver simpleMixInResolver, final RootNameLookup rootNameLookup, final ConfigOverrides configOverrides, final CoercionConfigs coercionConfigs) {
        super(deserializationConfig, subtypeResolver, simpleMixInResolver, rootNameLookup, configOverrides);
        _deserFeatures = deserializationConfig._deserFeatures;
        _problemHandlers = deserializationConfig._problemHandlers;
        _nodeFactory = deserializationConfig._nodeFactory;
        _ctorDetector = deserializationConfig._ctorDetector;
        _coercionConfigs = coercionConfigs;
        _parserFeatures = deserializationConfig._parserFeatures;
        _parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        _formatReadFeatures = deserializationConfig._formatReadFeatures;
        _formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }
    public DeserializationConfig(final DeserializationConfig baseSettings, final SubtypeResolver subtypeResolver, final SimpleMixInResolver simpleMixInResolver, final ConfigOverrides rootNameLookup, final CoercionConfigs configOverrides) {
        this(baseSettings, subtypeResolver, simpleMixInResolver, rootNameLookup, configOverrides, new CoercionConfigs());
    }

    public DeserializationConfig(DeserializationConfig deserializationConfig, SubtypeResolver subtypeResolver, SimpleMixInResolver mixIns, void aVoid, CoercionConfigs coercionConfigs) {
        super(deserializationConfig, subtypeResolver, mixIns, aVoid, coercionConfigs);
    }

    public DeserializationConfig withDefaultPrettyPrinter(PrettyPrinter prettyPrinter) {
        return new DeserializationConfig(this, _subtypeResolver, _mixIns, _configOverrides.setDefaultSetterInfo((JsonSetter.Value) prettyPrinter), _coercionConfigs);
    }
    public DeserializationConfig withCoercionConfigs(CoercionConfigs coercionConfigs) {
        return new DeserializationConfig(this, _subtypeResolver, _mixIns, _configOverrides, coercionConfigs);
    }

    private DeserializationConfig(final DeserializationConfig deserializationConfig, final SimpleMixInResolver simpleMixInResolver, final RootNameLookup rootNameLookup, final ConfigOverrides configOverrides, final CoercionConfigs coercionConfigs) {
        this(deserializationConfig, deserializationConfig._subtypeResolver, simpleMixInResolver, rootNameLookup, configOverrides, coercionConfigs);
    }
    private DeserializationConfig(final DeserializationConfig deserializationConfig, final int i2, final int i3, final int i4, final int i5, final int i6, final int i7) {
        super(deserializationConfig, i2);
        _deserFeatures = i3;
        _problemHandlers = deserializationConfig._problemHandlers;
        _nodeFactory = deserializationConfig._nodeFactory;
        _coercionConfigs = deserializationConfig._coercionConfigs;
        _ctorDetector = deserializationConfig._ctorDetector;
        _parserFeatures = i4;
        _parserFeaturesToChange = i5;
        _formatReadFeatures = i6;
        _formatReadFeaturesToChange = i7;
    }
    private DeserializationConfig(final DeserializationConfig deserializationConfig, final SubtypeResolver subtypeResolver) {
        super(deserializationConfig, subtypeResolver);
        _deserFeatures = deserializationConfig._deserFeatures;
        _problemHandlers = deserializationConfig._problemHandlers;
        _nodeFactory = deserializationConfig._nodeFactory;
        _coercionConfigs = deserializationConfig._coercionConfigs;
        _ctorDetector = deserializationConfig._ctorDetector;
        _parserFeatures = deserializationConfig._parserFeatures;
        _parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        _formatReadFeatures = deserializationConfig._formatReadFeatures;
        _formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }
    private DeserializationConfig(final DeserializationConfig deserializationConfig, final BaseSettings baseSettings) {
        super(deserializationConfig, baseSettings);
        _deserFeatures = deserializationConfig._deserFeatures;
        _problemHandlers = deserializationConfig._problemHandlers;
        _nodeFactory = deserializationConfig._nodeFactory;
        _coercionConfigs = deserializationConfig._coercionConfigs;
        _ctorDetector = deserializationConfig._ctorDetector;
        _parserFeatures = deserializationConfig._parserFeatures;
        _parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        _formatReadFeatures = deserializationConfig._formatReadFeatures;
        _formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }
    private DeserializationConfig(final DeserializationConfig deserializationConfig, final JsonNodeFactory jsonNodeFactory) {
        super(deserializationConfig);
        _deserFeatures = deserializationConfig._deserFeatures;
        _problemHandlers = deserializationConfig._problemHandlers;
        _nodeFactory = jsonNodeFactory;
        _coercionConfigs = deserializationConfig._coercionConfigs;
        _ctorDetector = deserializationConfig._ctorDetector;
        _parserFeatures = deserializationConfig._parserFeatures;
        _parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        _formatReadFeatures = deserializationConfig._formatReadFeatures;
        _formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }
    private DeserializationConfig(final DeserializationConfig deserializationConfig, final ConstructorDetector constructorDetector) {
        super(deserializationConfig);
        _deserFeatures = deserializationConfig._deserFeatures;
        _problemHandlers = deserializationConfig._problemHandlers;
        _nodeFactory = deserializationConfig._nodeFactory;
        _coercionConfigs = deserializationConfig._coercionConfigs;
        _ctorDetector = constructorDetector;
        _parserFeatures = deserializationConfig._parserFeatures;
        _parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        _formatReadFeatures = deserializationConfig._formatReadFeatures;
        _formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }
    private DeserializationConfig(final DeserializationConfig deserializationConfig, final LinkedNode<DeserializationProblemHandler> linkedNode) {
        super(deserializationConfig);
        _deserFeatures = deserializationConfig._deserFeatures;
        _problemHandlers = linkedNode;
        _nodeFactory = deserializationConfig._nodeFactory;
        _coercionConfigs = deserializationConfig._coercionConfigs;
        _ctorDetector = deserializationConfig._ctorDetector;
        _parserFeatures = deserializationConfig._parserFeatures;
        _parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        _formatReadFeatures = deserializationConfig._formatReadFeatures;
        _formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }
    private DeserializationConfig(final DeserializationConfig deserializationConfig, final PropertyName propertyName) {
        super(deserializationConfig, propertyName);
        _deserFeatures = deserializationConfig._deserFeatures;
        _problemHandlers = deserializationConfig._problemHandlers;
        _nodeFactory = deserializationConfig._nodeFactory;
        _coercionConfigs = deserializationConfig._coercionConfigs;
        _ctorDetector = deserializationConfig._ctorDetector;
        _parserFeatures = deserializationConfig._parserFeatures;
        _parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        _formatReadFeatures = deserializationConfig._formatReadFeatures;
        _formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }
    private DeserializationConfig(final DeserializationConfig deserializationConfig, final Class<?> cls) {
        super(deserializationConfig, cls);
        _deserFeatures = deserializationConfig._deserFeatures;
        _problemHandlers = deserializationConfig._problemHandlers;
        _nodeFactory = deserializationConfig._nodeFactory;
        _coercionConfigs = deserializationConfig._coercionConfigs;
        _ctorDetector = deserializationConfig._ctorDetector;
        _parserFeatures = deserializationConfig._parserFeatures;
        _parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        _formatReadFeatures = deserializationConfig._formatReadFeatures;
        _formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }
    private DeserializationConfig(final DeserializationConfig deserializationConfig, final ContextAttributes contextAttributes) {
        super(deserializationConfig, contextAttributes);
        _deserFeatures = deserializationConfig._deserFeatures;
        _problemHandlers = deserializationConfig._problemHandlers;
        _nodeFactory = deserializationConfig._nodeFactory;
        _coercionConfigs = deserializationConfig._coercionConfigs;
        _ctorDetector = deserializationConfig._ctorDetector;
        _parserFeatures = deserializationConfig._parserFeatures;
        _parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        _formatReadFeatures = deserializationConfig._formatReadFeatures;
        _formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }
    DeserializationConfig(final DeserializationConfig deserializationConfig, final SimpleMixInResolver simpleMixInResolver) {
        super(deserializationConfig, simpleMixInResolver);
        _deserFeatures = deserializationConfig._deserFeatures;
        _problemHandlers = deserializationConfig._problemHandlers;
        _nodeFactory = deserializationConfig._nodeFactory;
        _coercionConfigs = deserializationConfig._coercionConfigs;
        _ctorDetector = deserializationConfig._ctorDetector;
        _parserFeatures = deserializationConfig._parserFeatures;
        _parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        _formatReadFeatures = deserializationConfig._formatReadFeatures;
        _formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }
    BaseSettings getBaseSettings() {
        return _base;
    }
    protected DeserializationConfig _withBase(final BaseSettings baseSettings) {
        return _base == baseSettings ? this : new DeserializationConfig(this, baseSettings);
    }
    protected DeserializationConfig _withMapperFeatures(final int i2) {
        return new DeserializationConfig(this, i2, _deserFeatures, _parserFeatures, _parserFeaturesToChange, _formatReadFeatures, _formatReadFeaturesToChange);
    }
    public DeserializationConfig with(final SubtypeResolver subtypeResolver) {
        return _subtypeResolver == subtypeResolver ? this : new DeserializationConfig(this, subtypeResolver);
    }
    public DeserializationConfig withRootName(final PropertyName propertyName) {
        if (null == propertyName) {
            if (null == this._rootName) {
                return this;
            }
        } else if (propertyName.equals(_rootName)) {
            return this;
        }
        return new DeserializationConfig(this, propertyName);
    }
    public DeserializationConfig withView(final Class<?> cls) {
        return _view == cls ? this : new DeserializationConfig(this, cls);
    }
    public DeserializationConfig with(final ContextAttributes contextAttributes) {
        return contextAttributes == _attributes ? this : new DeserializationConfig(this, contextAttributes);
    }
    public DeserializationConfig with(final DeserializationFeature deserializationFeature) {
        final int mask = _deserFeatures | deserializationFeature.getMask();
        return mask == _deserFeatures ? this : new DeserializationConfig(this, _mapperFeatures, mask, _parserFeatures, _parserFeaturesToChange, _formatReadFeatures, _formatReadFeaturesToChange);
    }
    public DeserializationConfig with(final DeserializationFeature deserializationFeature, final DeserializationFeature... deserializationFeatureArr) {
        int mask = deserializationFeature.getMask() | _deserFeatures;
        for (final DeserializationFeature deserializationFeature2 : deserializationFeatureArr) {
            mask |= deserializationFeature2.getMask();
        }
        return mask == _deserFeatures ? this : new DeserializationConfig(this, _mapperFeatures, mask, _parserFeatures, _parserFeaturesToChange, _formatReadFeatures, _formatReadFeaturesToChange);
    }
    public DeserializationConfig withFeatures(final DeserializationFeature... deserializationFeatureArr) {
        int mask = _deserFeatures;
        for (final DeserializationFeature deserializationFeature : deserializationFeatureArr) {
            mask |= deserializationFeature.getMask();
        }
        return mask == _deserFeatures ? this : new DeserializationConfig(this, _mapperFeatures, mask, _parserFeatures, _parserFeaturesToChange, _formatReadFeatures, _formatReadFeaturesToChange);
    }
    public DeserializationConfig without(final DeserializationFeature deserializationFeature) {
        final int i2 = _deserFeatures & (~deserializationFeature.getMask());
        return i2 == _deserFeatures ? this : new DeserializationConfig(this, _mapperFeatures, i2, _parserFeatures, _parserFeaturesToChange, _formatReadFeatures, _formatReadFeaturesToChange);
    }
    public DeserializationConfig without(final DeserializationFeature deserializationFeature, final DeserializationFeature... deserializationFeatureArr) {
        int i2 = (~deserializationFeature.getMask()) & _deserFeatures;
        for (final DeserializationFeature deserializationFeature2 : deserializationFeatureArr) {
            i2 &= ~deserializationFeature2.getMask();
        }
        return i2 == _deserFeatures ? this : new DeserializationConfig(this, _mapperFeatures, i2, _parserFeatures, _parserFeaturesToChange, _formatReadFeatures, _formatReadFeaturesToChange);
    }
    public DeserializationConfig withoutFeatures(final DeserializationFeature... deserializationFeatureArr) {
        int i2 = _deserFeatures;
        for (final DeserializationFeature deserializationFeature : deserializationFeatureArr) {
            i2 &= ~deserializationFeature.getMask();
        }
        return i2 == _deserFeatures ? this : new DeserializationConfig(this, _mapperFeatures, i2, _parserFeatures, _parserFeaturesToChange, _formatReadFeatures, _formatReadFeaturesToChange);
    }
    public DeserializationConfig with(final JsonParser.Feature feature) {
        final int mask = _parserFeatures | feature.getMask();
        final int mask2 = _parserFeaturesToChange | feature.getMask();
        return (_parserFeatures == mask && _parserFeaturesToChange == mask2) ? this : new DeserializationConfig(this, _mapperFeatures, _deserFeatures, mask, mask2, _formatReadFeatures, _formatReadFeaturesToChange);
    }
    public DeserializationConfig withFeatures(final JsonParser.Feature... featureArr) {
        final int i2 = _parserFeatures;
        int i3 = i2;
        int i4 = _parserFeaturesToChange;
        for (final JsonParser.Feature feature : featureArr) {
            final int mask = feature.getMask();
            i3 |= mask;
            i4 |= mask;
        }
        return (_parserFeatures == i3 && _parserFeaturesToChange == i4) ? this : new DeserializationConfig(this, _mapperFeatures, _deserFeatures, i3, i4, _formatReadFeatures, _formatReadFeaturesToChange);
    }
    public DeserializationConfig without(final JsonParser.Feature feature) {
        final int i2 = _parserFeatures & (~feature.getMask());
        final int mask = _parserFeaturesToChange | feature.getMask();
        return (_parserFeatures == i2 && _parserFeaturesToChange == mask) ? this : new DeserializationConfig(this, _mapperFeatures, _deserFeatures, i2, mask, _formatReadFeatures, _formatReadFeaturesToChange);
    }
    public DeserializationConfig withoutFeatures(final JsonParser.Feature... featureArr) {
        final int i2 = _parserFeatures;
        int i3 = i2;
        int i4 = _parserFeaturesToChange;
        for (final JsonParser.Feature feature : featureArr) {
            final int mask = feature.getMask();
            i3 &= ~mask;
            i4 |= mask;
        }
        return (_parserFeatures == i3 && _parserFeaturesToChange == i4) ? this : new DeserializationConfig(this, _mapperFeatures, _deserFeatures, i3, i4, _formatReadFeatures, _formatReadFeaturesToChange);
    }
    public DeserializationConfig with(final FormatFeature formatFeature) {
        if (formatFeature instanceof JsonReadFeature) {
            return this._withJsonReadFeatures(formatFeature);
        }
        final int mask = _formatReadFeatures | formatFeature.getMask();
        final int mask2 = _formatReadFeaturesToChange | formatFeature.getMask();
        return (_formatReadFeatures == mask && _formatReadFeaturesToChange == mask2) ? this : new DeserializationConfig(this, _mapperFeatures, _deserFeatures, _parserFeatures, _parserFeaturesToChange, mask, mask2);
    }
    public DeserializationConfig withFeatures(final FormatFeature... formatFeatureArr) {
        if (0 < formatFeatureArr.length && (formatFeatureArr[0] instanceof JsonReadFeature)) {
            return this._withJsonReadFeatures(formatFeatureArr);
        }
        final int i2 = _formatReadFeatures;
        int i3 = i2;
        int i4 = _formatReadFeaturesToChange;
        for (final FormatFeature formatFeature : formatFeatureArr) {
            final int mask = formatFeature.getMask();
            i3 |= mask;
            i4 |= mask;
        }
        return (_formatReadFeatures == i3 && _formatReadFeaturesToChange == i4) ? this : new DeserializationConfig(this, _mapperFeatures, _deserFeatures, _parserFeatures, _parserFeaturesToChange, i3, i4);
    }
    public DeserializationConfig without(final FormatFeature formatFeature) {
        if (formatFeature instanceof JsonReadFeature) {
            return this._withoutJsonReadFeatures(formatFeature);
        }
        final int i2 = _formatReadFeatures & (~formatFeature.getMask());
        final int mask = _formatReadFeaturesToChange | formatFeature.getMask();
        return (_formatReadFeatures == i2 && _formatReadFeaturesToChange == mask) ? this : new DeserializationConfig(this, _mapperFeatures, _deserFeatures, _parserFeatures, _parserFeaturesToChange, i2, mask);
    }
    public DeserializationConfig withoutFeatures(final FormatFeature... formatFeatureArr) {
        if (0 < formatFeatureArr.length && (formatFeatureArr[0] instanceof JsonReadFeature)) {
            return this._withoutJsonReadFeatures(formatFeatureArr);
        }
        final int i2 = _formatReadFeatures;
        int i3 = i2;
        int i4 = _formatReadFeaturesToChange;
        for (final FormatFeature formatFeature : formatFeatureArr) {
            final int mask = formatFeature.getMask();
            i3 &= ~mask;
            i4 |= mask;
        }
        return (_formatReadFeatures == i3 && _formatReadFeaturesToChange == i4) ? this : new DeserializationConfig(this, _mapperFeatures, _deserFeatures, _parserFeatures, _parserFeaturesToChange, i3, i4);
    }
    private DeserializationConfig _withJsonReadFeatures(final FormatFeature... formatFeatureArr) {
        JsonParser.Feature featureMappedFeature;
        final int i2 = _parserFeatures;
        final int i3 = _parserFeaturesToChange;
        final int i4 = _formatReadFeatures;
        int i5 = i2;
        int i6 = i3;
        int i7 = i4;
        int i8 = _formatReadFeaturesToChange;
        for (final FormatFeature formatFeature : formatFeatureArr) {
            final int mask = formatFeature.getMask();
            i7 |= mask;
            i8 |= mask;
            if ((formatFeature instanceof JsonReadFeature) && null != (featureMappedFeature = ((JsonReadFeature) formatFeature).mappedFeature())) {
                final int mask2 = featureMappedFeature.getMask();
                i6 = mask2 | i6;
                i5 |= mask2;
            }
        }
        return (_formatReadFeatures == i7 && _formatReadFeaturesToChange == i8 && _parserFeatures == i5 && _parserFeaturesToChange == i6) ? this : new DeserializationConfig(this, _mapperFeatures, _deserFeatures, i5, i6, i7, i8);
    }
    private DeserializationConfig _withoutJsonReadFeatures(final FormatFeature... formatFeatureArr) {
        JsonParser.Feature featureMappedFeature;
        final int i2 = _parserFeatures;
        final int i3 = _parserFeaturesToChange;
        final int i4 = _formatReadFeatures;
        int i5 = i2;
        int i6 = i3;
        int i7 = i4;
        int i8 = _formatReadFeaturesToChange;
        for (final FormatFeature formatFeature : formatFeatureArr) {
            final int mask = formatFeature.getMask();
            i7 &= ~mask;
            i8 |= mask;
            if ((formatFeature instanceof JsonReadFeature) && null != (featureMappedFeature = ((JsonReadFeature) formatFeature).mappedFeature())) {
                final int mask2 = featureMappedFeature.getMask();
                i6 = mask2 | i6;
                i5 = (~mask2) & i5;
            }
        }
        return (_formatReadFeatures == i7 && _formatReadFeaturesToChange == i8 && _parserFeatures == i5 && _parserFeaturesToChange == i6) ? this : new DeserializationConfig(this, _mapperFeatures, _deserFeatures, i5, i6, i7, i8);
    }
    public DeserializationConfig with(final JsonNodeFactory jsonNodeFactory) {
        return _nodeFactory == jsonNodeFactory ? this : new DeserializationConfig(this, jsonNodeFactory);
    }
    public DeserializationConfig with(final ConstructorDetector constructorDetector) {
        return _ctorDetector == constructorDetector ? this : new DeserializationConfig(this, constructorDetector);
    }
    public DeserializationConfig withHandler(final DeserializationProblemHandler deserializationProblemHandler) {
        return LinkedNode.contains(_problemHandlers, deserializationProblemHandler) ? this : new DeserializationConfig(this, (LinkedNode<DeserializationProblemHandler>) new LinkedNode(deserializationProblemHandler, _problemHandlers));
    }
    public DeserializationConfig withNoProblemHandlers() {
        return null == this._problemHandlers ? this : new DeserializationConfig(this, (LinkedNode<DeserializationProblemHandler>) null);
    }
    public JsonParser initialize(final JsonParser jsonParser) {
        final int i2 = _parserFeaturesToChange;
        if (0 != i2) {
            jsonParser.overrideStdFeatures(_parserFeatures, i2);
        }
        final int i3 = _formatReadFeaturesToChange;
        if (0 != i3) {
            jsonParser.overrideFormatFeatures(_formatReadFeatures, i3);
        }
        return jsonParser;
    }
    public JsonParser initialize(final JsonParser jsonParser, final FormatSchema formatSchema) {
        final int i2 = _parserFeaturesToChange;
        if (0 != i2) {
            jsonParser.overrideStdFeatures(_parserFeatures, i2);
        }
        final int i3 = _formatReadFeaturesToChange;
        if (0 != i3) {
            jsonParser.overrideFormatFeatures(_formatReadFeatures, i3);
        }
        if (null != formatSchema) {
            jsonParser.setSchema(formatSchema);
        }
        return jsonParser;
    }
    public boolean useRootWrapping() {
        if (null != this._rootName) {
            return !r0.isEmpty().blockingGet();
        }
        return this.isEnabled(DeserializationFeature.UNWRAP_ROOT_VALUE);
    }
    public boolean isEnabled(final DeserializationFeature deserializationFeature) {
        return 0 != (deserializationFeature.getMask() & this._deserFeatures);
    }
    public boolean isEnabled(final JsonParser.Feature feature, final JsonFactory jsonFactory) {
        if (0 != (feature.getMask() & this._parserFeaturesToChange)) {
            return 0 != (feature.getMask() & this._parserFeatures);
        }
        return jsonFactory.isEnabled(feature);
    }
    public boolean hasDeserializationFeatures(final int i2) {
        return (_deserFeatures & i2) == i2;
    }
    public boolean hasSomeOfFeatures(final int i2) {
        return 0 != (i2 & this._deserFeatures);
    }
    public int getDeserializationFeatures() {
        return _deserFeatures;
    }
    public boolean requiresFullValue() {
        return DeserializationFeature.FAIL_ON_TRAILING_TOKENS.enabledIn(_deserFeatures);
    }
    public LinkedNode<DeserializationProblemHandler> getProblemHandlers() {
        return _problemHandlers;
    }
    public JsonNodeFactory getNodeFactory() {
        return _nodeFactory;
    }
    public ConstructorDetector getConstructorDetector() {
        final ConstructorDetector constructorDetector = _ctorDetector;
        return null == constructorDetector ? ConstructorDetector.DEFAULT : constructorDetector;
    }
    public BeanDescription introspect(final JavaType javaType) {
        return this.getClassIntrospector().forDeserialization(this, javaType, this);
    }
    public BeanDescription introspectForCreation(final JavaType javaType) {
        return this.getClassIntrospector().forCreation(this, javaType, this);
    }
    public BeanDescription introspectForBuilder(final JavaType javaType, final BeanDescription beanDescription) {
        return this.getClassIntrospector().forDeserializationWithBuilder(this, javaType, this, beanDescription);
    }
    public BeanDescription introspectForBuilder(final JavaType javaType) {
        return this.getClassIntrospector().forDeserializationWithBuilder(this, javaType, this);
    }
    public TypeDeserializer findTypeDeserializer(final JavaType javaType) throws JsonMappingException {
        final Collection<NamedType> collectionCollectAndResolveSubtypesByTypeId;
        final AnnotatedClass classInfo = this.introspectClassAnnotations(javaType.getRawClass()).getClassInfo();
        TypeResolverBuilder<?> typeResolverBuilderFindTypeResolver = this.getAnnotationIntrospector().findTypeResolver(this, classInfo, javaType);
        if (null == typeResolverBuilderFindTypeResolver) {
            typeResolverBuilderFindTypeResolver = this.getDefaultTyper(javaType);
            collectionCollectAndResolveSubtypesByTypeId = null;
            if (null == typeResolverBuilderFindTypeResolver) {
                return null;
            }
        } else {
            collectionCollectAndResolveSubtypesByTypeId = this.getSubtypeResolver().collectAndResolveSubtypesByTypeId(this, classInfo);
        }
        return typeResolverBuilderFindTypeResolver.buildTypeDeserializer(this, javaType, collectionCollectAndResolveSubtypesByTypeId);
    }
    public CoercionAction findCoercionAction(final LogicalType logicalType, final Class<?> cls, final CoercionInputShape coercionInputShape) {
        return _coercionConfigs.findCoercion(this, logicalType, cls, coercionInputShape);
    }
    public CoercionAction findCoercionFromBlankString(final LogicalType logicalType, final Class<?> cls, final CoercionAction coercionAction) {
        return _coercionConfigs.findCoercionFromBlankString(this, logicalType, cls, coercionAction);
    }
}
