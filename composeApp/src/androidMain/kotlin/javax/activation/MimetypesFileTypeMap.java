package javax.activation;

import sun.activation.registries.LogSupport;
import sun.activation.registries.MimeTypeFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class MimetypesFileTypeMap extends FileTypeMap {
    private static final int PROG = 0;
    private static MimeTypeFile defDB;
    private static final String defaultType = "application/octet-stream";
    private final MimeTypeFile[] DB;

    public MimetypesFileTypeMap() {
        Vector vector = new Vector(5);
        vector.addElement(null);
        LogSupport.log("MimetypesFileTypeMap: load HOME");
        try {
            String property = System.getProperty("user.home");
            if (null != property) {
                MimeTypeFile loadFile = loadFile(property + File.separator + ".mime.types");
                if (null != loadFile) {
                    vector.addElement(loadFile);
                }
            }
        } catch (SecurityException unused) {
        }
        LogSupport.log("MimetypesFileTypeMap: load SYS");
        try {
            String str = File.separator;
            final String sb = System.getProperty("java.home") + str +
                    "lib" +
                    str +
                    "mime.types";
            MimeTypeFile loadFile2 = loadFile(sb);
            if (null != loadFile2) {
                vector.addElement(loadFile2);
            }
        } catch (SecurityException unused2) {
        }
        LogSupport.log("MimetypesFileTypeMap: load JAR");
        loadAllResources(vector, "mime.types");
        LogSupport.log("MimetypesFileTypeMap: load DEF");
        synchronized (MimetypesFileTypeMap.class) {
            try {
                if (null == MimetypesFileTypeMap.defDB) {
                    defDB = loadResource("/mimetypes.default");
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        MimeTypeFile mimeTypeFile = defDB;
        if (null != mimeTypeFile) {
            vector.addElement(mimeTypeFile);
        }
        MimeTypeFile[] mimeTypeFileArr = new MimeTypeFile[vector.size()];
        this.DB = mimeTypeFileArr;
        vector.copyInto(mimeTypeFileArr);
    }
    private MimeTypeFile loadResource(String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "MimetypesFileTypeMap: can't load "
            r1 = 0
            java.lang.Class r2 = r6.getClass()     // Catch:{ IOException -> 0x0057, SecurityException -> 0x0054, all -> 0x0052 }
            java.io.InputStream r2 = javax.activation.SecuritySupport.getResourceAsStream(r2, r7)     // Catch:{ IOException -> 0x0057, SecurityException -> 0x0054, all -> 0x0052 }
            if (r2 == 0) goto L_0x0035
            com.sun.activation.registries.MimeTypeFile r3 = new com.sun.activation.registries.MimeTypeFile     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            r3.<init>((java.io.InputStream) r2)     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            boolean r4 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            if (r4 == 0) goto L_0x0031
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            java.lang.String r5 = "MimetypesFileTypeMap: successfully loaded mime types file: "
            r4.<init>(r5)     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            r4.append(r7)     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            com.sun.activation.registries.LogSupport.log(r4)     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            goto L_0x0031
        L_0x002a:
            r7 = move-exception
            r1 = r2
            goto L_0x008b
        L_0x002d:
            r3 = move-exception
            goto L_0x005a
        L_0x002f:
            r3 = move-exception
            goto L_0x0072
        L_0x0031:
            r2.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            return r3
        L_0x0035:
            boolean r3 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            if (r3 == 0) goto L_0x004c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            java.lang.String r4 = "MimetypesFileTypeMap: not loading mime types file: "
            r3.<init>(r4)     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            r3.append(r7)     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            com.sun.activation.registries.LogSupport.log(r3)     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
        L_0x004c:
            if (r2 == 0) goto L_0x008a
        L_0x004e:
            r2.close()     // Catch:{ IOException -> 0x008a }
            goto L_0x008a
        L_0x0052:
            r7 = move-exception
            goto L_0x008b
        L_0x0054:
            r3 = move-exception
            r2 = r1
            goto L_0x005a
        L_0x0057:
            r3 = move-exception
            r2 = r1
            goto L_0x0072
        L_0x005a:
            boolean r4 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch:{ all -> 0x002a }
            if (r4 == 0) goto L_0x006f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x002a }
            r4.<init>(r0)     // Catch:{ all -> 0x002a }
            r4.append(r7)     // Catch:{ all -> 0x002a }
            java.lang.String r7 = r4.toString()     // Catch:{ all -> 0x002a }
            com.sun.activation.registries.LogSupport.log(r7, r3)     // Catch:{ all -> 0x002a }
        L_0x006f:
            if (r2 == 0) goto L_0x008a
            goto L_0x004e
        L_0x0072:
            boolean r4 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch:{ all -> 0x002a }
            if (r4 == 0) goto L_0x0087
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x002a }
            r4.<init>(r0)     // Catch:{ all -> 0x002a }
            r4.append(r7)     // Catch:{ all -> 0x002a }
            java.lang.String r7 = r4.toString()     // Catch:{ all -> 0x002a }
            com.sun.activation.registries.LogSupport.log(r7, r3)     // Catch:{ all -> 0x002a }
        L_0x0087:
            if (r2 == 0) goto L_0x008a
            goto L_0x004e
        L_0x008a:
            return r1
        L_0x008b:
            if (r1 == 0) goto L_0x0090
            r1.close()     // Catch:{ IOException -> 0x0090 }
        L_0x0090:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.activation.MimetypesFileTypeMap.loadResource(java.lang.String):com.sun.activation.registries.MimeTypeFile");
    }
    private void loadAllResources(Vector r9, String r10) {
        /*
            r8 = this;
            java.lang.String r0 = "MimetypesFileTypeMap: can't load "
            r1 = 0
            java.lang.ClassLoader r2 = javax.activation.SecuritySupport.getContextClassLoader()     // Catch:{ Exception -> 0x0012 }
            if (r2 != 0) goto L_0x0015
            java.lang.Class r2 = r8.getClass()     // Catch:{ Exception -> 0x0012 }
            java.lang.ClassLoader r2 = r2.getClassLoader()     // Catch:{ Exception -> 0x0012 }
            goto L_0x0015
        L_0x0012:
            r2 = move-exception
            goto L_0x00d7
        L_0x0015:
            if (r2 == 0) goto L_0x001c
            java.net.URL[] r2 = javax.activation.SecuritySupport.getResources(r2, r10)     // Catch:{ Exception -> 0x0012 }
            goto L_0x0020
        L_0x001c:
            java.net.URL[] r2 = javax.activation.SecuritySupport.getSystemResources(r10)     // Catch:{ Exception -> 0x0012 }
        L_0x0020:
            if (r2 == 0) goto L_0x00ec
            boolean r3 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch:{ Exception -> 0x0012 }
            if (r3 == 0) goto L_0x002d
            java.lang.String r3 = "MimetypesFileTypeMap: getResources"
            com.sun.activation.registries.LogSupport.log(r3)     // Catch:{ Exception -> 0x0012 }
        L_0x002d:
            r3 = r1
        L_0x002e:
            int r4 = r2.length     // Catch:{ Exception -> 0x004e }
            if (r1 < r4) goto L_0x0034
            r1 = r3
            goto L_0x00ec
        L_0x0034:
            r4 = r2[r1]     // Catch:{ Exception -> 0x004e }
            boolean r5 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch:{ Exception -> 0x004e }
            if (r5 == 0) goto L_0x0052
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004e }
            java.lang.String r6 = "MimetypesFileTypeMap: URL "
            r5.<init>(r6)     // Catch:{ Exception -> 0x004e }
            r5.append(r4)     // Catch:{ Exception -> 0x004e }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x004e }
            com.sun.activation.registries.LogSupport.log(r5)     // Catch:{ Exception -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r2 = move-exception
            r1 = r3
            goto L_0x00d7
        L_0x0052:
            r5 = 0
            java.io.InputStream r5 = javax.activation.SecuritySupport.openStream(r4)     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            if (r5 == 0) goto L_0x0080
            com.sun.activation.registries.MimeTypeFile r6 = new com.sun.activation.registries.MimeTypeFile     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            r6.<init>((java.io.InputStream) r5)     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            r9.addElement(r6)     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            r3 = 1
            boolean r6 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            if (r6 == 0) goto L_0x0097
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            java.lang.String r7 = "MimetypesFileTypeMap: successfully loaded mime types from URL: "
            r6.<init>(r7)     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            r6.append(r4)     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            com.sun.activation.registries.LogSupport.log(r6)     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            goto L_0x0097
        L_0x007a:
            r1 = move-exception
            goto L_0x00d1
        L_0x007c:
            r6 = move-exception
            goto L_0x009d
        L_0x007e:
            r6 = move-exception
            goto L_0x00b5
        L_0x0080:
            boolean r6 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            if (r6 == 0) goto L_0x0097
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            java.lang.String r7 = "MimetypesFileTypeMap: not loading mime types from URL: "
            r6.<init>(r7)     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            r6.append(r4)     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            com.sun.activation.registries.LogSupport.log(r6)     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
        L_0x0097:
            if (r5 == 0) goto L_0x00cd
        L_0x0099:
            r5.close()     // Catch:{ IOException -> 0x00cd }
            goto L_0x00cd
        L_0x009d:
            boolean r7 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch:{ all -> 0x007a }
            if (r7 == 0) goto L_0x00b2
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x007a }
            r7.<init>(r0)     // Catch:{ all -> 0x007a }
            r7.append(r4)     // Catch:{ all -> 0x007a }
            java.lang.String r4 = r7.toString()     // Catch:{ all -> 0x007a }
            com.sun.activation.registries.LogSupport.log(r4, r6)     // Catch:{ all -> 0x007a }
        L_0x00b2:
            if (r5 == 0) goto L_0x00cd
            goto L_0x0099
        L_0x00b5:
            boolean r7 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch:{ all -> 0x007a }
            if (r7 == 0) goto L_0x00ca
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x007a }
            r7.<init>(r0)     // Catch:{ all -> 0x007a }
            r7.append(r4)     // Catch:{ all -> 0x007a }
            java.lang.String r4 = r7.toString()     // Catch:{ all -> 0x007a }
            com.sun.activation.registries.LogSupport.log(r4, r6)     // Catch:{ all -> 0x007a }
        L_0x00ca:
            if (r5 == 0) goto L_0x00cd
            goto L_0x0099
        L_0x00cd:
            int r1 = r1 + 1
            goto L_0x002e
        L_0x00d1:
            if (r5 == 0) goto L_0x00d6
            r5.close()     // Catch:{ IOException -> 0x00d6 }
        L_0x00d6:
            throw r1     // Catch:{ Exception -> 0x004e }
        L_0x00d7:
            boolean r3 = com.sun.activation.registries.LogSupport.isLoggable()
            if (r3 == 0) goto L_0x00ec
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            r3.append(r10)
            java.lang.String r0 = r3.toString()
            com.sun.activation.registries.LogSupport.log(r0, r2)
        L_0x00ec:
            if (r1 != 0) goto L_0x010a
            java.lang.String r0 = "MimetypesFileTypeMap: !anyLoaded"
            com.sun.activation.registries.LogSupport.log(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "/"
            r0.<init>(r1)
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            com.sun.activation.registries.MimeTypeFile r10 = r8.loadResource(r10)
            if (r10 == 0) goto L_0x010a
            r9.addElement(r10)
        L_0x010a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.activation.MimetypesFileTypeMap.loadAllResources(java.util.Vector, java.lang.String):void");
    }
    private MimeTypeFile loadFile(String str) {
        try {
            return new MimeTypeFile(str);
        } catch (IOException unused) {
            return null;
        }
    }
    public MimetypesFileTypeMap(String str) throws IOException {
        this();
        this.DB[0] = new MimeTypeFile(str);
    }
    public MimetypesFileTypeMap(InputStream inputStream) {
        this();
        try {
            this.DB[0] = new MimeTypeFile(inputStream);
        } catch (IOException unused) {
        }
    }
    public synchronized void addMimeTypes(String str) {
        try {
            MimeTypeFile[] mimeTypeFileArr = this.DB;
            if (null == mimeTypeFileArr[0]) {
                mimeTypeFileArr[0] = new MimeTypeFile();
            }
            this.DB[0].appendToRegistry(str);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    public String getContentType(File file) {
        return getContentType(file.getName());
    }
    public synchronized String getContentType(String str) {
        int lastIndexOf = str.lastIndexOf('.');
        if (0 > lastIndexOf) {
            return defaultType;
        }
        String substring = str.substring(lastIndexOf + 1);
        if (0 == substring.length()) {
            return defaultType;
        }
        int i2 = 0;
        while (true) {
            MimeTypeFile[] mimeTypeFileArr = this.DB;
            if (i2 >= mimeTypeFileArr.length) {
                return defaultType;
            }
            MimeTypeFile mimeTypeFile = mimeTypeFileArr[i2];
            if (null != mimeTypeFile) {
                String mIMETypeString = mimeTypeFile.getMIMETypeString(substring);
                if (null != mIMETypeString) {
                    return mIMETypeString;
                }
            }
            i2++;
        }
    }
}
