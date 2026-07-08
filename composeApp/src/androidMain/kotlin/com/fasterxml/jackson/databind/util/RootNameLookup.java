package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;

public class RootNameLookup implements Serializable {
    private static final long serialVersionUID = 1;
    protected transient LRUMap<ClassKey, PropertyName> _rootNames = new LRUMap<>(20, 200);
    public PropertyName findRootName(final JavaType javaType, final MapperConfig<?> mapperConfig) {
        return this.findRootName(javaType.getRawClass(), mapperConfig);
    }
    public PropertyName findRootName(final Class<?> cls, final MapperConfig<?> mapperConfig) {
        final ClassKey classKey = new ClassKey(cls);
        final PropertyName propertyName = _rootNames.get(classKey);
        if (null != propertyName) {
            return propertyName;
        }
        PropertyName propertyNameFindRootName = mapperConfig.getAnnotationIntrospector().findRootName(mapperConfig.introspectClassAnnotations(cls).getClassInfo());
        if (null == propertyNameFindRootName || !propertyNameFindRootName.hasSimpleName()) {
            propertyNameFindRootName = PropertyName.construct(cls.getSimpleName());
        }
        _rootNames.put(classKey, propertyNameFindRootName);
        return propertyNameFindRootName;
    }
    protected Object readResolve() {
        return new RootNameLookup();
    }
}
