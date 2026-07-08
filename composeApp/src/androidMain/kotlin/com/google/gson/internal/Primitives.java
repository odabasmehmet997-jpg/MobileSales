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

package com.google.gson.internal;

import java.lang.reflect.Type;

/**
 * Contains static utility methods pertaining to primitive types and their corresponding wrapper
 * types.
 *
 * @author Kevin Bourrillion
 */
public enum Primitives {
    ;

    /** Returns true if this type is a primitive. */
  public static boolean isPrimitive(Type type) {
    return type instanceof Class<?> && ((Class<?>) type).isPrimitive();
  }

  /**
   * Returns {@code true} if {@code type} is one of the nine primitive-wrapper types, such as {@link
   * Integer}.
   *
   * @see Class#isPrimitive
   */
  public static boolean isWrapperType(Type type) {
    return Integer.class == type
        || Float.class == type
        || Byte.class == type
        || Double.class == type
        || Long.class == type
        || Character.class == type
        || Boolean.class == type
        || Short.class == type
        || Void.class == type;
  }

  /**
   * Returns the corresponding wrapper type of {@code type} if it is a primitive type; otherwise
   * returns {@code type} itself. Idempotent.
   *
   * <pre>
   *     wrap(int.class) == Integer.class
   *     wrap(Integer.class) == Integer.class
   *     wrap(String.class) == String.class
   * </pre>
   */
  @SuppressWarnings({"unchecked", "MissingBraces"})
  public static <T> Class<T> wrap(Class<T> type) {
    if (int.class == type) return (Class<T>) Integer.class;
    if (float.class == type) return (Class<T>) Float.class;
    if (byte.class == type) return (Class<T>) Byte.class;
    if (double.class == type) return (Class<T>) Double.class;
    if (long.class == type) return (Class<T>) Long.class;
    if (char.class == type) return (Class<T>) Character.class;
    if (boolean.class == type) return (Class<T>) Boolean.class;
    if (short.class == type) return (Class<T>) Short.class;
    if (void.class == type) return (Class<T>) Void.class;
    return type;
  }

  /**
   * Returns the corresponding primitive type of {@code type} if it is a wrapper type; otherwise
   * returns {@code type} itself. Idempotent.
   *
   * <pre>
   *     unwrap(Integer.class) == int.class
   *     unwrap(int.class) == int.class
   *     unwrap(String.class) == String.class
   * </pre>
   */
  @SuppressWarnings({"unchecked", "MissingBraces"})
  public static <T> Class<T> unwrap(Class<T> type) {
    if (Integer.class == type) return (Class<T>) int.class;
    if (Float.class == type) return (Class<T>) float.class;
    if (Byte.class == type) return (Class<T>) byte.class;
    if (Double.class == type) return (Class<T>) double.class;
    if (Long.class == type) return (Class<T>) long.class;
    if (Character.class == type) return (Class<T>) char.class;
    if (Boolean.class == type) return (Class<T>) boolean.class;
    if (Short.class == type) return (Class<T>) short.class;
    if (Void.class == type) return (Class<T>) void.class;
    return type;
  }
}
