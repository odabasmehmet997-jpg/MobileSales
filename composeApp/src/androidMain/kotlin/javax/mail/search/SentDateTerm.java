package javax.mail.search;

import java.util.Date;
import javax.mail.Message;

public final class SentDateTerm extends DateTerm {
    private static final long serialVersionUID = 5647755030530907263L;

    public SentDateTerm(int i2, Date date) {
        super(i2, date);
    }

    public boolean match(Message message) {
        try {
            Date sentDate = message.getSentDate();
            if (null == sentDate) {
                return false;
            }
            return this.match(sentDate);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SentDateTerm)) {
            return false;
        }
        return super.equals(obj);
    }
}
