package org.apache.harmony.awt.datatransfer;

import java.awt.datatransfer.DataFlavor;


public interface DataProvider {
    String FORMAT_FILE_LIST = "application/x-java-file-list";
    String FORMAT_HTML = "text/html";
    String FORMAT_IMAGE = "image/x-java-image";
    String FORMAT_TEXT = "text/plain";
    String FORMAT_URL = "application/x-java-url";
    String TYPE_FILELIST = "application/x-java-file-list";
    String TYPE_HTML = "text/html";
    String TYPE_IMAGE = "image/x-java-image";
    String TYPE_PLAINTEXT = "text/plain";
    String TYPE_SERIALIZED = "application/x-java-serialized-object";
    String TYPE_TEXTENCODING = "application/x-java-text-encoding";
    String TYPE_URL = "application/x-java-url";
    DataFlavor urlFlavor = new DataFlavor("application/x-java-url;class=java.net.URL", "URL");
    String TYPE_URILIST = "text/uri-list";
    DataFlavor uriFlavor = new DataFlavor(TYPE_URILIST, "URI");

    String[] getFileList();

    String getHTML();

    String[] getNativeFormats();

    RawBitmap getRawBitmap();

    byte[] getSerializedObject(Class<?> cls);

    String getText();

    String getURL();

    boolean isNativeFormatAvailable(String str);
}
