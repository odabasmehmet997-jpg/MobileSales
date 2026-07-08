package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;

import java.io.Serializable;
import java.lang.reflect.TypeVariable;
import java.util.*;

public class TypeBindings implements Serializable {
    private static final TypeBindings EMPTY;
    private static final String[] NO_STRINGS;
    private static final JavaType[] NO_TYPES;
    private static final long serialVersionUID = 1;
    private final int _hashCode;
    private final String[] _names;
    private final JavaType[] _types;
    private final String[] _unboundVariables;

    static {
        final String[] strArr = new String[0];
        NO_STRINGS = strArr;
        final JavaType[] javaTypeArr = new JavaType[0];
        NO_TYPES = javaTypeArr;
        EMPTY = new TypeBindings(strArr, javaTypeArr, null);
    }

    private TypeBindings(String[] strArr, JavaType[] javaTypeArr, final String[] strArr2) {
        strArr = null == strArr ? TypeBindings.NO_STRINGS : strArr;
        _names = strArr;
        javaTypeArr = null == javaTypeArr ? TypeBindings.NO_TYPES : javaTypeArr;
        _types = javaTypeArr;
        if (strArr.length != javaTypeArr.length) {
            throw new IllegalArgumentException("Mismatching names (" + strArr.length + "), types (" + javaTypeArr.length + ")");
        }
        final int length = javaTypeArr.length;
        int iHashCode = 1;
        for (int i2 = 0; i2 < length; i2++) {
            iHashCode += _types[i2].hashCode();
        }
        _unboundVariables = strArr2;
        _hashCode = iHashCode;
    }

    public static TypeBindings emptyBindings() {
        return TypeBindings.EMPTY;
    }

    protected Object readResolve() {
        final String[] strArr = _names;
        return (null == strArr || 0 == strArr.length) ? TypeBindings.EMPTY : this;
    }

    public static TypeBindings create(final Class<?> cls, final List<JavaType> list) {
        final JavaType[] javaTypeArr;
        if (null == list || list.isEmpty()) {
            javaTypeArr = TypeBindings.NO_TYPES;
        } else {
            javaTypeArr = list.toArray(TypeBindings.NO_TYPES);
        }
        return TypeBindings.create(cls, javaTypeArr);
    }

    public static TypeBindings create(final Class<?> cls, JavaType[] javaTypeArr) {
        final String[] strArr;
        if (null == javaTypeArr) {
            javaTypeArr = TypeBindings.NO_TYPES;
        } else {
            final int length = javaTypeArr.length;
            if (1 == length) {
                return TypeBindings.create(cls, javaTypeArr[0]);
            }
            if (2 == length) {
                return TypeBindings.create(cls, javaTypeArr[0], javaTypeArr[1]);
            }
        }
        final TypeVariable<? extends Class<?>>[] typeParameters = cls.getTypeParameters();
        if (null == typeParameters || 0 == typeParameters.length) {
            strArr = TypeBindings.NO_STRINGS;
        } else {
            final int length2 = typeParameters.length;
            strArr = new String[length2];
            for (int i2 = 0; i2 < length2; i2++) {
                strArr[i2] = typeParameters[i2].getName();
            }
        }
        if (strArr.length != javaTypeArr.length) {
            String sb = "Cannot create TypeBindings for class " +
                    cls.getName() +
                    " with " +
                    javaTypeArr.length +
                    " type parameter" +
                    (1 == javaTypeArr.length ? "" : "s") +
                    ": class expects " +
                    strArr.length;
            throw new IllegalArgumentException(sb);
        }
        return new TypeBindings(strArr, javaTypeArr, null);
    }

    public static TypeBindings create(final Class<?> cls, final JavaType javaType) {
        final TypeVariable<?>[] typeVariableArrParamsFor1 = TypeParamStash.paramsFor1(cls);
        final int length = null == typeVariableArrParamsFor1 ? 0 : typeVariableArrParamsFor1.length;
        if (1 != length) {
            throw new IllegalArgumentException("Cannot create TypeBindings for class " + cls.getName() + " with 1 type parameter: class expects " + length);
        }
        return new TypeBindings(new String[]{typeVariableArrParamsFor1[0].getName()}, new JavaType[]{javaType}, null);
    }

