package javax.mail;

public record Provider(Type type, String protocol, String className, String vendor, String version) {
    public static class Type {
        public static final Type STORE = new Type("STORE");
        public static final Type TRANSPORT = new Type("TRANSPORT");
        private final String type;

        private Type(String str) {
            this.type = str;
        }

        public String toString() {
            return this.type;
        }
    }

    public String toString() {
        String stringBuffer2 = "javax.mail.Provider[" +
                this.type +
                "," +
                this.protocol +
                "," +
                this.className;
        if (null != vendor) {
            final String stringBuffer3 = stringBuffer2 +
                    "," +
                    this.vendor;
            stringBuffer2 = stringBuffer3;
        }
        if (null != version) {
            final String stringBuffer4 = stringBuffer2 +
                    "," +
                    this.version;
            stringBuffer2 = stringBuffer4;
        }
        final String stringBuffer5 = stringBuffer2 +
                "]";
        return stringBuffer5;
    }
}
