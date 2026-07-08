package javax.mail.search;

import javax.mail.Message;

public final class NotTerm extends SearchTerm {
    private static final long serialVersionUID = 7152293214217310216L;
    private final SearchTerm term;

    public NotTerm(SearchTerm searchTerm) {
        this.term = searchTerm;
    }

    public SearchTerm getTerm() {
        return this.term;
    }

    public boolean match(Message message) {
        return !this.term.match(message);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NotTerm)) {
            return false;
        }
        return ((NotTerm) obj).term.equals(this.term);
    }

    public int hashCode() {
        return this.term.hashCode() << 1;
    }
}
