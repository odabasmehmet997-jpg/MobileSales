package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.LinkedHashMap;


class ModelMap extends LinkedHashMap<String, ModelList> implements Iterable<ModelList> {
    private final Detail detail;

    public ModelMap(final Detail detail) {
        this.detail = detail;
    }

    public ModelMap getModels() throws Exception {
        final ModelMap modelMap = new ModelMap(detail);
        for (final String str : this.keySet()) {
            ModelList modelList = this.get(str);
            if (null != modelList) {
                modelList = modelList.build();
            }
            if (modelMap.containsKey(str)) {
                throw new PathException("Path with name '%s' is a duplicate in %s ", str, detail);
            }
            modelMap.put(str, modelList);
        }
        return modelMap;
    }

    public Model lookup(final String str, final int i2) {
        final ModelList modelList = this.get(str);
        if (null != modelList) {
            return modelList.lookup(i2);
        }
        return null;
    }

    public void register(final String str, final Model model) {
        ModelList modelList = this.get(str);
        if (null == modelList) {
            modelList = new ModelList();
            this.put(str, modelList);
        }
        modelList.register(model);
    }

    @Override // java.lang.Iterable
    public Iterator<ModelList> iterator() {
        return this.values().iterator();
    }
}
