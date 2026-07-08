package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class UUIDDeserializer extends FromStringDeserializer<UUID> {
    static final int[] HEX_DIGITS;
    private static final long serialVersionUID = 1;

    static {
        final int[] iArr = new int[127];
        HEX_DIGITS = iArr;
        Arrays.fill(iArr, -1);
        for (int i2 = 0; 10 > i2; i2++) {
            UUIDDeserializer.HEX_DIGITS[i2 + 48] = i2;
        }
        for (int i3 = 0; 6 > i3; i3++) {
            final int[] iArr2 = UUIDDeserializer.HEX_DIGITS;
            final int i4 = i3 + 10;
            iArr2[i3 + 97] = i4;
            iArr2[i3 + 65] = i4;
        }
    }

    public UUIDDeserializer() {
        super(UUID.class);
    }
    public Object getEmptyValue(final DeserializationContext deserializationContext) {
        return new UUID(0L, 0L);
    }

    protected UUID _deserialize(final String str, final DeserializationContext deserializationContext) throws IOException {
        if (36 != str.length()) {
            if (24 == str.length()) {
                return this._fromBytes(Base64Variants.getDefaultVariant().decode(str), deserializationContext);
            }
            return this._badFormat(str, deserializationContext);
        }
        if ('-' != str.charAt(8) || '-' != str.charAt(13) || '-' != str.charAt(18) || '-' != str.charAt(23)) {
            this._badFormat(str, deserializationContext);
        }
        return new UUID(((long) this.intFromChars(str, 0, deserializationContext) << 32) + (((long) this.shortFromChars(str, 9, deserializationContext) << 16) | this.shortFromChars(str, 14, deserializationContext)), (((long) this.intFromChars(str, 28, deserializationContext) << 32) >>> 32) | ((this.shortFromChars(str, 24, deserializationContext) | ((long) this.shortFromChars(str, 19, deserializationContext) << 16)) << 32));
    }
    protected UUID _deserializeEmbedded(final Object obj, final DeserializationContext deserializationContext) throws IOException {
        if (obj instanceof byte[]) {
            return this._fromBytes((byte[]) obj, deserializationContext);
        }
        return super._deserializeEmbedded(obj, deserializationContext);
    }

    private UUID _badFormat(final String str, final DeserializationContext deserializationContext) throws IOException {
        return (UUID) deserializationContext.handleWeirdStringValue(this.handledType(), str, "UUID has to be represented by standard 36-char representation", new Object[0]);
    }

    int intFromChars(final String str, final int i2, final DeserializationContext deserializationContext) throws JsonMappingException {
        return (this.byteFromChars(str, i2, deserializationContext) << 24) + (this.byteFromChars(str, i2 + 2, deserializationContext) << 16) + (this.byteFromChars(str, i2 + 4, deserializationContext) << 8) + this.byteFromChars(str, i2 + 6, deserializationContext);
    }

    int shortFromChars(final String str, final int i2, final DeserializationContext deserializationContext) throws JsonMappingException {
        return (this.byteFromChars(str, i2, deserializationContext) << 8) + this.byteFromChars(str, i2 + 2, deserializationContext);
    }

    int byteFromChars(final String str, final int i2, final DeserializationContext deserializationContext) throws JsonMappingException {
        final char cCharAt = str.charAt(i2);
        final int i3 = i2 + 1;
        final char cCharAt2 = str.charAt(i3);
        if ('\u007f' >= cCharAt && '\u007f' >= cCharAt2) {
            final int[] iArr = UUIDDeserializer.HEX_DIGITS;
            final int i4 = iArr[cCharAt2] | (iArr[cCharAt] << 4);
            if (0 <= i4) {
                return i4;
            }
        }
        if ('\u007f' < cCharAt || 0 > HEX_DIGITS[cCharAt]) {
            return this._badChar(str, i2, deserializationContext, cCharAt);
        }
        return this._badChar(str, i3, deserializationContext, cCharAt2);
    }

    int _badChar(final String str, final int i2, final DeserializationContext deserializationContext, final char c2) throws JsonMappingException {
        throw deserializationContext.weirdStringException(str, this.handledType(), String.format("Non-hex character '%c' (value 0x%s), not valid for UUID String", Character.valueOf(c2), Integer.toHexString(c2)));
    }

    private UUID _fromBytes(final byte[] bArr, final DeserializationContext deserializationContext) throws JsonMappingException {
        if (16 != bArr.length) {
            throw InvalidFormatException.from(deserializationContext.getParser(), "Can only construct UUIDs from byte[16]; got " + bArr.length + " bytes", bArr, this.handledType());
        }
        return new UUID(UUIDDeserializer._long(bArr, 0), UUIDDeserializer._long(bArr, 8));
    }

    private static long _long(final byte[] bArr, final int i2) {
        return (((long) UUIDDeserializer._int(bArr, i2 + 4) << 32) >>> 32) | ((long) UUIDDeserializer._int(bArr, i2) << 32);
    }

    private static int _int(final byte[] bArr, final int i2) {
        return (bArr[i2 + 3] & 255) | (bArr[i2] << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
    }
}
