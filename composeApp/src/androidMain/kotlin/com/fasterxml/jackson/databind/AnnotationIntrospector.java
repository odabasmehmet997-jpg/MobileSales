package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import org.simpleframework.xml.Transient;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AnnotationIntrospector implements Serializable {
    public void findAndAddVirtualProperties(final MapperConfig<?> mapperConfig, final AnnotatedClass annotatedClass, final List<BeanPropertyWriter> list) {
    }
    public VisibilityChecker<?> findAutoDetectVisibility(final AnnotatedClass annotatedClass, final VisibilityChecker<?> visibilityChecker) {
        return visibilityChecker;
    }
    public String findClassDescription(final AnnotatedClass annotatedClass) {
        return null;
    }
    public Object findContentDeserializer(final Annotated annotated) {
        return null;
    }
    public Object findContentSerializer(final Annotated annotated) {
        return null;
    }
    public JsonCreator.Mode findCreatorBinding(final Annotated annotated) {
        return null;
    }
    public Enum<?> findDefaultEnumValue(final Class<Enum<?>> cls) {
        return null;
    }
    public Object findDeserializationContentConverter(final AnnotatedMember annotatedMember) {
        return null;
    }
    public Class<?> findDeserializationContentType(final Annotated annotated, final JavaType javaType) {
        return null;
    }
    public Object findDeserializationConverter(final Annotated annotated) {
        return null;
    }
    public Class<?> findDeserializationKeyType(final Annotated annotated, final JavaType javaType) {
        return null;
    }
    public Class<?> findDeserializationType(final Annotated annotated, final JavaType javaType) {
        return null;
    }
    public Object findDeserializer(final Annotated annotated) {
        return null;
    }
    public void findEnumAliases(final Class<?> cls, final Enum<?>[] enumArr, final String[][] strArr) {
    }
    public String[] findEnumValues(final Class<?> cls, final Enum<?>[] enumArr, final String[] strArr) {
        return strArr;
    }
    public Object findFilterId(final Annotated annotated) {
        return null;
    }
    public Boolean findIgnoreUnknownProperties(final AnnotatedClass annotatedClass) {
        return null;
    }
    public String findImplicitPropertyName(final AnnotatedMember annotatedMember) {
        return null;
    }
    public Object findInjectableValueId(final AnnotatedMember annotatedMember) {
        return null;
    }
    public Object findKeyDeserializer(final Annotated annotated) {
        return null;
    }
    public Object findKeySerializer(final Annotated annotated) {
        return null;
    }
    public Boolean findMergeInfo(final Annotated annotated) {
        return null;
    }
    public PropertyName findNameForDeserialization(final Annotated annotated) {
        return null;
    }
    public PropertyName findNameForSerialization(final Annotated annotated) {
        return null;
    }
    public Object findNamingStrategy(final AnnotatedClass annotatedClass) {
        return null;
    }
    public Object findNullSerializer(final Annotated annotated) {
        return null;
    }
    public ObjectIdInfo findObjectIdInfo(final Annotated annotated) {
        return null;
    }
    public ObjectIdInfo findObjectReferenceInfo(final Annotated annotated, final ObjectIdInfo objectIdInfo) {
        return objectIdInfo;
    }
    public Class<?> findPOJOBuilder(final AnnotatedClass annotatedClass) {
        return null;
    }
    public JsonPOJOBuilder.Value findPOJOBuilderConfig(final AnnotatedClass annotatedClass) {
        return null;
    }
    public String[] findPropertiesToIgnore(final Annotated annotated, final boolean z) {
        return null;
    }
    public JsonProperty.Access findPropertyAccess(final Annotated annotated) {
        return null;
    }
    public List<PropertyName> findPropertyAliases(final Annotated annotated) {
        return null;
    }
    public TypeResolverBuilder<?> findPropertyContentTypeResolver(final MapperConfig<?> mapperConfig, final AnnotatedMember annotatedMember, final JavaType javaType) {
        return null;
    }
    public String findPropertyDefaultValue(final Annotated annotated) {
        return null;
    }
    public String findPropertyDescription(final Annotated annotated) {
        return null;
    }
    public Integer findPropertyIndex(final Annotated annotated) {
        return null;
    }
    public TypeResolverBuilder<?> findPropertyTypeResolver(final MapperConfig<?> mapperConfig, final AnnotatedMember annotatedMember, final JavaType javaType) {
        return null;
    }
    public ReferenceProperty findReferenceType(final AnnotatedMember annotatedMember) {
        return null;
    }
    public PropertyName findRenameByField(final MapperConfig<?> mapperConfig, final AnnotatedField annotatedField, final PropertyName propertyName) {
        return null;
    }
    public PropertyName findRootName(final AnnotatedClass annotatedClass) {
        return null;
    }
    public Object findSerializationContentConverter(final AnnotatedMember annotatedMember) {
        return null;
    }
    public Class<?> findSerializationContentType(final Annotated annotated, final JavaType javaType) {
        return null;
    }
    public Object findSerializationConverter(final Annotated annotated) {
        return null;
    }
    public JsonInclude.Include findSerializationInclusion(final Annotated annotated, final JsonInclude.Include include) {
        return include;
    }
    public JsonInclude.Include findSerializationInclusionForContent(final Annotated annotated, final JsonInclude.Include include) {
        return include;
    }
    public Class<?> findSerializationKeyType(final Annotated annotated, final JavaType javaType) {
        return null;
    }
    public String[] findSerializationPropertyOrder(final AnnotatedClass annotatedClass) {
        return null;
    }
    public Boolean findSerializationSortAlphabetically(final Annotated annotated) {
        return null;
    }
    public Class<?> findSerializationType(final Annotated annotated) {
        return null;
    }
    public JsonSerialize.Typing findSerializationTyping(final Annotated annotated) {
        return null;
    }
    public Object findSerializer(final Annotated annotated) {
        return null;
    }
    public List<NamedType> findSubtypes(final Annotated annotated) {
        return null;
    }
    public String findTypeName(final AnnotatedClass annotatedClass) {
        return null;
    }
    public TypeResolverBuilder<?> findTypeResolver(final MapperConfig<?> mapperConfig, final AnnotatedClass annotatedClass, final JavaType javaType) {
        return null;
    }
    public NameTransformer findUnwrappingNameTransformer(final AnnotatedMember annotatedMember) {
        return null;
    }
    public Object findValueInstantiator(final AnnotatedClass annotatedClass) {
        return null;
    }
    public Class<?>[] findViews(final Annotated annotated) {
        return null;
    }
    public PropertyName findWrapperName(final Annotated annotated) {
        return null;
    }
    public boolean hasAnyGetterAnnotation(final AnnotatedMethod annotatedMethod) {
        return false;
    }
    public Boolean hasAnySetter(final Annotated annotated) {
        return null;
    }
    public boolean hasAnySetterAnnotation(final AnnotatedMethod annotatedMethod) {
        return false;
    }
    public Boolean hasAsKey(final MapperConfig<?> mapperConfig, final Annotated annotated) {
        return null;
    }
    public boolean hasAsValueAnnotation(final AnnotatedMethod annotatedMethod) {
        return false;
    }
    public boolean hasCreatorAnnotation(final Annotated annotated) {
        return false;
    }
    public boolean hasIgnoreMarker(final AnnotatedMember annotatedMember) {
        return false;
    }
    public Boolean hasRequiredMarker(final AnnotatedMember annotatedMember) {
        return null;
    }
    public boolean isAnnotationBundle(final Annotation annotation) {
        return false;
    }
    public Boolean isIgnorableType(final AnnotatedClass annotatedClass) {
        return null;
    }
    public Boolean isTypeId(final AnnotatedMember annotatedMember) {
        return null;
    }
    public JavaType refineDeserializationType(final MapperConfig<?> mapperConfig, final Annotated annotated, final JavaType javaType) throws JsonMappingException {
        return javaType;
    }
    public JavaType refineSerializationType(final MapperConfig<?> mapperConfig, final Annotated annotated, final JavaType javaType) throws JsonMappingException {
        return javaType;
    }
    public AnnotatedMethod resolveSetterConflict(final MapperConfig<?> mapperConfig, final AnnotatedMethod annotatedMethod, final AnnotatedMethod annotatedMethod2) {
        return null;
    }
    public abstract Version version();
    public static class ReferenceProperty {
        private final String _name;
        private final Type _type;

        public enum Type {
            MANAGED_REFERENCE,
            BACK_REFERENCE
        }

        public ReferenceProperty(final Type type, final String str) {
            _type = type;
            _name = str;
        }

        public static ReferenceProperty managed(final String str) {
            return new ReferenceProperty(Type.MANAGED_REFERENCE, str);
        }

        public static ReferenceProperty back(final String str) {
            return new ReferenceProperty(Type.BACK_REFERENCE, str);
        }

        public String getName() {
            return _name;
        }

        public boolean isManagedReference() {
            return Type.MANAGED_REFERENCE == this._type;
        }

        public boolean isBackReference() {
            return Type.BACK_REFERENCE == this._type;
        }
    }
    public static AnnotationIntrospector nopInstance() {
        return NopAnnotationIntrospector.instance;
    }
    public static AnnotationIntrospector pair(final AnnotationIntrospector annotationIntrospector, final AnnotationIntrospector annotationIntrospector2) {
        return new AnnotationIntrospectorPair(annotationIntrospector, annotationIntrospector2);
    }
    public Collection<AnnotationIntrospector> allIntrospectors() {
        return Collections.singletonList(this);
    }
    public Collection<AnnotationIntrospector> allIntrospectors(final Collection<AnnotationIntrospector> collection) {
        collection.add(this);
        return collection;
    }
    public JsonIgnoreProperties.Value findPropertyIgnoralByName(final MapperConfig<?> mapperConfig, final Annotated annotated) {
        return this.findPropertyIgnorals(annotated);
    }
    public JsonIncludeProperties.Value findPropertyInclusionByName(final MapperConfig<?> mapperConfig, final Annotated annotated) {
        return JsonIncludeProperties.Value.all();
    }
    public JsonIgnoreProperties.Value findPropertyIgnorals(final Annotated annotated) {
        return JsonIgnoreProperties.Value.empty();
    }
    public JacksonInject.Value findInjectableValue(final AnnotatedMember annotatedMember) {
        final Object objFindInjectableValueId = this.findInjectableValueId(annotatedMember);
        if (null != objFindInjectableValueId) {
            return JacksonInject.Value.forId(objFindInjectableValueId);
        }
        return null;
    }
    public JsonFormat.Value findFormat(final Annotated annotated) {
        return JsonFormat.Value.empty();
    }
    public JsonInclude.Value findPropertyInclusion(final Annotated annotated) {
        return JsonInclude.Value.empty();
    }
    public Boolean hasAsValue(final Annotated annotated) {
        if ((annotated instanceof AnnotatedMethod) && this.hasAsValueAnnotation((AnnotatedMethod) annotated)) {
            return Boolean.TRUE;
        }
        return null;
    }
    public Boolean hasAnyGetter(final Annotated annotated) {
        if ((annotated instanceof AnnotatedMethod) && this.hasAnyGetterAnnotation((AnnotatedMethod) annotated)) {
            return Boolean.TRUE;
        }
        return null;
    }
    public String findEnumValue(final Enum<?> r1) {
        return r1.name();
    }
    public JsonSetter.Value findSetterInfo(final Annotated annotated) {
        return JsonSetter.Value.empty();
    }
    public JsonCreator.Mode findCreatorAnnotation(final MapperConfig<?> mapperConfig, final Annotated annotated) {
        if (!this.hasCreatorAnnotation(annotated)) {
            return null;
        }
        final JsonCreator.Mode modeFindCreatorBinding = this.findCreatorBinding(annotated);
        return null == modeFindCreatorBinding ? JsonCreator.Mode.DEFAULT : modeFindCreatorBinding;
    }
    protected <A extends Annotation> A _findAnnotation(final Annotated annotated, final Class<A> cls) {
        return (A) annotated.getAnnotation((Class<Transient>) cls);
    }
    protected boolean _hasAnnotation(final Annotated annotated, final Class<? extends Annotation> cls) {
        return annotated.hasAnnotation(cls);
    }
    protected boolean _hasOneOf(final Annotated annotated, final Class<? extends Annotation>[] clsArr) {
        return annotated.hasOneOf(clsArr);
    }
}
