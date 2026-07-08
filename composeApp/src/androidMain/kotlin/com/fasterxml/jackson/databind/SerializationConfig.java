package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.Instantiatable;
import com.fasterxml.jackson.databind.cfg.*;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import io.reactivex.Observable;
import java.text.DateFormat;

public final class SerializationConfig extends MapperConfigBase<SerializationFeature, SerializationConfig> {
    private static final PrettyPrinter DEFAULT_PRETTY_PRINTER = new DefaultPrettyPrinter();
    private static final int SER_FEATURE_DEFAULTS = MapperConfig.collectFeatureDefaults(SerializationFeature.class);
    private static final long serialVersionUID = 1L;
    private final PrettyPrinter _defaultPrettyPrinter;
    private final FilterProvider _filterProvider;
    private final int _formatWriteFeatures;
    private final int _formatWriteFeaturesToChange;
    private final int _generatorFeatures;
    private final int _generatorFeaturesToChange;
    private final int _serFeatures;
    private final Observable<Object> r0 = null;
    public MapperConfigBase withView(final Class<?> cls) {
        return this.withView(cls);
    }
    public SerializationConfig(final BaseSettings baseSettings, final SubtypeResolver subtypeResolver, final SimpleMixInResolver simpleMixInResolver, final RootNameLookup rootNameLookup, final ConfigOverrides configOverrides) {
        super(baseSettings, subtypeResolver, simpleMixInResolver, rootNameLookup, configOverrides);
        _serFeatures = SerializationConfig.SER_FEATURE_DEFAULTS;
        _filterProvider = null;
        _defaultPrettyPrinter = SerializationConfig.DEFAULT_PRETTY_PRINTER;
        _generatorFeatures = 0;
        _generatorFeaturesToChange = 0;
        _formatWriteFeatures = 0;
        _formatWriteFeaturesToChange = 0;
    }
    SerializationConfig(final SerializationConfig serializationConfig, final SubtypeResolver subtypeResolver, final SimpleMixInResolver simpleMixInResolver, final RootNameLookup rootNameLookup, final ConfigOverrides configOverrides) {
        super(serializationConfig, subtypeResolver, simpleMixInResolver, rootNameLookup, configOverrides);
        _serFeatures = serializationConfig._serFeatures;
        _filterProvider = serializationConfig._filterProvider;
        _defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        _generatorFeatures = serializationConfig._generatorFeatures;
        _generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        _formatWriteFeatures = serializationConfig._formatWriteFeatures;
        _formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }
    private SerializationConfig(final SerializationConfig serializationConfig, final SimpleMixInResolver simpleMixInResolver, final RootNameLookup rootNameLookup, final ConfigOverrides configOverrides) {
        this(serializationConfig, serializationConfig._subtypeResolver, simpleMixInResolver, rootNameLookup, configOverrides);
    }
    private SerializationConfig(final SerializationConfig serializationConfig, final SubtypeResolver subtypeResolver) {
        super(serializationConfig, subtypeResolver);
        _serFeatures = serializationConfig._serFeatures;
        _filterProvider = serializationConfig._filterProvider;
        _defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        _generatorFeatures = serializationConfig._generatorFeatures;
        _generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        _formatWriteFeatures = serializationConfig._formatWriteFeatures;
        _formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }
    private SerializationConfig(final SerializationConfig serializationConfig, final int i2, final int i3, final int i4, final int i5, final int i6, final int i7) {
        super(serializationConfig, i2);
        _serFeatures = i3;
        _filterProvider = serializationConfig._filterProvider;
        _defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        _generatorFeatures = i4;
        _generatorFeaturesToChange = i5;
        _formatWriteFeatures = i6;
        _formatWriteFeaturesToChange = i7;
    }
    private SerializationConfig(final SerializationConfig serializationConfig, final BaseSettings baseSettings) {
        super(serializationConfig, baseSettings);
        _serFeatures = serializationConfig._serFeatures;
        _filterProvider = serializationConfig._filterProvider;
        _defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        _generatorFeatures = serializationConfig._generatorFeatures;
        _generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        _formatWriteFeatures = serializationConfig._formatWriteFeatures;
        _formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }
    private SerializationConfig(final SerializationConfig serializationConfig, final FilterProvider filterProvider) {
        super(serializationConfig);
        _serFeatures = serializationConfig._serFeatures;
        _filterProvider = filterProvider;
        _defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        _generatorFeatures = serializationConfig._generatorFeatures;
        _generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        _formatWriteFeatures = serializationConfig._formatWriteFeatures;
        _formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }
    private SerializationConfig(final SerializationConfig serializationConfig, final Class<?> cls) {
        super(serializationConfig, cls);
        _serFeatures = serializationConfig._serFeatures;
        _filterProvider = serializationConfig._filterProvider;
        _defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        _generatorFeatures = serializationConfig._generatorFeatures;
        _generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        _formatWriteFeatures = serializationConfig._formatWriteFeatures;
        _formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }
    private SerializationConfig(final SerializationConfig serializationConfig, final PropertyName propertyName) {
        super(serializationConfig, propertyName);
        _serFeatures = serializationConfig._serFeatures;
        _filterProvider = serializationConfig._filterProvider;
        _defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        _generatorFeatures = serializationConfig._generatorFeatures;
        _generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        _formatWriteFeatures = serializationConfig._formatWriteFeatures;
        _formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }
    private SerializationConfig(final SerializationConfig serializationConfig, final ContextAttributes contextAttributes) {
        super(serializationConfig, contextAttributes);
        _serFeatures = serializationConfig._serFeatures;
        _filterProvider = serializationConfig._filterProvider;
        _defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        _generatorFeatures = serializationConfig._generatorFeatures;
        _generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        _formatWriteFeatures = serializationConfig._formatWriteFeatures;
        _formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }
    SerializationConfig(final SerializationConfig serializationConfig, final SimpleMixInResolver simpleMixInResolver) {
        super(serializationConfig, simpleMixInResolver);
        _serFeatures = serializationConfig._serFeatures;
        _filterProvider = serializationConfig._filterProvider;
        _defaultPrettyPrinter = serializationConfig._defaultPrettyPrinter;
        _generatorFeatures = serializationConfig._generatorFeatures;
        _generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        _formatWriteFeatures = serializationConfig._formatWriteFeatures;
        _formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }
    private SerializationConfig(final SerializationConfig serializationConfig, final PrettyPrinter prettyPrinter) {
        super(serializationConfig);
        _serFeatures = serializationConfig._serFeatures;
        _filterProvider = serializationConfig._filterProvider;
        _defaultPrettyPrinter = prettyPrinter;
        _generatorFeatures = serializationConfig._generatorFeatures;
        _generatorFeaturesToChange = serializationConfig._generatorFeaturesToChange;
        _formatWriteFeatures = serializationConfig._formatWriteFeatures;
        _formatWriteFeaturesToChange = serializationConfig._formatWriteFeaturesToChange;
    }
    protected SerializationConfig _withBase(final BaseSettings baseSettings) {
        return _base == baseSettings ? this : new SerializationConfig(this, baseSettings);
    }
    protected SerializationConfig _withMapperFeatures(final int i2) {
        return new SerializationConfig(this, i2, _serFeatures, _generatorFeatures, _generatorFeaturesToChange, _formatWriteFeatures, _formatWriteFeaturesToChange);
    }
    public SerializationConfig withRootName(final PropertyName propertyName) {
        if (null == propertyName) {
            if (null == this._rootName) {
                return this;
            }
        } else if (propertyName.equals(_rootName)) {
            return this;
        }
        return new SerializationConfig(this, propertyName);
    }
    public SerializationConfig with(final SubtypeResolver subtypeResolver) {
        return subtypeResolver == _subtypeResolver ? this : new SerializationConfig(this, subtypeResolver);
    }
    public SerializationConfig with(final ContextAttributes contextAttributes) {
        return contextAttributes == _attributes ? this : new SerializationConfig(this, contextAttributes);
    }
    public SerializationConfig with(final DateFormat dateFormat) {
        final SerializationConfig serializationConfig = super.with(dateFormat);
        if (null == dateFormat) {
            return serializationConfig.with(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
        return serializationConfig.without(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public DeserializationConfig withDefaultPrettyPrinter(PrettyPrinter prettyPrinter) {
        return null;
    }

    public SerializationConfig with(final SerializationFeature serializationFeature) {
        final int mask = _serFeatures | serializationFeature.getMask();
        return mask == _serFeatures ? this : new SerializationConfig(this, _mapperFeatures, mask, _generatorFeatures, _generatorFeaturesToChange, _formatWriteFeatures, _formatWriteFeaturesToChange);
    }
    public SerializationConfig with(final SerializationFeature serializationFeature, final SerializationFeature... serializationFeatureArr) {
        int mask = serializationFeature.getMask() | _serFeatures;
        for (final SerializationFeature serializationFeature2 : serializationFeatureArr) {
            mask |= serializationFeature2.getMask();
        }
        return mask == _serFeatures ? this : new SerializationConfig(this, _mapperFeatures, mask, _generatorFeatures, _generatorFeaturesToChange, _formatWriteFeatures, _formatWriteFeaturesToChange);
    }
    public SerializationConfig withFeatures(final SerializationFeature... serializationFeatureArr) {
        int mask = _serFeatures;
        for (final SerializationFeature serializationFeature : serializationFeatureArr) {
            mask |= serializationFeature.getMask();
        }
        return mask == _serFeatures ? this : new SerializationConfig(this, _mapperFeatures, mask, _generatorFeatures, _generatorFeaturesToChange, _formatWriteFeatures, _formatWriteFeaturesToChange);
    }
    public SerializationConfig without(final SerializationFeature serializationFeature) {
        final int i2 = _serFeatures & (~serializationFeature.getMask());
        return i2 == _serFeatures ? this : new SerializationConfig(this, _mapperFeatures, i2, _generatorFeatures, _generatorFeaturesToChange, _formatWriteFeatures, _formatWriteFeaturesToChange);
    }
    public SerializationConfig without(final SerializationFeature serializationFeature, final SerializationFeature... serializationFeatureArr) {
        int i2 = (~serializationFeature.getMask()) & _serFeatures;
        for (final SerializationFeature serializationFeature2 : serializationFeatureArr) {
            i2 &= ~serializationFeature2.getMask();
        }
        return i2 == _serFeatures ? this : new SerializationConfig(this, _mapperFeatures, i2, _generatorFeatures, _generatorFeaturesToChange, _formatWriteFeatures, _formatWriteFeaturesToChange);
    }
    public SerializationConfig withoutFeatures(final SerializationFeature... serializationFeatureArr) {
        int i2 = _serFeatures;
        for (final SerializationFeature serializationFeature : serializationFeatureArr) {
            i2 &= ~serializationFeature.getMask();
        }
        return i2 == _serFeatures ? this : new SerializationConfig(this, _mapperFeatures, i2, _generatorFeatures, _generatorFeaturesToChange, _formatWriteFeatures, _formatWriteFeaturesToChange);
    }
    public SerializationConfig with(final JsonGenerator.Feature feature) {
        final int mask = _generatorFeatures | feature.getMask();
        final int mask2 = _generatorFeaturesToChange | feature.getMask();
        return (_generatorFeatures == mask && _generatorFeaturesToChange == mask2) ? this : new SerializationConfig(this, _mapperFeatures, _serFeatures, mask, mask2, _formatWriteFeatures, _formatWriteFeaturesToChange);
    }
    public SerializationConfig withFeatures(final JsonGenerator.Feature... featureArr) {
        final int i2 = _generatorFeatures;
        int i3 = i2;
        int i4 = _generatorFeaturesToChange;
        for (final JsonGenerator.Feature feature : featureArr) {
            final int mask = feature.getMask();
            i3 |= mask;
            i4 |= mask;
        }
        return (_generatorFeatures == i3 && _generatorFeaturesToChange == i4) ? this : new SerializationConfig(this, _mapperFeatures, _serFeatures, i3, i4, _formatWriteFeatures, _formatWriteFeaturesToChange);
    }
    public SerializationConfig without(final JsonGenerator.Feature feature) {
        final int i2 = _generatorFeatures & (~feature.getMask());
        final int mask = _generatorFeaturesToChange | feature.getMask();
        return (_generatorFeatures == i2 && _generatorFeaturesToChange == mask) ? this : new SerializationConfig(this, _mapperFeatures, _serFeatures, i2, mask, _formatWriteFeatures, _formatWriteFeaturesToChange);
    }
    public SerializationConfig withoutFeatures(final JsonGenerator.Feature... featureArr) {
        final int i2 = _generatorFeatures;
        int i3 = i2;
        int i4 = _generatorFeaturesToChange;
        for (final JsonGenerator.Feature feature : featureArr) {
            final int mask = feature.getMask();
            i3 &= ~mask;
            i4 |= mask;
        }
        return (_generatorFeatures == i3 && _generatorFeaturesToChange == i4) ? this : new SerializationConfig(this, _mapperFeatures, _serFeatures, i3, i4, _formatWriteFeatures, _formatWriteFeaturesToChange);
    }
    public SerializationConfig with(final FormatFeature formatFeature) {
        if (formatFeature instanceof JsonWriteFeature) {
            return this._withJsonWriteFeatures(formatFeature);
        }
        final int mask = _formatWriteFeatures | formatFeature.getMask();
        final int mask2 = _formatWriteFeaturesToChange | formatFeature.getMask();
        return (_formatWriteFeatures == mask && _formatWriteFeaturesToChange == mask2) ? this : new SerializationConfig(this, _mapperFeatures, _serFeatures, _generatorFeatures, _generatorFeaturesToChange, mask, mask2);
    }
    public SerializationConfig withFeatures(final FormatFeature... formatFeatureArr) {
        if (0 < formatFeatureArr.length && (formatFeatureArr[0] instanceof JsonWriteFeature)) {
            return this._withJsonWriteFeatures(formatFeatureArr);
        }
        final int i2 = _formatWriteFeatures;
        int i3 = i2;
        int i4 = _formatWriteFeaturesToChange;
        for (final FormatFeature formatFeature : formatFeatureArr) {
            final int mask = formatFeature.getMask();
            i3 |= mask;
            i4 |= mask;
        }
        return (_formatWriteFeatures == i3 && _formatWriteFeaturesToChange == i4) ? this : new SerializationConfig(this, _mapperFeatures, _serFeatures, _generatorFeatures, _generatorFeaturesToChange, i3, i4);
    }
    public SerializationConfig without(final FormatFeature formatFeature) {
        if (formatFeature instanceof JsonWriteFeature) {

        }
        final int i2 = _formatWriteFeatures & (~formatFeature.getMask());
        final int mask = _formatWriteFeaturesToChange | formatFeature.getMask();
        return (_formatWriteFeatures == i2 && _formatWriteFeaturesToChange == mask) ? this : new SerializationConfig(this, _mapperFeatures, _serFeatures, _generatorFeatures, _generatorFeaturesToChange, i2, mask);
    }
    public SerializationConfig withoutFeatures(final FormatFeature... formatFeatureArr) {
        if (formatFeatureArr.length == 0 || !(formatFeatureArr[0] instanceof JsonWriteFeature)) {
            return this;
        }

        return null;
    }
    private SerializationConfig _withJsonWriteFeatures(final FormatFeature... formatFeatureArr) {
        JsonGenerator.Feature featureMappedFeature;
        final int i2 = _generatorFeatures;
        final int i3 = _generatorFeaturesToChange;
        final int i4 = _formatWriteFeatures;
        int i5 = i2;
        int i6 = i3;
        int i7 = i4;
        int i8 = _formatWriteFeaturesToChange;
        for (final FormatFeature formatFeature : formatFeatureArr) {
            final int mask = formatFeature.getMask();
            i7 |= mask;
            i8 |= mask;
            if ((formatFeature instanceof JsonWriteFeature) && null != (featureMappedFeature = ((JsonWriteFeature) formatFeature).mappedFeature())) {
                final int mask2 = featureMappedFeature.getMask();
                i6 = mask2 | i6;
                i5 |= mask2;
            }
        }
        return (_formatWriteFeatures == i7 && _formatWriteFeaturesToChange == i8 && _generatorFeatures == i5 && _generatorFeaturesToChange == i6) ? this : new SerializationConfig(this, _mapperFeatures, _serFeatures, i5, i6, i7, i8);
    }
    public SerializationConfig _withoutJsonWriteFeatures() {
        return _withoutJsonWriteFeatures((FormatFeature[]) null);
    }
    public SerializationConfig _withoutJsonWriteFeatures(final FormatFeature... formatFeatureArr) {
        JsonGenerator.Feature featureMappedFeature;
        final int i2 = _generatorFeatures;
        final int i3 = _generatorFeaturesToChange;
        final int i4 = _formatWriteFeatures;
        int i5 = i2;
        int i6 = i3;
        int i7 = i4;
        int i8 = _formatWriteFeaturesToChange;
        for (final FormatFeature formatFeature : formatFeatureArr) {
            final int mask = formatFeature.getMask();
            i7 &= ~mask;
            i8 |= mask;
            if ((formatFeature instanceof JsonWriteFeature) && null != (featureMappedFeature = ((JsonWriteFeature) formatFeature).mappedFeature())) {
                final int mask2 = featureMappedFeature.getMask();
                i6 = mask2 | i6;
                i5 = (~mask2) & i5;
            }
        }
        return (_formatWriteFeatures == i7 && _formatWriteFeaturesToChange == i8 && _generatorFeatures == i5 && _generatorFeaturesToChange == i6) ? this : new SerializationConfig(this, _mapperFeatures, _serFeatures, i5, i6, i7, i8);
    }
    public SerializationConfig withFilters(final FilterProvider filterProvider) {
        return filterProvider == _filterProvider ? this : new SerializationConfig(this, filterProvider);
    }
    public SerializationConfig withPropertyInclusion(final JsonInclude.Value value) {
        _configOverrides.setDefaultInclusion(value);
        return this;
    }
    public PrettyPrinter constructDefaultPrettyPrinter() {
        final PrettyPrinter prettyPrinter = _defaultPrettyPrinter;
        return prettyPrinter instanceof Instantiatable ? (PrettyPrinter) ((Instantiatable) prettyPrinter).createInstance() : prettyPrinter;
    }
    public void initialize(final JsonGenerator jsonGenerator) {
        final PrettyPrinter prettyPrinterConstructDefaultPrettyPrinter;
        if (SerializationFeature.INDENT_OUTPUT.enabledIn(_serFeatures) && null == jsonGenerator.getPrettyPrinter() && null != (prettyPrinterConstructDefaultPrettyPrinter = constructDefaultPrettyPrinter())) {
            jsonGenerator.setPrettyPrinter(prettyPrinterConstructDefaultPrettyPrinter);
        }
        final boolean zEnabledIn = SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN.enabledIn(_serFeatures);
        int i2 = _generatorFeaturesToChange;
        if (0 != i2 || zEnabledIn) {
            int i3 = _generatorFeatures;
            if (zEnabledIn) {
                final int mask = JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN.getMask();
                i3 |= mask;
                i2 |= mask;
            }
            jsonGenerator.overrideStdFeatures(i3, i2);
        }
        final int i4 = _formatWriteFeaturesToChange;
        if (0 != i4) {
            jsonGenerator.overrideFormatFeatures(_formatWriteFeatures, i4);
        }
    }
    public JsonInclude.Include getSerializationInclusion() {
        final JsonInclude.Include valueInclusion = this.getDefaultPropertyInclusion().getValueInclusion();
        return JsonInclude.Include.USE_DEFAULTS == valueInclusion ? JsonInclude.Include.ALWAYS : valueInclusion;
    }
    public boolean useRootWrapping() {
        if (null != this._rootName) {
            return !r0.isEmpty().blockingGet();
        }
        return this.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
    }
    public boolean isEnabled(final SerializationFeature serializationFeature) {
        return 0 != (serializationFeature.getMask() & this._serFeatures);
    }
    public boolean isEnabled(final JsonGenerator.Feature feature, final JsonFactory jsonFactory) {
        if (0 != (feature.getMask() & this._generatorFeaturesToChange)) {
            return 0 != (feature.getMask() & this._generatorFeatures);
        }
        return jsonFactory.isEnabled(feature);
    }
    public boolean hasSerializationFeatures(final int i2) {
        return (_serFeatures & i2) == i2;
    }
    public int getSerializationFeatures() {
        return _serFeatures;
    }
    public FilterProvider getFilterProvider() {
        return _filterProvider;
    }
    public PrettyPrinter getDefaultPrettyPrinter() {
        return _defaultPrettyPrinter;
    }
    public BeanDescription introspect(final JavaType javaType) {
        return this.getClassIntrospector().forSerialization(this, javaType, this);
    }
}
