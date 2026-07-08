/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal.bind;

import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.*;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.*;
import java.util.*;

/** Type adapter that reflects over the fields and methods of a class. */
public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
  private final ConstructorConstructor constructorConstructor;
  private final FieldNamingStrategy fieldNamingPolicy;
  private final Excluder excluder;
  private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
  private final List<ReflectionAccessFilter> reflectionFilters;

  public ReflectiveTypeAdapterFactory(
          final ConstructorConstructor constructorConstructor,
          final FieldNamingStrategy fieldNamingPolicy,
          final Excluder excluder,
          final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory,
          final List<ReflectionAccessFilter> reflectionFilters) {
    this.constructorConstructor = constructorConstructor;
    this.fieldNamingPolicy = fieldNamingPolicy;
    this.excluder = excluder;
    this.jsonAdapterFactory = jsonAdapterFactory;
    this.reflectionFilters = reflectionFilters;
  }

  private boolean includeField(final Field f, final boolean serialize) {
    return !this.excluder.excludeField(f, serialize);
  }

  /** first element holds the default name */
  @SuppressWarnings("MixedMutabilityReturnType")
  private List<String> getFieldNames(final Field f) {

    final String fieldName;
    final List<String> alternates;
    final SerializedName annotation = f.getAnnotation(SerializedName.class);
    if (null == annotation) {
      fieldName = this.fieldNamingPolicy.translateName(f);
      alternates = this.fieldNamingPolicy.alternateNames(f);
    } else {
      fieldName = annotation.value();
      alternates = Arrays.asList(annotation.alternate());
    }

    if (alternates.isEmpty()) {
      return Collections.singletonList(fieldName);
    }

    final List<String> fieldNames = new ArrayList<>(alternates.size() + 1);
    fieldNames.add(fieldName);
    fieldNames.addAll(alternates);
    return fieldNames;
  }

  
  public <T> TypeAdapters.EnumTypeAdapter create(final Gson gson, final TypeToken<T> type) {
    final Class<? super T> raw = type.getRawType();

    if (!Object.class.isAssignableFrom(raw)) {
      return null; // it's a primitive!
    }

    // Don't allow using reflection on anonymous and local classes because synthetic fields for
    // captured enclosing values make this unreliable
    if (ReflectionHelper.isAnonymousOrNonStaticLocal(raw)) {
      // This adapter just serializes and deserializes null, ignoring the actual values
      // This is done for backward compatibility; troubleshooting-wise it might be better to throw
      // exceptions
      return new TypeAdapter<T>() {
        
        public T read(final JsonReader in) throws IOException {
          in.skipValue();
          return null;
        }

        
        public void write(final JsonWriter out, final T value) throws IOException {
          out.nullValue();
        }

        
        public String toString() {
          return "AnonymousOrNonStaticLocalClassAdapter";
        }
      };
    }

    ReflectionAccessFilter.FilterResult filterResult =
        ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, raw);
    if (filterResult == ReflectionAccessFilter.FilterResult.BLOCK_ALL) {
      throw new JsonIOException(
          "ReflectionAccessFilter does not permit using reflection for "
              + raw
              + ". Register a TypeAdapter for this type or adjust the access filter.");
    }
    boolean blockInaccessible = filterResult == ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE;

    // If the type is actually a Java Record, we need to use the RecordAdapter instead. This will
    // always be false on JVMs that do not support records.
    if (ReflectionHelper.isRecord(raw)) {
      @SuppressWarnings("unchecked")
      TypeAdapter<T> adapter =
          (TypeAdapter<T>)
              new RecordAdapter<>(
                  raw, getBoundFields(gson, type, raw, blockInaccessible, true), blockInaccessible);
      return adapter;
    }

    ObjectConstructor<T> constructor = constructorConstructor.get(type, true);
    return new FieldReflectionAdapter<>(
        constructor, getBoundFields(gson, type, raw, blockInaccessible, false));
  }

  private static <M extends AccessibleObject & Member> void checkAccessible(
      Object object, M member) {
    if (!ReflectionAccessFilterHelper.canAccess(
        member, Modifier.isStatic(member.getModifiers()) ? null : object)) {
      String memberDescription = ReflectionHelper.getAccessibleObjectDescription(member, true);
      throw new JsonIOException(
          memberDescription
              + " is not accessible and ReflectionAccessFilter does not permit making it"
              + " accessible. Register a TypeAdapter for the declaring type, adjust the access"
              + " filter or increase the visibility of the element and its declaring type.");
    }
  }

  private BoundField createBoundField(
      Gson context,
      Field field,
      Method accessor,
      String serializedName,
      TypeToken<?> fieldType,
      boolean serialize,
      boolean blockInaccessible) {

    boolean isPrimitive = Primitives.isPrimitive(fieldType.getRawType());

    int modifiers = field.getModifiers();
    boolean isStaticFinalField = Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);

    JsonAdapter annotation = field.getAnnotation(JsonAdapter.class);
    TypeAdapter<?> mapped = null;
    if (annotation != null) {
      // This is not safe; requires that user has specified correct adapter class for @JsonAdapter
      mapped =
          jsonAdapterFactory.getTypeAdapter(
              constructorConstructor, context, fieldType, annotation, false);
    }
    boolean jsonAdapterPresent = mapped != null;
    if (mapped == null) {
      mapped = context.getAdapter(fieldType);
    }

    @SuppressWarnings("unchecked")
    TypeAdapter<Object> typeAdapter = (TypeAdapter<Object>) mapped;
    TypeAdapter<Object> writeTypeAdapter;
    if (serialize) {
      writeTypeAdapter =
          jsonAdapterPresent
              ? typeAdapter
              : new TypeAdapterRuntimeTypeWrapper<>(context, typeAdapter, fieldType.getType());
    } else {
      // Will never actually be used, but we set it to avoid confusing nullness-analysis tools
      writeTypeAdapter = typeAdapter;
    }
    return new BoundField(serializedName, field) {

      void write(JsonWriter writer, Object source) throws IOException, IllegalAccessException {
        if (blockInaccessible) {
          if (accessor == null) {
            checkAccessible(source, field);
          } else {
            // Note: This check might actually be redundant because access check for canonical
            // constructor should have failed already
            checkAccessible(source, accessor);
          }
        }

        Object fieldValue;
        if (accessor != null) {
          try {
            fieldValue = accessor.invoke(source);
          } catch (InvocationTargetException e) {
            String accessorDescription =
                ReflectionHelper.getAccessibleObjectDescription(accessor, false);
            throw new JsonIOException(
                "Accessor " + accessorDescription + " threw exception", e.getCause());
          }
        } else {
          fieldValue = field.get(source);
        }
        if (fieldValue == source) {
          // avoid direct recursion
          return;
        }
        writer.name(serializedName);
        writeTypeAdapter.write(writer, fieldValue);
      }


      void readIntoArray(JsonReader reader, int index, Object[] target)
          throws IOException, JsonParseException {
        Object fieldValue = typeAdapter.read(reader);
        if (fieldValue == null && isPrimitive) {
          throw new JsonParseException(
              "null is not allowed as value for record component '"
                  + fieldName
                  + "' of primitive type; at path "
                  + reader.getPath());
        }
        target[index] = fieldValue;
      }


      void readIntoField(JsonReader reader, Object target)
          throws IOException, IllegalAccessException {
        Object fieldValue = typeAdapter.read(reader);
        if (fieldValue != null || !isPrimitive) {
          if (blockInaccessible) {
            checkAccessible(target, field);
          } else if (isStaticFinalField) {
            // Reflection does not permit setting value of `static final` field, even after calling
            // `setAccessible`
            // Handle this here to avoid causing IllegalAccessException when calling `Field.set`
            String fieldDescription = ReflectionHelper.getAccessibleObjectDescription(field, false);
            throw new JsonIOException("Cannot set value of 'static final' " + fieldDescription);
          }
            this.field.set(target, fieldValue);
        }
      }
    };
  }

    /**
     * @param deserializedFields Maps from JSON member name to field
     */
    private record FieldsData(Map<String, BoundField> deserializedFields, List<BoundField> serializedFields) {
        static final FieldsData EMPTY = new FieldsData(Collections.emptyMap(), Collections.emptyList());

    }

  private static IllegalArgumentException createDuplicateFieldException(
          final Class<?> declaringType, final String duplicateName, final Field field1, final Field field2) {
    throw new IllegalArgumentException(
        "Class "
            + declaringType.getName()
            + " declares multiple JSON fields named '"
            + duplicateName
            + "'; conflict is caused by fields "
            + ReflectionHelper.fieldToString(field1)
            + " and "
            + ReflectionHelper.fieldToString(field2)
            + "\nSee "
            + TroubleshootingGuide.createUrl("duplicate-fields"));
  }

  private FieldsData getBoundFields(
          final Gson context, TypeToken<?> type, Class<?> raw, boolean blockInaccessible, final boolean isRecord) {
    if (raw.isInterface()) {
      return FieldsData.EMPTY;
    }

    final Map<String, BoundField> deserializedFields = new LinkedHashMap<>();
    // For serialized fields use a Map to track duplicate field names; otherwise this could be a
    // List<BoundField> instead
    final Map<String, BoundField> serializedFields = new LinkedHashMap<>();

    final Class<?> originalRaw = raw;
    while (Object.class != raw) {
      final Field[] fields = raw.getDeclaredFields();

      // For inherited fields, check if access to their declaring class is allowed
      if (raw != originalRaw && 0 < fields.length) {
        ReflectionAccessFilter.FilterResult filterResult =
            ReflectionAccessFilterHelper.getFilterResult(reflectionFilters, raw);
        if (ReflectionAccessFilter.FilterResult.BLOCK_ALL == filterResult) {
          throw new JsonIOException(
              "ReflectionAccessFilter does not permit using reflection for "
                  + raw
                  + " (supertype of "
                  + originalRaw
                  + "). Register a TypeAdapter for this type or adjust the access filter.");
        }
        blockInaccessible = ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE == filterResult;
      }

      for (Field field : fields) {
        boolean serialize = includeField(field, true);
        boolean deserialize = includeField(field, false);
        if (!serialize && !deserialize) {
          continue;
        }
        // The accessor method is only used for records. If the type is a record, we will read out
        // values via its accessor method instead of via reflection. This way we will bypass the
        // accessible restrictions
        Method accessor = null;
        if (isRecord) {
          // If there is a static field on a record, there will not be an accessor. Instead we will
          // use the default field serialization logic, but for deserialization the field is
          // excluded for simplicity.
          // Note that Gson ignores static fields by default, but
          // GsonBuilder.excludeFieldsWithModifiers can overwrite this.
          if (Modifier.isStatic(field.getModifiers())) {
            deserialize = false;
          } else {
            accessor = ReflectionHelper.getAccessor(raw, field);
            // If blockInaccessible, skip and perform access check later
            if (!blockInaccessible) {
              ReflectionHelper.makeAccessible(accessor);
            }

            // @SerializedName can be placed on accessor method, but it is not supported there
            // If field and method have annotation it is not easily possible to determine if
            // accessor method is implicit and has inherited annotation, or if it is explicitly
            // declared with custom annotation
            if (null != accessor.getAnnotation(SerializedName.class)
                && null == field.getAnnotation(SerializedName.class)) {
              String methodDescription =
                  ReflectionHelper.getAccessibleObjectDescription(accessor, false);
              throw new JsonIOException(
                  "@SerializedName on " + methodDescription + " is not supported");
            }
          }
        }

        // If blockInaccessible, skip and perform access check later
        // For Records if the accessor method is used the field does not have to be made accessible
        if (!blockInaccessible && null == accessor) {
          ReflectionHelper.makeAccessible(field);
        }

        Type fieldType = GsonTypes.resolve(type.getType(), raw, field.getGenericType());
        List<String> fieldNames = getFieldNames(field);
        String serializedName = fieldNames.get(0);
        BoundField boundField =
                createBoundField(
                context,
                field,
                accessor,
                serializedName,
                TypeToken.get(fieldType),
                serialize,
                blockInaccessible);

        if (deserialize) {
          for (String name : fieldNames) {
            BoundField replaced = deserializedFields.put(name, boundField);

            if (null != replaced) {
              throw createDuplicateFieldException(originalRaw, name, replaced.field, field);
            }
          }
        }

        if (serialize) {
          BoundField replaced = serializedFields.put(serializedName, boundField);
          if (null != replaced) {
            throw createDuplicateFieldException(originalRaw, serializedName, replaced.field, field);
          }
        }
      }
      type = TypeToken.get(GsonTypes.resolve(type.getType(), raw, raw.getGenericSuperclass()));
      raw = type.getRawType();
    }
    return new FieldsData(deserializedFields, new ArrayList<>(serializedFields.values()));
  }

  abstract static class BoundField {
    /** Name used for serialization (but not for deserialization) */
    final String serializedName;

    final Field field;

    /** Name of the underlying field */
    final String fieldName;

    protected BoundField(String serializedName, Field field) {
      this.serializedName = serializedName;
      this.field = field;
        this.fieldName = field.getName();
    }

    /** Read this field value from the source, and append its JSON value to the writer */
    abstract void write(JsonWriter writer, Object source)
        throws IOException, IllegalAccessException;

    /** Read the value into the target array, used to provide constructor arguments for records */
    abstract void readIntoArray(JsonReader reader, int index, Object[] target)
        throws IOException, JsonParseException;

    /**
     * Read the value from the reader, and set it on the corresponding field on target via
     * reflection
     */
    abstract void readIntoField(JsonReader reader, Object target)
        throws IOException, IllegalAccessException;
  }

  /**
   * Base class for Adapters produced by this factory.
   *
   * <p>The {@link RecordAdapter} is a special case to handle records for JVMs that support it, for
   * all other types we use the {@link FieldReflectionAdapter}. This class encapsulates the common
   * logic for serialization and deserialization. During deserialization, we construct an
   * accumulator A, which we use to accumulate values from the source JSON. After the object has
   * been read in full, the {@link #finalize(Object)} method is used to convert the accumulator to
   * an instance of T.
   *
   * @param <T> type of objects that this Adapter creates.
   * @param <A> type of accumulator used to build the deserialization result.
   */
  // This class is public because external projects check for this class with `instanceof` (even
  // though it is internal)
  public abstract static class Adapter<T, A> extends TypeAdapter<T> {
    private final FieldsData fieldsData;

    Adapter(FieldsData fieldsData) {
      this.fieldsData = fieldsData;
    }

    
    public void write(JsonWriter out, T value) throws IOException {
      if (null == value) {
        out.nullValue();
        return;
      }

      out.beginObject();
      try {
        for (BoundField boundField : fieldsData.serializedFields) {
          boundField.write(out, value);
        }
      } catch (IllegalAccessException e) {
        throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
      }
      out.endObject();
    }

    
    public T read(JsonReader in) throws IOException {
      if (JsonToken.NULL == in.peek()) {
        in.nextNull();
        return null;
      }

      A accumulator = createAccumulator();
      Map<String, BoundField> deserializedFields = fieldsData.deserializedFields;

      try {
        in.beginObject();
        while (in.hasNext()) {
          String name = in.nextName();
          BoundField field = deserializedFields.get(name);
          if (null == field) {
            in.skipValue();
          } else {
              readField(accumulator, in, field);
          }
        }
      } catch (IllegalStateException e) {
        throw new JsonSyntaxException(e);
      } catch (IllegalAccessException e) {
        throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
      }
      in.endObject();
      return finalize(accumulator);
    }

    /** Create the Object that will be used to collect each field value */
    abstract A createAccumulator();

    /**
     * Read a single BoundField into the accumulator. The JsonReader will be pointed at the start of
     * the value for the BoundField to read from.
     */
    abstract void readField(A accumulator, JsonReader in, BoundField field)
        throws IllegalAccessException, IOException;

    /** Convert the accumulator to a final instance of T. */
    abstract T finalize(A accumulator);
  }

  private static final class FieldReflectionAdapter<T> extends Adapter<T, T> {
    private final ObjectConstructor<T> constructor;

    FieldReflectionAdapter(ObjectConstructor<T> constructor, FieldsData fieldsData) {
      super(fieldsData);
      this.constructor = constructor;
    }

    
    T createAccumulator() {
      return constructor.construct();
    }

    
    void readField(T accumulator, JsonReader in, BoundField field)
        throws IllegalAccessException, IOException {
      field.readIntoField(in, accumulator);
    }

    
    T finalize(T accumulator) {
      return accumulator;
    }
  }

  private static final class RecordAdapter<T> extends Adapter<T, Object[]> {
    static final Map<Class<?>, Object> PRIMITIVE_DEFAULTS = primitiveDefaults();

    // The canonical constructor of the record
    private final Constructor<T> constructor;
    // Array of arguments to the constructor, initialized with default values for primitives
    private final Object[] constructorArgsDefaults;
    // Map from component names to index into the constructors arguments.
    private final Map<String, Integer> componentIndices = new HashMap<>();

    RecordAdapter(Class<T> raw, FieldsData fieldsData, boolean blockInaccessible) {
      super(fieldsData);
        constructor = ReflectionHelper.getCanonicalRecordConstructor(raw);

      if (blockInaccessible) {
          checkAccessible(null, constructor);
      } else {
        // Ensure the constructor is accessible
        ReflectionHelper.makeAccessible(constructor);
      }

      String[] componentNames = ReflectionHelper.getRecordComponentNames(raw);
      for (int i = 0; i < componentNames.length; i++) {
          componentIndices.put(componentNames[i], i);
      }
      Class<?>[] parameterTypes = constructor.getParameterTypes();

      // We need to ensure that we are passing non-null values to primitive fields in the
      // constructor. To do this, we create an Object[] where all primitives are initialized to
      // non-null values.
        constructorArgsDefaults = new Object[parameterTypes.length];
      for (int i = 0; i < parameterTypes.length; i++) {
        // This will correctly be null for non-primitive types:
          constructorArgsDefaults[i] = PRIMITIVE_DEFAULTS.get(parameterTypes[i]);
      }
    }

    private static Map<Class<?>, Object> primitiveDefaults() {
      Map<Class<?>, Object> zeroes = new HashMap<>();
      zeroes.put(byte.class, (byte) 0);
      zeroes.put(short.class, (short) 0);
      zeroes.put(int.class, 0);
      zeroes.put(long.class, 0L);
      zeroes.put(float.class, 0.0F);
      zeroes.put(double.class, 0.0D);
      zeroes.put(char.class, '\0');
      zeroes.put(boolean.class, false);
      return zeroes;
    }

    
    Object[] createAccumulator() {
      return constructorArgsDefaults.clone();
    }

    
    void readField(Object[] accumulator, JsonReader in, BoundField field) throws IOException {
      // Obtain the component index from the name of the field backing it
      Integer componentIndex = componentIndices.get(field.fieldName);
      if (null == componentIndex) {
        throw new IllegalStateException(
            "Could not find the index in the constructor '"
                + ReflectionHelper.constructorToString(constructor)
                + "' for field with name '"
                + field.fieldName
                + "', unable to determine which argument in the constructor the field corresponds"
                + " to. This is unexpected behavior, as we expect the RecordComponents to have the"
                + " same names as the fields in the Java class, and that the order of the"
                + " RecordComponents is the same as the order of the canonical constructor"
                + " parameters.");
      }
      field.readIntoArray(in, componentIndex, accumulator);
    }

    
    T finalize(Object[] accumulator) {
      try {
        return constructor.newInstance(accumulator);
      } catch (IllegalAccessException e) {
        throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
      }
      // Note: InstantiationException should be impossible because record class is not abstract;
      //  IllegalArgumentException should not be possible unless a bad adapter returns objects of
      //  the wrong type
      catch (InstantiationException | IllegalArgumentException e) {
        throw new RuntimeException(
            "Failed to invoke constructor '"
                + ReflectionHelper.constructorToString(constructor)
                + "' with args "
                + Arrays.toString(accumulator),
            e);
      } catch (InvocationTargetException e) {
        // TODO: JsonParseException ?
        throw new RuntimeException(
            "Failed to invoke constructor '"
                + ReflectionHelper.constructorToString(constructor)
                + "' with args "
                + Arrays.toString(accumulator),
            e.getCause());
      }
    }
  }
}
