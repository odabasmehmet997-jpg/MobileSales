package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.DefaultAccessorNamingStrategy;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public final class BaseSettings implements Serializable {
    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC");
    private static final long serialVersionUID = 1;
    private final AccessorNamingStrategy.Provider _accessorNaming;
    private final AnnotationIntrospector _annotationIntrospector;
    private final ClassIntrospector _classIntrospector;
    private final DateFormat _dateFormat;
    private final Base64Variant _defaultBase64;
    private final HandlerInstantiator _handlerInstantiator;
    private final Locale _locale;
    private final PropertyNamingStrategy _propertyNamingStrategy;
    private final TimeZone _timeZone;
    private final TypeFactory _typeFactory;
    private final TypeResolverBuilder<?> _typeResolverBuilder;
    private final PolymorphicTypeValidator _typeValidator;

    public BaseSettings(final ClassIntrospector classIntrospector, final AnnotationIntrospector annotationIntrospector, final PropertyNamingStrategy propertyNamingStrategy, final TypeFactory typeFactory, final TypeResolverBuilder<?> typeResolverBuilder, final DateFormat dateFormat, final HandlerInstantiator handlerInstantiator, final Locale locale, final TimeZone timeZone, final Base64Variant base64Variant, final PolymorphicTypeValidator polymorphicTypeValidator, final AccessorNamingStrategy.Provider provider) {
        _classIntrospector = classIntrospector;
        _annotationIntrospector = annotationIntrospector;
        _propertyNamingStrategy = propertyNamingStrategy;
        _typeFactory = typeFactory;
        _typeResolverBuilder = typeResolverBuilder;
        _dateFormat = dateFormat;
        _handlerInstantiator = handlerInstantiator;
        _locale = locale;
        _timeZone = timeZone;
        _defaultBase64 = base64Variant;
        _typeValidator = polymorphicTypeValidator;
        _accessorNaming = provider;
    }

    @Deprecated
    public BaseSettings(final ClassIntrospector classIntrospector, final AnnotationIntrospector annotationIntrospector, final PropertyNamingStrategy propertyNamingStrategy, final TypeFactory typeFactory, final TypeResolverBuilder<?> typeResolverBuilder, final DateFormat dateFormat, final HandlerInstantiator handlerInstantiator, final Locale locale, final TimeZone timeZone, final Base64Variant base64Variant, final PolymorphicTypeValidator polymorphicTypeValidator) {
        this(classIntrospector, annotationIntrospector, propertyNamingStrategy, typeFactory, typeResolverBuilder, dateFormat, handlerInstantiator, locale, timeZone, base64Variant, polymorphicTypeValidator, new DefaultAccessorNamingStrategy.Provider());
    }

    public BaseSettings copy() {
        return new BaseSettings(_classIntrospector.copy(), _annotationIntrospector, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, _dateFormat, _handlerInstantiator, _locale, _timeZone, _defaultBase64, _typeValidator, _accessorNaming);
    }

    public BaseSettings withClassIntrospector(final ClassIntrospector classIntrospector) {
        return _classIntrospector == classIntrospector ? this : new BaseSettings(classIntrospector, _annotationIntrospector, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, _dateFormat, _handlerInstantiator, _locale, _timeZone, _defaultBase64, _typeValidator, _accessorNaming);
    }

    public BaseSettings withAnnotationIntrospector(final AnnotationIntrospector annotationIntrospector) {
        return _annotationIntrospector == annotationIntrospector ? this : new BaseSettings(_classIntrospector, annotationIntrospector, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, _dateFormat, _handlerInstantiator, _locale, _timeZone, _defaultBase64, _typeValidator, _accessorNaming);
    }

    public BaseSettings withInsertedAnnotationIntrospector(final AnnotationIntrospector annotationIntrospector) {
        return this.withAnnotationIntrospector(AnnotationIntrospectorPair.create(annotationIntrospector, _annotationIntrospector));
    }

    public BaseSettings withAppendedAnnotationIntrospector(final AnnotationIntrospector annotationIntrospector) {
        return this.withAnnotationIntrospector(AnnotationIntrospectorPair.create(_annotationIntrospector, annotationIntrospector));
    }

    public BaseSettings withPropertyNamingStrategy(final PropertyNamingStrategy propertyNamingStrategy) {
        return _propertyNamingStrategy == propertyNamingStrategy ? this : new BaseSettings(_classIntrospector, _annotationIntrospector, propertyNamingStrategy, _typeFactory, _typeResolverBuilder, _dateFormat, _handlerInstantiator, _locale, _timeZone, _defaultBase64, _typeValidator, _accessorNaming);
    }

    public BaseSettings withAccessorNaming(final AccessorNamingStrategy.Provider provider) {
        return _accessorNaming == provider ? this : new BaseSettings(_classIntrospector, _annotationIntrospector, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, _dateFormat, _handlerInstantiator, _locale, _timeZone, _defaultBase64, _typeValidator, provider);
    }

    public BaseSettings withTypeFactory(final TypeFactory typeFactory) {
        return _typeFactory == typeFactory ? this : new BaseSettings(_classIntrospector, _annotationIntrospector, _propertyNamingStrategy, typeFactory, _typeResolverBuilder, _dateFormat, _handlerInstantiator, _locale, _timeZone, _defaultBase64, _typeValidator, _accessorNaming);
    }

    public BaseSettings withTypeResolverBuilder(final TypeResolverBuilder<?> typeResolverBuilder) {
        return _typeResolverBuilder == typeResolverBuilder ? this : new BaseSettings(_classIntrospector, _annotationIntrospector, _propertyNamingStrategy, _typeFactory, typeResolverBuilder, _dateFormat, _handlerInstantiator, _locale, _timeZone, _defaultBase64, _typeValidator, _accessorNaming);
    }

    public BaseSettings withDateFormat(DateFormat dateFormat) {
        if (_dateFormat == dateFormat) {
            return this;
        }
        if (null != dateFormat && this.hasExplicitTimeZone()) {
            dateFormat = this._force(dateFormat, _timeZone);
        }
        return new BaseSettings(_classIntrospector, _annotationIntrospector, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, dateFormat, _handlerInstantiator, _locale, _timeZone, _defaultBase64, _typeValidator, _accessorNaming);
    }

    public BaseSettings withHandlerInstantiator(final HandlerInstantiator handlerInstantiator) {
        return _handlerInstantiator == handlerInstantiator ? this : new BaseSettings(_classIntrospector, _annotationIntrospector, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, _dateFormat, handlerInstantiator, _locale, _timeZone, _defaultBase64, _typeValidator, _accessorNaming);
    }

    public BaseSettings with(final Locale locale) {
        return _locale == locale ? this : new BaseSettings(_classIntrospector, _annotationIntrospector, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, _dateFormat, _handlerInstantiator, locale, _timeZone, _defaultBase64, _typeValidator, _accessorNaming);
    }

    public BaseSettings with(final TimeZone timeZone) {
        if (timeZone == _timeZone) {
            return this;
        }
        return new BaseSettings(_classIntrospector, _annotationIntrospector, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, this._force(_dateFormat, null == timeZone ? BaseSettings.DEFAULT_TIMEZONE : timeZone), _handlerInstantiator, _locale, timeZone, _defaultBase64, _typeValidator, _accessorNaming);
    }

    public BaseSettings with(final Base64Variant base64Variant) {
        return base64Variant == _defaultBase64 ? this : new BaseSettings(_classIntrospector, _annotationIntrospector, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, _dateFormat, _handlerInstantiator, _locale, _timeZone, base64Variant, _typeValidator, _accessorNaming);
    }

    public BaseSettings with(final PolymorphicTypeValidator polymorphicTypeValidator) {
        return polymorphicTypeValidator == _typeValidator ? this : new BaseSettings(_classIntrospector, _annotationIntrospector, _propertyNamingStrategy, _typeFactory, _typeResolverBuilder, _dateFormat, _handlerInstantiator, _locale, _timeZone, _defaultBase64, polymorphicTypeValidator, _accessorNaming);
    }

    public ClassIntrospector getClassIntrospector() {
        return _classIntrospector;
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return _annotationIntrospector;
    }

    public PropertyNamingStrategy getPropertyNamingStrategy() {
        return _propertyNamingStrategy;
    }

    public AccessorNamingStrategy.Provider getAccessorNaming() {
        return _accessorNaming;
    }

    public TypeFactory getTypeFactory() {
        return _typeFactory;
    }

    public TypeResolverBuilder<?> getTypeResolverBuilder() {
        return _typeResolverBuilder;
    }

    public PolymorphicTypeValidator getPolymorphicTypeValidator() {
        return _typeValidator;
    }

    public DateFormat getDateFormat() {
        return _dateFormat;
    }

    public HandlerInstantiator getHandlerInstantiator() {
        return _handlerInstantiator;
    }

    public Locale getLocale() {
        return _locale;
    }

    public TimeZone getTimeZone() {
        final TimeZone timeZone = _timeZone;
        return null == timeZone ? BaseSettings.DEFAULT_TIMEZONE : timeZone;
    }

    public boolean hasExplicitTimeZone() {
        return null != this._timeZone;
    }

    public Base64Variant getBase64Variant() {
        return _defaultBase64;
    }

    private DateFormat _force(final DateFormat dateFormat, final TimeZone timeZone) {
        if (dateFormat instanceof StdDateFormat) {
            return ((StdDateFormat) dateFormat).withTimeZone(timeZone);
        }
        final DateFormat dateFormat2 = (DateFormat) dateFormat.clone();
        dateFormat2.setTimeZone(timeZone);
        return dateFormat2;
    }
}
