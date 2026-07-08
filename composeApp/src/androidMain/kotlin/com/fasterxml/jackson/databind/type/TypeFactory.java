package com.fasterxml.jackson.databind.type;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.fasterxml.jackson.databind.util.LookupCache;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
 
public class TypeFactory implements Serializable {
    private static final Class<?> CLS_BOOL;
    private static final Class<?> CLS_INT;
    private static final Class<?> CLS_LONG;
    protected static final SimpleType CORE_TYPE_BOOL;
    protected static final SimpleType CORE_TYPE_CLASS;
    protected static final SimpleType CORE_TYPE_COMPARABLE;
    protected static final SimpleType CORE_TYPE_ENUM;
    protected static final SimpleType CORE_TYPE_INT;
    protected static final SimpleType CORE_TYPE_JSON_NODE;
    protected static final SimpleType CORE_TYPE_LONG;
    protected static final SimpleType CORE_TYPE_OBJECT;
    protected static final SimpleType CORE_TYPE_STRING;
    private static final long serialVersionUID = 1;
    protected final ClassLoader _classLoader;
    protected final TypeModifier[] _modifiers;
    protected final TypeParser _parser;
    protected final LookupCache<Object, JavaType> _typeCache;
    private static final JavaType[] NO_TYPES = new JavaType[0];
    protected static final TypeFactory instance = new TypeFactory();
    protected static final TypeBindings EMPTY_BINDINGS = TypeBindings.emptyBindings();
    private static final Class<?> CLS_STRING = String.class;
    private static final Class<?> CLS_OBJECT = Object.class;
    private static final Class<?> CLS_COMPARABLE = Comparable.class;
    private static final Class<?> CLS_CLASS = Class.class;
    private static final Class<?> CLS_ENUM = Enum.class;
    private static final Class<?> CLS_JSON_NODE = JsonNode.class;

    static {
        final Class<?> cls = Boolean.TYPE;
        CLS_BOOL = cls;
        final Class<?> cls2 = Integer.TYPE;
        CLS_INT = cls2;
        final Class<?> cls3 = Long.TYPE;
        CLS_LONG = cls3;
        CORE_TYPE_BOOL = new SimpleType(cls);
        CORE_TYPE_INT = new SimpleType(cls2);
        CORE_TYPE_LONG = new SimpleType(cls3);
        CORE_TYPE_STRING = new SimpleType(String.class);
        CORE_TYPE_OBJECT = new SimpleType(Object.class);
        CORE_TYPE_COMPARABLE = new SimpleType(Comparable.class);
        CORE_TYPE_ENUM = new SimpleType(Enum.class);
        CORE_TYPE_CLASS = new SimpleType(Class.class);
        CORE_TYPE_JSON_NODE = new SimpleType(JsonNode.class);
    }

    private TypeFactory() {
        this((LookupCache<Object, JavaType>) null);
    }

    
    protected TypeFactory(final LRUMap<Object, JavaType> lRUMap) {
        this((LookupCache<Object, JavaType>) lRUMap);
    }

    protected TypeFactory(final LookupCache<Object, JavaType> lookupCache) {
        _typeCache = null == lookupCache ? new LRUMap<>(16, 200) : lookupCache;
        _parser = new TypeParser(this);
        _modifiers = null;
        _classLoader = null;
    }

    
    protected TypeFactory(final LRUMap<Object, JavaType> lRUMap, final TypeParser typeParser, final TypeModifier[] typeModifierArr, final ClassLoader classLoader) {
        this((LookupCache<Object, JavaType>) lRUMap, typeParser, typeModifierArr, classLoader);
    }

    protected TypeFactory(final LookupCache<Object, JavaType> lookupCache, final TypeParser typeParser, final TypeModifier[] typeModifierArr, final ClassLoader classLoader) {
        _typeCache = null == lookupCache ? new LRUMap<>(16, 200) : lookupCache;
        _parser = typeParser.withFactory(this);
        _modifiers = typeModifierArr;
        _classLoader = classLoader;
    }

    public TypeFactory withModifier(final TypeModifier typeModifier) {
        LookupCache<Object, JavaType> lookupCache = _typeCache;
        TypeModifier[] typeModifierArr = null;
        if (null == typeModifier) {
            lookupCache = null;
        } else {
            final TypeModifier[] typeModifierArr2 = _modifiers;
            if (null == typeModifierArr2) {
                typeModifierArr = new TypeModifier[]{typeModifier};
                lookupCache = null;
            } else {
                typeModifierArr = ArrayBuilders.insertInListNoDup(typeModifierArr2, typeModifier);
            }
        }
        return new TypeFactory(lookupCache, _parser, typeModifierArr, _classLoader);
    }

