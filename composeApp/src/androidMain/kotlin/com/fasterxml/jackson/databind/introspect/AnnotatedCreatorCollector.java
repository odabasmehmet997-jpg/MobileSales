package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
final class AnnotatedCreatorCollector extends CollectorBase {
    private final boolean _collectAnnotations;
    private AnnotatedConstructor _defaultConstructor;
    private final TypeResolutionContext _typeContext;
    AnnotatedCreatorCollector(AnnotationIntrospector annotationIntrospector, TypeResolutionContext typeResolutionContext, boolean z) {
        super(annotationIntrospector);
        this._typeContext = typeResolutionContext;
        this._collectAnnotations = z;
    }
    public static AnnotatedClass.Creators collectCreators(AnnotationIntrospector annotationIntrospector, TypeFactory typeFactory, TypeResolutionContext typeResolutionContext, JavaType javaType, Class<?> cls, boolean z) {
        return new AnnotatedCreatorCollector(annotationIntrospector, typeResolutionContext, z | (cls != null)).collect(typeFactory, javaType, cls);
    }
    AnnotatedClass.Creators collect(TypeFactory typeFactory, JavaType javaType, Class<?> cls) throws SecurityException {
        List<AnnotatedConstructor> list_findPotentialConstructors = _findPotentialConstructors(javaType, cls);
        List<AnnotatedMethod> list_findPotentialFactories = _findPotentialFactories(typeFactory, javaType, cls);
        if (this._collectAnnotations) {
            AnnotatedConstructor annotatedConstructor = this._defaultConstructor;
            if (annotatedConstructor != null && this._intr.hasIgnoreMarker(annotatedConstructor)) {
                this._defaultConstructor = null;
            }
            int size = list_findPotentialConstructors.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                if (this._intr.hasIgnoreMarker(list_findPotentialConstructors.get(size))) {
                    list_findPotentialConstructors.remove(size);
                }
            }
            int size2 = list_findPotentialFactories.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    break;
                }
                if (this._intr.hasIgnoreMarker(list_findPotentialFactories.get(size2))) {
                    list_findPotentialFactories.remove(size2);
                }
            }
        }
        return new AnnotatedClass.Creators(this._defaultConstructor, list_findPotentialConstructors, list_findPotentialFactories);
    }
    private List<AnnotatedConstructor> _findPotentialConstructors(JavaType javaType, Class<?> cls) throws SecurityException {
        ClassUtil.Ctor ctor;
        ArrayList arrayList;
        int r0;
        List<AnnotatedConstructor> listEmptyList;
        if (javaType.isEnumType()) {
            ctor = null;
            arrayList = null;
        } else {
            ctor = null;
            arrayList = null;
            for (ClassUtil.Ctor ctor2 : ClassUtil.getConstructors(javaType.getRawClass())) {
                if (isIncludableConstructor(ctor2.getConstructor())) {
                    if (ctor2.getParamCount() == 0) {
                        ctor = ctor2;
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(ctor2);
                    }
                }
            }
        }
        if (arrayList == null) {
            listEmptyList = Collections.emptyList();
            if (ctor == null) {
                return listEmptyList;
            }
            r0 = 0;
        } else {
            int size = arrayList.size();
            ArrayList arrayList2 = new ArrayList(size);
            for (int r3 = 0; r3 < size; r3++) {
                arrayList2.add(null);
            }
            r0 = size;
            listEmptyList = arrayList2;
        }
        if (cls != null) {
            MemberKey[] memberKeyArr = null;
            for (ClassUtil.Ctor ctor3 : ClassUtil.getConstructors(cls)) {
                if (ctor3.getParamCount() == 0) {
                    if (ctor != null) {
                        this._defaultConstructor = constructDefaultConstructor(ctor, ctor3);
                        ctor = null;
                    }
                } else if (arrayList != null) {
                    if (memberKeyArr == null) {
                        memberKeyArr = new MemberKey[r0];
                        for (int r9 = 0; r9 < r0; r9++) {
                            memberKeyArr[r9] = new MemberKey(((ClassUtil.Ctor) arrayList.get(r9)).getConstructor());
                        }
                    }
                    MemberKey memberKey = new MemberKey(ctor3.getConstructor());
                    int r10 = 0;
                    while (true) {
                        if (r10 >= r0) {
                            break;
                        }
                        if (memberKey.equals(memberKeyArr[r10])) {
                            listEmptyList.set(r10, constructNonDefaultConstructor((ClassUtil.Ctor) arrayList.get(r10), ctor3));
                            break;
                        }
                        r10++;
                    }
                }
            }
        }
        if (ctor != null) {
            this._defaultConstructor = constructDefaultConstructor(ctor, null);
        }
        for (int r1 = 0; r1 < r0; r1++) {
            if (listEmptyList.get(r1) == null) {
                listEmptyList.set(r1, constructNonDefaultConstructor((ClassUtil.Ctor) arrayList.get(r1), null));
            }
        }
        return listEmptyList;
    }
    private List<AnnotatedMethod> _findPotentialFactories(TypeFactory typeFactory, JavaType javaType, Class<?> cls) throws SecurityException {
        ArrayList arrayList = null;
        for (Method method : ClassUtil.getClassMethods(javaType.getRawClass())) {
            if (_isIncludableFactoryMethod(method)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(method);
            }
        }
        if (arrayList == null) {
            return Collections.emptyList();
        }
        TypeResolutionContext.Empty empty = new TypeResolutionContext.Empty(typeFactory);
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int r8 = 0; r8 < size; r8++) {
            arrayList2.add(null);
        }
        if (cls != null) {
            MemberKey[] memberKeyArr = null;
            for (Method method2 : cls.getDeclaredMethods()) {
                if (_isIncludableFactoryMethod(method2)) {
                    if (memberKeyArr == null) {
                        memberKeyArr = new MemberKey[size];
                        for (int r13 = 0; r13 < size; r13++) {
                            memberKeyArr[r13] = new MemberKey((Method) arrayList.get(r13));
                        }
                    }
                    MemberKey memberKey = new MemberKey(method2);
                    int r14 = 0;
                    while (true) {
                        if (r14 >= size) {
                            break;
                        }
                        if (memberKey.equals(memberKeyArr[r14])) {
                            arrayList2.set(r14, constructFactoryCreator((Method) arrayList.get(r14), empty, method2));
                            break;
                        }
                        r14++;
                    }
                }
            }
        }
        for (int r5 = 0; r5 < size; r5++) {
            if (arrayList2.get(r5) == null) {
                Method method3 = (Method) arrayList.get(r5);
                arrayList2.set(r5, constructFactoryCreator(method3, MethodGenericTypeResolver.narrowMethodTypeParameters(method3, javaType, typeFactory, empty), null));
            }
        }
        return arrayList2;
    }
    private static boolean _isIncludableFactoryMethod(Method method) {
        return Modifier.isStatic(method.getModifiers()) && !method.isSynthetic();
    }
    private AnnotatedConstructor constructDefaultConstructor(ClassUtil.Ctor ctor, ClassUtil.Ctor ctor2) {
        return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), collectAnnotations(ctor, ctor2), CollectorBase.NO_ANNOTATION_MAPS);
    }
    private AnnotatedConstructor constructNonDefaultConstructor(ClassUtil.Ctor ctor, ClassUtil.Ctor ctor2) {
        Annotation[][] annotationArr = new Annotation[0][];
        int paramCount = ctor.getParamCount();
        if (this._intr == null) {
            return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), CollectorBase._emptyAnnotationMap(), CollectorBase._emptyAnnotationMaps(paramCount));
        }
        if (paramCount == 0) {
            return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), collectAnnotations(ctor, ctor2), CollectorBase.NO_ANNOTATION_MAPS);
        }
        Annotation[][] parameterAnnotations = ctor.getParameterAnnotations();
        AnnotationMap[] annotationMapArrCollectAnnotations;
        annotationMapArrCollectAnnotations = null;
        annotationMapArrCollectAnnotations = null;
        if (paramCount != parameterAnnotations.length) {
            Class<?> declaringClass = ctor.getDeclaringClass();
            if (ClassUtil.isEnumType(declaringClass) && paramCount == parameterAnnotations.length + 2) {
                annotationArr = new Annotation[parameterAnnotations.length + 2][];
                System.arraycopy(parameterAnnotations, 0, annotationArr, 2, parameterAnnotations.length);
                annotationMapArrCollectAnnotations = collectAnnotations(annotationArr, null);
            } else {
                if (declaringClass.isMemberClass() && paramCount == parameterAnnotations.length + 1) {
                    annotationArr = new Annotation[parameterAnnotations.length + 1][];
                    System.arraycopy(parameterAnnotations, 0, annotationArr, 1, parameterAnnotations.length);
                    annotationArr[0] = CollectorBase.NO_ANNOTATIONS;
                    annotationMapArrCollectAnnotations = collectAnnotations(annotationArr, null);
                }
                if (annotationMapArrCollectAnnotations == null) {
                    throw new IllegalStateException(String.format("Internal error: constructor for %s has mismatch: %d parameters; %d sets of annotations", ctor.getDeclaringClass().getName(), Integer.valueOf(paramCount), Integer.valueOf(parameterAnnotations.length)));
                }
            }
            parameterAnnotations = annotationArr;
            if (annotationMapArrCollectAnnotations == null) {
            }
        } else {
            annotationMapArrCollectAnnotations = collectAnnotations(parameterAnnotations, ctor2 != null ? ctor2.getParameterAnnotations() : null);
        }
        return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), collectAnnotations(ctor, ctor2), annotationMapArrCollectAnnotations);
    }
    private AnnotatedMethod constructFactoryCreator(Method method, TypeResolutionContext typeResolutionContext, Method method2) {
        int length = method.getParameterTypes().length;
        if (this._intr == null) {
            return new AnnotatedMethod(typeResolutionContext, method, CollectorBase._emptyAnnotationMap(), CollectorBase._emptyAnnotationMaps(length));
        }
        if (length == 0) {
            return new AnnotatedMethod(typeResolutionContext, method, collectAnnotations(method, method2), CollectorBase.NO_ANNOTATION_MAPS);
        }
        return new AnnotatedMethod(typeResolutionContext, method, collectAnnotations(method, method2), collectAnnotations(method.getParameterAnnotations(), method2 == null ? null : method2.getParameterAnnotations()));
    }
    private AnnotationMap[] collectAnnotations(Annotation[][] annotationArr, Annotation[][] annotationArr2) {
        if (this._collectAnnotations) {
            int length = annotationArr.length;
            AnnotationMap[] annotationMapArr = new AnnotationMap[length];
            for (int r2 = 0; r2 < length; r2++) {
                AnnotationCollector annotationCollectorCollectAnnotations = collectAnnotations(AnnotationCollector.emptyCollector(), annotationArr[r2]);
                if (annotationArr2 != null) {
                    annotationCollectorCollectAnnotations = collectAnnotations(annotationCollectorCollectAnnotations, annotationArr2[r2]);
                }
                annotationMapArr[r2] = annotationCollectorCollectAnnotations.asAnnotationMap();
            }
            return annotationMapArr;
        }
        return CollectorBase.NO_ANNOTATION_MAPS;
    }
    private AnnotationMap collectAnnotations(ClassUtil.Ctor ctor, ClassUtil.Ctor ctor2) {
        if (this._collectAnnotations) {
            AnnotationCollector annotationCollectorCollectAnnotations = collectAnnotations(ctor.getDeclaredAnnotations());
            if (ctor2 != null) {
                annotationCollectorCollectAnnotations = collectAnnotations(annotationCollectorCollectAnnotations, ctor2.getDeclaredAnnotations());
            }
            return annotationCollectorCollectAnnotations.asAnnotationMap();
        }
        return CollectorBase._emptyAnnotationMap();
    }
    private AnnotationMap collectAnnotations(AnnotatedElement annotatedElement, AnnotatedElement annotatedElement2) {
        AnnotationCollector annotationCollectorCollectAnnotations = collectAnnotations(annotatedElement.getDeclaredAnnotations());
        if (annotatedElement2 != null) {
            annotationCollectorCollectAnnotations = collectAnnotations(annotationCollectorCollectAnnotations, annotatedElement2.getDeclaredAnnotations());
        }
        return annotationCollectorCollectAnnotations.asAnnotationMap();
    }
    private static boolean isIncludableConstructor(Constructor<?> constructor) {
        return !constructor.isSynthetic();
    }
}
