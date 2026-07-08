package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
 
public class ResolvedRecursiveType extends TypeBase {
    private static final long serialVersionUID = 1;
    protected JavaType _referencedType;
 
    protected JavaType _narrow(final Class<?> cls) {
        return this;
    }

    
    public boolean equals(final Object obj) {
        return obj == this;
    }
    public boolean isContainerType() {
        return false;
    }

    
    public JavaType refine(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr) {
        return null;
    }

    
    public JavaType withContentType(final JavaType javaType) {
        return this;
    }

    
    public JavaType withContentTypeHandler(final Object obj) {
        return this;
    }

    
    public JavaType withContentValueHandler(final Object obj) {
        return this;
    }

    
    public JavaType withStaticTyping() {
        return this;
    }

    
    public JavaType withTypeHandler(final Object obj) {
        return this;
    }

    
    public JavaType withValueHandler(final Object obj) {
        return this;
    }

    public ResolvedRecursiveType(final Class<?> cls, final TypeBindings typeBindings) {
        super(cls, typeBindings, null, null, 0, null, null, false);
    }

    public void setReference(final JavaType javaType) {
        if (null != this._referencedType) {
            throw new IllegalStateException("Trying to re-set self reference; old value = " + _referencedType + ", new = " + javaType);
        }
        _referencedType = javaType;
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public JavaType getSuperClass() {
        final JavaType javaType = _referencedType;
        if (null != javaType) {
            return javaType.getSuperClass();
        }
        return super.getSuperClass();
    }

    public JavaType getSelfReferencedType() {
        return _referencedType;
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public TypeBindings getBindings() {
        final JavaType javaType = _referencedType;
        if (null != javaType) {
            return javaType.getBindings();
        }
        return super.getBindings();
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getGenericSignature(final StringBuilder sb) {
        final JavaType javaType = _referencedType;
        if (null != javaType) {
            return javaType.getErasedSignature(sb);
        }
        sb.append("?");
        return sb;
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getErasedSignature(final StringBuilder sb) {
        final JavaType javaType = _referencedType;
        return null != javaType ? javaType.getErasedSignature(sb) : sb;
    }

    
    public String toString() {
        final StringBuilder sb = new StringBuilder(40);
        sb.append("[recursive type; ");
        final JavaType javaType = _referencedType;
        if (null == javaType) {
            sb.append("UNRESOLVED");
        } else {
            sb.append(javaType.getRawClass().getName());
        }
        return sb.toString();
    }
}
