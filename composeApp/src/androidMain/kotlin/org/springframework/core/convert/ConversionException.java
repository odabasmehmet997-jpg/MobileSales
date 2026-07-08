package org.springframework.core.convert;

import org.springframework.core.NestedRuntimeException;

public abstract class ConversionException extends NestedRuntimeException {
    protected ConversionException(final String str) {
        super(str);
    }
    protected ConversionException(final String str, final Throwable th) {
        super(str, th);
    }
}
