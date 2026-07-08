package javax.mail.search;

import javax.mail.Message;
import java.util.Arrays;
import java.util.Objects;

public final class AndTerm extends SearchTerm {
    private static final long serialVersionUID = -3583274505380989582L;
    private final SearchTerm[] terms;
    public AndTerm(SearchTerm searchTerm, SearchTerm searchTerm2) {
        SearchTerm[] searchTermArr = new SearchTerm[2];
        this.terms = searchTermArr;
        searchTermArr[0] = searchTerm;
        searchTermArr[1] = searchTerm2;
    }
    public AndTerm(SearchTerm[] searchTermArr) {
        this.terms = new SearchTerm[searchTermArr.length];
        System.arraycopy(searchTermArr, 0, this.terms, 0, searchTermArr.length);
    }
    public SearchTerm[] getTerms() {
        return this.terms.clone();
    }
    public boolean match(Message message) {
        for (SearchTerm term : this.terms) {
            if (!term.match(message)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.terms);
    }
}
