package com.google.zxing.aztec;

import com.google.zxing.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class AztecWriter implements Writer {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;
}
