package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import org.simpleframework.xml.Transient;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public final class AnnotatedClass extends Annotated implements TypeResolutionContext {
    private static final Creators NO_CREATORS = new Creators(null, Collections.emptyList(), Collections.emptyList());
    private final AnnotationIntrospector _annotationIntrospector;
    private final TypeBindings _bindings;
    private final Class<?> _class;
    private final Annotations _classAnnotations;
    private final boolean _collectAnnotations;
    private Creators _creators;
    private List<AnnotatedField> _fields;
    private AnnotatedMethodMap _memberMethods;
    private final ClassIntrospector.MixInResolver _mixInResolver;
    private transient Boolean _nonStaticInnerClass;
    private final Class<?> _primaryMixIn;
    private final List<JavaType> _superTypes;
    private final JavaType _type;
    private final TypeFactory _typeFactory;
    AnnotatedClass(JavaType javaType, Class<?> cls, List<JavaType> list, Class<?> cls2, Annotations annotations, TypeBindings typeBindings, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver, TypeFactory typeFactory, boolean z) {
        this._type = javaType;
        this._class = cls;
        this._superTypes = list;
        this._primaryMixIn = cls2;
        this._classAnnotations = annotations;
        this._bindings = typeBindings;
        this._annotationIntrospector = annotationIntrospector;
        this._mixInResolver = mixInResolver;
        this._typeFactory = typeFactory;
        this._collectAnnotations = z;
    }
    AnnotatedClass(Class<?> cls) {
        this._type = null;
        this._class = cls;
        this._superTypes = Collections.emptyList();
        this._primaryMixIn = null;
        this._classAnnotations = AnnotationCollector.emptyAnnotations();
        this._bindings = TypeBindings.emptyBindings();
        this._annotationIntrospector = null;
        this._mixInResolver = null;
        this._typeFactory = null;
        this._collectAnnotations = false;
    }
    public JavaType resolveType(Type type) {
        return this._typeFactory.resolveMemberType(type, this._bindings);
    }
    public Class<?> getAnnotated() {
        return this._class;
    }
    public int getModifiers() {
        return this._class.getModifiers();
    }
    public String getName() {
        return this._class.getName();
    }
    public <A extends Annotation> Transient getAnnotation(Class<Transient> cls) {
        return this._classAnnotations.get(cls);
    }
    public boolean hasAnnotation(Class<?> cls) {
        return this._classAnnotations.has(cls);
    }
    public boolean hasOneOf(Class<? extends Annotation>[] clsArr) {
        return this._classAnnotations.hasOneOf(clsArr);
    }
    public Class<?> getRawType() {
        return this._class;
    }
    public Iterable<Annotation> annotations() {
        Annotations annotations = this._classAnnotations;
        if (annotations instanceof AnnotationMap) {
            return ((AnnotationMap) annotations).annotations();
        }
        if ((annotations instanceof AnnotationCollector.OneAnnotation) || (annotations instanceof AnnotationCollector.TwoAnnotations)) {
            throw new UnsupportedOperationException("please use getAnnotations/ hasAnnotation to check for Annotations");
        }
        return Collections.emptyList();
    }
    public JavaType getType() {
        return this._type;
    }
    public Annotations getAnnotations() {
        return this._classAnnotations;
    }
    public boolean hasAnnotations() {
        return this._classAnnotations.size() > 0;
    }
    public AnnotatedConstructor getDefaultConstructor() {
        return _creators().defaultConstructor;
    }
    public List<AnnotatedConstructor> getConstructors() {
        return _creators().constructors;
    }
    public List<AnnotatedMethod> getFactoryMethods() {
        return _creators().creatorMethods;
    }
    public Iterable<AnnotatedMethod> memberMethods() {
        return _methods();
    }
    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        return _methods().find(str, clsArr);
    }
    public Iterable<AnnotatedField> fields() {
        return _fields();
    }
    public boolean isNonStaticInnerClass() {
        Boolean boolValueOf = this._nonStaticInnerClass;
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(ClassUtil.isNonStaticInnerClass(this._class));
            this._nonStaticInnerClass = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }
    private List<AnnotatedField> _fields() {
        List<AnnotatedField> listCollectFields = this._fields;
        if (listCollectFields == null) {
            JavaType javaType = this._type;
            if (javaType == null) {
                listCollectFields = Collections.emptyList();
            } else {
                listCollectFields = AnnotatedFieldCollector.collectFields(this._annotationIntrospector, this, this._mixInResolver, this._typeFactory, javaType, this._collectAnnotations);
            }
            this._fields = listCollectFields;
        }
        return listCollectFields;
    }
    private AnnotatedMethodMap _methods() {
        AnnotatedMethodMap annotatedMethodMapCollectMethods = this._memberMethods;
        if (annotatedMethodMapCollectMethods == null) {
            JavaType javaType = this._type;
            if (javaType == null) {
                annotatedMethodMapCollectMethods = new AnnotatedMethodMap();
            } else {
                annotatedMethodMapCollectMethods = AnnotatedMethodCollector.collectMethods(this._annotationIntrospector, this, this._mixInResolver, this._typeFactory, javaType, this._superTypes, this._primaryMixIn, this._collectAnnotations);
            }
            this._memberMethods = annotatedMethodMapCollectMethods;
        }
        return annotatedMethodMapCollectMethods;
    }
    private Creators _creators() {
        Creators creatorsCollectCreators = this._creators;
        if (creatorsCollectCreators == null) {
            JavaType javaType = this._type;
            if (javaType == null) {
                creatorsCollectCreators = NO_CREATORS;
            } else {
                creatorsCollectCreators = AnnotatedCreatorCollector.collectCreators(this._annotationIntrospector, this._typeFactory, this, javaType, this._primaryMixIn, this._collectAnnotations);
            }
            this._creators = creatorsCollectCreators;
        }
        return creatorsCollectCreators;
    }
    public String toString() {
        return "[AnnotatedClass " + this._class.getName() + "]";
    }
    public int hashCode() {
        return this._class.getName().hashCode();
    }
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return ClassUtil.hasClass(obj, AnnotatedClass.class) && ((AnnotatedClass) obj)._class == this._class;
    }
    public record Creators(AnnotatedConstructor defaultConstructor, List<AnnotatedConstructor> constructors,
                           List<AnnotatedMethod> creatorMethods) {
    }
}
