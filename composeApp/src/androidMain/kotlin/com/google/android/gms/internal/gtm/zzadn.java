package com.google.android.gms.internal.gtm;

import com.fasterxml.jackson.core.JsonFactory;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import org.ksoap2.serialization.MarshalHashtable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
enum zzadn {
    ;
    private static final char[] zza;

    static {
        char[] cArr = new char[80];
        zza = cArr;
        Arrays.fill(cArr, ' ');
    }

    static String zza(zzadl zzadl, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzadl, sb, 0);
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
            zzc(i2, sb);
            if (!str.isEmpty()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Character.toLowerCase(str.charAt(0)));
                for (int i3 = 1; i3 < str.length(); i3++) {
                    char charAt = str.charAt(i3);
                    if (Character.isUpperCase(charAt)) {
                        sb2.append("_");
                    }
                    sb2.append(Character.toLowerCase(charAt));
                }
                str = sb2.toString();
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzaeh.zza(new zzyv(((String) obj).getBytes(zzaco.zza))));
                sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
            } else if (obj instanceof zzyx) {
                sb.append(": \"");
                sb.append(zzaeh.zza((zzyx) obj));
                sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
            } else if (obj instanceof zzacf) {
                sb.append(" {");
                zzd((zzacf) obj, sb, i2 + 2);
                sb.append(SqlLiteVariable._NEW_LINE);
                zzc(i2, sb);
                sb.append("}");
            } else if (obj instanceof final Map.Entry entry) {
                int i4 = i2 + 2;
                sb.append(" {");
                zzb(sb, i4, "key", entry.getKey());
                zzb(sb, i4, "value", entry.getValue());
                sb.append(SqlLiteVariable._NEW_LINE);
                zzc(i2, sb);
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj);
            }
        }
    }

    private static void zzc(int i2, StringBuilder sb) {
        while (0 < i2) {
            int i3 = 80;
            if (80 >= i2) {
                i3 = i2;
            }
            sb.append(zza, 0, i3);
            i2 -= i3;
        }
    }

    private static void zzd(zzadl zzadl, StringBuilder sb, int i2) {
        int i3;
        boolean z;
        Method method;
        Method method2;
        zzadl zzadl2 = zzadl;
        StringBuilder sb2 = sb;
        int i4 = i2;
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        Method[] declaredMethods = zzadl.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i5 = 0;
        while (true) {
            i3 = 3;
            if (i5 >= length) {
                break;
            }
            Method method3 = declaredMethods[i5];
            if (!Modifier.isStatic(method3.getModifiers()) && 3 <= method3.getName().length()) {
                if (method3.getName().startsWith("set")) {
                    hashSet.add(method3.getName());
                } else if (Modifier.isPublic(method3.getModifiers()) && 0 == method3.getParameterTypes().length) {
                    if (method3.getName().startsWith("has")) {
                        hashMap.put(method3.getName(), method3);
                    } else if (method3.getName().startsWith("get")) {
                        treeMap.put(method3.getName(), method3);
                    }
                }
            }
            i5++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String substring = ((String) entry.getKey()).substring(i3);
            if (substring.endsWith("List") && !substring.endsWith("OrBuilderList") && !"List".equals(substring) && null != (method2 = (Method) entry.getValue()) && method2.getReturnType().equals(List.class)) {
                zzb(sb2, i4, substring.substring(0, substring.length() - 4), zzacf.zzak(method2, zzadl2));
            } else if (substring.endsWith(MarshalHashtable.NAME) && !substring.equals(MarshalHashtable.NAME) && null != (method = (Method) entry.getValue()) && method.getReturnType().equals(Map.class) && !method.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method.getModifiers())) {
                zzb(sb2, i4, substring.substring(0, substring.length() - 3), zzacf.zzak(method, zzadl2));
            } else if (hashSet.contains("set" + substring) && (!substring.endsWith("Bytes") || !treeMap.containsKey("get" + substring.substring(0, substring.length() - 5)))) {
                Method method4 = (Method) entry.getValue();
                Method method5 = (Method) hashMap.get("has" + substring);
                if (null != method4) {
                    Object zzak = zzacf.zzak(method4, zzadl2);
                    if (null == method5) {
                        if (zzak instanceof Boolean) {
                            if (!((Boolean) zzak).booleanValue()) {
                            }
                        } else if (zzak instanceof Integer) {
                            if (0 == ((Integer) zzak).intValue()) {
                            }
                        } else if (zzak instanceof Float) {
                            if (0 == Float.floatToRawIntBits(((Float) zzak).floatValue())) {
                            }
                        } else if (!(zzak instanceof Double)) {
                            if (zzak instanceof String) {
                                z = zzak.equals("");
                            } else if (zzak instanceof zzyx) {
                                z = zzak.equals(zzyx.zzb);
                            } else if (zzak instanceof zzadl) {
                                if (zzak == ((zzadl) zzak).zzay()) {
                                }
                            } else if ((zzak instanceof Enum) && 0 == ((Enum) zzak).ordinal()) {
                            }
                            if (z) {
                            }
                        } else if (0 == Double.doubleToRawLongBits(((Double) zzak).doubleValue())) {
                        }
                    } else if (!((Boolean) zzacf.zzak(method5, zzadl2, new Object[0])).booleanValue()) {
                    }
                    zzb(sb2, i4, substring, zzak);
                }
            }
            i3 = 3;
        }
        if (zzadl2 instanceof zzacc) {
            Iterator zzg = ((zzacc) zzadl2).zza.zzg();
            while (zzg.hasNext()) {
                Map.Entry entry2 = (Map.Entry) zzg.next();
                zzb(sb2, i4, "[" + ((zzacd) entry2.getKey()).zzb + "]", entry2.getValue());
            }
        }
        zzaen zzaen = ((zzacf) zzadl2).zzc;
        if (null != zzaen) {
            zzaen.zzi(sb2, i4);
        }
    }
}
