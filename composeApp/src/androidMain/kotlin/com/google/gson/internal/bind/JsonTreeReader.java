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

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * This reader walks the elements of a JsonElement as if it was coming from a character stream.
 *
 * @author Jesse Wilson
 */
public final class JsonTreeReader extends JsonReader {
  private static final Reader UNREADABLE_READER =
      new Reader() {
        
        public int read(char[] buffer, int offset, int count) {
          throw new AssertionError();
        }

        
        public void close() {
          throw new AssertionError();
        }
      };
  private static final Object SENTINEL_CLOSED = new Object();

  /** The nesting stack. Using a manual array rather than an ArrayList saves 20%. */
  private Object[] stack = new Object[32];

  /**
   * The used size of {@link #stack}; the value at {@code stackSize - 1} is the value last placed on
   * the stack. {@code stackSize} might differ from the nesting depth, because the stack also
   * contains temporary additional objects, for example for a JsonArray it contains the JsonArray
   * object as well as the corresponding iterator.
   */
  private int stackSize;

  /*
   * The path members. It corresponds directly to stack: At indices where the
   * stack contains an object (EMPTY_OBJECT, DANGLING_NAME or NONEMPTY_OBJECT),
   * pathNames contains the name at this scope. Where it contains an array
   * (EMPTY_ARRAY, NONEMPTY_ARRAY) pathIndices contains the current index in
   * that array. Otherwise the value is undefined, and we take advantage of that
   * by incrementing pathIndices when doing so isn't useful.
   */
  private String[] pathNames = new String[32];
  private int[] pathIndices = new int[32];

  public JsonTreeReader(JsonElement element) {
    super(UNREADABLE_READER);
      push(element);
  }

  
  public void beginArray() throws IOException {
      expect(JsonToken.BEGIN_ARRAY);
    JsonArray array = (JsonArray) peekStack();
      push(array.iterator());
      pathIndices[stackSize - 1] = 0;
  }

  
  public void endArray() throws IOException {
      expect(JsonToken.END_ARRAY);
      popStack(); // empty iterator
      popStack(); // array
    if (0 < this.stackSize) {
        pathIndices[stackSize - 1]++;
    }
  }

  
  public void beginObject() throws IOException {
      expect(JsonToken.BEGIN_OBJECT);
    JsonObject object = (JsonObject) peekStack();
      push(object.entrySet().iterator());
  }

  
  public void endObject() throws IOException {
      expect(JsonToken.END_OBJECT);
      pathNames[stackSize - 1] = null; // Free the last path name so that it can be garbage collected
      popStack(); // empty iterator
      popStack(); // object
    if (0 < this.stackSize) {
        pathIndices[stackSize - 1]++;
    }
  }

  
  public boolean hasNext() throws IOException {
    JsonToken token = peek();
    return JsonToken.END_OBJECT != token
        && JsonToken.END_ARRAY != token
        && JsonToken.END_DOCUMENT != token;
  }

  
  public JsonToken peek() throws IOException {
    if (0 == this.stackSize) {
      return JsonToken.END_DOCUMENT;
    }

    Object o = peekStack();
    if (o instanceof final Iterator<?> iterator) {
      boolean isObject = stack[stackSize - 2] instanceof JsonObject;
        if (iterator.hasNext()) {
        if (isObject) {
          return JsonToken.NAME;
        } else {
            push(iterator.next());
          return peek();
        }
      } else {
        return isObject ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
      }
    } else if (o instanceof JsonObject) {
      return JsonToken.BEGIN_OBJECT;
    } else if (o instanceof JsonArray) {
      return JsonToken.BEGIN_ARRAY;
    } else if (o instanceof final JsonPrimitive primitive) {
        if (primitive.isString()) {
        return JsonToken.STRING;
      } else if (primitive.isBoolean()) {
        return JsonToken.BOOLEAN;
      } else if (primitive.isNumber()) {
        return JsonToken.NUMBER;
      } else {
        throw new AssertionError();
      }
    } else if (o instanceof JsonNull) {
      return JsonToken.NULL;
    } else if (o == SENTINEL_CLOSED) {
      throw new IllegalStateException("JsonReader is closed");
    } else {
      throw new MalformedJsonException(
          "Custom JsonElement subclass " + o.getClass().getName() + " is not supported");
    }
  }

  private Object peekStack() {
    return stack[stackSize - 1];
  }

  @CanIgnoreReturnValue
  private Object popStack() {
      --stackSize;
      Object result = stack[stackSize];
      stack[stackSize] = null;
    return result;
  }

  private void expect(JsonToken expected) throws IOException {
    if (peek() != expected) {
      throw new IllegalStateException(
          "Expected " + expected + " but was " + peek() + locationString());
    }
  }

