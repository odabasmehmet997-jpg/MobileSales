package org.simpleframework.xml.stream;

import com.fasterxml.jackson.core.JsonFactory;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import java.io.BufferedWriter;
import java.io.Writer;


class Formatter {
    private final OutputBuffer buffer = new OutputBuffer();
    private final Indenter indenter;
    private Tag last;
    private final String prolog;
    private final Writer result;
    private static final char[] NAMESPACE = {'x', 'm', 'l', 'n', 's'};
    private static final char[] LESS = {'&', 'l', 't', ';'};
    private static final char[] GREATER = {'&', 'g', 't', ';'};
    private static final char[] DOUBLE = {'&', 'q', 'u', 'o', 't', ';'};
    private static final char[] SINGLE = {'&', 'a', 'p', 'o', 's', ';'};
    private static final char[] AND = {'&', 'a', 'm', 'p', ';'};
    private static final char[] OPEN = {'<', '!', '-', '-', ' '};
    private static final char[] CLOSE = {' ', '-', '-', '>'};

    private enum Tag {
        COMMENT,
        START,
        TEXT,
        END
    }

    private boolean isText(final char c2) {
        if ('\t' == c2 || '\n' == c2 || '\r' == c2 || ' ' == c2) {
            return true;
        }
        return ' ' < c2 && '~' >= c2 && '\u00f7' != c2;
    }

    public Formatter(final Writer writer, final Format format) {
        result = new BufferedWriter(writer, 1024);
        indenter = new Indenter(format);
        prolog = format.prolog();
    }

    public void writeProlog() throws Exception {
        final String str = prolog;
        if (null != str) {
            this.write(str);
            write(SqlLiteVariable._NEW_LINE);
        }
    }

    public void writeComment(final String str) throws Exception {
        final String pVar = indenter.top();
        if (Tag.START == this.last) {
            this.append('>');
        }
        if (null != pVar) {
            this.append(pVar);
            this.append(Formatter.OPEN);
            this.append(str);
            this.append(Formatter.CLOSE);
        }
        last = Tag.COMMENT;
    }

    public void writeStart(final String str, final String str2) throws Exception {
        final String push = indenter.push();
        final Tag tag = last;
        final Tag tag2 = Tag.START;
        if (tag == tag2) {
            this.append('>');
        }
        this.flush();
        this.append(push);
        this.append('<');
        if (!this.isEmpty(str2)) {
            this.append(str2);
            this.append(':');
        }
        this.append(str);
        last = tag2;
    }

    public void writeAttribute(final String str, final String str2, final String str3) throws Exception {
        if (Tag.START != this.last) {
            throw new NodeException("Start element required");
        }
        this.write(' ');
        this.write(str, str3);
        this.write('=');
        this.write(JsonFactory.DEFAULT_QUOTE_CHAR);
        this.escape(str2);
        this.write(JsonFactory.DEFAULT_QUOTE_CHAR);
    }

    public void writeNamespace(final String str, final String str2) throws Exception {
        if (Tag.START != this.last) {
            throw new NodeException("Start element required");
        }
        this.write(' ');
        this.write(Formatter.NAMESPACE);
        if (!this.isEmpty(str2)) {
            this.write(':');
            this.write(str2);
        }
        this.write('=');
        this.write(JsonFactory.DEFAULT_QUOTE_CHAR);
        this.escape(str);
        this.write(JsonFactory.DEFAULT_QUOTE_CHAR);
    }

    public void writeText(final String str) throws Exception {
        this.writeText(str, Mode.ESCAPE);
    }

    public void writeText(final String str, final Mode mode) throws Exception {
        if (Tag.START == this.last) {
            this.write('>');
        }
        if (Mode.DATA == mode) {
            this.data(str);
        } else {
            this.escape(str);
        }
        last = Tag.TEXT;
    }

    public void writeEnd(final String str, final String str2) throws Exception {
        final String pop = indenter.pop();
        final Tag tag = last;
        final Tag tag2 = Tag.START;
        if (tag == tag2) {
            this.write('/');
            this.write('>');
        } else {
            if (Tag.TEXT != tag) {
                this.write(pop);
            }
            if (last != tag2) {
                this.write('<');
                this.write('/');
                this.write(str, str2);
                this.write('>');
            }
        }
        last = Tag.END;
    }

    private void write(final char c2) throws Exception {
        buffer.write(result);
        buffer.clear();
        result.write(c2);
    }

    private void write(final char[] cArr) throws Exception {
        buffer.write(result);
        buffer.clear();
        result.write(cArr);
    }

    private void write(final String str) throws Exception {
        buffer.write(result);
        buffer.clear();
        result.write(str);
    }

    private void write(final String str, final String str2) throws Exception {
        buffer.write(result);
        buffer.clear();
        if (!this.isEmpty(str2)) {
            result.write(str2);
            result.write(58);
        }
        result.write(str);
    }

    private void append(final char c2) throws Exception {
        buffer.append(c2);
    }

    private void append(final char[] cArr) throws Exception {
        buffer.append(cArr);
    }

    private void append(final String str) throws Exception {
        buffer.append(str);
    }

    private void data(final String str) throws Exception {
        this.write("<![CDATA[");
        this.write(str);
        this.write("]]>");
    }

    private void escape(final String str) throws Exception {
        final int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            this.escape(str.charAt(i2));
        }
    }

    private void escape(final char c2) throws Exception {
        final char[] symbol = this.symbol(c2);
        if (null != symbol) {
            this.write(symbol);
        } else {
            this.write(c2);
        }
    }

    public void flush() throws Exception {
        buffer.write(result);
        buffer.clear();
        result.flush();
    }

    private String unicode(final char c2) {
        return Integer.toString(c2);
    }

    private boolean isEmpty(final String str) {
        return null == str || 0 == str.length();
    }

    private char[] symbol(final char c2) {
        if ('\"' == c2) {
            return Formatter.DOUBLE;
        }
        if ('<' == c2) {
            return Formatter.LESS;
        }
        if ('>' == c2) {
            return Formatter.GREATER;
        }
        if ('&' == c2) {
            return Formatter.AND;
        }
        if ('\'' != c2) {
            return null;
        }
        return Formatter.SINGLE;
    }
}
