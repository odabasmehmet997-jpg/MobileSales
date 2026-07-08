package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
 
public class ReferenceType extends SimpleType {
    private static final long serialVersionUID = 1;
    protected final JavaType _anchorType;
    protected final JavaType _referencedType;
 
    public boolean hasContentType() {
        return true;
    }
 
    public boolean isReferenceType() {
        return true;
    }

    protected ReferenceType(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr, final JavaType javaType2, final JavaType javaType3, final Object obj, final Object obj2, final boolean z) {
        super(cls, typeBindings, javaType, javaTypeArr, javaType2.hashCode(), obj, obj2, z);
        _referencedType = javaType2;
        _anchorType = null == javaType3 ? this : javaType3;
    }

    protected ReferenceType(final TypeBase typeBase, final JavaType javaType) {
        super(typeBase);
        _referencedType = javaType;
        _anchorType = this;
    }

    public static ReferenceType upgradeFrom(final JavaType javaType, final JavaType javaType2) {
        if (null == javaType2) {
            throw new IllegalArgumentException("Missing referencedType");
        }
        if (javaType instanceof TypeBase) {
            return new ReferenceType((TypeBase) javaType, javaType2);
        }
        throw new IllegalArgumentException("Cannot upgrade from an instance of " + javaType.getClass());
    }

    public static ReferenceType construct(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr, final JavaType javaType2) {
        return new ReferenceType(cls, typeBindings, javaType, javaTypeArr, javaType2, null, null, null, false);
    }

    @Deprecated
    public static ReferenceType construct(final Class<?> cls, final JavaType javaType) {
        return new ReferenceType(cls, TypeBindings.emptyBindings(), null, null, null, javaType, null, null, false);
    }

    
    public JavaType withContentType(final JavaType javaType) {
        return _referencedType == javaType ? this : new ReferenceType(_class, _bindings, _superClass, _superInterfaces, javaType, _anchorType, _valueHandler, _typeHandler, _asStatic);
    }

    
    public ReferenceType withTypeHandler(final Object obj) {
        return obj == _typeHandler ? this : new ReferenceType(_class, _bindings, _superClass, _superInterfaces, _referencedType, _anchorType, _valueHandler, obj, _asStatic);
    }

    
    public ReferenceType withContentTypeHandler(final Object obj) {
        return obj == _referencedType.getTypeHandler() ? this : new ReferenceType(_class, _bindings, _superClass, _superInterfaces, _referencedType.withTypeHandler(obj), _anchorType, _valueHandler, _typeHandler, _asStatic);
    }

    
    public ReferenceType withValueHandler(final Object obj) {
        return obj == _valueHandler ? this : new ReferenceType(_class, _bindings, _superClass, _superInterfaces, _referencedType, _anchorType, obj, _typeHandler, _asStatic);
    }

    
    public ReferenceType withContentValueHandler(final Object obj) {
        if (obj == _referencedType.getValueHandler()) {
            return this;
        }
        return new ReferenceType(_class, _bindings, _superClass, _superInterfaces, _referencedType.withValueHandler(obj), _anchorType, _valueHandler, _typeHandler, _asStatic);
    }

    
    public ReferenceType withStaticTyping() {
        return _asStatic ? this : new ReferenceType(_class, _bindings, _superClass, _superInterfaces, _referencedType.withStaticTyping(), _anchorType, _valueHandler, _typeHandler, true);
    }

    
    public JavaType refine(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr) {
        return new ReferenceType(cls, _bindings, javaType, javaTypeArr, _referencedType, _anchorType, _valueHandler, _typeHandler, _asStatic);
    }

    @Override // com.fasterxml.jackson.databind.type.SimpleType, com.fasterxml.jackson.databind.type.TypeBase
    protected String buildCanonicalName() {
        return _class.getName() + '<' + _referencedType.toCanonical() + '>';
    }

    
    @Deprecated
    protected JavaType _narrow(final Class<?> cls) {
        return new ReferenceType(cls, _bindings, _superClass, _superInterfaces, _referencedType, _anchorType, _valueHandler, _typeHandler, _asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.core.type.ResolvedType
    public JavaType getContentType() {
        return _referencedType;
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.core.type.ResolvedType
    public JavaType getReferencedType() {
        return _referencedType;
    }

    @Override // com.fasterxml.jackson.databind.type.SimpleType, com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getErasedSignature(final StringBuilder sb) {
        return _classSignature(_class, sb, true);
    }

    @Override // com.fasterxml.jackson.databind.type.SimpleType, com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getGenericSignature(final StringBuilder sb) {
        _classSignature(_class, sb, false);
        sb.append('<');
        final StringBuilder genericSignature = _referencedType.getGenericSignature(sb);
        genericSignature.append(">;");
        return genericSignature;
    }

    public JavaType getAnchorType() {
        return _anchorType;
    }

    public boolean isAnchorType() {
        return _anchorType == this;
    }

    
    public String toString() {
        String sb = "[reference type, class " +
                this.buildCanonicalName() +
                '<' +
                _referencedType +
                '>' +
                ']';
        return sb;
    }

    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (null == obj || obj.getClass() != this.getClass()) {
            return false;
        }
        final ReferenceType referenceType = (ReferenceType) obj;
        if (referenceType._class != _class) {
            return false;
        }
        return _referencedType.equals(referenceType._referencedType);
    }
}
