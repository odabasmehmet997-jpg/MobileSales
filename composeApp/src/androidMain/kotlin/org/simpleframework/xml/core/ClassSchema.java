package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;


class ClassSchema implements Schema {
    private final Caller caller;
    private final Decorator decorator;
    private final Instantiator factory;
    private final boolean primitive;
    private final Version revision;
    private final Section section;
    private final Label text;
    private final Class type;
    private final Label version;

    public ClassSchema(final Scanner scanner, final Context context) throws Exception {
        caller = scanner.getCaller(context);
        factory = scanner.getInstantiator();
        revision = scanner.getRevision();
        decorator = scanner.getDecorator();
        primitive = scanner.isPrimitive();
        version = scanner.getVersion();
        section = scanner.getSection();
        text = scanner.getText();
        type = scanner.getType();
    }

    @Override // org.simpleframework.xml.core.Schema
    public boolean isPrimitive() {
        return primitive;
    }

    @Override // org.simpleframework.xml.core.Schema
    public Instantiator getInstantiator() {
        return factory;
    }

    @Override // org.simpleframework.xml.core.Schema
    public Label getVersion() {
        return version;
    }

    @Override // org.simpleframework.xml.core.Schema
    public Version getRevision() {
        return revision;
    }

    @Override // org.simpleframework.xml.core.Schema
    public Decorator getDecorator() {
        return decorator;
    }

    @Override // org.simpleframework.xml.core.Schema
    public Caller getCaller() {
        return caller;
    }

    @Override // org.simpleframework.xml.core.Schema
    public Section getSection() {
        return section;
    }

    @Override // org.simpleframework.xml.core.Schema
    public Label getText() {
        return text;
    }

    public String toString() {
        return String.format("schema for %s", type);
    }
}
