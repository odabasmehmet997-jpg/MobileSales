package com.proje.mobilesales.core.tigerwcf.xml;

import android.util.Log;
import androidx.annotation.NonNull;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.utils.CompressUtil;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.dbmodel.ConvertBinaries;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;



public class ResultHandler<T> extends DefaultHandler2 {
    private static final String RESULTLINE = "RESULTLINE";
    private static final String RESULTXML = "RESULTXML";
    private static final String TAG = "ResultHandler";
    private Class<T> aClass;
    private StringBuilder builder;
    private T instance;
    private HashMap mMap;
    private List<T> tList;
    public void startDocument() throws SAXException {
        super.startDocument();
        settList(new ArrayList());
        this.builder = new StringBuilder();
        createMapList();
    }

    private void createMapList() {
        this.mMap = new HashMap();
        try {
            for (Field field : this.aClass.newInstance().getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    if (column.shared().useProperty()) {
                        this.mMap.put(column.name(), field.getName());
                    }
                }
            }
        } catch (IllegalAccessException | InstantiationException unused) {
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        super.startElement(str, str2, str3, attributes);
        if (str2.equalsIgnoreCase(RESULTLINE)) {
            try {
                this.instance = getaClass().newInstance();
            } catch (IllegalAccessException | InstantiationException unused) {
            }
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        super.endElement(str, str2, str3);
        if (str2.equalsIgnoreCase(RESULTLINE)) {
            if (this.instance.getClass().getSuperclass() == ConvertBinaries.class) {
                ((ConvertBinaries) this.instance).checkBinaries();
            }
            this.tList.add(this.instance);
            this.builder.setLength(0);
            return;
        }
        if (str2.equalsIgnoreCase(RESULTXML)) {
            return;
        }
        setData(this.instance, str2, this.builder.toString());
        this.builder.setLength(0);
    }
    public void endDocument() throws SAXException {
        super.endDocument();
    }
    public void characters(char[] cArr, int i2, int i3) throws SAXException {
        super.characters(cArr, i2, i3);
        this.builder.append(cArr, i2, i3);
    }

    private void setData(T t,  String str,  String str2) {
        Field field;
        try {
            field = t.getClass().getDeclaredField(this.mMap.get(str).toString());
        } catch (Exception unused) {
            field = null;
        }
        if (field != null) {
            try {
                field.setAccessible(true);
                try {
                    field.set(t, getValueDeclared(field.getType(), str2));
                } catch (IllegalAccessException unused2) {
                }
                field.setAccessible(false);
            } catch (Exception e2) {
                Log.e(TAG, "setData: ", e2);
            }
        }
    }

    private Object getValueDeclared(Class<?> cls, String str) throws IOException {
        if (cls.equals(String.class) || cls == String.class) {
            return StringUtils.checkNullValueString(str);
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
                                            return DateAndTimeUtils.toDate(str, "yyyy-MM-dd'T'HH:mm:ss");
                                        }
                                        if (!cls.equals(Byte.class)) {
                                            Class cls6 = Byte.TYPE;
                                            if (!cls.equals(cls6) && !cls.equals(cls6) && !cls.getComponentType().getSimpleName().equals("byte")) {
                                                return "";
                                            }
                                        }
                                        if (!cls.isArray()) {
                                            return Boolean.valueOf(StringUtils.convertStringToBoolean(str));
                                        }
                                        return CompressUtil.base64DecodeArray(str);
                                    }
                                }
                                return Integer.valueOf(StringUtils.convertStringToInt(str));
                            }
                        }
                        return Double.valueOf(StringUtils.convertStringToDouble(str));
                    }
                }
                return Float.valueOf(StringUtils.convertStringToFloat(str));
            }
        }
        return Integer.valueOf(StringUtils.convertStringToInt(str));
    }

    public List<T> gettList() {
        return this.tList;
    }

    private void settList(List<T> list) {
        this.tList = list;
    }

    public Class<T> getaClass() {
        return this.aClass;
    }

    public void setaClass(Class<T> cls) {
        this.aClass = cls;
    }
}
