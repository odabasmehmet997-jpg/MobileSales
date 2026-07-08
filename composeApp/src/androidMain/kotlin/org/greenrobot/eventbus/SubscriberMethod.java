package org.greenrobot.eventbus;

import java.lang.reflect.Method;

public class SubscriberMethod {
    final Class<?> eventType;
    final Method method;
    String methodString;
    final int priority;
    final boolean sticky;
    final ThreadMode threadMode;
    public SubscriberMethod(final Method method, final Class<?> cls, final ThreadMode threadMode, final int i2, final boolean z) {
        this.method = method;
        this.threadMode = threadMode;
        eventType = cls;
        priority = i2;
        sticky = z;
    }
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SubscriberMethod subscriberMethod)) {
            return false;
        }
        this.checkMethodString();
        subscriberMethod.checkMethodString();
        return methodString.equals(subscriberMethod.methodString);
    }
    private synchronized void checkMethodString() {
        if (null == this.methodString) {
            String sb = method.getDeclaringClass().getName() +
                    '#' +
                    method.getName() +
                    '(' +
                    eventType.getName();
            methodString = sb;
        }
    }
    public int hashCode() {
        return method.hashCode();
    }
}
