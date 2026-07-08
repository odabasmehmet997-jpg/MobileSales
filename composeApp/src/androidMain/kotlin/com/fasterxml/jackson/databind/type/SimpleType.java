package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.util.Collection;
import java.util.Map;
 
public class SimpleType extends TypeBase {
    private static final long serialVersionUID = 1;

    
    public boolean hasContentType() {
        return false;
    }
    public boolean isContainerType() {
        return false;
    }
    public JavaType refine(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr) {
        return null;
    }

    protected SimpleType(final Class<?> cls) {
        this(cls, TypeBindings.emptyBindings(), null, null);
    }

    protected SimpleType(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr) {
        this(cls, typeBindings, javaType, javaTypeArr, null, null, false);
    }

    protected SimpleType(final TypeBase typeBase) {
        super(typeBase);
    }

    protected SimpleType(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr, final Object obj, final Object obj2, final boolean z) {
        super(cls, typeBindings, javaType, javaTypeArr, 0, obj, obj2, z);
    }

    protected SimpleType(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr, final int i2, final Object obj, final Object obj2, final boolean z) {
        super(cls, typeBindings, javaType, javaTypeArr, i2, obj, obj2, z);
    }

    public static SimpleType constructUnsafe(final Class<?> cls) {
        return new SimpleType(cls, null, null, null, null, null, false);
    }
 
    public static SimpleType construct(final Class<?> cls) {
        if (Map.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Cannot construct SimpleType for a Map (class: " + cls.getName() + ")");
        }
        if (Collection.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Cannot construct SimpleType for a Collection (class: " + cls.getName() + ")");
        }
        if (cls.isArray()) {
            throw new IllegalArgumentException("Cannot construct SimpleType for an array (class: " + cls.getName() + ")");
        }
        final TypeBindings typeBindingsEmptyBindings = TypeBindings.emptyBindings();
        return new SimpleType(cls, typeBindingsEmptyBindings, SimpleType._buildSuperClass(cls.getSuperclass(), typeBindingsEmptyBindings), null, null, null, false);
    }
 
    protected JavaType _narrow(final Class<?> cls) {
        final Class<?> cls2 = _class;
        if (cls2 == cls) {
            return this;
        }
        if (!cls2.isAssignableFrom(cls)) {
            return new SimpleType(cls, _bindings, this, _superInterfaces, _valueHandler, _typeHandler, _asStatic);
        }
        final Class<? super Object> superclass = (Class<? super Object>) cls.getSuperclass();
        final Class<?> cls3 = _class;
        if (superclass == cls3) {
            return new SimpleType(cls, _bindings, this, _superInterfaces, _valueHandler, _typeHandler, _asStatic);
        }
        if (null != superclass && cls3.isAssignableFrom(superclass)) {
            return new SimpleType(cls, _bindings, this._narrow(superclass), null, _valueHandler, _typeHandler, _asStatic);
        }
        for (final Class<?> cls4 : cls.getInterfaces()) {
            final Class<?> cls5 = _class;
            if (cls4 == cls5) {
                return new SimpleType(cls, _bindings, null, new JavaType[]{this}, _valueHandler, _typeHandler, _asStatic);
            }
            if (cls5.isAssignableFrom(cls4)) {
                return new SimpleType(cls, _bindings, null, new JavaType[]{this._narrow(cls4)}, _valueHandler, _typeHandler, _asStatic);
            }
        }
        throw new IllegalArgumentException("Internal error: Cannot resolve sub-type for Class " + cls.getName() + " to " + _class.getName());
    }

    
    public JavaType withContentType(final JavaType javaType) {
        throw new IllegalArgumentException("Simple types have no content types; cannot call withContentType()");
    }

    
    public SimpleType withTypeHandler(final Object obj) {
        return _typeHandler == obj ? this : new SimpleType(_class, _bindings, _superClass, _superInterfaces, _valueHandler, obj, _asStatic);
    }

    
    public JavaType withContentTypeHandler(final Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; cannot call withContenTypeHandler()");
    }

    
    public SimpleType withValueHandler(final Object obj) {
        return obj == _valueHandler ? this : new SimpleType(_class, _bindings, _superClass, _superInterfaces, obj, _typeHandler, _asStatic);
    }

    
    public SimpleType withContentValueHandler(final Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; cannot call withContenValueHandler()");
    }

    
    public SimpleType withStaticTyping() {
        return _asStatic ? this : new SimpleType(_class, _bindings, _superClass, _superInterfaces, _valueHandler, _typeHandler, true);
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase
    protected String buildCanonicalName() {
        final StringBuilder sb = new StringBuilder();
        sb.append(_class.getName());
        final int size = _bindings.size();
        if (0 < size) {
            sb.append('<');
            for (int i2 = 0; i2 < size; i2++) {
                final JavaType javaTypeContainedType = this.containedType(i2);
                if (0 < i2) {
                    sb.append(',');
                }
                sb.append(javaTypeContainedType.toCanonical());
            }
            sb.append('>');
        }
        return sb.toString();
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getErasedSignature(final StringBuilder sb) {
        return _classSignature(_class, sb, true);
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        _classSignature(_class, sb, false);
        final int size = _bindings.size();
        if (0 < size) {
            sb.append('<');
            for (int i2 = 0; i2 < size; i2++) {
                sb = this.containedType(i2).getGenericSignature(sb);
            }
            sb.append('>');
        }
        sb.append(';');
        return sb;
    }

    private static JavaType _buildSuperClass(final Class<?> cls, final TypeBindings typeBindings) {
        if (null == cls) {
            return null;
        }
        if (Object.class == cls) {
            return TypeFactory.unknownType();
        }
        return new SimpleType(cls, typeBindings, SimpleType._buildSuperClass(cls.getSuperclass(), typeBindings), null, null, null, false);
    }

    
    public String toString() {
        String sb = "[simple type, class " +
                this.buildCanonicalName() +
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
        final SimpleType simpleType = (SimpleType) obj;
        if (simpleType._class != _class) {
            return false;
        }
        return _bindings.equals(simpleType._bindings);
    }
}
