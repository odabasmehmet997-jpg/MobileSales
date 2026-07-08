package javax.activation;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Enumeration;

enum SecuritySupport {
    ;

    public static ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    return Thread.currentThread().getContextClassLoader();
                } catch (SecurityException unused) {
                    return null;
                }
            }
        });
    }

    public static InputStream getResourceAsStream(final Class cls, final String str) throws IOException {
        try {
            return (InputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws IOException {
                    return cls.getResourceAsStream(str);
                }
            });
        } catch (PrivilegedActionException e2) {
            throw ((IOException) e2.getException());
        }
    }

    public static URL[] getResources(final ClassLoader classLoader, final String str) {
        return (URL[]) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    ArrayList arrayList = new ArrayList();
                    Enumeration<URL> resources = classLoader.getResources(str);
                    while (true) {
                        if (null == resources) {
                            break;
                        } else if (!resources.hasMoreElements()) {
                            break;
                        } else {
                            URL nextElement = resources.nextElement();
                            if (null != nextElement) {
                                arrayList.add(nextElement);
                            }
                        }
                    }
                    if (0 < arrayList.size()) {
                        return arrayList.toArray(new URL[arrayList.size()]);
                    }
                    return null;
                } catch (IOException | SecurityException unused) {
                    return null;
                }
            }
        });
    }

    public static URL[] getSystemResources(final String str) {
        return (URL[]) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    ArrayList arrayList = new ArrayList();
                    Enumeration<URL> systemResources = ClassLoader.getSystemResources(str);
                    while (true) {
                        if (null == systemResources) {
                            break;
                        } else if (!systemResources.hasMoreElements()) {
                            break;
                        } else {
                            URL nextElement = systemResources.nextElement();
                            if (null != nextElement) {
                                arrayList.add(nextElement);
                            }
                        }
                    }
                    if (0 < arrayList.size()) {
                        return arrayList.toArray(new URL[arrayList.size()]);
                    }
                    return null;
                } catch (IOException | SecurityException unused) {
                    return null;
                }
            }
        });
    }

    public static InputStream openStream(final URL url) throws IOException {
        try {
            return (InputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws IOException {
                    return url.openStream();
                }
            });
        } catch (PrivilegedActionException e2) {
            throw ((IOException) e2.getException());
        }
    }
}
