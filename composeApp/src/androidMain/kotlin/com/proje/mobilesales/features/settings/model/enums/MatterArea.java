package com.proje.mobilesales.features.settings.model.enums;

import kotlin.enums.EnumEntries;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class MatterArea  {
    private static final   EnumEntries ENTRIES = null;
    private static final   MatterArea[] VALUES;
    public static final Companion Companion;
    public static final MatterArea DOC_NO = new MatterArea("DOC_NO", 0, 0);
    public static final MatterArea FICHE_NO = new MatterArea("FICHE_NO", 1, 1);
    private int type;
    private static   MatterArea[] values() {
        return new MatterArea[]{DOC_NO, FICHE_NO};
    }
    public static  MatterArea fromMatterArea(int r1) {
        return Companion.fromMatterArea(r1);
    }
    private MatterArea(String str, int r2, int r3) {
        super();
        this.type = r3;
    }
    public  int getType() {
        return this.type;
    }
    public  void setType(int r1) {
        this.type = r1;
    }
    static {
        MatterArea[] matterAreaArrvalues = values();
        VALUES = matterAreaArrvalues; 
        Companion = new Companion(null);
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public MatterArea fromMatterArea(int r5) {
            for (MatterArea matterArea : MatterArea.values()) {
                if (matterArea.getType() == r5) {
                    return matterArea;
                }
            }
            return MatterArea.FICHE_NO;
        }
    }
}
