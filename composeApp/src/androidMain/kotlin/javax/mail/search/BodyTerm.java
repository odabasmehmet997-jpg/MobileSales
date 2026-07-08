package javax.mail.search;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;

public final class BodyTerm extends StringTerm {
    private static final long serialVersionUID = -4888862527916911385L;

    public BodyTerm(String str) {
        super(str);
    }

    public boolean match(Message message) {
        return matchPart(message);
    }

    private boolean matchPart(Part part) {
        try {
            if (part.isMimeType("text/*")) {
                String str = (String) part.getContent();
                if (null == str) {
                    return false;
                }
                return this.match(str);
            }
            if (part.isMimeType("multipart/*")) {
                Multipart multipart = (Multipart) part.getContent();
                int count = multipart.getCount();
                for (int i2 = 0; i2 < count; i2++) {
                    if (matchPart(multipart.getBodyPart(i2))) {
                        return true;
                    }
                }
            } else if (part.isMimeType("message/rfc822")) {
                return matchPart((Part) part.getContent());
            }
            return false;
        } catch (Exception unused) {
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BodyTerm)) {
            return false;
        }
        return super.equals(obj);
    }
}
