package com.sun.mail.handlers;

import myjava.awt.datatransfer.DataFlavor;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataContentHandler;
import javax.activation.DataSource;

public class image_gif implements DataContentHandler {
    static  Class classjavaawtImage;
    private static final ActivationDataFlavor myDF;
    static {
        Class clsClass = classjavaawtImage;
        if (clsClass == null) {
            final String s = "java.awt.Image";

            classjavaawtImage = clsClass;
        }
        myDF = new ActivationDataFlavor(clsClass, "image/gif", "GIF Image");
    }
    protected ActivationDataFlavor getDF() {
        return myDF;
    }
    public DataFlavor[] getTransferDataFlavors() {
        return new myjava.awt.datatransfer.DataFlavor[]{getDF()};
    }
    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws IOException {
        if (getDF().equals(dataFlavor)) {
            return getContent(dataSource);
        }
        return null;
    }
    public Object getContent(DataSource dataSource) throws IOException {
        InputStream inputStream = dataSource.getInputStream();
        byte[] bArr = new byte[1024];
        int i2 = 0;
        while (true) {
            int i3 = inputStream.read(bArr, i2, bArr.length - i2);
            if (i3 != -1) {
                i2 += i3;
                if (i2 >= bArr.length) {
                    int length = bArr.length;
                    byte[] bArr2 = new byte[length < 262144 ? length + length : length + 262144];
                    System.arraycopy(bArr, 0, bArr2, 0, i2);
                    bArr = bArr2;
                }
            } else {
                return Toolkit.getDefaultToolkit().createImage(bArr, 0, i2);
            }
        }
    }
    public void writeTo(Object obj, String str, OutputStream outputStream) throws IOException {
        if (!(obj instanceof Image)) {
            String stringBuffer = "\"" +
                    getDF().getMimeType() +
                    "\" DataContentHandler requires Image object, " +
                    "was given object of type " +
                    obj.getClass();
            throw new IOException(stringBuffer);
        }
        String stringBuffer2 = getDF().getMimeType() +
                " encoding not supported";
        throw new IOException(stringBuffer2);
    }
}
