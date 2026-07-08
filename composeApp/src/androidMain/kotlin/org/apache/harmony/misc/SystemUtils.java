package org.apache.harmony.misc;

public class SystemUtils {
    public static final int ARC_IA32 = 1;
    public static final int ARC_IA64 = 2;
    public static final int ARC_UNKNOWN = -1;
    public static final int OS_LINUX = 2;
    public static final int OS_UNKNOWN = -1;
    public static final int OS_WINDOWS = 1;
    private static int arc;
    private static int os;
    public static int getOS() {
        if (0 == os) {
            final String substring = System.getProperty("os.name").substring(0, 3);
            if (0 == substring.compareToIgnoreCase("win")) {
                SystemUtils.os = 1;
            } else if (0 == substring.compareToIgnoreCase("lin")) {
                SystemUtils.os = 2;
            } else {
                SystemUtils.os = -1;
            }
        }
        return SystemUtils.os;
    }
}
