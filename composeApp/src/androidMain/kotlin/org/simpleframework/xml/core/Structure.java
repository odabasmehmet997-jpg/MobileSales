package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;

class Structure {
    private final Instantiator factory;
    private final Model model;
    private final boolean primitive;
    private final Label text;
    private final Label version;
    public Structure(final Instantiator instantiator, final Model model, final Label label, final Label label2, final boolean z) {
        primitive = z;
        factory = instantiator;
        version = label;
        this.model = model;
        text = label2;
    }
    public Instantiator getInstantiator() {
        return factory;
    }
    public Section getSection() {
        return new ModelSection(model);
    }
    public boolean isPrimitive() {
        return primitive;
    }
    public Version getRevision() {
        final Label label = version;
        if (null != label) {
            return label.getContact().getAnnotation(Version.class);
        }
        return null;
    }
    public Label getVersion() {
        return version;
    }
    public Label getText() {
        return text;
    }
}
