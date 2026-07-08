package org.simpleframework.xml.core;

import java.util.Collections;
import java.util.List;


class LabelGroup {
    private final List<Label> list;
    private final int size;

    public LabelGroup(final Label label) {
        this(Collections.singletonList(label));
    }

    public LabelGroup(final List<Label> list) {
        size = list.size();
        this.list = list;
    }

    public List<Label> getList() {
        return list;
    }

    public Label getPrimary() {
        if (0 < this.size) {
            return list.get(0);
        }
        return null;
    }
}
