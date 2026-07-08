package javax.activation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.URL;
import myjava.awt.datatransfer.DataFlavor;
import myjava.awt.datatransfer.Transferable;
import myjava.awt.datatransfer.UnsupportedFlavorException;

public class DataHandler implements Transferable {
    private static final DataFlavor[] emptyFlavors = new DataFlavor[0];
    private static DataContentHandlerFactory factory;
    private CommandMap currentCommandMap;
    private DataContentHandler dataContentHandler;
    private DataSource dataSource;
    private DataContentHandler factoryDCH;
    private DataSource objDataSource;
    public  Object object;
    public  String objectMimeType;
    private DataContentHandlerFactory oldFactory;
    private final String shortType;
    private DataFlavor[] transferFlavors = emptyFlavors;
    public DataHandler(final DataSource dataSource2) {
        objDataSource = null;
        object = null;
        objectMimeType = null;
        currentCommandMap = null;
        transferFlavors = emptyFlavors;
        dataContentHandler = null;
        factoryDCH = null;
        shortType = null;
        dataSource = dataSource2;
        oldFactory = DataHandler.factory;
    }
    public DataHandler(final Object obj, final String str) {
        dataSource = null;
        objDataSource = null;
        currentCommandMap = null;
        transferFlavors = DataHandler.emptyFlavors;
        dataContentHandler = null;
        factoryDCH = null;
        shortType = null;
        object = obj;
        objectMimeType = str;
        oldFactory = DataHandler.factory;
    }
    public DataHandler(final URL url) {
        dataSource = null;
        objDataSource = null;
        object = null;
        objectMimeType = null;
        currentCommandMap = null;
        transferFlavors = DataHandler.emptyFlavors;
        dataContentHandler = null;
        factoryDCH = null;
        oldFactory = null;
        shortType = null;
        dataSource = new URLDataSource(url);
        oldFactory = DataHandler.factory;
    }
    private synchronized CommandMap getCommandMap() {
        final CommandMap commandMap = currentCommandMap;
        if (null != commandMap) {
            return commandMap;
        }
        return CommandMap.getDefaultCommandMap();
    }
    public DataSource getDataSource() {
        final DataSource dataSource2 = dataSource;
        if (null != dataSource2) {
            return dataSource2;
        }
        if (null == this.objDataSource) {
            objDataSource = new DataHandlerDataSource(this);
        }
        return objDataSource;
    }
    public String getName() {
        final DataSource dataSource2 = dataSource;
        if (null != dataSource2) {
            return dataSource2.getName();
        }
        return null;
    }
    public String getContentType() {
        final DataSource dataSource2 = dataSource;
        if (null != dataSource2) {
            return dataSource2.getContentType();
        }
        return objectMimeType;
    }
    public InputStream getInputStream() throws IOException {
        final DataSource dataSource2 = dataSource;
        if (null != dataSource2) {
            return dataSource2.getInputStream();
        }
        DataContentHandler dataContentHandler2 = this.getDataContentHandler();
        if (null == dataContentHandler2) {
            throw new UnsupportedDataTypeException("no DCH for MIME type " + this.getBaseType());
        } else if (!(dataContentHandler2 instanceof ObjectDataContentHandler) || null != ((ObjectDataContentHandler) dataContentHandler2).getDCH()) {
            PipedOutputStream pipedOutputStream = new PipedOutputStream();
            final PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
            new Thread(new Runnable() {
                /*  WARNING: Missing exception handler attribute for start block: B:2:0x0013 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r4 = this;
                        javax.activation.DataContentHandler r0 = r0     // Catch:{ IOException -> 0x0013, all -> 0x0019 }
                        javax.activation.DataHandler r1 = javax.activation.DataHandler.this     // Catch:{ IOException -> 0x0013, all -> 0x0019 }
                        java.lang.Object r1 = r1.object     // Catch:{ IOException -> 0x0013, all -> 0x0019 }
                        javax.activation.DataHandler r2 = javax.activation.DataHandler.this     // Catch:{ IOException -> 0x0013, all -> 0x0019 }
                        java.lang.String r2 = r2.objectMimeType     // Catch:{ IOException -> 0x0013, all -> 0x0019 }
                        java.io.PipedOutputStream r3 = r1     // Catch:{ IOException -> 0x0013, all -> 0x0019 }
                        r0.writeTo(r1, r2, r3)     // Catch:{ IOException -> 0x0013, all -> 0x0019 }
                    L_0x0013:
                        java.io.PipedOutputStream r0 = r1     // Catch:{ IOException -> 0x0020 }
                        r0.close()     // Catch:{ IOException -> 0x0020 }
                        goto L_0x0020
                    L_0x0019:
                        r0 = move-exception
                        java.io.PipedOutputStream r1 = r1     // Catch:{ IOException -> 0x001f }
                        r1.close()     // Catch:{ IOException -> 0x001f }
                    L_0x001f:
                        throw r0
                    L_0x0020:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: javax.activation.DataHandler.AnonymousClass1.run():void");
                }
            }, "DataHandler.getInputStream").start();
            return pipedInputStream;
        } else {
            throw new UnsupportedDataTypeException("no object DCH for MIME type " + this.getBaseType());
        }
    }
    public void writeTo(final OutputStream outputStream) throws IOException {
        final DataSource dataSource2 = dataSource;
        if (null != dataSource2) {
            final byte[] bArr = new byte[8192];
            final InputStream inputStream = dataSource2.getInputStream();
            while (true) {
                try {
                    final int read = inputStream.read(bArr);
                    if (0 < read) {
                        outputStream.write(bArr, 0, read);
                    } else {
                        return;
                    }
                } finally {
                    inputStream.close();
                }
            }
        } else {
            this.getDataContentHandler().writeTo(object, objectMimeType, outputStream);
        }
    }
    public OutputStream getOutputStream() throws IOException {
        final DataSource dataSource2 = dataSource;
        if (null != dataSource2) {
            return dataSource2.getOutputStream();
        }
        return null;
    }
    public synchronized DataFlavor[] getTransferDataFlavors() {
        try {
            if (DataHandler.factory != oldFactory) {
                transferFlavors = DataHandler.emptyFlavors;
            }
            if (transferFlavors == DataHandler.emptyFlavors) {
                transferFlavors = this.getDataContentHandler().getTransferDataFlavors();
            }
        } catch (final Throwable th) {
            while (true) {
                throw th;
            }
        }
        return transferFlavors;
    }
    public boolean isDataFlavorSupported(final DataFlavor dataFlavor) {
        final DataFlavor[] transferDataFlavors = this.getTransferDataFlavors();
        for (final DataFlavor equals : transferDataFlavors) {
            if (equals.equals(dataFlavor)) {
                return true;
            }
        }
        return false;
    }
    public Object getTransferData(final DataFlavor dataFlavor) throws UnsupportedFlavorException, IOException {
        return this.getDataContentHandler().getTransferData(dataFlavor, dataSource);
    }
    public synchronized void setCommandMap(final CommandMap commandMap) {
        if (commandMap != currentCommandMap || null == commandMap) {
            transferFlavors = DataHandler.emptyFlavors;
            dataContentHandler = null;
            currentCommandMap = commandMap;
        }
    }
    public CommandInfo[] getPreferredCommands() {
        if (null != this.dataSource) {
            return this.getCommandMap().getPreferredCommands(this.getBaseType(), dataSource);
        }
        return this.getCommandMap().getPreferredCommands(this.getBaseType());
    }
    public CommandInfo[] getAllCommands() {
        if (null != this.dataSource) {
            return this.getCommandMap().getAllCommands(this.getBaseType(), dataSource);
        }
        return this.getCommandMap().getAllCommands(this.getBaseType());
    }
    public CommandInfo getCommand(final String str) {
        if (null != this.dataSource) {
            return this.getCommandMap().getCommand(this.getBaseType(), str, dataSource);
        }
        return this.getCommandMap().getCommand(this.getBaseType(), str);
    }
    public Object getContent() throws IOException {
        final Object obj = object;
        if (null != obj) {
            return obj;
        }
        return this.getDataContentHandler().getContent(this.getDataSource());
    }
    public Object getBean(final CommandInfo commandInfo) {
        try {
            ClassLoader contextClassLoader = SecuritySupport.getContextClassLoader();
            if (null == contextClassLoader) {
                contextClassLoader = this.getClass().getClassLoader();
            }
            return commandInfo.getCommandObject(this, contextClassLoader);
        } catch (final IOException | ClassNotFoundException unused) {
            return null;
        }
    }
    private synchronized DataContentHandler getDataContentHandler() {
        final DataContentHandlerFactory dataContentHandlerFactory;
        try {
            final DataContentHandlerFactory dataContentHandlerFactory2 = DataHandler.factory;
            if (dataContentHandlerFactory2 != oldFactory) {
                oldFactory = dataContentHandlerFactory2;
                factoryDCH = null;
                dataContentHandler = null;
                transferFlavors = DataHandler.emptyFlavors;
            }
            final DataContentHandler dataContentHandler2 = dataContentHandler;
            if (null != dataContentHandler2) {
                return dataContentHandler2;
            }
            final String baseType = this.getBaseType();
            if (null == this.factoryDCH && null != (dataContentHandlerFactory = factory)) {
                factoryDCH = dataContentHandlerFactory.createDataContentHandler(baseType);
            }
            final DataContentHandler dataContentHandler3 = factoryDCH;
            if (null != dataContentHandler3) {
                dataContentHandler = dataContentHandler3;
            }
            if (null == this.dataContentHandler) {
                if (null != this.dataSource) {
                    dataContentHandler = this.getCommandMap().createDataContentHandler(baseType, dataSource);
                } else {
                    dataContentHandler = this.getCommandMap().createDataContentHandler(baseType);
                }
            }
            final DataSource dataSource2 = dataSource;
            if (null != dataSource2) {
                dataContentHandler = new DataSourceDataContentHandler(dataContentHandler, dataSource2);
            } else {
                dataContentHandler = new ObjectDataContentHandler(dataContentHandler, object, objectMimeType);
            }
            return dataContentHandler;
        } catch (final Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    private synchronized String getBaseType() {
        throw new UnsupportedOperationException("Method not decompiled: javax.activation.DataHandler.getBaseType():java.lang.String");
    }
    public static synchronized void setDataContentHandlerFactory(final DataContentHandlerFactory dataContentHandlerFactory) {
        synchronized (DataHandler.class) {
            if (null == factory) {
                final SecurityManager securityManager = System.getSecurityManager();
                if (null != securityManager) {
                    try {
                        securityManager.checkSetFactory();
                    } catch (final SecurityException e2) {
                        if (DataHandler.class.getClassLoader() != dataContentHandlerFactory.getClass().getClassLoader()) {
                            throw e2;
                        }
                    }
                }
                DataHandler.factory = dataContentHandlerFactory;
            } else {
                throw new Error("DataContentHandlerFactory already defined");
            }
        }
    }
}
