package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.RSS14Reader;
import com.google.zxing.oned.rss.expanded.RSSExpandedReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class MultiFormatOneDReader extends OneDReader {
    private final OneDReader[] readers;

    public MultiFormatOneDReader(final Map<DecodeHintType, ?> map) {
        final Collection collection;
        if (null == map) {
            collection = null;
        } else {
            collection = (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        }
        final boolean z = null != map && null != map.get(DecodeHintType.ASSUME_CODE_39_CHECK_DIGIT);
        final ArrayList arrayList = new ArrayList();
        if (null != collection) {
            if (collection.contains(BarcodeFormat.EAN_13) || collection.contains(BarcodeFormat.UPC_A) || collection.contains(BarcodeFormat.EAN_8) || collection.contains(BarcodeFormat.UPC_E)) {
                arrayList.add(new MultiFormatUPCEANReader(map));
            }
            if (collection.contains(BarcodeFormat.CODE_39)) {
                arrayList.add(new Code39Reader(z));
            }
            if (collection.contains(BarcodeFormat.CODE_93)) {
                arrayList.add(new Code93Reader());
            }
            if (collection.contains(BarcodeFormat.CODE_128)) {
                arrayList.add(new Code128Reader());
            }
            if (collection.contains(BarcodeFormat.ITF)) {
                arrayList.add(new ITFReader());
            }
            if (collection.contains(BarcodeFormat.CODABAR)) {
                arrayList.add(new CodaBarReader());
            }
            if (collection.contains(BarcodeFormat.RSS_14)) {
                arrayList.add(new RSS14Reader());
            }
            if (collection.contains(BarcodeFormat.RSS_EXPANDED)) {
                arrayList.add(new RSSExpandedReader());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new MultiFormatUPCEANReader(map));
            arrayList.add(new Code39Reader());
            arrayList.add(new CodaBarReader());
            arrayList.add(new Code93Reader());
            arrayList.add(new Code128Reader());
            arrayList.add(new ITFReader());
            arrayList.add(new RSS14Reader());
            arrayList.add(new RSSExpandedReader());
        }
        readers = (OneDReader[]) arrayList.toArray(new OneDReader[arrayList.size()]);
    }

    public Result decodeRow(final int i2, final BitArray bitArray, final Map<DecodeHintType, ?> map) throws NotFoundException {
        final OneDReader[] oneDReaderArr = readers;
        int i3 = 0;
        while (i3 < oneDReaderArr.length) {
            try {
                return oneDReaderArr[i3].decodeRow(i2, bitArray, map);
            } catch (final ReaderException unused) {
                i3++;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public void reset() {
        for (final OneDReader reset : readers) {
            reset.reset();
        }
    }
}
