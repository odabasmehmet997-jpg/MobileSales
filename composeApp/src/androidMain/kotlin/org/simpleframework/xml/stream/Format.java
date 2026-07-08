package org.simpleframework.xml.stream;


public record Format(int indent, String prolog, Style style, Verbosity verbosity) {
    public Format() {
        this(3);
    }

    public Format(final int i2) {
        this(i2, null, new IdentityStyle());
    }

    public Format(final String str) {
        this(3, str);
    }

    public Format(final int i2, final String str) {
        this(i2, str, new IdentityStyle());
    }

    public Format(final Verbosity verbosity) {
        this(3, verbosity);
    }

    public Format(final int i2, final Verbosity verbosity) {
        this(i2, new IdentityStyle(), verbosity);
    }

    public Format(final Style style) {
        this(3, style);
    }

    public Format(final Style style, final Verbosity verbosity) {
        this(3, style, verbosity);
    }

    public Format(final int i2, final Style style) {
        this(i2, null, style);
    }

    public Format(final int i2, final Style style, final Verbosity verbosity) {
        this(i2, null, style, verbosity);
    }

    public Format(final int i2, final String str, final Style style) {
        this(i2, str, style, Verbosity.HIGH);
    }

}
