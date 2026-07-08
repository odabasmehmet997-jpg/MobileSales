package myjava.awt.datatransfer;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

public final class MimeTypeProcessor {
    private static MimeTypeProcessor instance;
    private static boolean isMeaningfulChar(char c2) {
        return c2 >= '!' && c2 <= '~';
    }
    private static boolean isTSpecialChar(char c2) {
        return c2 == '(' || c2 == ')' || c2 == '[' || c2 == ']' || c2 == '<' || c2 == '>' || c2 == '@' || c2 == ',' || c2 == ';' || c2 == ':' || c2 == '\\' || c2 == '\"' || c2 == '/' || c2 == '?' || c2 == '=';
    }
    private MimeTypeProcessor() {
    }
    public static MimeType parse(String str) {
        if (instance == null) {
            instance = new MimeTypeProcessor();
        }
        MimeType mimeType = new MimeType();
        if (str != null) {
            StringPosition stringPosition = new StringPosition(null);
            retrieveType(str, mimeType, stringPosition);
            retrieveParams(str, mimeType, stringPosition);
        }
        return mimeType;
    }
    public static String assemble(MimeType mimeType) {
        StringBuilder sb = new StringBuilder();
        sb.append(mimeType.getFullType());
        Enumeration keys = mimeType.parameters.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            sb.append("; ");
            sb.append(str);
            sb.append("=\"");
            sb.append(mimeType.parameters.get(str));
            sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        }
        return sb.toString();
    }
    private static void retrieveType(String str, MimeType mimeType, StringPosition stringPosition) {
        mimeType.primaryType = retrieveToken(str, stringPosition).toLowerCase();
        int nextMeaningfulIndex = getNextMeaningfulIndex(str, stringPosition.i);
        stringPosition.i = nextMeaningfulIndex;
        if (nextMeaningfulIndex >= str.length() || str.charAt(stringPosition.i) != '/') {
            throw new IllegalArgumentException();
        }
        stringPosition.i++;
        mimeType.subType = retrieveToken(str, stringPosition).toLowerCase();
    }
    private static void retrieveParams(String str, MimeType mimeType, StringPosition stringPosition) {
        mimeType.parameters = new Hashtable();
        mimeType.systemParameters = new Hashtable();
        while (true) {
            int nextMeaningfulIndex = getNextMeaningfulIndex(str, stringPosition.i);
            stringPosition.i = nextMeaningfulIndex;
            if (nextMeaningfulIndex < str.length()) {
                if (str.charAt(stringPosition.i) == ';') {
                    stringPosition.i++;
                    retrieveParam(str, mimeType, stringPosition);
                } else {
                    throw new IllegalArgumentException();
                }
            } else {
                return;
            }
        }
    }
    private static void retrieveParam(String str, MimeType mimeType, StringPosition stringPosition) {
        String str2;
        String lowerCase = retrieveToken(str, stringPosition).toLowerCase();
        int nextMeaningfulIndex = getNextMeaningfulIndex(str, stringPosition.i);
        stringPosition.i = nextMeaningfulIndex;
        if (nextMeaningfulIndex >= str.length() || str.charAt(stringPosition.i) != '=') {
            throw new IllegalArgumentException();
        }
        int i2 = stringPosition.i + 1;
        stringPosition.i = i2;
        int nextMeaningfulIndex2 = getNextMeaningfulIndex(str, i2);
        stringPosition.i = nextMeaningfulIndex2;
        if (nextMeaningfulIndex2 < str.length()) {
            if (str.charAt(stringPosition.i) == '\"') {
                str2 = retrieveQuoted(str, stringPosition);
            } else {
                str2 = retrieveToken(str, stringPosition);
            }
            mimeType.parameters.put(lowerCase, str2);
            return;
        }
        throw new IllegalArgumentException();
    }
    private static String retrieveQuoted(String str, StringPosition stringPosition) {
        StringBuilder sb = new StringBuilder();
        stringPosition.i++;
        boolean z = true;
        do {
            if (str.charAt(stringPosition.i) != '\"' || !z) {
                int i2 = stringPosition.i;
                stringPosition.i = i2 + 1;
                char charAt = str.charAt(i2);
                if (!z) {
                    z = true;
                } else if (charAt == '\\') {
                    z = false;
                }
                if (z) {
                    sb.append(charAt);
                }
            } else {
                stringPosition.i++;
                return sb.toString();
            }
        } while (stringPosition.i != str.length());
        throw new IllegalArgumentException();
    }
    private static String retrieveToken(String str, StringPosition stringPosition) {
        StringBuilder sb = new StringBuilder();
        int nextMeaningfulIndex = getNextMeaningfulIndex(str, stringPosition.i);
        stringPosition.i = nextMeaningfulIndex;
        if (nextMeaningfulIndex >= str.length() || isTSpecialChar(str.charAt(stringPosition.i))) {
            throw new IllegalArgumentException();
        }
        do {
            int i2 = stringPosition.i;
            stringPosition.i = i2 + 1;
            sb.append(str.charAt(i2));
            if (stringPosition.i >= str.length() || !isMeaningfulChar(str.charAt(stringPosition.i))) {
                break;
            }
        } while (!isTSpecialChar(str.charAt(stringPosition.i)));
        return sb.toString();
    }
    private static int getNextMeaningfulIndex(String str, int i2) {
        while (i2 < str.length() && !isMeaningfulChar(str.charAt(i2))) {
            i2++;
        }
        return i2;
    }
    public static final class StringPosition {
        int i;
        private StringPosition(Object o) {
            this.i = 0;
        }
    }
    public static final class MimeType implements Cloneable, Serializable {
        private static final long serialVersionUID = -6693571907475992044L;
        private Hashtable<String, String> parameters;
        private String primaryType;
        private String subType;
        private Hashtable<String, Object> systemParameters;

        MimeType() {
            this.primaryType = null;
            this.subType = null;
            this.parameters = null;
            this.systemParameters = null;
        }

        
        public MimeType(String str, String str2) {
            this.primaryType = str;
            this.subType = str2;
            this.parameters = new Hashtable<>();
            this.systemParameters = new Hashtable<>();
        }

        
        public boolean equals(MimeType mimeType) {
            if (mimeType == null) {
                return false;
            }
            return getFullType().equals(mimeType.getFullType());
        }

        
        public String getPrimaryType() {
            return this.primaryType;
        }

        
        public String getSubType() {
            return this.subType;
        }

        
        public String getFullType() {
            return this.primaryType + "/" + this.subType;
        }

        
        public String getParameter(String str) {
            return this.parameters.get(str);
        }

        
        public void addParameter(String str, String str2) {
            if (str2 != null) {
                if (str2.charAt(0) == '\"' && str2.charAt(str2.length() - 1) == '\"') {
                    str2 = str2.substring(1, str2.length() - 2);
                }
                if (str2.length() != 0) {
                    this.parameters.put(str, str2);
                }
            }
        }

        void removeParameter(String str) {
            this.parameters.remove(str);
        }

        Object getSystemParameter(String str) {
            return this.systemParameters.get(str);
        }

        void addSystemParameter(String str, Object obj) {
            this.systemParameters.put(str, obj);
        }

        public Object clone() {
            MimeType mimeType = new MimeType(this.primaryType, this.subType);
            mimeType.parameters = (Hashtable) this.parameters.clone();
            mimeType.systemParameters = (Hashtable) this.systemParameters.clone();
            return mimeType;
        }
    }
}
