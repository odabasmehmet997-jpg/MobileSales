package org.springframework.beans;

import java.beans.PropertyChangeEvent;

public abstract class PropertyAccessException extends BeansException {
    private static final long serialVersionUID = 1;
    private transient PropertyChangeEvent propertyChangeEvent;
    public abstract String getErrorCode();
    protected PropertyAccessException(PropertyChangeEvent propertyChangeEvent, String str, Throwable th) {
        super(str, th);
        this.propertyChangeEvent = propertyChangeEvent;
    }
    protected PropertyAccessException(String str, Throwable th) {
        super(str, th);
    }
    public PropertyChangeEvent getPropertyChangeEvent() {
        return this.propertyChangeEvent;
    }
    public String getPropertyName() {
        PropertyChangeEvent propertyChangeEvent = this.propertyChangeEvent;
        if (propertyChangeEvent != null) {
            return propertyChangeEvent.getPropertyName();
        }
        return null;
    }
    public Object getValue() {
        PropertyChangeEvent propertyChangeEvent = this.propertyChangeEvent;
        if (propertyChangeEvent != null) {
            return propertyChangeEvent.getNewValue();
        }
        return null;
    }
}
