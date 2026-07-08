

package com.google.gson.internal.bind;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/** Type adapter for {@link Number}. */
public final class NumberTypeAdapter extends TypeAdapter<Number> {
  /** Gson default factory using {@link ToNumberPolicy#LAZILY_PARSED_NUMBER}. */
  private static final TypeAdapterFactory LAZILY_PARSED_NUMBER_FACTORY =
          newFactory(ToNumberPolicy.LAZILY_PARSED_NUMBER);

  private final ToNumberStrategy toNumberStrategy;

  private NumberTypeAdapter(ToNumberStrategy toNumberStrategy) {
    this.toNumberStrategy = toNumberStrategy;
  }

  private static TypeAdapterFactory newFactory(ToNumberStrategy toNumberStrategy) {
    NumberTypeAdapter adapter = new NumberTypeAdapter(toNumberStrategy);
    return new TypeAdapterFactory() {
      @SuppressWarnings("unchecked")
      
      public <T> TypeAdapters.EnumTypeAdapter create(Gson gson, TypeToken<T> type) {
        return Number.class == type.getRawType() ? (TypeAdapter<T>) adapter : null;
      }
    };
  }

  public static TypeAdapterFactory getFactory(ToNumberStrategy toNumberStrategy) {
    if (ToNumberPolicy.LAZILY_PARSED_NUMBER == toNumberStrategy) {
      return LAZILY_PARSED_NUMBER_FACTORY;
    } else {
      return newFactory(toNumberStrategy);
    }
  }

  
  public Number read(JsonReader in) throws IOException {
    JsonToken jsonToken = in.peek();
    switch (jsonToken) {
      case NULL:
        in.nextNull();
        return null;
      case NUMBER:
      case STRING:
        return toNumberStrategy.readNumber(in);
      default:
        throw new JsonSyntaxException(
            "Expecting number, got: " + jsonToken + "; at path " + in.getPath());
    }
  }

  
  public void write(JsonWriter out, Number value) throws IOException {
    out.value(value);
  }
}
