package com.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class LRUMap<K, V> implements LookupCache<K, V>, Serializable {
    private static final long serialVersionUID = 1;
    protected transient int _jdkSerializeMaxEntries;
    protected final transient ConcurrentHashMap<K, V> _map;
    protected final transient int _maxEntries;

    public LRUMap(final int i2, final int i3) {
        _map = new ConcurrentHashMap<>(i2, 0.8f, 4);
        _maxEntries = i3;
    }

    public V put(final K k2, final V v) {
        if (_map.size() >= _maxEntries) {
            synchronized (this) {
                if (this._map.size() >= this._maxEntries) {
                    clear();
                }
            }
        }
        return _map.put(k2, v);
    }

    public V putIfAbsent(final K k2, final V v) {
        if (_map.size() >= _maxEntries) {
            synchronized (this) {
                if (this._map.size() >= this._maxEntries) {
                    clear();
                }
            }
        }
        return _map.putIfAbsent(k2, v);
    }

    public V get(final Object obj) {
        return _map.get(obj);
    }

    public void clear() {
        _map.clear();
    }

    public int size() {
        return _map.size();
    }

    private void readObject(final ObjectInputStream objectInputStream) throws IOException {
        _jdkSerializeMaxEntries = objectInputStream.readInt();
    }

    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(_jdkSerializeMaxEntries);
    }

    protected Object readResolve() {
        final int i2 = _jdkSerializeMaxEntries;
        return new LRUMap(i2, i2);
    }
}
