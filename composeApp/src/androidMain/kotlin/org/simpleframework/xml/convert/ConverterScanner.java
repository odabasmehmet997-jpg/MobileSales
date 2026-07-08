package org.simpleframework.xml.convert;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import java.lang.annotation.Annotation;

class ConverterScanner {
    private final ConverterFactory factory = new ConverterFactory();
    private final ScannerBuilder builder = new ScannerBuilder();
    public Converter getConverter(final Type type, final Value value) throws Exception {
        final Convert convert = this.getConvert(type, this.getType(type, value));
        if (null != convert) {
            return factory.getInstance(convert);
        }
        return null;
    }
    public Converter getConverter(final Type type, final Object obj) throws Exception {
        final Convert convert = this.getConvert(type, this.getType(type, obj));
        if (null != convert) {
            return factory.getInstance(convert);
        }
        return null;
    }
    private Convert getConvert(final Type type, final Class cls) throws Exception {
        final Convert convert = this.getConvert(type);
        return null == convert ? this.getConvert(cls) : convert;
    }
    private Convert getConvert(final Type type) throws Exception {
        final Convert convert = type.getAnnotation(Convert.class);
        if (null == convert || null != type.getAnnotation(Element.class)) {
            return convert;
        }
        throw new ConvertException("Element annotation required for %s", type);
    }
    private Convert getConvert(final Class cls) throws Exception {
        final Convert convert = this.getAnnotation(cls, Convert.class);
        if (null == convert || null != getAnnotation(cls, Root.class)) {
            return convert;
        }
        throw new ConvertException("Root annotation required for %s", cls);
    }
    private <T extends Annotation> T getAnnotation(final Class<?> cls, final Class<T> cls2) {
        return builder.build(cls).scan(cls2);
    }
    private Class getType(final Type type, final Value value) {
        return null != value ? value.getType() : type.type();
    }
    private Class getType(final Type type, final Object obj) {
        return null != obj ? obj.getClass() : type.type();
    }
}
