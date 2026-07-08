package com.fasterxml.jackson.annotation;

public enum PropertyAccessor {
    GETTER,
    SETTER,
    CREATOR,
    FIELD,
    IS_GETTER,
    NONE,
    ALL;

    public boolean creatorEnabled() {
        return PropertyAccessor.CREATOR == this || PropertyAccessor.ALL == this;
    }

    public boolean getterEnabled() {
        return PropertyAccessor.GETTER == this || PropertyAccessor.ALL == this;
    }

    public boolean isGetterEnabled() {
        return PropertyAccessor.IS_GETTER == this || PropertyAccessor.ALL == this;
    }

    public boolean setterEnabled() {
        return PropertyAccessor.SETTER == this || PropertyAccessor.ALL == this;
    }

    public boolean fieldEnabled() {
        return PropertyAccessor.FIELD == this || PropertyAccessor.ALL == this;
    }
}
