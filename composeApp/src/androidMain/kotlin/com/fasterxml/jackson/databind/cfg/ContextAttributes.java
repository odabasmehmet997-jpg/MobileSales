package com.fasterxml.jackson.databind.cfg;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class ContextAttributes {
    public abstract Object getAttribute(Object obj);
    public abstract ContextAttributes withPerCallAttribute(Object obj, Object obj2);
    public abstract ContextAttributes withSharedAttribute(Object obj, Object obj2);
    public abstract ContextAttributes withSharedAttributes(Map<?, ?> map);
    public abstract ContextAttributes withoutSharedAttribute(Object obj);
    public static ContextAttributes getEmpty() {
        return Impl.getEmpty();
    }
    public static class Impl extends ContextAttributes implements Serializable {
        protected static final Impl EMPTY = new Impl(Collections.emptyMap());
        protected static final Object NULL_SURROGATE = new Object();
        private static final long serialVersionUID = 1;
        protected transient Map<Object, Object> _nonShared;
        protected final Map<?, ?> _shared;

        protected Impl(final Map<?, ?> map) {
            _shared = map;
            _nonShared = null;
        }

        protected Impl(final Map<?, ?> map, final Map<Object, Object> map2) {
            _shared = map;
            _nonShared = map2;
        }

        public static ContextAttributes getEmpty() {
            return Impl.EMPTY;
        }

        @Override // com.fasterxml.jackson.databind.cfg.ContextAttributes
        public ContextAttributes withSharedAttribute(final Object obj, final Object obj2) {
            final Map<Object, Object> map_copy;
            if (this == Impl.EMPTY) {
                map_copy = new HashMap<>(8);
            } else {
                map_copy = this._copy(_shared);
            }
            map_copy.put(obj, obj2);
            return new Impl(map_copy);
        }

        @Override // com.fasterxml.jackson.databind.cfg.ContextAttributes
        public ContextAttributes withSharedAttributes(final Map<?, ?> map) {
            return new Impl(map);
        }

        @Override // com.fasterxml.jackson.databind.cfg.ContextAttributes
        public ContextAttributes withoutSharedAttribute(final Object obj) {
            if (_shared.isEmpty() || !_shared.containsKey(obj)) {
                return this;
            }
            if (1 == this._shared.size()) {
                return Impl.EMPTY;
            }
            final Map<Object, Object> map_copy = this._copy(_shared);
            map_copy.remove(obj);
            return new Impl(map_copy);
        }

        @Override // com.fasterxml.jackson.databind.cfg.ContextAttributes
        public Object getAttribute(final Object obj) {
            final Object obj2;
            final Map<Object, Object> map = _nonShared;
            if (null != map && null != (obj2 = map.get(obj))) {
                if (obj2 == Impl.NULL_SURROGATE) {
                    return null;
                }
                return obj2;
            }
            return _shared.get(obj);
        }

        @Override // com.fasterxml.jackson.databind.cfg.ContextAttributes
        public ContextAttributes withPerCallAttribute(final Object obj, Object obj2) {
            if (null == obj2) {
                if (_shared.containsKey(obj)) {
                    obj2 = Impl.NULL_SURROGATE;
                } else {
                    final Map<Object, Object> map = _nonShared;
                    if (null != map && map.containsKey(obj)) {
                        _nonShared.remove(obj);
                    }
                    return this;
                }
            }
            final Map<Object, Object> map2 = _nonShared;
            if (null == map2) {
                return this.nonSharedInstance(obj, obj2);
            }
            map2.put(obj, obj2);
            return this;
        }

        protected ContextAttributes nonSharedInstance(final Object obj, Object obj2) {
            final HashMap map = new HashMap();
            if (null == obj2) {
                obj2 = Impl.NULL_SURROGATE;
            }
            map.put(obj, obj2);
            return new Impl(_shared, map);
        }

        private Map<Object, Object> _copy(final Map<?, ?> map) {
            return new HashMap(map);
        }
    }
}
