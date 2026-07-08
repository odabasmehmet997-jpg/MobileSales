package com.fasterxml.jackson.databind.cfg;

import java.io.Serializable;
import java.util.Arrays;

public class CoercionConfig implements Serializable {
    private static final int INPUT_SHAPE_COUNT = CoercionInputShape.values().length;
    private static final long serialVersionUID = 1;
    protected Boolean _acceptBlankAsEmpty;
    protected final CoercionAction[] _coercionsByShape;
    public CoercionConfig() {
        _coercionsByShape = new CoercionAction[CoercionConfig.INPUT_SHAPE_COUNT];
        _acceptBlankAsEmpty = Boolean.FALSE;
    }
    protected CoercionConfig(final CoercionConfig coercionConfig) {
        _acceptBlankAsEmpty = coercionConfig._acceptBlankAsEmpty;
        final CoercionAction[] coercionActionArr = coercionConfig._coercionsByShape;
        _coercionsByShape = Arrays.copyOf(coercionActionArr, coercionActionArr.length);
    }
    public CoercionAction findAction(final CoercionInputShape coercionInputShape) {
        return _coercionsByShape[coercionInputShape.ordinal()];
    }
    public Boolean getAcceptBlankAsEmpty() {
        return _acceptBlankAsEmpty;
    }
}
