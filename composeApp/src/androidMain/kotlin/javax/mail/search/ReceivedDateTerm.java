package javax.mail.search;

import java.util.Date;
import javax.mail.Message;

public final class ReceivedDateTerm extends DateTerm {
    private static final long serialVersionUID = -2756695246195503170L;

    public ReceivedDateTerm(int i2, Date date) {
        super(i2, date);
    }

    public boolean match(Message message) {
        try {
            Date receivedDate = message.getReceivedDate();
            if (null == receivedDate) {
                return false;
            }
            return this.match(receivedDate);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ReceivedDateTerm)) {
            return false;
        }
        return super.equals(obj);
    }
}
