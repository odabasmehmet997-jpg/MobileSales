package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;


class ModelList extends ArrayList<Model> {
    public ModelList build() {
        final ModelList modelList = new ModelList();
        final Iterator<Model> it = this.iterator();
        while (it.hasNext()) {
            modelList.register(it.next());
        }
        return modelList;
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        final Iterator<Model> it = this.iterator();
        while (it.hasNext()) {
            final Model next = it.next();
            if (null != next && !next.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public Model lookup(final int i2) {
        if (i2 <= this.size()) {
            return this.get(i2 - 1);
        }
        return null;
    }

    public void register(final Model model) {
        final int index = model.getIndex();
        final int size = this.size();
        for (int i2 = 0; i2 < index; i2++) {
            if (i2 >= size) {
                this.add(null);
            }
            final int i3 = index - 1;
            if (i2 == i3) {
                this.set(i3, model);
            }
        }
    }

    public Model take() {
        while (!this.isEmpty()) {
            final Model remove = this.remove(0);
            if (!remove.isEmpty()) {
                return remove;
            }
        }
        return null;
    }
}
