package com.google.zxing;

import com.google.zxing.aztec.AztecReader;
import com.google.zxing.datamatrix.DataMatrixReader;
import com.google.zxing.maxicode.MaxiCodeReader;
import com.google.zxing.oned.MultiFormatOneDReader;
import com.google.zxing.pdf417.PDF417Reader;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class MultiFormatReader implements Reader {
    private Map<DecodeHintType, ?> hints;
    private Reader[] readers;
    public Result decode(final BinaryBitmap binaryBitmap) throws NotFoundException {
        this.setHints(null);
        return this.decodeInternal(binaryBitmap);
    }
    public Result decode(final BinaryBitmap binaryBitmap, final Map<DecodeHintType, ?> map) throws NotFoundException {
        this.setHints(map);
        return this.decodeInternal(binaryBitmap);
    }
    public Result decodeWithState(final BinaryBitmap binaryBitmap) throws NotFoundException {
        if (null == this.readers) {
            this.setHints(null);
        }
        return this.decodeInternal(binaryBitmap);
    }
    public void setHints(final Map<DecodeHintType, ?> map) {
        final Collection collection;
        hints = map;
        boolean z = false;
        final boolean z2 = null != map && map.containsKey(DecodeHintType.TRY_HARDER);
        if (null == map) {
            collection = null;
        } else {
            collection = (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        }
        final ArrayList arrayList = new ArrayList();
        if (null != collection) {
            if (collection.contains(BarcodeFormat.UPC_A) || collection.contains(BarcodeFormat.UPC_E) || collection.contains(BarcodeFormat.EAN_13) || collection.contains(BarcodeFormat.EAN_8) || collection.contains(BarcodeFormat.CODABAR) || collection.contains(BarcodeFormat.CODE_39) || collection.contains(BarcodeFormat.CODE_93) || collection.contains(BarcodeFormat.CODE_128) || collection.contains(BarcodeFormat.ITF) || collection.contains(BarcodeFormat.RSS_14) || collection.contains(BarcodeFormat.RSS_EXPANDED)) {
                z = true;
            }
            if (z && !z2) {
                arrayList.add(new MultiFormatOneDReader(map));
            }
            if (collection.contains(BarcodeFormat.QR_CODE)) {
                arrayList.add(new QRCodeReader());
            }
            if (collection.contains(BarcodeFormat.DATA_MATRIX)) {
                arrayList.add(new DataMatrixReader());
            }
            if (collection.contains(BarcodeFormat.AZTEC)) {
                arrayList.add(new AztecReader());
            }
            if (collection.contains(BarcodeFormat.PDF_417)) {
                arrayList.add(new PDF417Reader());
            }
            if (collection.contains(BarcodeFormat.MAXICODE)) {
                arrayList.add(new MaxiCodeReader());
            }
            if (z && z2) {
                arrayList.add(new MultiFormatOneDReader(map));
            }
        }
        if (arrayList.isEmpty()) {
            if (!z2) {
                arrayList.add(new MultiFormatOneDReader(map));
            }
            arrayList.add(new QRCodeReader());
            arrayList.add(new DataMatrixReader());
            arrayList.add(new AztecReader());
            arrayList.add(new PDF417Reader());
            arrayList.add(new MaxiCodeReader());
            if (z2) {
                arrayList.add(new MultiFormatOneDReader(map));
            }
        }
        readers = (Reader[]) arrayList.toArray(new Reader[arrayList.size()]);
    }
    public void reset() {
        final Reader[] readerArr = readers;
        if (null != readerArr) {
            for (final Reader reset : readerArr) {
                reset.reset();
            }
        }
    }
    private Result decodeInternal(final BinaryBitmap binaryBitmap) throws NotFoundException {
        final Reader[] readerArr = readers;
        if (null != readerArr) {
            final int length = readerArr.length;
            int i2 = 0;
            while (i2 < length) {
                try {
                    return readerArr[i2].decode(binaryBitmap, hints);
                } catch (final ReaderException unused) {
                    i2++;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
