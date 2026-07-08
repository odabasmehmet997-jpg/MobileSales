package com.sun.mail.imap.protocol;

import javax.mail.Flags;

public class FLAGS extends Flags {
    static final char[] name = {'F', 'L', 'A', 'G', 'S'};
    private static final long serialVersionUID = 439049847053756670L;
    public int msgno;
    public FLAGS(final IMAPResponse iMAPResponse) {
        throw null;
    }
}
