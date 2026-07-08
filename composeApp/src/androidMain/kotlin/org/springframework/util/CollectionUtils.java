package org.springframework.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
public abstract class CollectionUtils {
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }
    public static <K, V> MultiValueMap<K, V> toMultiValueMap(Map<K, List<V>> map) {
        return new MultiValueMapAdapter(map);
    }
    public static <K, V> MultiValueMap<K, V> unmodifiableMultiValueMap(MultiValueMap<? extends K, ? extends V> multiValueMap) {
        Assert.notNull(multiValueMap, "'map' must not be null");
        LinkedHashMap<K, List<V>> linkedHashMap = new LinkedHashMap<>(multiValueMap.size());
        for (Map.Entry<? extends K, ? extends List<? extends V>> entry : multiValueMap.entrySet()) {
            linkedHashMap.put(entry.getKey(), Collections.unmodifiableList(entry.getValue()));
        }
        return toMultiValueMap(Collections.unmodifiableMap(linkedHashMap));
    }
    private static class MultiValueMapAdapter<K, V> implements MultiValueMap<K, V>, Serializable {
        private final Map<K, List<V>> map;

        public MultiValueMapAdapter(Map<K, List<V>> map) {
            Assert.notNull(map, "'map' must not be null");
            this.map = map;
        }
 
        public void add(K k2, V v) {
            List<V> linkedList = this.map.get(k2);
            if (linkedList == null) {
                linkedList = new LinkedList<>();
                this.map.put(k2, linkedList);
            }
            linkedList.add(v);
        }

        public V getFirst(K k2) {
            List<V> list = this.map.get(k2);
            if (list != null) {
                return list.get(0);
            }
            return null;
        }

        public void set(K k2, V v) {
            LinkedList linkedList = new LinkedList();
            linkedList.add(v);
            this.map.put(k2, linkedList);
        }

        public void setAll(Map<K, V> map) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                set(entry.getKey(), entry.getValue());
            }
        }

        public Map<K, V> toSingleValueMap() {
            LinkedHashMap linkedHashMap = new LinkedHashMap(this.map.size());
            for (Map.Entry<K, List<V>> entry : this.map.entrySet()) {
                linkedHashMap.put(entry.getKey(), entry.getValue().get(0));
            }
            return linkedHashMap;
        }

        
        public int size() {
            return this.map.size();
        }

        
        public boolean isEmpty() {
            return this.map.isEmpty();
        }

        
        public boolean containsKey(Object obj) {
            return this.map.containsKey(obj);
        }

        
        public boolean containsValue(Object obj) {
            return this.map.containsValue(obj);
        }

        
        public List<V> get(Object obj) {
            return this.map.get(obj);
        }

        
        public List<V> put(K k2, List<V> list) {
            return this.map.put(k2, list);
        }

        
        public List<V> remove(Object obj) {
            return this.map.remove(obj);
        }

        
        public void putAll(Map<? extends K, ? extends List<V>> map) {
            this.map.putAll(map);
        }

        
        public void clear() {
            this.map.clear();
        }

        
        public Set<K> keySet() {
            return this.map.keySet();
        }

        
        public Collection<List<V>> values() {
            return this.map.values();
        }

        
        public Set<Map.Entry<K, List<V>>> entrySet() {
            return this.map.entrySet();
        }

        
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return this.map.equals(obj);
        }

        
        public int hashCode() {
            return this.map.hashCode();
        }

        public String toString() {
            return this.map.toString();
        }
    }
}
