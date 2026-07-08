package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.cfg.MapperConfig;

import java.util.Set;

public class BasicPolymorphicTypeValidator extends PolymorphicTypeValidator.Base {
    private static final long serialVersionUID = 1;
    protected final TypeMatcher[] _baseTypeMatchers;
    protected final Set<Class<?>> _invalidBaseTypes;
    protected final TypeMatcher[] _subClassMatchers;
    protected final NameMatcher[] _subTypeNameMatchers;

    public static abstract class NameMatcher {
        public abstract boolean match(MapperConfig<?> mapperConfig, String str);
    }

    public static abstract class TypeMatcher {
        public abstract boolean match(MapperConfig<?> mapperConfig, Class<?> cls);
    }

    public static class Builder {
        protected Builder() {
        }
    }

    protected BasicPolymorphicTypeValidator(final Set<Class<?>> set, final TypeMatcher[] typeMatcherArr, final NameMatcher[] nameMatcherArr, final TypeMatcher[] typeMatcherArr2) {
        _invalidBaseTypes = set;
        _baseTypeMatchers = typeMatcherArr;
        _subTypeNameMatchers = nameMatcherArr;
        _subClassMatchers = typeMatcherArr2;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator.Base, com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator
    public PolymorphicTypeValidator.Validity validateBaseType(final MapperConfig<?> mapperConfig, final JavaType javaType) {
        final Class<?> rawClass = javaType.getRawClass();
        final Set<Class<?>> set = _invalidBaseTypes;
        if (null != set && set.contains(rawClass)) {
            return PolymorphicTypeValidator.Validity.DENIED;
        }
        final TypeMatcher[] typeMatcherArr = _baseTypeMatchers;
        if (null != typeMatcherArr) {
            for (final TypeMatcher typeMatcher : typeMatcherArr) {
                if (typeMatcher.match(mapperConfig, rawClass)) {
                    return PolymorphicTypeValidator.Validity.ALLOWED;
                }
            }
        }
        return PolymorphicTypeValidator.Validity.INDETERMINATE;
    }

    @Override // com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator.Base, com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator
    public PolymorphicTypeValidator.Validity validateSubClassName(final MapperConfig<?> mapperConfig, final JavaType javaType, final String str) throws JsonMappingException {
        final NameMatcher[] nameMatcherArr = _subTypeNameMatchers;
        if (null != nameMatcherArr) {
            for (final NameMatcher nameMatcher : nameMatcherArr) {
                if (nameMatcher.match(mapperConfig, str)) {
                    return PolymorphicTypeValidator.Validity.ALLOWED;
                }
            }
        }
        return PolymorphicTypeValidator.Validity.INDETERMINATE;
    }

    @Override // com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator.Base, com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator
    public PolymorphicTypeValidator.Validity validateSubType(final MapperConfig<?> mapperConfig, final JavaType javaType, final JavaType javaType2) throws JsonMappingException {
        if (null != this._subClassMatchers) {
            final Class<?> rawClass = javaType2.getRawClass();
            for (final TypeMatcher typeMatcher : _subClassMatchers) {
                if (typeMatcher.match(mapperConfig, rawClass)) {
                    return PolymorphicTypeValidator.Validity.ALLOWED;
                }
            }
        }
        return PolymorphicTypeValidator.Validity.INDETERMINATE;
    }
}
