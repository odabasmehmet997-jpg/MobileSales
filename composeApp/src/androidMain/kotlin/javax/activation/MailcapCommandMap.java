package javax.activation;

import sun.activation.registries.LogSupport;
import sun.activation.registries.MailcapFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MailcapCommandMap extends CommandMap {
    private static final int PROG = 0;
    private static MailcapFile defDB;
    private MailcapFile[] DB;
    public MailcapCommandMap() {
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(null);
        LogSupport.log("MailcapCommandMap: load HOME");
        try {
            String property = System.getProperty("user.home");
            if (null != property) {
                MailcapFile loadFile = loadFile(property + File.separator + ".mailcap");
                if (null != loadFile) {
                    arrayList.add(loadFile);
                }
            }
        } catch (SecurityException unused) {
        }
        LogSupport.log("MailcapCommandMap: load SYS");
        try {
            String str = File.separator;
            final String sb = System.getProperty("java.home") + str +
                    "lib" +
                    str +
                    "mailcap";
            MailcapFile loadFile2 = loadFile(sb);
            if (null != loadFile2) {
                arrayList.add(loadFile2);
            }
        } catch (SecurityException unused2) {
        }
        LogSupport.log("MailcapCommandMap: load JAR");
        loadAllResources(arrayList, "mailcap");
        LogSupport.log("MailcapCommandMap: load DEF");
        synchronized (MailcapCommandMap.class) {
            try {
                if (null == MailcapCommandMap.defDB) {
                    defDB = loadResource("mailcap.default");
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        MailcapFile mailcapFile = defDB;
        if (null != mailcapFile) {
            arrayList.add(mailcapFile);
        }
        MailcapFile[] mailcapFileArr = new MailcapFile[arrayList.size()];
        this.DB = mailcapFileArr;
        this.DB = (MailcapFile[]) arrayList.toArray(mailcapFileArr);
    }
    private MailcapFile loadResource(String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "MailcapCommandMap: can't load "
            r1 = 0
            java.lang.Class r2 = r6.getClass()     // Catch:{ IOException -> 0x0057, SecurityException -> 0x0054, all -> 0x0052 }
            java.io.InputStream r2 = javax.activation.SecuritySupport.getResourceAsStream(r2, r7)     // Catch:{ IOException -> 0x0057, SecurityException -> 0x0054, all -> 0x0052 }
            if (r2 == 0) goto L_0x0035
            com.sun.activation.registries.MailcapFile r3 = new com.sun.activation.registries.MailcapFile     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            r3.<init>((java.io.InputStream) r2)     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            boolean r4 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            if (r4 == 0) goto L_0x0031
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x002f, SecurityException -> 0x002d }
            java.lang.String r5 = "MailcapCommandMap: successfully loaded mailcap file: "
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
            java.lang.String r4 = "MailcapCommandMap: not loading mailcap file: "
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
        throw new UnsupportedOperationException("Method not decompiled: javax.activation.MailcapCommandMap.loadResource(java.lang.String):com.sun.activation.registries.MailcapFile");
    }
    private void loadAllResources(List r9, String r10) {
        /*
            r8 = this;
            java.lang.String r0 = "MailcapCommandMap: can't load "
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
            java.lang.String r3 = "MailcapCommandMap: getResources"
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
            java.lang.String r6 = "MailcapCommandMap: URL "
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
            com.sun.activation.registries.MailcapFile r6 = new com.sun.activation.registries.MailcapFile     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            r6.<init>((java.io.InputStream) r5)     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            r9.add(r6)     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            r3 = 1
            boolean r6 = com.sun.activation.registries.LogSupport.isLoggable()     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            if (r6 == 0) goto L_0x0097
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x007e, SecurityException -> 0x007c }
            java.lang.String r7 = "MailcapCommandMap: successfully loaded mailcap file from URL: "
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
            java.lang.String r7 = "MailcapCommandMap: not loading mailcap file from URL: "
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
            if (r1 != 0) goto L_0x0110
            boolean r0 = com.sun.activation.registries.LogSupport.isLoggable()
            if (r0 == 0) goto L_0x00f9
            java.lang.String r0 = "MailcapCommandMap: !anyLoaded"
            com.sun.activation.registries.LogSupport.log(r0)
        L_0x00f9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "/"
            r0.<init>(r1)
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            com.sun.activation.registries.MailcapFile r10 = r8.loadResource(r10)
            if (r10 == 0) goto L_0x0110
            r9.add(r10)
        L_0x0110:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.activation.MailcapCommandMap.loadAllResources(java.util.List, java.lang.String):void");
    }
    private MailcapFile loadFile(String str) {
        try {
            return new MailcapFile(str);
        } catch (IOException unused) {
            return null;
        }
    }
    public MailcapCommandMap(String str) throws IOException {
        this();
        if (LogSupport.isLoggable()) {
            LogSupport.log("MailcapCommandMap: load PROG from " + str);
        }
        MailcapFile[] mailcapFileArr = this.DB;
        if (null == mailcapFileArr[0]) {
            mailcapFileArr[0] = new MailcapFile(str);
        }
    }
    public MailcapCommandMap(InputStream inputStream) {
        this();
        LogSupport.log("MailcapCommandMap: load PROG");
        MailcapFile[] mailcapFileArr = this.DB;
        if (null == mailcapFileArr[0]) {
            try {
                mailcapFileArr[0] = new MailcapFile(inputStream);
            } catch (IOException unused) {
            }
        }
    }
    public synchronized CommandInfo[] getPreferredCommands(String str) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        if (null != str) {
            str = str.toLowerCase(Locale.ENGLISH);
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            final MailcapFile[] mailcapFileArr = DB;
            if (i3 >= mailcapFileArr.length) {
                break;
            }
            final MailcapFile mailcapFile = mailcapFileArr[i3];
            if (null != mailcapFile) {
                final Map mailcapList = mailcapFile.getMailcapList(str);
                if (null != mailcapList) {
                    this.appendPrefCmdsToList(mailcapList, arrayList);
                }
            }
            i3++;
        }
        while (true) {
            final MailcapFile[] mailcapFileArr2 = DB;
            if (i2 >= mailcapFileArr2.length) {
            } else {
                final MailcapFile mailcapFile2 = mailcapFileArr2[i2];
                if (null != mailcapFile2) {
                    final Map mailcapFallbackList = mailcapFile2.getMailcapFallbackList(str);
                    if (null != mailcapFallbackList) {
                        this.appendPrefCmdsToList(mailcapFallbackList, arrayList);
                    }
                }
                i2++;
            }
        }
    }
    private void appendPrefCmdsToList(Map map, List list) {
        for (Object str : map.keySet()) {
            if (!checkForVerb(list, (String) str)) {
                list.add(new CommandInfo((String) str, (String) ((List) map.get(str)).get(0)));
            }
        }
    }
    private boolean checkForVerb(List list, String str) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((CommandInfo) it.next()).getCommandName().equals(str)) {
                return true;
            }
        }
        return false;
    }
    public synchronized CommandInfo[] getAllCommands(String str) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        if (null != str) {
            str = str.toLowerCase(Locale.ENGLISH);
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            final MailcapFile[] mailcapFileArr = DB;
            if (i3 >= mailcapFileArr.length) {
                break;
            }
            final MailcapFile mailcapFile = mailcapFileArr[i3];
            if (null != mailcapFile) {
                final Map mailcapList = mailcapFile.getMailcapList(str);
                if (null != mailcapList) {
                    this.appendCmdsToList(mailcapList, arrayList);
                }
            }
            i3++;
        }
        while (true) {
            final MailcapFile[] mailcapFileArr2 = DB;
            if (i2 >= mailcapFileArr2.length) {
            } else {
                final MailcapFile mailcapFile2 = mailcapFileArr2[i2];
                if (null != mailcapFile2) {
                    final Map mailcapFallbackList = mailcapFile2.getMailcapFallbackList(str);
                    if (null != mailcapFallbackList) {
                        this.appendCmdsToList(mailcapFallbackList, arrayList);
                    }
                }
                i2++;
            }
        }
    }
    private void appendCmdsToList(Map map, List list) {
        for (Object str : map.keySet()) {
            for (Object commandInfo : (List) map.get(str)) {
                list.add(new CommandInfo((String) str, (String) commandInfo));
            }
        }
    }
    public synchronized CommandInfo getCommand(String r5, String r6) {

        throw new UnsupportedOperationException("Method not decompiled: javax.activation.MailcapCommandMap.getCommand(java.lang.String, java.lang.String):javax.activation.CommandInfo");
    }
    public synchronized void addMailcap(String str) {
        try {
            LogSupport.log("MailcapCommandMap: add to PROG");
            MailcapFile[] mailcapFileArr = this.DB;
            if (null == mailcapFileArr[0]) {
                mailcapFileArr[0] = new MailcapFile();
            }
            this.DB[0].appendToMailcap(str);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    public synchronized DataContentHandler createDataContentHandler(String r5) {

        throw new UnsupportedOperationException("Method not decompiled: javax.activation.MailcapCommandMap.createDataContentHandler(java.lang.String):javax.activation.DataContentHandler");
    }
    private DataContentHandler getDataContentHandler(String r4) {

        throw new UnsupportedOperationException("Method not decompiled: javax.activation.MailcapCommandMap.getDataContentHandler(java.lang.String):javax.activation.DataContentHandler");
    }
    public synchronized String[] getMimeTypes() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            MailcapFile[] mailcapFileArr = this.DB;
            if (i2 >= mailcapFileArr.length) {
            } else {
                MailcapFile mailcapFile = mailcapFileArr[i2];
                if (null != mailcapFile) {
                    String[] mimeTypes = mailcapFile.getMimeTypes();
                    if (null != mimeTypes) {
                        for (int i3 = 0; i3 < mimeTypes.length; i3++) {
                            if (!arrayList.contains(mimeTypes[i3])) {
                                arrayList.add(mimeTypes[i3]);
                            }
                        }
                    }
                }
                i2++;
            }
        }
    }
    public synchronized String[] getNativeCommands(String str) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        if (null != str) {
            str = str.toLowerCase(Locale.ENGLISH);
        }
        int i2 = 0;
        while (true) {
            MailcapFile[] mailcapFileArr = this.DB;
            if (i2 >= mailcapFileArr.length) {
            } else {
                MailcapFile mailcapFile = mailcapFileArr[i2];
                if (null != mailcapFile) {
                    String[] nativeCommands = mailcapFile.getNativeCommands(str);
                    if (null != nativeCommands) {
                        for (int i3 = 0; i3 < nativeCommands.length; i3++) {
                            if (!arrayList.contains(nativeCommands[i3])) {
                                arrayList.add(nativeCommands[i3]);
                            }
                        }
                    }
                }
                i2++;
            }
        }
    }
}
