package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.databind.cfg.*;
import com.fasterxml.jackson.databind.deser.*;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.introspect.*;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsontype.*;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.node.*;
import com.fasterxml.jackson.databind.ser.*;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class ObjectMapper extends ObjectCodec implements Serializable {
    protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR;
    protected static final BaseSettings DEFAULT_BASE;
    private static final long serialVersionUID = 2;
    protected final CoercionConfigs _coercionConfigs;
    protected final ConfigOverrides _configOverrides;
    protected DeserializationConfig _deserializationConfig;
    protected DefaultDeserializationContext _deserializationContext;
    protected InjectableValues _injectableValues;
    protected final JsonFactory _jsonFactory;
    protected SimpleMixInResolver _mixIns;
    protected Set<Object> _registeredModuleTypes;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected SerializationConfig _serializationConfig;
    protected SerializerFactory _serializerFactory;
    protected DefaultSerializerProvider _serializerProvider;
    protected SubtypeResolver _subtypeResolver;
    protected TypeFactory _typeFactory;
    public enum DefaultTyping {
        JAVA_LANG_OBJECT,
        OBJECT_AND_NON_CONCRETE,
        NON_CONCRETE_AND_ARRAYS,
        NON_FINAL,
        EVERYTHING
    }
    public static class DefaultTypeResolverBuilder extends StdTypeResolverBuilder implements Serializable {
        private static final long serialVersionUID = 1;
        protected final DefaultTyping _appliesFor;
        protected final PolymorphicTypeValidator _subtypeValidator;
        public DefaultTypeResolverBuilder(final DefaultTyping defaultTyping) {
            this(defaultTyping, LaissezFaireSubTypeValidator.instance);
        }
        public DefaultTypeResolverBuilder(final DefaultTyping defaultTyping, final PolymorphicTypeValidator polymorphicTypeValidator) {
            _appliesFor = DefaultTypeResolverBuilder._requireNonNull(defaultTyping, "Can not pass `null` DefaultTyping");
            _subtypeValidator = DefaultTypeResolverBuilder._requireNonNull(polymorphicTypeValidator, "Can not pass `null` PolymorphicTypeValidator");
        }
        private static <T> T _requireNonNull(final T t, final String str) {
            if (null != t) {
                return t;
            }
            throw new NullPointerException(str);
        }
        public static DefaultTypeResolverBuilder construct(final DefaultTyping defaultTyping, final PolymorphicTypeValidator polymorphicTypeValidator) {
            return new DefaultTypeResolverBuilder(defaultTyping, polymorphicTypeValidator);
        }
        public PolymorphicTypeValidator subTypeValidator(final MapperConfig<?> mapperConfig) {
            return _subtypeValidator;
        }
        public TypeDeserializer buildTypeDeserializer(final DeserializationConfig deserializationConfig, final JavaType javaType, final Collection<NamedType> collection) {
            if (this.useForType(javaType)) {
                return super.buildTypeDeserializer(deserializationConfig, javaType, collection);
            }
            return null;
        }
        public TypeSerializer buildTypeSerializer(final SerializationConfig serializationConfig, final JavaType javaType, final Collection<NamedType> collection) {
            if (this.useForType(javaType)) {
                return super.buildTypeSerializer(serializationConfig, javaType, collection);
            }
            return null;
        }

        public boolean useForType(JavaType javaType) {
            if (javaType.isPrimitive()) {
                return false;
            }
            final int i2 = C11913.f799x3ef634e7[_appliesFor.ordinal()];
            if (1 == i2) {
                while (javaType.isArrayType()) {
                    javaType = javaType.getContentType();
                }
            } else if (2 != i2) {
                if (3 != i2) {
                    if (4 != i2) {
                        return javaType.isJavaLangObject();
                    }
                    return true;
                }
                while (javaType.isArrayType()) {
                    javaType = javaType.getContentType();
                }
                while (javaType.isReferenceType()) {
                    javaType = javaType.getReferencedType();
                }
                return !javaType.isFinal() && !TreeNode.class.isAssignableFrom(javaType.getRawClass());
            }
            while (javaType.isReferenceType()) {
                javaType = javaType.getReferencedType();
            }
            return javaType.isJavaLangObject() || !(javaType.isConcrete() || TreeNode.class.isAssignableFrom(javaType.getRawClass()));
        }
    }
    enum C11913 {
        ;

        /* renamed from: SwitchMapcomfasterxmljacksondatabindObjectMapperDefaultTyping */
        static final int[] f799x3ef634e7;

        static {
            final int[] iArr = new int[DefaultTyping.values().length];
            f799x3ef634e7 = iArr;
            try {
                iArr[DefaultTyping.NON_CONCRETE_AND_ARRAYS.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C11913.f799x3ef634e7[DefaultTyping.OBJECT_AND_NON_CONCRETE.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C11913.f799x3ef634e7[DefaultTyping.NON_FINAL.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C11913.f799x3ef634e7[DefaultTyping.EVERYTHING.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                C11913.f799x3ef634e7[DefaultTyping.JAVA_LANG_OBJECT.ordinal()] = 5;
            } catch (final NoSuchFieldError unused5) {
            }
        }
    }
    static {
        final JacksonAnnotationIntrospector jacksonAnnotationIntrospector = new JacksonAnnotationIntrospector();
        DEFAULT_ANNOTATION_INTROSPECTOR = jacksonAnnotationIntrospector;
        DEFAULT_BASE = new BaseSettings(null, jacksonAnnotationIntrospector, null, TypeFactory.defaultInstance(), null, StdDateFormat.instance, null, Locale.getDefault(), null, Base64Variants.getDefaultVariant(), LaissezFaireSubTypeValidator.instance, new DefaultAccessorNamingStrategy.Provider());
    }
    public ObjectMapper() {
        this(null, null, null);
    }
    public ObjectMapper(final JsonFactory jsonFactory) {
        this(jsonFactory, null, null);
    }
    protected ObjectMapper(final ObjectMapper objectMapper) {
        _rootDeserializers = new ConcurrentHashMap<>(64, 0.6f, 2);
        final JsonFactory jsonFactoryCopy = objectMapper._jsonFactory.copy();
        _jsonFactory = jsonFactoryCopy;
        jsonFactoryCopy.setCodec(this);
        _subtypeResolver = objectMapper._subtypeResolver.copy();
        _typeFactory = objectMapper._typeFactory;
        _injectableValues = objectMapper._injectableValues;
        final ConfigOverrides configOverridesCopy = objectMapper._configOverrides.copy();
        _configOverrides = configOverridesCopy;
        final CoercionConfigs coercionConfigsCopy = objectMapper._coercionConfigs.copy();
        _coercionConfigs = coercionConfigsCopy;
        _mixIns = objectMapper._mixIns.copy();
        final RootNameLookup rootNameLookup = new RootNameLookup();
        _serializationConfig = new SerializationConfig(objectMapper._serializationConfig, _subtypeResolver, _mixIns, rootNameLookup, configOverridesCopy);
        _deserializationConfig = new DeserializationConfig(objectMapper._deserializationConfig, _subtypeResolver, _mixIns, rootNameLookup, configOverridesCopy, coercionConfigsCopy);
        _serializerProvider = objectMapper._serializerProvider.copy();
        _deserializationContext = objectMapper._deserializationContext.copy();
        _serializerFactory = objectMapper._serializerFactory;
        final Set<Object> set = objectMapper._registeredModuleTypes;
        if (null == set) {
            _registeredModuleTypes = null;
        } else {
            _registeredModuleTypes = new LinkedHashSet(set);
        }
    }
    public ObjectMapper(final JsonFactory jsonFactory, final DefaultSerializerProvider defaultSerializerProvider, final DefaultDeserializationContext defaultDeserializationContext) {
        _rootDeserializers = new ConcurrentHashMap<>(64, 0.6f, 2);
        if (null == jsonFactory) {
            _jsonFactory = new MappingJsonFactory(this);
        } else {
            _jsonFactory = jsonFactory;
            if (null == jsonFactory.getCodec()) {
                jsonFactory.setCodec(this);
            }
        }
        _subtypeResolver = new StdSubtypeResolver();
        final RootNameLookup rootNameLookup = new RootNameLookup();
        _typeFactory = TypeFactory.defaultInstance();
        final SimpleMixInResolver simpleMixInResolver = new SimpleMixInResolver(null);
        _mixIns = simpleMixInResolver;
        final BaseSettings baseSettingsWithClassIntrospector = ObjectMapper.DEFAULT_BASE.withClassIntrospector(this.defaultClassIntrospector());
        final ConfigOverrides configOverrides = new ConfigOverrides();
        _configOverrides = configOverrides;
        final CoercionConfigs coercionConfigs = new CoercionConfigs();
        _coercionConfigs = coercionConfigs;
        _serializationConfig = new SerializationConfig(baseSettingsWithClassIntrospector, _subtypeResolver, simpleMixInResolver, rootNameLookup, configOverrides);
        _deserializationConfig = new DeserializationConfig(baseSettingsWithClassIntrospector, _subtypeResolver, simpleMixInResolver, rootNameLookup, configOverrides, coercionConfigs);
        final boolean zRequiresPropertyOrdering = _jsonFactory.requiresPropertyOrdering();
        final SerializationConfig serializationConfig = _serializationConfig;
        final MapperFeature mapperFeature = MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;
        if (serializationConfig.isEnabled(mapperFeature) ^ zRequiresPropertyOrdering) {
            this.configure(mapperFeature, zRequiresPropertyOrdering);
        }
        _serializerProvider = null == defaultSerializerProvider ? new DefaultSerializerProvider.Impl() : defaultSerializerProvider;
        _deserializationContext = null == defaultDeserializationContext ? new DefaultDeserializationContext.Impl(BeanDeserializerFactory.instance) : defaultDeserializationContext;
        _serializerFactory = BeanSerializerFactory.instance;
    }
    protected ClassIntrospector defaultClassIntrospector() {
        return new BasicClassIntrospector();
    }
    public ObjectMapper copy() {
        this._checkInvalidCopy(ObjectMapper.class);
        return new ObjectMapper(this);
    }
    protected void _checkInvalidCopy(final Class<?> cls) {
        if (this.getClass() == cls) {
            return;
        }
        throw new IllegalStateException("Failed copy(): " + this.getClass().getName() + " (version: " + this.version() + ") does not override copy(); it has to");
    }
    protected ObjectReader _newReader(final DeserializationConfig deserializationConfig) {
        return new ObjectReader(this, deserializationConfig);
    }
    protected ObjectReader _newReader(final DeserializationConfig deserializationConfig, final JavaType javaType, final Object obj, final FormatSchema formatSchema, final InjectableValues injectableValues) {
        return new ObjectReader(this, deserializationConfig, javaType, obj, formatSchema, injectableValues);
    }
    protected ObjectWriter _newWriter(final SerializationConfig serializationConfig) {
        return new ObjectWriter(this, serializationConfig);
    }
    protected ObjectWriter _newWriter(final SerializationConfig serializationConfig, final FormatSchema formatSchema) {
        return new ObjectWriter(this, serializationConfig, formatSchema);
    }
    protected ObjectWriter _newWriter(final SerializationConfig serializationConfig, final JavaType javaType, final PrettyPrinter prettyPrinter) {
        return new ObjectWriter(this, serializationConfig, javaType, prettyPrinter);
    }
    public Version version() {
        return PackageVersion.VERSION;
    }
    public ObjectMapper registerModule(final Module module) {
        final Object typeId;
        this._assertNotNull("module", module);
        if (null == module.getModuleName()) {
            throw new IllegalArgumentException("Module without defined name");
        }
        if (null == module.version()) {
            throw new IllegalArgumentException("Module without defined version");
        }
        final Iterator<? extends Module> it = module.getDependencies().iterator();
        while (it.hasNext()) {
            this.registerModule(it.next());
        }
        if (this.isEnabled(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS) && null != (typeId = module.getTypeId())) {
            if (null == this._registeredModuleTypes) {
                _registeredModuleTypes = new LinkedHashSet();
            }
            if (!_registeredModuleTypes.add(typeId)) {
                return this;
            }
        }
        module.setupModule(new Module.SetupContext() {
            void C11891() {
            }
            public void addDeserializers(final Deserializers deserializers) {
                final DeserializerFactory deserializerFactoryWithAdditionalDeserializers = _deserializationContext._factory.withAdditionalDeserializers(deserializers);
                final ObjectMapper objectMapper = ObjectMapper.this;
                objectMapper._deserializationContext = objectMapper._deserializationContext.with(deserializerFactoryWithAdditionalDeserializers);
            }
            public void addKeyDeserializers(final KeyDeserializers keyDeserializers) {
                final DeserializerFactory deserializerFactoryWithAdditionalKeyDeserializers = _deserializationContext._factory.withAdditionalKeyDeserializers(keyDeserializers);
                final ObjectMapper objectMapper = ObjectMapper.this;
                objectMapper._deserializationContext = objectMapper._deserializationContext.with(deserializerFactoryWithAdditionalKeyDeserializers);
            }
            public void addBeanDeserializerModifier(final BeanDeserializerModifier beanDeserializerModifier) {
                final DeserializerFactory deserializerFactoryWithDeserializerModifier = _deserializationContext._factory.withDeserializerModifier(beanDeserializerModifier);
                final ObjectMapper objectMapper = ObjectMapper.this;
                objectMapper._deserializationContext = objectMapper._deserializationContext.with(deserializerFactoryWithDeserializerModifier);
            }
            public void addSerializers(final Serializers serializers) {
                final ObjectMapper objectMapper = ObjectMapper.this;
                objectMapper._serializerFactory = objectMapper._serializerFactory.withAdditionalSerializers(serializers);
            }
            public void addKeySerializers(final Serializers serializers) {
                final ObjectMapper objectMapper = ObjectMapper.this;
                objectMapper._serializerFactory = objectMapper._serializerFactory.withAdditionalKeySerializers(serializers);
            }
            public void addBeanSerializerModifier(final BeanSerializerModifier beanSerializerModifier) {
                final ObjectMapper objectMapper = ObjectMapper.this;
                objectMapper._serializerFactory = objectMapper._serializerFactory.withSerializerModifier(beanSerializerModifier);
            }
            public void addAbstractTypeResolver(final AbstractTypeResolver abstractTypeResolver) {
                final DeserializerFactory deserializerFactoryWithAbstractTypeResolver = _deserializationContext._factory.withAbstractTypeResolver(abstractTypeResolver);
                final ObjectMapper objectMapper = ObjectMapper.this;
                objectMapper._deserializationContext = objectMapper._deserializationContext.with(deserializerFactoryWithAbstractTypeResolver);
            }
            public void addValueInstantiators(final ValueInstantiators valueInstantiators) {
                final DeserializerFactory deserializerFactoryWithValueInstantiators = _deserializationContext._factory.withValueInstantiators(valueInstantiators);
                final ObjectMapper objectMapper = ObjectMapper.this;
                objectMapper._deserializationContext = objectMapper._deserializationContext.with(deserializerFactoryWithValueInstantiators);
            }
            public void registerSubtypes(final NamedType... namedTypeArr) {
                ObjectMapper.this.registerSubtypes(namedTypeArr);
            }
            public void setMixInAnnotations(final Class<?> cls, final Class<?> cls2) {
                addMixIn(cls, cls2);
            }

            public void setNamingStrategy(final PropertyNamingStrategy propertyNamingStrategy) {
                setPropertyNamingStrategy(propertyNamingStrategy);
            }
        });
        return this;
    }
    class C11891 implements Module.SetupContext {
        C11891() {
        }
        public void addDeserializers(final Deserializers deserializers) {
            final DeserializerFactory deserializerFactoryWithAdditionalDeserializers = _deserializationContext._factory.withAdditionalDeserializers(deserializers);
            final ObjectMapper objectMapper = ObjectMapper.this;
            objectMapper._deserializationContext = objectMapper._deserializationContext.with(deserializerFactoryWithAdditionalDeserializers);
        }
        public void addKeyDeserializers(final KeyDeserializers keyDeserializers) {
            final DeserializerFactory deserializerFactoryWithAdditionalKeyDeserializers = _deserializationContext._factory.withAdditionalKeyDeserializers(keyDeserializers);
            final ObjectMapper objectMapper = ObjectMapper.this;
            objectMapper._deserializationContext = objectMapper._deserializationContext.with(deserializerFactoryWithAdditionalKeyDeserializers);
        }
        public void addBeanDeserializerModifier(final BeanDeserializerModifier beanDeserializerModifier) {
            final DeserializerFactory deserializerFactoryWithDeserializerModifier = _deserializationContext._factory.withDeserializerModifier(beanDeserializerModifier);
            final ObjectMapper objectMapper = ObjectMapper.this;
            objectMapper._deserializationContext = objectMapper._deserializationContext.with(deserializerFactoryWithDeserializerModifier);
        }
        public void addSerializers(final Serializers serializers) {
            final ObjectMapper objectMapper = ObjectMapper.this;
            objectMapper._serializerFactory = objectMapper._serializerFactory.withAdditionalSerializers(serializers);
        }
        public void addKeySerializers(final Serializers serializers) {
            final ObjectMapper objectMapper = ObjectMapper.this;
            objectMapper._serializerFactory = objectMapper._serializerFactory.withAdditionalKeySerializers(serializers);
        }
        public void addBeanSerializerModifier(final BeanSerializerModifier beanSerializerModifier) {
            final ObjectMapper objectMapper = ObjectMapper.this;
            objectMapper._serializerFactory = objectMapper._serializerFactory.withSerializerModifier(beanSerializerModifier);
        }
        public void addAbstractTypeResolver(final AbstractTypeResolver abstractTypeResolver) {
            final DeserializerFactory deserializerFactoryWithAbstractTypeResolver = _deserializationContext._factory.withAbstractTypeResolver(abstractTypeResolver);
            final ObjectMapper objectMapper = ObjectMapper.this;
            objectMapper._deserializationContext = objectMapper._deserializationContext.with(deserializerFactoryWithAbstractTypeResolver);
        }
        public void addValueInstantiators(final ValueInstantiators valueInstantiators) {
            final DeserializerFactory deserializerFactoryWithValueInstantiators = _deserializationContext._factory.withValueInstantiators(valueInstantiators);
            final ObjectMapper objectMapper = ObjectMapper.this;
            objectMapper._deserializationContext = objectMapper._deserializationContext.with(deserializerFactoryWithValueInstantiators);
        }
        public void registerSubtypes(final NamedType... namedTypeArr) {
            ObjectMapper.this.registerSubtypes(namedTypeArr);
        }
        public void setMixInAnnotations(final Class<?> cls, final Class<?> cls2) {
            addMixIn(cls, cls2);
        }
        public void setNamingStrategy(final PropertyNamingStrategy propertyNamingStrategy) {
            setPropertyNamingStrategy(propertyNamingStrategy);
        }
    }
    public ObjectMapper registerModules(final Module... moduleArr) {
        for (final Module module : moduleArr) {
            this.registerModule(module);
        }
        return this;
    }
    public ObjectMapper registerModules(final Iterable<? extends Module> iterable) {
        this._assertNotNull("modules", iterable);
        final Iterator<? extends Module> it = iterable.iterator();
        while (it.hasNext()) {
            this.registerModule(it.next());
        }
        return this;
    }
    public Set<Object> getRegisteredModuleIds() {
        final Set<Object> set = _registeredModuleTypes;
        return null == set ? Collections.emptySet() : Collections.unmodifiableSet(set);
    }
    public static List<Module> findModules() {
        return ObjectMapper.findModules(null);
    }
    public static List<Module> findModules(final ClassLoader classLoader) {
        final ArrayList arrayList = new ArrayList();
        final Iterator it = ObjectMapper.secureGetServiceLoader(Module.class, classLoader).iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }
    private static <T> ServiceLoader<T> secureGetServiceLoader(final Class<T> cls, final ClassLoader classLoader) {
        if (null == System.getSecurityManager()) {
            return null == classLoader ? ServiceLoader.load(cls) : ServiceLoader.load(cls, classLoader);
        }
        return AccessController.doPrivileged(new PrivilegedAction<ServiceLoader<T>>() {
            final ClassLoader valclassLoader = null;
            final Class valclazz = null;
            void C11902(final ClassLoader classLoader2, final Class cls2) {
            }
            public ServiceLoader<T> run() {
                final ClassLoader classLoader2 = classLoader;
                return null == classLoader2 ? ServiceLoader.load(cls) : ServiceLoader.load(cls, classLoader2);
            }
        });
    }
    static class C11902<T> implements PrivilegedAction<ServiceLoader<T>> {
        final ClassLoader valclassLoader;
        final Class valclazz;
        C11902(ClassLoader classLoader2, final Class cls2, final ClassLoader valclassLoader, final Class valclazz) {
            this.valclassLoader = valclassLoader;
            this.valclazz = valclazz;
            classLoader2 = classLoader2;
            Class cls = cls2;
        }
        public ServiceLoader<T> run() {
            final ClassLoader classLoader = null;
            final ClassLoader classLoader2 = classLoader;
            return null == classLoader2 ? ServiceLoader.load(valclazz) : ServiceLoader.load(valclazz, classLoader2);
        }
    }
    public ObjectMapper findAndRegisterModules() {
        return this.registerModules(ObjectMapper.findModules());
    }
    public JsonGenerator createGenerator(final OutputStream outputStream) throws IOException {
        this._assertNotNull("out", outputStream);
        final JsonGenerator jsonGeneratorCreateGenerator = _jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
        _serializationConfig.initialize(jsonGeneratorCreateGenerator);
        return jsonGeneratorCreateGenerator;
    }
    public JsonGenerator createGenerator(final OutputStream outputStream, final JsonEncoding jsonEncoding) throws IOException {
        this._assertNotNull("out", outputStream);
        final JsonGenerator jsonGeneratorCreateGenerator = _jsonFactory.createGenerator(outputStream, jsonEncoding);
        _serializationConfig.initialize(jsonGeneratorCreateGenerator);
        return jsonGeneratorCreateGenerator;
    }
    public JsonGenerator createGenerator(final Writer writer) throws IOException {
        this._assertNotNull("w", writer);
        final JsonGenerator jsonGeneratorCreateGenerator = _jsonFactory.createGenerator(writer);
        _serializationConfig.initialize(jsonGeneratorCreateGenerator);
        return jsonGeneratorCreateGenerator;
    }
    public JsonGenerator createGenerator(final File file, final JsonEncoding jsonEncoding) throws IOException {
        this._assertNotNull("outputFile", file);
        final JsonGenerator jsonGeneratorCreateGenerator = _jsonFactory.createGenerator(file, jsonEncoding);
        _serializationConfig.initialize(jsonGeneratorCreateGenerator);
        return jsonGeneratorCreateGenerator;
    }
    public JsonGenerator createGenerator(final DataOutput dataOutput) throws IOException {
        this._assertNotNull("out", dataOutput);
        final JsonGenerator jsonGeneratorCreateGenerator = _jsonFactory.createGenerator(dataOutput);
        _serializationConfig.initialize(jsonGeneratorCreateGenerator);
        return jsonGeneratorCreateGenerator;
    }
    public JsonParser createParser(final File file) throws IOException {
        this._assertNotNull("src", file);
        return _deserializationConfig.initialize(_jsonFactory.createParser(file));
    }
    public JsonParser createParser(final URL url) throws IOException {
        this._assertNotNull("src", url);
        return _deserializationConfig.initialize(_jsonFactory.createParser(url));
    }
    public JsonParser createParser(final InputStream inputStream) throws IOException {
        this._assertNotNull("in", inputStream);
        return _deserializationConfig.initialize(_jsonFactory.createParser(inputStream));
    }
    public JsonParser createParser(final Reader reader) throws IOException {
        this._assertNotNull("r", reader);
        return _deserializationConfig.initialize(_jsonFactory.createParser(reader));
    }
    public JsonParser createParser(final byte[] bArr) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, bArr);
        return _deserializationConfig.initialize(_jsonFactory.createParser(bArr));
    }
    public JsonParser createParser(final byte[] bArr, final int i2, final int i3) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, bArr);
        return _deserializationConfig.initialize(_jsonFactory.createParser(bArr, i2, i3));
    }
    public JsonParser createParser(final String str) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, str);
        return _deserializationConfig.initialize(_jsonFactory.createParser(str));
    }
    public JsonParser createParser(final char[] cArr) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, cArr);
        return _deserializationConfig.initialize(_jsonFactory.createParser(cArr));
    }
    public JsonParser createParser(final char[] cArr, final int i2, final int i3) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, cArr);
        return _deserializationConfig.initialize(_jsonFactory.createParser(cArr, i2, i3));
    }
    public JsonParser createParser(final DataInput dataInput) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, dataInput);
        return _deserializationConfig.initialize(_jsonFactory.createParser(dataInput));
    }
    public JsonParser createNonBlockingByteArrayParser() throws IOException {
        return _deserializationConfig.initialize(_jsonFactory.createNonBlockingByteArrayParser());
    }
    public SerializationConfig getSerializationConfig() {
        return _serializationConfig;
    }
    public DeserializationConfig getDeserializationConfig() {
        return _deserializationConfig;
    }
    public DeserializationContext getDeserializationContext() {
        return _deserializationContext;
    }
    public ObjectMapper setSerializerFactory(final SerializerFactory serializerFactory) {
        _serializerFactory = serializerFactory;
        return this;
    }
    public SerializerFactory getSerializerFactory() {
        return _serializerFactory;
    }
    public ObjectMapper setSerializerProvider(final DefaultSerializerProvider defaultSerializerProvider) {
        _serializerProvider = defaultSerializerProvider;
        return this;
    }
    public SerializerProvider getSerializerProvider() {
        return _serializerProvider;
    }
    public SerializerProvider getSerializerProviderInstance() {
        return this._serializerProvider(_serializationConfig);
    }
    public ObjectMapper setMixIns(final Map<Class<?>, Class<?>> map) {
        _mixIns.setLocalDefinitions(map);
        return this;
    }
    public ObjectMapper addMixIn(final Class<?> cls, final Class<?> cls2) {
        _mixIns.addLocalDefinition(cls, cls2);
        return this;
    }
    public ObjectMapper setMixInResolver(final ClassIntrospector.MixInResolver mixInResolver) {
        final SimpleMixInResolver simpleMixInResolverWithOverrides = _mixIns.withOverrides(mixInResolver);
        if (simpleMixInResolverWithOverrides != _mixIns) {
            _mixIns = simpleMixInResolverWithOverrides;
            _deserializationConfig = new DeserializationConfig(_deserializationConfig, simpleMixInResolverWithOverrides);
            _serializationConfig = new SerializationConfig(_serializationConfig, simpleMixInResolverWithOverrides);
        }
        return this;
    }
    public Class<?> findMixInClassFor(final Class<?> cls) {
        return _mixIns.findMixInClassFor(cls);
    }
    public int mixInCount() {
        return _mixIns.localSize();
    }
    public void setMixInAnnotations(final Map<Class<?>, Class<?>> map) {
        this.setMixIns(map);
    }
    public final void addMixInAnnotations(final Class<?> cls, final Class<?> cls2) {
        this.addMixIn(cls, cls2);
    }
    public VisibilityChecker<?> getVisibilityChecker() {
        return _serializationConfig.getDefaultVisibilityChecker();
    }
    public ObjectMapper setVisibility(final VisibilityChecker<?> visibilityChecker) {
        _configOverrides.setDefaultVisibility(visibilityChecker);
        return this;
    }
    public ObjectMapper setVisibility(final PropertyAccessor propertyAccessor, final JsonAutoDetect.Visibility visibility) {
        _configOverrides.setDefaultVisibility(_configOverrides.getDefaultVisibility().withVisibility(propertyAccessor, visibility));
        return this;
    }
    public SubtypeResolver getSubtypeResolver() {
        return _subtypeResolver;
    }
    public ObjectMapper setSubtypeResolver(final SubtypeResolver subtypeResolver) {
        _subtypeResolver = subtypeResolver;
        _deserializationConfig = _deserializationConfig.with(subtypeResolver);
        _serializationConfig = _serializationConfig.with(subtypeResolver);
        return this;
    }
    public ObjectMapper setAnnotationIntrospector(final AnnotationIntrospector annotationIntrospector) {
        _serializationConfig = _serializationConfig.with(annotationIntrospector);
        _deserializationConfig = _deserializationConfig.with(annotationIntrospector);
        return this;
    }
    public ObjectMapper setAnnotationIntrospectors(final AnnotationIntrospector annotationIntrospector, final AnnotationIntrospector annotationIntrospector2) {
        _serializationConfig = _serializationConfig.with(annotationIntrospector);
        _deserializationConfig = _deserializationConfig.with(annotationIntrospector2);
        return this;
    }
    public ObjectMapper setPropertyNamingStrategy(final PropertyNamingStrategy propertyNamingStrategy) {
        _serializationConfig = _serializationConfig.with(propertyNamingStrategy);
        _deserializationConfig = _deserializationConfig.with(propertyNamingStrategy);
        return this;
    }
    public PropertyNamingStrategy getPropertyNamingStrategy() {
        return _serializationConfig.getPropertyNamingStrategy();
    }
    public ObjectMapper setAccessorNaming(final AccessorNamingStrategy.Provider provider) {
        _serializationConfig = _serializationConfig.with(provider);
        _deserializationConfig = _deserializationConfig.with(provider);
        return this;
    }
    public ObjectMapper setDefaultPrettyPrinter(final PrettyPrinter prettyPrinter) {
        _serializationConfig = _serializationConfig;
        _deserializationConfig = _deserializationConfig.withDefaultPrettyPrinter(prettyPrinter);
        return this;
    }
    public void setVisibilityChecker(final VisibilityChecker<?> visibilityChecker) {
        this.setVisibility(visibilityChecker);
    }
    public ObjectMapper setPolymorphicTypeValidator(final PolymorphicTypeValidator polymorphicTypeValidator) {
        _deserializationConfig = _deserializationConfig._withBase(_deserializationConfig.getBaseSettings().with(polymorphicTypeValidator));
        return this;
    }
    public PolymorphicTypeValidator getPolymorphicTypeValidator() {
        return _deserializationConfig.getBaseSettings().getPolymorphicTypeValidator();
    }
    public ObjectMapper setSerializationInclusion(final JsonInclude.Include include) {
        this.setPropertyInclusion(JsonInclude.Value.construct(include, include));
        return this;
    }
    public ObjectMapper setPropertyInclusion(final JsonInclude.Value value) {
        return this.setDefaultPropertyInclusion(value);
    }
    public ObjectMapper setDefaultPropertyInclusion(final JsonInclude.Value value) {
        _configOverrides.setDefaultInclusion(value);
        return this;
    }
    public ObjectMapper setDefaultPropertyInclusion(final JsonInclude.Include include) {
        _configOverrides.setDefaultInclusion(JsonInclude.Value.construct(include, include));
        return this;
    }
    public ObjectMapper setDefaultSetterInfo(final JsonSetter.Value value) {
        _configOverrides.setDefaultSetterInfo(value);
        return this;
    }
    public ObjectMapper setDefaultVisibility(final JsonAutoDetect.Value value) {
        _configOverrides.setDefaultVisibility(VisibilityChecker.Std.construct(value));
        return this;
    }
    public ObjectMapper setDefaultMergeable(final Boolean bool) {
        _configOverrides.setDefaultMergeable(bool);
        return this;
    }
    public ObjectMapper setDefaultLeniency(final Boolean bool) {
        _configOverrides.setDefaultLeniency(bool);
        return this;
    }
    public void registerSubtypes(final Class<?>... clsArr) {
        this._subtypeResolver.registerSubtypes(clsArr);
    }
    public void registerSubtypes(final NamedType... namedTypeArr) {
        this._subtypeResolver.registerSubtypes(namedTypeArr);
    }
    public void registerSubtypes(final Collection<Class<?>> collection) {
        this._subtypeResolver.registerSubtypes(collection);
    }
    public ObjectMapper activateDefaultTyping(final PolymorphicTypeValidator polymorphicTypeValidator) {
        return this.activateDefaultTyping(polymorphicTypeValidator, DefaultTyping.OBJECT_AND_NON_CONCRETE);
    }
    public ObjectMapper activateDefaultTyping(final PolymorphicTypeValidator polymorphicTypeValidator, final DefaultTyping defaultTyping) {
        return this.activateDefaultTyping(polymorphicTypeValidator, defaultTyping, JsonTypeInfo.EnumC1184As.WRAPPER_ARRAY);
    }
    public ObjectMapper activateDefaultTyping(final PolymorphicTypeValidator polymorphicTypeValidator, final DefaultTyping defaultTyping, final JsonTypeInfo.EnumC1184As enumC1184As) {
        if (JsonTypeInfo.EnumC1184As.EXTERNAL_PROPERTY == enumC1184As) {
            throw new IllegalArgumentException("Cannot use includeAs of " + JsonTypeInfo.EnumC1184As.EXTERNAL_PROPERTY);
        }
        return this.setDefaultTyping(this._constructDefaultTypeResolverBuilder(defaultTyping, polymorphicTypeValidator).init(JsonTypeInfo.EnumC1185Id.CLASS, null).inclusion(enumC1184As));
    }
    public ObjectMapper activateDefaultTypingAsProperty(final PolymorphicTypeValidator polymorphicTypeValidator, final DefaultTyping defaultTyping, final String str) {
        return this.setDefaultTyping(this._constructDefaultTypeResolverBuilder(defaultTyping, polymorphicTypeValidator).init(JsonTypeInfo.EnumC1185Id.CLASS, null).inclusion(JsonTypeInfo.EnumC1184As.PROPERTY).typeProperty(str));
    }
    public ObjectMapper deactivateDefaultTyping() {
        return this.setDefaultTyping(null);
    }
    public ObjectMapper setDefaultTyping(final TypeResolverBuilder<?> typeResolverBuilder) {
        _deserializationConfig = _deserializationConfig.with(typeResolverBuilder);
        _serializationConfig = _serializationConfig.with(typeResolverBuilder);
        return this;
    }
    public ObjectMapper enableDefaultTyping() {
        return this.activateDefaultTyping(this.getPolymorphicTypeValidator());
    }
    public ObjectMapper enableDefaultTyping(final DefaultTyping defaultTyping) {
        return this.enableDefaultTyping(defaultTyping, JsonTypeInfo.EnumC1184As.WRAPPER_ARRAY);
    }
    public ObjectMapper enableDefaultTyping(final DefaultTyping defaultTyping, final JsonTypeInfo.EnumC1184As enumC1184As) {
        return this.activateDefaultTyping(this.getPolymorphicTypeValidator(), defaultTyping, enumC1184As);
    }
    public ObjectMapper enableDefaultTypingAsProperty(final DefaultTyping defaultTyping, final String str) {
        return this.activateDefaultTypingAsProperty(this.getPolymorphicTypeValidator(), defaultTyping, str);
    }
    public ObjectMapper disableDefaultTyping() {
        return this.setDefaultTyping(null);
    }
    public MutableConfigOverride configOverride(final Class<?> cls) {
        return _configOverrides.findOrCreateOverride(cls);
    }
    public MutableCoercionConfig coercionConfigDefaults() {
        return _coercionConfigs.defaultCoercions();
    }
    public MutableCoercionConfig coercionConfigFor(final LogicalType logicalType) {
        return _coercionConfigs.findOrCreateCoercion(logicalType);
    }
    public MutableCoercionConfig coercionConfigFor(final Class<?> cls) {
        return _coercionConfigs.findOrCreateCoercion(cls);
    }
    public TypeFactory getTypeFactory() {
        return _typeFactory;
    }
    public ObjectMapper setTypeFactory(final TypeFactory typeFactory) {
        _typeFactory = typeFactory;
        _deserializationConfig = _deserializationConfig.with(typeFactory);
        _serializationConfig = _serializationConfig.with(typeFactory);
        return this;
    }
    public JavaType constructType(final Type type) {
        this._assertNotNull("t", type);
        return _typeFactory.constructType(type);
    }
    public JavaType constructType(final TypeReference<?> typeReference) {
        this._assertNotNull("typeRef", typeReference);
        return _typeFactory.constructType(typeReference);
    }
    public JsonNodeFactory getNodeFactory() {
        return _deserializationConfig.getNodeFactory();
    }
    public ObjectMapper setNodeFactory(final JsonNodeFactory jsonNodeFactory) {
        _deserializationConfig = _deserializationConfig.with(jsonNodeFactory);
        return this;
    }
    public ObjectMapper setConstructorDetector(final ConstructorDetector constructorDetector) {
        _deserializationConfig = _deserializationConfig.with(constructorDetector);
        return this;
    }
    public ObjectMapper addHandler(final DeserializationProblemHandler deserializationProblemHandler) {
        _deserializationConfig = _deserializationConfig.withHandler(deserializationProblemHandler);
        return this;
    }
    public ObjectMapper clearProblemHandlers() {
        _deserializationConfig = _deserializationConfig.withNoProblemHandlers();
        return this;
    }
    public ObjectMapper setConfig(final DeserializationConfig deserializationConfig) {
        this._assertNotNull("config", deserializationConfig);
        _deserializationConfig = deserializationConfig;
        return this;
    }
    public void setFilters(final FilterProvider filterProvider) {
        _serializationConfig = _serializationConfig.withFilters(filterProvider);
    }
    public ObjectMapper setFilterProvider(final FilterProvider filterProvider) {
        _serializationConfig = _serializationConfig.withFilters(filterProvider);
        return this;
    }
    public ObjectMapper setBase64Variant(final Base64Variant base64Variant) {
        _serializationConfig = _serializationConfig.with(base64Variant);
        _deserializationConfig = _deserializationConfig.with(base64Variant);
        return this;
    }
    public ObjectMapper setConfig(final SerializationConfig serializationConfig) {
        this._assertNotNull("config", serializationConfig);
        _serializationConfig = serializationConfig;
        return this;
    }
    public JsonFactory tokenStreamFactory() {
        return _jsonFactory;
    }
    public JsonFactory getFactory() {
        return _jsonFactory;
    }
    public JsonFactory getJsonFactory() {
        return this.getFactory();
    }
    public ObjectMapper setDateFormat(final DateFormat dateFormat) {
        _deserializationConfig = _deserializationConfig.with(dateFormat);
        _serializationConfig = _serializationConfig.with(dateFormat);
        return this;
    }
    public DateFormat getDateFormat() {
        return _serializationConfig.getDateFormat();
    }
    public Object setHandlerInstantiator(final HandlerInstantiator handlerInstantiator) {
        _deserializationConfig = _deserializationConfig.with(handlerInstantiator);
        _serializationConfig = _serializationConfig.with(handlerInstantiator);
        return this;
    }
    public ObjectMapper setInjectableValues(final InjectableValues injectableValues) {
        _injectableValues = injectableValues;
        return this;
    }
    public InjectableValues getInjectableValues() {
        return _injectableValues;
    }
    public ObjectMapper setLocale(final Locale locale) {
        _deserializationConfig = _deserializationConfig.with(locale);
        _serializationConfig = _serializationConfig.with(locale);
        return this;
    }
    public ObjectMapper setTimeZone(final TimeZone timeZone) {
        _deserializationConfig = _deserializationConfig.with(timeZone);
        _serializationConfig = _serializationConfig.with(timeZone);
        return this;
    }
    public boolean isEnabled(final MapperFeature mapperFeature) {
        return _serializationConfig.isEnabled(mapperFeature);
    }
    public ObjectMapper configure(final MapperFeature mapperFeature, final boolean z) {
        _serializationConfig = z ? _serializationConfig.with(mapperFeature) : _serializationConfig.without(mapperFeature);
        _deserializationConfig = z ? _deserializationConfig.with(mapperFeature) : _deserializationConfig.without(mapperFeature);
        return this;
    }
    public ObjectMapper enable(final MapperFeature... mapperFeatureArr) {
        _deserializationConfig = _deserializationConfig.with(mapperFeatureArr);
        _serializationConfig = _serializationConfig.with(mapperFeatureArr);
        return this;
    }
    public ObjectMapper disable(final MapperFeature... mapperFeatureArr) {
        _deserializationConfig = _deserializationConfig.without(mapperFeatureArr);
        _serializationConfig = _serializationConfig.without(mapperFeatureArr);
        return this;
    }
    public boolean isEnabled(final SerializationFeature serializationFeature) {
        return _serializationConfig.isEnabled(serializationFeature);
    }
    public ObjectMapper configure(final SerializationFeature serializationFeature, final boolean z) {
        _serializationConfig = z ? _serializationConfig.with(serializationFeature) : _serializationConfig.without(serializationFeature);
        return this;
    }
    public ObjectMapper enable(final SerializationFeature serializationFeature) {
        _serializationConfig = _serializationConfig.with(serializationFeature);
        return this;
    }
    public ObjectMapper enable(final SerializationFeature serializationFeature, final SerializationFeature... serializationFeatureArr) {
        _serializationConfig = _serializationConfig.with(serializationFeature, serializationFeatureArr);
        return this;
    }
    public ObjectMapper disable(final SerializationFeature serializationFeature) {
        _serializationConfig = _serializationConfig.without(serializationFeature);
        return this;
    }
    public ObjectMapper disable(final SerializationFeature serializationFeature, final SerializationFeature... serializationFeatureArr) {
        _serializationConfig = _serializationConfig.without(serializationFeature, serializationFeatureArr);
        return this;
    }
    public boolean isEnabled(final DeserializationFeature deserializationFeature) {
        return _deserializationConfig.isEnabled(deserializationFeature);
    }
    public ObjectMapper configure(final DeserializationFeature deserializationFeature, final boolean z) {
        _deserializationConfig = z ? _deserializationConfig.with(deserializationFeature) : _deserializationConfig.without(deserializationFeature);
        return this;
    }
    public ObjectMapper enable(final DeserializationFeature deserializationFeature) {
        _deserializationConfig = _deserializationConfig.with(deserializationFeature);
        return this;
    }
    public ObjectMapper enable(final DeserializationFeature deserializationFeature, final DeserializationFeature... deserializationFeatureArr) {
        _deserializationConfig = _deserializationConfig.with(deserializationFeature, deserializationFeatureArr);
        return this;
    }
    public ObjectMapper disable(final DeserializationFeature deserializationFeature) {
        _deserializationConfig = _deserializationConfig.without(deserializationFeature);
        return this;
    }
    public ObjectMapper disable(final DeserializationFeature deserializationFeature, final DeserializationFeature... deserializationFeatureArr) {
        _deserializationConfig = _deserializationConfig.without(deserializationFeature, deserializationFeatureArr);
        return this;
    }
    public boolean isEnabled(final JsonParser.Feature feature) {
        return _deserializationConfig.isEnabled(feature, _jsonFactory);
    }
    public ObjectMapper configure(final JsonParser.Feature feature, final boolean z) {
        _jsonFactory.configure(feature, z);
        return this;
    }
    public ObjectMapper enable(final JsonParser.Feature... featureArr) {
        for (final JsonParser.Feature feature : featureArr) {
            _jsonFactory.enable(feature);
        }
        return this;
    }
    public ObjectMapper disable(final JsonParser.Feature... featureArr) {
        for (final JsonParser.Feature feature : featureArr) {
            _jsonFactory.disable(feature);
        }
        return this;
    }
    public boolean isEnabled(final JsonGenerator.Feature feature) {
        return _serializationConfig.isEnabled(feature, _jsonFactory);
    }
    public ObjectMapper configure(final JsonGenerator.Feature feature, final boolean z) {
        _jsonFactory.configure(feature, z);
        return this;
    }
    public ObjectMapper enable(final JsonGenerator.Feature... featureArr) {
        for (final JsonGenerator.Feature feature : featureArr) {
            _jsonFactory.enable(feature);
        }
        return this;
    }
    public ObjectMapper disable(final JsonGenerator.Feature... featureArr) {
        for (final JsonGenerator.Feature feature : featureArr) {
            _jsonFactory.disable(feature);
        }
        return this;
    }
    public boolean isEnabled(final JsonFactory.Feature feature) {
        return _jsonFactory.isEnabled(feature);
    }
    public boolean isEnabled(final StreamReadFeature streamReadFeature) {
        return this.isEnabled(streamReadFeature.mappedFeature());
    }
    public boolean isEnabled(final StreamWriteFeature streamWriteFeature) {
        return this.isEnabled(streamWriteFeature.mappedFeature());
    }
    public <T> T readValue(final JsonParser jsonParser, final Class<T> cls) throws IOException {
        this._assertNotNull("p", jsonParser);
        return (T) this._readValue(this._deserializationConfig, jsonParser, _typeFactory.constructType(cls));
    }
    public <T> T readValue(final JsonParser jsonParser, final TypeReference<T> typeReference) throws IOException {
        this._assertNotNull("p", jsonParser);
        return (T) this._readValue(this._deserializationConfig, jsonParser, _typeFactory.constructType((TypeReference<?>) typeReference));
    }
    public final <T> T readValue(final JsonParser jsonParser, final ResolvedType resolvedType) throws IOException {
        this._assertNotNull("p", jsonParser);
        return (T) this._readValue(this._deserializationConfig, jsonParser, (JavaType) resolvedType);
    }
    public <T> T readValue(final JsonParser jsonParser, final JavaType javaType) throws IOException {
        this._assertNotNull("p", jsonParser);
        return (T) this._readValue(this._deserializationConfig, jsonParser, javaType);
    }
    public <T extends TreeNode> T readTree(final JsonParser jsonParser) throws IOException {
        this._assertNotNull("p", jsonParser);
        final DeserializationConfig deserializationConfig = this._deserializationConfig;
        if (null == jsonParser.currentToken() && null == jsonParser.nextToken()) {
            return null;
        }
        final JsonNode jsonNode = (JsonNode) this._readValue(deserializationConfig, jsonParser, this.constructType(JsonNode.class));
        return null == jsonNode ? (T) this.getNodeFactory().m827nullNode() : (T) jsonNode;
    }
    public <T> MappingIterator<T> readValues(final JsonParser jsonParser, final ResolvedType resolvedType) throws IOException {
        return this.readValues(jsonParser, (JavaType) resolvedType);
    }
    public <T> MappingIterator<T> readValues(final JsonParser jsonParser, final JavaType javaType) throws IOException {
        this._assertNotNull("p", jsonParser);
        final DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext = this.createDeserializationContext(jsonParser, this._deserializationConfig);
        return new MappingIterator<>(javaType, jsonParser, defaultDeserializationContextCreateDeserializationContext, this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext, javaType), false, null);
    }
    public <T> MappingIterator<T> readValues(final JsonParser jsonParser, final Class<T> cls) throws IOException {
        return this.readValues(jsonParser, _typeFactory.constructType(cls));
    }
    public <T> MappingIterator<T> readValues(final JsonParser jsonParser, final TypeReference<T> typeReference) throws IOException {
        return this.readValues(jsonParser, _typeFactory.constructType(typeReference));
    }
    public JsonNode readTree(final InputStream inputStream) throws IOException {
        this._assertNotNull("in", inputStream);
        return this._readTreeAndClose(_jsonFactory.createParser(inputStream));
    }
    public JsonNode readTree(final Reader reader) throws IOException {
        this._assertNotNull("r", reader);
        return this._readTreeAndClose(_jsonFactory.createParser(reader));
    }
    public JsonNode readTree(final String str) throws JsonProcessingException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, str);
        try {
            return this._readTreeAndClose(_jsonFactory.createParser(str));
        } catch (final JsonProcessingException e2) {
            throw e2;
        } catch (final IOException e3) {
            throw JsonMappingException.fromUnexpectedIOE(e3);
        }
    }
    public JsonNode readTree(final byte[] bArr) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, bArr);
        return this._readTreeAndClose(_jsonFactory.createParser(bArr));
    }
    public JsonNode readTree(final byte[] bArr, final int i2, final int i3) throws IOException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, bArr);
        return this._readTreeAndClose(_jsonFactory.createParser(bArr, i2, i3));
    }
    public JsonNode readTree(final File file) throws IOException {
        this._assertNotNull("file", file);
        return this._readTreeAndClose(_jsonFactory.createParser(file));
    }
    public JsonNode readTree(final URL url) throws IOException {
        this._assertNotNull("source", url);
        return this._readTreeAndClose(_jsonFactory.createParser(url));
    }
    public void writeValue(final JsonGenerator jsonGenerator, final Object obj) throws IOException {
        this._assertNotNull("g", jsonGenerator);
        final SerializationConfig serializationConfig = this._serializationConfig;
        if (serializationConfig.isEnabled(SerializationFeature.INDENT_OUTPUT) && null == jsonGenerator.getPrettyPrinter()) {
            jsonGenerator.setPrettyPrinter(serializationConfig.constructDefaultPrettyPrinter());
        }
        if (serializationConfig.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            this._writeCloseableValue(jsonGenerator, obj, serializationConfig);
            return;
        }
        this._serializerProvider(serializationConfig).serializeValue(jsonGenerator, obj);
        if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }
    public void writeTree(final JsonGenerator jsonGenerator, final TreeNode treeNode) throws IOException {
        this._assertNotNull("g", jsonGenerator);
        final SerializationConfig serializationConfig = this._serializationConfig;
        this._serializerProvider(serializationConfig).serializeValue(jsonGenerator, treeNode);
        if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }
    public void writeTree(final JsonGenerator jsonGenerator, final JsonNode jsonNode) throws IOException {
        this._assertNotNull("g", jsonGenerator);
        final SerializationConfig serializationConfig = this._serializationConfig;
        this._serializerProvider(serializationConfig).serializeValue(jsonGenerator, jsonNode);
        if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }
    public ObjectNode createObjectNode() {
        return _deserializationConfig.getNodeFactory().objectNode();
    }
    public ArrayNode createArrayNode() {
        return _deserializationConfig.getNodeFactory().arrayNode();
    }
    public JsonNode missingNode() {return _deserializationConfig.getNodeFactory().missingNode();
    }
    public JsonNode nullNode() {
        return _deserializationConfig.getNodeFactory().m827nullNode();
    }
    public JsonParser treeAsTokens(final TreeNode treeNode) {
        this._assertNotNull("n", treeNode);
        return new TreeTraversingParser((JsonNode) treeNode, this);
    }
    public <T> T treeToValue(final TreeNode treeNode, final Class<T> cls) throws JsonProcessingException, IllegalArgumentException {
        final T t;
        if (treeNode==null) {
            return null;
        }
        try {
            return (TreeNode.class.isAssignableFrom(cls) && cls.isAssignableFrom(treeNode.getClass())) ? (T) treeNode : (JsonToken.VALUE_EMBEDDED_OBJECT == treeNode.asToken() && (treeNode instanceof POJONode) && (null == (t = (T) ((POJONode) treeNode).getPojo()) || cls.isInstance(t))) ? t : this.readValue(this.treeAsTokens(treeNode), cls);
        } catch (final JsonProcessingException e2) {
            throw e2;
        } catch (final IOException e3) {
            throw new IllegalArgumentException(e3.getMessage(), e3);
        }
    }
    public <T extends JsonNode> T valueToTree(final Object obj) throws IllegalArgumentException {
        if (null == obj) {
            return (T) this.getNodeFactory().m827nullNode();
        }
        TokenBuffer tokenBuffer = new TokenBuffer(this, false);
        if (this.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            tokenBuffer = tokenBuffer.forceUseOfBigDecimal(true);
        }
        try {
            this.writeValue(tokenBuffer, obj);
            final JsonParser jsonParserAsParser = tokenBuffer.asParser();
            final T t = this.readTree(jsonParserAsParser);
            jsonParserAsParser.close();
            return t;
        } catch (final IOException e2) {
            throw new IllegalArgumentException(e2.getMessage(), e2);
        }
    }
    public boolean canSerialize(final Class<?> cls) {
        return this._serializerProvider(this._serializationConfig).hasSerializerFor(cls, null);
    }
    public boolean canSerialize(final Class<?> cls, final AtomicReference<Throwable> atomicReference) {
        return this._serializerProvider(this._serializationConfig).hasSerializerFor(cls, atomicReference);
    }
    public boolean canDeserialize(final JavaType javaType) {
        return this.createDeserializationContext(null, this._deserializationConfig).hasValueDeserializerFor(javaType, null);
    }
    public boolean canDeserialize(final JavaType javaType, final AtomicReference<Throwable> atomicReference) {
        return this.createDeserializationContext(null, this._deserializationConfig).hasValueDeserializerFor(javaType, atomicReference);
    }
    public <T> T readValue(final File file, final Class<T> cls) throws IOException {
        this._assertNotNull("src", file);
        return (T) this._readMapAndClose(_jsonFactory.createParser(file), _typeFactory.constructType(cls));
    }
    public <T> T readValue(final File file, final TypeReference<T> typeReference) throws IOException {
        this._assertNotNull("src", file);
        return (T) this._readMapAndClose(_jsonFactory.createParser(file), _typeFactory.constructType((TypeReference<?>) typeReference));
    }
    public <T> T readValue(final File file, final JavaType javaType) throws IOException {
        this._assertNotNull("src", file);
        return (T) this._readMapAndClose(_jsonFactory.createParser(file), javaType);
    }
    public <T> T readValue(final URL url, final Class<T> cls) throws IOException {
        this._assertNotNull("src", url);
        return (T) this._readMapAndClose(_jsonFactory.createParser(url), _typeFactory.constructType(cls));
    }
    public <T> T readValue(final URL url, final TypeReference<T> typeReference) throws IOException {
        this._assertNotNull("src", url);
        return (T) this._readMapAndClose(_jsonFactory.createParser(url), _typeFactory.constructType((TypeReference<?>) typeReference));
    }
    public <T> T readValue(final URL url, final JavaType javaType) throws IOException {
        this._assertNotNull("src", url);
        return (T) this._readMapAndClose(_jsonFactory.createParser(url), javaType);
    }
    public <T> T readValue(final String str, final Class<T> cls) throws JsonProcessingException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, str);
        return this.readValue(str, _typeFactory.constructType(cls));
    }
    public <T> T readValue(final String str, final TypeReference<T> typeReference) throws JsonProcessingException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, str);
        return this.readValue(str, _typeFactory.constructType(typeReference));
    }
    public <T> T readValue(final String str, final JavaType javaType) throws JsonProcessingException {
        this._assertNotNull(FirebaseAnalytics.Param.CONTENT, str);
        try {
            return (T) this._readMapAndClose(_jsonFactory.createParser(str), javaType);
        } catch (final JsonProcessingException e2) {
            throw e2;
        } catch (final IOException e3) {
            throw JsonMappingException.fromUnexpectedIOE(e3);
        }
    }
    public <T> T readValue(final Reader reader, final Class<T> cls) throws IOException {
        this._assertNotNull("src", reader);
        return (T) this._readMapAndClose(_jsonFactory.createParser(reader), _typeFactory.constructType(cls));
    }
    public <T> T readValue(final Reader reader, final TypeReference<T> typeReference) throws IOException {
        this._assertNotNull("src", reader);
        return (T) this._readMapAndClose(_jsonFactory.createParser(reader), _typeFactory.constructType((TypeReference<?>) typeReference));
    }
    public <T> T readValue(final Reader reader, final JavaType javaType) throws IOException {
        this._assertNotNull("src", reader);
        return (T) this._readMapAndClose(_jsonFactory.createParser(reader), javaType);
    }
    public <T> T readValue(final InputStream inputStream, final Class<T> cls) throws IOException {
        this._assertNotNull("src", inputStream);
        return (T) this._readMapAndClose(_jsonFactory.createParser(inputStream), _typeFactory.constructType(cls));
    }
    public <T> T readValue(final InputStream inputStream, final TypeReference<T> typeReference) throws IOException {
        this._assertNotNull("src", inputStream);
        return (T) this._readMapAndClose(_jsonFactory.createParser(inputStream), _typeFactory.constructType((TypeReference<?>) typeReference));
    }
    public <T> T readValue(final InputStream inputStream, final JavaType javaType) throws IOException {
        this._assertNotNull("src", inputStream);
        return (T) this._readMapAndClose(_jsonFactory.createParser(inputStream), javaType);
    }
    public <T> T readValue(final byte[] bArr, final Class<T> cls) throws IOException {
        this._assertNotNull("src", bArr);
        return (T) this._readMapAndClose(_jsonFactory.createParser(bArr), _typeFactory.constructType(cls));
    }
    public <T> T readValue(final byte[] bArr, final int i2, final int i3, final Class<T> cls) throws IOException {
        this._assertNotNull("src", bArr);
        return (T) this._readMapAndClose(_jsonFactory.createParser(bArr, i2, i3), _typeFactory.constructType(cls));
    }
    public <T> T readValue(final byte[] bArr, final TypeReference<T> typeReference) throws IOException {
        this._assertNotNull("src", bArr);
        return (T) this._readMapAndClose(_jsonFactory.createParser(bArr), _typeFactory.constructType((TypeReference<?>) typeReference));
    }
    public <T> T readValue(final byte[] bArr, final int i2, final int i3, final TypeReference<T> typeReference) throws IOException {
        this._assertNotNull("src", bArr);
        return (T) this._readMapAndClose(_jsonFactory.createParser(bArr, i2, i3), _typeFactory.constructType((TypeReference<?>) typeReference));
    }
    public <T> T readValue(final byte[] bArr, final JavaType javaType) throws IOException {
        this._assertNotNull("src", bArr);
        return (T) this._readMapAndClose(_jsonFactory.createParser(bArr), javaType);
    }
    public <T> T readValue(final byte[] bArr, final int i2, final int i3, final JavaType javaType) throws IOException {
        this._assertNotNull("src", bArr);
        return (T) this._readMapAndClose(_jsonFactory.createParser(bArr, i2, i3), javaType);
    }
    public <T> T readValue(final DataInput dataInput, final Class<T> cls) throws IOException {
        this._assertNotNull("src", dataInput);
        return (T) this._readMapAndClose(_jsonFactory.createParser(dataInput), _typeFactory.constructType(cls));
    }
    public <T> T readValue(final DataInput dataInput, final JavaType javaType) throws IOException {
        this._assertNotNull("src", dataInput);
        return (T) this._readMapAndClose(_jsonFactory.createParser(dataInput), javaType);
    }
    public void writeValue(final File file, final Object obj) throws IOException {
        this._writeValueAndClose(this.createGenerator(file, JsonEncoding.UTF8), obj);
    }
    public void writeValue(final OutputStream outputStream, final Object obj) throws IOException {
        this._writeValueAndClose(this.createGenerator(outputStream, JsonEncoding.UTF8), obj);
    }
    public void writeValue(final DataOutput dataOutput, final Object obj) throws IOException {
        this._writeValueAndClose(this.createGenerator(dataOutput), obj);
    }
    public void writeValue(final Writer writer, final Object obj) throws IOException {
        this._writeValueAndClose(this.createGenerator(writer), obj);
    }
    public String writeValueAsString(final Object obj) throws JsonProcessingException {
        final SegmentedStringWriter segmentedStringWriter = new SegmentedStringWriter(_jsonFactory._getBufferRecycler());
        try {
            this._writeValueAndClose(this.createGenerator(segmentedStringWriter), obj);
            return segmentedStringWriter.getAndClear();
        } catch (final JsonProcessingException e2) {
            throw e2;
        } catch (final IOException e3) {
            throw JsonMappingException.fromUnexpectedIOE(e3);
        }
    }
    public byte[] writeValueAsBytes(final Object obj) throws JsonProcessingException {
        final ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(_jsonFactory._getBufferRecycler());
        try {
            this._writeValueAndClose(this.createGenerator(byteArrayBuilder, JsonEncoding.UTF8), obj);
            final byte[] byteArray = byteArrayBuilder.toByteArray();
            byteArrayBuilder.release();
            return byteArray;
        } catch (final JsonProcessingException e2) {
            throw e2;
        } catch (final IOException e3) {
            throw JsonMappingException.fromUnexpectedIOE(e3);
        }
    }
    public ObjectWriter writer() {
        return this._newWriter(this._serializationConfig);
    }
    public ObjectWriter writer(final SerializationFeature serializationFeature) {
        return this._newWriter(this._serializationConfig.with(serializationFeature));
    }
    public ObjectWriter writer(final SerializationFeature serializationFeature, final SerializationFeature... serializationFeatureArr) {
        return this._newWriter(this._serializationConfig.with(serializationFeature, serializationFeatureArr));
    }
    public ObjectWriter writer(final DateFormat dateFormat) {
        return this._newWriter(this._serializationConfig.with(dateFormat));
    }
    public ObjectWriter writerWithView(final Class<?> cls) {
        return this._newWriter((SerializationConfig) this._serializationConfig.withView(cls));
    }
    public ObjectWriter writerFor(final Class<?> cls) {
        return this._newWriter(this._serializationConfig, null == cls ? null : _typeFactory.constructType(cls), null);
    }
    public ObjectWriter writerFor(final TypeReference<?> typeReference) {
        return this._newWriter(this._serializationConfig, null == typeReference ? null : _typeFactory.constructType(typeReference), null);
    }
    public ObjectWriter writerFor(final JavaType javaType) {
        return this._newWriter(this._serializationConfig, javaType, null);
    }
    public ObjectWriter writer(PrettyPrinter prettyPrinter) {
        if (null == prettyPrinter) prettyPrinter = ObjectWriter.NULL_PRETTY_PRINTER;
        return this._newWriter(this._serializationConfig, null, prettyPrinter);
    }
    public ObjectWriter writerWithDefaultPrettyPrinter() {
        final SerializationConfig serializationConfig = this._serializationConfig;
        return this._newWriter(serializationConfig, null, serializationConfig.getDefaultPrettyPrinter());
    }
    public ObjectWriter writer(final FilterProvider filterProvider) {
        return this._newWriter(this._serializationConfig.withFilters(filterProvider));
    }
    public ObjectWriter writer(final FormatSchema formatSchema) {
        this._verifySchemaType(formatSchema);
        return this._newWriter(this._serializationConfig, formatSchema);
    }
    public ObjectWriter writer(final Base64Variant base64Variant) {
        return this._newWriter(this._serializationConfig.with(base64Variant));
    }
    public ObjectWriter writer(final CharacterEscapes characterEscapes) {
        return this._newWriter(this._serializationConfig).with(characterEscapes);
    }
    public ObjectWriter writer(final ContextAttributes contextAttributes) {
        return this._newWriter(this._serializationConfig.with(contextAttributes));
    }
    public ObjectWriter writerWithType(final Class<?> cls) {
        return this._newWriter(this._serializationConfig, null == cls ? null : _typeFactory.constructType(cls), null);
    }
    public ObjectWriter writerWithType(final TypeReference<?> typeReference) {
        return this._newWriter(this._serializationConfig, null == typeReference ? null : _typeFactory.constructType(typeReference), null);
    }
    public ObjectWriter writerWithType(final JavaType javaType) {
        return this._newWriter(this._serializationConfig, javaType, null);
    }
    public ObjectReader reader() {
        return this._newReader(this._deserializationConfig).with(_injectableValues);
    }
    public ObjectReader reader(final DeserializationFeature deserializationFeature) {
        return this._newReader(this._deserializationConfig.with(deserializationFeature));
    }
    public ObjectReader reader(final DeserializationFeature deserializationFeature, final DeserializationFeature... deserializationFeatureArr) {
        return this._newReader(this._deserializationConfig.with(deserializationFeature, deserializationFeatureArr));
    }
    public ObjectReader readerForUpdating(final Object obj) {
        return this._newReader(this._deserializationConfig, _typeFactory.constructType(obj.getClass()), obj, null, _injectableValues);
    }
    public ObjectReader readerFor(final JavaType javaType) {
        return this._newReader(this._deserializationConfig, javaType, null, null, _injectableValues);
    }
    public ObjectReader readerFor(final Class<?> cls) {
        return this._newReader(this._deserializationConfig, _typeFactory.constructType(cls), null, null, _injectableValues);
    }
    public ObjectReader readerFor(final TypeReference<?> typeReference) {
        return this._newReader(this._deserializationConfig, _typeFactory.constructType(typeReference), null, null, _injectableValues);
    }
    public ObjectReader readerForArrayOf(final Class<?> cls) {
        return this._newReader(this._deserializationConfig, _typeFactory.constructArrayType(cls), null, null, _injectableValues);
    }
    public ObjectReader readerForListOf(final Class<?> cls) {
        return this._newReader(this._deserializationConfig, _typeFactory.constructCollectionType(List.class, cls), null, null, _injectableValues);
    }
    public ObjectReader readerForMapOf(final Class<?> cls) {
        return this._newReader(this._deserializationConfig, _typeFactory.constructMapType(Map.class, String.class, cls), null, null, _injectableValues);
    }
    public ObjectReader reader(final JsonNodeFactory jsonNodeFactory) {
        return this._newReader(this._deserializationConfig).with(jsonNodeFactory);
    }
    public ObjectReader reader(final FormatSchema formatSchema) {
        this._verifySchemaType(formatSchema);
        return this._newReader(this._deserializationConfig, null, null, formatSchema, _injectableValues);
    }
    public ObjectReader reader(final InjectableValues injectableValues) {
        return this._newReader(this._deserializationConfig, null, null, null, injectableValues);
    }
    public ObjectReader readerWithView(final Class<?> cls) {
        return this._newReader(this._deserializationConfig.withView(cls));
    }
    public ObjectReader reader(final Base64Variant base64Variant) {
        return this._newReader(this._deserializationConfig.with(base64Variant));
    }
    public ObjectReader reader(final ContextAttributes contextAttributes) {
        return this._newReader(this._deserializationConfig.with(contextAttributes));
    }
    public ObjectReader reader(final JavaType javaType) {
        return this._newReader(this._deserializationConfig, javaType, null, null, _injectableValues);
    }
    public ObjectReader reader(final Class<?> cls) {
        return this._newReader(this._deserializationConfig, _typeFactory.constructType(cls), null, null, _injectableValues);
    }
    public ObjectReader reader(final TypeReference<?> typeReference) {
        return this._newReader(this._deserializationConfig, _typeFactory.constructType(typeReference), null, null, _injectableValues);
    }
    public <T> T convertValue(final Object obj, final Class<T> cls) throws IllegalArgumentException {
        return (T) this._convert(obj, _typeFactory.constructType(cls));
    }
    public <T> T convertValue(final Object obj, final TypeReference<T> typeReference) throws IllegalArgumentException {
        return (T) this._convert(obj, _typeFactory.constructType(typeReference));
    }
    public <T> T convertValue(final Object obj, final JavaType javaType) throws IllegalArgumentException {
        return (T) this._convert(obj, javaType);
    }
    protected Object _convert(final Object obj, final JavaType javaType) throws IllegalArgumentException {
        final Object objDeserialize;
        TokenBuffer tokenBuffer = new TokenBuffer(this, false);
        if (this.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            tokenBuffer = tokenBuffer.forceUseOfBigDecimal(true);
        }
        try {
            this._serializerProvider(this._serializationConfig.without(SerializationFeature.WRAP_ROOT_VALUE)).serializeValue(tokenBuffer, obj);
            final JsonParser jsonParserAsParser = tokenBuffer.asParser();
            final DeserializationConfig deserializationConfig = this._deserializationConfig;
            final JsonToken jsonToken_initForReading = this._initForReading(jsonParserAsParser, javaType);
            if (JsonToken.VALUE_NULL == jsonToken_initForReading) {
                final DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext = this.createDeserializationContext(jsonParserAsParser, deserializationConfig);
                objDeserialize = this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext, javaType).getNullValue(defaultDeserializationContextCreateDeserializationContext);
            } else if (JsonToken.END_ARRAY == jsonToken_initForReading || JsonToken.END_OBJECT == jsonToken_initForReading) {
                objDeserialize = null;
            } else {
                final DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext2 = this.createDeserializationContext(jsonParserAsParser, deserializationConfig);
                objDeserialize = this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext2, javaType).deserialize(jsonParserAsParser, defaultDeserializationContextCreateDeserializationContext2);
            }
            jsonParserAsParser.close();
            return objDeserialize;
        } catch (final IOException e2) {
            throw new IllegalArgumentException(e2.getMessage(), e2);
        }
    }
    public <T> T updateValue(final T t, final Object obj) throws JsonMappingException {
        if (null == t || null == obj) {
            return t;
        }
        TokenBuffer tokenBuffer = new TokenBuffer(this, false);
        if (this.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            tokenBuffer = tokenBuffer.forceUseOfBigDecimal(true);
        }
        try {
            this._serializerProvider(this._serializationConfig.without(SerializationFeature.WRAP_ROOT_VALUE)).serializeValue(tokenBuffer, obj);
            final JsonParser jsonParserAsParser = tokenBuffer.asParser();
            final T t2 = this.readerForUpdating(t).readValue(jsonParserAsParser);
            jsonParserAsParser.close();
            return t2;
        } catch (final IOException e2) {
            if (e2 instanceof JsonMappingException) {
                throw ((JsonMappingException) e2);
            }
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }
    public JsonSchema generateJsonSchema(final Class<?> cls) throws JsonMappingException {
        return this._serializerProvider(this._serializationConfig).generateJsonSchema(cls);
    }
    public void acceptJsonFormatVisitor(final Class<?> cls, final JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        this.acceptJsonFormatVisitor(_typeFactory.constructType(cls), jsonFormatVisitorWrapper);
    }
    public void acceptJsonFormatVisitor(final JavaType javaType, final JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        if (null == javaType) {
            throw new IllegalArgumentException("type must be provided");
        }
        this._serializerProvider(this._serializationConfig).acceptJsonFormatVisitor(javaType, jsonFormatVisitorWrapper);
    }
    protected TypeResolverBuilder<?> _constructDefaultTypeResolverBuilder(final DefaultTyping defaultTyping, final PolymorphicTypeValidator polymorphicTypeValidator) {
        return DefaultTypeResolverBuilder.construct(defaultTyping, polymorphicTypeValidator);
    }
    protected DefaultSerializerProvider _serializerProvider(final SerializationConfig serializationConfig) {
        return _serializerProvider.createInstance(serializationConfig, _serializerFactory);
    }
    protected final void _writeValueAndClose(final JsonGenerator jsonGenerator, final Object obj) throws IOException {
        final SerializationConfig serializationConfig = this._serializationConfig;
        if (serializationConfig.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            this._writeCloseable(jsonGenerator, obj, serializationConfig);
            return;
        }
        try {
            this._serializerProvider(serializationConfig).serializeValue(jsonGenerator, obj);
            jsonGenerator.close();
        } catch (final Exception e2) {
            ClassUtil.closeOnFailAndThrowAsIOE(jsonGenerator, e2);
        }
    }
    private void _writeCloseable(final JsonGenerator jsonGenerator, final Object obj, final SerializationConfig serializationConfig) throws IOException {
        Closeable closeable = (Closeable) obj;
        Exception e;
        try {
            this._serializerProvider(serializationConfig).serializeValue(jsonGenerator, obj);
            try {
                closeable.close();
                jsonGenerator.close();
            } catch (final Exception e2) {
                e = e2;
                closeable = null;
                ClassUtil.closeOnFailAndThrowAsIOE(jsonGenerator, closeable, e);
            }
        } catch (final Exception e3) {
            e = e3;
        }
    }
    private void _writeCloseableValue(final JsonGenerator jsonGenerator, final Object obj, final SerializationConfig serializationConfig) throws IOException {
        final Closeable closeable = (Closeable) obj;
        try {
            this._serializerProvider(serializationConfig).serializeValue(jsonGenerator, obj);
            if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator.flush();
            }
            closeable.close();
        } catch (final Exception e2) {
            ClassUtil.closeOnFailAndThrowAsIOE(null, closeable, e2);
        }
    }
    protected final void _configAndWriteValue(final JsonGenerator jsonGenerator, final Object obj) throws IOException {
        this._serializationConfig.initialize(jsonGenerator);
        this._writeValueAndClose(jsonGenerator, obj);
    }
    protected Object _readValue(final DeserializationConfig deserializationConfig, final JsonParser jsonParser, final JavaType javaType) throws IOException {
        final Object rootValue;
        final JsonToken jsonToken_initForReading = this._initForReading(jsonParser, javaType);
        final DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext = this.createDeserializationContext(jsonParser, deserializationConfig);
        if (JsonToken.VALUE_NULL == jsonToken_initForReading) {
            rootValue = this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext, javaType).getNullValue(defaultDeserializationContextCreateDeserializationContext);
        } else {
            rootValue = (JsonToken.END_ARRAY == jsonToken_initForReading || JsonToken.END_OBJECT == jsonToken_initForReading) ? null : defaultDeserializationContextCreateDeserializationContext.readRootValue(jsonParser, javaType, this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext, javaType), null);
        }
        jsonParser.clearCurrentToken();
        if (deserializationConfig.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
            this._verifyNoTrailingTokens(jsonParser, defaultDeserializationContextCreateDeserializationContext, javaType);
        }
        return rootValue;
    }
    protected Object _readMapAndClose(final JsonParser jsonParser, final JavaType javaType) throws IOException {
        final Object rootValue;
        try {
            final DeserializationConfig deserializationConfig = this._deserializationConfig;
            final DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext = this.createDeserializationContext(jsonParser, deserializationConfig);
            final JsonToken jsonToken_initForReading = this._initForReading(jsonParser, javaType);
            if (JsonToken.VALUE_NULL == jsonToken_initForReading) {
                rootValue = this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext, javaType).getNullValue(defaultDeserializationContextCreateDeserializationContext);
            } else if (JsonToken.END_ARRAY == jsonToken_initForReading || JsonToken.END_OBJECT == jsonToken_initForReading) {
                rootValue = null;
            } else {
                rootValue = defaultDeserializationContextCreateDeserializationContext.readRootValue(jsonParser, javaType, this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext, javaType), null);
                defaultDeserializationContextCreateDeserializationContext.checkUnresolvedObjectId();
            }
            if (deserializationConfig.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
                this._verifyNoTrailingTokens(jsonParser, defaultDeserializationContextCreateDeserializationContext, javaType);
            }
            if (null != jsonParser) {
                jsonParser.close();
            }
            return rootValue;
        } catch (final Throwable th) {
            try {
                throw th;
            } catch (final Throwable th2) {
                if (null != jsonParser) {
                    try {
                        jsonParser.close();
                    } catch (final Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
    protected JsonNode _readTreeAndClose(final JsonParser jsonParser) throws IOException {
        final JsonNode jsonNodeM827nullNode;
        try {
            final JavaType javaTypeConstructType = this.constructType(JsonNode.class);
            final DeserializationConfig deserializationConfig = this._deserializationConfig;
            deserializationConfig.initialize(jsonParser);
            JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
            if (null == jsonTokenCurrentToken && null == (jsonTokenCurrentToken = jsonParser.nextToken())) {
                final JsonNode jsonNodeMissingNode = deserializationConfig.getNodeFactory().missingNode();
                jsonParser.close();
                return jsonNodeMissingNode;
            }
            final DefaultDeserializationContext defaultDeserializationContextCreateDeserializationContext = this.createDeserializationContext(jsonParser, deserializationConfig);
            if (JsonToken.VALUE_NULL == jsonTokenCurrentToken) {
                jsonNodeM827nullNode = deserializationConfig.getNodeFactory().m827nullNode();
            } else {
                jsonNodeM827nullNode = (JsonNode) defaultDeserializationContextCreateDeserializationContext.readRootValue(jsonParser, javaTypeConstructType, this._findRootDeserializer(defaultDeserializationContextCreateDeserializationContext, javaTypeConstructType), null);
            }
            if (deserializationConfig.isEnabled(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)) {
                this._verifyNoTrailingTokens(jsonParser, defaultDeserializationContextCreateDeserializationContext, javaTypeConstructType);
            }
            jsonParser.close();
            return jsonNodeM827nullNode;
        } catch (final Throwable th) {
            try {
                throw th;
            } catch (final Throwable th2) {
                if (null != jsonParser) {
                    try {
                        jsonParser.close();
                    } catch (final Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
    protected DefaultDeserializationContext createDeserializationContext(final JsonParser jsonParser, final DeserializationConfig deserializationConfig) {
        return _deserializationContext.createInstance(deserializationConfig, jsonParser, _injectableValues);
    }
    protected JsonToken _initForReading(final JsonParser jsonParser, final JavaType javaType) throws IOException {
        _deserializationConfig.initialize(jsonParser);
        JsonToken jsonTokenCurrentToken = jsonParser.currentToken();
        if (null == jsonTokenCurrentToken && null == (jsonTokenCurrentToken = jsonParser.nextToken())) {
            throw MismatchedInputException.from(jsonParser, javaType, "No content to map due to end-of-input");
        }
        return jsonTokenCurrentToken;
    }
    protected JsonToken _initForReading(final JsonParser jsonParser) throws IOException {
        return this._initForReading(jsonParser, null);
    }
    protected final void _verifyNoTrailingTokens(final JsonParser jsonParser, final DeserializationContext deserializationContext, final JavaType javaType) throws IOException {
        final JsonToken jsonTokenNextToken = jsonParser.nextToken();
        if (null != jsonTokenNextToken) {
            deserializationContext.reportTrailingTokens(ClassUtil.rawClass(javaType), jsonParser, jsonTokenNextToken);
        }
    }
    protected JsonDeserializer<Object> _findRootDeserializer(final DeserializationContext deserializationContext, final JavaType javaType) throws JsonMappingException {
        final JsonDeserializer<Object> jsonDeserializer = _rootDeserializers.get(javaType);
        if (null != jsonDeserializer) {
            return jsonDeserializer;
        }
        final JsonDeserializer<Object> jsonDeserializerFindRootValueDeserializer = deserializationContext.findRootValueDeserializer(javaType);
        if (null == jsonDeserializerFindRootValueDeserializer) {
            return deserializationContext.reportBadDefinition(javaType, "Cannot find a deserializer for type " + javaType);
        }
        _rootDeserializers.put(javaType, jsonDeserializerFindRootValueDeserializer);
        return jsonDeserializerFindRootValueDeserializer;
    }
    protected void _verifySchemaType(final FormatSchema formatSchema) {
        if (null == formatSchema || _jsonFactory.canUseSchema(formatSchema)) {
            return;
        }
        throw new IllegalArgumentException("Cannot use FormatSchema of type " + formatSchema.getClass().getName() + " for format " + _jsonFactory.getFormatName());
    }
    protected final void _assertNotNull(final String str, final Object obj) {
        if (null == obj) {
            throw new IllegalArgumentException(String.format("argument \"%s\" is null", str));
        }
    }
}
