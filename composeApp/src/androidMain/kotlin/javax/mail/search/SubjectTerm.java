package javax.mail.search;

import javax.mail.Message;

public final class SubjectTerm extends StringTerm {
    private static final long serialVersionUID = 7481568618055573432L;

    public SubjectTerm(String str) {
        super(str);
    }

    public boolean match(Message message) {
        try {
            String subject = message.getSubject();
            if (null == subject) {
                return false;
            }
            return this.match(subject);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SubjectTerm)) {
            return false;
        }
        return super.equals(obj);
    }
}
