package com.fasterxml.jackson.annotation;

public interface ObjectIdResolver {
    void bindItem(ObjectIdGenerator.IdKey idKey, Object obj);
    boolean canUseFor(ObjectIdResolver objectIdResolver);
    ObjectIdResolver newForDeserialization(Object obj);
    Object resolveId(ObjectIdGenerator.IdKey idKey);
}
