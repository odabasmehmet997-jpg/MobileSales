/*
 * Copyright (C) 2010 The Android Open Source Project
 * Copyright (C) 2012 Google Inc.
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

package com.google.gson.internal;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import java.io.*;
import java.util.*;

/**
 * A map of comparable keys to values. Unlike {@code TreeMap}, this class uses insertion order for
 * iteration order. Comparison order is only used as an optimization for efficient insertion and
 * removal.
 *
 * <p>This implementation was derived from Android 4.1's TreeMap class.
 */
@SuppressWarnings("serial") // ignore warning about missing serialVersionUID
public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
  @SuppressWarnings({"unchecked", "rawtypes"}) // to avoid Comparable<Comparable<Comparable<...>>>
  private static final Comparator<Comparable> NATURAL_ORDER =
      new Comparator<Comparable>() {
        
        public int compare(Comparable a, Comparable b) {
          return a.compareTo(b);
        }
      };

  private final Comparator<? super K> comparator;
  private final boolean allowNullValues;
  Node<K, V> root;
  int size;
  int modCount;

  // Used to preserve iteration order
  final Node<K, V> header;

  /**
   * Create a natural order, empty tree map whose keys must be mutually comparable and non-null, and
   * whose values can be {@code null}.
   */
  @SuppressWarnings("unchecked") // unsafe! this assumes K is comparable
  public LinkedTreeMap() {
    this((Comparator<? super K>) NATURAL_ORDER, true);
  }

  /**
   * Create a natural order, empty tree map whose keys must be mutually comparable and non-null.
   *
   * @param allowNullValues whether {@code null} is allowed as entry value
   */
  @SuppressWarnings("unchecked") // unsafe! this assumes K is comparable
  public LinkedTreeMap(boolean allowNullValues) {
    this((Comparator<? super K>) NATURAL_ORDER, allowNullValues);
  }

  /**
   * Create a tree map ordered by {@code comparator}. This map's keys may only be null if {@code
   * comparator} permits.
   *
   * @param comparator the comparator to order elements with, or {@code null} to use the natural
   *     ordering.
   * @param allowNullValues whether {@code null} is allowed as entry value
   */
  // unsafe! if comparator is null, this assumes K is comparable
  @SuppressWarnings({"unchecked", "rawtypes"})
  public LinkedTreeMap(Comparator<? super K> comparator, boolean allowNullValues) {
    this.comparator = null != comparator ? comparator : (Comparator) NATURAL_ORDER;
    this.allowNullValues = allowNullValues;
      this.header = new Node<>(allowNullValues);
  }

  
  public int size() {
    return size;
  }

  
  public V get(Object key) {
    Node<K, V> node = findByObject(key);
    return null != node ? node.value : null;
  }

  
  public boolean containsKey(Object key) {
    return null != this.findByObject(key);
  }

  @CanIgnoreReturnValue
  
  public V put(K key, V value) {
    if (null == key) {
      throw new NullPointerException("key == null");
    }
    if (null == value && !allowNullValues) {
      throw new NullPointerException("value == null");
    }
    Node<K, V> created = find(key, true);
    V result = created.value;
    created.value = value;
    return result;
  }

  
  public void clear() {
      root = null;
      size = 0;
      modCount++;

    // Clear iteration order
    Node<K, V> header = this.header;
    header.next = header.prev = header;
  }

  
  public V remove(Object key) {
    Node<K, V> node = removeInternalByKey(key);
    return null != node ? node.value : null;
  }

  /**
   * Returns the node at or adjacent to the given key, creating it if requested.
   *
   * @throws ClassCastException if {@code key} and the tree's keys aren't mutually comparable.
   */
  Node<K, V> find(K key, boolean create) {
    Comparator<? super K> comparator = this.comparator;
    Node<K, V> nearest = root;
    int comparison = 0;

    if (null != nearest) {
      // Micro-optimization: avoid polymorphic calls to Comparator.compare().
      @SuppressWarnings("unchecked") // Throws a ClassCastException below if there's trouble.
      Comparable<Object> comparableKey =
          (comparator == NATURAL_ORDER) ? (Comparable<Object>) key : null;

      while (true) {
        comparison =
            (null != comparableKey)
                ? comparableKey.compareTo(nearest.key)
                : comparator.compare(key, nearest.key);

        // We found the requested key.
        if (0 == comparison) {
          return nearest;
        }

        // If it exists, the key is in a subtree. Go deeper.
        Node<K, V> child = (0 > comparison) ? nearest.left : nearest.right;
        if (null == child) {
          break;
        }

        nearest = child;
      }
    }

    // The key doesn't exist in this tree.
    if (!create) {
      return null;
    }

    // Create the node and add it to the tree or the table.
    Node<K, V> header = this.header;
    Node<K, V> created;
    if (null == nearest) {
      // Check that the value is comparable if we didn't do any comparisons.
      if (comparator == NATURAL_ORDER && !(key instanceof Comparable)) {
        throw new ClassCastException(key.getClass().getName() + " is not Comparable");
      }
      created = new Node<>(allowNullValues, nearest, key, header, header.prev);
        root = created;
    } else {
      created = new Node<>(allowNullValues, nearest, key, header, header.prev);
      if (0 > comparison) { // nearest.key is higher
        nearest.left = created;
      } else { // comparison > 0, nearest.key is lower
        nearest.right = created;
      }
        rebalance(nearest, true);
    }
      size++;
      modCount++;

    return created;
  }

  @SuppressWarnings("unchecked")
  Node<K, V> findByObject(Object key) {
    try {
      return null != key ? find((K) key, false) : null;
    } catch (ClassCastException e) {
      return null;
    }
  }

  /**
   * Returns this map's entry that has the same key and value as {@code entry}, or null if this map
   * has no such entry.
   *
   * <p>This method uses the comparator for key equality rather than {@code equals}. If this map's
   * comparator isn't consistent with equals (such as {@code String.CASE_INSENSITIVE_ORDER}), then
   * {@code remove()} and {@code contains()} will violate the collections API.
   */
  Node<K, V> findByEntry(final Map.Entry<?, ?> entry) {
    final Node<K, V> mine = this.findByObject(entry.getKey());
    final boolean valuesEqual = null != mine && LinkedTreeMap.equal(mine.value, entry.getValue());
    return valuesEqual ? mine : null;
  }

  private static boolean equal(final Object a, final Object b) {
    return Objects.equals(a, b);
  }

  /**
   * Removes {@code node} from this tree, rearranging the tree's structure as necessary.
   *
   * @param unlink true to also unlink this node from the iteration linked list.
   */
  void removeInternal(final Node<K, V> node, final boolean unlink) {
    if (unlink) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }

    Node<K, V> left = node.left;
    Node<K, V> right = node.right;
    final Node<K, V> originalParent = node.parent;
    if (null != left && null != right) {

      /*
       * To remove a node with both left and right subtrees, move an
       * adjacent node from one of those subtrees into this node's place.
       *
       * Removing the adjacent node may change this node's subtrees. This
       * node may no longer have two subtrees once the adjacent node is
       * gone!
       */

      final Node<K, V> adjacent = (left.height > right.height) ? left.last() : right.first();
        this.removeInternal(adjacent, false); // takes care of rebalance and size--

      int leftHeight = 0;
      left = node.left;
      if (null != left) {
        leftHeight = left.height;
        adjacent.left = left;
        left.parent = adjacent;
        node.left = null;
      }

      int rightHeight = 0;
      right = node.right;
      if (null != right) {
        rightHeight = right.height;
        adjacent.right = right;
        right.parent = adjacent;
        node.right = null;
      }

      adjacent.height = Math.max(leftHeight, rightHeight) + 1;
        this.replaceInParent(node, adjacent);
      return;
    } else if (null != left) {
        this.replaceInParent(node, left);
      node.left = null;
    } else if (null != right) {
        this.replaceInParent(node, right);
      node.right = null;
    } else {
        this.replaceInParent(node, null);
    }

      this.rebalance(originalParent, false);
      this.size--;
      this.modCount++;
  }

  Node<K, V> removeInternalByKey(final Object key) {
    final Node<K, V> node = this.findByObject(key);
    if (null != node) {
        this.removeInternal(node, true);
    }
    return node;
  }

  @SuppressWarnings("ReferenceEquality")
  private void replaceInParent(final Node<K, V> node, final Node<K, V> replacement) {
    final Node<K, V> parent = node.parent;
    node.parent = null;
    if (null != replacement) {
      replacement.parent = parent;
    }

    if (null != parent) {
      if (parent.left == node) {
        parent.left = replacement;
      } else {
        assert parent.right == node;
        parent.right = replacement;
      }
    } else {
        this.root = replacement;
    }
  }

  /**
   * Rebalances the tree by making any AVL rotations necessary between the newly-unbalanced node and
   * the tree's root.
   *
   * @param insert true if the node was unbalanced by an insert; false if it was by a removal.
   */
  private void rebalance(final Node<K, V> unbalanced, final boolean insert) {
    for (Node<K, V> node = unbalanced; null != node; node = node.parent) {
      final Node<K, V> left = node.left;
      final Node<K, V> right = node.right;
      final int leftHeight = null != left ? left.height : 0;
      final int rightHeight = null != right ? right.height : 0;

      final int delta = leftHeight - rightHeight;
      if (-2 == delta) {
        final Node<K, V> rightLeft = right.left;
        final Node<K, V> rightRight = right.right;
        final int rightRightHeight = null != rightRight ? rightRight.height : 0;
        final int rightLeftHeight = null != rightLeft ? rightLeft.height : 0;

        final int rightDelta = rightLeftHeight - rightRightHeight;
        if (-1 == rightDelta || (0 == rightDelta && !insert)) {
            this.rotateLeft(node); // AVL right right
        } else {
          assert (1 == rightDelta);
            this.rotateRight(right); // AVL right left
            this.rotateLeft(node);
        }
        if (insert) {
          break; // no further rotations will be necessary
        }

      } else if (2 == delta) {
        final Node<K, V> leftLeft = left.left;
        final Node<K, V> leftRight = left.right;
        final int leftRightHeight = null != leftRight ? leftRight.height : 0;
        final int leftLeftHeight = null != leftLeft ? leftLeft.height : 0;

        final int leftDelta = leftLeftHeight - leftRightHeight;
        if (1 == leftDelta || (0 == leftDelta && !insert)) {
            this.rotateRight(node); // AVL left left
        } else {
          assert (-1 == leftDelta);
            this.rotateLeft(left); // AVL left right
            this.rotateRight(node);
        }
        if (insert) {
          break; // no further rotations will be necessary
        }

      } else if (0 == delta) {
        node.height = leftHeight + 1; // leftHeight == rightHeight
        if (insert) {
          break; // the insert caused balance, so rebalancing is done!
        }

      } else {
        assert (-1 == delta || 1 == delta);
        node.height = Math.max(leftHeight, rightHeight) + 1;
        if (!insert) {
          break; // the height hasn't changed, so rebalancing is done!
        }
      }
    }
  }

  /** Rotates the subtree so that its root's right child is the new root. */
  private void rotateLeft(final Node<K, V> root) {
    final Node<K, V> left = root.left;
    final Node<K, V> pivot = root.right;
    final Node<K, V> pivotLeft = pivot.left;
    final Node<K, V> pivotRight = pivot.right;

    // move the pivot's left child to the root's right
    root.right = pivotLeft;
    if (null != pivotLeft) {
      pivotLeft.parent = root;
    }

      this.replaceInParent(root, pivot);

    // move the root to the pivot's left
    pivot.left = root;
    root.parent = pivot;

    // fix heights
    root.height =
        Math.max(null != left ? left.height : 0, null != pivotLeft ? pivotLeft.height : 0) + 1;
    pivot.height = Math.max(root.height, null != pivotRight ? pivotRight.height : 0) + 1;
  }

  /** Rotates the subtree so that its root's left child is the new root. */
  private void rotateRight(final Node<K, V> root) {
    final Node<K, V> pivot = root.left;
    final Node<K, V> right = root.right;
    final Node<K, V> pivotLeft = pivot.left;
    final Node<K, V> pivotRight = pivot.right;

    // move the pivot's right child to the root's left
    root.left = pivotRight;
    if (null != pivotRight) {
      pivotRight.parent = root;
    }

      this.replaceInParent(root, pivot);

    // move the root to the pivot's right
    pivot.right = root;
    root.parent = pivot;

    // fixup heights
    root.height =
        Math.max(null != right ? right.height : 0, null != pivotRight ? pivotRight.height : 0) + 1;
    pivot.height = Math.max(root.height, null != pivotLeft ? pivotLeft.height : 0) + 1;
  }

  private EntrySet entrySet;
  private KeySet keySet;


  public Set<Map.Entry<K, V>> entrySet() {
    EntrySet result = this.entrySet;
    if (null == result) {
      result = this.entrySet = new EntrySet();
    }
    return result;
  }


  public Set<K> keySet() {
    KeySet result = this.keySet;
    if (null == result) {
      result = this.keySet = new KeySet();
    }
    return result;
  }

  static final class Node<K, V> implements Map.Entry<K, V> {
    Node<K, V> parent;
    Node<K, V> left;
    Node<K, V> right;
    Node<K, V> next;
    Node<K, V> prev;
    final K key;
    final boolean allowNullValue;
    V value;
    int height;

    /** Create the header entry */
    Node(final boolean allowNullValue) {
        this.key = null;
      this.allowNullValue = allowNullValue;
        this.next = this.prev = this;
    }

    /** Create a regular entry */
    Node(final boolean allowNullValue, final Node<K, V> parent, final K key, final Node<K, V> next, final Node<K, V> prev) {
      this.parent = parent;
      this.key = key;
      this.allowNullValue = allowNullValue;
      height = 1;
      this.next = next;
      this.prev = prev;
      prev.next = this;
      next.prev = this;
    }


    public K getKey() {
      return this.key;
    }


    public V getValue() {
      return this.value;
    }


    public V setValue(final V value) {
      if (null == value && !this.allowNullValue) {
        throw new NullPointerException("value == null");
      }
      final V oldValue = this.value;
      this.value = value;
      return oldValue;
    }


    public boolean equals(final Object o) {
      if (o instanceof Entry<?, ?> other) {
          return (null == key ? null == other.getKey() : this.key.equals(other.getKey()))
            && (null == value ? null == other.getValue() : this.value.equals(other.getValue()));
      }
      return false;
    }


    public int hashCode() {
      return (null == key ? 0 : this.key.hashCode()) ^ (null == value ? 0 : this.value.hashCode());
    }


    public String toString() {
      return this.key + "=" + this.value;
    }

    /** Returns the first node in this subtree. */
    public Node<K, V> first() {
      Node<K, V> node = this;
      Node<K, V> child = node.left;
      while (null != child) {
        node = child;
        child = node.left;
      }
      return node;
    }

    /** Returns the last node in this subtree. */
    public Node<K, V> last() {
      Node<K, V> node = this;
      Node<K, V> child = node.right;
      while (null != child) {
        node = child;
        child = node.right;
      }
      return node;
    }
  }

  private abstract class LinkedTreeMapIterator<T> implements Iterator<T> {
    Node<K, V> next = LinkedTreeMap.this.header.next;
    Node<K, V> lastReturned;
    int expectedModCount = LinkedTreeMap.this.modCount;

    LinkedTreeMapIterator() {}


    @SuppressWarnings("ReferenceEquality")
    public final boolean hasNext() {
      return this.next != LinkedTreeMap.this.header;
    }

    @SuppressWarnings("ReferenceEquality")
    final Node<K, V> nextNode() {
      final Node<K, V> e = this.next;
      if (e == LinkedTreeMap.this.header) {
        throw new NoSuchElementException();
      }
      if (LinkedTreeMap.this.modCount != this.expectedModCount) {
        throw new ConcurrentModificationException();
      }
        this.next = e.next;
        this.lastReturned = e;
      return e;
    }


    public final void remove() {
      if (null == lastReturned) {
        throw new IllegalStateException();
      }
        LinkedTreeMap.this.removeInternal(this.lastReturned, true);
        this.lastReturned = null;
        this.expectedModCount = LinkedTreeMap.this.modCount;
    }
  }

  class EntrySet extends AbstractSet<Map.Entry<K, V>> {

    public int size() {
      return LinkedTreeMap.this.size;
    }


    public Iterator<Map.Entry<K, V>> iterator() {
      return new LinkedTreeMapIterator<Map.Entry<K, V>>() {

        public Map.Entry<K, V> next() {
          return this.nextNode();
        }
      };
    }


    public boolean contains(final Object o) {
      return o instanceof Map.Entry && null != findByEntry((Entry<?, ?>) o);
    }


    public boolean remove(final Object o) {
      if (!(o instanceof Map.Entry)) {
        return false;
      }

      final Node<K, V> node = LinkedTreeMap.this.findByEntry((Map.Entry<?, ?>) o);
      if (null == node) {
        return false;
      }
        removeInternal(node, true);
      return true;
    }

    
    public void clear() {
      LinkedTreeMap.this.clear();
    }
  }

  final class KeySet extends AbstractSet<K> {
    
    public int size() {
      return size;
    }

    
    public Iterator<K> iterator() {
      return new LinkedTreeMapIterator<K>() {
        
        public K next() {
          return nextNode().key;
        }
      };
    }

    
    public boolean contains(Object o) {
      return containsKey(o);
    }

    
    public boolean remove(Object key) {
      return null != LinkedTreeMap.this.removeInternalByKey(key);
    }

    
    public void clear() {
      LinkedTreeMap.this.clear();
    }
  }

  /**
   * If somebody is unlucky enough to have to serialize one of these, serialize it as a
   * LinkedHashMap so that they won't need Gson on the other side to deserialize it. Using
   * serialization defeats our DoS defence, so most apps shouldn't use it.
   */
  private Object writeReplace() throws ObjectStreamException {
    return new LinkedHashMap<>(this);
  }

  private void readObject(ObjectInputStream in) throws IOException {
    // Don't permit directly deserializing this class; writeReplace() should have written a
    // replacement
    throw new InvalidObjectException("Deserialization is unsupported");
  }
}
