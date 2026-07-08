package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;

public final class ConstructorDetector implements Serializable {
    private static final long serialVersionUID = 1;
    private final boolean _allowJDKTypeCtors;
    private final boolean _requireCtorAnnotation;
    private final SingleArgConstructor _singleArgMode;
    public static final ConstructorDetector DEFAULT = new ConstructorDetector(SingleArgConstructor.HEURISTIC);
    public static final ConstructorDetector USE_PROPERTIES_BASED = new ConstructorDetector(SingleArgConstructor.PROPERTIES);
    public static final ConstructorDetector USE_DELEGATING = new ConstructorDetector(SingleArgConstructor.DELEGATING);
    public static final ConstructorDetector EXPLICIT_ONLY = new ConstructorDetector(SingleArgConstructor.REQUIRE_MODE);
    public enum SingleArgConstructor {
        DELEGATING,
        PROPERTIES,
        HEURISTIC,
        REQUIRE_MODE
    }
    private ConstructorDetector(final SingleArgConstructor singleArgConstructor, final boolean z, final boolean z2) {
        _singleArgMode = singleArgConstructor;
        _requireCtorAnnotation = z;
        _allowJDKTypeCtors = z2;
    }
    private ConstructorDetector(final SingleArgConstructor singleArgConstructor) {
        this(singleArgConstructor, false, false);
    }
    public ConstructorDetector withSingleArgMode(final SingleArgConstructor singleArgConstructor) {
        return new ConstructorDetector(singleArgConstructor, _requireCtorAnnotation, _allowJDKTypeCtors);
    }
    public ConstructorDetector withRequireAnnotation(final boolean z) {
        return new ConstructorDetector(_singleArgMode, z, _allowJDKTypeCtors);
    }
    public ConstructorDetector withAllowJDKTypeConstructors(final boolean z) {
        return new ConstructorDetector(_singleArgMode, _requireCtorAnnotation, z);
    }
    public SingleArgConstructor singleArgMode() {
        return _singleArgMode;
    }
    public boolean requireCtorAnnotation() {
        return _requireCtorAnnotation;
    }
    public boolean allowJDKTypeConstructors() {
        return _allowJDKTypeCtors;
    }
    public boolean singleArgCreatorDefaultsToDelegating() {
        return SingleArgConstructor.DELEGATING == this._singleArgMode;
    }
    public boolean singleArgCreatorDefaultsToProperties() {
        return SingleArgConstructor.PROPERTIES == this._singleArgMode;
    }
    public boolean shouldIntrospectorImplicitConstructors(final Class<?> cls) {
        if (_requireCtorAnnotation) {
            return false;
        }
        return _allowJDKTypeCtors || !ClassUtil.isJDKClass(cls) || Throwable.class.isAssignableFrom(cls);
    }
}
