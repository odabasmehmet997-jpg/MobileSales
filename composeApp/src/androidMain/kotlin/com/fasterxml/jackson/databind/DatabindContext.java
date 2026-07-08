package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DatabindContext {
    private static final int MAX_ERROR_STR_LEN = 500;
    public abstract boolean canOverrideAccessModifiers();
    public abstract JavaType constructSpecializedType(JavaType javaType, Class<?> cls);
    public abstract Class<?> getActiveView();
    public abstract AnnotationIntrospector getAnnotationIntrospector();
    public abstract Object getAttribute(Object obj);
    public abstract MapperConfig<?> getConfig();
    public abstract JsonFormat.Value getDefaultPropertyFormat(Class<?> cls);
    public abstract Locale getLocale();
    public abstract TimeZone getTimeZone();
    public abstract TypeFactory getTypeFactory();
    protected abstract JsonMappingException invalidTypeIdException(JavaType javaType, String str, String str2);
    public abstract boolean isEnabled(MapperFeature mapperFeature);
    public abstract <T> T reportBadDefinition(JavaType javaType, String str) throws JsonMappingException;
    public abstract DatabindContext setAttribute(Object obj, Object obj2);
    public JavaType constructType(final Type type) {
        if (null == type) {
            return null;
        }
        return this.getTypeFactory().constructType(type);
    }
    public JavaType resolveSubType(final JavaType javaType, final String str) throws JsonMappingException, IllegalArgumentException {
        if (0 < str.indexOf(60)) {
            final JavaType javaTypeConstructFromCanonical = this.getTypeFactory().constructFromCanonical(str);
            if (javaTypeConstructFromCanonical.isTypeOrSubTypeOf(javaType.getRawClass())) {
                return javaTypeConstructFromCanonical;
            }
        } else {
            try {
                final Class<?> clsFindClass = this.getTypeFactory().findClass(str);
                if (javaType.isTypeOrSuperTypeOf(clsFindClass)) {
                    return this.getTypeFactory().constructSpecializedType(javaType, clsFindClass);
                }
            } catch (final ClassNotFoundException unused) {
                return null;
            } catch (final Exception e2) {
                throw this.invalidTypeIdException(javaType, str, String.format("problem: (%s) %s", e2.getClass().getName(), ClassUtil.exceptionMessage(e2)));
            }
        }
        throw this.invalidTypeIdException(javaType, str, "Not a subtype");
    }
    public JavaType resolveAndValidateSubType(final JavaType javaType, final String str, final PolymorphicTypeValidator polymorphicTypeValidator) throws JsonMappingException, IllegalArgumentException {
        final int iIndexOf = str.indexOf(60);
        if (0 < iIndexOf) {
            return this._resolveAndValidateGeneric(javaType, str, polymorphicTypeValidator, iIndexOf);
        }
        final MapperConfig<?> config = this.getConfig();
        final PolymorphicTypeValidator.Validity validityValidateSubClassName = polymorphicTypeValidator.validateSubClassName(config, javaType, str);
        if (PolymorphicTypeValidator.Validity.DENIED == validityValidateSubClassName) {
            return this._throwSubtypeNameNotAllowed(javaType, str, polymorphicTypeValidator);
        }
        try {
            final Class<?> clsFindClass = this.getTypeFactory().findClass(str);
            if (!javaType.isTypeOrSuperTypeOf(clsFindClass)) {
                return this._throwNotASubtype(javaType, str);
            }
            final JavaType javaTypeConstructSpecializedType = config.getTypeFactory().constructSpecializedType(javaType, clsFindClass);
            return (PolymorphicTypeValidator.Validity.INDETERMINATE != validityValidateSubClassName || PolymorphicTypeValidator.Validity.ALLOWED == polymorphicTypeValidator.validateSubType(config, javaType, javaTypeConstructSpecializedType)) ? javaTypeConstructSpecializedType : (JavaType) this._throwSubtypeClassNotAllowed(javaType, str, polymorphicTypeValidator);
        } catch (final ClassNotFoundException unused) {
            return null;
        } catch (final Exception e2) {
            throw this.invalidTypeIdException(javaType, str, String.format("problem: (%s) %s", e2.getClass().getName(), ClassUtil.exceptionMessage(e2)));
        }
    }
    private JavaType _resolveAndValidateGeneric(final JavaType javaType, final String str, final PolymorphicTypeValidator polymorphicTypeValidator, final int i2) throws JsonMappingException, IllegalArgumentException {
        final MapperConfig<?> config = this.getConfig();
        final PolymorphicTypeValidator.Validity validityValidateSubClassName = polymorphicTypeValidator.validateSubClassName(config, javaType, str.substring(0, i2));
        if (PolymorphicTypeValidator.Validity.DENIED == validityValidateSubClassName) {
            return this._throwSubtypeNameNotAllowed(javaType, str, polymorphicTypeValidator);
        }
        final JavaType javaTypeConstructFromCanonical = this.getTypeFactory().constructFromCanonical(str);
        if (!javaTypeConstructFromCanonical.isTypeOrSubTypeOf(javaType.getRawClass())) {
            return this._throwNotASubtype(javaType, str);
        }
        final PolymorphicTypeValidator.Validity validity = PolymorphicTypeValidator.Validity.ALLOWED;
        return (validityValidateSubClassName == validity || polymorphicTypeValidator.validateSubType(config, javaType, javaTypeConstructFromCanonical) == validity) ? javaTypeConstructFromCanonical : (JavaType) this._throwSubtypeClassNotAllowed(javaType, str, polymorphicTypeValidator);
    }
    protected <T> T _throwNotASubtype(final JavaType javaType, final String str) throws JsonMappingException {
        throw this.invalidTypeIdException(javaType, str, "Not a subtype");
    }
    protected <T> T _throwSubtypeNameNotAllowed(final JavaType javaType, final String str, final PolymorphicTypeValidator polymorphicTypeValidator) throws JsonMappingException {
        throw this.invalidTypeIdException(javaType, str, "Configured `PolymorphicTypeValidator` (of type " + ClassUtil.classNameOf(polymorphicTypeValidator) + ") denied resolution");
    }
    protected <T> T _throwSubtypeClassNotAllowed(final JavaType javaType, final String str, final PolymorphicTypeValidator polymorphicTypeValidator) throws JsonMappingException {
        throw this.invalidTypeIdException(javaType, str, "Configured `PolymorphicTypeValidator` (of type " + ClassUtil.classNameOf(polymorphicTypeValidator) + ") denied resolution");
    }
    public ObjectIdGenerator<?> objectIdGeneratorInstance(final Annotated annotated, final ObjectIdInfo objectIdInfo) throws JsonMappingException {
        final Class<? extends ObjectIdGenerator<?>> generatorType = objectIdInfo.getGeneratorType();
        final MapperConfig<?> config = this.getConfig();
        final HandlerInstantiator handlerInstantiator = config.getHandlerInstantiator();
        ObjectIdGenerator<?> objectIdGeneratorObjectIdGeneratorInstance = null == handlerInstantiator ? null : handlerInstantiator.objectIdGeneratorInstance(config, annotated, generatorType);
        if (null == objectIdGeneratorObjectIdGeneratorInstance) {
            try {
                objectIdGeneratorObjectIdGeneratorInstance = ClassUtil.createInstance(generatorType, config.canOverrideAccessModifiers());
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return objectIdGeneratorObjectIdGeneratorInstance.forScope(objectIdInfo.getScope());
    }
    public ObjectIdResolver objectIdResolverInstance(final Annotated annotated, final ObjectIdInfo objectIdInfo) {
        final Class<? extends ObjectIdResolver> resolverType = objectIdInfo.getResolverType();
        final MapperConfig<?> config = this.getConfig();
        final HandlerInstantiator handlerInstantiator = config.getHandlerInstantiator();
        final ObjectIdResolver objectIdResolverResolverIdGeneratorInstance = null == handlerInstantiator ? null : handlerInstantiator.resolverIdGeneratorInstance(config, annotated, resolverType);
        try {
            return null == objectIdResolverResolverIdGeneratorInstance ? ClassUtil.createInstance(resolverType, config.canOverrideAccessModifiers()) : objectIdResolverResolverIdGeneratorInstance;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    public Converter<Object, Object> converterInstance(final Annotated annotated, final Object obj) throws JsonMappingException {
        if (null == obj) {
            return null;
        }
        if (obj instanceof Converter) {
            return (Converter) obj;
        }
        if (!(obj instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + obj.getClass().getName() + "; expected type Converter or Class<Converter> instead");
        }
        final Class<?> cls = (Class) obj;
        if (Converter.None.class == cls || ClassUtil.isBogusClass(cls)) {
            return null;
        }
        if (!Converter.class.isAssignableFrom(cls)) {
            throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<Converter>");
        }
        final MapperConfig<?> config = this.getConfig();
        final HandlerInstantiator handlerInstantiator = config.getHandlerInstantiator();
        final Converter<?, ?> converterConverterInstance = null != handlerInstantiator ? handlerInstantiator.converterInstance(config, annotated, cls) : null;
        try {
            return null == converterConverterInstance ? (Converter) ClassUtil.createInstance(cls, config.canOverrideAccessModifiers()) : (Converter<Object, Object>) converterConverterInstance;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    public <T> T reportBadDefinition(final Class<?> cls, final String str) throws JsonMappingException {
        return this.reportBadDefinition(this.constructType(cls), str);
    }
    protected final String _format(final String str, final Object... objArr) {
        return 0 < objArr.length ? String.format(str, objArr) : str;
    }
    protected final String _truncate(final String str) {
        if (null == str) {
            return "";
        }
        if (500 >= str.length()) {
            return str;
        }
        return str.substring(0, 500) + "]...[" + str.substring(str.length() - 500);
    }
    protected String _quotedString(final String str) {
        if (null == str) {
            return "[N/A]";
        }
        return String.format("\"%s\"", this._truncate(str));
    }
    protected String _colonConcat(final String str, final String str2) {
        if (null == str2) {
            return str;
        }
        return str + ": " + str2;
    }
    protected String _desc(final String str) {
        if (null == str) {
            return "[N/A]";
        }
        return this._truncate(str);
    }
}
