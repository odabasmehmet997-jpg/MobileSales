package com.proje.mobilesales.core.utils;

import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.features.dbmodel.WorTables;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WorTableNames {
    private static WorTableNames mInstance;
    private final ArrayList<String> list;
    public static WorTableNames getInstance() {
        if (mInstance == null) {
            mInstance = new WorTableNames();
            List table = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper().getTable(WorTables.class);
            mInstance.clearTableNames();
            Iterator it = table.iterator();
            while (it.hasNext()) {
                mInstance.addToTableNames(((WorTables) it.next()).getName());
            }
        }
        return mInstance;
    }
    private WorTableNames() {
        ArrayList<String> list1;
        list1 = null;
        list1 = new ArrayList<>();
        this.list = list1;
    }
    public ArrayList<String> getTableNames() {
        return this.list;
    }
    public void addToTableNames(String str) {
        this.list.add(str);
    }
    public boolean containsTableName(String str) {
        return this.list.contains(str);
    }
    public void clearTableNames() {
        this.list.clear();
    }
}
