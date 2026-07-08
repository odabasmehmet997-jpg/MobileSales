package org.simpleframework.xml.core;

import org.simpleframework.xml.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class SignatureScanner {
    private final SignatureBuilder builder;
    private final Constructor constructor;
    private final ParameterFactory factory;
    private final ParameterMap registry;
    private final Class type;
    public SignatureScanner(final Constructor constructor, final ParameterMap parameterMap, final Support support) throws Exception {
        builder = new SignatureBuilder(constructor);
        factory = new ParameterFactory(support);
        final Class declaringClass = constructor.getDeclaringClass();
        type = declaringClass;
        this.constructor = constructor;
        registry = parameterMap;
        this.scan(declaringClass);
    }
    public boolean isValid() {
        return builder.isValid();
    }
    public List<Signature> getSignatures() throws Exception {
        return builder.build();
    }
    private void scan(final Class cls) throws Exception {
        final Class<?>[] parameterTypes = constructor.getParameterTypes();
        for (int i2 = 0; i2 < parameterTypes.length; i2++) {
            this.scan(parameterTypes[i2], i2);
        }
    }
    private void scan(final Class cls, final int i2) throws Exception {
        final Annotation[][] parameterAnnotations = constructor.getParameterAnnotations();
        int i3 = 0;
        while (true) {
            final Annotation[] annotationArr = parameterAnnotations[i2];
            if (i3 >= annotationArr.length) {
                return;
            }
            final Iterator<Parameter> it = this.process(annotationArr[i3], i2).iterator();
            while (it.hasNext()) {
                builder.insert(it.next(), i2);
            }
            i3++;
        }
    }
    private List<Parameter> process(final Annotation annotation, final int i2) throws Exception {
        if (annotation instanceof Attribute) {
            return this.create(annotation, i2);
        }
        if (annotation instanceof Element) {
            return this.create(annotation, i2);
        }
        if (annotation instanceof ElementList) {
            return this.create(annotation, i2);
        }
        if (annotation instanceof ElementArray) {
            return this.create(annotation, i2);
        }
        if (annotation instanceof ElementMap) {
            return this.create(annotation, i2);
        }
        if (annotation instanceof ElementListUnion) {
            return this.union(annotation, i2);
        }
        if (annotation instanceof ElementMapUnion) {
            return this.union(annotation, i2);
        }
        if (annotation instanceof ElementUnion) {
            return this.union(annotation, i2);
        }
        if (annotation instanceof Text) {
            return this.create(annotation, i2);
        }
        return Collections.emptyList();
    }
    private List<Parameter> union(final Annotation annotation, final int i2) throws Exception {
        final Signature signature = new Signature(constructor);
        for (final Annotation annotation2 : this.extract(annotation)) {
            final Parameter parameterFactory = factory.getInstance(constructor, annotation, annotation2, i2);
            final String path = parameterFactory.getPath();
            if (signature.contains(path)) {
                throw new UnionException("Annotation name '%s' used more than once in %s for %s", path, annotation, type);
            }
            signature.set(path, parameterFactory);
            this.register(parameterFactory);
        }
        return signature.getAll();
    }
    private List<Parameter> create(final Annotation annotation, final int i2) throws Exception {
        final Parameter parameterFactory = factory.getInstance(constructor, annotation, i2);
        if (null != parameterFactory) {
            this.register(parameterFactory);
        }
        return Collections.singletonList(parameterFactory);
    }
    private Annotation[] extract(final Annotation annotation) throws Exception {
        final Method[] declaredMethods = annotation.annotationType().getDeclaredMethods();
        if (1 != declaredMethods.length) {
            throw new UnionException("Annotation '%s' is not a valid union for %s", annotation, type);
        }
        return (Annotation[]) declaredMethods[0].invoke(annotation, null);
    }
    private void register(final Parameter parameter) throws Exception {
        final String path = parameter.getPath();
        final Object key = parameter.getKey();
        if (registry.containsKey(key)) {
            this.validate(parameter, key);
        }
        if (registry.containsKey(path)) {
            this.validate(parameter, path);
        }
        registry.put(path, parameter);
        registry.put(key, parameter);
    }
    private void validate(final Parameter parameter, final Object obj) throws Exception {
        final Parameter parameter2 = registry.get(obj);
        if (parameter.isText() != parameter2.isText()) {
            final Annotation annotation = parameter.getAnnotation();
            final Annotation annotation2 = parameter2.getAnnotation();
            final String path = parameter.getPath();
            if (!annotation.equals(annotation2)) {
                throw new ConstructorException("Annotations do not match for '%s' in %s", path, type);
            }
            if (parameter2.getType() != parameter.getType()) {
                throw new ConstructorException("Parameter types do not match for '%s' in %s", path, type);
            }
        }
    }
}
