package javax.mail;

import java.io.Serializable;
import java.util.*;

public class Flags implements Cloneable, Serializable {
    private static final int ANSWERED_BIT = 1;
    private static final int DELETED_BIT = 2;
    private static final int DRAFT_BIT = 4;
    private static final int FLAGGED_BIT = 8;
    private static final int RECENT_BIT = 16;
    private static final int SEEN_BIT = 32;
    private static final int USER_BIT = Integer.MIN_VALUE;
    private static final long serialVersionUID = 6243590407214169028L;
    private int system_flags;
    private Hashtable user_flags;
    public static final class Flag {
        public static final Flag ANSWERED = new Flag(1);
        public static final Flag DELETED = new Flag(2);
        public static final Flag DRAFT = new Flag(4);
        public static final Flag FLAGGED = new Flag(8);
        public static final Flag RECENT = new Flag(16);
        public static final Flag SEEN = new Flag(32);
        public static final Flag USER = new Flag(Integer.MIN_VALUE);
        
        public int bit;

        private Flag(int i2) {
            this.bit = i2;
        }
    }
    public Flags() {
    }
    public Flags(Flags flags) {
        this.system_flags = flags.system_flags;
        Hashtable hashtable = flags.user_flags;
        if (null != hashtable) {
            this.user_flags = (Hashtable) hashtable.clone();
        }
    }
    public Flags(Flag flag) {
        this.system_flags = flag.bit;
    }
    public Flags(String str) {
        Hashtable hashtable = new Hashtable(1);
        this.user_flags = hashtable;
        hashtable.put(str.toLowerCase(Locale.ENGLISH), str);
    }
    public void add(Flag flag) {
        this.system_flags = flag.bit | this.system_flags;
    }
    public void add(String str) {
        if (null == user_flags) {
            this.user_flags = new Hashtable(1);
        }
        this.user_flags.put(str.toLowerCase(Locale.ENGLISH), str);
    }
    public void add(Flags flags) {
        this.system_flags |= flags.system_flags;
        if (null != flags.user_flags) {
            if (null == user_flags) {
                this.user_flags = new Hashtable(1);
            }
            final Iterator iterator = flags.user_flags.keySet().iterator();
            while (iterator.hasNext()) {
                String str = (String) iterator.next();
                this.user_flags.put(str, flags.user_flags.get(str));
            }
        }
    }
    public void remove(Flag flag) {
        this.system_flags = (~flag.bit) & this.system_flags;
    }
    public void remove(String str) {
        Hashtable hashtable = this.user_flags;
        if (null != hashtable) {
            hashtable.remove(str.toLowerCase(Locale.ENGLISH));
        }
    }
    public void remove(Flags flags) {
        this.system_flags &= ~flags.system_flags;
        Hashtable hashtable = flags.user_flags;
        if (null != hashtable && null != user_flags) {
            final Iterator iterator = hashtable.keySet().iterator();
            while (iterator.hasNext()) {
                this.user_flags.remove(iterator.next());
            }
        }
    }
    public boolean contains(Flag flag) {
        return 0 != (flag.bit & system_flags);
    }
    public boolean contains(String str) {
        Hashtable hashtable = this.user_flags;
        if (null == hashtable) {
            return false;
        }
        return hashtable.containsKey(str.toLowerCase(Locale.ENGLISH));
    }
    public boolean contains(Flags flags) {
        int i2 = flags.system_flags;
        if ((this.system_flags & i2) != i2) {
            return false;
        }
        Hashtable hashtable = flags.user_flags;
        if (null == hashtable) {
            return true;
        }
        if (null == user_flags) {
            return false;
        }
        final Iterator<String> iterator = hashtable.keySet().iterator();
        while (iterator.hasNext()) {
            if (!this.user_flags.containsKey(iterator.next())) {
                return false;
            }
        }
        return true;
    }
    public boolean equals(Object obj) {
        if (!(obj instanceof Flags flags)) {
            return false;
        }
        if (flags.system_flags != this.system_flags) {
            return false;
        }
        Hashtable<String, String> hashtable = flags.user_flags;
        if (null == hashtable && null == user_flags) {
            return true;
        }
        if (null == hashtable || null == user_flags || hashtable.size() != this.user_flags.size()) {
            return false;
        }
        final Iterator<String> iterator = flags.user_flags.keySet().iterator();
        while (iterator.hasNext()) {
            if (!this.user_flags.containsKey(iterator.next())) {
                return false;
            }
        }
        return true;
    }
    public int hashCode() {
        int i2 = this.system_flags;
        Hashtable<String, String> hashtable = this.user_flags;
        if (null != hashtable) {
            final Iterator<String> iterator = hashtable.keySet().iterator();
            while (iterator.hasNext()) {
                i2 += iterator.next().hashCode();
            }
        }
        return i2;
    }
    public Flag[] getSystemFlags() {
        Vector<Flag> vector = new Vector<>();
        if (0 != (system_flags & 1)) {
            vector.addElement(Flag.ANSWERED);
        }
        if (0 != (system_flags & 2)) {
            vector.addElement(Flag.DELETED);
        }
        if (0 != (system_flags & 4)) {
            vector.addElement(Flag.DRAFT);
        }
        if (0 != (system_flags & 8)) {
            vector.addElement(Flag.FLAGGED);
        }
        if (0 != (system_flags & 16)) {
            vector.addElement(Flag.RECENT);
        }
        if (0 != (system_flags & 32)) {
            vector.addElement(Flag.SEEN);
        }
        if (0 != (system_flags & Integer.MIN_VALUE)) {
            vector.addElement(Flag.USER);
        }
        Flag[] flagArr = new Flag[vector.size()];
        vector.copyInto(flagArr);
        return flagArr;
    }
    public String[] getUserFlags() {
        Vector<String> vector = new Vector<>();
        Hashtable<String, String> hashtable = this.user_flags;
        if (null != hashtable) {
            final Iterator<String> iterator = hashtable.values().iterator();
            while (iterator.hasNext()) {
                vector.addElement(iterator.next());
            }
        }
        String[] strArr = new String[vector.size()];
        vector.copyInto(strArr);
        return strArr;
    }
    public Object clone() {
        Flags flags;
        try {
            flags = (Flags) super.clone();
        } catch (CloneNotSupportedException unused) {
            flags = null;
        }
        Hashtable<String, String> hashtable = this.user_flags;
        if (null != hashtable) {
            flags.user_flags = (Hashtable<String, String>) hashtable.clone();
        }
        return flags;
    }
}
