package javax.activation;

import androidx.webkit.ProxyConfig;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Locale;

public class MimeType implements Externalizable {
    private static final String TSPECIALS = "()<>@,;:/[]?=\\\"";
    private MimeTypeParameterList parameters;
    private String primaryType;
    private String subType;

    public MimeType() {
        primaryType = "application";
        subType = ProxyConfig.MATCH_ALL_SCHEMES;
        parameters = new MimeTypeParameterList();
    }

    public MimeType(final String str) throws MimeTypeParseException {
        this.parse(str);
    }

    public MimeType(final String str, final String str2) throws MimeTypeParseException {
        if (this.isValidToken(str)) {
            final Locale locale = Locale.ENGLISH;
            primaryType = str.toLowerCase(locale);
            if (this.isValidToken(str2)) {
                subType = str2.toLowerCase(locale);
                parameters = new MimeTypeParameterList();
                return;
            }
            throw new MimeTypeParseException("Sub type is invalid.");
        }
        throw new MimeTypeParseException("Primary type is invalid.");
    }

    private void parse(final String str) throws MimeTypeParseException {
        final int indexOf = str.indexOf(47);
        final int indexOf2 = str.indexOf(59);
        if (0 > indexOf && 0 > indexOf2) {
            throw new MimeTypeParseException("Unable to find a sub type.");
        } else if (0 <= indexOf || 0 > indexOf2) {
            if (0 <= indexOf && 0 > indexOf2) {
                final String trim = str.substring(0, indexOf).trim();
                final Locale locale = Locale.ENGLISH;
                primaryType = trim.toLowerCase(locale);
                subType = str.substring(indexOf + 1).trim().toLowerCase(locale);
                parameters = new MimeTypeParameterList();
            } else if (indexOf < indexOf2) {
                final String trim2 = str.substring(0, indexOf).trim();
                final Locale locale2 = Locale.ENGLISH;
                primaryType = trim2.toLowerCase(locale2);
                subType = str.substring(indexOf + 1, indexOf2).trim().toLowerCase(locale2);
                parameters = new MimeTypeParameterList(str.substring(indexOf2));
            } else {
                throw new MimeTypeParseException("Unable to find a sub type.");
            }
            if (!this.isValidToken(primaryType)) {
                throw new MimeTypeParseException("Primary type is invalid.");
            } else if (!this.isValidToken(subType)) {
                throw new MimeTypeParseException("Sub type is invalid.");
            }
        } else {
            throw new MimeTypeParseException("Unable to find a sub type.");
        }
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(final String str) throws MimeTypeParseException {
        if (this.isValidToken(primaryType)) {
            primaryType = str.toLowerCase(Locale.ENGLISH);
            return;
        }
        throw new MimeTypeParseException("Primary type is invalid.");
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(final String str) throws MimeTypeParseException {
        if (this.isValidToken(subType)) {
            subType = str.toLowerCase(Locale.ENGLISH);
            return;
        }
        throw new MimeTypeParseException("Sub type is invalid.");
    }

    public MimeTypeParameterList getParameters() {
        return parameters;
    }

    public String getParameter(final String str) {
        return parameters.get(str);
    }

    public void setParameter(final String str, final String str2) {
        parameters.set(str, str2);
    }

    public void removeParameter(final String str) {
        parameters.remove(str);
    }

    public String toString() {
        return this.getBaseType() + parameters.toString();
    }

    public String getBaseType() {
        return primaryType + "/" + subType;
    }

    public boolean match(final MimeType mimeType) {
        if (primaryType.equals(mimeType.primaryType)) {
            return subType.equals(ProxyConfig.MATCH_ALL_SCHEMES) || mimeType.subType.equals(ProxyConfig.MATCH_ALL_SCHEMES) || subType.equals(mimeType.subType);
        }
        return false;
    }

    public boolean match(final String str) throws MimeTypeParseException {
        return this.match(new MimeType(str));
    }

    public void writeExternal(final ObjectOutput objectOutput) throws IOException {
        objectOutput.writeUTF(this.toString());
        objectOutput.flush();
    }

    public void readExternal(final ObjectInput objectInput) throws IOException, ClassNotFoundException {
        try {
            this.parse(objectInput.readUTF());
        } catch (final MimeTypeParseException e2) {
            throw new IOException(e2.toString());
        }
    }

    private static boolean isTokenChar(final char c2) {
        return ' ' < c2 && 127 > c2 && 0 > TSPECIALS.indexOf(c2);
    }

    private boolean isValidToken(final String str) {
        final int length = str.length();
        if (0 >= length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (!MimeType.isTokenChar(str.charAt(i2))) {
                return false;
            }
        }
        return true;
    }
}
