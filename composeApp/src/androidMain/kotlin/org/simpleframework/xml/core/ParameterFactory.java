package org.simpleframework.xml.core;

import org.simpleframework.xml.*;
import org.simpleframework.xml.stream.Format;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

class ParameterFactory {
    private final Format format;
    public ParameterFactory(final Support support) {
        format = support.getFormat();
    }
    public Parameter getInstance(final Constructor constructor, final Annotation annotation, final int i2) throws Exception {
        return this.getInstance(constructor, annotation, null, i2);
    }
    public Parameter getInstance(final Constructor constructor, final Annotation annotation, final Annotation annotation2, final int i2) throws Exception {
        final Constructor constructor2 = this.getConstructor(annotation);
        if (null != annotation2) {
            return (Parameter) constructor2.newInstance(constructor, annotation, annotation2, format, Integer.valueOf(i2));
        }
        return (Parameter) constructor2.newInstance(constructor, annotation, format, Integer.valueOf(i2));
    }
    private Constructor getConstructor(final Annotation annotation) throws Exception {
        final Constructor constructor = this.getBuilder(annotation).getConstructor();
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        return constructor;
    }
    private ParameterBuilder getBuilder(final Annotation annotation) throws Exception {
        if (annotation instanceof Element) {
            return new ParameterBuilder(ElementParameter.class, Element.class);
        }
        if (annotation instanceof ElementList) {
            return new ParameterBuilder(ElementListParameter.class, ElementList.class);
        }
        if (annotation instanceof ElementArray) {
            return new ParameterBuilder(ElementArrayParameter.class, ElementArray.class);
        }
        if (annotation instanceof ElementMapUnion) {
            return new ParameterBuilder(ElementMapUnionParameter.class, ElementMapUnion.class, ElementMap.class);
        }
        if (annotation instanceof ElementListUnion) {
            return new ParameterBuilder(ElementListUnionParameter.class, ElementListUnion.class, ElementList.class);
        }
        if (annotation instanceof ElementUnion) {
            return new ParameterBuilder(ElementUnionParameter.class, ElementUnion.class, Element.class);
        }
        if (annotation instanceof ElementMap) {
            return new ParameterBuilder(ElementMapParameter.class, ElementMap.class);
        }
        if (annotation instanceof Attribute) {
            return new ParameterBuilder(AttributeParameter.class, Attribute.class);
        }
        if (annotation instanceof Text) {
            return new ParameterBuilder(TextParameter.class, Text.class);
        }
        throw new PersistenceException("Annotation %s not supported", annotation);
    }
    private record ParameterBuilder(Class type, Class label, Class entry) {
            public ParameterBuilder(final Class cls, final Class cls2) {
                this(cls, cls2, null);
            }

        public Constructor getConstructor() throws Exception {
                final Class cls = entry;
                if (null != cls) {
                    return this.getConstructor(label, cls);
                }
                return this.getConstructor(label);
            }

            public Constructor getConstructor(final Class cls) throws Exception {
                return this.getConstructor(Constructor.class, cls, Format.class, Integer.TYPE);
            }

            public Constructor getConstructor(final Class cls, final Class cls2) throws Exception {
                return this.getConstructor(Constructor.class, cls, cls2, Format.class, Integer.TYPE);
            }

            private Constructor getConstructor(final Class... clsArr) throws Exception {
                return type.getConstructor(clsArr);
            }
        }
}
