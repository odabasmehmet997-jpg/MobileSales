package org.simpleframework.xml.strategy;


class Loader {
    Loader() {
    }

    public Class load(final String str) throws Exception {
        ClassLoader classLoader = Loader.getClassLoader();
        if (null == classLoader) {
            classLoader = Loader.getCallerClassLoader();
        }
        return classLoader.loadClass(str);
    }

    private static ClassLoader getCallerClassLoader() throws Exception {
        return Loader.class.getClassLoader();
    }

    private static ClassLoader getClassLoader() throws Exception {
        return Thread.currentThread().getContextClassLoader();
    }
}