  private String nextName(boolean skipName) throws IOException {
      expect(JsonToken.NAME);
    Iterator<?> i = (Iterator<?>) peekStack();
    Map.Entry<?, ?> entry = (Map.Entry<?, ?>) i.next();
    String result = (String) entry.getKey();
      pathNames[stackSize - 1] = skipName ? "<skipped>" : result;
      push(entry.getValue());
    return result;
  }

  
  public String nextName() throws IOException {
    return nextName(false);
  }

  
  public String nextString() throws IOException {
    JsonToken token = peek();
    if (JsonToken.STRING != token && JsonToken.NUMBER != token) {
      throw new IllegalStateException(
          "Expected " + JsonToken.STRING + " but was " + token + locationString());
    }
    String result = ((JsonPrimitive) popStack()).getAsString();
    if (0 < this.stackSize) {
        pathIndices[stackSize - 1]++;
    }
    return result;
  }

  
  public boolean nextBoolean() throws IOException {
      expect(JsonToken.BOOLEAN);
    boolean result = ((JsonPrimitive) popStack()).getAsBoolean();
    if (0 < this.stackSize) {
        pathIndices[stackSize - 1]++;
    }
    return result;
  }

  
  public void nextNull() throws IOException {
      expect(JsonToken.NULL);
      popStack();
    if (0 < this.stackSize) {
        pathIndices[stackSize - 1]++;
    }
  }

  
  public double nextDouble() throws IOException {
    JsonToken token = peek();
    if (JsonToken.NUMBER != token && JsonToken.STRING != token) {
      throw new IllegalStateException(
          "Expected " + JsonToken.NUMBER + " but was " + token + locationString());
    }
    double result = ((JsonPrimitive) peekStack()).getAsDouble();
    if (!isLenient() && (Double.isNaN(result) || Double.isInfinite(result))) {
      throw new MalformedJsonException("JSON forbids NaN and infinities: " + result);
    }
      popStack();
    if (0 < this.stackSize) {
        pathIndices[stackSize - 1]++;
    }
    return result;
  }

  
  public long nextLong() throws IOException {
    JsonToken token = peek();
    if (JsonToken.NUMBER != token && JsonToken.STRING != token) {
      throw new IllegalStateException(
          "Expected " + JsonToken.NUMBER + " but was " + token + locationString());
    }
    long result = ((JsonPrimitive) peekStack()).getAsLong();
      popStack();
    if (0 < this.stackSize) {
        pathIndices[stackSize - 1]++;
    }
    return result;
  }

  
  public int nextInt() throws IOException {
    JsonToken token = peek();
    if (JsonToken.NUMBER != token && JsonToken.STRING != token) {
      throw new IllegalStateException(
          "Expected " + JsonToken.NUMBER + " but was " + token + locationString());
    }
    int result = ((JsonPrimitive) peekStack()).getAsInt();
      popStack();
    if (0 < this.stackSize) {
        pathIndices[stackSize - 1]++;
    }
    return result;
  }

  JsonElement nextJsonElement() throws IOException {
    JsonToken peeked = peek();
    if (JsonToken.NAME == peeked
        || JsonToken.END_ARRAY == peeked
        || JsonToken.END_OBJECT == peeked
        || JsonToken.END_DOCUMENT == peeked) {
      throw new IllegalStateException("Unexpected " + peeked + " when reading a JsonElement.");
    }
    JsonElement element = (JsonElement) peekStack();
      skipValue();
    return element;
  }

  
  public void close() throws IOException {
      stack = new Object[] {SENTINEL_CLOSED};
      stackSize = 1;
  }

  
  public void skipValue() throws IOException {
    JsonToken peeked = peek();
    switch (peeked) {
      case NAME:
        @SuppressWarnings("unused") String unused = nextName(true);
        break;
      case END_ARRAY:
          endArray();
        break;
      case END_OBJECT:
          endObject();
        break;
      case END_DOCUMENT:
        // Do nothing
        break;
      default:
          popStack();
        if (0 < this.stackSize) {
            pathIndices[stackSize - 1]++;
        }
        break;
    }
  }

  
  public String toString() {
    return getClass().getSimpleName() + locationString();
  }

  public void promoteNameToValue() throws IOException {
      expect(JsonToken.NAME);
    Iterator<?> i = (Iterator<?>) peekStack();
    Map.Entry<?, ?> entry = (Map.Entry<?, ?>) i.next();
      push(entry.getValue());
      push(new JsonPrimitive((String) entry.getKey()));
  }

  private void push(Object newTop) {
    if (stackSize == stack.length) {
      int newLength = stackSize * 2;
        stack = Arrays.copyOf(stack, newLength);
        pathIndices = Arrays.copyOf(pathIndices, newLength);
        pathNames = Arrays.copyOf(pathNames, newLength);
    }
      stack[stackSize] = newTop;
      this.stackSize++;
  }

  private String getPath(boolean usePreviousPath) {
    StringBuilder result = new StringBuilder().append('');
    for (int i = 0; i < stackSize; i++) {
      if (stack[i] instanceof JsonArray) {
          ++i;
          if (i < stackSize && stack[i] instanceof Iterator) {
          int pathIndex = pathIndices[i];
          // If index is last path element it points to next array element; have to decrement
          // `- 1` covers case where iterator for next element is on stack
          // `- 2` covers case where peek() already pushed next element onto stack
          if (usePreviousPath && 0 < pathIndex && (i == stackSize - 1 || i == stackSize - 2)) {
            pathIndex--;
          }
          result.append('[').append(pathIndex).append(']');
        }
      } else if (stack[i] instanceof JsonObject) {
          ++i;
          if (i < stackSize && stack[i] instanceof Iterator) {
          result.append('.');
          if (null != this.pathNames[i]) {
            result.append(pathNames[i]);
          }
        }
      }
    }
    return result.toString();
  }

  
  public String getPath() {
    return getPath(false);
  }

  
  public String getPreviousPath() {
    return getPath(true);
  }

  private String locationString() {
    return " at path " + getPath();
  }
}