    public static TypeBindings create(final Class<?> cls, final JavaType javaType, final JavaType javaType2) {
        final TypeVariable<?>[] typeVariableArrParamsFor2 = TypeParamStash.paramsFor2(cls);
        final int length = null == typeVariableArrParamsFor2 ? 0 : typeVariableArrParamsFor2.length;
        if (2 != length) {
            throw new IllegalArgumentException("Cannot create TypeBindings for class " + cls.getName() + " with 2 type parameters: class expects " + length);
        }
        return new TypeBindings(new String[]{typeVariableArrParamsFor2[0].getName(), typeVariableArrParamsFor2[1].getName()}, new JavaType[]{javaType, javaType2}, null);
    }

    public static TypeBindings create(final List<String> list, final List<JavaType> list2) {
        if (null == list || list.isEmpty() || null == list2 || list2.isEmpty()) {
            return TypeBindings.EMPTY;
        }
        return new TypeBindings(list.toArray(TypeBindings.NO_STRINGS), list2.toArray(TypeBindings.NO_TYPES), null);
    }

    public static TypeBindings createIfNeeded(final Class<?> cls, final JavaType javaType) {
        final TypeVariable<? extends Class<?>>[] typeParameters = cls.getTypeParameters();
        final int length = null == typeParameters ? 0 : typeParameters.length;
        if (0 == length) {
            return TypeBindings.EMPTY;
        }
        if (1 != length) {
            throw new IllegalArgumentException("Cannot create TypeBindings for class " + cls.getName() + " with 1 type parameter: class expects " + length);
        }
        return new TypeBindings(new String[]{typeParameters[0].getName()}, new JavaType[]{javaType}, null);
    }

