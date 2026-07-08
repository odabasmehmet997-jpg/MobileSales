package com.google.android.datatransport.runtime.util;

import android.util.SparseArray;
import com.google.android.datatransport.Priority;
import java.util.HashMap;

public final class PriorityMapping {
    private static final HashMap<Priority, Integer> PRIORITY_INT_MAP;
    private static final SparseArray<Priority> PRIORITY_MAP = new SparseArray<>();
    static {
        HashMap<Priority, Integer> map = new HashMap<>();
        PRIORITY_INT_MAP = map;
        map.put(Priority.DEFAULT, 0);
        PRIORITY_INT_MAP.put(Priority.VERY_LOW, 1);
        PRIORITY_INT_MAP.put(Priority.HIGHEST, 2);
        for (Priority priority : PRIORITY_INT_MAP.keySet()) {
            PRIORITY_MAP.append(PRIORITY_INT_MAP.get(priority).intValue(), priority);
        }
    }
    public static Priority valueOf(int i2) {
        Priority priority = PRIORITY_MAP.get(i2);
        if (priority == null) {
            throw new IllegalArgumentException("Unknown Priority for value " + i2);
        }
        return priority;
    }
    public static int toInt(Priority priority) {
        Integer num = PRIORITY_INT_MAP.get(priority);
        if (num == null) {
            throw new IllegalStateException("PriorityMapping is missing known Priority value " + priority);
        }
        return num.intValue();
    }
}
