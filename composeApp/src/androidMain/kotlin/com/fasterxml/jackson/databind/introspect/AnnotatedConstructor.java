package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
 
public final class AnnotatedConstructor extends AnnotatedWithParams {
    private static final long serialVersionUID = 1;
    private final Constructor<?> _constructor;
    private Serialization _serialization;
    public AnnotatedConstructor(TypeResolutionContext typeResolutionContext, Constructor<?> constructor, AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(typeResolutionContext, annotationMap, annotationMapArr);
        if (constructor == null) {
            throw new IllegalArgumentException("Null constructor not allowed");
        }
        this._constructor = constructor;
    }
    private AnnotatedConstructor(Serialization serialization) {
        super(null, null, null);
        this._constructor = null;
        this._serialization = serialization;
    }
    public AnnotatedConstructor withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedConstructor(this._typeContext, this._constructor, annotationMap, this._paramAnnotations);
    }
    public Constructor<?> getAnnotated() {
        return this._constructor;
    }
    public int getModifiers() {
        return this._constructor.getModifiers();
    }
    public String getName() {
        return this._constructor.getName();
    }
    public JavaType getType() {
        return this._typeContext.resolveType(getRawType());
    }
    public Class<?> getRawType() {
        return this._constructor.getDeclaringClass();
    }
    public int getParameterCount() {
        return this._constructor.getParameterTypes().length;
    }
    public Class<?> getRawParameterType(int r2) {
        Class<?>[] parameterTypes = this._constructor.getParameterTypes();
        if (r2 >= parameterTypes.length) {
            return null;
        }
        return parameterTypes[r2];
    }
    public JavaType getParameterType(int r3) {
        Type[] genericParameterTypes = this._constructor.getGenericParameterTypes();
        if (r3 >= genericParameterTypes.length) {
            return null;
        }
        return this._typeContext.resolveType(genericParameterTypes[r3]);
    }
    public Type getGenericParameterType(int r2) {
        Type[] genericParameterTypes = this._constructor.getGenericParameterTypes();
        if (r2 >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[r2];
    }
    public Object call() throws Exception {
        return this._constructor.newInstance(null);
    }
    public Object call(Object[] objArr) throws Exception {
        return this._constructor.newInstance(objArr);
    }
    public Object call1(Object obj) throws Exception {
        return this._constructor.newInstance(obj);
    }
    public Class<?> getDeclaringClass() {
        return this._constructor.getDeclaringClass();
    }
    public Member getMember() {
        return this._constructor;
    }
    public void setValue(Object obj, Object obj2) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot call setValue() on constructor of " + getDeclaringClass().getName());
    }
    public Object getValue(Object obj) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot call getValue() on constructor of " + getDeclaringClass().getName());
    }
    public String toString() {
        int length = this._constructor.getParameterTypes().length;
        return String.format("[constructor for %s (%d arg%s), annotations: %s", ClassUtil.nameOf(this._constructor.getDeclaringClass()), Integer.valueOf(length), length == 1 ? "" : "s", this._annotations);
    }
    public int hashCode() {
        return this._constructor.getName().hashCode();
    }
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return ClassUtil.hasClass(obj, AnnotatedConstructor.class) && ((AnnotatedConstructor) obj)._constructor == this._constructor;
    }
    Object writeReplace() {
        return new AnnotatedConstructor(new Serialization(this._constructor));
    }
    Object readResolve() throws NoSuchMethodException, SecurityException {
        Serialization serialization = this._serialization;
        Class<?> cls = serialization.clazz;
        try {
            Constructor<?> declaredConstructor = cls.getDeclaredConstructor(serialization.args);
            if (!declaredConstructor.isAccessible()) {
                ClassUtil.checkAndFixAccess(declaredConstructor, false);
            }
            return new AnnotatedConstructor(null, declaredConstructor, null, null);
        } catch (Exception unused) {
            throw new IllegalArgumentException("Could not find constructor with " + this._serialization.args.length + " args from Class '" + cls.getName());
        }
    }
    private static final class Serialization implements Serializable {
        private static final long serialVersionUID = 1;
        private final Class<?>[] args;
        private final Class<?> clazz;

        public Serialization(Constructor<?> constructor) {
            this.clazz = constructor.getDeclaringClass();
            this.args = constructor.getParameterTypes();
        }
    }
}
