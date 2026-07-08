package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.Named;
import org.simpleframework.xml.Transient;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;
 
public interface BeanProperty extends Named {
    JsonFormat.Value EMPTY_FORMAT = new JsonFormat.Value();
    JsonInclude.Value EMPTY_INCLUDE = JsonInclude.Value.empty();
    void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor, SerializerProvider serializerProvider) throws JsonMappingException;
    JsonFormat.Value findPropertyFormat(MapperConfig<?> mapperConfig, Class<?> cls);
    JsonInclude.Value findPropertyInclusion(MapperConfig<?> mapperConfig, Class<?> cls);
    <A extends Annotation> A getAnnotation(Class<A> cls);
    <A extends Annotation> A getContextAnnotation(Class<A> cls);
    PropertyName getFullName();
    AnnotatedMember getMember();
    PropertyMetadata getMetadata();
    String getName();
    JavaType getType();
    PropertyName getWrapperName();
    class Std implements BeanProperty, Serializable {
        private static final long serialVersionUID = 1;
        protected final AnnotatedMember _member;
        protected final PropertyMetadata _metadata;
        protected final PropertyName _name;
        protected final JavaType _type;
        protected final PropertyName _wrapperName;
        public <A extends Annotation> A getContextAnnotation(final Class<A> cls) {
            return null;
        }
        public boolean isVirtual() {
            return false;
        }
        public Std(final PropertyName propertyName, final JavaType javaType, final PropertyName propertyName2, final AnnotatedMember annotatedMember, final PropertyMetadata propertyMetadata) {
            _name = propertyName;
            _type = javaType;
            _wrapperName = propertyName2;
            _metadata = propertyMetadata;
            _member = annotatedMember;
        }
        public Std(final PropertyName propertyName, final JavaType javaType, final PropertyName propertyName2, final Annotations annotations, final AnnotatedMember annotatedMember, final PropertyMetadata propertyMetadata) {
            this(propertyName, javaType, propertyName2, annotatedMember, propertyMetadata);
        }
        public Std(final Std std, final JavaType javaType) {
            this(std._name, javaType, std._wrapperName, std._member, std._metadata);
        }
        public Std withType(final JavaType javaType) {
            return new Std(this, javaType);
        }
        public <A extends Annotation> A getAnnotation(final Class<A> cls) {
            final AnnotatedMember annotatedMember = _member;
            if (null == annotatedMember) {
                return null;
            }
            return (A) annotatedMember.getAnnotation((Class<Transient>) cls);
        }
        public JsonFormat.Value findFormatOverrides(final AnnotationIntrospector annotationIntrospector) {
            final JsonFormat.Value valueFindFormat;
            final AnnotatedMember annotatedMember = _member;
            return (null == annotatedMember || null == annotationIntrospector || null == (valueFindFormat = annotationIntrospector.findFormat(annotatedMember))) ? EMPTY_FORMAT : valueFindFormat;
        }
        public JsonFormat.Value findPropertyFormat(final MapperConfig<?> mapperConfig, final Class<?> cls) {
            final AnnotatedMember annotatedMember;
            final JsonFormat.Value valueFindFormat;
            final JsonFormat.Value defaultPropertyFormat = mapperConfig.getDefaultPropertyFormat(cls);
            final AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
            return (null == annotationIntrospector || null == (annotatedMember = this._member) || null == (valueFindFormat = annotationIntrospector.findFormat(annotatedMember))) ? defaultPropertyFormat : defaultPropertyFormat.withOverrides(valueFindFormat);
        }
        public JsonInclude.Value findPropertyInclusion(final MapperConfig<?> mapperConfig, final Class<?> cls) {
            final AnnotatedMember annotatedMember;
            final JsonInclude.Value valueFindPropertyInclusion;
            final JsonInclude.Value defaultInclusion = mapperConfig.getDefaultInclusion(cls, _type.getRawClass());
            final AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
            return (null == annotationIntrospector || null == (annotatedMember = this._member) || null == (valueFindPropertyInclusion = annotationIntrospector.findPropertyInclusion(annotatedMember))) ? defaultInclusion : defaultInclusion.withOverrides(valueFindPropertyInclusion);
        }
        public List<PropertyName> findAliases(final MapperConfig<?> mapperConfig) {
            return Collections.emptyList();
        }
        public String getName() {
            return _name.getSimpleName();
        }
        public AnnotatedMethod getSetter() {
            return null;
        }
        public PropertyName getFullName() {
            return _name;
        }
        public JavaType getType() {
            return _type;
        }
        public PropertyName getWrapperName() {
            return _wrapperName;
        }
        public boolean isRequired() {
            return _metadata.isRequired();
        }
        public PropertyMetadata getMetadata() {
            return _metadata;
        }
        public AnnotatedMember getMember() {
            return _member;
        }
        public void depositSchemaProperty(final JsonObjectFormatVisitor jsonObjectFormatVisitor, final SerializerProvider serializerProvider) {
            throw new UnsupportedOperationException("Instances of " + this.getClass().getName() + " should not get visited");
        }
    }
    class Bogus implements BeanProperty {
        public void depositSchemaProperty(final JsonObjectFormatVisitor jsonObjectFormatVisitor, final SerializerProvider serializerProvider) throws JsonMappingException {
        }
        public JsonInclude.Value findPropertyInclusion(final MapperConfig<?> mapperConfig, final Class<?> cls) {
            return null;
        }
        public <A extends Annotation> A getAnnotation(final Class<A> cls) {
            return null;
        }
        public <A extends Annotation> A getContextAnnotation(final Class<A> cls) {
            return null;
        }
        public AnnotatedMember getMember() {
            return null;
        }
        public PropertyName getWrapperName() {
            return null;
        }
        public String getName() {
            return "";
        }
        public AnnotatedMethod getSetter() {
            return null;
        }
        public PropertyName getFullName() {
            return PropertyName.NO_NAME;
        }
        public JavaType getType() {
            return TypeFactory.unknownType();
        }
        public PropertyMetadata getMetadata() {
            return PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
        }
        public JsonFormat.Value findPropertyFormat(final MapperConfig<?> mapperConfig, final Class<?> cls) {
            return JsonFormat.Value.empty();
        }
    }
}
