package org.apache.harmony.awt.datatransfer;

import androidx.core.view.MotionEventCompat;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.SystemFlavorMap;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class DataSource implements DataProvider {
    protected final Transferable contents;
    private DataFlavor[] flavors;
    private List<String> nativeFormats;

    public DataSource(Transferable transferable) {
        this.contents = transferable;
    }

    private boolean isHtmlFlavor(DataFlavor dataFlavor) {
        return "html".equalsIgnoreCase(dataFlavor.getSubType());
    }

    protected DataFlavor[] getDataFlavors() {
        if (this.flavors == null) {
            this.flavors = this.contents.getTransferDataFlavors();
        }
        return this.flavors;
    }

    @Override // org.apache.harmony.awt.datatransfer.DataProvider
    public String[] getNativeFormats() {
        return getNativeFormatsList().toArray(new String[0]);
    }

    public List<String> getNativeFormatsList() {
        if (this.nativeFormats == null) {
            this.nativeFormats = getNativesForFlavors(getDataFlavors());
        }
        return this.nativeFormats;
    }

    private static List<String> getNativesForFlavors(DataFlavor[] dataFlavorArr) {
        ArrayList arrayList = new ArrayList();
        SystemFlavorMap defaultFlavorMap = SystemFlavorMap.getDefaultFlavorMap();
        for (DataFlavor dataFlavor : dataFlavorArr) {
            for (String str : defaultFlavorMap.getNativesForFlavor(dataFlavor)) {
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    private String getTextFromReader(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[1024];
        while (true) {
            int read = reader.read(cArr);
            if (read <= 0) {
                return sb.toString();
            }
            sb.append(cArr, 0, read);
        }
    }

    private String getText(boolean z) {
        DataFlavor[] transferDataFlavors = this.contents.getTransferDataFlavors();
        for (DataFlavor dataFlavor : transferDataFlavors) {
            if (dataFlavor.isFlavorTextType() && (!z || isHtmlFlavor(dataFlavor))) {
                try {
                    if (String.class.isAssignableFrom(dataFlavor.getRepresentationClass())) {
                        return (String) this.contents.getTransferData(dataFlavor);
                    }
                    return getTextFromReader(dataFlavor.getReaderForText(this.contents));
                } catch (Exception unused) {
                }
            }
        }
        return null;
    }

    @Override // org.apache.harmony.awt.datatransfer.DataProvider
    public String getText() {
        return getText(false);
    }

    @Override // org.apache.harmony.awt.datatransfer.DataProvider
    public String[] getFileList() {
        try {
            List list = (List) this.contents.getTransferData(DataFlavor.javaFileListFlavor);
            return (String[]) list.toArray(new String[list.size()]);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.apache.harmony.awt.datatransfer.DataProvider
    public String getURL() {
        try {
            try {
                try {
                    return this.contents.getTransferData(DataProvider.urlFlavor).toString();
                } catch (Exception unused) {
                    return this.contents.getTransferData(DataProvider.uriFlavor).toString();
                }
            } catch (Exception unused2) {
                return new URL(this.getText()).toString();
            }
        } catch (Exception unused3) {
            return null;
        }
    }

    @Override // org.apache.harmony.awt.datatransfer.DataProvider
    public String getHTML() {
        return getText(true);
    }

    @Override // org.apache.harmony.awt.datatransfer.DataProvider
    public RawBitmap getRawBitmap() {
        DataFlavor[] transferDataFlavors = this.contents.getTransferDataFlavors();
        for (DataFlavor dataFlavor : transferDataFlavors) {
            Class representationClass = dataFlavor.getRepresentationClass();
            if (representationClass != null && Image.class.isAssignableFrom(representationClass) && (dataFlavor.isMimeTypeEqual(DataFlavor.imageFlavor) || dataFlavor.isFlavorSerializedObjectType())) {
                try {
                    return this.getImageBitmap((Image) this.contents.getTransferData(dataFlavor));
                } catch (Throwable unused) {
                    continue;
                }
            }
        }
        return null;
    }

    private RawBitmap getImageBitmap(Image image) {
        if (image instanceof BufferedImage bufferedImage) {
            if (bufferedImage.getType() == 1) {
                return getImageBitmap32(bufferedImage);
            }
        }
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        if (width <= 0 || height <= 0) {
            return null;
        }
        BufferedImage bufferedImage2 = new BufferedImage(width, height, 1);
        Graphics graphics = bufferedImage2.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return getImageBitmap32(bufferedImage2);
    }

    private RawBitmap getImageBitmap32(BufferedImage bufferedImage) {
        int[] iArr = new int[bufferedImage.getWidth() * bufferedImage.getHeight()];
        DataBufferInt dataBuffer = bufferedImage.getRaster().getDataBuffer();
        int numBanks = dataBuffer.getNumBanks();
        int[] offsets = dataBuffer.getOffsets();
        int i2 = 0;
        for (int i3 = 0; i3 < numBanks; i3++) {
            int[] data = dataBuffer.getData(i3);
            int i4 = offsets[i3];
            System.arraycopy(data, i4, iArr, i2, data.length - i4);
            i2 += data.length - offsets[i3];
        }
        return new RawBitmap(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getWidth(), 32, 16711680, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 255, iArr);
    }

    @Override // org.apache.harmony.awt.datatransfer.DataProvider
    public byte[] getSerializedObject(Class<?> cls) {
        try {
            DataFlavor dataFlavor = new DataFlavor(cls, null);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject((Serializable) this.contents.getTransferData(dataFlavor));
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // org.apache.harmony.awt.datatransfer.DataProvider
    public boolean isNativeFormatAvailable(String str) {
        return getNativeFormatsList().contains(str);
    }
}
