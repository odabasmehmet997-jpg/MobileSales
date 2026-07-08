package cardtek.masterpass.management;

import cardtek.masterpass.attributes.MasterPassEditText;

public class MasterPassTextHelper {
    public String getText(MasterPassEditText masterPassEditText) {
        return masterPassEditText.getRawText();
    }
    public void setText(MasterPassEditText masterPassEditText, String str) {
        masterPassEditText.setText(str);
    }
}
