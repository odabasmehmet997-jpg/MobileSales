package com.proje.mobilesales.core.netsis;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.utils.CompressUtil;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.dbmodel.ConvertBinaries;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetsisDataConverter<T> {
    private static final String TAG = "NetsisDataConverter";

    public List<T> convertData(Class<T> cls, List<NetsisData> list) throws Exception {
        return convertData(cls, list, cls.isAnnotationPresent(SafeType.class));
    }

    public List<T> convertData(Class<T> cls, List<NetsisData> list, boolean z) throws Exception {
        Map<String, String> createMapField;
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() != 0) {
            if (z) {
                createMapField = createMapFieldSafe(cls, list.get(0));
            } else {
                createMapField = createMapField(cls, list.get(0));
            }
            for (NetsisData netsisData : list) {
                if (netsisData.getNetsisDataPrimitives() != null && netsisData.getNetsisDataPrimitives().size() > 0) {
                    T newInstance = cls.newInstance();
                    for (NetsisDataPrimitive netsisDataPrimitive : netsisData.getNetsisDataPrimitives()) {
                        if (!TextUtils.isEmpty(createMapField.get(netsisDataPrimitive.getName()))) {
                            setFieldValue(cls, newInstance, createMapField.get(netsisDataPrimitive.getName()), netsisDataPrimitive.getValue());
                        }
                    }
                    if (newInstance.getClass().getSuperclass() == ConvertBinaries.class) {
                        ((ConvertBinaries) newInstance).checkBinaries();
                    }
                    arrayList.add(newInstance);
                }
            }
        }
        return arrayList;
    }

    private void setFieldValue(Class<?> cls, T t, String str, Object obj) throws Exception {
        Field declaredField = cls.getDeclaredField(str);
        if (declaredField != null) {
            Object convertValueFieldType = convertValueFieldType(declaredField.getType(), obj);
            declaredField.setAccessible(true);
            declaredField.set(t, convertValueFieldType);
            declaredField.setAccessible(false);
        }
    }

    public Object convertValueFieldType(Class<?> cls, Object obj) throws Exception {
        if (cls.equals(String.class) || cls == String.class) {
            return String.valueOf(obj);
        }
        if (!cls.equals(Integer.class)) {
            Class cls2 = Integer.TYPE;
            if (!cls.equals(cls2) && !cls.equals(cls2)) {
                if (!cls.equals(Float.class)) {
                    Class cls3 = Float.TYPE;
                    if (!cls.equals(cls3) && !cls.equals(cls3)) {
                        if (!cls.equals(Double.class)) {
                            Class cls4 = Double.TYPE;
                            if (!cls.equals(cls4) && !cls.equals(cls4)) {
                                if (!cls.equals(Boolean.class)) {
                                    Class cls5 = Boolean.TYPE;
                                    if (!cls.equals(cls5) && !cls.equals(cls5)) {
                                        if (cls.equals(Date.class)) {
                                            return DateAndTimeUtils.toDate(String.valueOf(obj), "yyyy-MM-dd HH:mm:ss");
                                        }
                                        return cls.equals(byte[].class) ? CompressUtil.base64DecodeArray(obj.toString()) : obj;
                                    }
                                }
                                return Boolean.valueOf(String.valueOf(obj));
                            }
                        }
                        if (String.valueOf(obj).indexOf(".") > 0) {
                            return Double.valueOf(StringUtils.convertStringToDouble(String.valueOf(obj)));
                        }
                        return Double.valueOf(StringUtils.convertStringToDoubleNetsis(String.valueOf(obj)));
                    }
                }
                return Float.valueOf(String.valueOf(obj));
            }
        }
        return Integer.valueOf(StringUtils.convertStringToInt(String.valueOf(obj)));
    }

    private Map<String, String> createMapField(Class<T> cls, NetsisData netsisData) {
        HashMap hashMap = new HashMap();
        if (netsisData != null) {
            for (NetsisDataPrimitive netsisDataPrimitive : netsisData.getNetsisDataPrimitives()) {
                String searchField = searchField(cls, netsisDataPrimitive.getName());
                if (!TextUtils.isEmpty(searchField)) {
                    hashMap.put(netsisDataPrimitive.getName(), searchField);
                }
            }
        }
        return hashMap;
    }

    private Map<String, String> createMapFieldSafe(Class<T> cls, NetsisData netsisData) {
        HashMap hashMap = new HashMap();
        if (netsisData != null) {
            for (NetsisDataPrimitive netsisDataPrimitive : netsisData.getNetsisDataPrimitives()) {
                String searchFieldSafe = searchFieldSafe(cls, netsisDataPrimitive.getName());
                if (!TextUtils.isEmpty(searchFieldSafe)) {
                    hashMap.put(netsisDataPrimitive.getName(), searchFieldSafe);
                }
            }
        }
        return hashMap;
    }

    private String searchField(Class<T> cls, String str) {
        if (cls.isAnnotationPresent(Tables.class)) {
            Tables tables = cls.getAnnotation(Tables.class);
            for (Field field : cls.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class) && getMappingName(tables, field.getAnnotation(Column.class)).equals(str)) {
                    return field.getName();
                }
            }
        }
        return "";
    }

    private String getMappingName(Tables tables, Column column) {
        String netsisName = column.netsisName();
        if (!tables.appTable()) {
            if (!column.isAllSame() && !column.netsis().useProperty()) {
                return "";
            }
            if (!TextUtils.isEmpty(column.netsisName()) || !column.isAllSameMappingName()) {
                return netsisName;
            }
            return column.name();
        } else if (!column.netsis().useProperty()) {
            return "";
        } else {
            return column.name();
        }
    }

    private String searchFieldSafe(Class<T> cls, String str) {
        for (Field field : cls.getDeclaredFields()) {
            if (field.isAnnotationPresent(SerializedName.class)) {
                if (field.getAnnotation(SerializedName.class).value().equals(str)) {
                    return field.getName();
                }
            } else if (field.getName().equals(str)) {
                return field.getName();
            }
        }
        return "";
    }
}
