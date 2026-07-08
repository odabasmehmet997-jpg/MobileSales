package org.checkerframework.checker.formatter.qual;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public enum ConversionCategory {
    GENERAL("bBhHsS", null),
    CHAR("cC", Character.class, Byte.class, Short.class, Integer.class),
    INT("doxX", Byte.class, Short.class, Integer.class, Long.class, BigInteger.class),
    FLOAT("eEfgGaA", Float.class, Double.class, BigDecimal.class),
    TIME("tT", Long.class, Calendar.class, Date.class),
    CHAR_AND_INT(null, Byte.class, Short.class, Integer.class),
    INT_AND_TIME(null, Long.class),
    NULL(null),
    UNUSED(null, null);
    private static final ConversionCategory[] conversionCategoriesForIntersect;
    private static final ConversionCategory[] conversionCategoriesForUnion;
    private static final ConversionCategory[] conversionCategoriesWithChar;
    public final String chars;
    public final Class<?>[] types;
    static {
        final ConversionCategory conversionCategory = CHAR;
        final ConversionCategory conversionCategory2 = INT;
        final ConversionCategory conversionCategory3 = FLOAT;
        final ConversionCategory conversionCategory4 = TIME;
        final ConversionCategory conversionCategory5 = CHAR_AND_INT;
        final ConversionCategory conversionCategory6 = INT_AND_TIME;
        final ConversionCategory conversionCategory7 = NULL;
        conversionCategoriesWithChar = new ConversionCategory[]{INT, conversionCategory, conversionCategory2, conversionCategory3, conversionCategory4};
        conversionCategoriesForIntersect = new ConversionCategory[]{conversionCategory, conversionCategory2, conversionCategory3, conversionCategory4, conversionCategory5, conversionCategory6, conversionCategory7};
        conversionCategoriesForUnion = new ConversionCategory[]{conversionCategory7, conversionCategory5, conversionCategory6, conversionCategory, conversionCategory2, conversionCategory3, conversionCategory4};
    }
    ConversionCategory(String str, Class... clsArr) {
        this.chars = str;
        if (null == clsArr) {
            this.types = clsArr;
            return;
        }
        ArrayList arrayList = new ArrayList(clsArr.length);
        for (Class cls : clsArr) {
            arrayList.add(cls);
            Class<? extends Object> unwrapPrimitive = unwrapPrimitive(cls);
            if (null != unwrapPrimitive) {
                arrayList.add(unwrapPrimitive);
            }
        }
        this.types = (Class[]) arrayList.toArray(new Class[0]);
    }
    private static Class<? extends Object> unwrapPrimitive(Class<?> cls) {
        if (Byte.class == cls) {
            return Byte.TYPE;
        }
        if (Character.class == cls) {
            return Character.TYPE;
        }
        if (Short.class == cls) {
            return Short.TYPE;
        }
        if (Integer.class == cls) {
            return Integer.TYPE;
        }
        if (Long.class == cls) {
            return Long.TYPE;
        }
        if (Float.class == cls) {
            return Float.TYPE;
        }
        if (Double.class == cls) {
            return Double.TYPE;
        }
        if (Boolean.class == cls) {
            return Boolean.TYPE;
        }
        return null;
    }
    public static ConversionCategory fromConversionChar(char c) {
        ConversionCategory[] conversionCategoryArr = conversionCategoriesWithChar;
        for (ConversionCategory conversionCategory : conversionCategoryArr) {
            if (conversionCategory.chars.contains(String.valueOf(c))) {
                return conversionCategory;
            }
        }
        throw new IllegalArgumentException("Bad conversion character " + c);
    }
    private static <E> Set<E> arrayToSet(E[] eArr) {
        return new HashSet(Arrays.asList(eArr));
    }
    public static boolean isSubsetOf(ConversionCategory conversionCategory, ConversionCategory conversionCategory2) {
        return intersect(conversionCategory, conversionCategory2) == conversionCategory;
    }
    public static ConversionCategory intersect(ConversionCategory conversionCategory, ConversionCategory conversionCategory2) {
        final ConversionCategory conversionCategory3 = UNUSED;
        if (conversionCategory3 == conversionCategory) {
            return conversionCategory2;
        }
        if (conversionCategory3 == conversionCategory2) {
            return conversionCategory;
        }
        final ConversionCategory conversionCategory4 = GENERAL;
        if (conversionCategory4 == conversionCategory) {
            return conversionCategory2;
        }
        if (conversionCategory4 == conversionCategory2) {
            return conversionCategory;
        }
        Set arrayToSet = arrayToSet(conversionCategory.types);
        arrayToSet.retainAll(arrayToSet(conversionCategory2.types));
        ConversionCategory[] conversionCategoryArr = conversionCategoriesForIntersect;
        for (ConversionCategory conversionCategory5 : conversionCategoryArr) {
            if (arrayToSet(conversionCategory5.types).equals(arrayToSet)) {
                return conversionCategory5;
            }
        }
        throw new RuntimeException();
    }
    public static ConversionCategory union(ConversionCategory conversionCategory, ConversionCategory conversionCategory2) {
        ConversionCategory conversionCategory3 = UNUSED;
        if (conversionCategory == conversionCategory3 || conversionCategory2 == conversionCategory3 || conversionCategory == (conversionCategory3 = GENERAL) || conversionCategory2 == conversionCategory3) {
            return conversionCategory3;
        }
        final ConversionCategory conversionCategory4 = CHAR_AND_INT;
        if ((conversionCategory4 == conversionCategory && INT_AND_TIME == conversionCategory2) || (INT_AND_TIME == conversionCategory && conversionCategory4 == conversionCategory2)) {
            return INT;
        }
        Set arrayToSet = arrayToSet(conversionCategory.types);
        arrayToSet.addAll(arrayToSet(conversionCategory2.types));
        ConversionCategory[] conversionCategoryArr = conversionCategoriesForUnion;
        for (ConversionCategory conversionCategory5 : conversionCategoryArr) {
            if (arrayToSet(conversionCategory5.types).equals(arrayToSet)) {
                return conversionCategory5;
            }
        }
        return GENERAL;
    }
    public boolean isAssignableFrom(Class<?> cls) {
        Class<?>[] clsArr = this.types;
        if (null == clsArr || cls == Void.TYPE) {
            return true;
        }
        for (Class<?> cls2 : clsArr) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name());
        sb.append(" conversion category");
        Class<?>[] clsArr = this.types;
        if (null == clsArr || 0 == clsArr.length) {
            return sb.toString();
        }
        StringJoiner stringJoiner = new StringJoiner(", ", "(one of: ", ")");
        for (Class<?> cls : this.types) {
            stringJoiner.add(cls.getSimpleName());
        }
        sb.append(" ");
        sb.append(stringJoiner);
        return sb.toString();
    }
}
