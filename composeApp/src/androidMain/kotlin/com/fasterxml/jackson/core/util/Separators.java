package com.fasterxml.jackson.core.util;

import java.io.Serializable;

public record Separators(char objectFieldValueSeparator, char objectEntrySeparator,
                         char arrayValueSeparator) implements Serializable {
    private static final long serialVersionUID = 1;

    public static Separators createDefaultInstance() {
        return new Separators();
    }

    public Separators() {
        this(':', ',', ',');
    }

    public Separators withObjectFieldValueSeparator(final char c2) {
        return objectFieldValueSeparator == c2 ? this : new Separators(c2, objectEntrySeparator, arrayValueSeparator);
    }

    public Separators withObjectEntrySeparator(final char c2) {
        return objectEntrySeparator == c2 ? this : new Separators(objectFieldValueSeparator, c2, arrayValueSeparator);
    }

    public Separators withArrayValueSeparator(final char c2) {
        return arrayValueSeparator == c2 ? this : new Separators(objectFieldValueSeparator, objectEntrySeparator, c2);
    }
}
