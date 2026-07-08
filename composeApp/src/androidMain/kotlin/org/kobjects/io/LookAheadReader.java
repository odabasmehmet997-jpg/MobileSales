package org.kobjects.io;

import okhttp3.internal.http2.Http2;

import java.io.IOException;
import java.io.Reader;

public class LookAheadReader extends Reader {
    char[] buf;
    int bufPos;
    int bufValid;
    Reader reader;
    public LookAheadReader(Reader reader) {
        this.buf = new char[1000000 < Runtime.getRuntime().freeMemory() ? Http2.INITIAL_MAX_FRAME_SIZE : 128];
        this.bufPos = 0;
        this.bufValid = 0;
        this.reader = reader;
    }
    public int read(char[] cArr, int i2, int i3) throws IOException {
        if (0 == bufValid && -1 == this.peek(0)) {
            return -1;
        }
        int i4 = this.bufValid;
        if (i3 > i4) {
            i3 = i4;
        }
        char[] cArr2 = this.buf;
        int length = cArr2.length;
        int i5 = this.bufPos;
        if (i3 > length - i5) {
            i3 = cArr2.length - i5;
        }
        System.arraycopy(cArr2, i5, cArr, i2, i3);
        this.bufValid -= i3;
        int i6 = this.bufPos + i3;
        this.bufPos = i6;
        char[] cArr3 = this.buf;
        if (i6 > cArr3.length) {
            this.bufPos = i6 - cArr3.length;
        }
        return i3;
    }
    public String readTo(String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (-1 != this.peek(0) && -1 == str.indexOf((char) this.peek(0))) {
            stringBuffer.append((char) read());
        }
        return stringBuffer.toString();
    }
    public String readTo(char c) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (-1 != this.peek(0) && peek(0) != c) {
            stringBuffer.append((char) read());
        }
        return stringBuffer.toString();
    }
    public void close() throws IOException {
        this.reader.close();
    }
    public int read() throws IOException {
        int peek = peek(0);
        if (-1 != peek) {
            int i2 = this.bufPos + 1;
            this.bufPos = i2;
            if (i2 == this.buf.length) {
                this.bufPos = 0;
            }
            this.bufValid--;
        }
        return peek;
    }
    public int peek(int i2) throws IOException {
        if (127 < i2) {
            throw new RuntimeException("peek > 127 not supported!");
        }
        while (true) {
            int i3 = this.bufValid;
            if (i2 >= i3) {
                int i4 = this.bufPos + i3;
                char[] cArr = this.buf;
                int length = i4 % cArr.length;
                int read = this.reader.read(this.buf, length, Math.min(cArr.length - length, cArr.length - i3));
                if (-1 == read) {
                    return -1;
                }
                this.bufValid += read;
            } else {
                char[] cArr2 = this.buf;
                return cArr2[this.bufPos + (i2 % cArr2.length)];
            }
        }
    }
    public String readLine() throws IOException {
        if (-1 == this.peek(0)) {
            return null;
        }
        String readTo = readTo("\r\n");
        if (13 == this.read() && 10 == this.peek(0)) {
            read();
        }
        return readTo;
    }
    public String readWhile(String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (-1 != this.peek(0) && -1 != str.indexOf((char) this.peek(0))) {
            stringBuffer.append((char) read());
        }
        return stringBuffer.toString();
    }
    public void skip(String str) throws IOException {
        while (-1 != this.peek(0) && -1 != str.indexOf((char) this.peek(0))) {
            read();
        }
    }
}
