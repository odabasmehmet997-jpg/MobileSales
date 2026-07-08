package org.springframework.util;

import java.io.Serializable;
import java.util.*;

public class LinkedMultiValueMap<K, V> implements MultiValueMap<K, V>, Serializable {
    private static final long serialVersionUID = 3801124242820219131L;
    private final Map<K, List<V>> targetMap;        
    public LinkedMultiValueMap() {
        targetMap = new LinkedHashMap();
    }
    public LinkedMultiValueMap(final int i2) {
        targetMap = new LinkedHashMap(i2);
    }
    public LinkedMultiValueMap(final Map<K, List<V>> map) {
        targetMap = new LinkedHashMap(map);
    }
    public void add(final K k2, final V v) {
        List<V> list = targetMap.get(k2);
        if (null == list) {
            list = new LinkedList<>();
            targetMap.put(k2, list);
        }
        list.add(v);
    }
    public V getFirst(final K k2) {
        final List<V> list = targetMap.get(k2);
        if (null != list) {
            return list.get(0);
        }
        return null;
    }
    public void set(final K k2, final V v) {
        final LinkedList<V> linkedList = new LinkedList<>();
        linkedList.add(v);
        targetMap.put(k2, linkedList);
    }
    public void setAll(final Map<K, V> map) {
        for (final Entry<K, V> entry : map.entrySet()) {
            this.set(entry.getKey(), entry.getValue());
        }
    }
    public Map<K, V> toSingleValueMap() {
        final LinkedHashMap<K, V> linkedHashMap = new LinkedHashMap<>(targetMap.size());
        for (final Entry<K, List<V>> entry : targetMap.entrySet()) {
            linkedHashMap.put(entry.getKey(), entry.getValue().get(0));
        }
        return linkedHashMap;
    }
    public int size() {
        return targetMap.size();
    }
    public boolean isEmpty() {
        return targetMap.isEmpty();
    }
    public boolean containsKey(final Object obj) {
        return targetMap.containsKey(obj);
    }
    public boolean containsValue(final Object obj) {
        return targetMap.containsValue(obj);
    }
    public List<V> get(final Object obj) {
        return targetMap.get(obj);
    }
    public List<V> put(final K k2, final List<V> list) {
        return targetMap.put(k2, list);
    }
    public List<V> remove(final Object obj) {
        return targetMap.remove(obj);
    }
    public void putAll(final Map<? extends K, ? extends List<V>> map) {
        targetMap.putAll(map);
    }
    public void clear() {
        targetMap.clear();
    }
    public Set<K> keySet() {
        return targetMap.keySet();
    }
    public Collection<List<V>> values() {
        return targetMap.values();
    }
    public Set<Entry<K, List<V>>> entrySet() {
        return targetMap.entrySet();
    }
    public boolean equals(final Object obj) {
        return targetMap.equals(obj);
    }
    public int hashCode() {
        return targetMap.hashCode();
    }
    public String toString() {
        return targetMap.toString();
    }
}