    public static TypeBindings createIfNeeded(final Class<?> cls, JavaType[] javaTypeArr) {
        final TypeVariable<? extends Class<?>>[] typeParameters = cls.getTypeParameters();
        if (null == typeParameters || 0 == typeParameters.length) {
            return TypeBindings.EMPTY;
        }
        if (null == javaTypeArr) {
            javaTypeArr = TypeBindings.NO_TYPES;
        }
        final int length = typeParameters.length;
        final String[] strArr = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            strArr[i2] = typeParameters[i2].getName();
        }
        if (length != javaTypeArr.length) {
            String sb = "Cannot create TypeBindings for class " +
                    cls.getName() +
                    " with " +
                    javaTypeArr.length +
                    " type parameter" +
                    (1 == javaTypeArr.length ? "" : "s") +
                    ": class expects " +
                    length;
            throw new IllegalArgumentException(sb);
        }
        return new TypeBindings(strArr, javaTypeArr, null);
    }

    public TypeBindings withUnboundVariable(final String str) {
        final String[] strArr;
        final String[] strArr2 = _unboundVariables;
        final int length = null == strArr2 ? 0 : strArr2.length;
        if (0 == length) {
            strArr = new String[1];
        } else {
            strArr = Arrays.copyOf(strArr2, length + 1);
        }
        strArr[length] = str;
        return new TypeBindings(_names, _types, strArr);
    }

    public JavaType findBoundType(final String str) {
        final JavaType selfReferencedType;
        final int length = _names.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(_names[i2])) {
                final JavaType javaType = _types[i2];
                return (!(javaType instanceof ResolvedRecursiveType) || null == (selfReferencedType = ((ResolvedRecursiveType) javaType).getSelfReferencedType())) ? javaType : selfReferencedType;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return 0 == this._types.length;
    }

    public int size() {
        return _types.length;
    }

    public String getBoundName(final int i2) {
        if (0 > i2) {
            return null;
        }
        final String[] strArr = _names;
        if (i2 >= strArr.length) {
            return null;
        }
        return strArr[i2];
    }

    public JavaType getBoundType(final int i2) {
        if (0 > i2) {
            return null;
        }
        final JavaType[] javaTypeArr = _types;
        if (i2 >= javaTypeArr.length) {
            return null;
        }
        return javaTypeArr[i2];
    }

    public List<JavaType> getTypeParameters() {
        final JavaType[] javaTypeArr = _types;
        if (0 == javaTypeArr.length) {
            return Collections.emptyList();
        }
        return Arrays.asList(javaTypeArr);
    }

    public boolean hasUnbound(final String str) {
        final String[] strArr = _unboundVariables;
        if (null == strArr) {
            return false;
        }
        int length = strArr.length;
        do {
            length--;
            if (0 > length) {
                return false;
            }
        } while (!str.equals(_unboundVariables[length]));
        return true;
    }

    public Object asKey(final Class<?> cls) {
        return new AsKey(cls, _types, _hashCode);
    }

    public String toString() {
        if (0 == this._types.length) {
            return "<>";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append('<');
        final int length = _types.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (0 < i2) {
                sb.append(',');
            }
            sb.append(_types[i2].getGenericSignature());
        }
        sb.append('>');
        return sb.toString();
    }

    public int hashCode() {
        return _hashCode;
    }

    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!ClassUtil.hasClass(obj, this.getClass())) {
            return false;
        }
        final TypeBindings typeBindings = (TypeBindings) obj;
        final int length = _types.length;
        if (length != typeBindings.size()) {
            return false;
        }
        final JavaType[] javaTypeArr = typeBindings._types;
        for (int i2 = 0; i2 < length; i2++) {
            if (!javaTypeArr[i2].equals(_types[i2])) {
                return false;
            }
        }
        return true;
    }

    protected JavaType[] typeParameterArray() {
        return _types;
    }

    static class TypeParamStash {
        private static final TypeVariable<?>[] VARS_ABSTRACT_LIST = AbstractList.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_COLLECTION = Collection.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_ITERABLE = Iterable.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_LIST = List.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_ARRAY_LIST = ArrayList.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_MAP = Map.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_HASH_MAP = HashMap.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_LINKED_HASH_MAP = LinkedHashMap.class.getTypeParameters();

        TypeParamStash() {
        }

        public static TypeVariable<?>[] paramsFor1(final Class<?> cls) {
            if (Collection.class == cls) {
                return TypeParamStash.VARS_COLLECTION;
            }
            if (List.class == cls) {
                return TypeParamStash.VARS_LIST;
            }
            if (ArrayList.class == cls) {
                return TypeParamStash.VARS_ARRAY_LIST;
            }
            if (AbstractList.class == cls) {
                return TypeParamStash.VARS_ABSTRACT_LIST;
            }
            if (Iterable.class == cls) {
                return TypeParamStash.VARS_ITERABLE;
            }
            return cls.getTypeParameters();
        }

        public static TypeVariable<?>[] paramsFor2(final Class<?> cls) {
            if (Map.class == cls) {
                return TypeParamStash.VARS_MAP;
            }
            if (HashMap.class == cls) {
                return TypeParamStash.VARS_HASH_MAP;
            }
            if (LinkedHashMap.class == cls) {
                return TypeParamStash.VARS_LINKED_HASH_MAP;
            }
            return cls.getTypeParameters();
        }
    }

    static final class AsKey {
        private final int _hash;
        private final JavaType[] _params;
        private final Class<?> _raw;

        public AsKey(final Class<?> cls, final JavaType[] javaTypeArr, final int i2) {
            _raw = cls;
            _params = javaTypeArr;
            _hash = i2;
        }

        public int hashCode() {
            return _hash;
        }

        public boolean equals(final Object obj) {
            if (obj == this) {
                return true;
            }
            if (null == obj || AsKey.class != obj.getClass()) {
                return false;
            }
            final AsKey asKey = (AsKey) obj;
            if (_hash == asKey._hash && _raw == asKey._raw) {
                final JavaType[] javaTypeArr = asKey._params;
                final int length = _params.length;
                if (length == javaTypeArr.length) {
                    for (int i2 = 0; i2 < length; i2++) {
                        if (!_params[i2].equals(javaTypeArr[i2])) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return _raw.getName() + "<>";
        }
    }
}