    public TypeFactory withClassLoader(final ClassLoader classLoader) {
        return new TypeFactory(_typeCache, _parser, _modifiers, classLoader);
    }

    
    public TypeFactory withCache(final LRUMap<Object, JavaType> lRUMap) {
        return new TypeFactory(lRUMap, _parser, _modifiers, _classLoader);
    }

    public TypeFactory withCache(final LookupCache<Object, JavaType> lookupCache) {
        return new TypeFactory(lookupCache, _parser, _modifiers, _classLoader);
    }

    public static TypeFactory defaultInstance() {
        return TypeFactory.instance;
    }

    public void clearCache() {
        _typeCache.clear();
    }

    public ClassLoader getClassLoader() {
        return _classLoader;
    }

    public static JavaType unknownType() {
        return TypeFactory.defaultInstance()._unknownType();
    }

    public static Class<?> rawClass(final Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        return TypeFactory.defaultInstance().constructType(type).getRawClass();
    }

    public Class<?> findClass(final String str) throws ClassNotFoundException {
        Throwable rootCause;
        final Class<?> cls_findPrimitive;
        if (0 > str.indexOf(46) && null != (cls_findPrimitive = _findPrimitive(str))) {
            return cls_findPrimitive;
        }
        ClassLoader classLoader = this._classLoader;
        if (null == classLoader) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        if (null != classLoader) {
            try {
                return this.classForName(str, true, classLoader);
            } catch (final Exception e2) {
                rootCause = ClassUtil.getRootCause(e2);
            }
        } else {
            rootCause = null;
        }
        try {
            return this.classForName(str);
        } catch (final Exception e3) {
            if (null == rootCause) {
                rootCause = ClassUtil.getRootCause(e3);
            }
            ClassUtil.throwIfRTE(rootCause);
            throw new ClassNotFoundException(rootCause.getMessage(), rootCause);
        }
    }

    protected Class<?> classForName(final String str, final boolean z, final ClassLoader classLoader) throws ClassNotFoundException {
        return Class.forName(str, true, classLoader);
    }

    protected Class<?> classForName(final String str) throws ClassNotFoundException {
        return Class.forName(str);
    }

    protected Class<?> _findPrimitive(final String str) {
        if ("int".equals(str)) {
            return Integer.TYPE;
        }
        if ("long".equals(str)) {
            return Long.TYPE;
        }
        if (TypedValues.Custom.S_FLOAT.equals(str)) {
            return Float.TYPE;
        }
        if ("double".equals(str)) {
            return Double.TYPE;
        }
        if (TypedValues.Custom.S_BOOLEAN.equals(str)) {
            return Boolean.TYPE;
        }
        if ("byte".equals(str)) {
            return Byte.TYPE;
        }
        if ("char".equals(str)) {
            return Character.TYPE;
        }
        if ("short".equals(str)) {
            return Short.TYPE;
        }
        if ("void".equals(str)) {
            return Void.TYPE;
        }
        return null;
    }

    public JavaType constructSpecializedType(final JavaType javaType, final Class<?> cls) throws IllegalArgumentException {
        return this.constructSpecializedType(javaType, cls, false);
    }

    public JavaType constructSpecializedType(final JavaType javaType, final Class<?> cls, final boolean z) throws IllegalArgumentException {
        int length = 0;
        JavaType javaType_fromClass = null;
        final Class<?> rawClass = javaType.getRawClass();
        if (rawClass == cls) {
            return javaType;
        }
        if (Object.class == rawClass) {
            javaType_fromClass = this._fromClass(null, cls, TypeFactory.EMPTY_BINDINGS);
        } else {
            if (!rawClass.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(String.format("Class %s not subtype of %s", ClassUtil.nameOf(cls), ClassUtil.getTypeDescription(javaType)));
            }
            if (javaType.isContainerType()) {
                if (javaType.isMapLikeType()) {
                    if (HashMap.class == cls || LinkedHashMap.class == cls || EnumMap.class == cls || TreeMap.class == cls) {
                        javaType_fromClass = this._fromClass(null, cls, TypeBindings.create(cls, javaType.getKeyType(), javaType.getContentType()));
                    }
                } else if (javaType.isCollectionLikeType()) {
                    if (ArrayList.class == cls || LinkedList.class == cls || HashSet.class == cls || TreeSet.class == cls) {
                        javaType_fromClass = this._fromClass(null, cls, TypeBindings.create(cls, javaType.getContentType()));
                    } else {
                        if (EnumSet.class == rawClass) {
                            return javaType;
                        }
                        if (!javaType.getBindings().isEmpty()) {
                            javaType_fromClass = this._fromClass(null, cls, TypeFactory.EMPTY_BINDINGS);
                        } else {
                            javaType_fromClass = this._fromClass(null, cls, this._bindingsForSubtype(javaType, length, cls, z));
                        }
                    }
                }
            } else if (!javaType.getBindings().isEmpty() || 0 == (length = cls.getTypeParameters().length)) {
                javaType_fromClass = this._fromClass(null, cls, TypeFactory.EMPTY_BINDINGS);
            } else {
                javaType_fromClass = this._fromClass(null, cls, this._bindingsForSubtype(javaType, length, cls, z));
            }
        }
        return javaType_fromClass.withHandlersFrom(javaType);
    }

