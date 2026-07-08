package com.proje.mobilesales.core.reportparser;



public class ReportLayoutTabbedGroup extends ReportLayoutItem {
    private int selectedTabIndex = -1;
    private String selectedTabPageName;

    public String getSelectedTabPageName() {
        return selectedTabPageName;
    }

    public void setSelectedTabPageName(final String str) {
        selectedTabPageName = str;
    }

    public int getSelectedTabIndex() {
        final String str = selectedTabPageName;
        if (null == str || str.isEmpty() || 0 == getChildItems().size()) {
            return -1;
        }
        if (1 == getChildItems().size()) {
            return 0;
        }
        for (int i2 = 0; i2 < this.getChildItems().size(); i2++) {
            final ReportLayoutItem reportLayoutItem = this.getChildItems().get(i2);
            if ((reportLayoutItem instanceof ReportLayoutGroup) && reportLayoutItem.getName().equals(selectedTabPageName)) {
                selectedTabIndex = i2;
                return i2;
            }
        }
        return 0;
    }
}
