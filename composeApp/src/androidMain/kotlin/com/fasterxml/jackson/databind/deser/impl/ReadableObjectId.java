package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class ReadableObjectId {
    protected Object _item;
    protected final ObjectIdGenerator.IdKey _key;
    protected LinkedList<Referring> _referringProperties;
    protected ObjectIdResolver _resolver;
    public boolean tryToResolveUnresolved(final DeserializationContext deserializationContext) {
        return false;
    }
    public ReadableObjectId(final ObjectIdGenerator.IdKey idKey) {
        _key = idKey;
    }
    public void setResolver(final ObjectIdResolver objectIdResolver) {
        _resolver = objectIdResolver;
    }
    public ObjectIdGenerator.IdKey getKey() {
        return _key;
    }
    public void appendReferring(final Referring referring) {
        if (null == this._referringProperties) {
            _referringProperties = new LinkedList<>();
        }
        _referringProperties.add(referring);
    }
    public void bindItem(final Object obj) throws IOException {
        _resolver.bindItem(_key, obj);
        _item = obj;
        final Object obj2 = _key.key;
        final LinkedList<Referring> linkedList = _referringProperties;
        if (null != linkedList) {
            final Iterator<Referring> it = linkedList.iterator();
            _referringProperties = null;
            while (it.hasNext()) {
                it.next().handleResolvedForwardReference(obj2, obj);
            }
        }
    }
    public Object resolve() {
        final Object objResolveId = _resolver.resolveId(_key);
        _item = objResolveId;
        return objResolveId;
    }
    public boolean hasReferringProperties() {
        final LinkedList<Referring> linkedList = _referringProperties;
        return null != linkedList && !linkedList.isEmpty();
    }
    public Iterator<? extends Object> referringProperties() {
        final LinkedList<Referring> linkedList = _referringProperties;
        if (null == linkedList) {
            return Collections.emptyIterator();
        }
        return linkedList.iterator();
    }
    public String toString() {
        return String.valueOf(_key);
    }
    public static abstract class Referring {
        private final Class<?> _beanType;
        private final UnresolvedForwardReference _reference;

        public abstract void handleResolvedForwardReference(Object obj, Object obj2) throws IOException;

        protected Referring(final UnresolvedForwardReference unresolvedForwardReference, final Class<?> cls) {
            _reference = unresolvedForwardReference;
            _beanType = cls;
        }

        protected Referring(final UnresolvedForwardReference unresolvedForwardReference, final JavaType javaType) {
            _reference = unresolvedForwardReference;
            _beanType = javaType.getRawClass();
        }

        public JsonLocation getLocation() {
            return _reference.getLocation();
        }

        public Class<?> getBeanType() {
            return _beanType;
        }

        public boolean hasId(final Object obj) {
            return obj.equals(_reference.getUnresolvedId());
        }
    }
}
