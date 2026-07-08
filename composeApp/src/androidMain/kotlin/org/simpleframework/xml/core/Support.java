package org.simpleframework.xml.core;

import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.filter.Filter;
import org.simpleframework.xml.filter.PlatformFilter;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;
import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;
import org.simpleframework.xml.transform.Transformer;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class Support implements Filter {
    private final DetailExtractor defaults;
    private final DetailExtractor details;
    private final Filter filter;
    private final Format format;
    private final InstanceFactory instances;
    private final LabelExtractor labels;
    private final Matcher matcher;
    private final ScannerFactory scanners;
    private final Transformer transform;
    public Support() {
        this(new PlatformFilter());
    }
    public Support(final Filter filter) {
        this(filter, new EmptyMatcher());
    }
    public Support(final Filter filter, final Matcher matcher) {
        this(filter, matcher, new Format());
    }
    public Support(final Filter filter, final Matcher matcher, final Format format) {
        defaults = new DetailExtractor(this, DefaultType.FIELD);
        transform = new Transformer(matcher);
        scanners = new ScannerFactory(this);
        details = new DetailExtractor(this);
        labels = new LabelExtractor(format);
        instances = new InstanceFactory();
        this.matcher = matcher;
        this.filter = filter;
        this.format = format;
    }
    public String replace(final String str) {
        return filter.replace(str);
    }
    public Style getStyle() {
        return format.style();
    }
    public Format getFormat() {
        return format;
    }
    public Instance getInstance(final Value value) {
        return instances.getInstance(value);
    }
    public Instance getInstance(final Class cls) {
        return instances.getInstance(cls);
    }
    public Transform getTransform(final Class cls) throws Exception {
        return matcher.match(cls);
    }
    public Label getLabel(final Contact contact, final Annotation annotation) throws Exception {
        return labels.getLabel(contact, annotation);
    }
    public List<Label> getLabels(final Contact contact, final Annotation annotation) throws Exception {
        return labels.getList(contact, annotation);
    }
    public Detail getDetail(final Class cls) {
        return this.getDetail(cls, null);
    }
    public Detail getDetail(final Class cls, final DefaultType defaultType) {
        if (null != defaultType) {
            return defaults.getDetail(cls);
        }
        return details.getDetail(cls);
    }
    public ContactList getFields(final Class cls) throws Exception {
        return this.getFields(cls, null);
    }
    public ContactList getFields(final Class cls, final DefaultType defaultType) throws Exception {
        if (null != defaultType) {
            return defaults.getFields(cls);
        }
        return details.getFields(cls);
    }
    public ContactList getMethods(final Class cls) throws Exception {
        return this.getMethods(cls, null);
    }
    public ContactList getMethods(final Class cls, final DefaultType defaultType) throws Exception {
        if (null != defaultType) {
            return defaults.getMethods(cls);
        }
        return details.getMethods(cls);
    }
    public Scanner getScanner(final Class cls) throws Exception {
        return scanners.getInstance(cls);
    }
    public Object read(final String str, final Class cls) throws Exception {
        return transform.read(str, cls);
    }
    public String write(final Object obj, final Class cls) throws Exception {
        return transform.write(obj, cls);
    }
    public boolean valid(final Class cls) throws Exception {
        return transform.valid(cls);
    }
    public String getName(final Class cls) throws Exception {
        final String name = this.getScanner(cls).getName();
        return null != name ? name : this.getClassName(cls);
    }
    private String getClassName(Class cls) throws Exception {
        if (cls.isArray()) {
            cls = cls.getComponentType();
        }
        final String simpleName = cls.getSimpleName();
        return cls.isPrimitive() ? simpleName : Reflector.getName(simpleName);
    }
    public boolean isPrimitive(final Class cls) throws Exception {
        if (String.class == cls || Float.class == cls || Double.class == cls || Long.class == cls || Integer.class == cls || Boolean.class == cls || cls.isEnum() || cls.isPrimitive()) {
            return true;
        }
        return transform.valid(cls);
    }
    public boolean isContainer(final Class cls) {
        if (Collection.class.isAssignableFrom(cls) || Map.class.isAssignableFrom(cls)) {
            return true;
        }
        return cls.isArray();
    }
    public static boolean isFloat(final Class cls) throws Exception {
        return Double.class == cls || Float.class == cls || cls == Float.TYPE || cls == Double.TYPE;
    }
    public static boolean isAssignable(Class cls, final Class cls2) {
        if (cls.isPrimitive()) {
            cls = Support.getPrimitive(cls);
        }
        final boolean isPrimitive = cls2.isPrimitive();
        Class cls3 = cls2;
        if (isPrimitive) {
            cls3 = Support.getPrimitive(cls2);
        }
        return cls3.isAssignableFrom(cls);
    }
    public static Class getPrimitive(final Class cls) {
        if (cls == Double.TYPE) {
            return Double.class;
        }
        if (cls == Float.TYPE) {
            return Float.class;
        }
        if (cls == Integer.TYPE) {
            return Integer.class;
        }
        if (cls == Long.TYPE) {
            return Long.class;
        }
        if (cls == Boolean.TYPE) {
            return Boolean.class;
        }
        if (cls == Character.TYPE) {
            return Character.class;
        }
        if (cls == Short.TYPE) {
            return Short.class;
        }
        return cls == Byte.TYPE ? Byte.class : cls;
    }
}
