package org.springframework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public abstract class StringUtils {
    public static boolean isEmpty(Object obj) {
        return obj == null || "".equals(obj);
    }
    public static boolean hasLength(CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0;
    }
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }
    public static boolean hasText(CharSequence charSequence) {
        if (!hasLength(charSequence)) {
            return false;
        }
        int length = charSequence.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (!Character.isWhitespace(charSequence.charAt(i2))) {
                return true;
            }
        }
        return false;
    }
    public static boolean hasText(String str) {
        return hasText((CharSequence) str);
    }
    public static String replace(String str, String str2, String str3) {
        if (!hasLength(str) || !hasLength(str2) || str3 == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int iIndexOf = str.indexOf(str2);
        int length = str2.length();
        int i2 = 0;
        while (iIndexOf >= 0) {
            sb.append(str.substring(i2, iIndexOf));
            sb.append(str3);
            i2 = iIndexOf + length;
            iIndexOf = str.indexOf(str2, i2);
        }
        sb.append(str.substring(i2));
        return sb.toString();
    }
    public static String deleteAny(String str, String str2) {
        if (!hasLength(str) || !hasLength(str2)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (str2.indexOf(cCharAt) == -1) {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }
    public static String quote(String str) {
        if (str == null) {
            return null;
        }
        return "'" + str + "'";
    }
    public static String cleanPath(String str) {
        if (str == null) {
            return null;
        }
        String strReplace = replace(str, "\\", "/");
        int iIndexOf = strReplace.indexOf(":");
        String str2 = "";
        if (iIndexOf != -1) {
            int i2 = iIndexOf + 1;
            String strSubstring = strReplace.substring(0, i2);
            if (!strSubstring.contains("/")) {
                strReplace = strReplace.substring(i2);
                str2 = strSubstring;
            }
        }
        if (strReplace.startsWith("/")) {
            str2 = str2 + "/";
            strReplace = strReplace.substring(1);
        }
        String[] strArrDelimitedListToStringArray = delimitedListToStringArray(strReplace, "/");
        LinkedList linkedList = new LinkedList();
        int i3 = 0;
        for (int length = strArrDelimitedListToStringArray.length - 1; length >= 0; length--) {
            String str3 = strArrDelimitedListToStringArray[length];
            if (!".".equals(str3)) {
                if ("..".equals(str3)) {
                    i3++;
                } else if (i3 > 0) {
                    i3--;
                } else {
                    linkedList.add(0, str3);
                }
            }
        }
        for (int i4 = 0; i4 < i3; i4++) {
            linkedList.add(0, "..");
        }
        return str2 + collectionToDelimitedString(linkedList, "/");
    }
    public static String[] toStringArray(Collection<String> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new String[collection.size()]);
    }
    public static String[] tokenizeToStringArray(String str, String str2) {
        return tokenizeToStringArray(str, str2, true, true);
    }
    public static String[] tokenizeToStringArray(String str, String str2, boolean z, boolean z2) {
        if (str == null) {
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, str2);
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            if (z) {
                strNextToken = strNextToken.trim();
            }
            if (!z2 || strNextToken.length() > 0) {
                arrayList.add(strNextToken);
            }
        }
        return toStringArray(arrayList);
    }
    public static String[] delimitedListToStringArray(String str, String str2) {
        return delimitedListToStringArray(str, str2, null);
    }
    public static String[] delimitedListToStringArray(String str, String str2, String str3) {
        int length = 0;
        if (str == null) {
            return new String[0];
        }
        if (str2 == null) {
            return new String[]{str};
        }
        ArrayList arrayList = new ArrayList();
        if ("".equals(str2)) {
            while (length < str.length()) {
                int i2 = length + 1;
                arrayList.add(deleteAny(str.substring(length, i2), str3));
                length = i2;
            }
        } else {
            while (true) {
                int iIndexOf = str.indexOf(str2, length);
                if (iIndexOf == -1) {
                    break;
                }
                arrayList.add(deleteAny(str.substring(length, iIndexOf), str3));
                length = str2.length() + iIndexOf;
            }
            if (str.length() > 0 && length <= str.length()) {
                arrayList.add(deleteAny(str.substring(length), str3));
            }
        }
        return toStringArray(arrayList);
    }
    public static String collectionToDelimitedString(Collection<?> collection, String str, String str2, String str3) {
        if (CollectionUtils.isEmpty(collection)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            sb.append(str2);
            sb.append(it.next());
            sb.append(str3);
            if (it.hasNext()) {
                sb.append(str);
            }
        }
        return sb.toString();
    }
    public static String collectionToDelimitedString(Collection<?> collection, String str) {
        return collectionToDelimitedString(collection, str, "", "");
    }
    public static String collectionToCommaDelimitedString(Collection<?> collection) {
        return collectionToDelimitedString(collection, ",");
    }
}
