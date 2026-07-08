package javax.mail.internet;

public class ContentType {
    private ParameterList list;
    private String primaryType;
    private String subType;
    public ContentType() {
    }
    public ContentType(String str, String str2, ParameterList parameterList) {
        this.primaryType = str;
        this.subType = str2;
        this.list = parameterList;
    }
    public ContentType(String str) throws ParseException {
        HeaderTokenizer headerTokenizer = new HeaderTokenizer(str, HeaderTokenizer.MIME);
        HeaderTokenizer.Token next = headerTokenizer.next();
        if (-1 == next.type()) {
            this.primaryType = next.value();
            HeaderTokenizer.Token next2 = headerTokenizer.next();
            if ('/' == ((char) next2.type())) {
                HeaderTokenizer.Token next3 = headerTokenizer.next();
                if (-1 == next3.type()) {
                    this.subType = next3.value();
                    String remainder = headerTokenizer.getRemainder();
                    if (null != remainder) {
                        this.list = new ParameterList(remainder);
                        return;
                    }
                    return;
                }
                final String stringBuffer = "Expected MIME subtype, got " +
                        next3.value();
                throw new ParseException(stringBuffer);
            }
            final String stringBuffer2 = "Expected '/', got " +
                    next2.value();
            throw new ParseException(stringBuffer2);
        }
        final String stringBuffer3 = "Expected MIME type, got " +
                next.value();
        throw new ParseException(stringBuffer3);
    }
    public String getPrimaryType() {
        return this.primaryType;
    }
    public String getSubType() {
        return this.subType;
    }
    public String getBaseType() {
        final String stringBuffer = this.primaryType +
                '/' +
                this.subType;
        return stringBuffer;
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
    public void setPrimaryType(String str) {
        this.primaryType = str;
    }
    public void setSubType(String str) {
        this.subType = str;
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
    public String toString() {
        if (null == primaryType || null == subType) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.primaryType);
        stringBuffer.append('/');
        stringBuffer.append(this.subType);
        ParameterList parameterList = this.list;
        if (null != parameterList) {
            stringBuffer.append(parameterList.toString(stringBuffer.length() + 14));
        }
        return stringBuffer.toString();
    }
    public boolean match(ContentType contentType) {
        if (!this.primaryType.equalsIgnoreCase(contentType.primaryType)) {
            return false;
        }
        String subType2 = contentType.subType;
        return '*' == subType.charAt(0) || '*' == subType2.charAt(0) || this.subType.equalsIgnoreCase(subType2);
    }
    public boolean match(String str) {
        try {
            return match(new ContentType(str));
        } catch (ParseException unused) {
            return false;
        }
    }
}
