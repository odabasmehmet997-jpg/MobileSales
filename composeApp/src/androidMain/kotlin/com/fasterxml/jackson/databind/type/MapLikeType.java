package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.TypeVariable;
import java.util.Map;

public class MapLikeType extends TypeBase {
    private static final long serialVersionUID = 1;
    protected final JavaType _keyType;
    protected final JavaType _valueType;

    public boolean isContainerType() {
        return true;
    }

    public boolean isMapLikeType() {
        return true;
    }

    protected MapLikeType(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr, final JavaType javaType2, final JavaType javaType3, final Object obj, final Object obj2, final boolean z) {
        super(cls, typeBindings, javaType, javaTypeArr, javaType2.hashCode() ^ javaType3.hashCode(), obj, obj2, z);
        _keyType = javaType2;
        _valueType = javaType3;
    }

    protected MapLikeType(final TypeBase typeBase, final JavaType javaType, final JavaType javaType2) {
        super(typeBase);
        _keyType = javaType;
        _valueType = javaType2;
    }

    public static MapLikeType upgradeFrom(final JavaType javaType, final JavaType javaType2, final JavaType javaType3) {
        if (javaType instanceof TypeBase) {
            return new MapLikeType((TypeBase) javaType, javaType2, javaType3);
        }
        throw new IllegalArgumentException("Cannot upgrade from an instance of " + javaType.getClass());
    }

    public static MapLikeType construct(final Class<?> cls, final JavaType javaType, final JavaType javaType2) {
        final TypeBindings typeBindingsEmptyBindings;
        final TypeVariable<? extends Class<?>>[] typeParameters = cls.getTypeParameters();
        if (null == typeParameters || 2 != typeParameters.length) {
            typeBindingsEmptyBindings = TypeBindings.emptyBindings();
        } else {
            typeBindingsEmptyBindings = TypeBindings.create(cls, javaType, javaType2);
        }
        return new MapLikeType(cls, typeBindingsEmptyBindings, _bogusSuperClass(cls), null, javaType, javaType2, null, null, false);
    }

    protected JavaType _narrow(final Class<?> cls) {
        return new MapLikeType(cls, _bindings, _superClass, _superInterfaces, _keyType, _valueType, _valueHandler, _typeHandler, _asStatic);
    }

    public MapLikeType withKeyType(final JavaType javaType) {
        return javaType == _keyType ? this : new MapLikeType(_class, _bindings, _superClass, _superInterfaces, javaType, _valueType, _valueHandler, _typeHandler, _asStatic);
    }
    public JavaType withContentType(final JavaType javaType) {
        return _valueType == javaType ? this : new MapLikeType(_class, _bindings, _superClass, _superInterfaces, _keyType, javaType, _valueHandler, _typeHandler, _asStatic);
    }
    public MapLikeType withTypeHandler(final Object obj) {
        return new MapLikeType(_class, _bindings, _superClass, _superInterfaces, _keyType, _valueType, _valueHandler, obj, _asStatic);
    }
    public MapLikeType withContentTypeHandler(final Object obj) {
        return new MapLikeType(_class, _bindings, _superClass, _superInterfaces, _keyType, _valueType.withTypeHandler(obj), _valueHandler, _typeHandler, _asStatic);
    }

    public MapLikeType withValueHandler(final Object obj) {
        return new MapLikeType(_class, _bindings, _superClass, _superInterfaces, _keyType, _valueType, obj, _typeHandler, _asStatic);
    }

    public MapLikeType withContentValueHandler(final Object obj) {
        return new MapLikeType(_class, _bindings, _superClass, _superInterfaces, _keyType, _valueType.withValueHandler(obj), _valueHandler, _typeHandler, _asStatic);
    }

    public JavaType withHandlersFrom(final JavaType javaType) {
        final JavaType javaTypeWithHandlersFrom;
        final JavaType javaTypeWithHandlersFrom2;
        JavaType javaTypeWithHandlersFrom3 = super.withHandlersFrom(javaType);
        final JavaType keyType = javaType.getKeyType();
        if ((javaTypeWithHandlersFrom3 instanceof MapLikeType) && null != keyType && (javaTypeWithHandlersFrom2 = _keyType.withHandlersFrom(keyType)) != _keyType) {
            javaTypeWithHandlersFrom3 = ((MapLikeType) javaTypeWithHandlersFrom3).withKeyType(javaTypeWithHandlersFrom2);
        }
        final JavaType contentType = javaType.getContentType();
        return (null == contentType || (javaTypeWithHandlersFrom = _valueType.withHandlersFrom(contentType)) == _valueType) ? javaTypeWithHandlersFrom3 : javaTypeWithHandlersFrom3.withContentType(javaTypeWithHandlersFrom);
    }

    public MapLikeType withStaticTyping() {
        return _asStatic ? this : new MapLikeType(_class, _bindings, _superClass, _superInterfaces, _keyType, _valueType.withStaticTyping(), _valueHandler, _typeHandler, true);
    }
    public JavaType refine(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr) {
        return new MapLikeType(cls, typeBindings, javaType, javaTypeArr, _keyType, _valueType, _valueHandler, _typeHandler, _asStatic);
    }

    protected String buildCanonicalName() {
        final StringBuilder sb = new StringBuilder();
        sb.append(_class.getName());
        if (null != this._keyType) {
            sb.append('<');
            sb.append(_keyType.toCanonical());
            sb.append(',');
            sb.append(_valueType.toCanonical());
            sb.append('>');
        }
        return sb.toString();
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.core.type.ResolvedType
    public JavaType getKeyType() {
        return _keyType;
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.core.type.ResolvedType
    public JavaType getContentType() {
        return _valueType;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public Object getContentValueHandler() {
        return _valueType.getValueHandler();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public Object getContentTypeHandler() {
        return _valueType.getTypeHandler();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public boolean hasHandlers() {
        return super.hasHandlers() || _valueType.hasHandlers() || _keyType.hasHandlers();
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getErasedSignature(final StringBuilder sb) {
        return _classSignature(_class, sb, true);
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getGenericSignature(final StringBuilder sb) {
        _classSignature(_class, sb, false);
        sb.append('<');
        _keyType.getGenericSignature(sb);
        _valueType.getGenericSignature(sb);
        sb.append(">;");
        return sb;
    }

    public MapLikeType withKeyTypeHandler(final Object obj) {
        return new MapLikeType(_class, _bindings, _superClass, _superInterfaces, _keyType.withTypeHandler(obj), _valueType, _valueHandler, _typeHandler, _asStatic);
    }

    public MapLikeType withKeyValueHandler(final Object obj) {
        return new MapLikeType(_class, _bindings, _superClass, _superInterfaces, _keyType.withValueHandler(obj), _valueType, _valueHandler, _typeHandler, _asStatic);
    }

    @Deprecated
    public boolean isTrueMapType() {
        return Map.class.isAssignableFrom(_class);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public String toString() {
        return String.format("[map-like type; class %s, %s -> %s]", _class.getName(), _keyType, _valueType);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (null == obj || obj.getClass() != this.getClass()) {
            return false;
        }
        final MapLikeType mapLikeType = (MapLikeType) obj;
        return _class == mapLikeType._class && _keyType.equals(mapLikeType._keyType) && _valueType.equals(mapLikeType._valueType);
    }
}