    private TypeBindings _bindingsForSubtype(final JavaType javaType, final int i2, final Class<?> cls, final boolean z) throws IllegalArgumentException {
        final PlaceholderForType[] placeholderForTypeArr = new PlaceholderForType[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            placeholderForTypeArr[i3] = new PlaceholderForType(i3);
        }
        final JavaType javaTypeFindSuperType = this._fromClass(null, cls, TypeBindings.create(cls, placeholderForTypeArr)).findSuperType(javaType.getRawClass());
        if (null == javaTypeFindSuperType) {
            throw new IllegalArgumentException(String.format("Internal error: unable to locate supertype (%s) from resolved subtype %s", javaType.getRawClass().getName(), cls.getName()));
        }
        final String str_resolveTypePlaceholders = this._resolveTypePlaceholders(javaType, javaTypeFindSuperType);
        if (null != str_resolveTypePlaceholders && !z) {
            throw new IllegalArgumentException("Failed to specialize base type " + javaType.toCanonical() + " as " + cls.getName() + ", problem: " + str_resolveTypePlaceholders);
        }
        final JavaType[] javaTypeArr = new JavaType[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            JavaType javaTypeActualType = placeholderForTypeArr[i4].actualType();
            if (null == javaTypeActualType) {
                javaTypeActualType = TypeFactory.unknownType();
            }
            javaTypeArr[i4] = javaTypeActualType;
        }
        return TypeBindings.create(cls, javaTypeArr);
    }

    private String _resolveTypePlaceholders(final JavaType javaType, final JavaType javaType2) throws IllegalArgumentException {
        final List<JavaType> typeParameters = javaType.getBindings().getTypeParameters();
        final List<JavaType> typeParameters2 = javaType2.getBindings().getTypeParameters();
        final int size = typeParameters2.size();
        final int size2 = typeParameters.size();
        int i2 = 0;
        while (i2 < size2) {
            final JavaType javaType3 = typeParameters.get(i2);
            final JavaType javaTypeUnknownType = i2 < size ? typeParameters2.get(i2) : TypeFactory.unknownType();
            if (!this._verifyAndResolvePlaceholders(javaType3, javaTypeUnknownType) && !javaType3.hasRawClass(Object.class) && ((0 != i2 || !javaType.isMapLikeType() || !javaTypeUnknownType.hasRawClass(Object.class)) && (!javaType3.isInterface() || !javaType3.isTypeOrSuperTypeOf(javaTypeUnknownType.getRawClass())))) {
                return String.format("Type parameter #%d/%d differs; can not specialize %s with %s", Integer.valueOf(i2 + 1), Integer.valueOf(size2), javaType3.toCanonical(), javaTypeUnknownType.toCanonical());
            }
            i2++;
        }
        return null;
    }

