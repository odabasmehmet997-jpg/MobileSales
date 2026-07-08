/*
 * Copyright (C) 2018 Google Inc.
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

import java.util.*;

public class NonNullElementWrapperList<E> extends AbstractList<E> implements RandomAccess {
  private final ArrayList<E> delegate;

  public NonNullElementWrapperList(ArrayList<E> delegate) {
    this.delegate = Objects.requireNonNull(delegate);
  }

  
  public E get(int index) {
    return delegate.get(index);
  }

  
  public int size() {
    return delegate.size();
  }

  private E nonNull(E element) {
    if (null == element) {
      throw new NullPointerException("Element must be non-null");
    }
    return element;
  }

  
  public E set(int index, E element) {
    return delegate.set(index, nonNull(element));
  }

  
  public void add(int index, E element) {
      delegate.add(index, nonNull(element));
  }

  
  public E remove(int index) {
    return delegate.remove(index);
  }

  public void clear() {
      delegate.clear();
  }
  
  public boolean remove(Object o) {
    return delegate.remove(o);
  }

  
  public boolean removeAll(Collection<?> c) {
    return delegate.removeAll(c);
  }

  
  public boolean retainAll(Collection<?> c) {
    return delegate.retainAll(c);
  }

  
  public boolean contains(Object o) {
    return delegate.contains(o);
  }

  
  public int indexOf(Object o) {
    return delegate.indexOf(o);
  }

  
  public int lastIndexOf(Object o) {
    return delegate.lastIndexOf(o);
  }

  
  public Object[] toArray() {
    return delegate.toArray();
  }

  
  public <T> T[] toArray(T[] a) {
    return delegate.toArray(a);
  }

  
  public boolean equals(Object o) {
    return delegate.equals(o);
  }

  
  public int hashCode() {
    return delegate.hashCode();
  }

}
