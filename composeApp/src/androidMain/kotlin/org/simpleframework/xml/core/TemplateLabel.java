package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;

abstract class TemplateLabel implements Label {
    private final KeyBuilder builder = new KeyBuilder(this);
    public Type getDependent() throws Exception {
        return null;
    }
    public String getEntry() throws Exception {
        return null;
    }
    public Label getLabel(final Class cls) throws Exception {
        return this;
    }
    public boolean isAttribute() {
        return false;
    }
    public boolean isCollection() {
        return false;
    }
    public boolean isInline() {
        return false;
    }
    public boolean isText() {
        return false;
    }
    public boolean isTextList() {
        return false;
    }
    public boolean isUnion() {
        return false;
    }
    protected TemplateLabel() {
    }
    public Type getType(final Class cls) throws Exception {
        return this.getContact();
    }
    public String[] getNames() throws Exception {
        return new String[]{this.getPath(), this.getName()};
    }
    public String[] getPaths() throws Exception {
        return new String[]{this.getPath()};
    }
    public Object getKey() throws Exception {
        return builder.getKey();
    }
}
