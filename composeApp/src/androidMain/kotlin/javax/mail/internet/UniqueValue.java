package javax.mail.internet;

import javax.mail.Session;

class UniqueValue {
    private static int id;
    UniqueValue() {
    }
    public static String getUniqueBoundaryValue() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("----=_Part_");
        stringBuffer.append(getUniqueId());
        stringBuffer.append("_");
        stringBuffer.append(stringBuffer.hashCode());
        stringBuffer.append('.');
        stringBuffer.append(System.currentTimeMillis());
        return stringBuffer.toString();
    }
    public static String getUniqueMessageIDValue(Session session) {
        String str;
        InternetAddress localAddress = InternetAddress.getLocalAddress(session);
        if (null != localAddress) {
            str = localAddress.getAddress();
        } else {
            str = "javamailuser@localhost";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringBuffer.hashCode());
        stringBuffer.append('.');
        stringBuffer.append(getUniqueId());
        stringBuffer.append('.');
        stringBuffer.append(System.currentTimeMillis());
        stringBuffer.append('.');
        stringBuffer.append("JavaMail.");
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
    private static synchronized int getUniqueId() {
        int i2;
        synchronized (UniqueValue.class) {
            i2 = id;
            id = i2 + 1;
        }
        return i2;
    }
}
