package org.kobjects.pim;

public class VCard extends PimItem {
    public VCard() {
    }
    public VCard(final VCard vCard) {
        super(vCard);
    }
    public String getType() {
        return "vcard";
    }
    public int getArraySize(final String str) {
        if ("n".equals(str)) {
            return 5;
        }
        return "adr".equals(str) ? 6 : -1;
    }
}
