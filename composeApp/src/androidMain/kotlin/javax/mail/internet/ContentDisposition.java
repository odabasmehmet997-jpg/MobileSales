package javax.mail.internet;

public class ContentDisposition {
    private String disposition;
    private ParameterList list;
    public ContentDisposition() {
    }
    public ContentDisposition(String str, ParameterList parameterList) {
        this.disposition = str;
        this.list = parameterList;
    }
    public ContentDisposition(String str) throws ParseException {
        HeaderTokenizer headerTokenizer = new HeaderTokenizer(str, HeaderTokenizer.MIME);
        HeaderTokenizer.Token next = headerTokenizer.next();
        if (-1 == next.type()) {
            this.disposition = next.value();
            String remainder = headerTokenizer.getRemainder();
            if (null != remainder) {
                this.list = new ParameterList(remainder);
                return;
            }
            return;
        }
        final String stringBuffer = "Expected disposition, got " +
                next.value();
        throw new ParseException(stringBuffer);
    }
    public String getDisposition() {
        return this.disposition;
    }
    public String getParameter(String str) {
        ParameterList parameterList = this.list;
        if (null == parameterList) {
            return null;
        }
        return parameterList.get(str);
    }
    public ParameterList getParameterList() {
        return this.list;
    }
    public void setDisposition(String str) {
        this.disposition = str;
    }
    public void setParameter(String str, String str2) {
        if (null == list) {
            this.list = new ParameterList();
        }
        this.list.set(str, str2);
    }
    public void setParameterList(ParameterList parameterList) {
        this.list = parameterList;
    }
    @Override
    public String toString() {
        String str = this.disposition;
        if (null == str) {
            return null;
        }
        if (null == list) {
            return str;
        }
        return str + list.toString(str.length() + 21);
    }
}
