package org.greenrobot.eventbus;

import org.greenrobot.eventbus.meta.SubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class SubscriberMethodFinder {
    private final boolean ignoreGeneratedIndex;
    private final boolean strictMethodVerification;
    private final List<SubscriberInfoIndex> subscriberInfoIndexes;
    private static final Map<Class<?>, List<SubscriberMethod>> METHOD_CACHE = new ConcurrentHashMap();
    private static final FindState[] FIND_STATE_POOL = new FindState[4];
    SubscriberMethodFinder(final List<SubscriberInfoIndex> list, final boolean z, final boolean z2) {
        subscriberInfoIndexes = list;
        strictMethodVerification = z;
        ignoreGeneratedIndex = z2;
    }
    List<SubscriberMethod> findSubscriberMethods(final Class<?> cls) {
        final List<SubscriberMethod> findUsingInfo;
        final Map<Class<?>, List<SubscriberMethod>> map = SubscriberMethodFinder.METHOD_CACHE;
        final List<SubscriberMethod> list = map.get(cls);
        if (null != list) {
            return list;
        }
        if (ignoreGeneratedIndex) {
            findUsingInfo = this.findUsingReflection(cls);
        } else {
            findUsingInfo = this.findUsingInfo(cls);
        }
        if (findUsingInfo.isEmpty()) {
            throw new EventBusException("Subscriber " + cls + " and its super classes have no public methods with the @Subscribe annotation");
        }
        map.put(cls, findUsingInfo);
        return findUsingInfo;
    }
    private List<SubscriberMethod> findUsingInfo(final Class<?> cls) {
        final FindState prepareFindState = this.prepareFindState();
        prepareFindState.initForSubscriber(cls);
        while (null != prepareFindState.clazz) {
            this.getSubscriberInfo(prepareFindState);
            this.findUsingReflectionInSingleClass(prepareFindState);
            prepareFindState.moveToSuperclass();
        }
        return this.getMethodsAndRelease(prepareFindState);
    }
    private List<SubscriberMethod> getMethodsAndRelease(final FindState findState) {
        final ArrayList arrayList = new ArrayList(findState.subscriberMethods);
        findState.recycle();
        synchronized (SubscriberMethodFinder.FIND_STATE_POOL) {
            int i2 = 0;
            while (true) {
                if (4 <= i2) {
                    break;
                }
                try {
                    final FindState[] findStateArr = SubscriberMethodFinder.FIND_STATE_POOL;
                    if (null == findStateArr[i2]) {
                        findStateArr[i2] = findState;
                        break;
                    }
                    i2++;
                } catch (final Throwable th) {
                    throw th;
                }
            }
        }
        return arrayList;
    }
    private FindState prepareFindState() {
        synchronized (SubscriberMethodFinder.FIND_STATE_POOL) {
            for (int i2 = 0; 4 > i2; i2++) {
                try {
                    final FindState[] findStateArr = SubscriberMethodFinder.FIND_STATE_POOL;
                    final FindState findState = findStateArr[i2];
                    if (null != findState) {
                        findStateArr[i2] = null;
                        return findState;
                    }
                } catch (final Throwable th) {
                    throw th;
                }
            }
            return new FindState();
        }
    }
    private SubscriberInfo getSubscriberInfo(final FindState findState) {
        findState.getClass();
        final List<SubscriberInfoIndex> list = subscriberInfoIndexes;
        if (null == list) {
            return null;
        }
        final Iterator<SubscriberInfoIndex> it = list.iterator();
        while (it.hasNext()) {
            it.next().getSubscriberInfo(findState.clazz);
        }
        return null;
    }
    private List<SubscriberMethod> findUsingReflection(final Class<?> cls) {
        final FindState prepareFindState = this.prepareFindState();
        prepareFindState.initForSubscriber(cls);
        while (null != prepareFindState.clazz) {
            this.findUsingReflectionInSingleClass(prepareFindState);
            prepareFindState.moveToSuperclass();
        }
        return this.getMethodsAndRelease(prepareFindState);
    }
    private void findUsingReflectionInSingleClass(final FindState findState) {
        Method[] methods;
        try {
            try {
                methods = findState.clazz.getDeclaredMethods();
            } catch (final LinkageError e2) {
                final String str = "Could not inspect methods of " + findState.clazz.getName();
                throw new EventBusException(ignoreGeneratedIndex ? str + ". Please consider using EventBus annotation processor to avoid reflection." : str + ". Please make this class visible to EventBus annotation processor to avoid reflection.", e2);
            }
        } catch (final Throwable unused) {
            methods = findState.clazz.getMethods();
            findState.skipSuperClasses = true;
        }
        for (final Method method : methods) {
            final int modifiers = method.getModifiers();
            if (0 != (modifiers & 1) && 0 == (modifiers & 5192)) {
                final Class<?>[] parameterTypes = method.getParameterTypes();
                if (1 == parameterTypes.length) {
                    final Subscribe subscribe = method.getAnnotation(Subscribe.class);
                    if (null != subscribe) {
                        final Class<?> cls = parameterTypes[0];
                        if (findState.checkAdd(method, cls)) {
                            findState.subscriberMethods.add(new SubscriberMethod(method, cls, subscribe.threadMode(), subscribe.priority(), subscribe.sticky()));
                        }
                    }
                } else if (strictMethodVerification && method.isAnnotationPresent(Subscribe.class)) {
                    throw new EventBusException("@Subscribe method " + (method.getDeclaringClass().getName() + "." + method.getName()) + "must have exactly 1 parameter but has " + parameterTypes.length);
                }
            } else if (strictMethodVerification && method.isAnnotationPresent(Subscribe.class)) {
                throw new EventBusException((method.getDeclaringClass().getName() + "." + method.getName()) + " is a illegal @Subscribe method: must be public, non-static, and non-abstract");
            }
        }
    }
    static class FindState {
        Class<?> clazz;
        boolean skipSuperClasses;
        Class<?> subscriberClass;
        final List<SubscriberMethod> subscriberMethods = new ArrayList();
        final Map<Class, Object> anyMethodByEventType = new HashMap();
        final Map<String, Class> subscriberClassByMethodKey = new HashMap();
        final StringBuilder methodKeyBuilder = new StringBuilder(128);
        FindState() {
        }
        void initForSubscriber(final Class<?> cls) {
            clazz = cls;
            subscriberClass = cls;
            skipSuperClasses = false;
        }
        void recycle() {
            subscriberMethods.clear();
            anyMethodByEventType.clear();
            subscriberClassByMethodKey.clear();
            methodKeyBuilder.setLength(0);
            subscriberClass = null;
            clazz = null;
            skipSuperClasses = false;
        }
        boolean checkAdd(final Method method, final Class<?> cls) {
            final Object put = anyMethodByEventType.put(cls, method);
            if (null == put) {
                return true;
            }
            if (put instanceof Method) {
                if (!this.checkAddWithMethodSignature((Method) put, cls)) {
                    throw new IllegalStateException();
                }
                anyMethodByEventType.put(cls, this);
            }
            return this.checkAddWithMethodSignature(method, cls);
        }
        private boolean checkAddWithMethodSignature(final Method method, final Class<?> cls) {
            methodKeyBuilder.setLength(0);
            methodKeyBuilder.append(method.getName());
            final StringBuilder sb = methodKeyBuilder;
            sb.append('>');
            sb.append(cls.getName());
            final String sb2 = methodKeyBuilder.toString();
            final Class<?> declaringClass = method.getDeclaringClass();
            final Class put = subscriberClassByMethodKey.put(sb2, declaringClass);
            if (null == put || put.isAssignableFrom(declaringClass)) {
                return true;
            }
            subscriberClassByMethodKey.put(sb2, put);
            return false;
        }
        void moveToSuperclass() {
            if (skipSuperClasses) {
                clazz = null;
                return;
            }
            final Class<? super Object> superclass = (Class<? super Object>) clazz.getSuperclass();
            clazz = superclass;
            final String name = superclass.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.") || name.startsWith("androidx.")) {
                clazz = null;
            }
        }
    }
}
