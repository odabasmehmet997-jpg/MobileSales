package javax.mail;

public record MessageContext(Part part) {
    public Message getMessage() {
        try {
            return getMessage(this.part);
        } catch (MessagingException unused) {
            return null;
        }
    }
    private static Message getMessage(Part part2) throws MessagingException {
        while (null != part2) {
            if (part2 instanceof Message) {
                return (Message) part2;
            }
            Multipart parent = ((BodyPart) part2).getParent();
            if (null == parent) {
                return null;
            }
            part2 = parent.getParent();
        }
        return null;
    }
    public Session getSession() {
        Message message = getMessage();
        if (null != message) {
            return message.session;
        }
        return null;
    }
}
