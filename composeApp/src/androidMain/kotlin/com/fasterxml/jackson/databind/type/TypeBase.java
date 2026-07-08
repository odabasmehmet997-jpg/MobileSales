package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
 
public abstract class TypeBase extends JavaType implements JsonSerializable {
    private static final TypeBindings NO_BINDINGS = TypeBindings.emptyBindings();
    private static final JavaType[] NO_TYPES = new JavaType[0];
    private static final long serialVersionUID = 1;
    protected final TypeBindings _bindings;
    volatile transient String _canonicalName;
    protected final JavaType _superClass;
    protected final JavaType[] _superInterfaces;

    
    public abstract StringBuilder getErasedSignature(StringBuilder sb);

    
    public abstract StringBuilder getGenericSignature(StringBuilder sb);

    protected TypeBase(final Class<?> cls, final TypeBindings typeBindings, final JavaType javaType, final JavaType[] javaTypeArr, final int i2, final Object obj, final Object obj2, final boolean z) {
        super(cls, i2, obj, obj2, z);
        _bindings = null == typeBindings ? TypeBase.NO_BINDINGS : typeBindings;
        _superClass = javaType;
        _superInterfaces = javaTypeArr;
    }

    protected TypeBase(final TypeBase typeBase) {
        super(typeBase);
        _superClass = typeBase._superClass;
        _superInterfaces = typeBase._superInterfaces;
        _bindings = typeBase._bindings;
    }

    @Override // com.fasterxml.jackson.core.type.ResolvedType
    public String toCanonical() {
        final String str = _canonicalName;
        return null == str ? this.buildCanonicalName() : str;
    }

    protected String buildCanonicalName() {
        return _class.getName();
    }

    
    public TypeBindings getBindings() {
        return _bindings;
    }

    public int containedTypeCount() {
        return _bindings.size();
    }

    public JavaType containedType(final int i2) {
        return _bindings.getBoundType(i2);
    }
    public String containedTypeName(final int i2) {
        return _bindings.getBoundName(i2);
    }

    
    public JavaType getSuperClass() {
        return _superClass;
    }

    
    public List<JavaType> getInterfaces() {
        final JavaType[] javaTypeArr = _superInterfaces;
        if (null == javaTypeArr) {
            return Collections.emptyList();
        }
        final int length = javaTypeArr.length;
        if (0 == length) {
            return Collections.emptyList();
        }
        if (1 == length) {
            return Collections.singletonList(javaTypeArr[0]);
        }
        return Arrays.asList(javaTypeArr);
    }

    
    public final JavaType findSuperType(final Class<?> cls) {
        final JavaType javaTypeFindSuperType;
        final JavaType[] javaTypeArr;
        if (cls == _class) {
            return this;
        }
        if (cls.isInterface() && null != (javaTypeArr = this._superInterfaces)) {
            final int length = javaTypeArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                final JavaType javaTypeFindSuperType2 = _superInterfaces[i2].findSuperType(cls);
                if (null != javaTypeFindSuperType2) {
                    return javaTypeFindSuperType2;
                }
            }
        }
        final JavaType javaType = _superClass;
        if (null == javaType || null == (javaTypeFindSuperType = javaType.findSuperType(cls))) {
            return null;
        }
        return javaTypeFindSuperType;
    }

    
    public JavaType[] findTypeParameters(final Class<?> cls) {
        final JavaType javaTypeFindSuperType = this.findSuperType(cls);
        if (null == javaTypeFindSuperType) {
            return TypeBase.NO_TYPES;
        }
        return javaTypeFindSuperType.getBindings().typeParameterArray();
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializable
    public void serializeWithType(final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider, final TypeSerializer typeSerializer) throws IOException {
        final WritableTypeId writableTypeId = new WritableTypeId(this, JsonToken.VALUE_STRING);
        typeSerializer.writeTypePrefix(jsonGenerator, writableTypeId);
        this.serialize(jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffix(jsonGenerator, writableTypeId);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializable
    public void serialize(final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(this.toCanonical());
    }

    protected static StringBuilder _classSignature(final Class<?> cls, final StringBuilder sb, final boolean z) {
        if (cls.isPrimitive()) {
            if (cls == Boolean.TYPE) {
                sb.append('Z');
            } else if (cls == Byte.TYPE) {
                sb.append('B');
            } else if (cls == Short.TYPE) {
                sb.append('S');
            } else if (cls == Character.TYPE) {
                sb.append('C');
            } else if (cls == Integer.TYPE) {
                sb.append('I');
            } else if (cls == Long.TYPE) {
                sb.append('J');
            } else if (cls == Float.TYPE) {
                sb.append('F');
            } else if (cls == Double.TYPE) {
                sb.append('D');
            } else if (cls == Void.TYPE) {
                sb.append('V');
            } else {
                throw new IllegalStateException("Unrecognized primitive type: " + cls.getName());
            }
        } else {
            sb.append('L');
            final String name = cls.getName();
            final int length = name.length();
            for (int i2 = 0; i2 < length; i2++) {
                char cCharAt = name.charAt(i2);
                if ('.' == cCharAt) {
                    cCharAt = '/';
                }
                sb.append(cCharAt);
            }
            if (z) {
                sb.append(';');
            }
        }
        return sb;
    }

    protected static JavaType _bogusSuperClass(final Class<?> cls) {
        if (null == cls.getSuperclass()) {
            return null;
        }
        return TypeFactory.unknownType();
    }
}
