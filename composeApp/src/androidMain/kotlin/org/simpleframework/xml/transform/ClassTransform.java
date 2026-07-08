package org.simpleframework.xml.transform;


class ClassTransform implements Transform<Class> {
    private static final String BOOLEAN = "boolean";
    private static final String BYTE = "byte";
    private static final String CHARACTER = "char";
    private static final String DOUBLE = "double";
    private static final String FLOAT = "float";
    private static final String INTEGER = "int";
    private static final String LONG = "long";
    private static final String SHORT = "short";
    private static final String VOID = "void";

    ClassTransform() {
    }

    
    @Override // org.simpleframework.xml.transform.Transform
    public Class read(final String str) throws Exception {
        final Class readPrimitive = this.readPrimitive(str);
        if (null != readPrimitive) {
            return readPrimitive;
        }
        ClassLoader classLoader = ClassTransform.getClassLoader();
        if (null == classLoader) {
            classLoader = this.getCallerClassLoader();
        }
        return classLoader.loadClass(str);
    }

    private Class readPrimitive(final String str) throws Exception {
        if (str.equals(ClassTransform.BYTE)) {
            return Byte.TYPE;
        }
        if (str.equals(ClassTransform.SHORT)) {
            return Short.TYPE;
        }
        if (str.equals(ClassTransform.INTEGER)) {
            return Integer.TYPE;
        }
        if (str.equals(ClassTransform.LONG)) {
            return Long.TYPE;
        }
        if (str.equals(ClassTransform.CHARACTER)) {
            return Character.TYPE;
        }
        if ("float".equals(str)) {
            return Float.TYPE;
        }
        if (str.equals(ClassTransform.DOUBLE)) {
            return Double.TYPE;
        }
        if ("boolean".equals(str)) {
            return Boolean.TYPE;
        }
        if (str.equals(ClassTransform.VOID)) {
            return Void.TYPE;
        }
        return null;
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final Class cls) throws Exception {
        return cls.getName();
    }

    private ClassLoader getCallerClassLoader() {
        return this.getClass().getClassLoader();
    }

    private static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
