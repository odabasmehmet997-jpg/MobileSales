package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
 
public class PlaceholderForType extends TypeBase {
    private static final long serialVersionUID = 1;
    protected JavaType _actualType;
    protected final int _ordinal;
 
    public boolean equals(final Object obj) {
        return obj == this;
    }
 
    public boolean isContainerType() {
        return false;
    }

    public PlaceholderForType(final int i2) {
        super(Object.class, TypeBindings.emptyBindings(), TypeFactory.unknownType(), null, 1, null, null, false);
        _ordinal = i2;
    }

    public JavaType actualType() {
        return _actualType;
    }

    public void actualType(final JavaType javaType) {
        _actualType = javaType;
    }
 
    protected String buildCanonicalName() {
        return this.toString();
    }
 
    public StringBuilder getGenericSignature(final StringBuilder sb) {
        return this.getErasedSignature(sb);
    }

    
    public StringBuilder getErasedSignature(final StringBuilder sb) {
        sb.append('');
        sb.append(_ordinal + 1);
        return sb;
    }

    
    public JavaType withTypeHandler(final Object obj) {
        return this._unsupported();
    }

    
    public JavaType withContentTypeHandler(final Object obj) {
        return this._unsupported();
    }

    
    public JavaType withValueHandler(final Object obj) {
        return this._unsupported();
    }

    
    public JavaType withContentValueHandler(final Object obj) {
        return this._unsupported();
    }

    
    public JavaType withContentType(final JavaType javaType) {
        return this._unsupported();
    }

    
    public JavaType withStaticTyping() {
        return this._unsupported();
    }

    
    public JavaType refine(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr) {
        return this._unsupported();
    }

    
    @Deprecated
    protected JavaType _narrow(final Class<?> cls) {
        return this._unsupported();
    }

    
    public String toString() {
        return this.getErasedSignature(new StringBuilder()).toString();
    }

    private <T> T _unsupported() {
        throw new UnsupportedOperationException("Operation should not be attempted on " + this.getClass().getName());
    }
}
