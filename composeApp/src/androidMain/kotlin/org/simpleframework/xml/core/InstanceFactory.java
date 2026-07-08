package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

import java.lang.reflect.Constructor;


class InstanceFactory {
    private final Cache<Constructor> cache = new ConcurrentCache();

    public Instance getInstance(final Value value) {
        return new ValueInstance(value);
    }

    public Instance getInstance(final Class cls) {
        return new ClassInstance(cls);
    }

    protected Object getObject(final Class cls) throws Exception {
        Constructor fetch = cache.fetch(cls);
        if (null == fetch) {
            fetch = cls.getDeclaredConstructor(null);
            if (!fetch.isAccessible()) {
                fetch.setAccessible(true);
            }
            cache.cache(cls, fetch);
        }
        return fetch.newInstance(null);
    }

    private class ValueInstance implements Instance {
        private final Class type;
        private final Value value;

        public ValueInstance(final Value value) {
            type = value.getType();
            this.value = value;
        }

        @Override // org.simpleframework.xml.core.Instance
        public Object getInstance() throws Exception {
            if (value.isReference()) {
                return value.getValue();
            }
            final Object object = getObject(type);
            final Value value = this.value;
            if (null != value) {
                value.setValue(object);
            }
            return object;
        }

        @Override // org.simpleframework.xml.core.Instance
        public Object setInstance(final Object obj) {
            final Value value = this.value;
            if (null != value) {
                value.setValue(obj);
            }
            return obj;
        }

        @Override // org.simpleframework.xml.core.Instance
        public boolean isReference() {
            return value.isReference();
        }

        @Override // org.simpleframework.xml.core.Instance
        public Class getType() {
            return type;
        }
    }

    private class ClassInstance implements Instance {
        private final Class type;
        private Object value;

        @Override // org.simpleframework.xml.core.Instance
        public boolean isReference() {
            return false;
        }

        public ClassInstance(final Class cls) {
            type = cls;
        }

        @Override // org.simpleframework.xml.core.Instance
        public Object getInstance() throws Exception {
            if (null == this.value) {
                value = getObject(type);
            }
            return value;
        }

        @Override // org.simpleframework.xml.core.Instance
        public Object setInstance(final Object obj) throws Exception {
            value = obj;
            return obj;
        }

        @Override // org.simpleframework.xml.core.Instance
        public Class getType() {
            return type;
        }
    }
}
