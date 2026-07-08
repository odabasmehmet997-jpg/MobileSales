package javax.activation;

import myjava.awt.datatransfer.DataFlavor;

public class ActivationDataFlavor extends DataFlavor {
    private String humanPresentableName;
    private MimeType mimeObject;
    private String mimeType;
    private Class representationClass;

    public String normalizeMimeType(String str) {
        return str;
    }
    public String normalizeMimeTypeParameter(String str, String str2) {
        return str2;
    }
    public ActivationDataFlavor(Class cls, String str, String str2) {
        super(str, str2);
        this.mimeObject = null;
        this.mimeType = str;
        this.humanPresentableName = str2;
        this.representationClass = cls;
    }
    public ActivationDataFlavor(Class cls, String str) {
        super((Class<?>) cls, str);
        this.mimeType = null;
        this.mimeObject = null;
        this.humanPresentableName = null;
        this.representationClass = null;
        this.mimeType = super.getMimeType();
        this.representationClass = cls;
        this.humanPresentableName = str;
    }
    public ActivationDataFlavor(String str, String str2) {
        super(str, str2);
        this.mimeObject = null;
        this.humanPresentableName = null;
        this.representationClass = null;
        this.mimeType = str;
        try {
            this.representationClass = Class.forName("java.io.InputStream");
        } catch (ClassNotFoundException unused) {
        }
        this.humanPresentableName = str2;
    }
    public String getMimeType() {
        return this.mimeType;
    }
    public Class getRepresentationClass() {
        return this.representationClass;
    }
    public String getHumanPresentableName() {
        return this.humanPresentableName;
    }
    public void setHumanPresentableName(String str) {
        this.humanPresentableName = str;
    }
    public boolean equals(DataFlavor dataFlavor) {
        return isMimeTypeEqual(dataFlavor) && dataFlavor.getRepresentationClass() == this.representationClass;
    }
    public boolean isMimeTypeEqual(String str) {
        try {
            if (null == mimeObject) {
                this.mimeObject = new MimeType(this.mimeType);
            }
            return this.mimeObject.match(new MimeType(str));
        } catch (MimeTypeParseException unused) {
            return this.mimeType.equalsIgnoreCase(str);
        }
    }
}
