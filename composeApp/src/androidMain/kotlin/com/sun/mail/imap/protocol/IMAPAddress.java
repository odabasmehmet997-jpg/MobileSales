package com.sun.mail.imap.protocol;

import sun.mail.iap.Response;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

class IMAPAddress extends InternetAddress {
    private static final long serialVersionUID = -3835822029483122232L;
    private final boolean group;
    private final InternetAddress[] grouplist;
    private final String groupname;
    IMAPAddress(final Response response)  {
        throw null;
    }
    public boolean isEndOfGroup() {
        return group && null == this.groupname;
    }
    public boolean isGroup() {
        return group;
    }
    public InternetAddress[] getGroup(final boolean z) throws AddressException {
        final InternetAddress[] internetAddressArr = grouplist;
        if (null == internetAddressArr) {
            return null;
        }
        return internetAddressArr.clone();
    }
}
