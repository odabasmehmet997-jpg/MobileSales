package javax.mail;

public abstract class BodyPart implements Part {
    protected Multipart parent;
    public Multipart getParent() {
        return this.parent;
    }
    public void setParent(Multipart multipart) {
        this.parent = multipart;
    }
}
