package javax.mail.search;

import javax.mail.Address;
import javax.mail.Message;

public final class FromStringTerm extends AddressStringTerm {
    private static final long serialVersionUID = 5801127523826772788L;

    public FromStringTerm(String str) {
        super(str);
    }

    public boolean match(Message message) {
        try {
            Address[] from = message.getFrom();
            if (null == from) {
                return false;
            }
            for (Address match : from) {
                if (this.match(match)) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FromStringTerm)) {
            return false;
        }
        return super.equals(obj);
    }
}
