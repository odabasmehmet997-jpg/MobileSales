package org.simpleframework.xml.core;

import java.lang.reflect.Method;
import java.util.Map;


class Function {
    private final boolean contextual;
    private final Method method;

    public Function(final Method method) {
        this(method, false);
    }

    public Function(final Method method, final boolean z) {
        contextual = z;
        this.method = method;
    }

    public Object call(final Context context, final Object obj) throws Exception {
        if (null == obj) {
            return null;
        }
        final Map map = context.getSession().getMap();
        if (contextual) {
            return method.invoke(obj, map);
        }
        return method.invoke(obj, null);
    }
}
