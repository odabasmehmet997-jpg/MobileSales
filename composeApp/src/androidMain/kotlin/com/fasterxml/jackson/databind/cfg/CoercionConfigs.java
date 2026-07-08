package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.type.LogicalType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CoercionConfigs implements Serializable {
    private static final int TARGET_TYPE_COUNT = LogicalType.values().length;
    private static final long serialVersionUID = 1;
    protected CoercionAction _defaultAction;
    protected final MutableCoercionConfig _defaultCoercions;
    protected Map<Class<?>, MutableCoercionConfig> _perClassCoercions;
    protected MutableCoercionConfig[] _perTypeCoercions;
    public CoercionConfigs() {
        this(CoercionAction.TryConvert, new MutableCoercionConfig(), null, null);
    }
    protected CoercionConfigs(final CoercionAction coercionAction, final MutableCoercionConfig mutableCoercionConfig, final MutableCoercionConfig[] mutableCoercionConfigArr, final Map<Class<?>, MutableCoercionConfig> map) {
        _defaultCoercions = mutableCoercionConfig;
        _defaultAction = coercionAction;
        _perTypeCoercions = mutableCoercionConfigArr;
        _perClassCoercions = map;
    }
    public CoercionConfigs copy() {
        final MutableCoercionConfig[] mutableCoercionConfigArr;
        final MutableCoercionConfig[] mutableCoercionConfigArr2 = _perTypeCoercions;
        HashMap map = null;
        if (null == mutableCoercionConfigArr2) {
            mutableCoercionConfigArr = null;
        } else {
            final int length = mutableCoercionConfigArr2.length;
            mutableCoercionConfigArr = new MutableCoercionConfig[length];
            for (int i2 = 0; i2 < length; i2++) {
                mutableCoercionConfigArr[i2] = CoercionConfigs._copy(_perTypeCoercions[i2]);
            }
        }
        if (null != this._perClassCoercions) {
            map = new HashMap();
            for (final Map.Entry<Class<?>, MutableCoercionConfig> entry : _perClassCoercions.entrySet()) {
                map.put(entry.getKey(), entry.getValue().copy());
            }
        }
        return new CoercionConfigs(_defaultAction, _defaultCoercions.copy(), mutableCoercionConfigArr, map);
    }
    private static MutableCoercionConfig _copy(final MutableCoercionConfig mutableCoercionConfig) {
        if (null == mutableCoercionConfig) {
            return null;
        }
        return mutableCoercionConfig.copy();
    }
    public MutableCoercionConfig defaultCoercions() {
        return _defaultCoercions;
    }
    public MutableCoercionConfig findOrCreateCoercion(final LogicalType logicalType) {
        if (null == this._perTypeCoercions) {
            _perTypeCoercions = new MutableCoercionConfig[CoercionConfigs.TARGET_TYPE_COUNT];
        }
        final MutableCoercionConfig mutableCoercionConfig = _perTypeCoercions[logicalType.ordinal()];
        if (null != mutableCoercionConfig) {
            return mutableCoercionConfig;
        }
        final MutableCoercionConfig[] mutableCoercionConfigArr = _perTypeCoercions;
        final int iOrdinal = logicalType.ordinal();
        final MutableCoercionConfig mutableCoercionConfig2 = new MutableCoercionConfig();
        mutableCoercionConfigArr[iOrdinal] = mutableCoercionConfig2;
        return mutableCoercionConfig2;
    }
    public MutableCoercionConfig findOrCreateCoercion(final Class<?> cls) {
        if (null == this._perClassCoercions) {
            _perClassCoercions = new HashMap();
        }
        final MutableCoercionConfig mutableCoercionConfig = _perClassCoercions.get(cls);
        if (null != mutableCoercionConfig) {
            return mutableCoercionConfig;
        }
        final MutableCoercionConfig mutableCoercionConfig2 = new MutableCoercionConfig();
        _perClassCoercions.put(cls, mutableCoercionConfig2);
        return mutableCoercionConfig2;
    }
    public CoercionAction findCoercion(final DeserializationConfig deserializationConfig, final LogicalType logicalType, final Class<?> cls, final CoercionInputShape coercionInputShape) {
        final MutableCoercionConfig mutableCoercionConfig;
        final CoercionAction coercionActionFindAction;
        final MutableCoercionConfig mutableCoercionConfig2;
        final CoercionAction coercionActionFindAction2;
        final Map<Class<?>, MutableCoercionConfig> map = _perClassCoercions;
        if (null != map && null != cls && null != (mutableCoercionConfig2 = map.get(cls)) && null != (coercionActionFindAction2 = mutableCoercionConfig2.findAction(coercionInputShape))) {
            return coercionActionFindAction2;
        }
        final MutableCoercionConfig[] mutableCoercionConfigArr = _perTypeCoercions;
        if (null != mutableCoercionConfigArr && null != logicalType && null != (mutableCoercionConfig = mutableCoercionConfigArr[logicalType.ordinal()]) && null != (coercionActionFindAction = mutableCoercionConfig.findAction(coercionInputShape))) {
            return coercionActionFindAction;
        }
        final CoercionAction coercionActionFindAction3 = _defaultCoercions.findAction(coercionInputShape);
        if (null != coercionActionFindAction3) {
            return coercionActionFindAction3;
        }
        final int i2 = C11921.SwitchMapcomfasterxmljacksondatabindcfgCoercionInputShape[coercionInputShape.ordinal()];
        boolean z = true;
        if (1 == i2) {
            return deserializationConfig.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT) ? CoercionAction.AsNull : CoercionAction.Fail;
        }
        if (2 == i2) {
            if (LogicalType.Integer == logicalType) {
                return deserializationConfig.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT) ? CoercionAction.TryConvert : CoercionAction.Fail;
            }
        } else if (3 == i2 && LogicalType.Enum == logicalType && deserializationConfig.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
            return CoercionAction.Fail;
        }
        if (LogicalType.Float != logicalType && LogicalType.Integer != logicalType && LogicalType.Boolean != logicalType && LogicalType.DateTime != logicalType) {
            z = false;
        }
        if (z && !deserializationConfig.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS)) {
            return CoercionAction.Fail;
        }
        if (CoercionInputShape.EmptyString == coercionInputShape) {
            if (z || deserializationConfig.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) {
                return CoercionAction.AsNull;
            }
            if (LogicalType.OtherScalar == logicalType) {
                return CoercionAction.TryConvert;
            }
            return CoercionAction.Fail;
        }
        return _defaultAction;
    }
    enum C11921 {
        ;
        static final int[] SwitchMapcomfasterxmljacksondatabindcfgCoercionInputShape;

        static {
            final int[] iArr = new int[CoercionInputShape.values().length];
            SwitchMapcomfasterxmljacksondatabindcfgCoercionInputShape = iArr;
            try {
                iArr[CoercionInputShape.EmptyArray.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C11921.SwitchMapcomfasterxmljacksondatabindcfgCoercionInputShape[CoercionInputShape.Float.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C11921.SwitchMapcomfasterxmljacksondatabindcfgCoercionInputShape[CoercionInputShape.Integer.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
        }
    }
    public CoercionAction findCoercionFromBlankString(final DeserializationConfig deserializationConfig, final LogicalType logicalType, final Class<?> cls, final CoercionAction coercionAction) {
        Boolean acceptBlankAsEmpty;
        CoercionAction coercionActionFindAction;
        final MutableCoercionConfig mutableCoercionConfig;
        final MutableCoercionConfig mutableCoercionConfig2;
        final Map<Class<?>, MutableCoercionConfig> map = _perClassCoercions;
        if (null == map || null == cls || null == (mutableCoercionConfig2 = map.get(cls))) {
            acceptBlankAsEmpty = null;
            coercionActionFindAction = null;
        } else {
            acceptBlankAsEmpty = mutableCoercionConfig2.getAcceptBlankAsEmpty();
            coercionActionFindAction = mutableCoercionConfig2.findAction(CoercionInputShape.EmptyString);
        }
        final MutableCoercionConfig[] mutableCoercionConfigArr = _perTypeCoercions;
        if (null != mutableCoercionConfigArr && null != logicalType && null != (mutableCoercionConfig = mutableCoercionConfigArr[logicalType.ordinal()])) {
            if (null == acceptBlankAsEmpty) {
                acceptBlankAsEmpty = mutableCoercionConfig.getAcceptBlankAsEmpty();
            }
            if (null == coercionActionFindAction) {
                coercionActionFindAction = mutableCoercionConfig.findAction(CoercionInputShape.EmptyString);
            }
        }
        if (null == acceptBlankAsEmpty) {
            acceptBlankAsEmpty = _defaultCoercions.getAcceptBlankAsEmpty();
        }
        if (null == coercionActionFindAction) {
            coercionActionFindAction = _defaultCoercions.findAction(CoercionInputShape.EmptyString);
        }
        return !Boolean.TRUE.equals(acceptBlankAsEmpty) ? coercionAction : null != coercionActionFindAction ? coercionActionFindAction : deserializationConfig.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) ? CoercionAction.AsNull : CoercionAction.Fail;
    }
}
