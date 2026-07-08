package com.fasterxml.jackson.databind.util;

import java.io.Serializable;

public class ViewMatcher implements Serializable {
    protected static final ViewMatcher EMPTY = new ViewMatcher();
    private static final long serialVersionUID = 1;
    public boolean isVisibleForView(final Class<?> cls) {
        return false;
    }
    public static ViewMatcher construct(final Class<?>[] clsArr) {
        if (null == clsArr) {
            return ViewMatcher.EMPTY;
        }
        final int length = clsArr.length;
        if (0 == length) {
            return ViewMatcher.EMPTY;
        }
        if (1 == length) {
            return new Single(clsArr[0]);
        }
        return new Multi(clsArr);
    }
    private static final class Single extends ViewMatcher {
        private static final long serialVersionUID = 1;
        private final Class<?> _view;

        public Single(final Class<?> cls) {
            _view = cls;
        }

        @Override // com.fasterxml.jackson.databind.util.ViewMatcher
        public boolean isVisibleForView(final Class<?> cls) {
            final Class<?> cls2 = _view;
            return cls == cls2 || cls2.isAssignableFrom(cls);
        }
    }
    private static final class Multi extends ViewMatcher {
        private static final long serialVersionUID = 1;
        private final Class<?>[] _views;

        public Multi(final Class<?>[] clsArr) {
            _views = clsArr;
        }

        @Override // com.fasterxml.jackson.databind.util.ViewMatcher
        public boolean isVisibleForView(final Class<?> cls) {
            final int length = _views.length;
            for (int i2 = 0; i2 < length; i2++) {
                final Class<?> cls2 = _views[i2];
                if (cls == cls2 || cls2.isAssignableFrom(cls)) {
                    return true;
                }
            }
            return false;
        }
    }
}
