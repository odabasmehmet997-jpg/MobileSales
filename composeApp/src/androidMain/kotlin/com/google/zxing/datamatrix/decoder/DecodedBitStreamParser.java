package com.google.zxing.datamatrix.decoder;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.fasterxml.jackson.core.JsonFactory;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

enum DecodedBitStreamParser {
    ;
    private static final char[] C40_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] C40_SHIFT2_SET_CHARS;
    private static final char[] TEXT_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] TEXT_SHIFT2_SET_CHARS;
    private static final char[] TEXT_SHIFT3_SET_CHARS = {'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', 127};

    private enum Mode {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE
    }

    static {
        final char[] cArr = {'!', JsonFactory.DEFAULT_QUOTE_CHAR, '#', '', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_'};
        C40_SHIFT2_SET_CHARS = cArr;
        TEXT_SHIFT2_SET_CHARS = cArr;
    }

    static com.google.zxing.common.DecoderResult decode(final byte[] r8) throws com.google.zxing.FormatException {
        /*
            com.google.zxing.common.BitSource r0 = new com.google.zxing.common.BitSource
            r0.<init>(r8)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r2 = 100
            r1.<init>(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = 0
            r2.<init>(r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 1
            r3.<init>(r4)
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParserMode r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE
        L_0x001a:
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParserMode r6 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE
            if (r5 != r6) goto L_0x0023
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParserMode r5 = decodeAsciiSegment(r0, r1, r2)
            goto L_0x0052
        L_0x0023:
            int[] r7 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.AnonymousClass1.SwitchMapcomgooglezxingdatamatrixdecoderDecodedBitStreamParserMode
            int r5 = r5.ordinal()
            r5 = r7[r5]
            if (r5 == r4) goto L_0x004e
            r7 = 2
            if (r5 == r7) goto L_0x004a
            r7 = 3
            if (r5 == r7) goto L_0x0046
            r7 = 4
            if (r5 == r7) goto L_0x0042
            r7 = 5
            if (r5 != r7) goto L_0x003d
            decodeBase256Segment(r0, r1, r3)
            goto L_0x0051
        L_0x003d:
            com.google.zxing.FormatException r8 = com.google.zxing.FormatException.getFormatInstance()
            throw r8
        L_0x0042:
            decodeEdifactSegment(r0, r1)
            goto L_0x0051
        L_0x0046:
            decodeAnsiX12Segment(r0, r1)
            goto L_0x0051
        L_0x004a:
            decodeTextSegment(r0, r1)
            goto L_0x0051
        L_0x004e:
            decodeC40Segment(r0, r1)
        L_0x0051:
            r5 = r6
        L_0x0052:
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParserMode r6 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.PAD_ENCODE
            if (r5 == r6) goto L_0x005c
            int r6 = r0.available()
            if (r6 > 0) goto L_0x001a
        L_0x005c:
            int r0 = r2.length()
            if (r0 <= 0) goto L_0x0065
            r1.append(r2)
        L_0x0065:
            com.google.zxing.common.DecoderResult r0 = new com.google.zxing.common.DecoderResult
            java.lang.String r1 = r1.toString()
            boolean r2 = r3.isEmpty()
            r4 = 0
            if (r2 == 0) goto L_0x0073
            r3 = r4
        L_0x0073:
            r0.<init>(r8, r1, r3, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decode(byte[]):com.google.zxing.common.DecoderResult");
    }

    enum AnonymousClass1 {
        ;
        static final int[] SwitchMapcomgooglezxingdatamatrixdecoderDecodedBitStreamParserMode;

        static {

            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.AnonymousClass1.<clinit>():void");
        }
    }

    private static Mode decodeAsciiSegment(final BitSource bitSource, final StringBuilder sb, final StringBuilder sb2) throws FormatException {
        boolean z = false;
        do {
            int readBits = bitSource.readBits(8);
            if (0 == readBits) {
                throw FormatException.getFormatInstance();
            } else if (128 >= readBits) {
                if (z) {
                    readBits += 128;
                }
                sb.append((char) (readBits - 1));
                return Mode.ASCII_ENCODE;
            } else if (129 == readBits) {
                return Mode.PAD_ENCODE;
            } else {
                if (229 >= readBits) {
                    final int i2 = readBits - 130;
                    if (10 > i2) {
                        sb.append('0');
                    }
                    sb.append(i2);
                } else if (230 == readBits) {
                    return Mode.C40_ENCODE;
                } else {
                    if (231 == readBits) {
                        return Mode.BASE256_ENCODE;
                    }
                    if (232 == readBits) {
                        sb.append(29);
                    } else if (!(233 == readBits || 234 == readBits)) {
                        if (235 == readBits) {
                            z = true;
                        } else if (236 == readBits) {
                            sb.append("[)>\u001e05\u001d");
                            sb2.insert(0, "\u001e\u0004");
                        } else if (237 == readBits) {
                            sb.append("[)>\u001e06\u001d");
                            sb2.insert(0, "\u001e\u0004");
                        } else if (238 == readBits) {
                            return Mode.ANSIX12_ENCODE;
                        } else {
                            if (239 == readBits) {
                                return Mode.TEXT_ENCODE;
                            }
                            if (240 == readBits) {
                                return Mode.EDIFACT_ENCODE;
                            }
                            if (!(241 == readBits || 242 > readBits || (254 == readBits && 0 == bitSource.available()))) {
                                throw FormatException.getFormatInstance();
                            }
                        }
                    }
                }
            }
        } while (0 < bitSource.available());
        return Mode.ASCII_ENCODE;
    }

    private static void decodeC40Segment(final BitSource bitSource, final StringBuilder sb) throws FormatException {
        int readBits;
        final int[] iArr = new int[3];
        boolean z = false;
        int i2 = 0;
        while (8 != bitSource.available() && 254 != (readBits = bitSource.readBits(8))) {
            DecodedBitStreamParser.parseTwoBytes(readBits, bitSource.readBits(8), iArr);
            for (int i3 = 0; 3 > i3; i3++) {
                final int i4 = iArr[i3];
                if (0 != i2) {
                    if (1 != i2) {
                        if (2 == i2) {
                            final char[] cArr = DecodedBitStreamParser.C40_SHIFT2_SET_CHARS;
                            if (i4 < cArr.length) {
                                final char c2 = cArr[i4];
                                if (z) {
                                    sb.append((char) (c2 + 128));
                                } else {
                                    sb.append(c2);
                                }
                            } else if (27 == i4) {
                                sb.append(29);
                            } else if (30 == i4) {
                                z = true;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                            i2 = 0;
                        } else if (3 != i2) {
                            throw FormatException.getFormatInstance();
                        } else if (z) {
                            sb.append((char) (i4 + 224));
                        } else {
                            sb.append((char) (i4 + 96));
                            i2 = 0;
                        }
                    } else if (z) {
                        sb.append((char) (i4 + 128));
                    } else {
                        sb.append((char) i4);
                        i2 = 0;
                    }
                    z = false;
                    i2 = 0;
                } else if (3 > i4) {
                    i2 = i4 + 1;
                } else {
                    final char[] cArr2 = DecodedBitStreamParser.C40_BASIC_SET_CHARS;
                    if (i4 < cArr2.length) {
                        final char c3 = cArr2[i4];
                        if (z) {
                            sb.append((char) (c3 + 128));
                            z = false;
                        } else {
                            sb.append(c3);
                        }
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
            }
            if (0 >= bitSource.available()) {
                return;
            }
        }
    }

    private static void decodeTextSegment(final BitSource bitSource, final StringBuilder sb) throws FormatException {
        int readBits;
        final int[] iArr = new int[3];
        boolean z = false;
        int i2 = 0;
        while (8 != bitSource.available() && 254 != (readBits = bitSource.readBits(8))) {
            DecodedBitStreamParser.parseTwoBytes(readBits, bitSource.readBits(8), iArr);
            for (int i3 = 0; 3 > i3; i3++) {
                final int i4 = iArr[i3];
                if (0 != i2) {
                    if (1 != i2) {
                        if (2 == i2) {
                            final char[] cArr = DecodedBitStreamParser.TEXT_SHIFT2_SET_CHARS;
                            if (i4 < cArr.length) {
                                final char c2 = cArr[i4];
                                if (z) {
                                    sb.append((char) (c2 + 128));
                                } else {
                                    sb.append(c2);
                                }
                            } else if (27 == i4) {
                                sb.append(29);
                            } else if (30 == i4) {
                                z = true;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                            i2 = 0;
                        } else if (3 == i2) {
                            final char[] cArr2 = DecodedBitStreamParser.TEXT_SHIFT3_SET_CHARS;
                            if (i4 < cArr2.length) {
                                final char c3 = cArr2[i4];
                                if (z) {
                                    sb.append((char) (c3 + 128));
                                } else {
                                    sb.append(c3);
                                    i2 = 0;
                                }
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    } else if (z) {
                        sb.append((char) (i4 + 128));
                    } else {
                        sb.append((char) i4);
                        i2 = 0;
                    }
                    z = false;
                    i2 = 0;
                } else if (3 > i4) {
                    i2 = i4 + 1;
                } else {
                    final char[] cArr3 = DecodedBitStreamParser.TEXT_BASIC_SET_CHARS;
                    if (i4 < cArr3.length) {
                        final char c4 = cArr3[i4];
                        if (z) {
                            sb.append((char) (c4 + 128));
                            z = false;
                        } else {
                            sb.append(c4);
                        }
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
            }
            if (0 >= bitSource.available()) {
                return;
            }
        }
    }

    private static void decodeAnsiX12Segment(final BitSource bitSource, final StringBuilder sb) throws FormatException {
        int readBits;
        final int[] iArr = new int[3];
        while (8 != bitSource.available() && 254 != (readBits = bitSource.readBits(8))) {
            DecodedBitStreamParser.parseTwoBytes(readBits, bitSource.readBits(8), iArr);
            for (int i2 = 0; 3 > i2; i2++) {
                final int i3 = iArr[i2];
                if (0 == i3) {
                    sb.append(13);
                } else if (1 == i3) {
                    sb.append('*');
                } else if (2 == i3) {
                    sb.append('>');
                } else if (3 == i3) {
                    sb.append(' ');
                } else if (14 > i3) {
                    sb.append((char) (i3 + 44));
                } else if (40 > i3) {
                    sb.append((char) (i3 + 51));
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            if (0 >= bitSource.available()) {
                return;
            }
        }
    }

    private static void parseTwoBytes(final int i2, final int i3, final int[] iArr) {
        final int i4 = ((i2 << 8) + i3) - 1;
        final int i5 = i4 / 1600;
        iArr[0] = i5;
        final int i6 = i4 - (i5 * 1600);
        final int i7 = i6 / 40;
        iArr[1] = i7;
        iArr[2] = i6 - (i7 * 40);
    }

    private static void decodeEdifactSegment(final BitSource bitSource, final StringBuilder sb) {
        while (16 < bitSource.available()) {
            for (int i2 = 0; 4 > i2; i2++) {
                int readBits = bitSource.readBits(6);
                if (31 == readBits) {
                    final int bitOffset = 8 - bitSource.getBitOffset();
                    if (8 != bitOffset) {
                        bitSource.readBits(bitOffset);
                        return;
                    }
                    return;
                }
                if (0 == (readBits & 32)) {
                    readBits |= 64;
                }
                sb.append((char) readBits);
            }
            if (0 >= bitSource.available()) {
                return;
            }
        }
    }

    private static void decodeBase256Segment(final BitSource bitSource, final StringBuilder sb, final Collection<byte[]> collection) throws FormatException {
        final int byteOffset = bitSource.getByteOffset();
        int i2 = byteOffset + 2;
        int unrandomize255State = DecodedBitStreamParser.unrandomize255State(bitSource.readBits(8), byteOffset + 1);
        if (0 == unrandomize255State) {
            unrandomize255State = bitSource.available() / 8;
        } else if (250 <= unrandomize255State) {
            unrandomize255State = ((unrandomize255State - 249) * ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + DecodedBitStreamParser.unrandomize255State(bitSource.readBits(8), i2);
            i2 = byteOffset + 3;
        }
        if (0 <= unrandomize255State) {
            final byte[] bArr = new byte[unrandomize255State];
            int i3 = 0;
            while (i3 < unrandomize255State) {
                if (8 <= bitSource.available()) {
                    bArr[i3] = (byte) DecodedBitStreamParser.unrandomize255State(bitSource.readBits(8), i2);
                    i3++;
                    i2++;
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            collection.add(bArr);
            try {
                sb.append(new String(bArr, "ISO8859_1"));
            } catch (final UnsupportedEncodingException e2) {
                throw new IllegalStateException("Platform does not support required encoding: " + e2);
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int unrandomize255State(final int i2, final int i3) {
        final int i4 = i2 - (((i3 * 149) % 255) + 1);
        return 0 <= i4 ? i4 : i4 + 256;
    }
}
