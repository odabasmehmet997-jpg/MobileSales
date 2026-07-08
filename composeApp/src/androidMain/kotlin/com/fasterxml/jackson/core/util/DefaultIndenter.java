package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import java.io.IOException;

public class DefaultIndenter extends DefaultPrettyPrinter.NopIndenter {
    private static final int INDENT_LEVELS = 16;
    public static final DefaultIndenter SYSTEM_LINEFEED_INSTANCE;
    public static final String SYS_LF;
    private static final long serialVersionUID = 1;
    private final int charsPerLevel;
    private final String eol;
    private final char[] indents;
    public boolean isInline() {
        return false;
    }

    static {
        String property;
        try {
            property = System.getProperty("line.separator");
        } catch (final Throwable unused) {
            property = SqlLiteVariable._NEW_LINE;
        }
        SYS_LF = property;
        SYSTEM_LINEFEED_INSTANCE = new DefaultIndenter("  ", property);
    }

    public DefaultIndenter() {
        this("  ", DefaultIndenter.SYS_LF);
    }

    public DefaultIndenter(final String str, final String str2) {
        charsPerLevel = str.length();
        indents = new char[str.length() * 16];
        int length = 0;
        for (int i2 = 0; 16 > i2; i2++) {
            str.getChars(0, str.length(), indents, length);
            length += str.length();
        }
        eol = str2;
    }

    public DefaultIndenter withLinefeed(final String str) {
        return str.equals(eol) ? this : new DefaultIndenter(this.getIndent(), str);
    }

    public DefaultIndenter withIndent(final String str) {
        return str.equals(this.getIndent()) ? this : new DefaultIndenter(str, eol);
    }
    public void writeIndentation(final JsonGenerator jsonGenerator, final int i2) throws IOException {
        jsonGenerator.writeRaw(eol);
        if (0 >= i2) {
            return;
        }
        int length = i2 * charsPerLevel;
        while (true) {
            final char[] cArr = indents;
            if (length > cArr.length) {
                jsonGenerator.writeRaw(cArr, 0, cArr.length);
                length -= indents.length;
            } else {
                jsonGenerator.writeRaw(cArr, 0, length);
                return;
            }
        }
    }

    public String getEol() {
        return eol;
    }

    public String getIndent() {
        return new String(indents, 0, charsPerLevel);
    }
}
