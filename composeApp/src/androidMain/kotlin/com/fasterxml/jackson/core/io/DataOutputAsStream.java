package com.fasterxml.jackson.core.io;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;

public class DataOutputAsStream extends OutputStream {
    protected final DataOutput _output;
    public DataOutputAsStream(final DataOutput dataOutput) {
        _output = dataOutput;
    }
    public void write(final int i2) throws IOException {
        _output.write(i2);
    }
    public void write(final byte[] bArr) throws IOException {
        _output.write(bArr, 0, bArr.length);
    }
    public void write(final byte[] bArr, final int i2, final int i3) throws IOException {
        _output.write(bArr, i2, i3);
    }
}
