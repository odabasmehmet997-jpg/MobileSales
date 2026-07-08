package org.springframework.core.convert;


/* loaded from: classes.dex */
public final class ConverterNotFoundException extends ConversionException {
    private final TypeDescriptor sourceType;
    private final TypeDescriptor targetType;

    public ConverterNotFoundException(final TypeDescriptor typeDescriptor, final TypeDescriptor typeDescriptor2) {
        super("No converter found capable of converting from type " + typeDescriptor + " to type " + typeDescriptor2);
        sourceType = typeDescriptor;
        targetType = typeDescriptor2;
    }

    public TypeDescriptor getSourceType() {
        return sourceType;
    }

    public TypeDescriptor getTargetType() {
        return targetType;
    }
}
