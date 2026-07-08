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

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class JsonElement {

  protected JsonElement() {}

  public abstract JsonElement deepCopy();

  public boolean isJsonArray() {
    return this instanceof JsonArray;
  }

  public boolean isJsonObject() {
    return this instanceof JsonObject;
  }

  public boolean isJsonPrimitive() {
    return this instanceof JsonPrimitive;
  }

  public boolean isJsonNull() {
    return this instanceof JsonNull;
  }

  public JsonObject getAsJsonObject() {
    if (isJsonObject()) {
      return (JsonObject) this;
    }
    throw new IllegalStateException("Not a JSON Object: " + this);
  }

  public JsonArray getAsJsonArray() {
    if (isJsonArray()) {
      return (JsonArray) this;
    }
    throw new IllegalStateException("Not a JSON Array: " + this);
  }

  public JsonPrimitive getAsJsonPrimitive() {
    if (isJsonPrimitive()) {
      return (JsonPrimitive) this;
    }
    throw new IllegalStateException("Not a JSON Primitive: " + this);
  }

  public JsonNull getAsJsonNull() {
    if (isJsonNull()) {
      return (JsonNull) this;
    }
    throw new IllegalStateException("Not a JSON Null: " + this);
  }

  public boolean getAsBoolean() {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public Number getAsNumber() {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  public String getAsString() {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public double getAsDouble() {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public float getAsFloat() {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public long getAsLong() {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public int getAsInt() {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public byte getAsByte() {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public char getAsCharacter() {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public BigDecimal getAsBigDecimal() {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }
  public BigInteger getAsBigInteger() {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public short getAsShort() {
    throw new UnsupportedOperationException(getClass().getSimpleName());
  }

  public String toString() {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      JsonWriter jsonWriter = new JsonWriter(Streams.writerForAppendable(stringBuilder));
      // Make writer lenient because toString() must not fail, even if for example JsonPrimitive
      // contains NaN
      jsonWriter.setStrictness(Strictness.LENIENT);
      Streams.write(this, jsonWriter);
      return stringBuilder.toString();
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }
}
