package javax.activation;

import java.io.IOException;
import java.io.OutputStream;
import myjava.awt.datatransfer.DataFlavor;
import myjava.awt.datatransfer.UnsupportedFlavorException;

class DataSourceDataContentHandler implements DataContentHandler {
    private final DataContentHandler dch;
    private final DataSource ds;
    private DataFlavor[] transferFlavors;
    public DataSourceDataContentHandler(DataContentHandler dataContentHandler, DataSource dataSource) {
        this.ds = dataSource;
        this.dch = dataContentHandler;
    }
    public DataFlavor[] getTransferDataFlavors() {
        if (null == transferFlavors) {
            DataContentHandler dataContentHandler = this.dch;
            if (null != dataContentHandler) {
                this.transferFlavors = dataContentHandler.getTransferDataFlavors();
            } else {
                DataFlavor[] dataFlavorArr = new DataFlavor[1];
                this.transferFlavors = dataFlavorArr;
                dataFlavorArr[0] = new ActivationDataFlavor(this.ds.getContentType(), this.ds.getContentType());
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
            return dataSource.getInputStream();
        }
        throw new UnsupportedFlavorException(dataFlavor);
    }
    public Object getContent(DataSource dataSource) throws IOException {
        DataContentHandler dataContentHandler = this.dch;
        if (null != dataContentHandler) {
            return dataContentHandler.getContent(dataSource);
        }
        return dataSource.getInputStream();
    }
    public void writeTo(Object obj, String str, OutputStream outputStream) throws IOException {
        DataContentHandler dataContentHandler = this.dch;
        if (null != dataContentHandler) {
            dataContentHandler.writeTo(obj, str, outputStream);
            return;
        }
        throw new UnsupportedDataTypeException("no DCH for content type " + this.ds.getContentType());
    }
}
