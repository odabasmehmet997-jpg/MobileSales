package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;


class ModelSection implements Section {
    private LabelMap attributes;
    private LabelMap elements;
    private final Model model;
    private ModelMap models;

    public ModelSection(final Model model) {
        this.model = model;
    }

    @Override // org.simpleframework.xml.core.Section
    public String getName() {
        return model.getName();
    }

    @Override // org.simpleframework.xml.core.Section
    public String getPrefix() {
        return model.getPrefix();
    }

    @Override // org.simpleframework.xml.core.Section
    public String getPath(final String str) throws Exception {
        final Expression expression = model.getExpression();
        return null == expression ? str : expression.getElement(str);
    }

    @Override // org.simpleframework.xml.core.Section
    public String getAttribute(final String str) throws Exception {
        final Expression expression = model.getExpression();
        return null == expression ? str : expression.getAttribute(str);
    }

    @Override // java.lang.Iterable
    public Iterator<String> iterator() {
        final ArrayList arrayList = new ArrayList();
        final Iterator<String> it = model.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList.iterator();
    }

    @Override // org.simpleframework.xml.core.Section
    public boolean isSection(final String str) throws Exception {
        return null != getModels().get(str);
    }

    public ModelMap getModels() throws Exception {
        if (null == this.models) {
            models = model.getModels();
        }
        return models;
    }

    @Override // org.simpleframework.xml.core.Section
    public Label getText() throws Exception {
        return model.getText();
    }

    @Override // org.simpleframework.xml.core.Section
    public LabelMap getAttributes() throws Exception {
        if (null == this.attributes) {
            attributes = model.getAttributes();
        }
        return attributes;
    }

    @Override // org.simpleframework.xml.core.Section
    public LabelMap getElements() throws Exception {
        if (null == this.elements) {
            elements = model.getElements();
        }
        return elements;
    }

    @Override // org.simpleframework.xml.core.Section
    public Label getElement(final String str) throws Exception {
        return this.getElements().getLabel(str);
    }

    @Override // org.simpleframework.xml.core.Section
    public Section getSection(final String str) throws Exception {
        final Model take;
        final ModelList modelList = this.getModels().get(str);
        if (null == modelList || null == (take = modelList.take())) {
            return null;
        }
        return new ModelSection(take);
    }
}
