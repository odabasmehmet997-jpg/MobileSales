package javax.activation;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import myjava.awt.datatransfer.DataFlavor;
import myjava.awt.datatransfer.UnsupportedFlavorException;

class ObjectDataContentHandler implements DataContentHandler {
    private final DataContentHandler dch;
    private final String mimeType;
    private final Object obj;
    private DataFlavor[] transferFlavors;

    public ObjectDataContentHandler(DataContentHandler dataContentHandler, Object obj2, String str) {
        this.obj = obj2;
        this.mimeType = str;
        this.dch = dataContentHandler;
    }

    public DataContentHandler getDCH() {
        return this.dch;
    }

    public synchronized DataFlavor[] getTransferDataFlavors() {
        try {
            if (null == transferFlavors) {
                DataContentHandler dataContentHandler = this.dch;
                if (null != dataContentHandler) {
                    this.transferFlavors = dataContentHandler.getTransferDataFlavors();
                } else {
                    DataFlavor[] dataFlavorArr = new DataFlavor[1];
                    this.transferFlavors = dataFlavorArr;
                    Class<?> cls = this.obj.getClass();
                    String str = this.mimeType;
                    dataFlavorArr[0] = new ActivationDataFlavor(cls, str, str);
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.transferFlavors;
    }

    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws UnsupportedFlavorException, IOException {
        DataContentHandler dataContentHandler = this.dch;
        if (null != dataContentHandler) {
            return dataContentHandler.getTransferData(dataFlavor, dataSource);
        }
        if (dataFlavor.equals(getTransferDataFlavors()[0])) {
            return this.obj;
        }
        throw new UnsupportedFlavorException(dataFlavor);
    }

    public Object getContent(DataSource dataSource) {
        return this.obj;
    }

    public void writeTo(Object obj2, String str, OutputStream outputStream) throws IOException {
        DataContentHandler dataContentHandler = this.dch;
        if (null != dataContentHandler) {
            dataContentHandler.writeTo(obj2, str, outputStream);
        } else if (obj2 instanceof byte[]) {
            outputStream.write((byte[]) obj2);
        } else if (obj2 instanceof String) {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write((String) obj2);
            outputStreamWriter.flush();
        } else {
            throw new UnsupportedDataTypeException("no object DCH for MIME type " + this.mimeType);
        }
    }
}
