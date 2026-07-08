package javax.activation;

import java.io.File;

public abstract class FileTypeMap {
    private static FileTypeMap defaultMap;
    public abstract String getContentType(File file);
    public abstract String getContentType(String str);
    public static void setDefaultFileTypeMap(final FileTypeMap fileTypeMap) {
        final SecurityManager securityManager = System.getSecurityManager();
        if (null != securityManager) {
            try {
                securityManager.checkSetFactory();
            } catch (final SecurityException e2) {
                if (FileTypeMap.class.getClassLoader() != fileTypeMap.getClass().getClassLoader()) {
                    throw e2;
                }
            }
        }
        FileTypeMap.defaultMap = fileTypeMap;
    }
    public static FileTypeMap getDefaultFileTypeMap() {
        if (null == defaultMap) {
            FileTypeMap.defaultMap = new MimetypesFileTypeMap();
        }
        return FileTypeMap.defaultMap;
    }
}
