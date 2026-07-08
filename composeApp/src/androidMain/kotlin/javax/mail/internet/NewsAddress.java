package javax.mail.internet;

import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.mail.Address;

public class NewsAddress extends Address {
    private static final long serialVersionUID = -4203797299824684143L;
    protected String host;
    protected String newsgroup;

    public NewsAddress() {
    }

    public NewsAddress(String str) {
        this(str, null);
    }

    public NewsAddress(String str, String str2) {
        this.newsgroup = str;
        this.host = str2;
    }

    public String getType() {
        return "news";
    }

    public void setNewsgroup(String str) {
        this.newsgroup = str;
    }

    public String getNewsgroup() {
        return this.newsgroup;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public String getHost() {
        return this.host;
    }

    public String toString() {
        return this.newsgroup;
    }

    public boolean equals(Object obj) {
        String str;
        if (!(obj instanceof final NewsAddress newsAddress)) {
            return false;
        }
        if (!this.newsgroup.equals(newsAddress.newsgroup)) {
            return false;
        }
        String str2 = this.host;
        return (null == str2 && null == newsAddress.host) || (null != str2 && null != (str = newsAddress.host) && str2.equalsIgnoreCase(str));
    }

    public int hashCode() {
        String str = this.newsgroup;
        int hashCode = null != str ? str.hashCode() : 0;
        String str2 = this.host;
        return null != str2 ? hashCode + str2.toLowerCase(Locale.ENGLISH).hashCode() : hashCode;
    }

    public static String toString(Address[] addressArr) {
        if (null == addressArr || 0 == addressArr.length) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(addressArr[0].toString());
        for (int i2 = 1; i2 < addressArr.length; i2++) {
            stringBuffer.append(",");
            stringBuffer.append(addressArr[i2].toString());
        }
        return stringBuffer.toString();
    }

    public static NewsAddress[] parse(String str) throws AddressException {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        Vector vector = new Vector();
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(new NewsAddress(stringTokenizer.nextToken()));
        }
        int size = vector.size();
        NewsAddress[] newsAddressArr = new NewsAddress[size];
        if (0 < size) {
            vector.copyInto(newsAddressArr);
        }
        return newsAddressArr;
    }
}
