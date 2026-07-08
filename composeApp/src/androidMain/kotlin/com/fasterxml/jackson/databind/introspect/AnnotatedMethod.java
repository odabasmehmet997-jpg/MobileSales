package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public final class AnnotatedMethod extends AnnotatedWithParams implements Serializable {
    private static final long serialVersionUID = 1;
    private final transient Method _method;
    private Class<?>[] _paramClasses;
    private Serialization _serialization;
    public AnnotatedMethod(TypeResolutionContext typeResolutionContext, Method method, AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(typeResolutionContext, annotationMap, annotationMapArr);
        if (method == null) {
            throw new IllegalArgumentException("Cannot construct AnnotatedMethod with null Method");
        }
        this._method = method;
    }
    private AnnotatedMethod(Serialization serialization) {
        super(null, null, null);
        this._method = null;
        this._serialization = serialization;
    }
    public AnnotatedMethod withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedMethod(this._typeContext, this._method, annotationMap, this._paramAnnotations);
    }
    public Method getAnnotated() {
        return this._method;
    }
    public int getModifiers() {
        return this._method.getModifiers();
    }
    public String getName() {
        return this._method.getName();
    }
    public JavaType getType() {
        return this._typeContext.resolveType(this._method.getGenericReturnType());
    }
    public Class<?> getRawType() {
        return this._method.getReturnType();
    }
    public Object call() throws Exception {
        return this._method.invoke(null, null);
    }
    public Object call(Object[] objArr) throws Exception {
        return this._method.invoke(null, objArr);
    }
    public Object call1(Object obj) throws Exception {
        return this._method.invoke(null, obj);
    }
    public Object callOn(Object obj) throws Exception {
        return this._method.invoke(obj, null);
    }
    public Object callOnWith(Object obj, Object... objArr) throws Exception {
        return this._method.invoke(obj, objArr);
    }
    public int getParameterCount() {
        return getRawParameterTypes().length;
    }
    public Class<?> getRawParameterType(int r2) {
        Class<?>[] rawParameterTypes = getRawParameterTypes();
        if (r2 >= rawParameterTypes.length) {
            return null;
        }
        return rawParameterTypes[r2];
    }
    public JavaType getParameterType(int r3) {
        Type[] genericParameterTypes = this._method.getGenericParameterTypes();
        if (r3 >= genericParameterTypes.length) {
            return null;
        }
        return this._typeContext.resolveType(genericParameterTypes[r3]);
    }
    public Type getGenericParameterType(int r2) {
        Type[] genericParameterTypes = getGenericParameterTypes();
        if (r2 >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[r2];
    }
    public Class<?> getDeclaringClass() {
        return this._method.getDeclaringClass();
    }
    public Method getMember() {
        return this._method;
    }
    public void setValue(Object obj, Object obj2) throws IllegalArgumentException {
        try {
            this._method.invoke(obj, obj2);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new IllegalArgumentException("Failed to setValue() with method " + getFullName() + ": " + e2.getMessage(), e2);
        }
    }
    public Object getValue(Object obj) throws IllegalArgumentException {
        try {
            return this._method.invoke(obj, null);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new IllegalArgumentException("Failed to getValue() with method " + this.getFullName() + ": " + e2.getMessage(), e2);
        }
    }
    public String getFullName() {
        String fullName = super.getFullName();
        int parameterCount = getParameterCount();
        if (parameterCount == 0) {
            return fullName + "()";
        }
        if (parameterCount == 1) {
            return fullName + "(" + getRawParameterType(0).getName() + ")";
        }
        return String.format("%s(%d params)", super.getFullName(), Integer.valueOf(getParameterCount()));
    }
    public Class<?>[] getRawParameterTypes() {
        if (this._paramClasses == null) {
            this._paramClasses = this._method.getParameterTypes();
        }
        return this._paramClasses;
    }
    public Type[] getGenericParameterTypes() {
        return this._method.getGenericParameterTypes();
    }
    public Class<?> getRawReturnType() {
        return this._method.getReturnType();
    }
    public boolean hasReturnType() {
        return getRawReturnType() != Void.TYPE;
    }
    public String toString() {
        return "[method " + getFullName() + "]";
    }
    public int hashCode() {
        return this._method.getName().hashCode();
    }
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return ClassUtil.hasClass(obj, AnnotatedMethod.class) && ((AnnotatedMethod) obj)._method == this._method;
    }
    Object writeReplace() {
        return new AnnotatedMethod(new Serialization(this._method));
    }
    Object readResolve() throws NoSuchMethodException, SecurityException {
        Serialization serialization = this._serialization;
        Class<?> cls = serialization.clazz;
        try {
            Method declaredMethod = cls.getDeclaredMethod(serialization.name, serialization.args);
            if (!declaredMethod.isAccessible()) {
                ClassUtil.checkAndFixAccess(declaredMethod, false);
            }
            return new AnnotatedMethod(null, declaredMethod, null, null);
        } catch (Exception unused) {
            throw new IllegalArgumentException("Could not find method '" + this._serialization.name + "' from Class '" + cls.getName());
        }
    }
    public Object getOwner() {
        return null;
    }
    private static final class Serialization implements Serializable {
        private static final long serialVersionUID = 1;
        private final Class<?>[] args;
        private final Class<?> clazz;
        private final String name;

        public Serialization(Method method) {
            this.clazz = method.getDeclaringClass();
            this.name = method.getName();
            this.args = method.getParameterTypes();
        }
    }
}
