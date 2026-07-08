package org.simpleframework.xml.core;

import org.simpleframework.xml.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


class MethodScanner extends ContactList {
    private final Detail detail;
    private final MethodPartFactory factory;
    private final PartMap read;
    private final Support support;
    private final PartMap write;

    public MethodScanner(final Detail detail, final Support support) throws Exception {
        factory = new MethodPartFactory(detail, support);
        write = new PartMap();
        read = new PartMap();
        this.support = support;
        this.detail = detail;
        this.scan(detail);
    }

    private void scan(final Detail detail) throws Exception {
        final DefaultType override = detail.getOverride();
        final DefaultType access = detail.getAccess();
        final Class cls = detail.getSuper();
        if (null != cls) {
            this.extend(cls, override);
        }
        this.extract(detail, access);
        this.extract(detail);
        this.build();
        this.validate();
    }

    private void extend(final Class cls, final DefaultType defaultType) throws Exception {
        final Iterator<Contact> it = support.getMethods(cls, defaultType).iterator();
        while (it.hasNext()) {
            this.process((MethodContact) it.next());
        }
    }

    private void extract(final Detail detail) throws Exception {
        for (final MethodDetail methodDetail : detail.getMethods()) {
            final Annotation[] annotations = methodDetail.getAnnotations();
            final Method method = methodDetail.getMethod();
            for (final Annotation annotation : annotations) {
                this.scan(method, annotation, annotations);
            }
        }
    }

    private void extract(final Detail detail, final DefaultType defaultType) throws Exception {
        final List<MethodDetail> methods = detail.getMethods();
        if (DefaultType.PROPERTY == defaultType) {
            for (final MethodDetail methodDetail : methods) {
                final Annotation[] annotations = methodDetail.getAnnotations();
                final Method method = methodDetail.getMethod();
                if (null != this.factory.getType(method)) {
                    this.process(method, annotations);
                }
            }
        }
    }

    private void scan(final Method method, final Annotation annotation, final Annotation[] annotationArr) throws Exception {
        if (annotation instanceof Attribute) {
            this.process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementUnion) {
            this.process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementListUnion) {
            this.process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementMapUnion) {
            this.process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementList) {
            this.process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementArray) {
            this.process(method, annotation, annotationArr);
        }
        if (annotation instanceof ElementMap) {
            this.process(method, annotation, annotationArr);
        }
        if (annotation instanceof Element) {
            this.process(method, annotation, annotationArr);
        }
        if (annotation instanceof Version) {
            this.process(method, annotation, annotationArr);
        }
        if (annotation instanceof Text) {
            this.process(method, annotation, annotationArr);
        }
        if (annotation instanceof Transient) {
            this.remove(method, annotation, annotationArr);
        }
    }

    private void process(final Method method, final Annotation annotation, final Annotation[] annotationArr) throws Exception {
        final MethodPart methodPartFactory = factory.getInstance(method, annotation, annotationArr);
        final MethodType methodType = methodPartFactory.getMethodType();
        if (MethodType.GET == methodType) {
            this.process(methodPartFactory, read);
        }
        if (MethodType.IS == methodType) {
            this.process(methodPartFactory, read);
        }
        if (MethodType.SET == methodType) {
            this.process(methodPartFactory, write);
        }
    }

    private void process(final Method method, final Annotation[] annotationArr) throws Exception {
        final MethodPart methodPartFactory = factory.getInstance(method, annotationArr);
        final MethodType methodType = methodPartFactory.getMethodType();
        if (MethodType.GET == methodType) {
            this.process(methodPartFactory, read);
        }
        if (MethodType.IS == methodType) {
            this.process(methodPartFactory, read);
        }
        if (MethodType.SET == methodType) {
            this.process(methodPartFactory, write);
        }
    }

    private void process(final MethodPart methodPart, final PartMap partMap) {
        final String name = methodPart.getName();
        if (null != name) {
            partMap.put(name, methodPart);
        }
    }

    private void process(final MethodContact methodContact) {
        final MethodPart read = methodContact.getRead();
        final MethodPart write = methodContact.getWrite();
        if (null != write) {
            this.insert(write, this.write);
        }
        this.insert(read, this.read);
    }

    private void insert(MethodPart methodPart, final PartMap partMap) {
        final String name = methodPart.getName();
        final MethodPart remove = partMap.remove(name);
        if (null != remove && this.isText(methodPart)) {
            methodPart = remove;
        }
        partMap.put(name, methodPart);
    }

    private boolean isText(final MethodPart methodPart) {
        return methodPart.getAnnotation() instanceof Text;
    }

    private void remove(final Method method, final Annotation annotation, final Annotation[] annotationArr) throws Exception {
        final MethodPart methodPartFactory = factory.getInstance(method, annotation, annotationArr);
        final MethodType methodType = methodPartFactory.getMethodType();
        if (MethodType.GET == methodType) {
            this.remove(methodPartFactory, read);
        }
        if (MethodType.IS == methodType) {
            this.remove(methodPartFactory, read);
        }
        if (MethodType.SET == methodType) {
            this.remove(methodPartFactory, write);
        }
    }

    private void remove(final MethodPart methodPart, final PartMap partMap) throws Exception {
        final String name = methodPart.getName();
        if (null != name) {
            partMap.remove(name);
        }
    }

    private void build() throws Exception {
        final Iterator<String> it = read.iterator();
        while (it.hasNext()) {
            final String next = it.next();
            final MethodPart methodPart = read.get(next);
            if (null != methodPart) {
                this.build(methodPart, next);
            }
        }
    }

    private void build(final MethodPart methodPart, final String str) throws Exception {
        final MethodPart take = write.take(str);
        if (null != take) {
            this.build(methodPart, take);
        } else {
            this.build(methodPart);
        }
    }

    private void build(final MethodPart methodPart) throws Exception {
        this.add(new MethodContact(methodPart));
    }

    private void build(final MethodPart methodPart, final MethodPart methodPart2) throws Exception {
        final Annotation annotation = methodPart.getAnnotation();
        final String name = methodPart.getName();
        if (!methodPart2.getAnnotation().equals(annotation)) {
            throw new MethodException("Annotations do not match for '%s' in %s", name, detail);
        }
        final Class type = methodPart.getType();
        if (type != methodPart2.getType()) {
            throw new MethodException("Method types do not match for %s in %s", name, type);
        }
        this.add(new MethodContact(methodPart, methodPart2));
    }

    private void validate() throws Exception {
        final Iterator<String> it = write.iterator();
        while (it.hasNext()) {
            final String next = it.next();
            final MethodPart methodPart = write.get(next);
            if (null != methodPart) {
                this.validate(methodPart, next);
            }
        }
    }

    private void validate(final MethodPart methodPart, final String str) throws Exception {
        final MethodPart take = read.take(str);
        final Method method = methodPart.getMethod();
        if (null == take) {
            throw new MethodException("No matching get method for %s in %s", method, detail);
        }
    }

    private static class PartMap extends LinkedHashMap<String, MethodPart> implements Iterable<String> {
        private PartMap() {
        }

        @Override // java.lang.Iterable
        public Iterator<String> iterator() {
            return this.keySet().iterator();
        }

        public MethodPart take(final String str) {
            return this.remove(str);
        }
    }
}
