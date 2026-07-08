package com.fasterxml.jackson.core.io;

import java.io.*;

public abstract class InputDecorator implements Serializable {
    private static final long serialVersionUID = 1;
    public abstract InputStream decorate(IOContext iOContext, InputStream inputStream) throws IOException;
    public abstract InputStream decorate(IOContext iOContext, byte[] bArr, int i2, int i3) throws IOException;
    public abstract Reader decorate(IOContext iOContext, Reader reader) throws IOException;
   public DataInput decorate(final IOContext iOContext, final DataInput dataInput) throws IOException {
        throw new UnsupportedOperationException();
    }
}
