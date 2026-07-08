package org.springframework.beans;

import org.springframework.core.NestedRuntimeException;
import org.springframework.util.ObjectUtils;


public abstract class BeansException extends NestedRuntimeException {
    private static final long serialVersionUID = 1;
    protected BeansException(String str) {
        super(str);
    }
    protected BeansException(String str, Throwable th) {
        super(str, th);
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof final BeansException beansException)) {
            return false;
        }
        return getMessage().equals(beansException.getMessage()) && ObjectUtils.nullSafeEquals(getCause(), beansException.getCause());
    }
    public int hashCode() {
        return getMessage().hashCode();
    }
    public String toString() {
        return "BeansException{" +
                "message='" + getMessage() + '\'' +
                ", cause=" + getCause() +
                '}';
    }
}
