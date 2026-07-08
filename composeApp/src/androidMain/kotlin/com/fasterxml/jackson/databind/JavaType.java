package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.List;

public abstract class JavaType extends ResolvedType implements Serializable, Type {
    private static final long serialVersionUID = 1;
    protected final boolean _asStatic;
    protected final Class<?> _class;
    protected final int _hash;
    protected final Object _typeHandler;
    protected final Object _valueHandler;
    protected abstract JavaType _narrow(Class<?> cls);
    public abstract JavaType containedType(int i2);
    public abstract int containedTypeCount();
    public abstract String containedTypeName(int i2);
    public abstract boolean equals(Object obj);
    public abstract JavaType findSuperType(Class<?> cls);
    public abstract JavaType[] findTypeParameters(Class<?> cls);
    public abstract TypeBindings getBindings();
    public JavaType getContentType() {
        return null;
    }
    public Object getContentTypeHandler() {
        return null;
    }
    public Object getContentValueHandler() {
        return null;
    }
    public abstract StringBuilder getErasedSignature(StringBuilder sb);
    public abstract StringBuilder getGenericSignature(StringBuilder sb);
    public abstract List<JavaType> getInterfaces();
    public JavaType getKeyType() {
        return null;
    }
    public Class<?> getParameterSource() {
        return null;
    }
    public JavaType getReferencedType() {
        return null;
    }
    public abstract JavaType getSuperClass();
    public boolean hasContentType() {
        return true;
    }
    public boolean isArrayType() {
        return false;
    }
    public boolean isCollectionLikeType() {
        return false;
    }
    public abstract boolean isContainerType();
    public boolean isMapLikeType() {
        return false;
    }
    public abstract JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr);
    public abstract String toString();
    public abstract JavaType withContentType(JavaType javaType);
    public abstract JavaType withContentTypeHandler(Object obj);
    public abstract JavaType withContentValueHandler(Object obj);
    public abstract JavaType withStaticTyping();
    public abstract JavaType withTypeHandler(Object obj);
    public abstract JavaType withValueHandler(Object obj);
    protected JavaType(final Class<?> cls, final int i2, final Object obj, final Object obj2, final boolean z) {
        _class = cls;
        _hash = cls.getName().hashCode() + i2;
        _valueHandler = obj;
        _typeHandler = obj2;
        _asStatic = z;
    }
    protected JavaType(final JavaType javaType) {
        _class = javaType._class;
        _hash = javaType._hash;
        _valueHandler = javaType._valueHandler;
        _typeHandler = javaType._typeHandler;
        _asStatic = javaType._asStatic;
    }
    public JavaType withHandlersFrom(final JavaType javaType) {
        final Object typeHandler = javaType.getTypeHandler();
        final JavaType javaTypeWithTypeHandler = typeHandler != _typeHandler ? this.withTypeHandler(typeHandler) : this;
        final Object valueHandler = javaType.getValueHandler();
        return valueHandler != _valueHandler ? javaTypeWithTypeHandler.withValueHandler(valueHandler) : javaTypeWithTypeHandler;
    }
    public JavaType forcedNarrowBy(final Class<?> cls) {
        return cls == _class ? this : this._narrow(cls);
    }
    public final Class<?> getRawClass() {
        return _class;
    }
    public final boolean hasRawClass(final Class<?> cls) {
        return _class == cls;
    }
    public final boolean isTypeOrSubTypeOf(final Class<?> cls) {
        final Class<?> cls2 = _class;
        return cls2 == cls || cls.isAssignableFrom(cls2);
    }
    public final boolean isTypeOrSuperTypeOf(final Class<?> cls) {
        final Class<?> cls2 = _class;
        return cls2 == cls || cls2.isAssignableFrom(cls);
    }
    public boolean isAbstract() {
        return Modifier.isAbstract(_class.getModifiers());
    }
    public boolean isConcrete() {
        if (0 == (this._class.getModifiers() & 1536)) {
            return true;
        }
        return _class.isPrimitive();
    }
    public boolean isThrowable() {
        return Throwable.class.isAssignableFrom(_class);
    }
    public final boolean isEnumType() {
        return ClassUtil.isEnumType(_class);
    }
    public final boolean isEnumImplType() {
        return ClassUtil.isEnumType(_class) && Enum.class != this._class;
    }
    public final boolean isRecordType() {
        return ClassUtil.isRecordType(_class);
    }
    public final boolean isInterface() {
        return _class.isInterface();
    }
    public final boolean isPrimitive() {
        return _class.isPrimitive();
    }
    public final boolean isFinal() {
        return Modifier.isFinal(_class.getModifiers());
    }
    public final boolean isJavaLangObject() {
        return Object.class == this._class;
    }
    public final boolean useStaticType() {
        return _asStatic;
    }
    public boolean hasGenericTypes() {
        return 0 < containedTypeCount();
    }
    public JavaType containedTypeOrUnknown(final int i2) {
        final JavaType javaTypeContainedType = this.containedType(i2);
        return null == javaTypeContainedType ? TypeFactory.unknownType() : javaTypeContainedType;
    }
    public <T> T getValueHandler() {
        return (T) _valueHandler;
    }
    public <T> T getTypeHandler() {
        return (T) _typeHandler;
    }
    public boolean hasValueHandler() {
        return null != this._valueHandler;
    }
    public boolean hasHandlers() {
        return null != this._typeHandler || null != this._valueHandler;
    }
    public String getGenericSignature() {
        final StringBuilder sb = new StringBuilder(40);
        this.getGenericSignature(sb);
        return sb.toString();
    }
    public String getErasedSignature() {
        final StringBuilder sb = new StringBuilder(40);
        this.getErasedSignature(sb);
        return sb.toString();
    }
    public final int hashCode() {
        return _hash;
    }
}
