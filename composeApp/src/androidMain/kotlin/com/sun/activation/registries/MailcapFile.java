package com.sun.activation.registries;

import androidx.webkit.ProxyConfig;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MailcapFile {
    private static boolean addReverse;
    private final Map fallback_hash = new HashMap();
    private final Map native_commands = new HashMap();
    private final Map type_hash = new HashMap();
    static {
        try {
            MailcapFile.addReverse = Boolean.getBoolean("javax.activation.addreverse");
        } catch (final Throwable unused) {
        }
    }
    public MailcapFile(final String r3) throws IOException {
        throw new UnsupportedOperationException("Method not decompiled: com.sun.activation.registries.MailcapFile.<init>(java.lang.String):void");
    }
    public MailcapFile(final InputStream inputStream) throws IOException {
        if (LogSupport.isLoggable()) {
            LogSupport.log("new MailcapFile: InputStream");
        }
        this.parse(new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1)));
    }
    public MailcapFile() {
        if (LogSupport.isLoggable()) {
            LogSupport.log("new MailcapFile: default");
        }
    }
    public Map getMailcapList(final String str) {
        final Map map = (Map) type_hash.get(str);
        final int indexOf = str.indexOf(47) + 1;
        if (str.substring(indexOf).equals(ProxyConfig.MATCH_ALL_SCHEMES)) {
            return map;
        }
        final Map map2 = (Map) type_hash.get(str.substring(0, indexOf) + ProxyConfig.MATCH_ALL_SCHEMES);
        if (null != map2) {
            return null != map ? this.mergeResults(map, map2) : map2;
        }
        return map;
    }
    public Map getMailcapFallbackList(final String str) {
        final Map map = (Map) fallback_hash.get(str);
        final int indexOf = str.indexOf(47) + 1;
        if (str.substring(indexOf).equals(ProxyConfig.MATCH_ALL_SCHEMES)) {
            return map;
        }
        final Map map2 = (Map) fallback_hash.get(str.substring(0, indexOf) + ProxyConfig.MATCH_ALL_SCHEMES);
        if (null != map2) {
            return null != map ? this.mergeResults(map, map2) : map2;
        }
        return map;
    }
    public String[] getMimeTypes() {
        final HashSet hashSet = new HashSet(type_hash.keySet());
        hashSet.addAll(fallback_hash.keySet());
        hashSet.addAll(native_commands.keySet());
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }
    public String[] getNativeCommands(final String str) {
        final List list = (List) native_commands.get(str.toLowerCase(Locale.ENGLISH));
        if (null != list) {
            return (String[]) list.toArray(new String[list.size()]);
        }
        return null;
    }
    private Map mergeResults(final Map map, final Map map2) {
        final HashMap hashMap = new HashMap(map);
        for (final Object str : map2.keySet()) {
            final List list = (List) hashMap.get(str);
            if (null == list) {
                hashMap.put(str, map2.get(str));
            } else {
                final ArrayList arrayList = new ArrayList(list);
                arrayList.addAll((List) map2.get(str));
                hashMap.put(str, arrayList);
            }
        }
        return hashMap;
    }
    public void appendToMailcap(final String str) {
        if (LogSupport.isLoggable()) {
            LogSupport.log("appendToMailcap: " + str);
        }
        try {
            this.parse(new StringReader(str));
        } catch (final IOException unused) {
        }
    }
    private void parse(final java.io.Reader r7) throws IOException {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.activation.registries.MailcapFile.parse(java.io.Reader):void");
    }
    public void parseLine(final String str) throws MailcapParseException, IOException {
        final String str2;
        int nextToken;
        final MailcapTokenizer mailcapTokenizer = new MailcapTokenizer(str);
        mailcapTokenizer.setIsAutoquoting(false);
        if (LogSupport.isLoggable()) {
            LogSupport.log("parse: " + str);
        }
        final int nextToken2 = mailcapTokenizer.nextToken();
        if (2 != nextToken2) {
            MailcapFile.reportParseError(2, nextToken2, mailcapTokenizer.getCurrentTokenValue());
        }
        final String currentTokenValue = mailcapTokenizer.getCurrentTokenValue();
        final Locale locale = Locale.ENGLISH;
        final String lowerCase = currentTokenValue.toLowerCase(locale);
        int nextToken3 = mailcapTokenizer.nextToken();
        if (!(47 == nextToken3 || 59 == nextToken3)) {
            MailcapFile.reportParseError(47, 59, nextToken3, mailcapTokenizer.getCurrentTokenValue());
        }
        if (47 == nextToken3) {
            final int nextToken4 = mailcapTokenizer.nextToken();
            if (2 != nextToken4) {
                MailcapFile.reportParseError(2, nextToken4, mailcapTokenizer.getCurrentTokenValue());
            }
            str2 = mailcapTokenizer.getCurrentTokenValue().toLowerCase(locale);
            nextToken3 = mailcapTokenizer.nextToken();
        } else {
            str2 = ProxyConfig.MATCH_ALL_SCHEMES;
        }
        final String str3 = lowerCase + "/" + str2;
        if (LogSupport.isLoggable()) {
            LogSupport.log("  Type: " + str3);
        }
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (59 != nextToken3) {
            MailcapFile.reportParseError(59, nextToken3, mailcapTokenizer.getCurrentTokenValue());
        }
        mailcapTokenizer.setIsAutoquoting(true);
        int nextToken5 = mailcapTokenizer.nextToken();
        mailcapTokenizer.setIsAutoquoting(false);
        if (!(2 == nextToken5 || 59 == nextToken5)) {
            MailcapFile.reportParseError(2, 59, nextToken5, mailcapTokenizer.getCurrentTokenValue());
        }
        if (2 == nextToken5) {
            final List list = (List) native_commands.get(str3);
            if (null == list) {
                final ArrayList arrayList = new ArrayList();
                arrayList.add(str);
                native_commands.put(str3, arrayList);
            } else {
                list.add(str);
            }
        }
        if (59 != nextToken5) {
            nextToken5 = mailcapTokenizer.nextToken();
        }
        if (59 == nextToken5) {
            boolean z = false;
            do {
                final int nextToken6 = mailcapTokenizer.nextToken();
                if (2 != nextToken6) {
                    MailcapFile.reportParseError(2, nextToken6, mailcapTokenizer.getCurrentTokenValue());
                }
                final String lowerCase2 = mailcapTokenizer.getCurrentTokenValue().toLowerCase(Locale.ENGLISH);
                nextToken = mailcapTokenizer.nextToken();
                if (!(61 == nextToken || 59 == nextToken || 5 == nextToken)) {
                    MailcapFile.reportParseError(61, 59, 5, nextToken, mailcapTokenizer.getCurrentTokenValue());
                }
                if (61 == nextToken) {
                    mailcapTokenizer.setIsAutoquoting(true);
                    final int nextToken7 = mailcapTokenizer.nextToken();
                    mailcapTokenizer.setIsAutoquoting(false);
                    if (2 != nextToken7) {
                        MailcapFile.reportParseError(2, nextToken7, mailcapTokenizer.getCurrentTokenValue());
                    }
                    final String currentTokenValue2 = mailcapTokenizer.getCurrentTokenValue();
                    if (lowerCase2.startsWith("x-java-")) {
                        final String substring = lowerCase2.substring(7);
                        if (!"fallback-entry".equals(substring) || !"true".equalsIgnoreCase(currentTokenValue2)) {
                            if (LogSupport.isLoggable()) {
                                LogSupport.log("    Command: " + substring + ", Class: " + currentTokenValue2);
                            }
                            List list2 = (List) linkedHashMap.get(substring);
                            if (null == list2) {
                                list2 = new ArrayList();
                                linkedHashMap.put(substring, list2);
                            }
                            if (MailcapFile.addReverse) {
                                list2.add(0, currentTokenValue2);
                            } else {
                                list2.add(currentTokenValue2);
                            }
                        } else {
                            z = true;
                        }
                    }
                    nextToken = mailcapTokenizer.nextToken();
                    continue;
                }
            } while (59 == nextToken);
            final Map map = z ? fallback_hash : type_hash;
            final Map map2 = (Map) map.get(str3);
            if (null == map2) {
                map.put(str3, linkedHashMap);
                return;
            }
            if (LogSupport.isLoggable()) {
                LogSupport.log("Merging commands for type " + str3);
            }
            for (final Object str4 : map2.keySet()) {
                final List list3 = (List) map2.get(str4);
                final List<String> list4 = (List) linkedHashMap.get(str4);
                if (null != list4) {
                    for (final String str5 : list4) {
                        if (!list3.contains(str5)) {
                            if (MailcapFile.addReverse) {
                                list3.add(0, str5);
                            } else {
                                list3.add(str5);
                            }
                        }
                    }
                }
            }
            for (final Object str6 : linkedHashMap.keySet()) {
                if (!map2.containsKey(str6)) {
                    map2.put(str6, linkedHashMap.get(str6));
                }
            }
        } else if (5 != nextToken5) {
            MailcapFile.reportParseError(5, 59, nextToken5, mailcapTokenizer.getCurrentTokenValue());
        }
    }
    protected static void reportParseError(final int i2, final int i3, final String str) throws MailcapParseException {
        throw new MailcapParseException("Encountered a " + MailcapTokenizer.nameForToken(i3) + " token (" + str + ") while expecting a " + MailcapTokenizer.nameForToken(i2) + " token.");
    }
    protected static void reportParseError(final int i2, final int i3, final int i4, final String str) throws MailcapParseException {
        throw new MailcapParseException("Encountered a " + MailcapTokenizer.nameForToken(i4) + " token (" + str + ") while expecting a " + MailcapTokenizer.nameForToken(i2) + " or a " + MailcapTokenizer.nameForToken(i3) + " token.");
    }
    protected static void reportParseError(final int i2, final int i3, final int i4, final int i5, final String str) throws MailcapParseException {
        if (LogSupport.isLoggable()) {
            LogSupport.log("PARSE ERROR: Encountered a " + MailcapTokenizer.nameForToken(i5) + " token (" + str + ") while expecting a " + MailcapTokenizer.nameForToken(i2) + ", a " + MailcapTokenizer.nameForToken(i3) + ", or a " + MailcapTokenizer.nameForToken(i4) + " token.");
        }
        throw new MailcapParseException("Encountered a " + MailcapTokenizer.nameForToken(i5) + " token (" + str + ") while expecting a " + MailcapTokenizer.nameForToken(i2) + ", a " + MailcapTokenizer.nameForToken(i3) + ", or a " + MailcapTokenizer.nameForToken(i4) + " token.");
    }
}
