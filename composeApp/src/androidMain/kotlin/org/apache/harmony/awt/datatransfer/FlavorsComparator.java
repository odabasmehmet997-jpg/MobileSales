package org.apache.harmony.awt.datatransfer;

import java.awt.datatransfer.DataFlavor;
import java.util.Comparator;


public class FlavorsComparator implements Comparator<DataFlavor> {
    public int compare(DataFlavor dataFlavor, DataFlavor dataFlavor2) {
        if (!dataFlavor.isFlavorTextType() && !dataFlavor2.isFlavorTextType()) {
            return 0;
        }
        if (!dataFlavor.isFlavorTextType() && dataFlavor2.isFlavorTextType()) {
            return -1;
        }
        if ((!dataFlavor.isFlavorTextType() || dataFlavor2.isFlavorTextType()) && DataFlavor.selectBestTextFlavor(new DataFlavor[]{dataFlavor, dataFlavor2}) == dataFlavor) {
            return -1;
        }
        return 1;
    }
}
