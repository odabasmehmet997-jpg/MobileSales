package com.fasterxml.jackson.databind.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum IgnorePropertiesUtil {
    ;

    public static boolean shouldIgnore(final Object obj, final Collection<String> collection, final Collection<String> collection2) {
        if (null == collection && null == collection2) {
            return false;
        }
        if (null == collection2) {
            return collection.contains(obj);
        }
        if (null == collection) {
            return !collection2.contains(obj);
        }
        return !collection2.contains(obj) || collection.contains(obj);
    }

    public static Checker buildCheckerIfNeeded(final Set<String> set, final Set<String> set2) {
        if (null == set2 && (null == set || set.isEmpty())) {
            return null;
        }
        return Checker.construct(set, set2);
    }

    public static Set<String> combineNamesToInclude(final Set<String> set, final Set<String> set2) {
        if (null == set) {
            return set2;
        }
        if (null == set2) {
            return set;
        }
        final HashSet hashSet = new HashSet();
        for (final String str : set2) {
            if (set.contains(str)) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    public static final class Checker implements Serializable {
        private static final long serialVersionUID = 1;
        private final Set<String> _toIgnore;
        private final Set<String> _toInclude;

        private Checker(final Set<String> set, final Set<String> set2) {
            _toIgnore = null == set ? Collections.emptySet() : set;
            _toInclude = set2;
        }

        public static Checker construct(final Set<String> set, final Set<String> set2) {
            return new Checker(set, set2);
        }

        public boolean shouldIgnore(final Object obj) {
            final Set<String> set = _toInclude;
            return !(null == set || set.contains(obj)) || _toIgnore.contains(obj);
        }
    }
}
