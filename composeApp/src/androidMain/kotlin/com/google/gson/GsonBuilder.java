/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.*;
import com.google.gson.internal.sql.SqlTypesSupport;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.proje.mobilesales.core.reportparser.ReportLayoutItem;
import com.proje.mobilesales.core.utils.RuntimeTypeAdapterFactory;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public final class GsonBuilder {
  private static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
  private static final Strictness DEFAULT_STRICTNESS = null;
  private static final FormattingStyle DEFAULT_FORMATTING_STYLE = FormattingStyle.COMPACT;
  private static final boolean DEFAULT_ESCAPE_HTML = true;
  private static final boolean DEFAULT_SERIALIZE_NULLS = false;
  private static final boolean DEFAULT_COMPLEX_MAP_KEYS = false;
  private static final boolean DEFAULT_SPECIALIZE_FLOAT_VALUES = false;
  private static final boolean DEFAULT_USE_JDK_UNSAFE = true;
  private static final String DEFAULT_DATE_PATTERN = null;
  private static final FieldNamingStrategy DEFAULT_FIELD_NAMING_STRATEGY =
      FieldNamingPolicy.IDENTITY;
  private static final ToNumberStrategy DEFAULT_OBJECT_TO_NUMBER_STRATEGY = ToNumberPolicy.DOUBLE;
  private static final ToNumberStrategy DEFAULT_NUMBER_TO_NUMBER_STRATEGY =
      ToNumberPolicy.LAZILY_PARSED_NUMBER;

  static final ConstructorConstructor DEFAULT_CONSTRUCTOR_CONSTRUCTOR =
      new ConstructorConstructor(
          Collections.emptyMap(), DEFAULT_USE_JDK_UNSAFE, Collections.emptyList());

  static final JsonAdapterAnnotationTypeAdapterFactory
      DEFAULT_JSON_ADAPTER_ANNOTATION_TYPE_ADAPTER_FACTORY =
          new JsonAdapterAnnotationTypeAdapterFactory(DEFAULT_CONSTRUCTOR_CONSTRUCTOR);

  static final GsonBuilder DEFAULT = new GsonBuilder();

  static final List<TypeAdapterFactory> DEFAULT_TYPE_ADAPTER_FACTORIES =
          GsonBuilder.DEFAULT.createFactories(
              DEFAULT_CONSTRUCTOR_CONSTRUCTOR, DEFAULT_JSON_ADAPTER_ANNOTATION_TYPE_ADAPTER_FACTORY);

  Excluder excluder = Excluder.DEFAULT;
  LongSerializationPolicy longSerializationPolicy = LongSerializationPolicy.DEFAULT;
  FieldNamingStrategy fieldNamingPolicy = DEFAULT_FIELD_NAMING_STRATEGY;
  final Map<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
  final List<TypeAdapterFactory> factories = new ArrayList<>();

  final List<TypeAdapterFactory> hierarchyFactories = new ArrayList<>();

  boolean serializeNulls = DEFAULT_SERIALIZE_NULLS;
  String datePattern = DEFAULT_DATE_PATTERN;
  int dateStyle = DateFormat.DEFAULT;
  int timeStyle = DateFormat.DEFAULT;
  boolean complexMapKeySerialization = DEFAULT_COMPLEX_MAP_KEYS;
  boolean serializeSpecialFloatingPointValues = DEFAULT_SPECIALIZE_FLOAT_VALUES;
  boolean escapeHtmlChars = DEFAULT_ESCAPE_HTML;
  FormattingStyle formattingStyle = DEFAULT_FORMATTING_STYLE;
  boolean generateNonExecutableJson = DEFAULT_JSON_NON_EXECUTABLE;
  Strictness strictness = DEFAULT_STRICTNESS;
  boolean useJdkUnsafe = DEFAULT_USE_JDK_UNSAFE;
  ToNumberStrategy objectToNumberStrategy = DEFAULT_OBJECT_TO_NUMBER_STRATEGY;
  ToNumberStrategy numberToNumberStrategy = DEFAULT_NUMBER_TO_NUMBER_STRATEGY;
  final ArrayDeque<ReflectionAccessFilter> reflectionFilters = new ArrayDeque<>();

  public GsonBuilder() {}


  GsonBuilder(Gson gson) {
      this.excluder = gson.excluder;
      this.fieldNamingPolicy = gson.fieldNamingStrategy;
      this.instanceCreators.putAll(gson.instanceCreators);
      this.serializeNulls = gson.serializeNulls;
      this.complexMapKeySerialization = gson.complexMapKeySerialization;
      this.generateNonExecutableJson = gson.generateNonExecutableJson;
      this.escapeHtmlChars = gson.htmlSafe;
      this.formattingStyle = gson.formattingStyle;
      this.strictness = gson.strictness;
      this.serializeSpecialFloatingPointValues = gson.serializeSpecialFloatingPointValues;
      this.longSerializationPolicy = gson.longSerializationPolicy;
      this.datePattern = gson.datePattern;
      this.dateStyle = gson.dateStyle;
      this.timeStyle = gson.timeStyle;
      this.factories.addAll(gson.builderFactories);
      this.hierarchyFactories.addAll(gson.builderHierarchyFactories);
      this.useJdkUnsafe = gson.useJdkUnsafe;
      this.objectToNumberStrategy = gson.objectToNumberStrategy;
      this.numberToNumberStrategy = gson.numberToNumberStrategy;
      this.reflectionFilters.addAll(gson.reflectionFilters);
  }

  public GsonBuilder setVersion(double version) {
    if (Double.isNaN(version) || 0.0 > version) {
      throw new IllegalArgumentException("Invalid version: " + version);
    }
      excluder = excluder.withVersion(version);
    return this;
  }

  public GsonBuilder excludeFieldsWithModifiers(int... modifiers) {
    Objects.requireNonNull(modifiers);
      excluder = excluder.withModifiers(modifiers);
    return this;
  }

  public GsonBuilder generateNonExecutableJson() {
      this.generateNonExecutableJson = true;
    return this;
  }

  public GsonBuilder excludeFieldsWithoutExposeAnnotation() {
      excluder = excluder.excludeFieldsWithoutExposeAnnotation();
    return this;
  }

  public GsonBuilder serializeNulls() {
      this.serializeNulls = true;
    return this;
  }

  public GsonBuilder enableComplexMapKeySerialization() {
      complexMapKeySerialization = true;
    return this;
  }

  public GsonBuilder disableInnerClassSerialization() {
      excluder = excluder.disableInnerClassSerialization();
    return this;
  }

  public GsonBuilder setLongSerializationPolicy(LongSerializationPolicy serializationPolicy) {
      this.longSerializationPolicy = Objects.requireNonNull(serializationPolicy);
    return this;
  }

  public GsonBuilder setFieldNamingPolicy(FieldNamingPolicy namingConvention) {
    return setFieldNamingStrategy(namingConvention);
  }

  public GsonBuilder setFieldNamingStrategy(FieldNamingStrategy fieldNamingStrategy) {
      this.fieldNamingPolicy = Objects.requireNonNull(fieldNamingStrategy);
    return this;
  }

  public GsonBuilder setObjectToNumberStrategy(ToNumberStrategy objectToNumberStrategy) {
    this.objectToNumberStrategy = Objects.requireNonNull(objectToNumberStrategy);
    return this;
  }

  public GsonBuilder setNumberToNumberStrategy(ToNumberStrategy numberToNumberStrategy) {
    this.numberToNumberStrategy = Objects.requireNonNull(numberToNumberStrategy);
    return this;
  }

  public GsonBuilder setExclusionStrategies(ExclusionStrategy... strategies) {
    Objects.requireNonNull(strategies);
    for (ExclusionStrategy strategy : strategies) {
        excluder = excluder.withExclusionStrategy(strategy, true, true);
    }
    return this;
  }

  public GsonBuilder addSerializationExclusionStrategy(ExclusionStrategy strategy) {
    Objects.requireNonNull(strategy);
      excluder = excluder.withExclusionStrategy(strategy, true, false);
    return this;
  }

  public GsonBuilder addDeserializationExclusionStrategy(ExclusionStrategy strategy) {
    Objects.requireNonNull(strategy);
      excluder = excluder.withExclusionStrategy(strategy, false, true);
    return this;
  }

  public GsonBuilder setPrettyPrinting() {
    return setFormattingStyle(FormattingStyle.PRETTY);
  }


  public GsonBuilder setFormattingStyle(FormattingStyle formattingStyle) {
    this.formattingStyle = Objects.requireNonNull(formattingStyle);
    return this;
  }

  public GsonBuilder setLenient() {
    return setStrictness(Strictness.LENIENT);
  }

  public GsonBuilder setStrictness(Strictness strictness) {
    this.strictness = Objects.requireNonNull(strictness);
    return this;
  }

  public GsonBuilder disableHtmlEscaping() {
      this.escapeHtmlChars = false;
    return this;
  }

  public GsonBuilder setDateFormat(String pattern) {
    if (null != pattern) {
      try {
        SimpleDateFormat unused = new SimpleDateFormat(pattern);
      } catch (IllegalArgumentException e) {
        // Throw exception if it is an invalid date format
        throw new IllegalArgumentException("The date pattern '" + pattern + "' is not valid", e);
      }
    }
      this.datePattern = pattern;
    return this;
  }

  public GsonBuilder setDateFormat(int dateStyle) {
    this.dateStyle = checkDateFormatStyle(dateStyle);
      this.datePattern = null;
    return this;
  }

  public GsonBuilder setDateFormat(int dateStyle, int timeStyle) {
    this.dateStyle = checkDateFormatStyle(dateStyle);
    this.timeStyle = checkDateFormatStyle(timeStyle);
      this.datePattern = null;
    return this;
  }

  private static int checkDateFormatStyle(int style) {

    if (0 > style || 3 < style) {
      throw new IllegalArgumentException("Invalid style: " + style);
    }
    return style;
  }

  public GsonBuilder registerTypeAdapter(Type type, Object typeAdapter) {
    Objects.requireNonNull(type);
    Objects.requireNonNull(typeAdapter);
    if (!(typeAdapter instanceof JsonSerializer<?>
        || typeAdapter instanceof JsonDeserializer<?>
        || typeAdapter instanceof InstanceCreator<?>
        || typeAdapter instanceof TypeAdapter<?>)) {
      throw new IllegalArgumentException(
          "Class "
              + typeAdapter.getClass().getName()
              + " does not implement any supported type adapter class or interface");
    }

    if (hasNonOverridableAdapter(type)) {
      throw new IllegalArgumentException("Cannot override built-in adapter for " + type);
    }

    if (typeAdapter instanceof InstanceCreator<?>) {
        instanceCreators.put(type, (InstanceCreator<?>) typeAdapter);
    }
    if (typeAdapter instanceof JsonSerializer<?> || typeAdapter instanceof JsonDeserializer<?>) {
      TypeToken<?> typeToken = TypeToken.get(type);
        factories.add(TreeTypeAdapter.newFactoryWithMatchRawType(typeToken, typeAdapter));
    }
    if (typeAdapter instanceof TypeAdapter<?>) {
      @SuppressWarnings({"unchecked", "rawtypes"}) TypeAdapterFactory factory =
          TypeAdapters.newFactory(TypeToken.get(type), (TypeAdapter) typeAdapter);
        factories.add(factory);
    }
    return this;
  }

  private static boolean hasNonOverridableAdapter(Type type) {
    return Object.class == type;

  }

  public GsonBuilder registerTypeAdapterFactory(RuntimeTypeAdapterFactory<ReportLayoutItem> factory) {
    Objects.requireNonNull(factory);
      factories.add(factory);
    return this;
  }

  public GsonBuilder registerTypeHierarchyAdapter(Class<?> baseType, Object typeAdapter) {
    Objects.requireNonNull(baseType);
    Objects.requireNonNull(typeAdapter);
    if (!(typeAdapter instanceof JsonSerializer<?>
        || typeAdapter instanceof JsonDeserializer<?>
        || typeAdapter instanceof TypeAdapter<?>)) {
      throw new IllegalArgumentException(
          "Class "
              + typeAdapter.getClass().getName()
              + " does not implement any supported type adapter class or interface");
    }

    if (typeAdapter instanceof JsonDeserializer || typeAdapter instanceof JsonSerializer) {
        hierarchyFactories.add(TreeTypeAdapter.newTypeHierarchyFactory(baseType, typeAdapter));
    }
    if (typeAdapter instanceof TypeAdapter<?>) {
      @SuppressWarnings({"unchecked", "rawtypes"}) TypeAdapterFactory factory =
          TypeAdapters.newTypeHierarchyFactory(baseType, (TypeAdapter) typeAdapter);
        factories.add(factory);
    }
    return this;
  }

  public GsonBuilder serializeSpecialFloatingPointValues() {
      this.serializeSpecialFloatingPointValues = true;
    return this;
  }

  public GsonBuilder disableJdkUnsafe() {
      this.useJdkUnsafe = false;
    return this;
  }

  public GsonBuilder addReflectionAccessFilter(ReflectionAccessFilter filter) {
    Objects.requireNonNull(filter);
      reflectionFilters.addFirst(filter);
    return this;
  }

  public Gson create() {
    return new Gson(this);
  }

  List<TypeAdapterFactory> createFactories(
          ConstructorConstructor constructorConstructor,
          JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory) {
    ArrayList<TypeAdapterFactory> factories = new ArrayList<>();

    factories.add(TypeAdapters.JSON_ELEMENT_FACTORY);
    factories.add(ObjectTypeAdapter.getFactory(objectToNumberStrategy));

    factories.add(excluder);
      addUserDefinedAdapters(factories);
      addDateTypeAdapters(factories);
    factories.add(TypeAdapters.STRING_FACTORY);
    factories.add(TypeAdapters.INTEGER_FACTORY);
    factories.add(TypeAdapters.BOOLEAN_FACTORY);
    factories.add(TypeAdapters.BYTE_FACTORY);
    factories.add(TypeAdapters.SHORT_FACTORY);
    TypeAdapter<Number> longAdapter = longSerializationPolicy.typeAdapter();
    factories.add(TypeAdapters.newFactory(long.class, Long.class, longAdapter));
    factories.add(TypeAdapters.newFactory(double.class, Double.class, doubleAdapter()));
    factories.add(TypeAdapters.newFactory(float.class, Float.class, floatAdapter()));
    factories.add(NumberTypeAdapter.getFactory(numberToNumberStrategy));
    factories.add(TypeAdapters.ATOMIC_INTEGER_FACTORY);
    factories.add(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
    factories.add(
        TypeAdapters.newFactory(AtomicLong.class, TypeAdapters.atomicLongAdapter(longAdapter)));
    factories.add(
        TypeAdapters.newFactory(
            AtomicLongArray.class, TypeAdapters.atomicLongArrayAdapter(longAdapter)));
    factories.add(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
    factories.add(TypeAdapters.CHARACTER_FACTORY);
    factories.add(TypeAdapters.STRING_BUILDER_FACTORY);
    factories.add(TypeAdapters.STRING_BUFFER_FACTORY);
    factories.add(TypeAdapters.BIG_DECIMAL_FACTORY);
    factories.add(TypeAdapters.BIG_INTEGER_FACTORY);
    factories.add(TypeAdapters.LAZILY_PARSED_NUMBER_FACTORY);
    factories.add(TypeAdapters.URL_FACTORY);
    factories.add(TypeAdapters.URI_FACTORY);
    factories.add(TypeAdapters.UUID_FACTORY);
    factories.add(TypeAdapters.CURRENCY_FACTORY);
    factories.add(TypeAdapters.LOCALE_FACTORY);
    factories.add(TypeAdapters.INET_ADDRESS_FACTORY);
    factories.add(TypeAdapters.BIT_SET_FACTORY);
    factories.add(DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY);
    factories.add(TypeAdapters.CALENDAR_FACTORY);
    TypeAdapterFactory javaTimeFactory = TypeAdapters.javaTimeTypeAdapterFactory();
    if (null != javaTimeFactory) {
      factories.add(javaTimeFactory);
    }
    factories.addAll(SqlTypesSupport.SQL_TYPE_FACTORIES);
    factories.add(ArrayTypeAdapter.FACTORY);
    factories.add(TypeAdapters.CLASS_FACTORY);

    factories.add(new CollectionTypeAdapterFactory(constructorConstructor));
    factories.add(new MapTypeAdapterFactory(constructorConstructor, complexMapKeySerialization));
    factories.add(jsonAdapterFactory);
    factories.add(TypeAdapters.ENUM_FACTORY);
    factories.add(
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
                fieldNamingPolicy,
                excluder,
            jsonAdapterFactory,
                newImmutableList(reflectionFilters)));

    factories.trimToSize();
    return Collections.unmodifiableList(factories);
  }

  static <E> List<E> newImmutableList(Collection<E> collection) {
    if (collection.isEmpty()) {
      return Collections.emptyList();
    }
    if (1 == collection.size()) {
      return Collections.singletonList(
          collection instanceof List
              ? ((List<E>) collection).get(0)
              : collection.iterator().next());
    }
    @SuppressWarnings("unchecked") List<E> list = (List<E>) Collections.unmodifiableList(Arrays.asList(collection.toArray()));
    return list;
  }

  private TypeAdapter<Number> doubleAdapter() {
    return serializeSpecialFloatingPointValues ? TypeAdapters.DOUBLE : TypeAdapters.DOUBLE_STRICT;
  }

  private TypeAdapter<Number> floatAdapter() {
    return serializeSpecialFloatingPointValues ? TypeAdapters.FLOAT : TypeAdapters.FLOAT_STRICT;
  }

  private void addUserDefinedAdapters(List<TypeAdapterFactory> all) {
    if (!this.factories.isEmpty()) {
      List<TypeAdapterFactory> reversedFactories = new ArrayList<>(this.factories);
      Collections.reverse(reversedFactories);
      all.addAll(reversedFactories);
    }

    if (!this.hierarchyFactories.isEmpty()) {
      List<TypeAdapterFactory> reversedHierarchyFactories =
          new ArrayList<>(this.hierarchyFactories);
      Collections.reverse(reversedHierarchyFactories);
      all.addAll(reversedHierarchyFactories);
    }
  }

  private void addDateTypeAdapters(List<TypeAdapterFactory> factories) {
    TypeAdapterFactory dateAdapterFactory;
    boolean sqlTypesSupported = SqlTypesSupport.SUPPORTS_SQL_TYPES;
    TypeAdapterFactory sqlTimestampAdapterFactory = null;
    TypeAdapterFactory sqlDateAdapterFactory = null;

    if (null != this.datePattern && !datePattern.trim().isEmpty()) {
      dateAdapterFactory = DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory(datePattern);

      if (sqlTypesSupported) {
        sqlTimestampAdapterFactory =
            SqlTypesSupport.TIMESTAMP_DATE_TYPE.createAdapterFactory(datePattern);
        sqlDateAdapterFactory = SqlTypesSupport.DATE_DATE_TYPE.createAdapterFactory(datePattern);
      }
    } else if (DateFormat.DEFAULT != this.dateStyle || DateFormat.DEFAULT != this.timeStyle) {
      dateAdapterFactory =
          DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory(dateStyle, timeStyle);

      if (sqlTypesSupported) {
        sqlTimestampAdapterFactory =
            SqlTypesSupport.TIMESTAMP_DATE_TYPE.createAdapterFactory(dateStyle, timeStyle);
        sqlDateAdapterFactory =
            SqlTypesSupport.DATE_DATE_TYPE.createAdapterFactory(dateStyle, timeStyle);
      }
    } else {
      return;
    }

    factories.add(dateAdapterFactory);
    if (sqlTypesSupported) {
      factories.add(sqlTimestampAdapterFactory);
      factories.add(sqlDateAdapterFactory);
    }
  }
}
