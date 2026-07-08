package javax.mail.search;

public abstract class StringTerm extends SearchTerm {
    private static final long serialVersionUID = 1274042129007696269L;
    protected boolean ignoreCase;
    protected String pattern;
    protected StringTerm(String str) {
        this.pattern = str;
        this.ignoreCase = true;
    }
    protected StringTerm(String str, boolean z) {
        this.pattern = str;
        this.ignoreCase = z;
    }
    public String getPattern() {
        return this.pattern;
    }
    public boolean getIgnoreCase() {
        return this.ignoreCase;
    }
    public boolean match(String str) {
        int length = str.length() - this.pattern.length();
        for (int i2 = 0; i2 <= length; i2++) {
            boolean z = this.ignoreCase;
            String str2 = this.pattern;
            if (str.regionMatches(z, i2, str2, 0, str2.length())) {
                return true;
            }
        }
        return false;
    }
    public boolean equals(Object obj) {
        if (!(obj instanceof StringTerm stringTerm)) {
            return false;
        }
        if (this.ignoreCase) {
            return stringTerm.pattern.equalsIgnoreCase(this.pattern) && stringTerm.ignoreCase == this.ignoreCase;
        }
        return stringTerm.pattern.equals(this.pattern) && stringTerm.ignoreCase == this.ignoreCase;
    }
    public int hashCode() {
        return this.ignoreCase ? this.pattern.hashCode() : ~this.pattern.hashCode();
    }
}
