package com.google.zxing.client.android;

import android.content.Intent;
import com.google.zxing.BarcodeFormat;

import java.util.*;
import java.util.regex.Pattern;

public class DecodeFormatManager {
    static final Set<BarcodeFormat> AZTEC_FORMATS;
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    static final Set<BarcodeFormat> DATA_MATRIX_FORMATS;
    private static final Map<String, Set<BarcodeFormat>> FORMATS_FOR_MODE;
    static final Set<BarcodeFormat> INDUSTRIAL_FORMATS;
    private static final Set<BarcodeFormat> ONE_D_FORMATS;
    static final Set<BarcodeFormat> PDF417_FORMATS;
    static final Set<BarcodeFormat> PRODUCT_FORMATS;
    static final Set<BarcodeFormat> QR_CODE_FORMATS;
    static {
        final EnumSet of = EnumSet.of(BarcodeFormat.QR_CODE);
        QR_CODE_FORMATS = of;
        final EnumSet of2 = EnumSet.of(BarcodeFormat.DATA_MATRIX);
        DATA_MATRIX_FORMATS = of2;
        final EnumSet of3 = EnumSet.of(BarcodeFormat.AZTEC);
        AZTEC_FORMATS = of3;
        final EnumSet of4 = EnumSet.of(BarcodeFormat.PDF_417);
        PDF417_FORMATS = of4;
        final EnumSet of5 = EnumSet.of(BarcodeFormat.UPC_A, BarcodeFormat.UPC_E, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8, BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED);
        PRODUCT_FORMATS = of5;
        final EnumSet of6 = EnumSet.of(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.CODABAR);
        INDUSTRIAL_FORMATS = of6;
        final EnumSet copyOf = EnumSet.copyOf(of5);
        ONE_D_FORMATS = copyOf;
        copyOf.addAll(of6);
        final HashMap hashMap = new HashMap();
        FORMATS_FOR_MODE = hashMap;
        hashMap.put("ONE_D_MODE", copyOf);
        hashMap.put("PRODUCT_MODE", of5);
        hashMap.put("QR_CODE_MODE", of);
        hashMap.put("DATA_MATRIX_MODE", of2);
        hashMap.put("AZTEC_MODE", of3);
        hashMap.put("PDF417_MODE", of4);
    }
    public static Set<BarcodeFormat> parseDecodeFormats(final Intent intent) {
        final String stringExtra = intent.getStringExtra("SCAN_FORMATS");
        return DecodeFormatManager.parseDecodeFormats(null != stringExtra ? Arrays.asList(DecodeFormatManager.COMMA_PATTERN.split(stringExtra)) : null, intent.getStringExtra("SCAN_MODE"));
    }
    private static <E extends Enum<E>> Set<BarcodeFormat> parseDecodeFormats(final Iterable<String> iterable, final String str) {
        if (null != iterable) {
            final EnumSet<E> noneOf = (EnumSet<E>) EnumSet.noneOf(BarcodeFormat.class);
            try {
                for (final String valueOf : iterable) {
                    noneOf.add((E) BarcodeFormat.valueOf(valueOf));
                }
                return (Set<BarcodeFormat>) noneOf;
            } catch (final IllegalArgumentException unused) {
            }
        }
        if (null != str) {
            return DecodeFormatManager.FORMATS_FOR_MODE.get(str);
        }
        return null;
    }
}