    private boolean _verifyAndResolvePlaceholders(final JavaType javaType, final JavaType javaType2) {
        if (javaType2 instanceof PlaceholderForType) {
            ((PlaceholderForType) javaType2).actualType(javaType);
            return true;
        }
        if (javaType.getRawClass() != javaType2.getRawClass()) {
            return false;
        }
        final List<JavaType> typeParameters = javaType.getBindings().getTypeParameters();
        final List<JavaType> typeParameters2 = javaType2.getBindings().getTypeParameters();
        final int size = typeParameters.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this._verifyAndResolvePlaceholders(typeParameters.get(i2), typeParameters2.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public JavaType constructGeneralizedType(final JavaType javaType, final Class<?> cls) {
        final Class<?> rawClass = javaType.getRawClass();
        if (rawClass == cls) {
            return javaType;
        }
        final JavaType javaTypeFindSuperType = javaType.findSuperType(cls);
        if (null != javaTypeFindSuperType) {
            return javaTypeFindSuperType;
        }
        if (!cls.isAssignableFrom(rawClass)) {
            throw new IllegalArgumentException(String.format("Class %s not a super-type of %s", cls.getName(), javaType));
        }
        throw new IllegalArgumentException(String.format("Internal error: class %s not included as super-type for %s", cls.getName(), javaType));
    }

    public JavaType constructFromCanonical(final String str) throws IllegalArgumentException {
        return _parser.parse(str);
    }

    public JavaType[] findTypeParameters(final JavaType javaType, final Class<?> cls) {
        final JavaType javaTypeFindSuperType = javaType.findSuperType(cls);
        if (null == javaTypeFindSuperType) {
            return TypeFactory.NO_TYPES;
        }
        return javaTypeFindSuperType.getBindings().typeParameterArray();
    }

    
    public JavaType[] findTypeParameters(final Class<?> cls, final Class<?> cls2, final TypeBindings typeBindings) {
        return this.findTypeParameters(this.constructType(cls, typeBindings), cls2);
    }

    
    public JavaType[] findTypeParameters(final Class<?> cls, final Class<?> cls2) {
        return this.findTypeParameters(this.constructType(cls), cls2);
    }

    public JavaType moreSpecificType(final JavaType javaType, final JavaType javaType2) {
        final Class<?> rawClass;
        final Class<?> rawClass2;
        return null == javaType ? javaType2 : (null == javaType2 || (rawClass = javaType.getRawClass()) == (rawClass2 = javaType2.getRawClass()) || !rawClass.isAssignableFrom(rawClass2)) ? javaType : javaType2;
    }

    public JavaType constructType(final Type type) {
        return this._fromAny(null, type, TypeFactory.EMPTY_BINDINGS);
    }

    public JavaType constructType(final TypeReference<?> typeReference) {
        return this._fromAny(null, typeReference.getType(), TypeFactory.EMPTY_BINDINGS);
    }

    public JavaType resolveMemberType(final Type type, final TypeBindings typeBindings) {
        return this._fromAny(null, type, typeBindings);
    }

    
    public JavaType constructType(final Type type, final TypeBindings typeBindings) {
        if (type instanceof Class) {
            return this._applyModifiers(type, this._fromClass(null, (Class) type, typeBindings));
        }
        return this._fromAny(null, type, typeBindings);
    }

    
    public JavaType constructType(final Type type, final Class<?> cls) {
        return this.constructType(type, null == cls ? null : this.constructType(cls));
    }

    
    public JavaType constructType(final Type type, final JavaType javaType) {
        TypeBindings bindings;
        if (null == javaType) {
            bindings = TypeFactory.EMPTY_BINDINGS;
        } else {
            final TypeBindings bindings2 = javaType.getBindings();
            if (Class.class != type.getClass()) {
                JavaType superClass = javaType;
                bindings = bindings2;
                while (bindings.isEmpty() && null != (superClass = superClass.getSuperClass())) {
                    bindings = superClass.getBindings();
                }
            } else {
                bindings = bindings2;
            }
        }
        return this._fromAny(null, type, bindings);
    }

    public ArrayType constructArrayType(final Class<?> cls) {
        return ArrayType.construct(this._fromAny(null, cls, null), null);
    }

    public ArrayType constructArrayType(final JavaType javaType) {
        return ArrayType.construct(javaType, null);
    }

    public CollectionType constructCollectionType(final Class<? extends Collection> cls, final Class<?> cls2) {
        return this.constructCollectionType(cls, this._fromClass(null, cls2, TypeFactory.EMPTY_BINDINGS));
    }

    public CollectionType constructCollectionType(final Class<? extends Collection> cls, final JavaType javaType) {
        final TypeBindings typeBindingsCreateIfNeeded = TypeBindings.createIfNeeded(cls, javaType);
        final CollectionType collectionType = (CollectionType) this._fromClass(null, cls, typeBindingsCreateIfNeeded);
        if (typeBindingsCreateIfNeeded.isEmpty() && null != javaType) {
            final JavaType contentType = collectionType.findSuperType(Collection.class).getContentType();
            if (!contentType.equals(javaType)) {
                throw new IllegalArgumentException(String.format("Non-generic Collection class %s did not resolve to something with element type %s but %s ", ClassUtil.nameOf(cls), javaType, contentType));
            }
        }
        return collectionType;
    }

    public CollectionLikeType constructCollectionLikeType(final Class<?> cls, final Class<?> cls2) {
        return this.constructCollectionLikeType(cls, this._fromClass(null, cls2, TypeFactory.EMPTY_BINDINGS));
    }

    public CollectionLikeType constructCollectionLikeType(final Class<?> cls, final JavaType javaType) {
        final JavaType javaType_fromClass = this._fromClass(null, cls, TypeBindings.createIfNeeded(cls, javaType));
        if (javaType_fromClass instanceof CollectionLikeType) {
            return (CollectionLikeType) javaType_fromClass;
        }
        return CollectionLikeType.upgradeFrom(javaType_fromClass, javaType);
    }

    public MapType constructMapType(final Class<? extends Map> cls, final Class<?> cls2, final Class<?> cls3) {
        final JavaType javaType_fromClass;
        final JavaType javaType_fromClass2;
        if (Properties.class == cls) {
            javaType_fromClass = TypeFactory.CORE_TYPE_STRING;
            javaType_fromClass2 = javaType_fromClass;
        } else {
            final TypeBindings typeBindings = TypeFactory.EMPTY_BINDINGS;
            javaType_fromClass = this._fromClass(null, cls2, typeBindings);
            javaType_fromClass2 = this._fromClass(null, cls3, typeBindings);
        }
        return this.constructMapType(cls, javaType_fromClass, javaType_fromClass2);
    }

    public MapType constructMapType(final Class<? extends Map> cls, final JavaType javaType, final JavaType javaType2) {
        final TypeBindings typeBindingsCreateIfNeeded = TypeBindings.createIfNeeded(cls, new JavaType[]{javaType, javaType2});
        final MapType mapType = (MapType) this._fromClass(null, cls, typeBindingsCreateIfNeeded);
        if (typeBindingsCreateIfNeeded.isEmpty()) {
            final JavaType javaTypeFindSuperType = mapType.findSuperType(Map.class);
            final JavaType keyType = javaTypeFindSuperType.getKeyType();
            if (!keyType.equals(javaType)) {
                throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with key type %s but %s ", ClassUtil.nameOf(cls), javaType, keyType));
            }
            final JavaType contentType = javaTypeFindSuperType.getContentType();
            if (!contentType.equals(javaType2)) {
                throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with value type %s but %s ", ClassUtil.nameOf(cls), javaType2, contentType));
            }
        }
        return mapType;
    }

    public MapLikeType constructMapLikeType(final Class<?> cls, final Class<?> cls2, final Class<?> cls3) {
        final TypeBindings typeBindings = TypeFactory.EMPTY_BINDINGS;
        return this.constructMapLikeType(cls, this._fromClass(null, cls2, typeBindings), this._fromClass(null, cls3, typeBindings));
    }

    public MapLikeType constructMapLikeType(final Class<?> cls, final JavaType javaType, final JavaType javaType2) {
        final JavaType javaType_fromClass = this._fromClass(null, cls, TypeBindings.createIfNeeded(cls, new JavaType[]{javaType, javaType2}));
        if (javaType_fromClass instanceof MapLikeType) {
            return (MapLikeType) javaType_fromClass;
        }
        return MapLikeType.upgradeFrom(javaType_fromClass, javaType, javaType2);
    }

    public JavaType constructSimpleType(final Class<?> cls, final JavaType[] javaTypeArr) {
        return this._fromClass(null, cls, TypeBindings.create(cls, javaTypeArr));
    }

    
    public JavaType constructSimpleType(final Class<?> cls, final Class<?> cls2, final JavaType[] javaTypeArr) {
        return this.constructSimpleType(cls, javaTypeArr);
    }

    public JavaType constructReferenceType(final Class<?> cls, final JavaType javaType) {
        return ReferenceType.construct(cls, TypeBindings.create(cls, javaType), null, null, javaType);
    }

    
    public JavaType uncheckedSimpleType(final Class<?> cls) {
        return this._constructSimple(cls, TypeFactory.EMPTY_BINDINGS, null, null);
    }

    public JavaType constructParametricType(final Class<?> cls, final Class<?>... clsArr) {
        final int length = clsArr.length;
        final JavaType[] javaTypeArr = new JavaType[length];
        for (int i2 = 0; i2 < length; i2++) {
            javaTypeArr[i2] = this._fromClass(null, clsArr[i2], TypeFactory.EMPTY_BINDINGS);
        }
        return this.constructParametricType(cls, javaTypeArr);
    }

    public JavaType constructParametricType(final Class<?> cls, final JavaType... javaTypeArr) {
        return this.constructParametricType(cls, TypeBindings.create(cls, javaTypeArr));
    }

    public JavaType constructParametricType(final Class<?> cls, final TypeBindings typeBindings) {
        return this._applyModifiers(cls, this._fromClass(null, cls, typeBindings));
    }

    
    public JavaType constructParametrizedType(final Class<?> cls, final Class<?> cls2, final JavaType... javaTypeArr) {
        return this.constructParametricType(cls, javaTypeArr);
    }

    
    public JavaType constructParametrizedType(final Class<?> cls, final Class<?> cls2, final Class<?>... clsArr) {
        return this.constructParametricType(cls, clsArr);
    }

    public CollectionType constructRawCollectionType(final Class<? extends Collection> cls) {
        return this.constructCollectionType(cls, TypeFactory.unknownType());
    }

    public CollectionLikeType constructRawCollectionLikeType(final Class<?> cls) {
        return this.constructCollectionLikeType(cls, TypeFactory.unknownType());
    }

    public MapType constructRawMapType(final Class<? extends Map> cls) {
        return this.constructMapType(cls, TypeFactory.unknownType(), TypeFactory.unknownType());
    }

    public MapLikeType constructRawMapLikeType(final Class<?> cls) {
        return this.constructMapLikeType(cls, TypeFactory.unknownType(), TypeFactory.unknownType());
    }

    private JavaType _mapType(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr) {
        final JavaType javaType_unknownType;
        final JavaType javaType2;
        final JavaType javaType3;
        if (Properties.class == cls) {
            javaType_unknownType = TypeFactory.CORE_TYPE_STRING;
        } else {
            final List<JavaType> typeParameters = typeBindings.getTypeParameters();
            final int size = typeParameters.size();
            if (0 != size) {
                if (2 == size) {
                    final JavaType javaType4 = typeParameters.get(0);
                    javaType2 = typeParameters.get(1);
                    javaType3 = javaType4;
                    return MapType.construct(cls, typeBindings, javaType, javaTypeArr, javaType3, javaType2);
                }
                throw new IllegalArgumentException(String.format("Strange Map type %s with %d type parameter%s (%s), can not resolve", ClassUtil.nameOf(cls), Integer.valueOf(size), 1 == size ? "" : "s", typeBindings));
            }
            javaType_unknownType = this._unknownType();
        }
        javaType3 = javaType_unknownType;
        javaType2 = javaType3;
        return MapType.construct(cls, typeBindings, javaType, javaTypeArr, javaType3, javaType2);
    }

    private JavaType _collectionType(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr) {
        final JavaType javaType_unknownType;
        final List<JavaType> typeParameters = typeBindings.getTypeParameters();
        if (typeParameters.isEmpty()) {
            javaType_unknownType = this._unknownType();
        } else if (1 == typeParameters.size()) {
            javaType_unknownType = typeParameters.get(0);
        } else {
            throw new IllegalArgumentException("Strange Collection type " + cls.getName() + ": cannot determine type parameters");
        }
        return CollectionType.construct(cls, typeBindings, javaType, javaTypeArr, javaType_unknownType);
    }

    private JavaType _referenceType(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr) {
        final JavaType javaType_unknownType;
        final List<JavaType> typeParameters = typeBindings.getTypeParameters();
        if (typeParameters.isEmpty()) {
            javaType_unknownType = this._unknownType();
        } else if (1 == typeParameters.size()) {
            javaType_unknownType = typeParameters.get(0);
        } else {
            throw new IllegalArgumentException("Strange Reference type " + cls.getName() + ": cannot determine type parameters");
        }
        return ReferenceType.construct(cls, typeBindings, javaType, javaTypeArr, javaType_unknownType);
    }

    protected JavaType _constructSimple(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr) {
        final JavaType javaType_findWellKnownSimple;
        return (!typeBindings.isEmpty() || null == (javaType_findWellKnownSimple = _findWellKnownSimple(cls))) ? this._newSimpleType(cls, typeBindings, javaType, javaTypeArr) : javaType_findWellKnownSimple;
    }

    protected JavaType _newSimpleType(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr) {
        return new SimpleType(cls, typeBindings, javaType, javaTypeArr);
    }

    protected JavaType _unknownType() {
        return TypeFactory.CORE_TYPE_OBJECT;
    }

    protected JavaType _findWellKnownSimple(final Class<?> cls) {
        if (cls.isPrimitive()) {
            if (cls == TypeFactory.CLS_BOOL) {
                return TypeFactory.CORE_TYPE_BOOL;
            }
            if (cls == TypeFactory.CLS_INT) {
                return TypeFactory.CORE_TYPE_INT;
            }
            if (cls == TypeFactory.CLS_LONG) {
                return TypeFactory.CORE_TYPE_LONG;
            }
            return null;
        }
        if (TypeFactory.CLS_STRING == cls) {
            return TypeFactory.CORE_TYPE_STRING;
        }
        if (TypeFactory.CLS_OBJECT == cls) {
            return TypeFactory.CORE_TYPE_OBJECT;
        }
        if (TypeFactory.CLS_JSON_NODE == cls) {
            return TypeFactory.CORE_TYPE_JSON_NODE;
        }
        return null;
    }

    protected JavaType _fromAny(final ClassStack classStack, final Type type, final TypeBindings typeBindings) {
        final JavaType javaType_fromWildcard;
        if (type instanceof Class) {
            javaType_fromWildcard = this._fromClass(classStack, (Class) type, TypeFactory.EMPTY_BINDINGS);
        } else if (type instanceof ParameterizedType) {
            javaType_fromWildcard = this._fromParamType(classStack, (ParameterizedType) type, typeBindings);
        } else {
            if (type instanceof JavaType) {
                return (JavaType) type;
            }
            if (type instanceof GenericArrayType) {
                javaType_fromWildcard = this._fromArrayType(classStack, (GenericArrayType) type, typeBindings);
            } else if (type instanceof TypeVariable) {
                javaType_fromWildcard = this._fromVariable(classStack, (TypeVariable) type, typeBindings);
            } else if (type instanceof WildcardType) {
                javaType_fromWildcard = this._fromWildcard(classStack, (WildcardType) type, typeBindings);
            } else {
                String sb = "Unrecognized Type: " +
                        (null == type ? "[null]" : type.toString());
                throw new IllegalArgumentException(sb);
            }
        }
        return this._applyModifiers(type, javaType_fromWildcard);
    }

    protected JavaType _applyModifiers(final Type type, JavaType javaType) {
        if (null == this._modifiers) {
            return javaType;
        }
        TypeBindings bindings = javaType.getBindings();
        if (null == bindings) {
            bindings = TypeFactory.EMPTY_BINDINGS;
        }
        final TypeModifier[] typeModifierArr = _modifiers;
        final int length = typeModifierArr.length;
        int i2 = 0;
        while (i2 < length) {
            final TypeModifier typeModifier = typeModifierArr[i2];
            final JavaType javaTypeModifyType = typeModifier.modifyType(javaType, type, bindings, this);
            if (null == javaTypeModifyType) {
                throw new IllegalStateException(String.format("TypeModifier %s (of type %s) return null for type %s", typeModifier, typeModifier.getClass().getName(), javaType));
            }
            i2++;
            javaType = javaTypeModifyType;
        }
        return javaType;
    }

    protected JavaType _fromClass(final ClassStack classStack, final Class<?> cls, final TypeBindings typeBindings) {
        final ClassStack classStackChild;
        final JavaType javaType_resolveSuperClass;
        final JavaType[] javaTypeArr_resolveSuperInterfaces;
        final JavaType javaType_newSimpleType;
        final JavaType javaType_findWellKnownSimple = this._findWellKnownSimple(cls);
        if (null != javaType_findWellKnownSimple) {
            return javaType_findWellKnownSimple;
        }
        final Object objAsKey = (null == typeBindings || typeBindings.isEmpty()) ? cls : typeBindings.asKey(cls);
        JavaType javaTypeRefine = _typeCache.get(objAsKey);
        if (null != javaTypeRefine) {
            return javaTypeRefine;
        }
        if (null == classStack) {
            classStackChild = new ClassStack(cls);
        } else {
            final ClassStack classStackFind = classStack.find(cls);
            if (null != classStackFind) {
                final ResolvedRecursiveType resolvedRecursiveType = new ResolvedRecursiveType(cls, TypeFactory.EMPTY_BINDINGS);
                classStackFind.addSelfReference(resolvedRecursiveType);
                return resolvedRecursiveType;
            }
            classStackChild = classStack.child(cls);
        }
        if (cls.isArray()) {
            javaType_newSimpleType = ArrayType.construct(this._fromAny(classStackChild, cls.getComponentType(), typeBindings), typeBindings);
        } else {
            if (cls.isInterface()) {
                javaTypeArr_resolveSuperInterfaces = this._resolveSuperInterfaces(classStackChild, cls, typeBindings);
                javaType_resolveSuperClass = null;
            } else {
                javaType_resolveSuperClass = this._resolveSuperClass(classStackChild, cls, typeBindings);
                javaTypeArr_resolveSuperInterfaces = this._resolveSuperInterfaces(classStackChild, cls, typeBindings);
            }
            final JavaType[] javaTypeArr = javaTypeArr_resolveSuperInterfaces;
            final JavaType javaType = javaType_resolveSuperClass;
            if (Properties.class == cls) {
                final SimpleType simpleType = TypeFactory.CORE_TYPE_STRING;
                javaTypeRefine = MapType.construct(cls, typeBindings, javaType, javaTypeArr, simpleType, simpleType);
            } else if (null != javaType) {
                javaTypeRefine = javaType.refine(cls, typeBindings, javaType, javaTypeArr);
            }
            javaType_newSimpleType = (null == javaTypeRefine && null == (javaTypeRefine = _fromWellKnownClass(classStackChild, cls, typeBindings, javaType, javaTypeArr)) && null == (javaTypeRefine = _fromWellKnownInterface(classStackChild, cls, typeBindings, javaType, javaTypeArr))) ? this._newSimpleType(cls, typeBindings, javaType, javaTypeArr) : javaTypeRefine;
        }
        classStackChild.resolveSelfReferences(javaType_newSimpleType);
        if (!javaType_newSimpleType.hasHandlers()) {
            _typeCache.putIfAbsent(objAsKey, javaType_newSimpleType);
        }
        return javaType_newSimpleType;
    }

    protected JavaType _resolveSuperClass(final ClassStack classStack, final Class<?> cls, final TypeBindings typeBindings) {
        final Type genericSuperclass = ClassUtil.getGenericSuperclass(cls);
        if (null == genericSuperclass) {
            return null;
        }
        return this._fromAny(classStack, genericSuperclass, typeBindings);
    }

    protected JavaType[] _resolveSuperInterfaces(final ClassStack classStack, final Class<?> cls, final TypeBindings typeBindings) {
        final Type[] genericInterfaces = ClassUtil.getGenericInterfaces(cls);
        if (null == genericInterfaces || 0 == genericInterfaces.length) {
            return TypeFactory.NO_TYPES;
        }
        final int length = genericInterfaces.length;
        final JavaType[] javaTypeArr = new JavaType[length];
        for (int i2 = 0; i2 < length; i2++) {
            javaTypeArr[i2] = this._fromAny(classStack, genericInterfaces[i2], typeBindings);
        }
        return javaTypeArr;
    }

    protected JavaType _fromWellKnownClass(final ClassStack classStack, final Class<?> cls, TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr) {
        if (null == typeBindings) {
            typeBindings = TypeFactory.EMPTY_BINDINGS;
        }
        if (Map.class == cls) {
            return this._mapType(cls, typeBindings, javaType, javaTypeArr);
        }
        if (Collection.class == cls) {
            return this._collectionType(cls, typeBindings, javaType, javaTypeArr);
        }
        if (AtomicReference.class == cls) {
            return this._referenceType(cls, typeBindings, javaType, javaTypeArr);
        }
        return null;
    }

    protected JavaType _fromWellKnownInterface(final ClassStack classStack, final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr) {
        for (final JavaType javaType2 : javaTypeArr) {
            final JavaType javaTypeRefine = javaType2.refine(cls, typeBindings, javaType, javaTypeArr);
            if (null != javaTypeRefine) {
                return javaTypeRefine;
            }
        }
        return null;
    }

    protected JavaType _fromParamType(final ClassStack classStack, final ParameterizedType parameterizedType, final TypeBindings typeBindings) {
        final TypeBindings typeBindingsCreate;
        final Class<?> cls = (Class) parameterizedType.getRawType();
        if (TypeFactory.CLS_ENUM == cls) {
            return TypeFactory.CORE_TYPE_ENUM;
        }
        if (TypeFactory.CLS_COMPARABLE == cls) {
            return TypeFactory.CORE_TYPE_COMPARABLE;
        }
        if (TypeFactory.CLS_CLASS == cls) {
            return TypeFactory.CORE_TYPE_CLASS;
        }
        final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        final int length = null == actualTypeArguments ? 0 : actualTypeArguments.length;
        if (0 == length) {
            typeBindingsCreate = TypeFactory.EMPTY_BINDINGS;
        } else {
            final JavaType[] javaTypeArr = new JavaType[length];
            for (int i2 = 0; i2 < length; i2++) {
                javaTypeArr[i2] = this._fromAny(classStack, actualTypeArguments[i2], typeBindings);
            }
            typeBindingsCreate = TypeBindings.create(cls, javaTypeArr);
        }
        return this._fromClass(classStack, cls, typeBindingsCreate);
    }

    protected JavaType _fromArrayType(final ClassStack classStack, final GenericArrayType genericArrayType, final TypeBindings typeBindings) {
        return ArrayType.construct(this._fromAny(classStack, genericArrayType.getGenericComponentType(), typeBindings), typeBindings);
    }

    protected JavaType _fromVariable(final ClassStack classStack, final TypeVariable<?> typeVariable, final TypeBindings typeBindings) {
        final Type[] bounds;
        final String name = typeVariable.getName();
        if (null == typeBindings) {
            throw new IllegalArgumentException("Null `bindings` passed (type variable \"" + name + "\")");
        }
        final JavaType javaTypeFindBoundType = typeBindings.findBoundType(name);
        if (null != javaTypeFindBoundType) {
            return javaTypeFindBoundType;
        }
        if (typeBindings.hasUnbound(name)) {
            return TypeFactory.CORE_TYPE_OBJECT;
        }
        final TypeBindings typeBindingsWithUnboundVariable = typeBindings.withUnboundVariable(name);
        synchronized (typeVariable) {
            bounds = typeVariable.getBounds();
        }
        return this._fromAny(classStack, bounds[0], typeBindingsWithUnboundVariable);
    }

    protected JavaType _fromWildcard(final ClassStack classStack, final WildcardType wildcardType, final TypeBindings typeBindings) {
        return this._fromAny(classStack, wildcardType.getUpperBounds()[0], typeBindings);
    }
}
