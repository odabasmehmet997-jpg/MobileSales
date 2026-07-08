package javax.mail.search;

import javax.mail.Address;
import javax.mail.Message;

public final class FromTerm extends AddressTerm {
    private static final long serialVersionUID = 5214730291502658665L;

    public FromTerm(Address address) {
        super(address);
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
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FromTerm)) {
            return false;
        }
        return super.equals(obj);
    }
}
