package sun.mail.handlers;

import myjava.awt.datatransfer.DataFlavor;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataContentHandler;
import javax.activation.DataSource;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeUtility;
import java.io.*;

public class text_plain implements DataContentHandler {
    static   Class classjavalangString;
    private static final ActivationDataFlavor myDF;
    static {
        Class cls = classjavalangString;
        if (null == cls) {
            cls = ("java.lang.String").getClass();
            classjavalangString = cls;
        }
        myDF = new ActivationDataFlavor(cls, "text/plain", "Text String");
    }
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{getDF()};
    }
    private static class NoCloseOutputStream extends FilterOutputStream {
        public void close() {
        }

        public NoCloseOutputStream(OutputStream outputStream) {
            super(outputStream);
        }
    }
    public ActivationDataFlavor getDF() {
        return myDF;
    }
    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws IOException {
        if (getDF().equals(dataFlavor)) {
            return getContent(dataSource);
        }
        return null;
    }
    public Object getContent(DataSource dataSource) throws IOException {
        String str = null;
        try {
            str = getCharset(dataSource.getContentType());
            InputStreamReader inputStreamReader = new InputStreamReader(dataSource.getInputStream(), str);
            try {
                char[] cArr = new char[1024];
                int i2 = 0;
                while (true) {
                    int read = inputStreamReader.read(cArr, i2, cArr.length - i2);
                    if (-1 == read) {
                        break;
                    }
                    i2 += read;
                    if (i2 >= cArr.length) {
                        int length = cArr.length;
                        char[] cArr2 = new char[(262144 > length ? length + length : length + 262144)];
                        System.arraycopy(cArr, 0, cArr2, 0, i2);
                        cArr = cArr2;
                    }
                }
                String str2 = new String(cArr, 0, i2);
                try {
                    inputStreamReader.close();
                } catch (IOException unused) {
                }
                return str2;
            } finally {
                try {
                    inputStreamReader.close();
                } catch (IOException unused2) {
                }
            }
        } catch (IllegalArgumentException unused3) {
            throw new UnsupportedEncodingException(str);
        }
    }
    public void writeTo(Object obj, String str, OutputStream outputStream) throws IOException {
        String str2;
        if (obj instanceof String str3) {
            try {
                str2 = getCharset(str);
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new NoCloseOutputStream(outputStream), str2);
                    outputStreamWriter.write(str3, 0, str3.length());
                    outputStreamWriter.close();
                } catch (IllegalArgumentException unused) {
                    throw new UnsupportedEncodingException(str2);
                }
            } catch (IllegalArgumentException unused2) {
                str2 = null;
                throw new UnsupportedEncodingException(str2);
            }
        } else {
            final String stringBuffer = "\"" +
                    getDF().getMimeType() +
                    "\" DataContentHandler requires String object, " +
                    "was given object of type " +
                    obj.getClass();
            throw new IOException(stringBuffer);
        }
    }
    private String getCharset(String str) {
        try {
            String parameter = new ContentType(str).getParameter("charset");
            if (null == parameter) {
                parameter = "us-ascii";
            }
            return MimeUtility.javaCharset(parameter);
        } catch (Exception unused) {
            return null;
        }
    }
}
