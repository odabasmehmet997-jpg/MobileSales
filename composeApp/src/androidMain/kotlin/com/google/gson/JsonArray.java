

package com.google.gson;

import com.google.gson.internal.NonNullElementWrapperList;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class JsonArray extends JsonElement implements Iterable<JsonElement> {
  private final ArrayList<JsonElement> elements;
  public JsonArray() {
      elements = new ArrayList<>();
  }

  public JsonArray(int capacity) {
      elements = new ArrayList<>(capacity);
  }


  public JsonArray deepCopy() {
    if (!elements.isEmpty()) {
      JsonArray result = new JsonArray(elements.size());
      for (JsonElement element : elements) {
        result.add(element.deepCopy());
      }
      return result;
    }
    return new JsonArray();
  }
  public void add(Boolean bool) {
      elements.add(null == bool ? JsonNull.INSTANCE : new JsonPrimitive(bool));
  }

  public void add(Character character) {
      elements.add(null == character ? JsonNull.INSTANCE : new JsonPrimitive(character));
  }

  public void add(Number number) {
      elements.add(null == number ? JsonNull.INSTANCE : new JsonPrimitive(number));
  }

  public void add(String string) {
      elements.add(null == string ? JsonNull.INSTANCE : new JsonPrimitive(string));
  }

  public void add(JsonElement element) {
    if (null == element) {
      element = JsonNull.INSTANCE;
    }
      elements.add(element);
  }

  public void addAll(JsonArray array) {
      elements.addAll(array.elements);
  }

  public JsonElement set(int index, JsonElement element) {
    return elements.set(index, null == element ? JsonNull.INSTANCE : element);
  }

  public boolean remove(JsonElement element) {
    return elements.remove(element);
  }

  public JsonElement remove(int index) {
    return elements.remove(index);
  }

  public boolean contains(JsonElement element) {
    return elements.contains(element);
  }


  public int size() {
    return elements.size();
  }

  public boolean isEmpty() {
    return elements.isEmpty();
  }



  public Iterator<JsonElement> iterator() {
    return elements.iterator();
  }

  public JsonElement get(int i) {
    return elements.get(i);
  }

  private JsonElement getAsSingleElement() {
    int size = elements.size();
    if (1 == size) {
      return elements.get(0);
    }
    throw new IllegalStateException("Array must have size 1, but has size " + size);
  }


  public Number getAsNumber() {
    return getAsSingleElement().getAsNumber();
  }


  public String getAsString() {
    return getAsSingleElement().getAsString();
  }

  public double getAsDouble() {
    return getAsSingleElement().getAsDouble();
  }



  public BigDecimal getAsBigDecimal() {
    return getAsSingleElement().getAsBigDecimal();
  }

  public BigInteger getAsBigInteger() {
    return getAsSingleElement().getAsBigInteger();
  }



  public float getAsFloat() {
    return getAsSingleElement().getAsFloat();
  }


  public long getAsLong() {
    return getAsSingleElement().getAsLong();
  }


  public int getAsInt() {
    return getAsSingleElement().getAsInt();
  }


  public byte getAsByte() {
    return getAsSingleElement().getAsByte();
  }


  public char getAsCharacter() {
    return getAsSingleElement().getAsCharacter();
  }


  public short getAsShort() {
    return getAsSingleElement().getAsShort();
  }


  public boolean getAsBoolean() {
    return getAsSingleElement().getAsBoolean();
  }

  public List<JsonElement> asList() {
    return new NonNullElementWrapperList<>(elements);
  }

  public boolean equals(Object o) {
    return (o == this) || (o instanceof JsonArray && ((JsonArray) o).elements.equals(elements));
  }
  public int hashCode() {
    return elements.hashCode();
  }
}
