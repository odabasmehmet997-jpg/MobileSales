package javax.mail.search;

import javax.mail.Message;

public final class OrTerm extends SearchTerm {
    private static final long serialVersionUID = 5380534067523646936L;
    private final SearchTerm[] terms;

    public OrTerm(SearchTerm searchTerm, SearchTerm searchTerm2) {
        SearchTerm[] searchTermArr = new SearchTerm[2];
        this.terms = searchTermArr;
        searchTermArr[0] = searchTerm;
        searchTermArr[1] = searchTerm2;
    }

    public OrTerm(SearchTerm[] searchTermArr) {
        this.terms = new SearchTerm[searchTermArr.length];
        System.arraycopy(searchTermArr, 0, this.terms, 0, searchTermArr.length);
    }

    public SearchTerm[] getTerms() {
        return this.terms.clone();
    }

    public boolean match(Message message) {
        int i2 = 0;
        while (true) {
            SearchTerm[] searchTermArr = this.terms;
            if (i2 >= searchTermArr.length) {
                return false;
            }
            if (searchTermArr[i2].match(message)) {
                return true;
            }
            i2++;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof final OrTerm orTerm)) {
            return false;
        }
        if (orTerm.terms.length != this.terms.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            SearchTerm[] searchTermArr = this.terms;
            if (i2 >= searchTermArr.length) {
                return true;
            }
            if (!searchTermArr[i2].equals(orTerm.terms[i2])) {
                return false;
            }
            i2++;
        }
    }

    public int hashCode() {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            SearchTerm[] searchTermArr = this.terms;
            if (i2 >= searchTermArr.length) {
                return i3;
            }
            i3 += searchTermArr[i2].hashCode();
            i2++;
        }
    }
}
