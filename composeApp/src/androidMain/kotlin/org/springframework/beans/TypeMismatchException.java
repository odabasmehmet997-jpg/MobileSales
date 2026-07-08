package org.springframework.beans;

import org.springframework.util.ClassUtils;

import java.beans.PropertyChangeEvent;

import static ads_mobile_sdk.gg1.r0;

public class TypeMismatchException extends PropertyAccessException {
    public static final String ERROR_CODE = "typeMismatch";
    private static final long serialVersionUID = 1;
    private final Class<?> requiredType;
    private final transient Object value;
    public TypeMismatchException(PropertyChangeEvent propertyChangeEvent, Class<?> cls) {
        this(propertyChangeEvent, cls, null);
    }
    public TypeMismatchException(PropertyChangeEvent propertyChangeEvent, Class<?> cls, Throwable th) {
        super(propertyChangeEvent, r0.toString(), th);
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Failed to convert property value of type '");
        sb.append(ClassUtils.getDescriptiveType(propertyChangeEvent.getNewValue()));
        sb.append("'");
        String str2 = "";
        if (null != cls) {
            str = " to required type '" + ClassUtils.getQualifiedName(cls) + "'";
        } else {
            str = "";
        }
        sb.append(str);
        if (null != propertyChangeEvent.getPropertyName()) {
            str2 = " for property '" + propertyChangeEvent.getPropertyName() + "'";
        }
        sb.append(str2);
        this.value = propertyChangeEvent.getNewValue();
        this.requiredType = cls;
    }
    public TypeMismatchException(Object obj, Class<?> cls) {
        this(obj, cls, null);
    }
    public TypeMismatchException(Object obj, Class<?> cls, Throwable th) {
        super(r0.toString(), th);
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Failed to convert value of type '");
        sb.append(ClassUtils.getDescriptiveType(obj));
        sb.append("'");
        if (null != cls) {
            str = " to required type '" + ClassUtils.getQualifiedName(cls) + "'";
        } else {
            str = "";
        }
        sb.append(str);
        this.value = obj;
        this.requiredType = cls;
    }
    public Object getValue() {
        return this.value;
    }
    public Class<?> getRequiredType() {
        return this.requiredType;
    }
    public String getErrorCode() {
        return ERROR_CODE;
    }
}
