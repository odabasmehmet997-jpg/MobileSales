package com.proje.mobilesales.features.model;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class PanelVersion {

    public String version;
    public PanelVersion() {
        this(null, 1, null);
    }
    public PanelVersion(final String str) {
        version = str;
    }
    public  PanelVersion(final String str, final int i, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i & 1) ? null : str);
    }
}
