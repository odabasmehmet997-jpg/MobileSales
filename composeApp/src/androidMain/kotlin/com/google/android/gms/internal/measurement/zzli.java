package com.google.android.gms.internal.measurement;

import com.fasterxml.jackson.core.JsonFactory;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import org.ksoap2.serialization.MarshalHashtable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
enum zzli {
    ;

    static String zza(zzlg zzlg, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzlg, sb, 0);
        return sb.toString();
    }

    static void zzb(StringBuilder sb, int i2, String str, Object obj) {
        if (obj instanceof List) {
            for (Object zzb : (List) obj) {
                zzb(sb, i2, str, zzb);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry zzb2 : ((Map) obj).entrySet()) {
                zzb(sb, i2, str, zzb2);
            }
        } else {
            sb.append(10);
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                sb.append(' ');
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzmg.zza(zziy.zzm((String) obj)));
                sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
            } else if (obj instanceof zziy) {
                sb.append(": \"");
                sb.append(zzmg.zza((zziy) obj));
                sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
            } else if (obj instanceof zzjz) {
                sb.append(" {");
                zzd((zzjz) obj, sb, i2 + 2);
                sb.append(SqlLiteVariable._NEW_LINE);
                while (i3 < i2) {
                    sb.append(' ');
                    i3++;
                }
                sb.append("}");
            } else if (obj instanceof final Map.Entry entry) {
                sb.append(" {");
                int i5 = i2 + 2;
                zzb(sb, i5, "key", entry.getKey());
                zzb(sb, i5, "value", entry.getValue());
                sb.append(SqlLiteVariable._NEW_LINE);
                while (i3 < i2) {
                    sb.append(' ');
                    i3++;
                }
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj);
            }
        }
    }

    private static String zzc(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }

    private static void zzd(zzlg zzlg, StringBuilder sb, int i2) {
        String str;
        boolean z;
        String str2;
        String str3;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet<>();
        for (Method method : zzlg.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (0 == method.getParameterTypes().length) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str4 : treeSet) {
            String substring = str4.startsWith("get") ? str4.substring(3) : str4;
            if (substring.endsWith("List") && !substring.endsWith("OrBuilderList") && !"List".equals(substring)) {
                String valueOf = substring.substring(0, 1).toLowerCase();
                String valueOf2 = substring.substring(1, substring.length() - 4);
                if (0 != valueOf2.length()) {
                    str3 = valueOf + valueOf2;
                } else {
                    str3 = valueOf;
                }
                Method method2 = (Method) hashMap.get(str4);
                if (null != method2 && method2.getReturnType().equals(List.class)) {
                    zzb(sb, i2, zzc(str3), zzjz.zzbC(method2, zzlg));
                }
            }
            if (substring.endsWith(MarshalHashtable.NAME) && !substring.equals(MarshalHashtable.NAME)) {
                String valueOf3 = substring.substring(0, 1).toLowerCase();
                String valueOf4 = substring.substring(1, substring.length() - 3);
                if (0 != valueOf4.length()) {
                    str2 = valueOf3 + valueOf4;
                } else {
                    str2 = valueOf3;
                }
                Method method3 = (Method) hashMap.get(str4);
                if (null != method3 && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zzb(sb, i2, zzc(str2), zzjz.zzbC(method3, zzlg));
                }
            }
            if (null != hashMap2.get(0 != substring.length() ? "set" + substring : "set")) {
                if (substring.endsWith("Bytes")) {
                    String valueOf5 = substring.substring(0, substring.length() - 5);
                    if (hashMap.containsKey(0 != valueOf5.length() ? "get" + valueOf5 : "get")) {
                    }
                }
                String valueOf6 = substring.substring(0, 1).toLowerCase();
                String valueOf7 = substring.substring(1);
                String concat = 0 != valueOf7.length() ? valueOf6 + valueOf7 : valueOf6;
                Method method4 = (Method) hashMap.get(0 != substring.length() ? "get" + substring : "get");
                if (0 != substring.length()) {
                    str = "has" + substring;
                } else {
                    str = "has";
                }
                Method method5 = (Method) hashMap.get(str);
                if (null != method4) {
                    Object zzbC = zzjz.zzbC(method4, zzlg);
                    if (null == method5) {
                        if (zzbC instanceof Boolean) {
                            if (!((Boolean) zzbC).booleanValue()) {
                            }
                        } else if (zzbC instanceof Integer) {
                            if (0 == ((Integer) zzbC).intValue()) {
                            }
                        } else if (zzbC instanceof Float) {
                            if (0 == Float.floatToRawIntBits(((Float) zzbC).floatValue())) {
                            }
                        } else if (!(zzbC instanceof Double)) {
                            if (zzbC instanceof String) {
                                z = zzbC.equals("");
                            } else if (zzbC instanceof zziy) {
                                z = zzbC.equals(zziy.zzb);
                            } else if (zzbC instanceof zzlg) {
                                if (zzbC == ((zzlg) zzbC).zzbJ()) {
                                }
                            } else if ((zzbC instanceof Enum) && 0 == ((Enum) zzbC).ordinal()) {
                            }
                            if (z) {
                            }
                        } else if (0 == Double.doubleToRawLongBits(((Double) zzbC).doubleValue())) {
                        }
                    } else if (!((Boolean) zzjz.zzbC(method5, zzlg, new Object[0])).booleanValue()) {
                    }
                    zzb(sb, i2, zzc(concat), zzbC);
                }
            }
        }
        if (!(zzlg instanceof final zzjw zzjw)) {
            zzmj zzmj = ((zzjz) zzlg).zzc;
            if (null != zzmj) {
                zzmj.zzg(sb, i2);
                return;
            }
            return;
        }
        throw null;
    }
}
