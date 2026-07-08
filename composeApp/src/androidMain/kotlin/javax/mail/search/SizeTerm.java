package javax.mail.search;

import javax.mail.Message;

public final class SizeTerm extends IntegerComparisonTerm {
    private static final long serialVersionUID = -2556219451005103709L;

    public SizeTerm(int i2, int i3) {
        super(i2, i3);
    }

    public boolean match(Message message) {
        try {
            int size = message.getSize();
            if (-1 == size) {
                return false;
            }
            return this.match(size);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SizeTerm)) {
            return false;
        }
        return super.equals(obj);
    }
}
