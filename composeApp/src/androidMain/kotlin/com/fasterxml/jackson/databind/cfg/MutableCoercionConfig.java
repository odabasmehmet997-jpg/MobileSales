package com.fasterxml.jackson.databind.cfg;

public class MutableCoercionConfig extends CoercionConfig {
    private static final long serialVersionUID = 1;
    public MutableCoercionConfig() {
    }
    protected MutableCoercionConfig(final MutableCoercionConfig mutableCoercionConfig) {
        super(mutableCoercionConfig);
    }
    public MutableCoercionConfig copy() {
        return new MutableCoercionConfig(this);
    }
    public MutableCoercionConfig setCoercion(final CoercionInputShape coercionInputShape, final CoercionAction coercionAction) {
        _coercionsByShape[coercionInputShape.ordinal()] = coercionAction;
        return this;
    }
    public MutableCoercionConfig setAcceptBlankAsEmpty(final Boolean bool) {
        _acceptBlankAsEmpty = bool;
        return this;
    }
}
