package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class MultiFormatUPCEANReader extends OneDReader {
    private final UPCEANReader[] readers;

    public MultiFormatUPCEANReader(final Map<DecodeHintType, ?> map) {
        final Collection collection;
        if (null == map) {
            collection = null;
        } else {
            collection = (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        }
        final ArrayList arrayList = new ArrayList();
        if (null != collection) {
            if (collection.contains(BarcodeFormat.EAN_13)) {
                arrayList.add(new EAN13Reader());
            } else if (collection.contains(BarcodeFormat.UPC_A)) {
                arrayList.add(new UPCAReader());
            }
            if (collection.contains(BarcodeFormat.EAN_8)) {
                arrayList.add(new EAN8Reader());
            }
            if (collection.contains(BarcodeFormat.UPC_E)) {
                arrayList.add(new UPCEReader());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new EAN13Reader());
            arrayList.add(new EAN8Reader());
            arrayList.add(new UPCEReader());
        }
        readers = (UPCEANReader[]) arrayList.toArray(new UPCEANReader[arrayList.size()]);
    }

    public Result decodeRow(final int i2, final BitArray bitArray, final Map<DecodeHintType, ?> map) throws NotFoundException {
        Collection collection;
        final int[] findStartGuardPattern = UPCEANReader.findStartGuardPattern(bitArray);
        final UPCEANReader[] uPCEANReaderArr = readers;
        boolean z = false;
        int i3 = 0;
        while (i3 < uPCEANReaderArr.length) {
            try {
                final Result decodeRow = uPCEANReaderArr[i3].decodeRow(i2, bitArray, findStartGuardPattern, map);
                final boolean z2 = BarcodeFormat.EAN_13 == decodeRow.getBarcodeFormat() && '0' == decodeRow.getText().charAt(0);
                if (null == map) {
                    collection = null;
                } else {
                    collection = (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
                }
                if (null == collection || collection.contains(BarcodeFormat.UPC_A)) {
                    z = true;
                }
                if (!z2 || !z) {
                    return decodeRow;
                }
                final Result result = new Result(decodeRow.getText().substring(1), decodeRow.getRawBytes(), decodeRow.getResultPoints(), BarcodeFormat.UPC_A);
                result.putAllMetadata(decodeRow.getResultMetadata());
                return result;
            } catch (final ReaderException unused) {
                i3++;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public void reset() {
        for (final UPCEANReader reset : readers) {
            reset.reset();
        }
    }
}
