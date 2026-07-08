package javax.mail;

import java.util.Vector;

public class FetchProfile {
    private Vector headers;
    private Vector specials;
    public static class Item {
        public static final Item CONTENT_INFO = new Item("CONTENT_INFO");
        public static final Item ENVELOPE = new Item("ENVELOPE");
        public static final Item FLAGS = new Item("FLAGS");
        private final String name;

        protected Item(String str) {
            this.name = str;
        }

        public String toString() {
            final String stringBuffer = getClass().getName() +
                    "[" +
                    this.name +
                    "]";
            return stringBuffer;
        }
    }
    public void add(Item item) {
        if (null == specials) {
            this.specials = new Vector();
        }
        this.specials.addElement(item);
    }
    public void add(String str) {
        if (null == headers) {
            this.headers = new Vector();
        }
        this.headers.addElement(str);
    }
    public boolean contains(Item item) {
        Vector vector = this.specials;
        return null != vector && vector.contains(item);
    }
    public boolean contains(String str) {
        Vector vector = this.headers;
        return null != vector && vector.contains(str);
    }
    public Item[] getItems() {
        Vector vector = this.specials;
        if (null == vector) {
            return new Item[0];
        }
        Item[] itemArr = new Item[vector.size()];
        vector.copyInto(itemArr);
        return itemArr;
    }
    public String[] getHeaderNames() {
        Vector vector = this.headers;
        if (null == vector) {
            return new String[0];
        }
        String[] strArr = new String[vector.size()];
        vector.copyInto(strArr);
        return strArr;
    }
}
