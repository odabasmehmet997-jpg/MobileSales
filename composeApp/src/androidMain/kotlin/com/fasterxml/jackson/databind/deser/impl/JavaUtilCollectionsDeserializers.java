package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.ksoap2.serialization.MarshalHashtable;

import java.util.*;

public class JavaUtilCollectionsDeserializers {
    private static final Class<?> CLASS_AS_ARRAYS_LIST = Arrays.asList(null, null).getClass();
    private static final Class<?> CLASS_SINGLETON_LIST;
    private static final Class<?> CLASS_SINGLETON_MAP;
    private static final Class<?> CLASS_SINGLETON_SET;
    private static final Class<?> CLASS_UNMODIFIABLE_LIST;
    private static final Class<?> CLASS_UNMODIFIABLE_LIST_ALIAS;
    private static final Class<?> CLASS_UNMODIFIABLE_MAP;
    private static final Class<?> CLASS_UNMODIFIABLE_SET;
    static {
        final Boolean bool = Boolean.TRUE;
        final Set setSingleton = Collections.singleton(bool);
        CLASS_SINGLETON_SET = setSingleton.getClass();
        CLASS_UNMODIFIABLE_SET = Collections.unmodifiableSet(setSingleton).getClass();
        final List listSingletonList = Collections.singletonList(bool);
        CLASS_SINGLETON_LIST = listSingletonList.getClass();
        CLASS_UNMODIFIABLE_LIST = Collections.unmodifiableList(listSingletonList).getClass();
        CLASS_UNMODIFIABLE_LIST_ALIAS = Collections.unmodifiableList(new LinkedList()).getClass();
        final Map mapSingletonMap = Collections.singletonMap("a", "b");
        CLASS_SINGLETON_MAP = mapSingletonMap.getClass();
        CLASS_UNMODIFIABLE_MAP = Collections.unmodifiableMap(mapSingletonMap).getClass();
    }
    public static JsonDeserializer<?> findForCollection(final DeserializationContext deserializationContext, final JavaType javaType) throws JsonMappingException {
        final JavaUtilCollectionsConverter javaUtilCollectionsConverterConverter;
        if (javaType.hasRawClass(JavaUtilCollectionsDeserializers.CLASS_AS_ARRAYS_LIST)) {
            javaUtilCollectionsConverterConverter = JavaUtilCollectionsDeserializers.converter(11, javaType, List.class);
        } else if (javaType.hasRawClass(JavaUtilCollectionsDeserializers.CLASS_SINGLETON_LIST)) {
            javaUtilCollectionsConverterConverter = JavaUtilCollectionsDeserializers.converter(2, javaType, List.class);
        } else if (javaType.hasRawClass(JavaUtilCollectionsDeserializers.CLASS_SINGLETON_SET)) {
            javaUtilCollectionsConverterConverter = JavaUtilCollectionsDeserializers.converter(1, javaType, Set.class);
        } else if (javaType.hasRawClass(JavaUtilCollectionsDeserializers.CLASS_UNMODIFIABLE_LIST) || javaType.hasRawClass(JavaUtilCollectionsDeserializers.CLASS_UNMODIFIABLE_LIST_ALIAS)) {
            javaUtilCollectionsConverterConverter = JavaUtilCollectionsDeserializers.converter(5, javaType, List.class);
        } else if (javaType.hasRawClass(JavaUtilCollectionsDeserializers.CLASS_UNMODIFIABLE_SET)) {
            javaUtilCollectionsConverterConverter = JavaUtilCollectionsDeserializers.converter(4, javaType, Set.class);
        } else {
            final String str_findUtilSyncTypeName = JavaUtilCollectionsDeserializers._findUtilSyncTypeName(javaType.getRawClass());
            if (str_findUtilSyncTypeName.endsWith("Set")) {
                javaUtilCollectionsConverterConverter = JavaUtilCollectionsDeserializers.converter(7, javaType, Set.class);
            } else if (str_findUtilSyncTypeName.endsWith("List")) {
                javaUtilCollectionsConverterConverter = JavaUtilCollectionsDeserializers.converter(9, javaType, List.class);
            } else {
                if (!str_findUtilSyncTypeName.endsWith("Collection")) {
                    return null;
                }
                javaUtilCollectionsConverterConverter = JavaUtilCollectionsDeserializers.converter(8, javaType, Collection.class);
            }
        }
        return new StdDelegatingDeserializer(javaUtilCollectionsConverterConverter);
    }
    public static JsonDeserializer<?> findForMap(final DeserializationContext deserializationContext, final JavaType javaType) throws JsonMappingException {
        final JavaUtilCollectionsConverter javaUtilCollectionsConverterConverter;
        if (javaType.hasRawClass(JavaUtilCollectionsDeserializers.CLASS_SINGLETON_MAP)) {
            javaUtilCollectionsConverterConverter = JavaUtilCollectionsDeserializers.converter(3, javaType, Map.class);
        } else if (javaType.hasRawClass(JavaUtilCollectionsDeserializers.CLASS_UNMODIFIABLE_MAP)) {
            javaUtilCollectionsConverterConverter = JavaUtilCollectionsDeserializers.converter(6, javaType, Map.class);
        } else {
            if (!JavaUtilCollectionsDeserializers._findUtilSyncTypeName(javaType.getRawClass()).endsWith(MarshalHashtable.NAME)) {
                return null;
            }
            javaUtilCollectionsConverterConverter = JavaUtilCollectionsDeserializers.converter(10, javaType, Map.class);
        }
        return new StdDelegatingDeserializer(javaUtilCollectionsConverterConverter);
    }
    static JavaUtilCollectionsConverter converter(final int i2, final JavaType javaType, final Class<?> cls) {
        return new JavaUtilCollectionsConverter(i2, javaType.findSuperType(cls));
    }
    private static String _findUtilSyncTypeName(final Class<?> cls) {
        final String str_findUtilCollectionsTypeName = JavaUtilCollectionsDeserializers._findUtilCollectionsTypeName(cls);
        if (null != str_findUtilCollectionsTypeName && str_findUtilCollectionsTypeName.startsWith("Synchronized")) {
            return str_findUtilCollectionsTypeName.substring(12);
        }
        return "";
    }
    private static String _findUtilCollectionsTypeName(final Class<?> cls) {
        final String name = cls.getName();
        if (name.startsWith("java.util.Collections")) {
            return name.substring(22);
        }
        return "";
    }
    private record JavaUtilCollectionsConverter(int _kind, JavaType _inputType) implements Converter<Object, Object> {
        public Object convert(final Object obj) {
                if (null == obj) {
                    return null;
                }
                switch (_kind) {
                    case 1:
                        final Set set = (Set) obj;
                        this._checkSingleton(set.size());
                        return Collections.singleton(set.iterator().next());
                    case 2:
                        final List list = (List) obj;
                        this._checkSingleton(list.size());
                        return Collections.singletonList(list.get(0));
                    case 3:
                        final Map map = (Map) obj;
                        this._checkSingleton(map.size());
                        final Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
                        return Collections.singletonMap(entry.getKey(), entry.getValue());
                    case 4:
                        return Collections.unmodifiableSet((Set) obj);
                    case 5:
                        return Collections.unmodifiableList((List) obj);
                    case 6:
                        return Collections.unmodifiableMap((Map) obj);
                    case 7:
                        return Collections.synchronizedSet((Set) obj);
                    case 8:
                        return Collections.synchronizedCollection((Collection) obj);
                    case 9:
                        return Collections.synchronizedList((List) obj);
                    case 10:
                        return Collections.synchronizedMap((Map) obj);
                    default:
                        return obj;
                }
            }
        public JavaType getInputType(final TypeFactory typeFactory) {
                return _inputType;
            }
        public JavaType getOutputType(final TypeFactory typeFactory) {
                return _inputType;
            }
        private void _checkSingleton(final int i2) {
                if (1 == i2) {
                    return;
                }
                throw new IllegalArgumentException("Can not deserialize Singleton container from " + i2 + " entries");
            }
        }
}
