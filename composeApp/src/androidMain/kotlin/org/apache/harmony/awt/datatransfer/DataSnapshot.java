package org.apache.harmony.awt.datatransfer;

import myjava.awt.datatransfer.DataFlavor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DataSnapshot implements DataProvider {
    private final String[] fileList;
    private final String html;
    private final String[] nativeFormats;
    private final RawBitmap rawBitmap;
    private final Map<Class<?>, byte[]> serializedObjects = Collections.synchronizedMap(new HashMap());
    private final String text;
    private final String url;

    public DataSnapshot(DataProvider dataProvider) {
        Class<?> dataFlavor;
        Class<?> representationClass;
        byte[] serializedObject;
        this.nativeFormats = dataProvider.getNativeFormats();
        this.text = dataProvider.getText();
        this.fileList = dataProvider.getFileList();
        this.url = dataProvider.getURL();
        this.html = dataProvider.getHTML();
        this.rawBitmap = dataProvider.getRawBitmap();
        int i2 = 0;
        while (true) {
            String[] strArr = this.nativeFormats;
            if (i2 < strArr.length) {
                Object SystemFlavorMap = null;
                dataFlavor = SystemFlavorMap.getClass();
                if (!((dataFlavor == null) || (((serializedObject = dataProvider.getSerializedObject((representationClass = dataFlavor.getRepresentationClass())))) == null))) {
                    this.serializedObjects.put(representationClass, serializedObject);
                }
                i2++;
            } else {
                return;
            }
        }
    }
    public boolean isNativeFormatAvailable(String str) {
        if (str == null) {
            return false;
        }
        if (str.equals("text/plain")) {
            return this.text != null;
        }
        if (str.equals("application/x-java-file-list")) {
            return this.fileList != null;
        }
        if (str.equals("application/x-java-url")) {
            return this.url != null;
        }
        if (str.equals("text/html")) {
            return this.html != null;
        }
        if (str.equals("image/x-java-image")) {
            return this.rawBitmap != null;
        }
        try {
            return this.serializedObjects.containsKey(SystemFlavorMap.decodeDataFlavor(str).getRepresentationClass());
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // org.apache.harmony.awt.datatransfer.DataProvider
    public String getText() {
        return this.text;
    }

    @Override // org.apache.harmony.awt.datatransfer.DataProvider
    public String[] getFileList() {
        return this.fileList;
    }

    @Override // org.apache.harmony.awt.datatransfer.DataProvider
    public String getURL() {
        return this.url;
    }

    @Override // org.apache.harmony.awt.datatransfer.DataProvider
    public String getHTML() {
        return this.html;
    }

    @Override // org.apache.harmony.awt.datatransfer.DataProvider
    public RawBitmap getRawBitmap() {
        return this.rawBitmap;
    }

    public int[] getRawBitmapHeader() {
        RawBitmap rawBitmap = this.rawBitmap;
        if (rawBitmap != null) {
            return rawBitmap.getHeader();
        }
        return null;
    }

    public byte[] getRawBitmapBuffer8() {
        RawBitmap rawBitmap = this.rawBitmap;
        if (rawBitmap != null) {
            Object obj = rawBitmap.buffer;
            if (obj instanceof byte[]) {
                return (byte[]) obj;
            }
        }
        return null;
    }

    public short[] getRawBitmapBuffer16() {
        RawBitmap rawBitmap = this.rawBitmap;
        if (rawBitmap != null) {
            Object obj = rawBitmap.buffer;
            if (obj instanceof short[]) {
                return (short[]) obj;
            }
        }
        return null;
    }

    public int[] getRawBitmapBuffer32() {
        RawBitmap rawBitmap = this.rawBitmap;
        if (rawBitmap != null) {
            Object obj = rawBitmap.buffer;
            if (obj instanceof int[]) {
                return (int[]) obj;
            }
        }
        return null;
    }
    public byte[] getSerializedObject(Class<?> cls) {
        return this.serializedObjects.get(cls);
    }

    public byte[] getSerializedObject(String str) {
        try {
            return getSerializedObject(SystemFlavorMap.decodeDataFlavor(str).getRepresentationClass());
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.apache.harmony.awt.datatransfer.DataProvider
    public String[] getNativeFormats() {
        return this.nativeFormats;
    }
}
