package com.fasterxml.jackson.annotation;

public class SimpleObjectIdResolver implements ObjectIdResolver {
    protected Map<ObjectIdGenerator.IdKey, Object> _items;

    public void bindItem(final ObjectIdGenerator.IdKey idKey, final Object obj) {
        final Map<ObjectIdGenerator.IdKey, Object> map = _items;
        if (null == map) {
            _items = new HashMap();
        } else {
            final Object obj2 = map.get(idKey);
            if (null != obj2) {
                if (obj2 == obj) {
                    return;
                }
                throw new IllegalStateException("Already had POJO for id (" + idKey.key.getClass().getName() + ") [" + idKey + "]");
            }
        }
        _items.put(idKey, obj);
    }
    public Object resolveId(final ObjectIdGenerator.IdKey idKey) {
        final Map<ObjectIdGenerator.IdKey, Object> map = _items;
        if (null == map) {
            return null;
        }
        return map.get(idKey);
    }
    public boolean canUseFor(final ObjectIdResolver objectIdResolver) {
        return objectIdResolver.getClass() == this.getClass();
    }
    public ObjectIdResolver newForDeserialization(final Object obj) {
        return new SimpleObjectIdResolver();
    }
}
