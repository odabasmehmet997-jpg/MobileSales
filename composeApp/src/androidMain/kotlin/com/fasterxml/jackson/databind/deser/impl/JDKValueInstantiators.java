package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.JsonLocationInstantiator;

import java.io.IOException;
import java.util.*;

public class JDKValueInstantiators {
    public static ValueInstantiator findStdValueInstantiator(final DeserializationConfig deserializationConfig, final Class<?> cls) {
        if (JsonLocation.class == cls) {
            return new JsonLocationInstantiator();
        }
        if (Collection.class.isAssignableFrom(cls)) {
            if (ArrayList.class == cls) {
                return ArrayListInstantiator.INSTANCE;
            }
            final Set set = Collections.emptySet();
            if (set.getClass() == cls) {
                return new ConstantValueInstantiator(set);
            }
            final List list = Collections.emptyList();
            if (list.getClass() == cls) {
                return new ConstantValueInstantiator(list);
            }
            return null;
        }
        if (!Map.class.isAssignableFrom(cls)) {
            return null;
        }
        if (LinkedHashMap.class == cls) {
            return LinkedHashMapInstantiator.INSTANCE;
        }
        if (HashMap.class == cls) {
            return HashMapInstantiator.INSTANCE;
        }
        final Map map = Collections.emptyMap();
        if (map.getClass() == cls) {
            return new ConstantValueInstantiator(map);
        }
        return null;
    }
    private static class ArrayListInstantiator extends ValueInstantiator.Base {
        public static final ArrayListInstantiator INSTANCE = new ArrayListInstantiator();
        private static final long serialVersionUID = 2;

        public boolean canCreateUsingDefault() {
            return true;
        }
        public boolean canInstantiate() {
            return true;
        }

        public ArrayListInstantiator() {
            super(ArrayList.class);
        }
        public Object createUsingDefault(final DeserializationContext deserializationContext) throws IOException {
            return new ArrayList();
        }
    }
    private static class HashMapInstantiator extends ValueInstantiator.Base {
        public static final HashMapInstantiator INSTANCE = new HashMapInstantiator();
        private static final long serialVersionUID = 2;
        public boolean canCreateUsingDefault() {
            return true;
        }
        public boolean canInstantiate() {
            return true;
        }
        public HashMapInstantiator() {
            super(HashMap.class);
        }
        public Object createUsingDefault(final DeserializationContext deserializationContext) throws IOException {
            return new HashMap();
        }
    }
    private static class LinkedHashMapInstantiator extends ValueInstantiator.Base {
        public static final LinkedHashMapInstantiator INSTANCE = new LinkedHashMapInstantiator();
        private static final long serialVersionUID = 2;
        public boolean canCreateUsingDefault() {
            return true;
        }
        public boolean canInstantiate() {
            return true;
        }
        public LinkedHashMapInstantiator() {
            super(LinkedHashMap.class);
        }
        public Object createUsingDefault(final DeserializationContext deserializationContext) throws IOException {
            return new LinkedHashMap();
        }
    }
    private static class ConstantValueInstantiator extends ValueInstantiator.Base {
        private static final long serialVersionUID = 2;
        protected final Object _value;
        public boolean canCreateUsingDefault() {
            return true;
        }
        public boolean canInstantiate() {
            return true;
        }
        public ConstantValueInstantiator(final Object obj) {
            super(obj.getClass());
            _value = obj;
        }
        public Object createUsingDefault(final DeserializationContext deserializationContext) throws IOException {
            return _value;
        }
    }
}
